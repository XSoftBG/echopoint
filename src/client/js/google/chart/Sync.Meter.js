/**
 * Component rendering peer: Meter
 *
 * @author Rakesh 2008-08-27
 * @version: $Id: Sync.Meter.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.google.chart.MeterSync = Core.extend(
    echopoint.google.chart.internal.AbstractChartSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.METER, this );
  },

  /** Over-ridden to return the appropriate chart type. */
  getChartType: function()
  {
    return echopoint.google.chart.Meter.CHART_TYPE;
  },

  /**
   * Over-ridden to configure the map regions and colours.
   *
   * @see #setLabel
   * @param url The url object that is to be updated.
   * @return The updated url object.
   */
  setAdditionalParameters: function( url )
  {
    url = this.setLabel( url );
    return url;
  },

  /**
   * Set the label for the meter.
   *
   * @param url The url object that is to be updated.
   * @return The updated url object.
   */
  setLabel: function( url )
  {
    var label = this.component.render( echopoint.google.chart.Meter.LABEL );

    if ( label )
    {
      url += "&chl=" + label;
    }

    return url;
  }
});
