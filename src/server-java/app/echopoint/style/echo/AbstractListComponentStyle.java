package echopoint.style.echo;

import static echopoint.util.ColorKit.makeColor;
import static nextapp.echo.app.Component.PROPERTY_BACKGROUND;
import static nextapp.echo.app.list.AbstractListComponent.PROPERTY_BORDER;
import nextapp.echo.app.Border;
import echopoint.style.AbstractStyle;

/**
 * The default style to associate with list components.
 *
 * @author Rakesh Vidyadharan 2009-05-24
 * @version $Id: AbstractListComponentStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class AbstractListComponentStyle extends AbstractStyle
{
  private static final long serialVersionUID = 1l;

  /**
   * The colour to use for background and border.
   *
   * {@value}
   */
  public static final String COLOR = "#cfdfff";

  /** {@inheritDoc} */
  @Override
  protected void init()
  {
    super.init();

    set( PROPERTY_BACKGROUND, makeColor( COLOR ) );
    set( PROPERTY_BORDER,
        new Border( 2, makeColor( COLOR ), Border.STYLE_GROOVE ) );
  }
}
