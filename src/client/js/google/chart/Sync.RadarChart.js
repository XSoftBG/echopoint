/**
 * Component rendering peer: RadarChart
 *
 * @author Rakesh 2008-08-24
 * @version: $Id: Sync.RadarChart.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.google.chart.RadarChartSync = Core.extend(
    echopoint.google.chart.internal.AdvancedChartSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.RADAR_CHART, this );
  },

  /** Over-ridden to return the appropriate chart type. */
  getChartType: function()
  {
    var lineStyle = this.component.render( echopoint.google.chart.RadarChart.LINE_STYLE );
    return ( lineStyle ) ? lineStyle : echopoint.google.chart.RadarChart.STRAIGHT_LINE;
  }
});
