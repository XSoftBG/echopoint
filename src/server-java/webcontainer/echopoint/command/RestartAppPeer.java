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

import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.AbstractCommandSynchronizePeer;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

/**
 * A <code>CommandSynchronizePeer</code> implementation for the {@link RestartApp} command.
 *
 * @author ivan
 * @version $Id: RestartAppPeer.java,v 1.1 2011-03-30 14:44:25 ivan Exp $
 */
public class RestartAppPeer extends AbstractCommandSynchronizePeer
{ 
  /** The associated client-side JavaScript module <code>Service</code>. */
  private static final Service RESTART_APP_SERVICE = JavaScriptService.forResource("echopoint.RestartApp",
          "resource/js/RemoteClient.RestartApp.js");
  
  static { WebContainerServlet.getServiceRegistry().add(RESTART_APP_SERVICE); }
  
  public RestartAppPeer() { super(); }
  
  /**
    * @see nextapp.echo.webcontainer.CommandSynchronizePeer#getCommandClass()
    */
  public Class getCommandClass() { return RestartApp.class; }
  
  /**
    * @see nextapp.echo.webcontainer.AbstractCommandSynchronizePeer#init(nextapp.echo.app.util.Context)
    */
  public void init( final Context context)
  {
    final ServerMessage serverMessage = (ServerMessage) context.get(ServerMessage.class);
    serverMessage.addLibrary(RESTART_APP_SERVICE.getId());
  }
}