package echopoint.style;

import static echopoint.InfoWindow.PROPERTY_INSETS;
import static echopoint.InfoWindow.PROPERTY_OTHER_TEXT_FOREGROUND;
import static echopoint.InfoWindow.PROPERTY_OTHER_TEXT_INSETS;
import static echopoint.InfoWindow.PROPERTY_TEXT_BACKGROUND;
import static echopoint.InfoWindow.PROPERTY_TEXT_FONT;
import static echopoint.InfoWindow.PROPERTY_TEXT_FOREGROUND;
import static echopoint.InfoWindow.PROPERTY_TEXT_INSETS;
import static echopoint.InfoWindow.PROPERTY_TITLE_ALIGNMENT;
import static echopoint.InfoWindow.PROPERTY_TITLE_BACKGROUND;
import static echopoint.InfoWindow.PROPERTY_TITLE_FONT;
import static echopoint.InfoWindow.PROPERTY_TITLE_FOREGROUND;
import static echopoint.InfoWindow.PROPERTY_WIDTH;
import static echopoint.util.ColorKit.makeColor;
import static nextapp.echo.app.Alignment.ALIGN_LEFT;
import static nextapp.echo.app.Component.PROPERTY_BACKGROUND;
import static nextapp.echo.app.Component.PROPERTY_FOREGROUND;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;

/**
 * A default style class for {@link echopoint.InfoWindow} components.
 * Over-ride the {@link #init} method or the individual style configuration
 * methods as appropriate.
 *
 * @author Rakesh Vidyadharan 2009-05-14
 * @version $Id: InfoWindowStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class InfoWindowStyle extends AbstractStyle
{
  private static final long serialVersionUID = 1L;

  /**
   * The default background to use for info window components.
   *
   * {@value}
   */
  public static final String BACKGROUND = "#f9f9f9";

  /**
   * The default foreground colour to use for the component.
   *
   * {@value}
   */
  public static final String FOREGROUND = "#a10202";

  /**
   * The default text background to use for info window components.
   *
   * {@value}
   */
  public static final String TEXT_BACKGROUND = "#cfdfff";

  /**
   * The text foreground colour to use for the component.
   *
   * {@value}
   */
  public static final String TEXT_FOREGROUND = "#2f2f4f";

  /**
   * The text foreground colour to use for the title bar.
   *
   * {@value}
   */
  public static final String TITLE_FOREGROUND = "#ffffff";

  /**
   * Sub-classes may over-ride this method, or the individual styling methods
   * depending upon which approach is more convenient.
   *
   * @see #setProperties()
   * @see #setTextProperties()
   * @see #setOtherTextProperties()
   * @see #setTitleProperties()
   */
  protected void init()
  {
    super.init();
    
    setProperties();
    setTextProperties();
    setOtherTextProperties();
    setTitleProperties();
  }

  /** Set general style properties for the content in info window itself. */
  protected void setProperties()
  {
    set( PROPERTY_BACKGROUND, makeColor( BACKGROUND ) );
    set( PROPERTY_FOREGROUND, makeColor( FOREGROUND ) );
    set( PROPERTY_INSETS, new Insets( new Extent( 10 ) ) );
    set( PROPERTY_WIDTH, new Insets( new Extent( 250 ) ) );
  }

  /** Set the style properties for the text that drives the info window. */
  protected void setTextProperties()
  {
    set( PROPERTY_TEXT_BACKGROUND, makeColor( TEXT_BACKGROUND ) );
    set( PROPERTY_TEXT_FONT, DefaultFont.fontWithStyle( "BOLD" ) );
    set( PROPERTY_TEXT_FOREGROUND, makeColor( TEXT_FOREGROUND ) );
    set( PROPERTY_TEXT_INSETS,
        new Insets( new Extent( 3 ), new Extent( 8 ) ) );
  }

  /** Set the style properties for the text that is displayed in the component. */
  protected void setOtherTextProperties()
  {
    set( PROPERTY_OTHER_TEXT_FOREGROUND, makeColor( "#000000" ) );
    set( PROPERTY_OTHER_TEXT_INSETS,
        new Insets( new Extent( 2 ), new Extent( 5 ) ) );
  }

  /** Set the style properties for title of the info window. */
  protected void setTitleProperties()
  {
    set( PROPERTY_TITLE_ALIGNMENT, ALIGN_LEFT );
    set( PROPERTY_TITLE_BACKGROUND, Background.getInstance() );
    set( PROPERTY_TITLE_FONT, DefaultFont.getInstance( "BOLD", "12px" ) );
    set( PROPERTY_TITLE_FOREGROUND, makeColor( TITLE_FOREGROUND ) );
  }
}
