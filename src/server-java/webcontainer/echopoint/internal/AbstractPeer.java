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

package echopoint.internal;

import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.AbstractComponentSynchronizePeer;
import nextapp.echo.webcontainer.ServerMessage;

/**
 * An abstract peer that may be used as the base class for all peers.  Ensures
 * that the echopoint library is boot strapped.
 *
 * @see CommonResources
 * @see CommonService
 * @author Rakesh 2008-07-20
 * @version $Id: AbstractPeer.java,v 1.2 2011-05-30 14:44:56 perxi Exp $
 */
public abstract class AbstractPeer extends AbstractComponentSynchronizePeer
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
    final ServerMessage serverMessage =
            (ServerMessage) context.get( ServerMessage.class );
    serverMessage.addLibrary( CommonService.ECHOPOINT_SERVICE.getId() );
    serverMessage.addLibrary( CommonService.JQUERY_SERVICE.getId() );
  }
}
