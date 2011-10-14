package echopoint.style.google.chart;

import static echopoint.google.chart.internal.AdvancedChart.PROPERTY_AXIS_TYPE;
import static echopoint.google.chart.internal.AdvancedChart.PROPERTY_GRID_LINES;
import static echopoint.google.chart.internal.SimpleChart.PROPERTY_LEGEND_POSITION;

/**
 * A base style class for {@link echopoint.google.chart.internal.AdvancedChart}
 * components.
 *
 * @author Rakesh Vidyadharan 2009-05-19
 * @version $Id: AdvancedChartStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public abstract class AdvancedChartStyle extends AbstractChartStyle
{
  @Override
  protected void init()
  {
    super.init();

    set( PROPERTY_AXIS_TYPE, "x,y,t,r" );
    set( PROPERTY_GRID_LINES, "10,20,5,2" );
    set( PROPERTY_LEGEND_POSITION, "l" );
  }
}
