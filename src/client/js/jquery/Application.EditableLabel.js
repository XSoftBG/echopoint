/** The name of the EditableLabel component. */
echopoint.constants.EDITABLE_LABEL = "echopoint.jquery.EditableLabel";

/**
 * A editable label component based upon Jeditable jQuery plugin.
 *
 * @author Rakesh 2009-09-30
 * @version $Id: Application.EditableLabel.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.jquery.EditableLabel = Core.extend( echopoint.internal.AbstractContainer,
{
  /** Properties defined for this component. */
  $static:
  {
    /** The text for the label. */
    TEXT: "text",

    /** The tooltip (title) for the label. */
    TOOL_TIP_TEXT: "toolTipText"
  },

  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.EDITABLE_LABEL, this );
  },

  componentType: echopoint.constants.EDITABLE_LABEL
});
