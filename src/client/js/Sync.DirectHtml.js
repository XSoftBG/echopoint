/**
 * Component rendering peer: DirectHtml
 *
 * @author Rakesh 2008-06-20
 * @version $Id: Sync.DirectHtml.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.DirectHtmlSync = Core.extend( echopoint.internal.AbstractHtmlComponentSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.DIRECT_HTML, this );
  },

  /** The container element for this component */
  containerType: "div"
});
