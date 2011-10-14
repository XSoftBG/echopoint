/**
 * Component rendering peer: LightBox
 *
 * @author Rakesh 2009-02-24
 * @version: $Id: Sync.LightBox.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.LightBoxSync = Core.extend( Echo.Render.ComponentSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.LIGHT_BOX, this );
  },

  $static:
  {
    IMAGE: "images/translucent_80_percent.png"
  },

  /** The container used to display the translucent image. */
  _container: null,

  /** The container used to display child components. */
  _layoutContainer: null,

  renderAdd: function( update, parentElement )
  {
    this._createContainer();
    this._createLayoutContainer();

    var componentCount = this.component.getComponentCount();
    for ( var i = 0; i < componentCount; ++i )
    {
      Echo.Render.renderComponentAdd(
          update, this.component.getComponent( i ), this._layoutContainer );
    }

    var po = this.component.render( echopoint.LightBox.PARENT_ONLY, false );
    if ( po )
    {
      parentElement.appendChild( this._container );
    }
    else
    {
      document.body.appendChild( this._container );
    }
  },

  renderDispose: function()
  {
    var parentOnly = this.component.render( echopoint.LightBox.PARENT_ONLY, false );

    if ( ! parentOnly )
    {
      document.body.removeChild( this._container );
    }

    this._container = null;
    this._layoutContainer = null;
  },

  renderUpdate: function( update )
  {
    var toggle = update.getUpdatedProperty( echopoint.LightBox.HIDDEN );
    if ( toggle )
    {
      this._toggle( toggle.newValue );
    }
  },

  /** Create the container used to display the light box image. */
  _createContainer: function()
  {
    this._container = this._createDiv();
    this._container.id = this.component.renderId;
    this._container.style.cursor = "wait";

    var overlayImg = document.createElement( "img" );
    overlayImg.style.width = "100%";
    overlayImg.style.height = "100%";
    overlayImg.style.left = "0px";
    overlayImg.style.top = "0px";
    overlayImg.src = this._getImage();
    this._container.appendChild( overlayImg );

    var hidden = this.component.render( echopoint.LightBox.HIDDEN, false );
    this._toggle( hidden );

    return this._container;
  },

  /** Create the container used to layout child components */
  _createLayoutContainer: function()
  {
    this._layoutContainer = this._createDiv();
    this._layoutContainer.id = this.component.renderId + "LayoutContainer";
    this._container.appendChild( this._layoutContainer );

    return this._layoutContainer;
  },

  /** Create a div element with default properties. */
  _createDiv: function()
  {
    var div = document.createElement( "div" );
    div.style.backgroundColor = "transparent";
    div.style.position = "absolute";
    div.style.left = "0px";
    div.style.top = "0px";
    div.style.margin = "0px";
    div.style.padding = "0px";

    return div;
  },

  _getImage: function()
  {
    var url = this.component.render( echopoint.LightBox.TRANSLUCENT_IMAGE );
    if ( ! url )
    {
      url = this.client.getResourceUrl( "echopoint", echopoint.LightBoxSync.IMAGE );
    }

    return url;
  },

  /** Toggle the display state of the light box */
  _toggle: function( hide )
  {
    if ( ! hide ) this._showLightBox();
    else  this._hideLightBox();
  },

  _showLightBox: function()
  {
    var parentOnly = this.component.render( echopoint.LightBox.PARENT_ONLY, false );

    if ( ! parentOnly )
    {
      var parent = document.body;

      if ( this._container != parent.firstChild )
      {
        parent.insertBefore( this._container, parent.firstChild );
      }
    }

    this._container.style.width = "100%";
    this._container.style.height = "100%";
    this._container.style.display = "";
  },

  _hideLightBox: function()
  {
    this._container.style.display = "none";
  }
});
