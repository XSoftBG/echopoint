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
package echopoint.tucana;

import static nextapp.echo.webcontainer.WebContainerServlet.getServiceRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import nextapp.echo.app.Command;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.AbstractCommandSynchronizePeer;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.UserInstance;
import nextapp.echo.webcontainer.service.JavaScriptService;
import echopoint.internal.CommonResources;
import echopoint.internal.CommonService;

/**
 * Synchronisation peer for the {@link echopoint.tucana.DownloadCommand} command.
 *
 * @author Rakesh Vidyadharan 2008-11-10
 * @version $Id: DownloadCommandPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class DownloadCommandPeer extends AbstractCommandSynchronizePeer
{
  private static final Map<String, DownloadCommand> ID_TO_DOWNLOAD_MAP =
      new ConcurrentHashMap<String, DownloadCommand>();

  private static final Service DOWNLOAD_SERVICE =
      JavaScriptService.forResource( DownloadCommand.class.getName(),
          "resource/js/tucana/DownloadService.js" );

  static
  {
    CommonResources.install();
    DownloadService.install();
    getServiceRegistry().add( DOWNLOAD_SERVICE );
  }

  public DownloadCommandPeer()
  {
    super();
    addProperty( "uri", new AbstractCommandSynchronizePeer.PropertyPeer()
    {
      public Object getProperty( Context context, Command command )
      {
        final DownloadCommand download = (DownloadCommand) command;
        final UserInstance userInstance = (UserInstance) context.get( UserInstance.class );
        final String id = download.getRenderId();
        ID_TO_DOWNLOAD_MAP.put( id, download );
        return DownloadService.getInstance().createUri( userInstance, id );
      }
    } );
  }

  @Override
  public void init( Context context )
  {
    super.init( context );
    ServerMessage serverMessage = (ServerMessage)
        context.get( ServerMessage.class );
    serverMessage.addLibrary( CommonService.ECHOPOINT_SERVICE.getId() );
    serverMessage.addLibrary( DOWNLOAD_SERVICE.getId() );
  }

  public Class getCommandClass()
  {
    return DownloadCommand.class;
  }

  /**
   * Returns the {@link echopoint.tucana.DownloadCommand} having the passed
   * id, and removes it from the internal map.
   *
   * <p>This means that a particular download command
   * cannot be re-used. A new download command must be created every time,
   * e.g. each time your download button is clicked.</p>
   *
   * <p>This is necessary to prevent memory leaks.</p>
   *
   * @param id The download id.
   * @return The {@link echopoint.tucana.DownloadCommand} instance.
   */
  public static DownloadCommand getAndRemoveDownload( String id )
  {
    return ID_TO_DOWNLOAD_MAP.remove( id );
  }
}
