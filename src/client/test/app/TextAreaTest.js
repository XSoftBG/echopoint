/**
 * The test class used to test echopoint.TextArea component.
 *
 * @author ASchild 2009-12-28
 * @version $Id: TextAreaTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.TextAreaTest = Core.extend(
{
  $construct: function( testArea )
  {
    var row = new Echo.Row( { styleName: "Default" } );
    row.add( this._createLabel() );
    row.add( new echopoint.Strut() );
    row.add( this._createTextArea() );

    testArea.add( new echopoint.Strut() );
    testArea.add( row );
    testArea.add( new echopoint.Strut() );
    testArea.add( this._createNote() );
    testArea.add( new echopoint.Strut() );
    testArea.add( this._createTextArea2() );
    testArea.add( new echopoint.Strut() );
    testArea.add( this._createNote2() );
    testArea.add( new echopoint.Strut() );
    testArea.add( this._createTextArea3() );
  },

  _createLabel: function()
  {
    return new Echo.Label(
    {
      renderId: "echopointUnitTestTextAreaLabel",
      styleName: "Default",
      text: "Try entering characters"
    });
  },

  _createTextArea: function()
  {
    return new echopoint.TextArea(
    {
        wrap: "off",
        autoResize: true,
        styleName: "Default",
        text: "My textarea\nwrap=off autoResize=true",
        height: 100,
        width: 300
    });
  },

  _createTextArea2: function()
  {
    return new echopoint.TextArea(
    {
        wrap: "off",
        autoResize: false,
        styleName: "Default",
        text: "My textarea\nwrap=off autoResize=false",
        height: 100,
        width: 300
    });
  },

  _createTextArea3: function()
  {
    return new echopoint.TextArea(
    {
        wrap: "virtual",
        autoResize: true,
        styleName: "Default",
        text: "My textarea\nwrap=virtual autoResize=true",
        height: 100,
        width: 300
    });
  },

  _createNote: function()
  {
    return new echopoint.HtmlLabel(
    {
      renderId: "echopointUnitTestTextAreaNote",
      styleName: "Default",
      text: "AutoResize = true and wrap=off"
    });
  },

  _createNote2: function()
  {
    return new echopoint.HtmlLabel(
    {
      renderId: "echopointUnitTestTextAreaNote2",
      styleName: "Default",
      text: "AutoResize = false and wrap=off"
    });
  }
});
