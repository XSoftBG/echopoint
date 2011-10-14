/** The name of the Anchor component. */
echopoint.constants.ANCHOR = "echopoint.Anchor";

/**
 * A component that represents a HTML anchor tag.  This component makes it
 * easier to create regular HTML links without having to configure a {@link
 * Echo.Button} component and an associated action listener.  You can create
 * a raw anchor tag using {@link echopoint.internal.AbstractHtmlComponent}
 * implementations, but may find configuring styles not as convenient.
 *
 * <p><b>Note:</b> Since anchor tags cannot be fully styled using in-line
 * styles, this component does not offer the ability to configure styles for
 * hover, active or visited.</p>
 *
 * @author Rakesh 2008-10-23
 * @version $Id: Application.Anchor.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.Anchor = Core.extend( Echo.Component,
{
  /** Properties defined for this component. */
  $static:
  {
    /**
     * The background to use for the anchor text.  This property is best styled.
     */
    BACKGROUND: "background",

    /**
     * The font to use for the anchor text.  This property is best styled.
     */
    FONT: "font",

    /**
     * The foreground to use for the anchor text.  This property is best styled.
     */
    FOREGROUND: "foreground",

    /** The target for the anchor tag.  Use to control target window/frame. */
    TARGET: "target",

    /** The text that is to be hyper-linked. */
    TEXT: "text",

    /** The tooltip (title) for the anchor tag. */
    TOOL_TIP_TEXT: "toolTipText",

    /** The destination URI to which the anchor tag points. */
    URI: "uri"
  },

  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.ANCHOR, this );
  },

  componentType: echopoint.constants.ANCHOR
});
