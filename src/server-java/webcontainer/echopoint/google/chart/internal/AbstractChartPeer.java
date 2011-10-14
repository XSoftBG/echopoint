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

package echopoint.google.chart.internal;

import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import echopoint.google.chart.model.Title;
import echopoint.internal.AbstractContainerPeer;

/**
 * Rendering peer for the {@link echopoint.google.chart.internal.AbstractChart}
 * component.
 *
 * @author Rakesh 2008-08-10
 * @version $Id: AbstractChartPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class AbstractChartPeer extends AbstractContainerPeer
{
  /** The name of the component for which this class is a peer. */
  private static final String COMPONENT_NAME = AbstractChart.class.getName();

  /** The serialiser for converting custom model objects to JSON. */
  protected static final XStream xstream;

  /** The JS service files to load. */
  private static final String[] SERVICE_FILES =
      {
          "resource/js/google/chart/Model.Google.js",
          "resource/js/google/chart/Application.Chart.js",
          "resource/js/google/chart/Sync.AbstractChart.js"
      };

  /** The service for the client side peer for this component. */
  private static final Service COMPONENT_SERVICE =
      JavaScriptService.forResources( COMPONENT_NAME, SERVICE_FILES );

  /** Register the services */
  static
  {
    WebContainerServlet.getServiceRegistry().add( COMPONENT_SERVICE );

    xstream = new XStream( new JsonHierarchicalStreamDriver() );
    xstream.processAnnotations( Title.class );
    xstream.setMode( XStream.NO_REFERENCES );
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
   * {@inheritDoc}
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#getComponentClass
   */
  @Override
  public Class getComponentClass()
  {
    return AbstractChart.class;
  }

  /**
   * {@inheritDoc}
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#getClientComponentType
   */
  @Override
  public String getClientComponentType( final boolean shortType )
  {
    return COMPONENT_NAME;
  }

  /**
   * Over-ridden to handle requests for the {@link
   * echopoint.google.chart.internal.AbstractChart#PROPERTY_TITLE} and {@link
   * echopoint.google.chart.internal.AbstractChart#PROPERTY_DATA} properties.
   *
   * @see nextapp.echo.webcontainer.ComponentSynchronizePeer#getOutputProperty(
   *   Context, Component, String, int)
   */
  @Override
  public Object getOutputProperty( final Context context,
      final Component component, final String propertyName,
      final int propertyIndex )
  {
    if ( AbstractChart.PROPERTY_DATA.equals( propertyName ) ||
        AbstractChart.PROPERTY_TITLE.equals( propertyName ) )
    {
      return xstream.toXML( component.get( propertyName ) );
    }

    return super.getOutputProperty(
        context, component, propertyName, propertyIndex );
  }
}
