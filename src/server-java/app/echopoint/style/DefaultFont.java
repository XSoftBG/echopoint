package echopoint.style;

import static echopoint.util.FontKit.makeFont;
import nextapp.echo.app.Font;

/**
 * A font utility class that is used to enforce an application wide font face
 * policy.  The default font face, font style and font size to use may be
 * specified as system properties with the following keys:
 *
 * <ul>
 *   <li>{@code echopoint.style.DefaultFont.typeface} - Example
 *     {@code "'Verdana, Times New Roman'"}</li>
 *   <li>{@code echopoint.style.DefaultFont.style} - Example {@code BOLD}</li>
 *   <li>{@code echopoint.style.DefaultFont.size} - Example {@code 12pt}</li>
 * </ul>
 *
 * <p>Alternatively, the default font properties may be specified in your
 * {@code web.xml} file as servlet {@code init-param} values if extending
 * from the {@link echopoint.Servlet} class (in the webcontainer tree).</p>
 *
 * <p><b>Note:</b> Please note that the fonts are created using {@link
 * echopoint.util.FontKit#makeFont(String)}, so please follow the same
 * conventions when configuring the font properties.</p>
 *
 * @author Rakesh 2009-05-13
 * @version $Id: DefaultFont.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class DefaultFont
{
  /** The system property used to configure the font typeface. */
  public static final String TYPEFACE_KEY =
      DefaultFont.class.getName() + ".typeface";

  /**
   * The default font face to use if the typeface system property is not set.
   *
   * {@value}
   */
  public static final String TYPEFACE_VALUE =
      "'Verdana, Times New Roman, Lucida Grande'";

  /** The system property used to configure the default font style. */
  public static final String STYLE_KEY =
      DefaultFont.class.getName() + ".style";

  /**
   * The default font style to use for the application if not configured.
   *
   * {@value}
   */
  public static final String STYLE_VALUE = "PLAIN";

  /** The system property used to configure the default font size. */
  public static final String SIZE_KEY =
      DefaultFont.class.getName() + ".size";

  /**
   * The default font size used if the property is not configured.
   *
   * {@value}
   */
  public static final String SIZE_VALUE = "10pt";

  private static String typeface;
  private static String style;
  private static String size;

  /** Static initialiser to read the system defaults. */
  static
  {
    typeface = System.getProperty( TYPEFACE_KEY, TYPEFACE_VALUE );
    style = System.getProperty( STYLE_KEY, STYLE_VALUE );
    size = System.getProperty( SIZE_KEY, SIZE_VALUE );
  }

  private DefaultFont() {}

  /**
   * Return the default font for the application.
   *
   * @return The default font to use for the application.
   */
  public static Font getInstance()
  {
    return makeFont( DefaultFont.typeface + "," +
        DefaultFont.style + "," + DefaultFont.size );
  }

  public static Font getInstance( String style, String size )
  {
    return makeFont( DefaultFont.typeface + "," + style + "," + size );
  }

  /**
   * Return the default font (typeface and style) for the application with
   * the specified size.
   *
   * @param size The size for the requested font.
   * @return The standard font of requested size.
   */
  public static Font fontWithSize( final String size )
  {
    return makeFont( DefaultFont.typeface + "," +
        DefaultFont.style + "," + size );
  }

  /**
   * Return the standard font (typeface and size) with the specified style.
   *
   * @param style The style to use for the font.
   * @return The standard font with the specified style.
   */
  public static Font fontWithStyle( final String style )
  {
    return makeFont( DefaultFont.typeface + "," +
        style + "," + DefaultFont.size );
  }

  /**
   * Set the default typeface to use.  Note that this method will need to be
   * invoked before the stylesheet is loaded.
   *
   * @see echopoint.util.FontKit#makeFont(String)
   * @param typeface The typeface to use as default.
   */
  public static void setTypeFace( final String typeface )
  {
    DefaultFont.typeface = typeface;
  }

  /**
   * Set the default style to use.  Note that this method will need to be
   * invoked before the stylesheet is loaded.
   *
   * @see echopoint.util.FontKit#makeFont(String)
   * @param style The style to use as default.
   */
  public static void setStyle( final String style )
  {
    DefaultFont.style = style;
  }

  /**
   * Set the default size to use.  Note that this method will need to be
   * invoked before the stylesheet is loaded.
   *
   * @see echopoint.util.FontKit#makeFont(String)
   * @param size The size to use as default.
   */
  public static void setSize( final String size )
  {
    DefaultFont.size = size;
  }
}
