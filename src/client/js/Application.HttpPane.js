/** The name of the HttpPane component. */
echopoint.constants.HTTP_PANE = "echopoint.HttpPane";

/**
 * A container for displaying the contents of a user specified URI.  The
 * contents are displayed in an <code>iframe</code> component that is not
 * available in XHTML-Strict.  If Echo changes to strict DTD, then the
 * iframe will need to be replaced with a <code>object</code> element.
 *
 * @author Rakesh 2008-07-13
 * @version $Id: Application.HttpPane.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.HttpPane = Core.extend( Echo.Component,
{
  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.HTTP_PANE, this );
  },

  /** The properties supported by this component. */
  $static:
  {
    URI: "uri"
  },

  componentType: echopoint.constants.HTTP_PANE
});

