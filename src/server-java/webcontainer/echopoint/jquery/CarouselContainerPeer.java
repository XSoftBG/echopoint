package echopoint.jquery;

import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

/**
 * Rendering peer for the {@link echopoint.jquery.CarouselContainer} component.
 *
 * @author Hans Holmlund 2009-07-09
 * @version $Id: CarouselContainerPeer.java,v 1.2 2010-12-06 10:11:50 perxi Exp $
 */
public class CarouselContainerPeer extends JQueryAbstractPeer {


    /** The component name for which this class is a peer. */
    private static final String COMPONENT_NAME = CarouselContainer.class.getName();


    /** The JS service files to load. */
    private static final String[] SERVICE_FILES =
            {
                    "resource/js/jquery/jquery.mousewheel.min.js",
                    "resource/js/jquery/jcarousellite_1.0.1.min.js",
                    "resource/js/jquery/jquery-CallbackContext.js",
                    "resource/js/jquery/Application.CarouselContainer.js",
                    "resource/js/jquery/Sync.CarouselContainer.js"
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
        return COMPONENT_NAME;
    }

    /**
     * @see nextapp.echo.webcontainer.ComponentSynchronizePeer#getComponentClass()
     */
    public Class getComponentClass() {
        return CarouselContainer.class;
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
