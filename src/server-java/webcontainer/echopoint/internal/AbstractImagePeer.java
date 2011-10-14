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

import static echopoint.internal.AbstractContainer.ACTION_LISTENERS_CHANGED_PROPERTY;
import static echopoint.internal.AbstractContainer.INPUT_ACTION;
import static nextapp.echo.webcontainer.WebContainerServlet.getServiceRegistry;
import static nextapp.echo.webcontainer.service.JavaScriptService.forResources;
import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;

/**
 * Component rendering peer for the {@link echopoint.internal.AbstractImage} component.
 *
 * @author Rakesh Vidyadharan 2009-12-18
 * @version $Id: AbstractImagePeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class AbstractImagePeer extends AbstractContainerPeer
{
  /** The component name for which this class is a peer. */
  private static final String COMPONENT_NAME = AbstractImage.class.getName();

  /** The JS service files to load. */
  private static final String[] SERVICE_FILES =
      {
          "resource/js/Application.AbstractImage.js",
          "resource/js/Sync.AbstractImage.js"
      };

  /** The service for the client side peer for this component. */
  private static final Service COMPONENT_SERVICE =
      forResources( COMPONENT_NAME, SERVICE_FILES );

  /** Register the services and resources. */
  static
  {
    getServiceRegistry().add( COMPONENT_SERVICE );
  }

  public AbstractImagePeer()
  {
    addOutputProperty(AbstractImage.PROPERTY_IMAGE);
    addEvent( new DefaultEventPeer(
        INPUT_ACTION, ACTION_LISTENERS_CHANGED_PROPERTY ) );
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
    return AbstractImage.class;
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
   * Over-ridden to handle requests for the {@link echopoint.model.Cursor}
   * property associated with the image.
   * 
   * {@inheritDoc}
   */
  @Override
  public Object getOutputProperty( final Context context,
      final Component component, final String propertyName, final int propertyIndex )
  {
    if( AbstractImage.PROPERTY_CURSOR.equals( propertyName ) )
      return component.get( propertyName ).toString();
    else
    if( AbstractImage.PROPERTY_IMAGE.equals( propertyName ) )
      return ((AbstractImage)component).getImage();
    else  
      return super.getOutputProperty( context, component, propertyName, propertyIndex );
  }
}