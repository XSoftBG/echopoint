package echopoint.style.google.chart;

import static echopoint.google.chart.BarChart.PROPERTY_SIZE;
import static echopoint.internal.AbstractContainer.PROPERTY_HEIGHT;
import static echopoint.internal.AbstractContainer.PROPERTY_WIDTH;

/**
 * A default style class for the {@link echopoint.google.chart.BarChart}
 * component.
 *
 * @author Rakesh Vidyadharan 2009-05-18
 * @version $Id: BarChartStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class BarChartStyle extends AdvancedChartStyle
{
  private static final long serialVersionUID = 1L;

  /** {@inheritDoc} */
  @Override
  protected void init()
  {
    super.init();

    set( PROPERTY_SIZE, "10,5,15" );
    set( PROPERTY_WIDTH, get( PROPERTY_HEIGHT ) );
  }
}
