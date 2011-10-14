/**
 * A test class for the echopoint.BorderLayout client-side component.
 * Displays two container components, one full and the other partial.
 *
 * @author Rakesh 2009-03-31
 * @version $Id: BorderLayoutTest.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.test.BorderLayoutTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createFull() );
    testArea.add( this._createCentre() );
  },

  _createFull: function()
  {
    var layout = new echopoint.BorderLayout(
    {
      renderId: "echopointUnitTestBorderLayoutFull",
      styleName: "Default"
    } );

    var layoutData = { alignment: "center", background: "#0000ff" };

    var north = new echopoint.HtmlLabel(
    {
      renderId: "echopointUnitTestBoderLayoutFullNorth",
      layoutData: layoutData,
      text: "<b>This is the north content.  Should stretch across area.</b>"
    } );
    layout.addToRegion( north, echopoint.BorderLayout.NORTH );

    layoutData = { background: "#00ff00" };
    var west = new echopoint.HtmlLabel(
    {
      renderId: "echopointUnitTestBoderLayoutFullWest",
      layoutData: layoutData,
      text: "<b>West region</b>"
    } );
    layout.addToRegion( west, echopoint.BorderLayout.WEST );

    layoutData = { alignment: "center", background: "#a1a1a1" };
    var centre = new echopoint.HtmlLabel(
    {
      renderId: "echopointUnitTestBoderLayoutFullCentre",
      layoutData: layoutData,
      text: "<b>This is the centre content.  Should fill most of central area.</b>"
    } );
    layout.addToRegion( centre, echopoint.BorderLayout.CENTER );

    layoutData = { background: "#ff0000" };
    var east = new echopoint.HtmlLabel(
    {
      renderId: "echopointUnitTestBoderLayoutFullEast",
      layoutData: layoutData,
      text: "<b>East region</b>"
    } );
    layout.addToRegion( east, echopoint.BorderLayout.EAST );

    layoutData = { alignment: "center", background: "#2f2f4f" };
    var south = new echopoint.HtmlLabel(
    {
      renderId: "echopointUnitTestBoderLayoutFullSouth",
      layoutData: layoutData,
      text: "<b>This is the south content.  Should stretch across area.</b>"
    } );
    layout.addToRegion( south, echopoint.BorderLayout.SOUTH );

    try
    {
      layout.addToRegion( south, echopoint.BorderLayout.SOUTH );
      document.writeln( "Illegal child exception should have been thrown!" );
    }
    catch ( e )
    {
    }

    return layout;
  },

  _createCentre: function()
  {
    var centre = new echopoint.HtmlLabel(
    {
      renderId: "echopointUnitTestBoderLayoutPartialCentre",
      text: "<b>This is the centre content.  Should fill most of central area.</b>"
    } );

    var layout = new echopoint.BorderLayout(
    {
      renderId: "echopointUnitTestBorderLayoutPartial",
      styleName: "Default"
    } );
    layout.addToRegion( centre );

    return layout;
  }
} );
