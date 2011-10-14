/**
 * Component rendering peer: HtmlLabel
 *
 * @author Rakesh 2008-06-20
 * @version $Id: Sync.HtmlLabel.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.HtmlLabelSync = Core.extend( echopoint.internal.AbstractHtmlComponentSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.HTML_LABEL, this );
  },

  /** The container element for this component */
  containerType: "span"
});
