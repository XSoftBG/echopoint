/**
 * Component rendering peer: AutoLookupTextField
 *
 * @author Rakesh 2009-03-29
 * @version: $Id: Sync.AutoLookupSelectField.js,v 1.48 2011/10/14 10:05:33 perxi Exp $
 */
echopoint.AutoLookupSelectFieldSync = Core.extend( echopoint.RegexTextFieldSync,
{
  _outstandingAjaxCall: null,
  _searchingStatusDivE: null,
  _notFoundStatusDivE: null,
  _popupDivE: null,
  _searchEntries: [],
  _selectedOpt: null,
  _bodyE: null,
  _autoselect: true,
  _comboMode: false,
  _comboAllEntries: [],
  _serviceURI: null,
  _serviceURICombo: null,
  _firstEntryDiv: null,
  _lastEntryDiv: null,
  _popupButton: null,
  _selectedBG: null,
  _selectedFG: null,
  _actionClick: false,
  _cs: null, // case sensitive
  _deferredSelectedOpt: null,
  _deferredFoundOpt: null,
  _popupDivInitSize: null,
  _lastFilteredVal: '',
  _addableMode: true,
  _lm_portion: null, // lazy mode - portion
  _maxMenuSize: null,
  _processClickBodyRef: null,
  _processMouseScrollRef: null,
  _tooltipMode: false,
  _excludeValue: false,
  _outstandingAjaxCallResponseHandlerRef: null,

  $static: 
  {
    DEFAULT_OPTIONS_MENU_BORDER: "1px groove #bbbbbb",
    DEFAULT_OPTIONS_MENU_BACKGROUND: "#ffffff",  
    DEFAULT_SELECTED_BACKGROUND: "#678db2",
    DEFAULT_SELECTED_FOREGROUND: "#ffffff",
    OPTIONS_MENU_HEIGHT: 169,
    
    findPositionWithScrolling: function(element)
    {
      var getNextAncestor = function(e) 
      {
        var actualStyle;
        if (window.getComputedStyle)
          actualStyle = getComputedStyle(e, null).position;
        else if(e.currentStyle)
          actualStyle = e.currentStyle.position;
        else
          actualStyle = e.style.position;
          // --------------------------------------------------------------------------
          // * fallback for browsers with low support - only reliable for inline styles
        
        // * the offsetParent of a fixed position element is null so it will stop
        // ----------------------------------------------------------------------
        if (actualStyle == 'absolute' || actualStyle == 'fixed')
          return e.offsetParent;
        
        return e.parentNode;
      }
    
      if (typeof(element.offsetParent) != 'undefined')
      {
        var originalElement = element;      

        for (var posX = 0, posY = 0; element; element = element.offsetParent)
        {
          posX += element.offsetLeft;
          posY += element.offsetTop;
        }

        // * older browsers cannot check element scrolling
        // -----------------------------------------------
        if(!originalElement.parentNode || !originalElement.style || typeof(originalElement.scrollTop) == 'undefined')          
          return [posX, posY];

        element = getNextAncestor(originalElement);
        while(element && element != document.body && element != document.documentElement)
        {
          posX -= element.scrollLeft;
          posY -= element.scrollTop;
          element = getNextAncestor(element);
        }
        
        return [posX, posY];
      }
      else
        return [element.x, element.y];
    }
  },

  $virtual:
  { 
    renderAddToParentTf: function (parentElement)
    {
      echopoint.RegexTextFieldSync.prototype.renderAddToParentTf.call(this,parentElement);
      this._popupButton = document.createElement("img");
      Echo.Sync.ImageReference.renderImg( this.component.render('popupIcon', null), this._popupButton );
      this._popupButton.setAttribute("style", "margin-left:2px;position:absolute");
      Core.Web.Event.add(this._popupButton, "click", Core.method(this, this._processClickPopupButton), false);
      this.container.style.position = "relative";
      this.container.appendChild(this._popupButton);
    },

    processInputRestrictionsClear: function()
    {
      if(this.client && this.client.verifyInput(this.component))
      {
        if( this._deferredSelectedOpt )
        {
          this._storeOption(this._deferredSelectedOpt);
          this._closeDropDown();
        }
        else
        if( this._deferredFoundOpt )
          this._storeOptionValue( this._deferredFoundOpt );

        this._deferredFoundOpt = null;
        this._deferredSelectedOpt = null;
        if( !this._focused ) this._processAddableMode();
      }
      else
      {
        this._deferredFoundOpt = null;
        this._deferredSelectedOpt = null;
      }
      echopoint.RegexTextFieldSync.prototype.processInputRestrictionsClear.call(this);
    }
  },

  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.AUTO_LOOKUP_SELECT_FIELD, this );
  },

  $construct: function()
  {
    echopoint.RegexTextFieldSync.call( this );
    this._superStoreValue = this._storeValue; // hack: override TextComponent::_storeValue
    this._storeValue      = this._storeInputValue;
    this._processClickBodyRef = Core.method(this, this._processClickBody);
    this._processMouseScrollRef = Core.method(this, this._processMouseScroll);
    this._outstandingAjaxCallResponseHandlerRef = Core.method(this, this._ajaxResponse);
  },

  getSupportedPartialProperties: function() 
  {
    var v = echopoint.RegexTextFieldSync.prototype.getSupportedPartialProperties.call(this);
    v.push("actionClick");
    v.push("comboListChanged");
    v.push("selectedBG");
    v.push("selectedFG");
    v.push("optMenuBG");
    v.push("optMenuBorder");
    v.push("key");
    v.push("searchVal");
    v.push("background");
    return v;
  },

  renderUpdate: function(update)
  {
    var status = echopoint.RegexTextFieldSync.prototype.renderUpdate.call(this, update);
    var v = update.getUpdatedProperty("selectedBG");
    if( v ) this._selectedBG = v.newValue;
    v = update.getUpdatedProperty("selectedFG");
    if( v ) this._selectedFG = v.newValue;
    v = update.getUpdatedProperty("optMenuBG");
    if( v ) Echo.Sync.Color.render( v.newValue, this._popupDivE, "backgroundColor" );
    v = update.getUpdatedProperty("optMenuBorder");
    if( v ) Echo.Sync.Border.render( v.newValue, this._popupDivE );
    v = update.getUpdatedProperty("actionClick");
    if( v ) this._actionClick = v.newValue;
    if( update.getUpdatedProperty("comboListChanged") ) this._makeAjaxCall(null);  // load combo list
    if( update.getUpdatedProperty("background") )       Echo.Sync.Color.render(Echo.Sync.getEffectProperty(this.component, "background", "disabledBackground", true), this.input, "backgroundColor");
    return status;
  },

  /**
	 * Echo life cycle method (on creation)
	 */
  renderAdd: function( update, parentElement ) 
  {
    echopoint.RegexTextFieldSync.prototype.renderAdd.call(this, update, parentElement); //call super method
    var searchBarSearchingIcon = this.component.render('searchBarSearchingIcon', null);
    var searchBarSearchingText = this.component.render('searchBarSearchingText', 'Searching...');
    this._autoselect           = this.component.render('autoSelect', true);
    this._comboMode            = this.component.render('comboboxMode', false);
    this._selectedBG           = this.component.render('selectedBG', echopoint.AutoLookupSelectFieldSync.DEFAULT_SELECTED_BACKGROUND);
    this._selectedFG           = this.component.render('selectedFG', echopoint.AutoLookupSelectFieldSync.DEFAULT_SELECTED_FOREGROUND);
    this._actionClick          = this.component.render('actionClick', false);
    this._addableMode          = this.component.render('addableMode', true);
    this._cs                   = this.component.render('caseSensitive', false) && !this._upperCase && !this._lowerCase ? "" : "i";
    this._lm_portion           = this.component.render('lazyMode', null);
    this._tooltipMode          = this.component.render('tooltipsMode', false);
    this._excludeValue         = this.component.render('excludeValue', false);
    this._serviceURICombo      = "?sid=echopoint.AutoLookupSelectService&elementId=" + this.component.renderId.substring(2) +
                                 (this.client._uiid !== null ? "&uiid="+this.client._uiid : "");
    this._serviceURI           = this._serviceURICombo + "&searchValue=";

    this._popupDivE = document.createElement('div'); // create drop down div
    this._popupDivE.id = this.component.renderId + '_DDMenu';
    this._popupDivE.style.position   = 'fixed';
    this._popupDivE.style.visibility = 'hidden';
    this._popupDivE.style.overflow   = 'auto';
    this._popupDivE.style.maxHeight  = echopoint.AutoLookupSelectFieldSync.OPTIONS_MENU_HEIGHT + 'px';
    this._popupDivE.style.zIndex     = 1001;
    Echo.Sync.Color.render( this.component.render( 'optMenuBG', echopoint.AutoLookupSelectFieldSync.DEFAULT_OPTIONS_MENU_BACKGROUND ), this._popupDivE, "backgroundColor" );
    Echo.Sync.Border.render( this.component.render( 'optMenuBorder', echopoint.AutoLookupSelectFieldSync.DEFAULT_OPTIONS_MENU_BORDER ), this._popupDivE );
    this._bodyE = document.getElementsByTagName('body')[0];
    this._bodyE.appendChild(this._popupDivE);
    this._searchingStatusDivE = document.createElement('div'); // searching status bar
    this._searchingStatusDivE.id = -1;
    if( searchBarSearchingIcon || searchBarSearchingText )
    {
      var xhtml = '<table cellpadding=0 cellspacing=0 border=0><tbody><tr>';
      if( searchBarSearchingIcon ) xhtml += '<td><img src="' + searchBarSearchingIcon + '"/></td>';
      xhtml += '<td>' + searchBarSearchingText + '</td></tr></tbody></table>';
      this._searchingStatusDivE.innerHTML = xhtml;
    }
    this._popupDivE.appendChild(this._searchingStatusDivE);

    this._notFoundStatusDivE = document.createElement('div'); // not-found bar
    this._notFoundStatusDivE.id = -2;
    this._notFoundStatusDivE.innerHTML = this.component.render('noMatchingOptionText', 'No results found');
    this._notFoundStatusDivE.style.display = 'none';
    this._popupDivE.appendChild(this._notFoundStatusDivE);
    this._popupDivInitSize = this._popupDivE.childElementCount;
    if(this._comboMode) this._makeAjaxCall(null);  // load combo list
    Core.Web.Event.add( this._popupDivE, "click", Core.method(this, this._processClickOption), false );
    Core.Web.Event.add( this._popupDivE, "mouseover", Core.method(this, this._processMouseoverOption), false );
    Core.Web.Event.add( this._popupDivE, "scroll", Core.method(this, this._processScrollMenu), false );
  },

  getFocusFlags: function()
  {
    return this._isDropDownVisible() ? 0 : echopoint.RegexTextFieldSync.prototype.getFocusFlags.call(this); //prevent that up/down keys change the focus to another component scroll instead up/down in the popup entry list
  },

  isTextFieldEditable: function() { return !this.input.readOnly && this.component.isEnabled(); },
 
  renderDisplay: function()
  {
    echopoint.RegexTextFieldSync.prototype.renderDisplay.call(this); //call super method
    var popup_ico_height = new Core.Web.Measure.Bounds(this._popupButton).height;
    if(popup_ico_height === 0) popup_ico_height = 10;
    this._popupButton.style.marginTop = (new Core.Web.Measure.Bounds(this.input).height- popup_ico_height)/2 + "px";
  },

  /**
	 * Echo life cycle method (on deletion)
	 */
  renderDispose: function( update ) 
  {
    this._closeDropDown();
    this._deferredFoundOpt = null;
    this._deferredSelectedOpt = null;
    this._popupDivE.parentNode.removeChild(this._popupDivE);
    Core.Web.Event.removeAll( this._popupDivE );
    Core.Web.Event.removeAll( this._popupButton );
    this._popupDivE = null;
    echopoint.RegexTextFieldSync.prototype.renderDispose.call(this, update);
  },

  /**
   * Calculate a combobox entries list
   */ 
  _calcComboList: function(val)
  {
    this._maxMenuSize = this._lm_portion;
    if( val === null ) // show full list
      this._searchEntries = this._comboAllEntries;
    else
    {
      this._searchEntries = [];
      var desired_eidx = null;
      var val_reg_expr = new RegExp(val, this._cs);
      for( var i = 0; i < this._comboAllEntries.length; i++ ) 
      {
        var entry = this._comboAllEntries[i];
        if( val_reg_expr.test(entry.searchVal) )
        {
          this._searchEntries.push(entry);
          if( !desired_eidx && val === entry.searchVal )
            desired_eidx = this._searchEntries.length-1;
        }
      }
      if( desired_eidx  )
      {
        if( this._searchEntries.length > 1 )
        {
          var first_entry = this._searchEntries[0];
          this._searchEntries[0] = this._searchEntries[desired_eidx];
          this._searchEntries[desired_eidx] = first_entry;
        }
        return this._searchEntries[0];
      }
    }
    return {
      key: null, 
      searchVal: null
    };
  },

  /**
   * Calculate and show a combobox entries list
   */ 
  _showComboList: function(val)
  {
    var opt = this._calcComboList(val);
    this._showDropDown();
    return opt;
  },

  _showOptionsMenu: function(val)
  {
    if( this._comboMode )
    {
      var opt = this._showComboList(val);
      if( val !== null )
        if(this.client.verifyInput(this.component))
          this._storeOptionValue( opt );
        else
          this._deferredFoundOpt = opt; // waiting for callback: _processInputRestrictionsClear
    }
    else
      this._makeAjaxCall(val);
  },

  /**
	 * Show or hide the 'Searching' status
	 */	
  _updateSearchUI: function( isSearching ) 
  {
    if(this._notFoundStatusDivE.innerHTML !== null && isSearching) this._notFoundStatusDivE.style.display = 'none';
    if(this._searchingStatusDivE.innerHTML !== null) this._searchingStatusDivE.style.display = isSearching ? 'block' : 'none';
  },
	
  _createEntryDiv: function(entry)
  {
    var entryDiv = document.createElement('div');
    entryDiv.id = entry.idx;
    entryDiv.innerHTML = entry.value;
    entryDiv.style.font = this.input.style.font;
    entryDiv.style.color = this.input.style.color;
    entryDiv.style.cursor = 'default';
    this._popupDivE.insertBefore(entryDiv, this._searchingStatusDivE); // we always have the statusbar div as a reference point
    return entryDiv;
  },

  _createEntryDivTooltip: function(entry)
  {
    this._createEntryDiv(entry).title = entry.searchVal;
  },

  _updateEntryDiv: function(entry, entryDiv)
  {
    var entry_value = entry.value;
    if( entryDiv.style.display === 'none' ) entryDiv.style.display = 'block';
    if( entry_value !== entryDiv.innerHTML )
    {
      entryDiv.id = entry.idx;
      entryDiv.innerHTML = entry_value;
      return true;
    }
    return false;
  },

  _updateEntryDivTooltip: function(entry, entryDiv)
  {
    if( this._updateEntryDiv(entry, entryDiv) ) entryDiv.title = entry.searchVal;
  },

  _updateDropDown: function()
  {
    this._updateOptions();
    if(this._autoselect) this._selectOption(this._firstEntryDiv);  // first is auto selected when adding
    if(this._notFoundStatusDivE.innerHTML !== null)
      this._notFoundStatusDivE.style.display = this._searchEntries.length === 0 ? 'block' : 'none'; // if we have no matching options then show some text indicating this
  },

  _updateOptions: function()
  {
    var entries       = this._popupDivE.getElementsByTagName('div');
    var entries_size  = entries.length-this._popupDivInitSize;
    var e2add_size    = this._maxMenuSize ? Math.min(this._searchEntries.length, this._maxMenuSize) : this._searchEntries.length;
    var e2update_size = Math.min(e2add_size, entries_size);

    if( this._tooltipMode )
    {
      for(var i = 0; i < e2update_size; i++)
        this._updateEntryDivTooltip( this._searchEntries[i], entries[i] );
      for(var i = e2update_size; i < e2add_size; i++)
        this._createEntryDivTooltip( this._searchEntries[i] );
    }
    else
    {
      for(var i = 0; i < e2update_size; i++)
        this._updateEntryDiv( this._searchEntries[i], entries[i] );
      for(var i = e2update_size; i < e2add_size; i++)
        this._createEntryDiv( this._searchEntries[i] );
    }

    for(var i = e2add_size; i < entries_size; i++)
    {
      var entryDiv = entries[i];
      if(entryDiv.style.display !== 'none') entryDiv.style.display = 'none';
    }
    if( e2add_size > 0 )
    {
      this._firstEntryDiv = entries[0];
      this._lastEntryDiv  = entries[e2add_size-1];
    }
    else
    {
      this._firstEntryDiv = null;
      this._lastEntryDiv  = null;
    }
  },
	
  /**
	 * Called to select a specific option as the current one
	 */
  _selectOption: function( newSelectedOptionE ) 
  {                           
    if( newSelectedOptionE && (newSelectedOptionE === this._popupDivE || newSelectedOptionE.id < 0) ) return; // it's not an option
    if( this._selectedOpt ) {
      this._selectedOpt.style.background = this._popupDivE.style.background;
      this._selectedOpt.style.color = this.input.style.color;
    }// unselect: de-hilight to previous selected item
    this._selectedOpt = newSelectedOptionE;
    if( this._selectedOpt ) {
      this._selectedOpt.style.background = this._selectedBG;
      this._selectedOpt.style.color = this._selectedFG;
    } // select: hightlight new selected option
  },

  _isDropDownVisible: function() { return this._popupDivE.style.visibility !== 'hidden'; },

  _showDropDown: function()
  {
    this._updateDropDown();
    if( this._searchEntries.length === 0 )
    {
      this._hideDropDown();
      return;
    }
    if( this._isDropDownVisible() ) return;
    
    var cellBounds = new Core.Web.Measure.Bounds(this.input);
    var inputPosition = echopoint.AutoLookupSelectFieldSync.findPositionWithScrolling(this.input);
    
    this._popupDivE.style.left       = inputPosition[0] + 'px';
    this._popupDivE.style.top        = (inputPosition[1] + cellBounds.height) + 'px';    
    this._popupDivE.style.minWidth   = (cellBounds.width - 1) + 'px';
    this._popupDivE.style.width      = this._popupDivE.style.minWidth;
    this._popupDivE.style.visibility = 'visible';
    Core.Web.Event.add(this._bodyE, "mousedown", this._processClickBodyRef, true); // attach document listener so we can know about outside clicks    
    
    // * attach document listener so we can know about mouse scroll
    // ------------------------------------------------------------
    if (Core.Web.Env.BROWSER_CHROME)
      Core.Web.Event.add(this._bodyE, "mousewheel", this._processMouseScrollRef, true);
    else
      Core.Web.Event.add(this._bodyE, "DOMMouseScroll", this._processMouseScrollRef, true);
  },
  
  _hideDropDown: function() 
  {
    if( !this._isDropDownVisible() ) return;
    if(this._selectedOpt && this.input.value.toUpperCase() === this._selectedOpt.innerHTML.toUpperCase())
      { this._storeOption( this._comboAllEntries[this._selectedOpt.id] ); this._superStoreValue({ keyCode: 13, type: "keydown" }); }
    this._selectOption(null);
    this._popupDivE.style.visibility = 'hidden';
    Core.Web.Event.remove(this._bodyE, "mousedown", this._processClickBodyRef, true);
    
    // * detach document listener so we can know about mouse scroll
    // ------------------------------------------------------------
    if (Core.Web.Env.BROWSER_CHROME)
      Core.Web.Event.remove(this._bodyE, "mousewheel", this._processMouseScrollRef, true);
    else
      Core.Web.Event.remove(this._bodyE, "DOMMouseScroll", this._processMouseScrollRef, true);
  },
	
  /**
    * Called when the user presses the search button or click popup-button. We AJAX back to the server to get some new entries based on the argument 'val'.
    */
  _makeAjaxCall: function(val) 
  {
    this._cancelAnyAjaxCall(); // if we have an outstanding AJAX call in progress then we should cancell it. It may complete but we dont care for its results any more.
    this._updateSearchUI(true); // show the 'Searching status bar'
    this._outstandingAjaxCall = new Core.Web.HttpConnection( val ? this._serviceURI + encodeURI(val) : this._serviceURICombo, "GET", null, "text/xml" ); // Make an AJAX call to search for new values    
    this._outstandingAjaxCall.addResponseListener(this._outstandingAjaxCallResponseHandlerRef);
    this._outstandingAjaxCall.connect();
  },

  _cancelAnyAjaxCall: function() 
  {
    if( this._outstandingAjaxCall )
    {
      this._outstandingAjaxCall.removeResponseListener(this._outstandingAjaxCallResponseHandlerRef);
      this._outstandingAjaxCall = null;
      this._updateSearchUI(false); // hide the 'Searching status bar'
    }
  },

  /**
	 * Asynchronous ajax callback method
	 */	
  _ajaxResponse: function(echoEvent) 
  {
    try
    {
      this._searchEntries = [];
      if( echoEvent.valid && echoEvent.source.getResponseXml() )
      {
        // OK we get a series of XML messages back here just like the pre-populate message so add those entries
        var autoLookUpModelE = echoEvent.source.getResponseXml().documentElement.getElementsByTagName('autoLookupModel');
        if( autoLookUpModelE.length > 0 ) 
        {
          var entriesNL = autoLookUpModelE[0].getElementsByTagName('entry');
          if( !this._tooltipMode && this._excludeValue )
            for( var index = 0; index < entriesNL.length; index++ )  //convert from xml entry to LookupEntry
            {
              var entryE = entriesNL[index];
              var key    = entryE.getElementsByTagName('key')[0].firstChild;
              var search = entryE.getElementsByTagName('searchVal')[0].firstChild;
              this._searchEntries[index] = {
                idx: index, 
                value: (search ? search.data : "&nbsp"), 
                key: (key ? key.data : ""), 
                searchVal: (search ? search.data : "")
              };
            }
          else
            for( var index = 0; index < entriesNL.length; index++ )  //convert from xml entry to LookupEntry
            {
              var entryE = entriesNL[index];
              var value  = entryE.getElementsByTagName('value')[0].firstChild;
              var key    = entryE.getElementsByTagName('key')[0].firstChild;
              var search = entryE.getElementsByTagName('searchVal')[0].firstChild;
              this._searchEntries[index] = {
                idx: index, 
                value: (value ? value.data : "&nbsp;"), 
                key: (key ? key.data : ""), 
                searchVal: (search ? search.data : "")
              };
            }
        }
      }
      else
      if(!echoEvent.valid)
        throw new Error( "Invalid HTTP response, received status: " + echoEvent.source.getStatus() );
    }
    finally 
    {
      this._cancelAnyAjaxCall();
    }
    this._comboAllEntries = this._searchEntries;
    if( !this._comboMode ) this._showDropDown(); // now popilate the popup and display it
    else
    if( this._isDropDownVisible() ) this._showComboList(null);
  },
	
  /**
	  * This not only hides the drop down but it cancels any AJAX calls and removes options as well.
	  */
  _closeDropDown: function() 
  {
    this._cancelAnyAjaxCall();
    this._hideDropDown();
  },
	
  _processLazyMode: function()
  {
    if( this._popupDivE.scrollTop === (this._popupDivE.scrollHeight - this._popupDivE.clientHeight) && this._maxMenuSize && this._maxMenuSize < this._searchEntries.length) // reaches the end of scroll and we have more options
    {
      this._maxMenuSize += this._lm_portion;
      var lastEntry = this._lastEntryDiv;
      this._updateOptions();
      if(lastEntry.nextSibling) this._popupDivE.scrollTop += new Core.Web.Measure.Bounds(lastEntry.nextSibling).height;
    }
  },

  _processAddableMode: function()
  {
    if(!this._addableMode && this.component.get('key') === null && this._deferredSelectedOpt === null && this._deferredFoundOpt === null)
      this.input.value = "";
  },

  _scrollToPage: function( forward )
  {
    var scroll_pos_begin = this._popupDivE.scrollTop;
    var maxscroll_pos    = this._popupDivE.scrollHeight - this._popupDivE.clientHeight;
    do
    {
      this._scrollToOption(forward);
    }
    while( Math.abs(this._popupDivE.scrollTop - scroll_pos_begin) <= echopoint.AutoLookupSelectFieldSync.OPTIONS_MENU_HEIGHT &&
      this._popupDivE.scrollTop < maxscroll_pos && this._popupDivE.scrollTop > 0 );
  },

  /**
	 * Move the selection on option one forward or backward
	 */
  _scrollToOption: function( forward ) 
  {
    if( this._selectedOpt )
    {
      var newSelectionE = (forward ? this._selectedOpt.nextSibling : this._selectedOpt.previousSibling);
      if( newSelectionE && (newSelectionE !== this._popupDivE && newSelectionE.id >= 0) )
      {
        this._popupDivE.scrollTop += (forward ? new Core.Web.Measure.Bounds(this._selectedOpt).height : -new Core.Web.Measure.Bounds(newSelectionE).height);
        this._selectOption(newSelectionE);
      }
      else
        this._processLazyMode();
    }
    else
    if( !this._autoSelect && this._firstEntryDiv !== null )
      this._selectOption( this._firstEntryDiv );
  },

  _storeInputValue: function(event)
  {
    if( !this._focused )
      this._processAddableMode();
    else
    if( event && this.client && this.component.isActive() && this.isTextFieldEditable() )
      switch( event.keyCode )
      {
        case 33: // PAGE UP
          if( event.type === "keydown" ) this._scrollToPage(false);
          break;
        case 34: // PAGE DOWN
          if( event.type === "keydown" ) this._scrollToPage(true);
          break;
        case 38: // UP ARROW
          if( event.type === "keydown" ) this._scrollToOption(false);
          break;
        case 40: // DOWN ARROW
          if( event.type === "keydown" ) this._scrollToOption(true);
          break;
        case 13: // ENTER
          this._tryStoreOption(event);
          this._processAddableMode();
          return; // _tryStoreOption always call _superStoreValue
        case 27: // ESC
        case 9 : // TAB
          this._closeDropDown();
          break;
        default: // all other keys
          if( this._lastFilteredVal !== this.input.value )
          {
            this._lastFilteredVal = this.input.value;
            this._showOptionsMenu( this.input.value );
          }
          break;
      }

    this._superStoreValue(event)
  },

  _storeOptionValue: function(opt)
  {
    this.component.set("key",  opt.key, true);
    this.component.set("searchVal", opt.searchVal, true);
  },

  _storeOption: function(opt)
  {
    this.doValidate();
    this._storeOptionValue(opt);
  },
 
  /**
	 * One entry has been selected (by click or by enter key). Set the appropiate text, key, searchVal and close the popup.
	 */
  _tryStoreOption: function(event)
  {
    if( this._selectedOpt )
    {
      var selectedEntry = this._comboAllEntries[this._selectedOpt.id];
      this.input.value = selectedEntry.searchVal;
      if(this.client.verifyInput(this.component))
      {
        this._storeOption( selectedEntry );
        this._storeSelection();
        this._superStoreValue(event);
      }
      else
      {
        this._deferredSelectedOpt = selectedEntry;
        this._superStoreValue(event); // register processInputRestrictionsClear listener !
        return; // waiting for callback: processInputRestrictionsClear
      }
    }
    this._closeDropDown();
  },
	
  /**
	 * Action listener (when the user clicks on an entry)
	 */
  _processClickOption: function(event)
  {
    this._selectOption( Core.Web.DOM.getEventTarget( event ? event : window.event ) );
    this._tryStoreOption( this._actionClick ? {
      keyCode: 13, 
      type: "keydown"
    } /*simulated ENTER*/ : null );
    this.input.focus();  // we never want focus on the popup
  },

  /**
	 * Mouse listener (creates rollover-effect)
	 */
  _processMouseoverOption: function(event)
  {
    this._selectOption( Core.Web.DOM.getEventTarget( event ? event : window.event ) );
  },

  _processScrollMenu: function(event) { this._processLazyMode(); },

  _processClickBody: function(event)
  {
    var target = Core.Web.DOM.getEventTarget( event ? event : window.event );
    if( Core.Web.DOM.isAncestorOf(this.input, target) || !Core.Web.DOM.isAncestorOf(this._popupDivE, target) && !Core.Web.DOM.isAncestorOf(this._popupButton, target) )
      this._closeDropDown();
  },
  
  _processMouseScroll: function(event)
  {
    this._processClickBody(event);
  },

  _processClickPopupButton: function(event)
  {
    if( this.isTextFieldEditable() )
    {
      if( this._isDropDownVisible() ) this._closeDropDown(); else this._showOptionsMenu(null);
      this.input.focus();  // we never want focus on the popup
    }
  }  
});
