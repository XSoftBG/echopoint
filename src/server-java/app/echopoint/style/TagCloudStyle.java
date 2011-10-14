package echopoint.style;

import static echopoint.TagCloud.PROPERTY_ROLLOVER_BACKGROUND;
import static echopoint.TagCloud.PROPERTY_ROLLOVER_ENABLED;
import static echopoint.TagCloud.PROPERTY_ROLLOVER_FOREGROUND;
import static echopoint.internal.AbstractContainer.PROPERTY_INSETS;
import static echopoint.util.ColorKit.makeColor;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;

/**
 * The default style to associate with {@link echopoint.TagCloud} components.
 *
 * @author Rakesh Vidyadharan 2009-05-18
 * @version $Id: TagCloudStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class TagCloudStyle extends AbstractStyle
{
  private static final long serialVersionUID = 1l;

  /** {@inheritDoc} */
  @Override
  protected void init()
  {
    super.init();

    set( PROPERTY_INSETS, new Insets( new Extent( 1 ) ) );
    set( PROPERTY_ROLLOVER_BACKGROUND, Background.getInstance() );
    set( PROPERTY_ROLLOVER_FOREGROUND, makeColor( "#c1c1c1" ) );
    set( PROPERTY_ROLLOVER_ENABLED, true );
  }
}
