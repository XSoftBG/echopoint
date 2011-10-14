/** The name of the InfoWindow component. */
echopoint.constants.INFO_WINDOW = "echopoint.InfoWindow";

/**
 * A component used display an informational floating window (similar to
 * Google GInfoWindow) when hovering over a parent component.
 *
 * @author Rakesh 2008-10-21
 * @version $Id: Application.InfoWindow.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.InfoWindow = Core.extend( Echo.Component,
{
  /** Properties defined for this component. */
  $static:
  {
    /**
     * Optional prefix text before the text that drives the display of the
     * info window.  The content should be plain text without any markup.
     */
    PREFIX: "prefix",

    /**
     * Optional postfix text after the text that drives the display of the
     * info window.  The content should be plain text without any markup.
     */
    POSTFIX: "postfix",

    /**
     * The text that drives the display of the info window.  Hovering over
     * the text will display the info window.  This property may be styled.
     */
    TEXT: "text",

    /** The font to use for the driver text. */
    TEXT_FONT: "textFont",

    /** The foreground for the driver text. */
    TEXT_FOREGROUND: "textForeground",

    /** The background for the driver text. */
    TEXT_BACKGROUND: "textBackground",

    /** The insets for the driver text. */
    TEXT_INSETS: "textInsets",

    /** The font to use for the pre/postfix text. */
    OTHER_TEXT_FONT: "otherTextFont",

    /** The foreground for the pre/postfix text. */
    OTHER_TEXT_FOREGROUND: "otherTextForeground",

    /** The background for the pre/postfix text. */
    OTHER_TEXT_BACKGROUND: "otherTextBackground",

    /** The insets for the pre/postfix text. */
    OTHER_TEXT_INSETS: "otherTextInsets",

    /**
     * The title to display for the info window.  Specify this only if
     * you want a default title bar component.  The text may contain HTML
     * markup.
     */
    TITLE: "title",

    /** The alignment for the title of the info window. */
    TITLE_ALIGNMENT: "titleAlignment",

    /** The font to use for the title of the info window. */
    TITLE_FONT: "titleFont",

    /** The foreground for the title of the info window. */
    TITLE_FOREGROUND: "titleForeground",

    /**
     * The background for the title of the info window.  This is also used
     * to render the rounded box that represents the info window.
     */
    TITLE_BACKGROUND: "titleBackground",

    /** The insets for the title of the info window. */
    TITLE_INSETS: "titleInsets",

    /**
     * The content to display in the info window.  Specify this only
     * if you want a default content area.  The text may contain HTML
     * markup.
     */
    CONTENT: "content",

    /** The alignment of the content text in the info window. */
    ALIGNMENT: "alignment",

    /**
     * The font to use for the title and content areas.  This property will
     * be used only if the default window is used.
     */
    FONT: "font",

    /**
     * The insets to use for the title bar and content areas.  This property
     * will be used only if the default window is used.
     */
    INSETS: "insets",

    /** The width of the info window.  This property is best styled. */
    WIDTH: "width"
  },

  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.INFO_WINDOW, this );
  },

  componentType: echopoint.constants.INFO_WINDOW
});
