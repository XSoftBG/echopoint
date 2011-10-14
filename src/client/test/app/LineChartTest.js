/**
 * A test class for the echopoint.google.chart.LineChart client-side component.
 * Displays simple and multi value charts to test Google Chart API interaction.
 *
 * @author Rakesh 2008-08-08
 * @version $Id: LineChartTest.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.test.LineChartTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createSimple() );
    testArea.add( this._createComplex() );
  },

  _createSimple: function()
  {
    var xdata = [ 30,60,70,90,95,110 ];
    var xmax = 120;

    var markers = new Array();
    markers[0] = new echopoint.google.chart.model.ShapeMarker( "o", "ff3333", 5 );

    var data = new echopoint.google.chart.model.ChartData( xdata, xmax );
    data.color = "00ff00";
    data.markers = markers;

    var title = new echopoint.google.chart.model.Title();
    title.add( "Simple Chart" );

    var labels = new Array();
    labels[0] = [ "0", "20", "40", "60", "80", "100" ];
    labels[1] = [ "0", "25", "50", "75", "100" ];

    var ranges = new Array();
    ranges[0] = new echopoint.google.chart.model.Range( 20, 125 );
    ranges[1] = new echopoint.google.chart.model.Range( 25, 150 );

    var rangeMarkers = new Array();
    rangeMarkers[0] = new echopoint.google.chart.model.RangeMarker( "r", "ff0000", 0.1, 0.11 );
    rangeMarkers[1] = new echopoint.google.chart.model.RangeMarker( "R", "0000ff", 0.1, 0.11 );

    return new echopoint.google.chart.LineChart(
    {
      renderId: "echopointUnitTestSimpleLineChart",
      styleName: "SimpleChart",
      data: [ data ],
      title: title,
      axisLabels: labels,
      axisRanges: ranges,
      rangeMarkers: rangeMarkers
    });
  },

  _createComplex: function()
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
    data[0].ydata = [ 20,30,40,50,60,70,80 ];
    data[0].ymax = 100;
    data[0].legend = "First";
    data[0].markers = markers1;

    var markers2 = new Array();
    markers2[0] = new echopoint.google.chart.model.ShapeMarker( "x", "ff00ff", 7 );
    markers2[1] = new echopoint.google.chart.model.ShapeMarker( "s", "ff00ff", 7 );
    markers2[2] = new echopoint.google.chart.model.ShapeMarker( "o", "ff00ff", 7 );
    markers2[3] = new echopoint.google.chart.model.ShapeMarker( "d", "ff00ff", 7 );
    markers2[4] = new echopoint.google.chart.model.ShapeMarker( "c", "ff00ff", 7 );
    markers2[5] = new echopoint.google.chart.model.ShapeMarker( "a", "ff00ff", 7 );

    data[1] = new echopoint.google.chart.model.ChartData(
        [ 10,30,40,45,52 ], 60 );
    data[1].ydata = [ 100,90,40,20,10 ]
    data[1].ymax = 110;
    data[1].legend = "Second";
    data[1].markers = markers2;

    var markers3 = new Array();
    markers3[0] = new echopoint.google.chart.model.ShapeMarker( "x", "ff3300", 7 );
    markers3[1] = new echopoint.google.chart.model.ShapeMarker( "a", "ff3300", 7 );
    markers3[2] = new echopoint.google.chart.model.ShapeMarker( "o", "ff3300", 7 );
    markers3[3] = new echopoint.google.chart.model.ShapeMarker( "c", "ff3300", 7 );
    markers3[4] = new echopoint.google.chart.model.ShapeMarker( "t", "ff3300", 7 );

    data[2] = new echopoint.google.chart.model.ChartData( [ -1 ], 0 );
    data[2].ydata = [ 5,33,50,55,7 ];
    data[2].ymax = 60;
    data[2].legend = "Third";
    data[2].markers = markers3;

    var title = new echopoint.google.chart.model.Title( "Complex Chart" );
    title.add( "Multiple Line Title" );

    var labels = new Array();
    labels[0] = [ 0, 20, 40, 60, 80, 100 ];
    labels[1] = [ 0, 25, 50, 75, 100 ];
    labels[2] = [ "Min", "One Third", "Full" ];
    labels[3] = [ 0, 50, 100 ];

    var positions = new Array();
    positions[0] = [];
    positions[1] = [];
    positions[2] = [ 0, 3, 10 ]; // No idea what values to show, this does not work
    positions[3] = [];

    var lineStyles = new Array();
    lineStyles[0] = new echopoint.google.chart.model.LineStyle( 3, 6, 3 );
    lineStyles[1] = new echopoint.google.chart.model.LineStyle( 2, 4, 2 );
    lineStyles[2] = new echopoint.google.chart.model.LineStyle( 4 );

    var ranges = new Array();
    ranges[0] = new echopoint.google.chart.model.RangeMarker( "r", "e5ecf9", 0.35, 0.25 );
    ranges[1] = new echopoint.google.chart.model.RangeMarker( "R", "a0bae9", 0.35, 0.25 );


    var fillArea = new Array();
    fillArea[0] = new echopoint.google.chart.model.FillArea( "b", "224499", 0, 1 );
    fillArea[1] = new echopoint.google.chart.model.FillArea( "b", "76a4fb", 1, 2 );

    return new echopoint.google.chart.LineChart(
    {
      renderId: "echopointUnitTestStrutComplexLineChart",
      styleName: "ComplexChart",
      data: data,
      title: title,
      axisLabels: labels,
      labelPositions: positions,
      lineStyles: lineStyles,
      rangeMarkers: ranges,
      fillArea: fillArea
    });
  }
});
