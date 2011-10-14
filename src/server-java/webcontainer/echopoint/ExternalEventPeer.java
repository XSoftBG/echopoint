package echopoint;

import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;
import echopoint.externalevent.ExternalEventMonitor;
import echopoint.internal.AbstractPeer;

/**
 * Created: 2009-apr-14
 */
public class ExternalEventPeer extends AbstractPeer {

    /**
     * The component name for which this class is a peer.
     */
    private static final String COMPONENT_NAME = ExternalEventMonitor.class.getName();

    /**
     * The JS service files to load.
     */
    private static final String[] SERVICE_FILES =
            {
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
        return COMPONENT_NAME;
    }


    public Class getComponentClass() {
        return ExternalEventMonitor.class;
    }


    public void init(final Context context, final Component component) {
        super.init(context, component);
        final ServerMessage serverMessage =
                (ServerMessage) context.get(ServerMessage.class);
        serverMessage.addLibrary(COMPONENT_NAME);
    }    
}
