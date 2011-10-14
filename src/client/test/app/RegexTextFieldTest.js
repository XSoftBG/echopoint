/**
 * The test class used to test echopoint.RegexTextField component.
 *
 * @author Rakesh 2009-03-0u
 * @version $Id: RegexTextFieldTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.RegexTextFieldTest = Core.extend(
{
  $construct: function( testArea )
  {
    var row = new Echo.Row( { styleName: "Default" } );
    row.add( this._createLabel() );
    row.add( new echopoint.Strut() );
    row.add( this._createTextField() );

    testArea.add( new echopoint.Strut() );
    testArea.add( row );
    testArea.add( new echopoint.Strut() );
    testArea.add( this._createNote() );
  },

  _createLabel: function()
  {
    return new Echo.Label(
    {
      renderId: "echopointUnitTestRegexTextFieldLabel",
      styleName: "Default",
      text: "Try entering characters"
    });
  },

  _createTextField: function()
  {
    return new echopoint.RegexTextField(
    {
      renderId: "echopointUnitTestRegexTextField",
      styleName: "Default",
      defaultText: "1234.56",
      //text: "1.23",
      regex: "^[\\d]+[.]{0,1}[\\d]{0,2}$"
    });
  },

  _createNote: function()
  {
    return new echopoint.HtmlLabel(
    {
      renderId: "echopointUnitTestRegexTextFieldNote",
      styleName: "Default",
      text: "Note that the field will allow only the number characters " +
            "0-9 and the period (.).  Also note that only one period " +
            "character is allowed by the field.  You may enter up to " +
            "two fractional digits."
    });
  }
});
