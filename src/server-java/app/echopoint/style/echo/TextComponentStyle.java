package echopoint.style.echo;

import static echopoint.style.echo.ResourceImages.InputFieldBackground;
import static echopoint.util.ColorKit.makeColor;
import static nextapp.echo.app.Component.PROPERTY_BACKGROUND;
import static nextapp.echo.app.text.TextComponent.PROPERTY_BACKGROUND_IMAGE;
import static nextapp.echo.app.text.TextComponent.PROPERTY_BORDER;
import static nextapp.echo.app.text.TextComponent.PROPERTY_DISABLED_FOREGROUND;
import nextapp.echo.app.Border;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import echopoint.style.AbstractStyle;

/**
 * A default style to apply to {@link nextapp.echo.app.text.TextComponent}
 * derivatives.
 *
 * @author Rakesh Vidyadharan 2009-05-24
 * @version $Id: TextComponentStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class TextComponentStyle extends AbstractStyle
{
  private static final long serialVersionUID = 1l;

  /**
   * The background and border colour to apply.
   *
   * {@value}
   */
  public static final String BACKGROUND = "#cfdfff";

  /** {@inheritDoc} */
  @Override
  protected void init()
  {
    super.init();

    set( PROPERTY_BACKGROUND, makeColor( BACKGROUND ) );
    set( PROPERTY_BACKGROUND_IMAGE, new FillImage( InputFieldBackground,
        new Extent( 0 ), new Extent( 50, Extent.PERCENT ),
        FillImage.REPEAT_HORIZONTAL ) );
    set( PROPERTY_BORDER,
        new Border( 2, makeColor( BACKGROUND ), Border.STYLE_GROOVE ) );
    set( PROPERTY_DISABLED_FOREGROUND, makeColor( "#999999") );
  }
}
