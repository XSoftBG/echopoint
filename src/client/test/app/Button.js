/**
 * A custom button class that is used to trigger testing of a specified
 * EchoPoint client-side component.
 *
 * @author Rakesh 2008-06-26
 * @version $Id: Button.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.test.Button = Core.extend( Echo.Button,
{
  /** The name of the component that is being tested. */
  _component: null,

  $construct: function( component )
  {
    this._component = component;

    Echo.Button.call( this,
    {
      renderId: "echopointUnitTestButton" + component,
      styleName: "Default",
      text: component,
      events:
      {
        action: Core.method( this, this._actionPerformed )
      }
    });
  },

  /**
   * The event listener for displaying the component test object in
   * {@link MainContent#getTestArea}.
   */
  _actionPerformed: function()
  {
    var mainContent = testApp.getMainContent();
    var testArea = mainContent.getTestArea();
    testArea.removeAll();
    new ( eval( "echopoint.test." + this._component + "Test" ) )( testArea );
  }
});