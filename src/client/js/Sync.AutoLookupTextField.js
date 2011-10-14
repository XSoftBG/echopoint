/**
 * Component rendering peer: AutoLookupTextField
 *
 * @author Christoff Spinner 2009-03-29
 * @version: $Id: Sync.AutoLookupTextField.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.AutoLookupTextFieldSync = Core.extend( echopoint.internal.TextFieldSync, {

	_outstandingAjaxCall : null,
	_searchingStatusDivE : null,
	_notFoundStatusDivE : null,
	_popupDivE: null,
	_iframeE: null,
	_searchEntries: null,
	_selectedOptionE: null,
	_docClickHandler: null,
	_bodyE: null,

	$load: function() {
		Echo.Render.registerPeer( echopoint.constants.AUTO_LOOKUP_TEXT_FIELD, this );
	},

	$construct: function() {
		echopoint.internal.TextFieldSync.call( this );
	},

	/**
	 * Echo life cycle method (on creation)
	 */
	renderAdd: function( update, parentElement ) {
		//call super method
		echopoint.internal.TextFieldSync.prototype.renderAdd.call(this, update, parentElement);

		var searchBarSearchingIcon 			= this.component.render('searchBarSearchingIcon', null);
		var searchBarSearchingText 			= this.component.render('searchBarSearchingText', 'Searching...');
		var noMatchingOptionText	 		= this.component.render('noMatchingOptionText', 'No entries found');

		// create drop down div
		this._popupDivE = document.createElement('div');
	    this._popupDivE.id = this.elementId;
		this._popupDivE.style.position = 'absolute';
		this._popupDivE.style.visibility = 'hidden';
		this._popupDivE.style.background = '#fff';
		this._popupDivE.style.border = 'thin groove #bbb';


	    // searching status bar
		this._searchingStatusDivE = document.createElement('div');
		this._searchingStatusDivE.id = this.component.renderId + '_SearchingStatus';
		var xhtml = '<table cellpadding=0 cellspacing=0 border=0><tbody><tr>';
		if ( this._searchBarSearchingIcon ) {
			xhtml += '<td><img src="' + searchBarSearchingIcon + '"/></td>';
 		}
		xhtml += '<td>' + searchBarSearchingText + '</td></tr></tbody></table>';
		this._searchingStatusDivE.innerHTML = xhtml;
 		this._popupDivE.appendChild(this._searchingStatusDivE);

		// not-found bar
		this._notFoundStatusDivE = document.createElement('div');
		this._notFoundStatusDivE.id = this.component.renderId + '_NotFoundStatus';
		this._notFoundStatusDivE.innerHTML = noMatchingOptionText;
		this._notFoundStatusDivE.style.display = 'none';
 		this._popupDivE.appendChild(this._notFoundStatusDivE);

	    // append the popup to the main body
		this._bodyE = document.getElementsByTagName('body')[0];
		this._bodyE.appendChild(this._popupDivE);
		if (this._iframeE) {
			this._bodyE.appendChild(this._iframeE);
		}

	    this.input.setAttribute( "onKeyUp",
	        "return echopoint.AutoLookupTextFieldSync._processKeyUpStatic('" +  this.component.renderId +  "',event)" );
	},

	 /** @see Echo.Render.ComponentSync#getFocusFlags */
	getFocusFlags: function() {
		if ( this._popupDivE.style.visibility == 'hidden' ) {
			//normal focus behaviour
			return Echo.Render.ComponentSync.FOCUS_PERMIT_ARROW_UP | Echo.Render.ComponentSync.FOCUS_PERMIT_ARROW_DOWN;
		} else {
			//prevent that up/down keys change the focus to another component
			//scroll instead up/down in the popup entry list
	        return 0;
		}
    },

	/**
	 * Echo life cycle method (on deletion)
	 */
	renderDispose: function( update ) {
		this._removeOptions();
		this._popupDivE.parentNode.removeChild(this._popupDivE);
		if ( this._iframeE )	{
			this._iframeE.parentNode.removeChild(this._iframeE);
		}
		Core.Web.Event.remove( this._bodyE, "mousedown", this._docClickHandler, true );
		Core.Web.Event.removeAll( this.input );
		this.input = null;
	},

	 $static: {
	    _processKeyUpStatic: function( renderId, event ) {
			var tf = echopoint.internal.TextFieldSync.instances.map[renderId];
			if ( ! tf ) return false;
			return tf._processKeyUp( event );
	    }
	},

	/**
	 * Overriding the TextField method
	 */
	doFilter: function( echoEvent ) {
		if (echoEvent.keyCode == 13) { // ENTER
	       	this._actionOption();
		}
		return true;
  	},

	/** This method overrides the super processKeyUp(e) method. */
	_processKeyUp: function(echoEvent){
		echoEvent = (echoEvent) ? echoEvent : window.event;
		var key = echoEvent.keyCode;

	 	// ignore function keys
		if ( key >= 112 && key <= 123 ) return;

		switch ( key ) {
			// none of these affect the text field content so ignore
			case 16: //shift
			case 17: //ctrl
			case 18: //alt
			case 19: //pause
			case 20: //caps lock
			case 35: //end
			case 36: //home
			case 37: //left arrow
			case 39: //right arrow
			case 44: //print screen
			case 45: //insert
			case 144: //num lock
			case 145: //scroll lock
			case 33: //page up
			case 34: //page down
				break;
			// list box navigation keys
			case 9://tab
			case 13: //enter
			case 27: //esc
			case 38: //up arrow
			case 40: //down arrow
				this._handleNavKeys(echoEvent);
				break;
			// all other keys
			default:
				this._makeAjaxCall();
				break;
		}
		return true;
	},

	/**
	 * Show or hide the 'Searching' status
	 */
	_updateSearchUI: function( isSearching ) {
		if (isSearching) {
			this._notFoundStatusDivE.style.display = 'none';
		}
		this._searchingStatusDivE.style.display = isSearching ? 'block' : 'none';
	},

	/**
	 * Clear the list from the entries
	 */
	_removeOptions: function() {
		var childListArr = this._popupDivE.getElementsByTagName('div');
		for ( var index = childListArr.length - 1; index >= 0; index-- ) {
			var entryDiv = childListArr[index];
			if ( entryDiv.getAttribute('optionValue') ) {
				Core.Web.Event.removeAll( entryDiv );
				this._popupDivE.removeChild(entryDiv);
			}
			if (entryDiv.getAttribute('noMatchingOption')) {
				this._popupDivE.removeChild(entryDiv);
			}
		}
		this._resizeThings();
	},

	_resizeThings: function() {
		if ( this._iframeE ) {
			var cellBounds = new Core.Web.Measure.Bounds(this._popupDivE);
			this._iframeE.style.width = cellBounds.width + 'px';
			this._iframeE.style.height = cellBounds.height + 'px';
		}
	},

	/**
	 * Add the entries to the popup
	 */
	_addOptions: function() {
		// now add the new ones as mouseoverable divs with xhtml
		this._selectOption(null);
		for ( var i = 0; i < this._searchEntries.length; i++ ) {
			var entry = this._searchEntries[i];
			var entryDiv = document.createElement('div');
			this._popupDivE.insertBefore(entryDiv, this._searchingStatusDivE); // we always have the statusbar div as a reference point
			entryDiv.innerHTML = entry;
			entryDiv.id = this._popupDivE.id + '_' + i;
			// so we later can know what value this represents
			entryDiv.setAttribute('optionIndex', i);
			entryDiv.setAttribute('optionValue', entry);
			entryDiv.style.cursor = 'default';
		    Core.Web.Event.add( entryDiv, "click", Core.method( this, this._onclick ), false );
		    Core.Web.Event.add( entryDiv, "mouseover", Core.method( this, this._onmouseover ), false );

			if ( i == 0 ) { // first is auto selected when adding
				this._selectOption(entryDiv);
			}
		}
		// if we have no matching options then show some text indicating this
		if ( this._searchEntries.length == 0 ) {
			this._notFoundStatusDivE.style.display = 'block';
		} else {
			this._notFoundStatusDivE.style.display = 'none';
		}
		this._resizeThings();
	},

	/**
	 * Called to select a specific option as the current one
	 */
	_selectOption: function( newSelectedOptionE ) {
		// de-hilight to previous selected item
	    if ( this._selectedOptionE ) {
			this._selectedOptionE.style.background = '#fff';  //unselected
		}
		this._selectedOptionE = newSelectedOptionE;
		if ( this._selectedOptionE ) {
			this._selectedOptionE.style.background = '#ccc';  //selected
    	}
	},

	_showDropDown: function() {
		if ( this._popupDivE.style.visibility != 'hidden' ) {
			return;
    	}
		var cellBounds = new Core.Web.Measure.Bounds(this.input);
		this._popupDivE.style.left = cellBounds.left + 'px';
		this._popupDivE.style.top = (cellBounds.top + cellBounds.height) + 'px';
		this._popupDivE.style.minWidth = cellBounds.width + 'px';
		this._popupDivE.style.zIndex = 1001;  //zIndex + 2;
		if ( this._iframeE ) {
			this._iframeE.style.zIndex = 1000;
			this._iframeE.style.left = cellBounds.left + 'px';
			this._iframeE.style.top = (cellBounds.top + cellBounds.height) + 'px';
			this._iframeE.style.minWidth = cellBounds.width + 'px';
			this._iframeE.style.visibility = 'visible';
		}
		this._popupDivE.style.visibility = 'visible';

		// attach document listener so we can know about outside clicks
		var that = this;
		this._docClickHandler = function( echoEvent ) {
			var targetE = echoEvent.target;
			if ( Core.Web.DOM.isAncestorOf(that._popupDivE, targetE) || Core.Web.DOM.isAncestorOf(that.input, targetE) ) {
		        // its on us so safe to ignore
        		return;
		    }
		     // they have clicked outside the popup or text field so hide it
	      	that._closeDropDown();
    	};
	    Core.Web.Event.add(this._bodyE, "mousedown", this._docClickHandler, true );
  	},

  	_hideDropDown: function() {
		this._popupDivE.style.visibility = 'hidden';
		if ( this._iframeE )	{
			this._iframeE.style.visibility = 'hidden';
		}
		Core.Web.Event.remove( this._bodyE, "mousedown", this._docClickHandler, true );
	},


	/**
	 * Called when the user presses the search button.	We AJAX back to the server to get some
	 * new entries based on the current value
	 */
	_makeAjaxCall: function() {
		// if we have an outstanding AJAX call in progress then we should cancell it.It may
		// complete but we dont care for its results any more.
		if ( this._outstandingAjaxCall ) {
			this._outstandingAjaxCall.cancelled = true;
			this._outstandingAjaxCall = null;
		}

		this._storeValue();
		var partialSearchValue = this.input.value;
		if ( !partialSearchValue ) {
			// update the search UI so that it not longer has "Searching...." as its text
			this._closeDropDown();
			return; // nothing to search for
		}
		// show the 'Searching status'
		this._showDropDown();
		this._updateSearchUI(true);

		// Make an AJAX call to search for new values
		var uri = "?sid=echopoint.AutoLookupService&elementId=" + this.component.renderId + "&searchValue=" + partialSearchValue;
	    this._outstandingAjaxCall = new Core.Web.HttpConnection(uri, "GET", null, null );
	    this._outstandingAjaxCall.addResponseListener( Core.method( this, this._ajaxResponse ) );
		this._outstandingAjaxCall.connect();
	},

	_cancelAnyAjaxCalls: function() {
		this._outstandingAjaxCall = null;
	    if ( this._outstandingAjaxCall ) {
			this._outstandingAjaxCall.cancelled = true;
			this._outstandingAjaxCall = null;
		}
	},

	/**
	 * Asynchronous ajax callback method
	 */
	_ajaxResponse: function(echoEvent) {
		var ajaxCall = echoEvent.source;
		if (!echoEvent.valid || ajaxCall.cancelled || !ajaxCall.getResponseXml()) {
			ajaxCall.dispose();
			this._outstandingAjaxCall = null;
			if (echoEvent.valid) {
				return;
			} else {
				throw new Error("Invalid HTTP response, received status: " + echoEvent.source.getStatus());
			}
		}
		// it worked!
		try {
			// OK we get a series of XML messages back here just like the pre-populate message so add those entries
			var dataElement = ajaxCall.getResponseXml().documentElement;
			var autoLookUpModelE = dataElement.getElementsByTagName('autoLookupModel');
			if ( autoLookUpModelE.length > 0 ) {
				var entriesNL = autoLookUpModelE[0].getElementsByTagName('entry');
				this._searchEntries = [];
		    	for ( var index = 0, len = entriesNL.length; index < len; index++ ) {
		    		//convert from xml entry to LookupEntry
					var entryE = entriesNL[index];
					var value = entryE.getElementsByTagName('value')[0].firstChild.data;
					var xhtml = entryE.getElementsByTagName('xhtml')[0].firstChild.data;
					this._searchEntries[index] = value;
				}
			}
		} finally {
			ajaxCall.dispose();
			this._outstandingAjaxCall = null;
		}

		// now popilate the popup and display it
		this._removeOptions();
		this._addOptions();
		this._showDropDown();
		this._updateSearchUI(false);
	},

	/**
	  * This not only hides the drop down but it cancels any AJAX calls and
	  * removes options as well.
	  */
	_closeDropDown: function() {
		this._cancelAnyAjaxCalls();
		this._hideDropDown();
		this._removeOptions();
	},

	/**
	 * Move the selection one forward or backward
	 */
	_incrementOption: function( forward ) {
		if ( this._selectedOptionE ) {
			var newSelectionE = (forward ? this._selectedOptionE.nextSibling : this._selectedOptionE.previousSibling);
			if ( newSelectionE && newSelectionE != this._searchingStatusDivE ) {
				this._selectOption(newSelectionE);
			}
		}
	},

	/**
	 * One entry has been selected (by click or by enter key)
	 * Set the appropiate text and close the popup
	 */
	_actionOption: function() {
		if ( this._selectedOptionE ) {
			var optionValue = this._selectedOptionE.getAttribute('optionValue');
			this.input.value = optionValue;
			this.component.set("text", optionValue);
			this._closeDropDown();
			this.component.doAction();  //fire action event
		}
	},

	/**
	 * Action listener (when the user clicks on an entry)
	 */
	_onclick: function( echoEvent ) {
		this._selectOption(echoEvent.target);
		this._actionOption();
		// we never want focus on the popup
		this.input.focus();
	},

	/**
	 * Mouse listener (creates rollover-effect)
	 */
	_onmouseover: function( echoEvent ) {
		this._selectOption(echoEvent.target);
	},

	/**
	 *
	 */
	_handleNavKeys: function( echoEvent ) {
		switch ( echoEvent.keyCode ) {
			case 27: // ESC
			case 9 : // TAB
				this._closeDropDown();
				break;
			case 38:	// UP ARROW
				this._incrementOption(forward = false);
				break;
			case 40: // DOWN ARROW
				this._incrementOption(forward = true);
				break;
		}
	}

});

