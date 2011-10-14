/**
 * Component rendering peer: Anchor
 *
 * @author Rakesh 2008-07-23
 * @version: $Id: Sync.Anchor.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.AnchorSync = Core.extend( Echo.Render.ComponentSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.ANCHOR, this );
  },

  /** The anchor tag that drives the display of the info window. */
  _anchor: null,

  renderAdd: function( update, parentElement )
  {
    parentElement.appendChild( this._createAnchor() );
  },

  renderDispose: function( update )
  {
    this._anchor = null;
  },

  renderUpdate: function( update )
  {
    var parentElement = this._anchor.parentNode;
    Echo.Render.renderComponentDispose( update, update.parent );
    parentElement.removeChild( this._anchor );
    this.renderAdd( update, parentElement );
    return false;
  },

  /** Create the anchor tag used to drive the info window. */
  _createAnchor: function()
  {
    this._anchor = document.createElement( "a" );
    this._anchor.id = this.component.renderId;
    this._anchor.href = this._getUri();
    this._anchor.appendChild( document.createTextNode(
        this.component.render( echopoint.Anchor.TEXT ) ) );

    this._anchor.style.cssText = this._renderStyle();
    this._renderTarget();
    this._renderToolTip();

    return this._anchor;
  },

  /** Render the style attributes for the anchor tag. */
  _renderStyle: function()
  {
    var css = this._addForeground( echopoint.Anchor.FOREGROUND );
    css += this._addBackground( echopoint.Anchor.BACKGROUND );
    css += this._addFont( echopoint.Anchor.FONT );

    return css;
  },

  _addForeground: function( property )
  {
    var value = this.component.render( property );
    return ( value ) ? " color: " + value + ";" : "";
  },

  _addBackground: function( property )
  {
    var value = this.component.render( property );
    return ( value ) ? " background: " + value + ";" : "";
  },

  _addFont: function( property )
  {
    var font = this.component.render( property );
    if ( ! font ) return "";
    var css = "";

    if ( font.typeface )
    {
      css += " font-family: " + ( font.typeface instanceof Array ) ?
             font.typeface.join( "," ) : font.typeface;
      css += ";";
    }

    if ( font.size )
    {
      css += " font-size: " + Echo.Sync.Extent.toCssValue( font.size ) + ";";
    }

    if ( font.bold )
    {
      css += " font-weight: bold;";
    }
    if ( font.italic )
    {
      css += " font-style: italic;";
    }

    if ( font.underline )
    {
      css += " text-decoration: underline;";
    }
    else if ( font.overline )
    {
      css += " text-decoration: overline;";
    }
    else if ( font.lineThrough )
    {
      css += " text-decoration: line-through;";
    }
    else
    {
      css += " text-decoration: none;";
    }

    return css;
  },

  _renderTarget: function()
  {
    var target = this.component.render( echopoint.Anchor.TARGET );
    if ( target )
    {
      this._anchor.target = target;
    }
  },

  _renderToolTip: function()
  {
    var tt = this.component.render( echopoint.Anchor.TOOL_TIP_TEXT );
    if ( tt )
    {
      this._anchor.title = tt;
    }
  },

  _getUri: function()
  {
    return this.component.render( echopoint.Anchor.URI, "#" );
  }
} );
