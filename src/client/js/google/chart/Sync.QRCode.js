/**
 * Component rendering peer: QRCode
 *
 * @author Rakesh 2008-08-28
 * @version: $Id: Sync.QRCode.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.google.chart.QRCodeSync = Core.extend(
    echopoint.google.chart.internal.AbstractChartSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.QRCODE, this );
  },

  /** Over-ridden to return the appropriate chart type. */
  getChartType: function()
  {
    return echopoint.google.chart.QRCode.CHART_TYPE;
  },

  /**
   * Create the Google Chart API URL to use with this chart.  Over-ridden
   * to comply with the reduced URL format for QR codes.
   */
  getUrl: function()
  {
    var url = "http://chart.apis.google.com/chart?chs=";
    url += this.getWidth() + "x" + this.getHeight();
    url += "&cht=" + this.getChartType();

    url = this.setText( url );
    url = this.setEncoding( url );

    return url;
  },

  /**
   * Set the text to be encoded as part of the URL.
   *
   * @param url The URL that is to be modified.
   * @return The modified URL object.
   */
  setText: function( url )
  {
    var text = this.component.get( echopoint.google.chart.QRCode.TEXT );
    url += "&chl=" + escape( text );
    return url;
  },

  /**
   * Set the output encoding to be used as part of the URL.
   *
   * @param url The URL that is to be modified.
   * @return The modified URL object.
   */
  setEncoding: function( url )
  {
    var encoding = this.component.render( echopoint.google.chart.QRCode.ENCODING );
    if ( encoding )
    {
      url += "&choe=" + encoding;
    }

    return url;
  }
});
