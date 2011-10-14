package echopoint.jquery;

import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

/**
 * Rendering peer for the {@link echopoint.jquery.TooltipContainerPeer} component.
 *
 * @author Hans Holmlund 2009-04-21
 * @version $Id: TooltipContainerPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */

public class TooltipContainerPeer  extends JQueryAbstractPeer {


    /** The component name for which this class is a peer. */
    private static final String COMPONENT_NAME = TooltipContainer.class.getName();


    /** The JS service files to load. */
    private static final String[] SERVICE_FILES =
            {
                    "resource/js/jquery/jquery.qtip-1.0.0-rc3.min.js",
                    "resource/js/jquery/Application.TooltipContainer.js",
                    "resource/js/jquery/Sync.TooltipContainer.js"
            };


    /** The associated client-side JavaScript module <code>Service</code>. */
    private static final Service COMPONENT_SERVICE =
            JavaScriptService.forResources(COMPONENT_NAME, SERVICE_FILES);

    static {
        WebContainerServlet.getServiceRegistry().add(COMPONENT_SERVICE);
    }

    /**
     * @see nextapp.echo.webcontainer.ComponentSynchronizePeer#getClientComponentType(boolean)
     */
    public String getClientComponentType(boolean shortType) {
        return "echopoint.TooltipContainer";
    }

    /**
     * @see nextapp.echo.webcontainer.ComponentSynchronizePeer#getComponentClass()
     */
    public Class getComponentClass() {
        return TooltipContainer.class;
    }

    /**
     * @see nextapp.echo.webcontainer.ComponentSynchronizePeer#init(nextapp.echo.app.util.Context, nextapp.echo.app.Component)
     */


    public void init(final Context context, final Component component) {
        super.init(context, component);
        final ServerMessage serverMessage =
                (ServerMessage) context.get(ServerMessage.class);
        serverMessage.addLibrary(COMPONENT_NAME);
    }

}
