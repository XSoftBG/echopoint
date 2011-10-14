/**
 * Root namespace for the Echopoint client-side test application.
 *
 * @author Rakesh 2008-06-26
 * @version $Id: TestApp.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test = {};

/** The main application class implementation. */
echopoint.test.TestApp = Core.extend( Echo.Application,
{
  /** The {@link MainContent} for the application. */
  _mainContent: null,

  $construct: function()
  {
    Echo.Application.call( this );
    this.rootComponent.removeAll();
    this._mainContent = new echopoint.test.MainContent();
    this.rootComponent.add( this._mainContent );
  },

  /** Return {@link _mainContent}. */
  getMainContent: function() { return this._mainContent; }
});

/** The global application instance. */
var testApp = null;

/** Boostrapping code for the test application. */
function init()
{
  Core.Web.init();
  testApp = new echopoint.test.TestApp();
  var client = new Echo.FreeClient( testApp, document.getElementById( "rootArea" ) );
  client.addResourcePath( "Echo", "lib/echo/" );
  client.addResourcePath( "Extras", "lib/extras/" );
  client.addResourcePath( "echopoint", "../" );
  testApp.setStyleSheet( echopoint.test.TestApp.StyleSheet );
  client.init();
};

