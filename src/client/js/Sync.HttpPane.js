/**
 * Component rendering peer: HttpPane
 *
 * @author Rakesh 2008-07-13
 * @version: $Id: Sync.HttpPane.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.HttpPaneSync = Core.extend( echopoint.internal.AbstractContainerSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.HTTP_PANE, this );
  },

  $static:
  {
    DEFAULT_HEIGHT: "100%",
    DEFAULT_WIDTH: "100%"
  },

  /**
   * The container element in which the iframe is contained.  This is used
   * to enable proper <code>height</code> style property, since percentage
   * values do not work properly when set directly on the iframe.
   */
  _container: null,

  /** The iframe used to load the user specified URI. */
  _iframe: null,

  renderAdd: function( update, parentElement )
  {
    this._container = document.createElement( "div" );
    this._container.id = this.component.renderId;
    this.renderStyle( this._container );
    this._renderContainerStyle();
    this._container.appendChild( this._createIframe() );

    parentElement.appendChild( this._container );
  },

  renderDispose: function( update )
  {
    this._iframe = null;
    this._container = null;
  },

  renderUpdate: function( update )
  {
    this.renderStyle( this._container, update );

    var property = update.getUpdatedProperty( echopoint.HttpPane.URI );
    if ( property )
    {
      this._iframe.src = property.newValue;
    }
  },

  /** Create the iframe in which the URI contents are displayed. */
  _createIframe: function()
  {
    this._iframe = document.createElement( "iframe" );
    this._iframe.allowtransparency = "true";
    this._iframe.src = this.component.get( echopoint.HttpPane.URI );
    this._renderIframeStyle();

    return this._iframe;
  },

  /** Over-ridden to return the value to use. */
  getDefaultHeight: function() { return echopoint.HttpPaneSync.DEFAULT_HEIGHT; },

  /** Over-ridden to return the value to use. */
  getDefaultWidth: function() { return echopoint.HttpPaneSync.DEFAULT_WIDTH; },

  /** Additional style configuration for the container element. */
  _renderContainerStyle: function()
  {
    this._container.style.position = "absolute";
    this._container.style.overflow = "auto";
  },

  /** The styles to apply to the iframe element. */
  _renderIframeStyle: function()
  {
    this._iframe.style.position = "relative";
    this._iframe.style.width = "100%";
    this._iframe.style.height = "100%";
    this._iframe.style.borderStyle = "none";
  }
});
