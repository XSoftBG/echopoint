/**
 * A test class for the echopoint.google.chart.Map client-side component.
 * Displays simple and multi value charts to test Google Chart API interaction.
 *
 * @author Rakesh 2008-08-25
 * @version $Id: MapTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.MapTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createSimple() );
    testArea.add( this._createComplex() );
  },

  _createSimple: function()
  {
    var xdata = [ -1 ];
    var data = new echopoint.google.chart.model.ChartData( xdata, 0 );

    var title = new echopoint.google.chart.model.Title();
    title.add( "World" );

    return new echopoint.google.chart.Map(
    {
      renderId: "echopointUnitTestSimpleMap",
      styleName: "SimpleChart",
      data: [ data ],
      title: title
    });
  },

  _createComplex: function()
  {
    var xdata = [ 0, 5, 9 ];
    var data = new echopoint.google.chart.model.ChartData( xdata, 9 );
    var title = new echopoint.google.chart.model.Title( "Africa" );

    return new echopoint.google.chart.Map(
    {
      renderId: "echopointUnitTestStrutComplexMap",
      styleName: "ComplexChart",
      data: [ data ],
      title: title
    });
  }
});
