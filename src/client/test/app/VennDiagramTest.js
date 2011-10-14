/**
 * A test class for the echopoint.google.chart.VennDiagram client-side component.
 * Displays simple bar charts to test Google Chart API interaction.
 *
 * @author Rakesh 2008-08-22
 * @version $Id: VennDiagramTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.VennDiagramTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createChart() );
  },

  _createChart: function()
  {
    var data = new Array();
    data[0] = new echopoint.google.chart.model.ChartData(
        [ 100, 80, 60, 30, 30, 30, 10 ] );

    var title = new echopoint.google.chart.model.Title( "Venn Diagram" );

    return new echopoint.google.chart.VennDiagram(
    {
      renderId: "echopointUnitTestStrutComplexVennDiagram",
      styleName: "SimpleChart",
      data: data,
      title: title
    });
  }
});
