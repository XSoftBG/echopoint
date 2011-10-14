/**
 * A test class for the echopoint.google.chart.BarChart client-side component.
 * Displays simple bar charts to test Google Chart API interaction.
 *
 * @author Rakesh 2008-08-19
 * @version $Id: BarChartTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.BarChartTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createChart() );
  },

  _createChart: function()
  {
    var markers1 = new Array();
    markers1[0] = new echopoint.google.chart.model.ShapeMarker( "a", "00ff33", 7 );
    markers1[1] = new echopoint.google.chart.model.ShapeMarker( "c", "00ff33", 7 );
    markers1[2] = new echopoint.google.chart.model.ShapeMarker( "d", "00ff33", 7 );
    markers1[3] = new echopoint.google.chart.model.ShapeMarker( "o", "00ff33", 7 );
    markers1[4] = new echopoint.google.chart.model.ShapeMarker( "s", "00ff33", 7 );
    markers1[5] = new echopoint.google.chart.model.ShapeMarker( "tValue+7", "00ff33", 7 );
    markers1[6] = new echopoint.google.chart.model.ShapeMarker( "x", "00ff33", 7 );

    var data = new Array();
    data[0] = new echopoint.google.chart.model.ChartData(
        [ 0, 30, 60, 70, 90, 95, 100 ], 110 );
    data[0].markers = markers1;

    var title = new echopoint.google.chart.model.Title( "Bar Chart" );

    var labels = new Array();
    labels[0] = [ 0, 20, 40, 60, 80, 100 ];
    labels[1] = [ 0, 25, 50, 75, 100 ];
    labels[2] = [ "Min", "One Third", "Full" ];
    labels[3] = [ 0, 50, 100 ];

    var positions = new Array();
    positions[0] = [];
    positions[1] = [];
    positions[2] = [ 0, 3, 10 ]; // No idea what values to show, this does not seem to work
    positions[3] = [];

    var lineStyles = new Array();
    lineStyles[0] = new echopoint.google.chart.model.LineStyle( 3, 6, 3 );
    lineStyles[1] = new echopoint.google.chart.model.LineStyle( 2, 4, 2 );
    lineStyles[2] = new echopoint.google.chart.model.LineStyle( 4 );

    var ranges = new Array();
    ranges[0] = new echopoint.google.chart.model.RangeMarker( "r", "e5ecf9", 0.35, 0.25 );
    ranges[1] = new echopoint.google.chart.model.RangeMarker( "R", "a0bae9", 0.35, 0.25 );

    return new echopoint.google.chart.BarChart(
    {
      renderId: "echopointUnitTestStrutComplexBarChart",
      styleName: "SimpleChart",
      data: data,
      title: title,
      axisLabels: labels,
      labelPositions: positions,
      lineStyles: lineStyles,
      rangeMarkers: ranges,
      zeroLine: "0.5"
    });
  }
});
