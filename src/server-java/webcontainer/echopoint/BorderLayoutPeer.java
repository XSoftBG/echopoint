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

import java.util.LinkedHashMap;

import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import echopoint.internal.AbstractContainerPeer;

/**
 * Component rendering peer for {@link echopoint.BorderLayout}.
 *
 * @author Rakesh Vidyadharan 2009-04-02
 * @version $Id: BorderLayoutPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class BorderLayoutPeer extends AbstractContainerPeer
{
  /** The component name for which this class is a peer. */
  private static final String COMPONENT_NAME = BorderLayout.class.getName();

  /** The property name for the region to child index mapping. */
  private static final String REGION_TO_INDEX = "regionToIndex";

  /** The property name for the child index to region mapping. */
  private static final String INDEX_TO_REGION = "indexToRegion";

  /** The property name to apply to the

  /** The serialiser used to serialise {@link echopoint.model.Tag} instances. */
  protected static final XStream xstream;

  /** The JS service files to load. */
  private static final String[] SERVICE_FILES =
      {
          "resource/js/Application.BorderLayout.js",
          "resource/js/Sync.BorderLayout.js"
      };

  /** The service for the client side peer for this component. */
  private static final Service COMPONENT_SERVICE =
      JavaScriptService.forResources( COMPONENT_NAME, SERVICE_FILES );

  /** Register the services and resources. */
  static
  {
    WebContainerServlet.getServiceRegistry().add( COMPONENT_SERVICE );

    xstream = new XStream( new JsonHierarchicalStreamDriver() );
    xstream.alias( "map", LinkedHashMap.class );
    xstream.setMode( XStream.NO_REFERENCES );
  }

  /**
   * Default constructor.  Adds output properties for {@link #REGION_TO_INDEX}
   * and {@link #INDEX_TO_REGION}.
   */
  public BorderLayoutPeer()
  {
    addOutputProperty( REGION_TO_INDEX );
    addOutputProperty( INDEX_TO_REGION );
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
   * #REGION_TO_INDEX} and {@link #INDEX_TO_REGION} properties.  The map
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
    if ( REGION_TO_INDEX.equals( propertyName ) )
    {
      return xstream.toXML( ( (BorderLayout) component ).getRegionToIndex() );
    }

    if ( INDEX_TO_REGION.equals( propertyName ) )
    {
      return xstream.toXML( ( (BorderLayout) component ).getIndexToRegion() );
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
    return BorderLayout.class;
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
