/**
 * Component rendering peer: Gallery
 *
 * @author sieskei 2011-03-14
 * @version: $Id: Sync.Gallery.js,v 1.2 2011-03-18 15:52:29 yozov Exp $
 */
echopoint.GallerySync = Core.extend( Echo.Render.ComponentSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.GALLERY, this );
  },

  _gallery_div: null,
  _gallery_content: null,
  _images: null,
  _height: null,
  _width: null,
  _is_empty: null,
  _empty_msg: null,

  renderAdd: function( update, parentElement )
  {
    this._gallery_div              = document.createElement( "div" );
    this._gallery_div.id           = this.component.renderId + '_gallery_div';
    this._gallery_div.style.width  = "100%";
    this._gallery_div.style.height = "100%";

    this._gallery_div.appendChild(this._initGallery(true));
    parentElement.appendChild(this._gallery_div);

    if(!this._is_empty)
      this._calcGallery();
  },

  renderUpdate: function(update)
  {
    var updated_properties = update.getUpdatedPropertyNames();
    has_new_images = false;
    for(i = 0; i < updated_properties.length; i++)
    {
      var prop_name = updated_properties[i];
      switch(prop_name)
      {
        case echopoint.Gallery.HEIGHT:
          _new_height = update.getUpdatedProperty(prop_name).newValue;
          this._height = parseInt(_new_height.substring(0, _new_height.length - 2));
          break;
        case echopoint.Gallery.WIDTH:
          _new_width = update.getUpdatedProperty(prop_name).newValue;
          this._width = parseInt(_new_width.substring(0, _new_width.length - 2));
          break;
        case echopoint.Gallery.IMAGES:
          this._images = update.getUpdatedProperty(prop_name).newValue;
          break;
      }
    }
    this._clearGalleryDiv();
    this._gallery_div.appendChild(this._initGallery(false));

    if(!this._is_empty)
      this._calcGallery();
  },
  
  renderDispose: function( update )
  {
    this._clearGalleryDiv();
    this._gallery_div = null;
  },

  _clearGalleryDiv: function()
  {
    while (this._gallery_div.hasChildNodes())
      this._gallery_div.removeChild(this._gallery_div.firstChild);
  },

  _initGallery: function(_is_new)
  {
    if (jQuery("#bxGalleryCSS").length == 0)
    {
      var stylesheet = this.component.render(echopoint.Gallery.CSS);
      jQuery("head").append("<style type=\"text/css\" id=\"bxGalleryCSS\">"+stylesheet+"</style>");
    }

    if(_is_new)
    {
      _temp_heigth = this.component.render(echopoint.Gallery.HEIGHT, "350px")
      this._height = parseInt(_temp_heigth.substring(0, _temp_heigth.length - 2));
      _temp_width  = this.component.render(echopoint.Gallery.WIDTH, "600px");
      this._width = parseInt(_temp_width.substring(0, _temp_width.length - 2));
      this._empty_msg = this.component.render(echopoint.Gallery.EMPTY_MSG, "Gallery is empty.");
      this._images = this.component.render(echopoint.Gallery.IMAGES, "");
    }

    if(this._images != "")
    {
      this._is_empty = false;
      this._images = this._images.split(',');
      this._createUL();
    }
    else
    {
      this._is_empty = true;
      this._createEmpty();
    }

    return this._gallery_content;
  },

  _createUL: function()
  {
    this._gallery_content = document.createElement( "ul" );
    this._gallery_content.id = this.component.renderId + '_gallery_content';
    for(i = 0; i < this._images.length; i++)
    {
      li = document.createElement( "li" );
      img = document.createElement( "img" );
      img.src = this._images[i];
      li.appendChild(img);
      this._gallery_content.appendChild(li);
    }
  },

  _createEmpty: function()
  {
    this._gallery_content = document.createElement( "span" );
    this._gallery_content.id = this.component.renderId + '_gallery_content';
    this._gallery_content.className = "gallery_empty";
    this._gallery_content.innerHTML = this._empty_msg;
  },

  _calcGallery: function()
  {
    $(this._gallery_content).bxGallery({
      maxheight: this._height,
      maxwidth: this._width,
      thumbwidth: 75,
      thumbplacement: 'left',
      thumbcontainer: 100
    });
  }
});
