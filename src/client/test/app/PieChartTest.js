/**
 * A test class for the echopoint.google.chart.PieChart client-side component.
 * Displays simple bar charts to test Google Chart API interaction.
 *
 * @author Rakesh 2008-08-21
 * @version $Id: PieChartTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.PieChartTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createChart() );
  },

  _createChart: function()
  {
    var data = new Array();
    data[0] = new echopoint.google.chart.model.ChartData(
        [ 31, 28, 31, 30, 31, 31, 31, 31, 30, 31, 30, 31 ] );

    var title = new echopoint.google.chart.model.Title( "Pie Chart" );

    var labels = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ];

    return new echopoint.google.chart.PieChart(
    {
      renderId: "echopointUnitTestStrutComplexPieChart",
      styleName: "SimpleChart",
      data: data,
      title: title,
      labels: labels
    });
  }
});
