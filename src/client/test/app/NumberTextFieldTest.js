/**
 * The test class used to test echopoint.NumberTextField component.
 *
 * @author Rakesh 2009-03-0u
 * @version $Id: NumberTextFieldTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.NumberTextFieldTest = Core.extend(
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
      renderId: "echopointUnitTestNumberTextFieldLabel",
      styleName: "Default",
      text: "Try entering characters"
    });
  },

  _createTextField: function()
  {
    return new echopoint.NumberTextField(
    {
      renderId: "echopointUnitTestNumberTextField",
      styleName: "Default",
      precision: 4
    });
  },

  _createNote: function()
  {
    return new echopoint.HtmlLabel(
    {
      renderId: "echopointUnitTestNumberTextFieldNote",
      styleName: "Default",
      text: "Note that the field will allow only the number characters " +
            "0-9 and the period (.).  Also note that only one period " +
            "character is allowed by the field."
    });
  }
});
