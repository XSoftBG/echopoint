package echopoint.style;

import static echopoint.ProgressBar.PROPERTY_BAR_BACKGROUND;
import static echopoint.internal.AbstractContainer.PROPERTY_BORDER;
import static echopoint.internal.AbstractContainer.PROPERTY_HEIGHT;
import static echopoint.internal.AbstractContainer.PROPERTY_INSETS;
import static echopoint.util.ColorKit.makeColor;
import static nextapp.echo.app.Component.PROPERTY_BACKGROUND;
import static nextapp.echo.app.Component.PROPERTY_FOREGROUND;
import nextapp.echo.app.Border;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;

/**
 * A default style for the {@link echopoint.ProgressBar} component.
 *
 * @author Rakesh Vidyadharan 2009-05-15
 * @version $Id: ProgressBarStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class ProgressBarStyle extends AbstractStyle
{
  private static final long serialVersionUID = 1L;

  /**
   * The default background colour to use for the component.
   *
   * {@value}
   */
  public static final String BACKGROUND = "#a1a1a1";

  /**
   * The default foreground colour to use for the component.
   *
   * {@value}
   */
  public static final String FOREGROUND = "#ffffff";

  /**
   * The default bar background colour to use for the component.
   *
   * {@value}
   */
  public static final String BAR_BACKGROUND = "#1a428a";

  /** {@inheritDoc} */
  @Override
  protected void init()
  {
    super.init();

    set( PROPERTY_BACKGROUND, makeColor( BACKGROUND ) );
    set( PROPERTY_FOREGROUND, makeColor( FOREGROUND ) );
    set( PROPERTY_BAR_BACKGROUND, makeColor( BAR_BACKGROUND ) );
    set( PROPERTY_BORDER,
        new Border( 1, makeColor( "#3d3d3d" ), Border.STYLE_INSET ) );
    set( PROPERTY_HEIGHT, new Extent( 25 ) );
    set( PROPERTY_INSETS, new Insets( new Extent( 1 ) ) );
  }
}
