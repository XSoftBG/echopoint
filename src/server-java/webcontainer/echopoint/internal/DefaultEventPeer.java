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
import nextapp.echo.webcontainer.AbstractComponentSynchronizePeer.EventPeer;

/**
 * A default event peer implementation since they all have the same
 * implementation.
 *
 * @author Rakesh Vidyadharan 2009-02-19
 * @version $Id: DefaultEventPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class DefaultEventPeer extends EventPeer
{
  /**
   * Create a new event peer for the specified event type and listener.
   *
   * @param eventType The name of the event, as serialized to the client
   * @param listenerPropertyName The name of the event property in the
   *   {@link nextapp.echo.app.Component}, i.e., the property name of the
   *   {@code PropertyChangeEvent} fired when listeners are added/removed
   */
  public DefaultEventPeer( final String eventType, final String listenerPropertyName )
  {
    super( eventType, listenerPropertyName );
  }

  /**
   * Create a new event peer for the specified event type and listener and
   * with the specified data class.
   *
   * @param eventType The name of the event, as serialized to the client
   * @param listenerPropertyName The name of the event property in the
   *   {@link nextapp.echo.app.Component}, i.e., the property name of the
   *   {@code PropertyChangeEvent} fired when listeners are added/removed
   * @param eventDataClass The Class type of the event data that will be
   *   received from the client (used to determine serialization peer to
   *   use for processing)
   */
  public DefaultEventPeer( final String eventType, final String listenerPropertyName,
      final Class eventDataClass )
  {
    super( eventType, listenerPropertyName, eventDataClass );
  }

  /** {@inheritDoc} */
  @Override
  public boolean hasListeners( Context context, Component component )
  {
    return ( (AbstractContainer) component ).hasActionListeners();
  }
}
