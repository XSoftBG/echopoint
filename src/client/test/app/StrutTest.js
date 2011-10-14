/**
 * A test class for the echopoint.Strut client-side component.
 * Displays strut components in column and row containers to ensure that
 * the height and width style properties are honoured.
 *
 * @author Rakesh 2008-07-20
 * @version $Id: StrutTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.StrutTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createRow() );
    testArea.add( this._createColumn() );
  },

  _createRow: function()
  {
    var row = new Echo.Row( { style: "Default" } );

    row.add( new Echo.Label(
    {
      renderId: "echopointUnitTestStrutRowLabel1",
      styleName: "Default",
      text: "Label 1"
    } ) );

    var strut = new echopoint.Strut(
    {
      renderId: "echopointUnitTestStrutRow",
      styleName: "RowStrut"
    } );
    row.add( strut );

    row.add( new Echo.Label(
    {
      renderId: "echopointUnitTestStrutRowLabel2",
      styleName: "Default",
      text: "Label 2"
    } ) );

    row.add( new echopoint.Strut() );

    var textField = new Echo.TextField(
    {
      renderId: "echopointUnitTestStrutRowTextField",
      styleName: "Default"
    } );
    row.add( textField );

    row.add( new echopoint.Strut() );

    row.add( this._createButton( "Change Width",
        echopoint.internal.AbstractContainer.WIDTH,  strut, textField ) );

    return row;
  },

  _createColumn: function()
  {
    var column = new Echo.Column( { style: "Default" } );

    column.add( new Echo.Label(
    {
      renderId: "echopointUnitTestStrutColumnLabel3",
      styleName: "Default",
      text: "Label 3"
    } ) );

    var strut = new echopoint.Strut(
    {
      renderId: "echopointUnitTestStrutColumn",
      styleName: "ColumnStrut"
    } );
    column.add( strut );

    column.add( new Echo.Label(
    {
      renderId: "echopointUnitTestStrutColumnLabel4",
      styleName: "Default",
      text: "Label 4"
    } ) );

    column.add( new echopoint.Strut() );

    var textField = new Echo.TextField(
    {
      renderId: "echopointUnitTestStrutColumnTextField",
      styleName: "Default"
    } );
    column.add( textField );

    column.add( new echopoint.Strut() );

    var row = new Echo.Row( { style: "Default" } );
    row.add( this._createButton( "Change Height",
        echopoint.internal.AbstractContainer.HEIGHT, strut, textField ) );
    column.add( row );

    return column;
  },

  _createButton: function( title, property, strut, textField )
  {
    var button = new Echo.Button(
    {
      renderId: "echopointUnitTestStrutButton" + property,
      styleName: "Default",
      text: title,
      events:
      {
        action: function()
        {
          strut.set( property,  textField.get( "text" ) );
        }
      }
    } );

    return button;
  }
});
