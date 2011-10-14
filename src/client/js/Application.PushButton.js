/** The name of the PushButton. */
echopoint.constants.PUSH_BUTTON = "echopoint.PushButton";

/**
 * The class definition for the push button component.
 *
 * @author Rakesh 2009-02-24
 * @version $Id: Application.PushButton.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.PushButton = Core.extend( Echo.Component,
{
  /** Properties defined for this component. */
  $static:
  {
    // Event listener
    ACTION_COMPLETE: "action",
    ACTION_COMMAND: "actionCommand",

    // Style attributes
    ALIGNMENT: "alignment",
    BACKGROUND: "background",
    BORDER: "border",
    FONT: "font",
    FOREGROUND: "foreground",
    INSETS: "insets",
    HEIGHT: "height",
    WIDTH: "width",
    TEXT: "text",
    TOOL_TIP_TEXT: "toolTipText"
  },

  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.PUSH_BUTTON, this );
  },

  /** Programmatically performs an event on the button. */
  doAction: function()
  {
    var ac = this.get( echopoint.PushButton.ACTION_COMMAND );
    this.fireEvent( { type: echopoint.PushButton.ACTION_COMPLETE, source: this,
      actionCommand: ac } );
  },

  componentType: echopoint.constants.PUSH_BUTTON
});
