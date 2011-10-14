/** The name of the LightBox. */
echopoint.constants.LIGHT_BOX = "echopoint.LightBox";

/**
 * The class definition for the abstract container component that is the root
 * for most components.
 *
 * @author Rakesh 2009-02-24
 * @version $Id: Application.LightBox.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.LightBox = Core.extend( Echo.Component,
{
  /** Properties defined for this component. */
  $static:
  {
    HIDDEN: "hidden",
    PARENT_ONLY: "parentOnly",
    TRANSLUCENT_IMAGE: "translucentImage"
  },

  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.LIGHT_BOX, this );
  },

  componentType: echopoint.constants.LIGHT_BOX,

  /** Mark this component as a pane (PaneContainer). */
  pane: true
});
