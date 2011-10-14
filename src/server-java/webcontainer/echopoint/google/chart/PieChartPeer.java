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

package echopoint.google.chart;

import java.util.Collection;

import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;
import echopoint.google.chart.internal.SimpleChartPeer;

/**
 * Rendering peer for the {@link echopoint.google.chart.PieChart} component.
 *
 * @author Rakesh 2008-08-21
 * @version $Id: PieChartPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class PieChartPeer extends SimpleChartPeer
{
  /** The component name for which this class is a peer. */
  private static final String COMPONENT_NAME = PieChart.class.getName();

  /** The service for the client side peer for this component. */
  private static final Service COMPONENT_SERVICE =
      JavaScriptService.forResource( PieChartPeer.COMPONENT_NAME,
          "resource/js/google/chart/Sync.PieChart.js" );

  /** Register the services */
  static
  {
    WebContainerServlet.getServiceRegistry().add( COMPONENT_SERVICE );
  }

  /**
   * {@inheritDoc}
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
   */
  @Override
  public Class getComponentClass()
  {
    return PieChart.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getClientComponentType( final boolean shortType )
  {
    return COMPONENT_NAME;
  }

  /**
   * Over-ridden to handle requests for the {@link
   * echopoint.google.chart.PieChart#PROPERTY_DIMENSIONS} and {@link
   * echopoint.google.chart.PieChart#PROPERTY_LABELS} properties.
   *
   * @see nextapp.echo.webcontainer.ComponentSynchronizePeer#getOutputProperty(
   *   Context, Component, String, int)
   */
  @Override
  @SuppressWarnings( {"unchecked"} )
  public Object getOutputProperty( final Context context,
      final Component component, final String propertyName,
      final int propertyIndex )
  {
    if ( PieChart.PROPERTY_DIMENSIONS.equals( propertyName ) )
    {
      return component.get( propertyName ).toString();
    }
    else if ( PieChart.PROPERTY_LABELS.equals( propertyName ) )
    {
      final StringBuilder builder = new StringBuilder( 128 );
      final Collection<String> labels =
          (Collection<String>) component.get( propertyName );

      boolean first = true;
      for ( String label : labels )
      {
        if ( ! first ) builder.append( "|" );
        first = false;
        builder.append( label );
      }

      return builder.toString();
    }

    return super.getOutputProperty(
        context, component, propertyName, propertyIndex );
  }
}