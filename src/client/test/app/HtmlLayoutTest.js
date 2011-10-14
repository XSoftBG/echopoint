/**
 * A test class for the echopoint.HtmlLayout client-side component.
 *
 * @author Rakesh 2009-04-06
 * @version $Id: HtmlLayoutTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.HtmlLayoutTest = Core.extend(
{
  $construct: function( testArea )
  {
    var html = this._createHtmlContainer();
    html.add( this._createOne() );
    html.add( this._createTwo() );
    html.add( this._createThree() );

    testArea.add( html );
  },

  _createHtmlContainer: function()
  {
    return new echopoint.HtmlLayout(
    {
      renderId: "echopointUnitTestHtmlLayout",
      styleName: "Default",
      text: "<table border='1'>" +
            "<tr>" +
            "<td colspan='2'>" +
            "This is regular HTML text in component!" +
            "</td>" +
            "</tr>" +
            "<tr>" +
            "<td id='one' colspan='2'>" +
            "</td>" +
            "</tr>" +
            "<tr>" +
            "<td id='two'>" +
            "</td>" +
            "<td id='three'>" +
            "</td>" +
            "</tr>" +
            "<tr>" +
            "<td>" +
            "A regular column." +
            "</td>" +
            "<td>" +
            "Another regular column." +
            "</td>" +
            "</tr>" +
            "</table>"
    } );
  },

  _createOne: function()
  {
    return new Echo.Button(
    {
      renderId: "echopointUnitTestHtmlContainerOne",
      styleName: "Default",
      layoutData: { containerId: "one" },
      text: "Button One"
    });
  },

  _createTwo: function()
  {
    return new Echo.Label(
    {
      renderId: "echopointUnitTestHtmlContainerTwo",
      styleName: "Default",
      layoutData: { containerId: "two" },
      text: "Label One"
    });
  },

  _createThree: function()
  {
    return new Echo.Label(
    {
      renderId: "echopointUnitTestHtmlContainerThree",
      styleName: "Default",
      layoutData: { containerId: "three" },
      text: "Label Two"
    });
  }
} );
