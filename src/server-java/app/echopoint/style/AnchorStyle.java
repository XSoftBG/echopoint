package echopoint.style;

import static echopoint.Anchor.PROPERTY_TARGET;
import static echopoint.util.ColorKit.makeColor;
import static nextapp.echo.app.Component.PROPERTY_FOREGROUND;

/**
 * The default style used for all {@link echopoint.Anchor} components.
 *
 * @author Rakesh 2009-05-12
 * @version $Id: AnchorStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class AnchorStyle extends AbstractStyle
{
  private static final long serialVersionUID = 1L;

  /**
   * The default foreground colour for the anchor tag.
   *
   * {@value}
   */
  public static final String COLOR = "#2f2f4f";

  /**
   * The default target to associate with the anchor tag.
   *
   * {@value}
   */
  public static final String TARGET = "_blank";

  /** {@inheritDoc} */
  protected void init()
  {
    super.init();
    set( PROPERTY_FOREGROUND, makeColor( COLOR ) );
    set( PROPERTY_TARGET, TARGET );
  }
}
