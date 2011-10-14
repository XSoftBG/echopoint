package echopoint.style.google.chart;

import echopoint.google.chart.BarChart;
import echopoint.google.chart.LineChart;
import echopoint.google.chart.Map;
import echopoint.google.chart.RadarChart;
import echopoint.google.chart.ScatterPlot;
import echopoint.google.chart.Sparkline;
import echopoint.style.echo.extras.ExtrasStyleSheet;

/**
 * An extensible stylesheet that enforces a default look-and-feel for all
 * EchoPoint Google Chart API components.  Can be used as the starting point
 * for applications built using the framework.  We hope this promotes an
 * object-oriented management of styles for applications.
 *
 * <p>It is recommended that you extend from {@link echopoint.Servlet} (in
 * the webcontainer area) since the base servlet takes care of initialising
 * default style properties from init parameters.</p>
 *
 * <p>This class is based upon the
 * <a href='http://sptci.com/docs/public/com/sptci/echo/style/StyleSheet.html'>StyleSheet</a>
 * implementation that <a href='http://sptci.com/'>SPT</a> has used as the
 * basis for building a number of Echo2/3 applications.</p>
 *
 * @author Rakesh Vidyadharan 2009-05-24
 * @version $Id: ChartStyleSheet.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class ChartStyleSheet extends ExtrasStyleSheet
{
  private static final long serialVersionUID = 1L;

  /** {@inheritDoc} */
  @Override
  protected void init()
  {
    super.init();

    addBarChartStyles();
    addLineChartStyles();
    addMapStyles();
    addScatterPlotStyles();
    addSparklineStyles();
    addRadarChartStyles();
  }

  /** Add styles for {@link echopoint.google.chart.BarChart} components. */
  protected void addBarChartStyles()
  {
    final BarChartStyle style = new BarChartStyle();
    addStyle( BarChart.class, "", style );
    addStyle( BarChart.class, "Default", style );
  }

  /** Add styles for {@link echopoint.google.chart.LineChart} components. */
  protected void addLineChartStyles()
  {
    final LineChartStyle style = new LineChartStyle();
    addStyle( LineChart.class, "", style );
    addStyle( LineChart.class, "Default", style );
  }

  /** Add styles for {@link echopoint.google.chart.Map} components. */
  protected void addMapStyles()
  {
    final MapStyle style = new MapStyle();
    addStyle( Map.class, "", style );
    addStyle( Map.class, "Default", style );
  }

  /** Add styles for {@link echopoint.google.chart.ScatterPlot} components. */
  protected void addScatterPlotStyles()
  {
    final ScatterPlotStyle style = new ScatterPlotStyle();
    addStyle( ScatterPlot.class, "", style );
    addStyle( ScatterPlot.class, "Default", style );
  }

  /** Add styles for {@link echopoint.google.chart.Sparkline} components. */
  protected void addSparklineStyles()
  {
    final SparklineStyle style = new SparklineStyle();
    addStyle( Sparkline.class, "", style );
    addStyle( Sparkline.class, "Default", style );
  }

  /** Add styles for {@link echopoint.google.chart.RadarChart} components. */
  protected void addRadarChartStyles()
  {
    final RadarChartStyle style = new RadarChartStyle();
    addStyle( RadarChart.class, "", style );
    addStyle( RadarChart.class, "Default", style );
  }
}
