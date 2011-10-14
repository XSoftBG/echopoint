/**
 * Component rendering peer: PushButton
 *
 * @author Rakesh 2009-02-24
 * @version $Id: Sync.PushButton.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.PushButtonSync = Core.extend( echopoint.internal.AbstractContainerSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.PUSH_BUTTON, this );
  },

  $static:
  {
    DEFAULT_WIDTH: null,
    DEFAULT_HEIGHT: null
  },

  /**
   * The default width property to use.  Sub-classes should over-ride as
   * necessary.
   */
  getDefaultWidth: function()
  {
    return echopoint.PushButtonSync.DEFAULT_WIDTH;
  },

  /**
   * The default height property to use.  Sub-classes should over-ride as
   * necessary.
   */
  getDefaultHeight: function()
  {
    return echopoint.PushButtonSync.DEFAULT_HEIGHT;
  },

  /** The input that is used to display the button. */
  _input: null,

  /** The enabled status of the component. */
  enabled: null,

  renderAdd: function( update, parentElement )
  {
    this.enabled = this.component.isRenderEnabled();
    parentElement.appendChild( this._createInput() );
  },

  renderDispose: function()
  {
    Core.Web.Event.removeAll( this._input );
    this._input = null;
  },

  renderUpdate: function( update )
  {
    if ( update )
    {
      var property = update.getUpdatedProperty( "enabled" );

      if ( property != null )
      {
        if ( property.newValue && ! this.enabled )
        {
          Core.Web.Event.add( this._input, "click",
              Core.method( this, this._processClick ), false );
        }
        else
        {
          Core.Web.Event.removeAll( this._input );
        }
        this._input.disabled= !this.enabled; // Visual feedback of enabled/disabled

        this.enabled = property.newValue;
      }
    }

    this.renderStyle( this._input, update );
    this._setText();
    return false; // Child elements not supported: safe to return false.
  },

  _createInput: function()
  {
    this._input = document.createElement( "input" );
    this._input.id = this.component.renderId;
    this._input.type = "submit";

    if ( this.enabled )
    {
      Core.Web.Event.add( this._input, "click",
          Core.method( this, this._processClick ), false );
    }
    this._input.disabled= !this.enabled; // Visual feedback of enabled/disabled

    this._setText();
    this.renderStyle( this._input );
    return this._input;
  },

  _setText: function()
  {
    this._input.value = this.component.render( echopoint.PushButton.TEXT, "Submit" );

    var tooltip = this.component.render( echopoint.PushButton.TOOL_TIP_TEXT );
    if ( tooltip )
    {
      this._input.title = tooltip;
    }
  },

  _processClick: function()
  {
    if ( !this.client || !this.client.verifyInput( this.component ) ) return true;

    this.component.application.setFocusedComponent( this.component );
    this.component.doAction();
  },

  /**
   * Over-ridden to set only if a width property was specified.
   *
   * @param container The element to which the style will be applied.
   * @param update The update object that will be queried for style change.
   */
  renderWidth: function( container, update )
  {
    if ( update )
    {
      var property = update.getUpdatedProperty(
          echopoint.internal.AbstractContainer.WIDTH );
      if ( property )
      {
        container.style.width = Echo.Sync.Extent.toCssValue(
            property.newValue, true, true );
      }
    }
    else
    {
      var width = this.getWidth();
      if ( width )
      {
        container.style.width =
            Echo.Sync.Extent.toCssValue( width, true, true );
      }
    }
  },

  /**
   * Over-ridden to set only if a height property was specified.
   *
   * @param container The element to which the style will be applied.
   * @param update The update object that will be queried for style change.
   */
  renderHeight: function( container, update )
  {
    if ( update )
    {
      var property = update.getUpdatedProperty(
          echopoint.internal.AbstractContainer.HEIGHT );
      if ( property )
      {
        container.style.height = Echo.Sync.Extent.toCssValue(
            property.newValue, false, true );
      }
    }
    else
    {
      var height = this.getHeight();
      if ( height )
      {
        container.style.height =
            Echo.Sync.Extent.toCssValue( height, false, true );
      }
    }
  }
});
