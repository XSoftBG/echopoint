/**
 * Component rendering peer: ProgressBar
 *
 * @author Rakesh 2008-10-29
 * @version $Id: Sync.ProgressBar.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.ProgressBarSync = Core.extend( echopoint.internal.AbstractContainerSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.PROGRESS_BAR, this );
  },

  $static:
  {
    DEFAULT_HEIGHT: "15px",
    DEFAULT_WIDTH: "300px"
  },

  /** The parent container in which the child bar is rendered. */
  _div: null,

  /** The progress bar element. */
  _bar: null,

  /** The optional text to display in the progress bar.*/
  _text: null,

  renderAdd: function( update, parentElement )
  {
    this._createParent( parentElement );
    this._div.appendChild( this._createBar() );

    var text = this.component.render( echopoint.ProgressBar.TEXT );
    this._createText( ( text ) ? text : "" );

    this._renderStyle( update );
    this._renderBar( update );
  },

  renderDispose: function( update )
  {
    this._text = null;
    this._bar = null;
    this._div = null;
  },

  renderUpdate: function( update )
  {
    this.renderStyle( this._div, update );
    this._renderStyle( update );
    this._renderBar( update );

    return false;
  },

  /** The default height to use for the progress bar element. */
  getDefaultHeight: function()
  {
    return echopoint.ProgressBarSync.DEFAULT_HEIGHT;
  },

  /** The default width to use for the progress bar element. */
  getDefaultWidth: function()
  {
    return echopoint.ProgressBarSync.DEFAULT_WIDTH;
  },

  /** Create the parent div element that holds the child progress element. */
  _createParent: function( parentElement )
  {
    this._div = document.createElement( "div" );
    this._div.id = this.component.renderId;
    this.renderStyle( this._div );

    parentElement.appendChild( this._div );
    return this._div;
  },

  /** Create the variable width element that represents the progress bar. */
  _createBar: function()
  {
    var wrapper = document.createElement( "div" );
    wrapper.id = this.component.renderId + "|wrapper";
    wrapper.style.position = "absolute";
    wrapper.style.height = this._div.style.height;
    wrapper.style.width = this._div.style.width;
    wrapper.style.offsetLeft = this._div.style.offsetLeft;
    wrapper.style.offsetTop = this._div.style.offsetTop;
    wrapper.style.zIndex = 1;

    this._bar = document.createElement( "div" );
    this._bar.id = this.component.renderId + "|bar";
    this._bar.style.position = "absolute";
    this._bar.style.height = wrapper.style.height;
    this._bar.style.offsetLeft = wrapper.style.offsetLeft;
    this._bar.style.offsetTop = wrapper.style.offsetTop;
    this._bar.style.zIndex = 2;

    this._renderStyle();
    wrapper.appendChild( this._bar );
    return wrapper;
  },

  /** Create the optional text element to display in the progress bar. */
  _createText: function( text )
  {
    this._text = document.createElement( "div" );
    this._text.id = this.component.renderId + "|text";
    this._text.style.position = "absolute";
    this._text.style.textAlign = "center";
    this._text.style.verticalAlign = "middle";
    this._text.style.color = this._div.style.color;
    this._text.style.height = this._div.style.height;
    this._text.style.width = this._div.style.width;
    this._text.style.offsetLeft = this._div.style.offsetLeft;
    this._text.style.offsetTop = this._div.style.offsetTop;
    this._text.style.zIndex = 3;

    var font = this.component.render( echopoint.internal.AbstractContainer.FONT );
    if ( font ) this._text.style.font = font;

    this._text.appendChild( document.createTextNode( text ) );
    this._div.appendChild( this._text );
  },

  /** Render the foreground colour for the bar element. */
  _renderStyle: function( update )
  {
    var value = null;
    var property = echopoint.ProgressBar.BAR_BACKGROUND;

    if ( update )
    {
      var prop = update.getUpdatedProperty( property );
      if ( prop ) value = prop.newValue;
    }
    else
    {
      value = this.component.render( property,
          echopoint.ProgressBar.DEFAULT_BAR_BACKGROUND );
    }

    if ( value )
    {
      this._bar.style.backgroundColor = value;
    }
  },

  /** Render updates to the text and percentage in the bar. */
  _renderBar: function( update )
  {
      this._setPercentage(
          this.component.get( echopoint.ProgressBar.PERCENTAGE ) );
      this._setText( this.component.get( echopoint.ProgressBar.TEXT ) );
  },

  _setText: function( value )
  {
    if ( ! value && ! this._text ) return;
    if ( ! value ) value = "";

    Core.Web.DOM.removeAllChildren( this._text );
    this._text.appendChild( document.createTextNode( value ) );
  },

  _setPercentage: function( value )
  {
    this._bar.style.width = ( ! value ) ? "0px" : parseInt( value ) + "%";
  }
});
