/**
 * Component rendering peer: ImageIcon
 *
 * @author Rakesh 2008-10-20
 * @version $Id: Sync.ImageIcon.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.ImageIconSync = Core.extend( echopoint.internal.AbstractImageSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.IMAGE_ICON, this );
  },

  renderAdd: function( update, parentElement )
  {
    parentElement.appendChild( this.createImage() );
  },

  renderDispose: function( update )
  {
    this.image = null;
  },

  renderUpdate: function( update )
  {
    var element = this.image;
    var containerElement = element.parentNode;
    this.renderDispose(update);
    containerElement.removeChild(element);
    this.renderAdd(update, containerElement);
    return false; // Child elements not supported: safe to return false.
  }
});
