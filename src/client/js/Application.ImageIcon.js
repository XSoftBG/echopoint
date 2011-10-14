/** The name of the ImageIcon component. */
echopoint.constants.IMAGE_ICON = "echopoint.ImageIcon";

/**
 * A component used to display an image.  Differs from buttons and labels in
 * that the precise size of the image can be specified directly.
 *
 * @author Rakesh 2008-10-20
 * @version $Id: Application.ImageIcon.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.ImageIcon = Core.extend( echopoint.internal.AbstractImage,
{
  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.IMAGE_ICON, this );
  },

  componentType: echopoint.constants.IMAGE_ICON
});
