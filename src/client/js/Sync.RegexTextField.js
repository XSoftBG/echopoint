/**
 * Component rendering peer: RegexTextField
 *
 * @author Rakesh 2009-03-08
 * @version: $Id: Sync.RegexTextField.js,v 1.31 2011-06-14 07:23:38 perxi Exp $
 */
echopoint.RegexTextFieldSync = Core.extend( echopoint.internal.TextFieldSync,
{
  regexp_filter: null,
  _val_bp : null, // input.value before paste action 
  _regexp_msg : null,
  _invalid_message: null,
  invalid_msg_width: null,
  invalid_msg_alignment: null,
  invalid_msg_left: null,  
  invalid_msg_font: null,
  last_key_rejected: false,
	allowed_chars: null,
	_invalid_filter_message: null,
	_last_status: null,
	VALUE_VALID: true,
	VALUE_INVALID: false,

  /** Fires event if the value changed its status from valid to invalid and vice versa */
	_statusChanged: function(new_status)
	{
		if(this._last_status != new_status)
		{
			this._last_status = new_status;
			if(new_status == this.VALUE_VALID)
				this.component.fireExpValid();
			else
				this.component.fireExpInvalid();
		}			
	},

	doValidate: function()
	{
		if(!this.last_key_rejected)
		{
			if( this.regexp_filter && !this.regexp_filter.test( this.input.value ) ) // if the new value is invalid
			{
				this._statusChanged(this.VALUE_INVALID);
				this._statusInvalid(this._invalid_message);
			}
			else
			{
				this._statusChanged(this.VALUE_VALID);
				this._statusValid();
			}
		}
		else
			this._statusInvalid(this._invalid_filter_message); 
	},

  // call to remove the invalid message 
  _statusValid : function()
  {
    this._regexp_msg.style.display = "none";
    Echo.Sync.Color.render(this.component.render("foreground", "#000000"), this.input, "color");
		if(this.component.isEnabled())
    	Echo.Sync.Color.render(this.component.render("background", "#ffffff"), this.input, "backgroundColor");
		else
			Echo.Sync.Color.render(this.component.render("background", "#ffffff"), this.input, "disabledBackground");
  },

  // call to set the invalid message to the invalid component
  _statusInvalid : function(msg)
  {
		if(!this.last_key_rejected) // key not accepted => we haven't changed the value => should not change the status!
			this._statusChanged(this.VALUE_INVALID);
    if (msg)
    { 
			// does not show an error message (except invalid filter message) when the value is empty 
			if(this.input.value == "" && !this.last_key_rejected) 
			{
				this._statusValid(); // if there was an error we need to hide it
				return;
			}
      this.positionMsgComp(msg);
      this._regexp_msg.style.display = "";
			var invalid_fg_color = this.component.render("invalid_foreground", null);
			if(invalid_fg_color)
      	Echo.Sync.Color.render(invalid_fg_color, this.input, "color");
			var invalid_bg_color = this.component.render("invalid_background", null);
			if(invalid_bg_color)
      	Echo.Sync.Color.render(invalid_bg_color, this.input, "backgroundColor");
  
      Echo.Sync.Color.render(this.component.render("invalid_msg_foreground", "#000000"), this._regexp_msg, "color");
      Echo.Sync.Color.render(this.component.render("invalid_msg_background", "#ffffff"), this._regexp_msg, "backgroundColor");
    }
  },

  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.REGEX_TEXT_FIELD, this );
  },

  $construct: function()
  {
    echopoint.internal.TextFieldSync.call( this );
  },

  $virtual: 
  {
    getSupportedPartialProperties: function() 
    {
      var v = echopoint.internal.TextFieldSync.prototype.getSupportedPartialProperties.call(this);
      v.push(echopoint.RegexTextField.REGEX); v.push("filter"); v.push("invalid_msg"); v.push("invalid_filter_msg");
      return v;
    },

    clientKeyPressRejected: function( event ) 
    {
      Core.Web.DOM.preventEventDefault(event.domEvent);
      this.last_key_rejected=true;
      return false;
    },

    clientKeyPressAccepted: function( event )
    { 
      this.last_key_rejected=false;
			return echopoint.internal.TextFieldSync.prototype.clientKeyPress.call(this, event);
    },

		clientKeyUp: function(event) 
		{
    	event = event ? event : window.event;
			var result = echopoint.internal.TextFieldSync.prototype.clientKeyUp.call(this, event);
			var keyCode = event.domEvent.keyCode == 0 ? event.domEvent.charCode : event.domEvent.keyCode;
			if(keyCode != 9) // tab should not call doValidate ... 
				this.doValidate();
			return result;
    },
  
    /** Display default text if no value was input. */
    processBlur: function( event )
    {
			this.last_key_rejected = false;
			this.doValidate(); // will hide the invalid filter msg or the error when the value is empty
      return echopoint.internal.TextFieldSync.prototype.processBlur.call( this, event );
    },

    renderAddToParentTf: function (parentElement)
    {
      this.container = document.createElement("div");
      this.container.appendChild(this.input);
      this.container.appendChild(this._regexp_msg); 
      parentElement.appendChild(this.container);
    },

    processInputRestrictionsClear: function()
    {
      if(this.client && this.client.verifyInput(this.component))
        this.doValidate();
      echopoint.internal.TextFieldSync.prototype.processInputRestrictionsClear.call(this);
    }
  },

  // Positioning the this._regexp_msg component
  positionMsgComp: function(msg)
  {
    var message_position  = this.component.render('invalid_msg_orientation', 'bottom');
    this._regexp_msg.innerHTML = msg;
    Echo.Sync.Font.render(Echo.Sync.getEffectProperty(this.component, "invalid_msg_font", "disabledFont", true), this._regexp_msg);
    var cellBounds = new Core.Web.Measure.Bounds(this.input);
    this.invalid_msg_left = cellBounds.left;
    this._regexp_msg.style.position = 'fixed';
    
    if ( this.invalid_msg_width != -1 )
    {
      this._regexp_msg.style.width = this.invalid_msg_width + 'px';
      if ( this.invalid_msg_alignment != null )
      {
        if ( this.invalid_msg_alignment == "left" )
          this.invalid_msg_left = cellBounds.left;        
        else
        if ( this.invalid_msg_alignment == "center" )
          this.invalid_msg_left = cellBounds.left + (cellBounds.width - this.invalid_msg_width)/2;
        else
        if ( this.invalid_msg_alignment == "right" )
          this.invalid_msg_left = cellBounds.left + cellBounds.width - this.invalid_msg_width;        
      }
    }
    else
      this._regexp_msg.style.width = cellBounds.width + 'px';
    
    if ( message_position == "bottom")
    {
      this._regexp_msg.style.left = this.invalid_msg_left + 'px';
      this._regexp_msg.style.top  = cellBounds.bottom + 'px';
    }
    else
    if ( message_position == "top")
    {
      //Display the message div for few moment with left position = 0 - this._regexp_msg.style.width
      //                          and top position = '0px' to calculate the height of the message div
      this._regexp_msg.style.left = (0 - this._regexp_msg.style.width) + 'px';
      this._regexp_msg.style.top = '0px';
      this._regexp_msg.style.display = "";

      var msgCellBounds = new Core.Web.Measure.Bounds(this._regexp_msg);
      this._regexp_msg.style.left = this.invalid_msg_left - 5 + 'px';
      this._regexp_msg.style.top = (cellBounds.top - msgCellBounds.height) + 'px';

      this._regexp_msg.style.display = "none";
    }
    else
    if ( message_position == "left")
    {
      this._regexp_msg.style.left = (cellBounds.left - cellBounds.width - 5) + 'px';
      this._regexp_msg.style.top  = cellBounds.top + 'px';
    }
    else
    if ( message_position == "right")
    {
      this._regexp_msg.style.left = (cellBounds.left + cellBounds.width - 5) + 'px';
      this._regexp_msg.style.top  = cellBounds.top + 'px';
    }
  },

  // Override the TextField method: The filter function implementation.
  clientKeyPress: function( event )
	{
    event = event ? event : window.event;
    if( this.client && this.component.isActive() && !this.component.doKeyPress(event.keyCode, event.charCode) ) 
      return this.clientKeyPressAccepted(event);
  
    var charCode = event.domEvent.charCode;
    // Skip copy, cut, paste, select-all, arrow left, arrow right etc.
		if( !event.domEvent.metaKey && !event.domEvent.ctrlKey && !event.domEvent.altKey && charCode > 31 && charCode != 37 && charCode != 39 && this.regexp_filter)
    {
      var position = this.getCaretPosition();
			var new_char = String.fromCharCode( charCode );
      var value = this.input.value.substring( 0, position ) + new_char + this.input.value.substring( position );			
			if( !this.regexp_filter.test( value )) // if the new value is invalid
			{				
				if(this.allowed_chars)
					if(!this.allowed_chars.test(new_char) ) // if the added char is invalid
						return this.clientKeyPressRejected( event ); // refuse it
					else
					{
						this._statusChanged(this.VALUE_INVALID);
						return this.clientKeyPressAccepted( event );
					}
				else
					return this.clientKeyPressRejected( event ); // there are no allowed_chars & the regex is not valid => refuse it
			}
			else
				this._statusChanged(this.VALUE_VALID);
    }
    return this.clientKeyPressAccepted( event );
	},

  // Run the processPaste after the input field has had text pasted into it (copy+paste from a keyboard or a mouse)
  processPaste: function(event) 
  {
    event = event ? event : window.event;
    this._val_bp = this.input.value;
    Core.Web.Scheduler.run(Core.method(this, this._filterInput), 1, false); // Hack: we have new_value after 1milisec :-)
  },
  
  _filterInput: function() 
  {
    if( this.allowed_chars && !this.allowed_chars.test( this.input.value ) ) 
			this.input.value = this._val_bp;		
		this.doValidate();
  },

	_getDefaultStatus: function()
	{
		if(this.regexp_filter == null)			
			return this.VALUE_VALID;
		return this.regexp_filter.test("") ? this.VALUE_VALID : this.VALUE_INVALID; // decides if empty value is valid		
	},

  // Override the TextField method
  renderAdd : function(update, parentElement) 
  {
    this._regexp_msg = document.createElement("div");
    this._regexp_msg.style.display = "none";
    this._invalid_message = this.component.render('invalid_msg', null);
		this._invalid_filter_message = this.component.render('invalid_filter_msg', this._invalid_message);
    this.invalid_msg_width = this.component.render('invalid_msg_width', null);
    this.invalid_msg_alignment = this.component.render('invalid_msg_alignment', null);
    echopoint.internal.TextFieldSync.prototype.renderAdd.call(this, update, parentElement);
    var regex = this.component.render( echopoint.RegexTextField.REGEX );
		var filter = this.component.render( "filter", null);
		if(filter)
		{
			filter = "^(" + filter + ")+$";
			this.allowed_chars = 	new RegExp(filter);
		}
    if(regex)
		{
			if(this._lowerCase || this._upperCase)
      	this.regexp_filter = new RegExp(regex, "i");
			else
				this.regexp_filter = new RegExp(regex);
			this._last_status = this._getDefaultStatus();
		}
		else
			this._last_status = true; // no regexp => empty value is valid
		if(this._lastProcessedValue != null) // not rendered for the first time
			this.doValidate();
    Core.Web.Event.add(this.input, "paste", Core.method(this, this.processPaste), false);
  },

  /** @see Echo.Render.ComponentSync#renderDisplay */
  renderDisplay: function()
  {
    echopoint.internal.TextFieldSync.prototype.renderDisplay.call(this);
    if( this._regexp_msg.style.display != "none" )
    {
      this.positionMsgComp( this._regexp_msg.innerHTML );
      this._regexp_msg.style.display = "";
    }
  },

  // Override the TextField method
  renderUpdate: function(update) 
  {
    var regex = update.getUpdatedProperty( echopoint.RegexTextField.REGEX );
    if(regex)
		{			
			if(this._lowerCase || this._upperCase)
      	this.regexp_filter = new RegExp(regex.newValue, "i");
			else
				this.regexp_filter = new RegExp(regex.newValue);
		}
		var filter = update.getUpdatedProperty( "filter");
		if(filter)
		{
			filter = "^(" + filter.newValue + ")+$";
			this.allowed_chars = 	new RegExp(filter);
		}
    var invalid_msg = update.getUpdatedProperty("invalid_msg");
		var filter_msg  = update.getUpdatedProperty("invalid_filter_msg");
		if(filter_msg)
			this._invalid_filter_message = filter_msg.newValue;
    else
    if(invalid_msg && this._invalid_filter_message == this._invalid_message)
      this._invalid_filter_message = invalid_msg.newValue;
    if(invalid_msg) this._invalid_message = invalid_msg.newValue;
		if(regex || filter || invalid_msg || filter_msg)
		{
			if(this._lastProcessedValue != null) // not rendered for the first time
				this.doValidate();
			this._last_status = this._getDefaultStatus();
		}
		return echopoint.internal.TextFieldSync.prototype.renderUpdate.call(this, update);
  }
});

