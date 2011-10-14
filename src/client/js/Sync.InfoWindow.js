/**
 * Component rendering peer: InfoWindow
 *
 * @author Rakesh 2008-07-21, Nikhil C.R
 * @version: $Id: Sync.InfoWindow.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.InfoWindowSync = Core.extend( Echo.Render.ComponentSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.INFO_WINDOW, this );
  },

  $static:
  {
    /** The default width for the info window. */
    DEFAULT_WIDTH: "300px",

    /** The default background colour for the info window. */
    DEFAULT_BACKGROUND: "#c50101"
  },

  /** The container for the text and optional prefix/postfix content. */
  _span: null,

  /** The text that drives the display of the info window. */
  _text: null,

  /** The container element for the info window. */
  _infoWindow: null,

  /** The element used to display the title bar for the window. */
  _titleBar: null,

  /** The element used to display the content of the info window. */
  _content: null,

  renderAdd: function( update, parentElement )
  {
    parentElement.appendChild( this._createSpan() );
    this._createInfoWindow();
    this._renderStyle();
  },

  renderDispose: function()
  {
    this._content = null;
    this._titleBar = null;
    this._infoWindow = null;
    this._text = null;
    this._span = null;
  },

  renderUpdate: function( update )
  {
    var parentElement = this._text.parentNode;
    Echo.Render.renderComponentDispose( update, update.parent );
    parentElement.removeChild( this._span );
    document.body.removeChild( this._infoWindow );
    this.renderAdd( update, parentElement );
    return false;
  },

  /** Create the span element used to drive the info window. */
  _createSpan: function()
  {
    this._span = document.createElement( "span" );
    this._span.id = this.component.renderId + "|Span";
    this._renderFont( this._span, echopoint.InfoWindow.OTHER_TEXT_FONT );
    this._renderForeground( this._span, echopoint.InfoWindow.OTHER_TEXT_FOREGROUND );
    this._renderBackground( this._span, echopoint.InfoWindow.OTHER_TEXT_BACKGROUND );
    this._renderInsets( this._span, echopoint.InfoWindow.OTHER_TEXT_INSETS );

    var prefix = this.component.render( echopoint.InfoWindow.PREFIX );
    if ( prefix )
    {
      this._span.appendChild( document.createTextNode( prefix ) );
    }

    this._span.appendChild( this._createText() );

    var postfix = this.component.render( echopoint.InfoWindow.POSTFIX );
    if ( postfix )
    {
      this._span.appendChild( document.createTextNode( postfix ) );
    }

    Core.Web.Event.add( this._text, "mouseover",
        Core.method( this, this._processRolloverEnter ), false );
    Core.Web.Event.add( this._text, "mouseout",
        Core.method( this, this._processRolloverExit ), false );
    return this._span;
  },

  /** Create the driver text component. */
  _createText: function()
  {
    this._text = document.createElement( "span" );
    this._text.id = this.component.renderId + "|Span|Text";
    this._renderFont( this._text, echopoint.InfoWindow.TEXT_FONT );
    this._renderForeground( this._text, echopoint.InfoWindow.TEXT_FOREGROUND );
    this._renderBackground( this._text, echopoint.InfoWindow.TEXT_BACKGROUND );
    this._renderInsets( this._text, echopoint.InfoWindow.TEXT_INSETS );
    this._text.appendChild( document.createTextNode(
        this.component.render( echopoint.InfoWindow.TEXT ) ) );

    return this._text;
  },

  /** Create the parent container that represents the info window. */
  _createInfoWindow: function()
  {
    this._infoWindow = document.createElement( "div" );
    this._infoWindow.id = this.component.renderId;
    this._infoWindow.style.overflow = "scroll";

    this._infoWindow.appendChild( this._createTop() );
    this._infoWindow.appendChild( this._createTitleBar() );
    this._infoWindow.appendChild( this._createContent() );
    this._infoWindow.appendChild( this._createBottom() );

    document.body.appendChild( this._infoWindow );
    return this._infoWindow;
  },

  /**
   * Render the styles for the container window.
   *
   * @param update The update object that will be queried for style updates.
   *   If this is not specified or is <code>null</code>, then the styles
   *   will be applied unconditionally.  This helps to use this optional
   *   parameter to drive use from {@link #renderAdd} or {@link #renderUpdate}.
   */
  _renderWindowStyle: function( update )
  {
    this._infoWindow.style.display = "none";
    this._renderWidth( this._infoWindow, update );

    if ( ! update )
    {
      this._infoWindow.style.position = "absolute";
      this._infoWindow.style.zIndex = 100000;
      this._infoWindow.style.opacity = 1.0;
    }
  },

  /** Create the top ronded corners for the info window. */
  _createTop: function()
  {
    var div = document.createElement( "div" );

    var rtop = document.createElement( "b" );
    rtop.style.display = "block";
    rtop.style.background = "transparent";
    rtop.style.height = "7px";

    rtop.appendChild( this._createR1() );
    rtop.appendChild( this._createR2() );
    rtop.appendChild( this._createR3() );
    rtop.appendChild( this._createR4() );
    rtop.appendChild( this._createR5() );
    rtop.appendChild( this._createR6() );
    rtop.appendChild( this._createR7() );
    div.appendChild( rtop );
    return div;
  },

  /** Create the title bar for the info window. */
  _createTitleBar: function()
  {
    this._titleBar = document.createElement( "div" );
    this._titleBar.id = this.component.renderId + "|TitleBar";
    this._titleBar.innerHTML = this.component.render(
        echopoint.InfoWindow.TITLE, "" );

    return this._titleBar;
  },

  /**
   * Render the styles for the title bar area.
   *
   * @param update The update object that will be queried for style updates.
   *   If this is not specified or is <code>null</code>, then the styles
   *   will be applied unconditionally.  This helps to use this optional
   *   parameter to drive use from {@link #renderAdd} or {@link #renderUpdate}.
   */
  _renderTitleStyle: function( update )
  {
    this._titleBar.style.display = "block";
    this._renderFont( this._titleBar, echopoint.InfoWindow.TITLE_FONT, update );
    this._renderInsets( this._titleBar, echopoint.InfoWindow.TITLE_INSETS, update );
    var bg = this._getBackground();
    this._titleBar.style.background = bg;
    this._titleBar.style.borderLeft = "thin groove " + bg;
    this._titleBar.style.borderRight = "thin groove " + bg;
    this._titleBar.style.color = this.component.render(
        echopoint.InfoWindow.TITLE_FOREGROUND, "white" );
    this._renderFont( this._titleBar, echopoint.InfoWindow.TITLE_FONT, update );
    this._renderAlignment( this._titleBar, echopoint.InfoWindow.TITLE_ALIGNMENT, "center" );
  },

  /** Create the content area for the info window. */
  _createContent: function()
  {
    this._content = document.createElement( "div" );
    this._content.id = this.component.renderId + "|Content";
    this._content.innerHTML = this.component.render(
        echopoint.InfoWindow.CONTENT, "" );

    return this._content;
  },

  /**
   * Render the styles for the content area.
   *
   * @param update The update object that will be queried for style updates.
   *   If this is not specified or is <code>null</code>, then the styles
   *   will be applied unconditionally.  This helps to use this optional
   *   parameter to drive use from {@link #renderAdd} or {@link #renderUpdate}.
   */
  _renderContentStyle: function( update )
  {
    this._content.style.display = "block";
    this._content.style.overflow = "auto";
    this._renderFont( this._content, echopoint.InfoWindow.FONT, update );
    Echo.Sync.Color.renderFB( this.component,  this._content );
    this._renderInsets( this._content, echopoint.InfoWindow.INSETS, update );
    var bg = this._getBackground();
    this._content.style.borderLeft = "thin groove " + bg;
    this._content.style.borderRight = "thin groove " + bg;
    this._renderAlignment( this._content, echopoint.InfoWindow.ALIGNMENT, "left" );
  },

  /** Create the bottom ronded corners for the info window. */
  _createBottom: function()
  {
    var div = document.createElement( "div" );

    var rbottom = document.createElement( "b" );
    rbottom.style.display = "block";
    rbottom.style.background = "transparent";
    rbottom.style.height = "7px";

    rbottom.appendChild( this._createR7() );
    rbottom.appendChild( this._createR6() );
    rbottom.appendChild( this._createR5() );
    rbottom.appendChild( this._createR4() );
    rbottom.appendChild( this._createR3() );
    rbottom.appendChild( this._createR2() );
    rbottom.appendChild( this._createR1() );
    div.appendChild( rbottom );
    return div;
  },

  /** Create the rounder corner1 component. */
  _createR1: function()
  {
    var r = document.createElement( "b" );
    r.style.display = "block";
    r.style.height = "1px";
    r.style.overflow = "hidden";
    r.style.background = this._getBackground();
    r.style.margin = "0 8px";
    //r.style.margin = "0 5px";
    return r;
  },

  /** Create the rounder corner2 component. */
  _createR2: function()
  {
    var r = document.createElement( "b" );
    r.style.display = "block";
    r.style.height = "1px";
    r.style.overflow = "hidden";
    r.style.background = this._getBackground();
    r.style.margin = "0 5px";
    //r.style.margin = "0 3px";
    return r;
  },

  /** Create the rounder corner3 component. */
  _createR3: function()
  {
    var r = document.createElement( "b" );
    r.style.display = "block";
    r.style.height = "1px";
    r.style.overflow = "hidden";
    r.style.background = this._getBackground();
    r.style.margin = "0 4.5px";
    //r.style.margin = "0 2px";
    return r;
  },

  /** Create the rounder corner4 component. */
  _createR4: function()
  {
    var r = document.createElement( "b" );
    r.style.display = "block";
    r.style.height = "1px";
    r.style.overflow = "hidden";
    r.style.background = this._getBackground();
    r.style.margin = "0 3.5px";
    //r.style.margin = "0 1px; height: 2px";
    return r;
  },

  /** Create the rounder corner5 component. */
  _createR5: function()
  {
    var r = document.createElement( "b" );
    r.style.display = "block";
    r.style.height = "1px";
    r.style.overflow = "hidden";
    r.style.background = this._getBackground();
    r.style.margin = "0 2px";
    return r;
  },

  /** Create the rounder corner6 component. */
  _createR6: function()
  {
    var r = document.createElement( "b" );
    r.style.display = "block";
    r.style.height = "1px";
    r.style.overflow = "hidden";
    r.style.background = this._getBackground();
    r.style.margin = "0 1.5px";
    return r;
  },

  /** Create the rounder corner7 component. */
  _createR7: function()
  {
    var r = document.createElement( "b" );
    r.style.display = "block";
    r.style.height = "1px";
    r.style.overflow = "hidden";
    r.style.background = this._getBackground();
    r.style.margin = "0 1px";
    return r;
  },

  /**
   * The method used to render the full style properties for the container.
   * This may also be invoked from {@link #renderUpdate} to selectively
   * re-apply the styles that have changed.
   *
   * @param update The update object that will be queried for style updates.
   *   If this is not specified or is <code>null</code>, then the styles
   *   will be applied unconditionally.  This helps to use this optional
   *   parameter to drive use from {@link #renderAdd} or {@link #renderUpdate}.
   */
  _renderStyle: function( update )
  {
    this._renderWindowStyle( update );
    this._renderTitleStyle( update );
    this._renderContentStyle( update );
  },

  /**
   * Render the <code>font</code> style property for the component.
   * This may be used to render update of the font property alone from
   * {@link #renderUpdate}.
   *
   * @param container The element to which the style will be applied.
   * @param property The name of the property that contains the font information.
   * @param update The update object that will be queried for style change.
   */
  _renderFont: function( container, property, update )
  {
    if ( update )
    {
      var value = update.getUpdatedProperty( property );
      if ( value )
      {
        Echo.Sync.Font.render( value.newValue, container );
      }
    }
    else
    {
      Echo.Sync.Font.render( this.component.render( property ), container );
    }
  },

  /** Render the foreground property for the container element. */
  _renderForeground: function( container, property, update )
  {
    var value = null;

    if ( update )
    {
      value = update.getUpdatedProperty( property );
      if ( value )
      {
        container.style.color = value.newValue;
      }
    }
    else
    {
      value = this.component.render( property );
      if ( value ) container.style.color = value;
    }
  },

  /** Render the background property for the container element. */
  _renderBackground: function( container, property, update )
  {
    var value = null;

    if ( update )
    {
      value = update.getUpdatedProperty( property );
      if ( value )
      {
        container.style.backgroundColor = value.newValue;
      }
    }
    else
    {
      value = this.component.render( property );
      if ( value ) container.style.backgroundColor = value;
    }
  },

  /**
   * Render the <code>insets</code> style property for the component.
   * This may be used to render update of the insets property alone from
   * {@link #renderUpdate}.
   *
   * @param container The element to which the style will be applied.
   * @param property The name of the property that contains the insets.
   * @param update The update object that will be queried for style change.
   */
  _renderInsets: function( container, property, update )
  {
    if ( update )
    {
      var value = update.getUpdatedProperty( property );
      if ( value )
      {
        Echo.Sync.Insets.render( value.newValue, container, "padding" );
      }
    }
    else
    {
      Echo.Sync.Insets.render(
          this.component.render( property ), container, "padding" );
    }
  },

  /**
   * Render the <code>alignment</code> style property for the component.
   *
   * @param container The element to which the style will be applied.
   * @param property The name of the property that contains the insets.
   * @param defaultValue The default value to apply if the property has not
   *   been set/styled.
   * @param update The update object that will be queried for style change.
   */
  _renderAlignment: function( container, property, defaultValue, update )
  {
    if ( update )
    {
      var value = update.getUpdatedProperty( property );
      if ( value )
      {
        container.style.textAlign = value.newValue;
      }
    }
    else
    {
      container.style.textAlign = this.component.render( property, defaultValue );
    }
  },

  /**
   * Return the background colour to use for the info window corners and
   * title bar.
   */
  _getBackground: function()
  {
    return this.component.render(
        echopoint.InfoWindow.TITLE_BACKGROUND, echopoint.InfoWindowSync.DEFAULT_BACKGROUND );
  },

  /**
   * Return the width to use for the component.  If no width property has
   * been specified, returns {@link #DEFAULT_WIDTH}.
   */
  _getWidth: function()
  {
    return this.component.render( echopoint.InfoWindow.WIDTH,
        echopoint.InfoWindowSync.DEFAULT_WIDTH );
  },

  /**
   * Render the <code>width</code> style property for the component.
   * This may be used to render update of the width property alone from
   * {@link #renderUpdate}.
   *
   * @param container The element to which the style will be applied.
   * @param update The update object that will be queried for style change.
   */
  _renderWidth: function( container, update )
  {
    if ( update )
    {
      var property = update.getUpdatedProperty(
          echopoint.InfoWindow.WIDTH );
      if ( property )
      {
        container.style.width = Echo.Sync.Extent.toCssValue(
            property.newValue, true, true );
      }
    }
    else
    {
      container.style.width = Echo.Sync.Extent.toCssValue(
          this._getWidth(), true, true );
    }
  },

  /** The event handler for mouse-over (hover) over the text element. */
  _processRolloverEnter: function( e )
  {
    if ( !this.client || !this.client.verifyInput( this.component ) || Core.Web.dragInProgress )
    {
      return true;
    }

    this._setLocation( e );
  },

  /** The event handler for mouse-out from the text element. */
  _processRolloverExit: function()
  {
    if ( !this.client || !this.client.verifyInput( this.component ) )
    {
      return true;
    }

    this._infoWindow.style.display = "none";
  },

  /** Set the position of the info window taking into account position on screen. */
  _setLocation: function( event )
  {
    var sw = 0;
    var sh = 0;
    if ( typeof( window.pageXOffset ) == 'number' )
    {
      sw = window.pageXOffset;
      sh = window.pageYOffset;
    }
    else
    {
      sw = document.body.scrollLeft;
      sh = document.body.scrollTop;
    }

    var dw = document.body.clientWidth ? document.body.clientWidth : window.innerWidth;
    var dh = document.body.clientHeight ? document.body.clientHeight : window.innerHeight;
    var d = this._infoWindow;
    d.style.display = "block";

    if ( ( ( event.clientX + d.offsetWidth ) > dw )
        && ( event.clientX > d.offsetWidth ) )
    {
      d.style.left = ( event.clientX - d.offsetWidth + sw ) + 'px';
    }
    else
    {
      d.style.left = ( event.clientX + sw ) + 'px';
    }

    if ( ( ( event.clientY + d.offsetHeight + 25 ) > dh )
        && ( event.clientY > (d.offsetHeight + 25 ) ) )
    {
      d.style.top = ( event.clientY - ( d.offsetHeight + 15 ) + sh ) + 'px';
    }
    else
    {
      d.style.top = ( event.clientY + sh + 15 ) + 'px';
    }
  }
} );
