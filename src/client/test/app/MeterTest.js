/**
 * A test class for the echopoint.google.chart.Meter client-side component.
 * Displays simple and multi value charts to test Google Chart API interaction.
 *
 * @author Rakesh 2008-08-27
 * @version $Id: MeterTest.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.test.MeterTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createMeter() );
  },

  _createMeter: function()
  {
    var xdata = [ 70 ];
    var data = new echopoint.google.chart.model.ChartData( xdata, 100 );
    var title = new echopoint.google.chart.model.Title();
    title.add( "Google-o-meter" );

    return new echopoint.google.chart.Meter(
    {
      renderId: "echopointUnitTestSimpleMeter",
      styleName: "SimpleChart",
      data: [ data ],
      title: title
    });
  }
});
