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
package echopoint.command;

import nextapp.echo.app.Command;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.AbstractCommandSynchronizePeer;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

/**
 * A <code>CommandSynchronizePeer</code> implementation for the {@link JavaScriptEval} command.
 *
 * @author Mikael S\u00f6derman 2009-04-03
 * @version $Id: JavaScriptEvalPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class JavaScriptEvalPeer extends AbstractCommandSynchronizePeer {

    /** The associated client-side JavaScript module <code>Service</code>. */
    private static final Service JAVA_SCRIPT_EVAL_SERVICE = JavaScriptService.forResource("echopoint.JavaScriptEval", 
            "resource/js/RemoteClient.JavaScriptEval.js");

    static {
        WebContainerServlet.getServiceRegistry().add(JAVA_SCRIPT_EVAL_SERVICE);
    }

    /**
     * Default constructor.
     */
    public JavaScriptEvalPeer() {
        super();
        addProperty("script", new AbstractCommandSynchronizePeer.PropertyPeer() {
            public Object getProperty(Context context, Command command) {
                return ((JavaScriptEval) command).getJavaScript();
            }
        });
    }

    /**
     * @see nextapp.echo.webcontainer.CommandSynchronizePeer#getCommandClass()
     */
    public Class getCommandClass() {
        return JavaScriptEval.class;
    }

    /**
     * @see nextapp.echo.webcontainer.AbstractCommandSynchronizePeer#init(nextapp.echo.app.util.Context)
     */
    public void init(Context context) {
        ServerMessage serverMessage = (ServerMessage) context.get(ServerMessage.class);
        serverMessage.addLibrary(JAVA_SCRIPT_EVAL_SERVICE.getId());
    }
}

