package echopoint.style;

import static echopoint.util.ColorKit.makeColor;
import nextapp.echo.app.Color;

/**
 * A utility class used to enforce a application wide standard background
 * colour for components that need a standard background colour.  Note that
 * this class uses {@link echopoint.util.ColorKit#makeColor(String)} to
 * initialise the colour.  Please use a format as documented.
 *
 * <p>The default colour to use may be specified as a system property or as
 * a servlet {@code init-param} if using the {@link echopoint.Servlet} in
 * your application.  The key to use is {@code
 * echopoint.style.Background.value}.</p>
 *
 * @author Rakesh Vidyadharan 2009-05-14
 * @version $Id: Background.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class Background
{
  /** The property used to configure the default colour. */
  public static final String BACKGROUND_KEY =
      Background.class.getName() + ".value";

  /**
   * The default background value to use if not configured.
   *
   * {@value}
   */
  public static final String BACKGROUND = "#32476a";

  /** The default colour value to use. */
  private static String color;

  /** Static initialiser to initialise the default colour. */
  static
  {
    color = System.getProperty( BACKGROUND_KEY, BACKGROUND );
  }

  /**
   * Return the default background colour.
   *
   * @return The default background colour to use.
   */
  public static Color getInstance()
  {
    return makeColor( color );
  }

  /**
   * Set the default colour value to use.  Note that this must be set before
   * the style sheet is loaded to be fully useful.
   *
   * @param value The colour value to use.
   */
  public static void setBackground( final String value )
  {
    color = value;
  }
}
