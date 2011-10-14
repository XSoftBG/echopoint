/**
 * A test class for the echopoint.google.chart.ScatterPlot client-side component.
 * Displays simple and multi value charts to test Google Chart API interaction.
 *
 * @author Rakesh 2008-08-22
 * @version $Id: ScatterPlotTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.ScatterPlotTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createChart() );
  },

  _createChart: function()
  {
    var data = new Array();
    data[0] = new echopoint.google.chart.model.ChartData(
        [ 0, 30, 60, 70, 90, 95, 100 ], 110 );
    data[0].ydata = [ 20, 30, 40, 50, 60, 70, 80 ];
    data[0].ymax = 100;
    data[0].size = [ 1, 2, 3, 4, 5, 6, 7 ];

    var title = new echopoint.google.chart.model.Title( "ScatterPlot" );

    return new echopoint.google.chart.ScatterPlot(
    {
      renderId: "echopointUnitTestStrutComplexScatterPlot",
      styleName: "ComplexChart",
      data: data,
      title: title
    });
  }
});