/**
 * Remote regex text field component.
 */
echopoint.RemoteRegexTextField = Core.extend(echopoint.RegexTextField,
{
  /** @see Echo.Component#componentType */
  componentType: "RxRTF",

  $load: function()
  {
    Echo.ComponentFactory.registerType("RxRTF", this);
  }
});

/**
 * Remote regex text field component synchronization peer.
 */
echopoint.RemoteRegexTextField.Sync = Core.extend(echopoint.RegexTextFieldSync,
{
  $load: function()
  {
    Echo.Render.registerPeer("RxRTF", this);
  },

  $include: [ Echo.Sync.RemoteTextComponent._SyncMixins],

  /** Constructor. */
  $construct: function()
  {
    echopoint.RegexTextFieldSync.call(this);
    this._remoteInit();
  },

  /** @see Echo.Sync.TextComponent#getSupportedPartialProperties */
  getSupportedPartialProperties: function()
  {
    return this._remoteGetSupportedPartialProperties(
        echopoint.RegexTextFieldSync.prototype.getSupportedPartialProperties.call(this));
  },

  /** @see Echo.Sync.TextComponent#processBlur */
  processBlur: function(e)
  {
    echopoint.RegexTextFieldSync.prototype.processBlur.call(this, e);
    this._remoteBlur();
  },

  /** @see Echo.Sync.TextComponent#processFocus */
  processFocus: function(e)
  {
    echopoint.RegexTextFieldSync.prototype.processFocus.call(this, e);
    this._remoteFocus();
  },

  /** @see Echo.Render.ComponentSync#renderAdd */
  renderAdd: function(update, parentElement)
  {
    echopoint.RegexTextFieldSync.prototype.renderAdd.call(this, update, parentElement);
    this._remoteAdd();
  },

  /** @see Echo.Render.ComponentSync#renderDispose */
  renderDispose: function(update)
  {
    this._remoteDispose();
    echopoint.RegexTextFieldSync.prototype.renderDispose.call(this, update);
  },

  /** @see Echo.Render.ComponentSync#renderUpdate */
  renderUpdate: function(update)
  {
    this._remoteUpdate();
    echopoint.RegexTextFieldSync.prototype.renderUpdate.call(this, update);
  }
});
