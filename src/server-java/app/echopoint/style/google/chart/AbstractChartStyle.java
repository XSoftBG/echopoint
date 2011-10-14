package echopoint.style.google.chart;

import static echopoint.google.chart.internal.AbstractChart.PROPERTY_FILL;
import static echopoint.internal.AbstractContainer.PROPERTY_BORDER;
import static echopoint.internal.AbstractContainer.PROPERTY_INSETS;
import static echopoint.util.ColorKit.makeColor;
import static nextapp.echo.app.Component.PROPERTY_FOREGROUND;
import nextapp.echo.app.Border;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import echopoint.style.AbstractStyle;

/**
 * The default base style class for Google Chart API components.
 *
 * @author Rakesh Vidyadharan 2009-05-18
 * @version $Id: AbstractChartStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public abstract class AbstractChartStyle extends AbstractStyle
{
  /** {@inheritDoc} */
  @Override
  protected void init()
  {
    super.init();

    set( PROPERTY_BORDER,
        new Border( 2, makeColor( "#cfdfff" ), Border.STYLE_GROOVE ) );
    set( PROPERTY_FILL, "bg,s,efefef|c,lg,45,ffffff,0,76a4fb,0.75" );
    set( PROPERTY_FOREGROUND, makeColor( "#ff0000" ) );
    set( PROPERTY_INSETS, new Insets( new Extent( 10 ) ) );
  }
}
