/** The name of the AbstractImage component. */
echopoint.constants.ABSTRACT_IMAGE = "echopoint.internal.AbstractImage";

/**
 * An abstract base component for components that render images.
 *
 * @author Rakesh 2009-12-18
 * @version $Id: Application.AbstractImage.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.internal.AbstractImage = Core.extend( echopoint.internal.AbstractContainer,
{
  $abstract: true,

  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.ABSTRACT_IMAGE, this );
  },

  /** Properties defined for this component. */
  $static:
  {
    ACTION_COMPLETE: "action",
    ACTION_COMMAND: "actionCommand",

    // The alternative text for the image if the image cannot be displayed.
    TEXT: "alt",

    // The property that holds the tooltip for the image.
    TOOL_TIP_TEXT: "toolTipText",

    // The property that holds the standard cursor when hovering over image
    CURSOR: "cursor",

    // The property that holds the URI for the image.
    URL: "url"
  },

  $virtual:
  {
    /** Programmatically performs a click action. */
    doAction: function()
    {
      var ac = this.get( echopoint.internal.AbstractImage.ACTION_COMMAND );
      this.fireEvent( { type: echopoint.internal.AbstractImage.ACTION_COMPLETE,
        source: this, actionCommand: ac } );
    }
  },

  componentType: echopoint.constants.ABSTRACT_IMAGE
});
