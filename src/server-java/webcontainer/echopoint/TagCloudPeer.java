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

package echopoint;

import static echopoint.internal.AbstractContainer.ACTION_LISTENERS_CHANGED_PROPERTY;
import static echopoint.internal.AbstractContainer.INPUT_ACTION;
import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import echopoint.internal.AbstractContainerPeer;
import echopoint.internal.DefaultEventPeer;
import echopoint.model.Tag;

/**
 * Component rendering peer for {@link echopoint.TagCloud}.
 *
 * @author Rakesh 2008-07-20
 * @version $Id: TagCloudPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class TagCloudPeer extends AbstractContainerPeer
{
  /** The component name for which this class is a peer. */
  private static final String COMPONENT_NAME = TagCloud.class.getName();

  /** The serialiser used to serialise {@link echopoint.model.Tag} instances. */
  protected static final XStream xstream;

  /** The JS service files to load. */
  private static final String[] SERVICE_FILES =
      {
          "resource/js/Application.TagCloud.js",
          "resource/js/Sync.TagCloud.js"
      };

  /** The service for the client side peer for this component. */
  private static final Service COMPONENT_SERVICE =
      JavaScriptService.forResources( COMPONENT_NAME, SERVICE_FILES );

  /** Register the services and resources. */
  static
  {
    WebContainerServlet.getServiceRegistry().add( COMPONENT_SERVICE );

    xstream = new XStream( new JsonHierarchicalStreamDriver() );
    xstream.processAnnotations( Tag.class );
    xstream.setMode( XStream.NO_REFERENCES );
  }

  /** Register an event peer for client events. */
  public TagCloudPeer()
  {
    addEvent( new DefaultEventPeer(
        INPUT_ACTION, ACTION_LISTENERS_CHANGED_PROPERTY, String.class ) );
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
    serverMessage.addLibrary( COMPONENT_NAME );
  }

  /**
   * Over-ridden to handle requests for the {@link
   * echopoint.TagCloud#PROPERTY_TAGS} property.  The collection of tag
   * instances are serialised as a JSON stucture.
   *
   * @see nextapp.echo.webcontainer.ComponentSynchronizePeer#getOutputProperty(Context,
   *      Component, String, int)
   */
  @Override
  public Object getOutputProperty( final Context context,
      final Component component, final String propertyName,
      final int propertyIndex )
  {
    if ( TagCloud.PROPERTY_TAGS.equals( propertyName ) )
    {
      return xstream.toXML( ( (TagCloud) component ).getData() );
    }

    return super.getOutputProperty(
        context, component, propertyName, propertyIndex );
  }

  /**
   * {@inheritDoc}
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#getComponentClass
   */
  public Class getComponentClass()
  {
    return TagCloud.class;
  }

  /**
   * {@inheritDoc}
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#getClientComponentType
   */
  public String getClientComponentType( final boolean shortType )
  {
    return COMPONENT_NAME;
  }
}
