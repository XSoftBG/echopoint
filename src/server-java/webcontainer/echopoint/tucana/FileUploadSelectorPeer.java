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

import static echopoint.internal.AbstractContainer.ACTION_LISTENERS_CHANGED_PROPERTY;
import static echopoint.tucana.FileUploadSelector.COMPLETE_ACTION;
import static echopoint.tucana.FileUploadSelector.START_ACTION;
import static nextapp.echo.webcontainer.WebContainerServlet.getResourceRegistry;
import static nextapp.echo.webcontainer.WebContainerServlet.getServiceRegistry;
import static nextapp.echo.webcontainer.service.JavaScriptService.forResources;

import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ContentType;
import nextapp.echo.webcontainer.ResourceRegistry;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.UserInstance;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;
import echopoint.ProgressBar;
import echopoint.internal.AbstractContainerPeer;
import echopoint.internal.DefaultEventPeer;

/**
 * Rendering peer for the {@link echopoint.tucana.FileUploadSelector}
 * component.
 *
 * @author Rakesh Vidyadharan 2008-11-3
 * @version $Id: FileUploadSelectorPeer.java,v 1.2 2011-05-30 14:45:01 perxi Exp $
 */
public class FileUploadSelectorPeer extends AbstractContainerPeer
{
  /** The name of the component for which this class is a peer. */
  private static final String COMPONENT_NAME = FileUploadSelector.class.getName();

  /** The JS service files to load. */
  private static final String[] SERVICE_FILES =
      {
          "resource/js/tucana/Application.FileUploadSelector.js",
          "resource/js/tucana/Sync.FileUploadSelector.js"
      };

  /** The service for the client side peer for this component. */
  private static final Service COMPONENT_SERVICE =
      forResources( COMPONENT_NAME, SERVICE_FILES );

  /** Register the services */
  static
  {
    UploadProgressService.install();
    getServiceRegistry().add( COMPONENT_SERVICE );
    final ResourceRegistry resources = getResourceRegistry();
    resources.add( "echopoint", "images/upload.png", ContentType.IMAGE_PNG );
    resources.add( "echopoint", "images/cancel.png", ContentType.IMAGE_PNG );
    resources.add( "echopoint", "images/wait.png", ContentType.IMAGE_PNG );
  }

  /** Default constructor.  Register events and required componens . */
  public FileUploadSelectorPeer()
  {
    addRequiredComponentClass( ProgressBar.class );
    addEvent( new DefaultEventPeer(
        START_ACTION, ACTION_LISTENERS_CHANGED_PROPERTY ) );
    addEvent( new DefaultEventPeer(
        COMPLETE_ACTION, ACTION_LISTENERS_CHANGED_PROPERTY ) );
  }

  /** {@inheritDoc} */
  @Override
  public void init( final Context context, final Component component )
  {
    super.init( context, component );
    UploadReceiverService.install();
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
    return FileUploadSelector.class;
  }

  /**
   * {@inheritDoc}
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#getClientComponentType
   */
  public String getClientComponentType( final boolean shortType )
  {
    return COMPONENT_NAME;
  }

  /**
   * Over-ridden to handle requests for the <code>uploadIndex</code> property.
   *
   * @see nextapp.echo.webcontainer.ComponentSynchronizePeer#getOutputProperty(Context,
   *      Component, String, int)
   */
  @Override
  public Object getOutputProperty( final Context context,
      final Component component, final String propertyName, final int propertyIndex )
  {
    if ( FileUploadSelector.PROPERTY_BUTTON_DISPLAY.equals( propertyName ) ||
        FileUploadSelector.PROPERTY_BUTTON_MODE.equals( propertyName ) )
    {
      return component.get( propertyName ).toString();
    }

    return super.getOutputProperty( context, component, propertyName, propertyIndex );
  }

  /**
   * Gets the render state for the given component. Synchronization is handled
   * internally.
   *
   * @param uploadSelect The file upload component instance.
   * @param userInstance The user instance for the session.
   * @return the render state, never <code>null</code>.
   */
  protected static UploadRenderState getRenderState(
      final FileUploadSelector uploadSelect, final UserInstance userInstance )
  {
    UploadRenderState renderState;
    synchronized ( uploadSelect )
    {
      renderState = (UploadRenderState) userInstance.getRenderState( uploadSelect );
      if ( renderState == null )
      {
        renderState = new UploadRenderState();
        userInstance.setRenderState( uploadSelect, renderState );
      }
    }
    return renderState;
  }
}
