package echopoint.jquery;

import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import echopoint.internal.AbstractPeer;
import echopoint.internal.CommonResources;
import echopoint.internal.CommonService;

/**
 * An abstract peer that may be used as the base class for all jQuery peers. Ensures
 * that the jQuery library is boot strapped.
 *
 * @see echopoint.internal.CommonResources
 * @see echopoint.internal.CommonService
 * @author Hans Holmlund 2009-04-21
 * @version $Id: JQueryAbstractPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public abstract class JQueryAbstractPeer extends AbstractPeer
{
    /** Register the core services */
    static
    {
        CommonResources.install();
    }

    /**
     * {@inheritDoc}
     * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#init
     */
    @Override
    public void init( final Context context, final Component component )
    {
        super.init( context, component );
        ServerMessage serverMessage =
                (ServerMessage) context.get( ServerMessage.class );
        serverMessage.addLibrary( CommonService.JQUERY_SERVICE.getId() );
    }
}

