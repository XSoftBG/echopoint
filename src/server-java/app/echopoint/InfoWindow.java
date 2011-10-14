/*
 * This file is part of the Echo Point Project.  This project is a
 * collection of Components that have extended the Echo Web Application
 * Framework Version 3.
 *
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */
package echopoint;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Font;
import nextapp.echo.app.Insets;

/**
 * A component that displays a floating information window when hovering over
 * a <i>driver</i> text.  The driver text and optionally text surrounding the
 * driver text are displayed in this component, while the floating information
 * window is displayed or hidden as the user hovers over or moves out of the
 * driver text.
 *
 * <p>The following shows sample use of this component.</p>
 * <pre>
 *   import echopoint.InfoWindow;
 *
 *     ...
 *     final InfoWindow infoWindow = new InfoWindow();
 *     infoWindow.setTitle( "Note!" );
 *     infoWindow.setContent( "My floating ino window" );
 *     infoWindow.setText( "Hover over me" );
 *     ...
 *     container.add( infoWindow ); // container is any appropriate container component.
 * </pre>
 *
 * @author Rakesh 2008-10-24
 * @version $Id: InfoWindow.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class InfoWindow extends Component
{
  private static final long serialVersionUID = 1l;

  /**
   * Optional prefix text before the text that drives the display of the
   * info window.  The content should be plain text without any markup.
   * This property may be styled.
   */
  public static final String PROPERTY_PREFIX = "prefix";

  /**
   * Optional postfix text after the text that drives the display of the
   * info window.  The content should be plain text without any markup.
   * This property may be styled.
   */
  public static final String PROPERTY_POSTFIX = "postfix";

  /**
   * The text that drives the display of the info window.  Hovering over
   * the text will display the info window.  This property may be styled.
   */
  public static final String PROPERTY_TEXT = "text";

  /** The font to use for the driver text.  This is best styled. */
  public static final String PROPERTY_TEXT_FONT = "textFont";

  /** The foreground for the driver text.  This is best styled. */
  public static final String PROPERTY_TEXT_FOREGROUND = "textForeground";

  /** The background for the driver text.  This is best styled. */
  public static final String PROPERTY_TEXT_BACKGROUND = "textBackground";

  /** The insets for the driver text.  This is best styled. */
  public static final String PROPERTY_TEXT_INSETS = "textInsets";

  /** The font to use for the pre/postfix text.  This is best styled. */
  public static final String PROPERTY_OTHER_TEXT_FONT = "otherTextFont";

  /** The foreground for the pre/postfix text.  This is best styled. */
  public static final String PROPERTY_OTHER_TEXT_FOREGROUND = "otherTextForeground";

  /** The background for the pre/postfix text.  This is best styled. */
  public static final String PROPERTY_OTHER_TEXT_BACKGROUND = "otherTextBackground";

  /** The insets for the pre/postfix text.  This is best styled. */
  public static final String PROPERTY_OTHER_TEXT_INSETS = "otherTextInsets";

  /**
   * The title to display for the info window.  Specify this only if
   * you want a default title bar component.  The text may contain HTML
   * markup.
   */
  public static final String PROPERTY_TITLE = "title";

  /** The alignment to use for the title.  This is best styled. */
  public static final String PROPERTY_TITLE_ALIGNMENT = "titleAlignment";

  /** The font to use for the title.  This is best styled. */
  public static final String PROPERTY_TITLE_FONT = "titleFont";

  /** The foreground for the title.  This is best styled. */
  public static final String PROPERTY_TITLE_FOREGROUND = "titleForeground";

  /** The background for the title.  This is best styled. */
  public static final String PROPERTY_TITLE_BACKGROUND = "titleBackground";

  /** The insets for the title.  This is best styled. */
  public static final String PROPERTY_TITLE_INSETS = "titleInsets";

  /**
   * The content to display in the info window.  Specify this only
   * if you want a default content area.  The text may contain HTML
   * markup.
   */
  public static final String PROPERTY_CONTENT = "content";

  /** The alignment for the content text in the info window. */
  public static final String PROPERTY_ALIGNMENT = "alignment";

  /** The insets to apply for the content within the info window. */
  public static final String PROPERTY_INSETS = "insets";

  /** The width of the info window.  This property is best styled. */
  public static final String PROPERTY_WIDTH = "width";

  /**
   * Return the value of the {@link #PROPERTY_CONTENT} property.
   *
   * @return The content to be displayed in the info window.
   */
  public String getContent()
  {
    return (String) get( PROPERTY_CONTENT );
  }

  /**
   * Set the value of the {@link #PROPERTY_CONTENT} property.
   *
   * @param content The value to set for the property.
   */
  public void setContent( final String content )
  {
    set( PROPERTY_CONTENT, content );
  }

  /**
   * Return the value of the {@link #PROPERTY_TITLE} property.
   *
   * @return The title that drives display of the info window.
   */
  public String getTitle()
  {
    return (String) get( PROPERTY_TITLE );
  }

  /**
   * Set the value of the {@link #PROPERTY_TITLE} property.
   *
   * @param title The value to set for the property.
   */
  public void setTitle( final String title )
  {
    set( PROPERTY_TITLE, title );
  }

  /**
   * Return the value of the {@link #PROPERTY_TITLE_ALIGNMENT} property.
   *
   * @return The alignment to use for the title text.
   */
  public Alignment getTitleAlignment()
  {
    return (Alignment) get( PROPERTY_TITLE_ALIGNMENT );
  }

  /**
   * Set the value of the {@link #PROPERTY_TITLE_ALIGNMENT} property.
   *
   * @param titleAlignment The value to set for the property.
   */
  public void setTitleAlignment( final Alignment titleAlignment )
  {
    set( PROPERTY_TITLE_ALIGNMENT, titleAlignment );
  }

  /**
   * Return the value of the {@link #PROPERTY_TITLE_FONT} property.
   *
   * @return The font to use for the title.
   */
  public Font getTitleFont()
  {
    return (Font) get( PROPERTY_TITLE_FONT );
  }

  /**
   * Set the value of the {@link #PROPERTY_TITLE_FONT} property.
   *
   * @param titleFont The value to set for the property.
   */
  public void setTitleFont( final Font titleFont )
  {
    set( PROPERTY_TITLE_FONT, titleFont );
  }

  /**
   * Return the value of the {@link #PROPERTY_TITLE_FOREGROUND} property.
   *
   * @return The font to use for the title.
   */
  public Color getTitleForeground()
  {
    return (Color) get( PROPERTY_TITLE_FOREGROUND );
  }

  /**
   * Set the value of the {@link #PROPERTY_TITLE_FOREGROUND} property.
   *
   * @param titleForeground The value to set for the property.
   */
  public void setTitleForeground( final Color titleForeground )
  {
    set( PROPERTY_TITLE_FOREGROUND, titleForeground );
  }

  /**
   * Return the value of the {@link #PROPERTY_TITLE_BACKGROUND} property.
   *
   * @return The font to use for the title.
   */
  public Color getTitleBackground()
  {
    return (Color) get( PROPERTY_TITLE_BACKGROUND );
  }

  /**
   * Set the value of the {@link #PROPERTY_TITLE_BACKGROUND} property.
   *
   * @param titleBackground The value to set for the property.
   */
  public void setTitleBackground( final Color titleBackground )
  {
    set( PROPERTY_TITLE_BACKGROUND, titleBackground );
  }

  /**
   * Return the value of the {@link #PROPERTY_TITLE_INSETS} property.
   *
   * @return The insets to use for the title.
   */
  public Insets getTitleInsets()
  {
    return (Insets) get( PROPERTY_TITLE_INSETS );
  }

  /**
   * Set the value of the {@link #PROPERTY_TITLE_INSETS} property.
   *
   * @param titleInsets The value to set for the property.
   */
  public void setTitleInsets( final Insets titleInsets )
  {
    set( PROPERTY_TITLE_INSETS, titleInsets );
  }

  /**
   * Return the value of the {@link #PROPERTY_PREFIX} property.
   *
   * @return The optional prefix text to display before the driver text.
   */
  public String getPrefix()
  {
    return (String) get( PROPERTY_PREFIX );
  }

  /**
   * Set the value of the {@link #PROPERTY_PREFIX} property.
   *
   * @param prefix The value to set for the property.
   */
  public void setPrefix( final String prefix )
  {
    set( PROPERTY_PREFIX, prefix );
  }

  /**
   * Return the value of the {@link #PROPERTY_POSTFIX} property.
   *
   * @return The postfix text to display after the driver text.
   */
  public String getPostfix()
  {
    return (String) get( PROPERTY_POSTFIX );
  }

  /**
   * Set the value of the {@link #PROPERTY_POSTFIX} property.
   *
   * @param postfix The value to set for the property.
   */
  public void setPostfix( final String postfix )
  {
    set( PROPERTY_POSTFIX, postfix );
  }

  /**
   * Return the value of the {@link #PROPERTY_TEXT} property.
   *
   * @return The text that drives display of the info window.
   */
  public String getText()
  {
    return (String) get( PROPERTY_TEXT );
  }

  /**
   * Set the value of the {@link #PROPERTY_TEXT} property.
   *
   * @param text The value to set for the property.
   */
  public void setText( final String text )
  {
    set( PROPERTY_TEXT, text );
  }

  /**
   * Return the value of the {@link #PROPERTY_TEXT_FONT} property.
   *
   * @return The font to use for the driver text.
   */
  public Font getTextFont()
  {
    return (Font) get( PROPERTY_TEXT_FONT );
  }

  /**
   * Set the value of the {@link #PROPERTY_TEXT_FONT} property.
   *
   * @param textFont The value to set for the property.
   */
  public void setTextFont( final Font textFont )
  {
    set( PROPERTY_TEXT_FONT, textFont );
  }

  /**
   * Return the value of the {@link #PROPERTY_TEXT_FOREGROUND} property.
   *
   * @return The font to use for the driver text.
   */
  public Color getTextForeground()
  {
    return (Color) get( PROPERTY_TEXT_FOREGROUND );
  }

  /**
   * Set the value of the {@link #PROPERTY_TEXT_FOREGROUND} property.
   *
   * @param textForeground The value to set for the property.
   */
  public void setTextForeground( final Color textForeground )
  {
    set( PROPERTY_TEXT_FOREGROUND, textForeground );
  }

  /**
   * Return the value of the {@link #PROPERTY_TEXT_BACKGROUND} property.
   *
   * @return The font to use for the driver text.
   */
  public Color getTextBackground()
  {
    return (Color) get( PROPERTY_TEXT_BACKGROUND );
  }

  /**
   * Set the value of the {@link #PROPERTY_TEXT_BACKGROUND} property.
   *
   * @param textBackground The value to set for the property.
   */
  public void setTextBackground( final Color textBackground )
  {
    set( PROPERTY_TEXT_BACKGROUND, textBackground );
  }

  /**
   * Return the value of the {@link #PROPERTY_TEXT_INSETS} property.
   *
   * @return The insets to use for the driver text.
   */
  public Insets getTextInsets()
  {
    return (Insets) get( PROPERTY_TEXT_INSETS );
  }

  /**
   * Set the value of the {@link #PROPERTY_TEXT_INSETS} property.
   *
   * @param textInsets The value to set for the property.
   */
  public void setTextInsets( final Insets textInsets )
  {
    set( PROPERTY_TEXT_INSETS, textInsets );
  }

  /**
   * Return the value of the {@link #PROPERTY_OTHER_TEXT_FONT} property.
   *
   * @return The font to use for the pre/postfix text.
   */
  public Font getOtherTextFont()
  {
    return (Font) get( PROPERTY_OTHER_TEXT_FONT );
  }

  /**
   * Set the value of the {@link #PROPERTY_OTHER_TEXT_FONT} property.
   *
   * @param textFont The value to set for the property.
   */
  public void setOtherTextFont( final Font textFont )
  {
    set( PROPERTY_OTHER_TEXT_FONT, textFont );
  }

  /**
   * Return the value of the {@link #PROPERTY_OTHER_TEXT_FOREGROUND} property.
   *
   * @return The font to use for the pre/postfix text.
   */
  public Color getOtherTextForeground()
  {
    return (Color) get( PROPERTY_OTHER_TEXT_FOREGROUND );
  }

  /**
   * Set the value of the {@link #PROPERTY_OTHER_TEXT_FOREGROUND} property.
   *
   * @param textForeground The value to set for the property.
   */
  public void setOtherTextForeground( final Color textForeground )
  {
    set( PROPERTY_OTHER_TEXT_FOREGROUND, textForeground );
  }

  /**
   * Return the value of the {@link #PROPERTY_OTHER_TEXT_BACKGROUND} property.
   *
   * @return The font to use for the pre/postfix text.
   */
  public Color getOtherTextBackground()
  {
    return (Color) get( PROPERTY_OTHER_TEXT_BACKGROUND );
  }

  /**
   * Set the value of the {@link #PROPERTY_OTHER_TEXT_BACKGROUND} property.
   *
   * @param textBackground The value to set for the property.
   */
  public void setOtherTextBackground( final Color textBackground )
  {
    set( PROPERTY_OTHER_TEXT_BACKGROUND, textBackground );
  }

  /**
   * Return the value of the {@link #PROPERTY_OTHER_TEXT_INSETS} property.
   *
   * @return The insets to use for the pre/postfix text.
   */
  public Insets getOtherTextInsets()
  {
    return (Insets) get( PROPERTY_OTHER_TEXT_INSETS );
  }

  /**
   * Set the value of the {@link #PROPERTY_OTHER_TEXT_INSETS} property.
   *
   * @param textInsets The value to set for the property.
   */
  public void setOtherTextInsets( final Insets textInsets )
  {
    set( PROPERTY_OTHER_TEXT_INSETS, textInsets );
  }

  /**
   * Return the value of the {@link #PROPERTY_ALIGNMENT} property.
   *
   * @return The alignment to use for the content text.
   */
  public Alignment getAlignment()
  {
    return (Alignment) get( PROPERTY_ALIGNMENT );
  }

  /**
   * Set the value of the {@link #PROPERTY_ALIGNMENT} property.
   *
   * @param alignment The value to set for the property.
   */
  public void setAlignment( final Alignment alignment )
  {
    set( PROPERTY_ALIGNMENT, alignment );
  }

  /**
   * Return the value of the {@link #PROPERTY_INSETS} property.
   *
   * @return The insets to use for the pre/postfix text.
   */
  public Insets getInsets()
  {
    return (Insets) get( PROPERTY_INSETS );
  }

  /**
   * Set the value of the {@link #PROPERTY_INSETS} property.
   *
   * @param insets The value to set for the property.
   */
  public void setInsets( final Insets insets )
  {
    set( PROPERTY_INSETS, insets );
  }

  /**
   * Return the value of the {@link #PROPERTY_WIDTH} property.
   *
   * @return The width of the info window component.
   */
  public Extent getWidth()
  {
    return (Extent) get( PROPERTY_WIDTH );
  }

  /**
   * Set the value of the {@link #PROPERTY_WIDTH} property.
   *
   * @param width The value to set for the property.
   */
  public void setWidth( final Extent width )
  {
    set( PROPERTY_WIDTH, width );
  }
}
