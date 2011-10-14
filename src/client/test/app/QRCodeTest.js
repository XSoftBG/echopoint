/**
 * A test class for the echopoint.google.chart.QRCode client-side component.
 * Displays simple and multi value charts to test Google Chart API interaction.
 *
 * @author Rakesh 2008-08-27
 * @version $Id: QRCodeTest.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.test.QRCodeTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createQRCode() );
  },

  _createQRCode: function()
  {
    var title = new echopoint.google.chart.model.Title();
    title.add( "QRCode" );

    var text = "EchoPoint text to be encoded."

    return new echopoint.google.chart.QRCode(
    {
      renderId: "echopointUnitTestSimpleQRCode",
      styleName: "SimpleChart",
      title: title,
      text: text
    });
  }
});
