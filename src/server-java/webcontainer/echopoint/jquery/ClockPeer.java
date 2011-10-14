/*
 * This file is part of the Echo Point Project.  This project is a
 * collection of Components that have extended the Echo Web Application
 * Framework Version 3.
 *
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */
package echopoint.jquery;

import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

/**
 * Rendering peer for the {@link echopoint.jquery.Clock} component.
 *
 * @author Hans Holmlund 2009-03-23
 * @version $Id: ClockPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class ClockPeer extends JQueryAbstractPeer {

    /**
     * The component name for which this class is a peer.
     */
    private static final String COMPONENT_NAME = Clock.class.getName();

    /**
     * The JS service files to load.
     */
    private static final String[] SERVICE_FILES =
            {
                    "resource/js/jquery/jquery.jclock.js",
                    "resource/js/jquery/Application.Clock.js",
                    "resource/js/jquery/Sync.Clock.js"
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
        return Clock.class;
    }

    public void init(final Context context, final Component component) {
        super.init(context, component);
        final ServerMessage serverMessage =
                (ServerMessage) context.get(ServerMessage.class);
        serverMessage.addLibrary(COMPONENT_NAME);
    }
}
