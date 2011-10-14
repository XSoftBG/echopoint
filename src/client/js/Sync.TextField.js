/**
 * Base component rendering peer for text components.  Exposes event
 * handling functions for sub-classes.
 *
 * @author Rakesh 2009-03-05
 * @version: $Id: Sync.TextField.js,v 1.7 2011-06-14 07:23:45 perxi Exp $
 */
echopoint.internal.TextFieldSync = Core.extend( Echo.Sync.TextField,
{
  $abstract: true,

  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.TEXT_FIELD, this );
  },

  $construct: function()
  {
    Echo.Sync.TextField.call( this );
    this.superProcessClick = this._processClick;
    this._processClick     = this.processClick;
    this.superRenderAddToParent = this.renderAddToParent;
    this.renderAddToParent      = this.renderAddToParentTf;
    this._superProcessRestrictionsClear = this._processRestrictionsClear; // hack: override _processRestrictionsClear
    this._processRestrictionsClear      = this.processInputRestrictionsClear;
  },

  $virtual:
  {
    /** The default text for the input.  Will be cleared after first use. */
    defaultText: null,

    processInputRestrictionsClear: function()
    {
      this._superProcessRestrictionsClear();
    },

    renderAddToParentTf: function( parentElement )
    {
      this.superRenderAddToParent(parentElement);
    },

    /** The event handler for a mouse click event. */
    processClick: function( event )
    {
      if ( this.input.value == this.defaultText )
      {
        Echo.Sync.Color.render(
            Echo.Sync.getEffectProperty( this.component, "foreground",
                "foreground", true ),  this.input, "color" );
        this.input.value = "";
      }
      
      this.defaultText = null;
      this.superProcessClick( event );
    },

    /** The event listener for a focus change event. */
    processFocus: function( event )
    {
      if ( this.input.value == this.defaultText )
      {
        Echo.Sync.Color.render(
            Echo.Sync.getEffectProperty( this.component, "foreground",
                "foreground", true ),  this.input, "color" );
        this.input.value = "";
        this.defaultText = null;
      }
      return Echo.Sync.TextField.prototype.processFocus.call( this, event );
    },
  
    /** Display default text if no value was input. */
    processBlur: function( event )
    {
      var text = this.component.render(
          echopoint.internal.TextField.DEFAULT_TEXT );
      if ( text && this.input.value == "" )
      {
        Echo.Sync.Color.render(
            Echo.Sync.getEffectProperty( this.component, "foreground",
                "disabledForeground", true ),  this.input, "color" );
        this.input.value = text;
        this.defaultText = text;
      }
      return Echo.Sync.TextField.prototype.processBlur.call( this, event );
    },

    /** Return the caret position in the text field component. */
    getCaretPosition: function()
    {
      var position = ( this.input.value ) ? this.input.value.length : 0;

      if ( document.selection )
      {
        // IE
        this.input.focus();
        var selection = document.selection.createRange();
        var length = document.selection.createRange().text.length;
        selection.moveStart( 'character', -this.input.value.length );
        position = selection.text.length - length;
      }
      else if ( this.input.selectionStart ||
                this.input.selectionStart == '0' )
      {
        // FireFox
        position = this.input.selectionStart;
      }

      return position;
    },

    /** Set the default text if applicable. */
    setDefaultText: function()
    {
      this.defaultText = this.component.render(
          echopoint.internal.TextField.DEFAULT_TEXT );
      var value = this.component.render( "text" );

      if ( this.defaultText && ( ! value || value == '' ) )
      {
        Echo.Sync.Color.render(
            Echo.Sync.getEffectProperty( this.component, "foreground",
                "disabledForeground", true ),  this.input, "color" );
        this.input.setAttribute( "value", this.defaultText );
      }
      else
      {
        this.defaultText = null;
      }
    }
  },

  renderAdd: function( update, parentElement )
  {
    Echo.Sync.TextField.prototype.renderAdd.call(this, update, parentElement );
    this.setDefaultText();
  }
});
