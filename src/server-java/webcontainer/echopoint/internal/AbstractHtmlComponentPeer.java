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
import nextapp.echo.app.update.ClientUpdateManager;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

/**
 * Rendering peer for the {@link echopoint.internal.AbstractHtmlComponent} class.
 *
 * @author Rakesh 2008-03-22
 * @version $Id: AbstractHtmlComponentPeer.java,v 1.3 2011/10/14 10:05:56 perxi Exp $
 */
public class AbstractHtmlComponentPeer extends AbstractContainerPeer
{
  /** The name of the component for which this class is a peer. */
  private static final String COMPONENT_NAME = AbstractHtmlComponent.class.getName();

  /** The JS service files to load. */
  private static final String[] SERVICE_FILES =
  {
    "resource/js/Application.HtmlComponent.js",
    "resource/js/Sync.AbstractHtmlComponent.js"
  };

  /** The service for the client side peer for this component. */
  private static final Service COMPONENT_SERVICE =
    JavaScriptService.forResources( COMPONENT_NAME, SERVICE_FILES );

  /** Register the services */
  static
  {
    WebContainerServlet.getServiceRegistry().add( COMPONENT_SERVICE );
  }

  public AbstractHtmlComponentPeer()
  {
    super();
    addOutputProperty(AbstractHtmlComponent.PROCESS_FORMS_PROPERTY);
    addOutputProperty(AbstractHtmlComponent.PROPERTY_TEXT);
    addEvent(new EventPeer(AbstractHtmlComponent.CONTENT_CHANGED_EVENT, AbstractHtmlComponent.CONTENT_CHANGED_LISTENERS_PROPERTY) {
      @Override
      public boolean hasListeners(Context context, Component c) {
          return ((AbstractHtmlComponent) c).hasContentChangedListeners();
      }
		});
  }

  /**
   * {@inheritDoc}
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#init
   */
  @Override
  public void init( final Context context, final  Component component )
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
    return AbstractHtmlComponent.class;
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
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#getInputPropertyClass(java.lang.String)
   */

  @Override
  public Class getInputPropertyClass(String propertyName)
  {
    if(AbstractHtmlComponent.PROPERTY_TEXT.equals(propertyName))
      return String.class;    
    return super.getInputPropertyClass(propertyName);
  }

  @Override
  public Object getOutputProperty(Context context, Component component, String propertyName, int propertyIndex)
  {
    if(propertyName.equals(AbstractHtmlComponent.PROPERTY_TEXT))
    {      
      return ((AbstractHtmlComponent)component).getText();
    }

    return super.getOutputProperty(context, component, propertyName, propertyIndex);
  }

  @Override
	public void storeInputProperty(Context context, Component component, String propertyName, int propertyIndex, Object newValue) {
		if( propertyName.equals(AbstractHtmlComponent.PROPERTY_TEXT)  )
    {
      ClientUpdateManager clientUpdateManager =  (ClientUpdateManager) context.get(ClientUpdateManager.class);
      clientUpdateManager.setComponentProperty(component, propertyName, newValue);
    }
		super.storeInputProperty(context, component, propertyName, propertyIndex, newValue);
	}
}
