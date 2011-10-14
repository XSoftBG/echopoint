/**
 * A test class for the echopoint.HttpPane client-side component.
 * Displays a HttpPane component along with a text field for updating the
 * URI displayed in the component.
 *
 * @author Rakesh 2008-07-13
 * @version $Id: HttpPaneTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.HttpPaneTest = Core.extend(
{
  $static:
  {
    DEFAULT_URI: "https://echopoint.dev.java.net/"
  },

  $construct: function( testArea )
  {
    var component = this._createComponent( echopoint.test.HttpPaneTest.DEFAULT_URI );
    var textField = this._createTextField();

    testArea.add( textField );
    testArea.add( this._createButton( textField, component ) );
    testArea.add( component );
  },

  /** Create the text field used to update the URI of the component. */
  _createTextField: function()
  {
    return new Echo.TextField(
    {
      renderId: "echopointUnitTestHttpPaneTextField",
      styleName: "Default",
      text: echopoint.test.HttpPaneTest.DEFAULT_URI
    });
  },

  /**
   * Create the button used to update the specified component URI
   * with the contents of the specified text field.
   *
   * @param textField The text field from which content is to be read.
   * @param component The component whose content is to be set.
   */
  _createButton: function( textField, component )
  {
    var row = new Echo.Row( { style: "Default" } );
    var button = new Echo.Button(
    {
      renderId: "echopointUnitTestHttpPaneDisplay",
      styleName: "Default",
      text: "Load",
      events:
      {
        action: function()
        {
          component.set( echopoint.HttpPane.URI, textField.get( "text" ) );
        }
      }
    } );

    row.add( button );
    return row;
  },

  /** Create the component being tested. */
  _createComponent: function( testUri )
  {
    return new echopoint.HttpPane(
    {
      renderId: "echopointUnitTestHttpPane",
      styleName: "Default",
      uri: testUri
    } );
  }
});
