package echopoint;

import nextapp.echo.app.Component;
import echopoint.google.chart.model.ChartData;

/**
 * <p>&copy; Copyright 2008 <a href='http://sptci.com/' target='_top'>Sans
 * Pareil Technologies, Inc.</a></p>
 *
 * @author Rakesh Vidyadharan 2008-11-12
 * @version $Id: GoogleChartTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public abstract class GoogleChartTest<C extends Component> extends AbstractTest<C>
{
  private static ThreadLocal<ChartData<Integer>> data =
      new ThreadLocal<ChartData<Integer>>();

  protected static void setData( final ChartData<Integer> data )
  {
    GoogleChartTest.data.set( data );
  }

  protected static ChartData<Integer> getData()
  {
    return data.get();
  }
}
