/** The name of the AbstractContainer. */
echopoint.constants.ABSTRACT_CONTAINER = "echopoint.internal.AbstractContainer";

/**
 * The class definition for the abstract container component that is the root
 * for most components.
 *
 * @author Rakesh 2008-07-20
 * @version $Id: Application.AbstractContainer.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.internal.AbstractContainer = Core.extend( Echo.Component,
{
  $abstract: true,

  /** Properties defined for this component. */
  $static:
  {
    ALIGNMENT: "alignment",
    BACKGROUND_IMAGE: "backgroundImage",
    BACKGROUND: "background",
    BORDER: "border",
    FONT: "font",
    FOREGROUND: "foreground",
    INSETS: "insets",
    HEIGHT: "height",
    LAYOUT_DATA: "layoutData",
    WIDTH: "width"
  },

  $load: function()
  {
    Echo.ComponentFactory.registerType(
        echopoint.constants.ABSTRACT_CONTAINER, this );
  }
});

