/**
 * Component rendering peer: Sparkline
 *
 * @author Rakesh 2008-08-20
 * @version: $Id: Sync.Sparkline.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.google.chart.SparklineSync = Core.extend(
    echopoint.google.chart.internal.AdvancedChartSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.SPARKLINE, this );
  },

  /** Over-ridden to return the appropriate chart type. */
  getChartType: function()
  {
    return echopoint.google.chart.Sparkline.CHART_TYPE;
  }
});
