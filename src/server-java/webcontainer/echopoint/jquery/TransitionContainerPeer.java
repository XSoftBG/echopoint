package echopoint.jquery;

import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

/**
 * Rendering peer for the {@link TransitionContainer} component.
 *
 * @author Mikael S\u00f6derman 2009-06-03
 * @version $Id: TransitionContainerPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class TransitionContainerPeer extends JQueryAbstractPeer {

    /**
     * The component name for which this class is a peer.
     */
    private static final String COMPONENT_NAME = TransitionContainer.class.getName();

    /**
     * The JS service files to load.
     */
    private static final String[] SERVICE_FILES =
            {
                    "resource/js/jquery/jquery-ui-1.7.1.core.min.js",
                    "resource/js/jquery/jquery-ui-1.7.1.effects.min.js",
                    "resource/js/jquery/jquery-CallbackContext.js",
                    "resource/js/jquery/Application.TransitionContainer.js",
                    "resource/js/jquery/Sync.TransitionContainer.js"
            };


    /**
     * The service for the client side peer for this component.
     */
    private static final Service COMPONENT_SERVICE =
            JavaScriptService.forResources(COMPONENT_NAME, SERVICE_FILES);

    static {
        WebContainerServlet.getServiceRegistry().add(COMPONENT_SERVICE);
    }

    public String getClientComponentType(boolean b) {
        return "echopoint.TransitionContainer";
    }

    public Class getComponentClass() {
        return TransitionContainer.class;
    }

    public void init(final Context context, final Component component) {
        super.init(context, component);
        final ServerMessage serverMessage =
                (ServerMessage) context.get(ServerMessage.class);
        serverMessage.addLibrary(COMPONENT_NAME);
    }
}
