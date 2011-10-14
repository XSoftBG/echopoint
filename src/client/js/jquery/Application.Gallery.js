/** The name of the Gallery component. */
echopoint.constants.GALLERY = "echopoint.jquery.Gallery";

/**
 * A component that represents a image gallery.
 *
 * @author sieskei 2011-03-14
 * @version $Id: Application.Gallery.js,v 1.2 2011-03-18 15:52:29 yozov Exp $
 */
echopoint.Gallery = Core.extend(Echo.Component,
{
  $static:
  {
    IMAGES:    "images",
    WIDTH:     "width",
    HEIGHT:    "height",
    CSS:       "css",
    EMPTY_MSG: "empty_msg"
  },

  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.GALLERY, this );
  },

  componentType: echopoint.constants.GALLERY
});
