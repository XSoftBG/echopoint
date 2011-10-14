package echopoint;

import static nextapp.echo.webcontainer.WebContainerServlet.getServiceRegistry;
import static nextapp.echo.webcontainer.service.JavaScriptService.forResource;
import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import echopoint.internal.TextFieldPeer;

/**
 * <p>&copy; Copyright 2009 <a href='http://sptci.com/' target='_top'>Sans
 * Pareil Technologies, Inc.</a></p>
 *
 * @author Rakesh Vidyadharan 2009-03-07
 * @version $Id: RegexTextFieldPeer.java,v 1.8 2010-11-24 13:29:59 ivan Exp $
 */
public class RegexTextFieldPeer extends TextFieldPeer
{
  /** The name of the component for which this class is a peer. */
  private static final String COMPONENT_NAME = RegexTextField.class.getName();

  /** The service for the client side peer for this component. */
  private static final Service COMPONENT_SERVICE = forResource( COMPONENT_NAME,
      "resource/js/Sync.RegexTextField.js" );

  /** Register the services */
  static
  {
    getServiceRegistry().add( COMPONENT_SERVICE );
  }

  public RegexTextFieldPeer()
  {
    super();
    addOutputProperty(RegexTextField.PROPERTY_REGEX);
    addOutputProperty(RegexTextField.PROPERTY_INVALID_MSG_ORIENTATION);
    addOutputProperty(RegexTextField.PROPERTY_INVALID_MSG);
    addOutputProperty(RegexTextField.PROPERTY_INVALID_MSG_BACKGROUND);
    addOutputProperty(RegexTextField.PROPERTY_INVALID_MSG_FOREGROUND);
    addOutputProperty(RegexTextField.PROPERTY_INVALID_BACKGROUND);
    addOutputProperty(RegexTextField.PROPERTY_INVALID_FOREGROUND);
    addOutputProperty(RegexTextField.PROPERTY_INVALID_MSG_WIDTH);
    addOutputProperty(RegexTextField.PROPERTY_INVALID_MSG_ALIGNMENT);
    addOutputProperty(RegexTextField.PROPERTY_ISVALID);
    addOutputProperty(RegexTextField.PROPERTY_FILTER);

		addEvent(new EventPeer(RegexTextField.VALID_VALUE_EVENT, RegexTextField.VALIDATION_LISTENERS_CHANGED_PROPERTY) {
				public boolean hasListeners(Context context, Component c) {
						return ((RegexTextField) c).hasValidationListeners();
				}
		});
		addEvent(new EventPeer(RegexTextField.INVALID_VALUE_EVENT, RegexTextField.VALIDATION_LISTENERS_CHANGED_PROPERTY) {
				public boolean hasListeners(Context context, Component c) {
						return ((RegexTextField) c).hasValidationListeners();
				}
		});
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
    return RegexTextField.class;
  }

  /**
   * {@inheritDoc}
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#getClientComponentType
   */
  public String getClientComponentType( final boolean shortType )
  {
    return shortType ? "RxRTF" : COMPONENT_NAME;
  }

  @Override
  public Object getOutputProperty(Context context, Component component, String propertyName, int propertyIndex) 
  {
    if( propertyName.equals(RegexTextField.PROPERTY_REGEX) ) 
      return ((RegexTextField)component).getRegex();
    else if( propertyName.equals(RegexTextField.PROPERTY_INVALID_MSG_ORIENTATION) ) 
      return ((RegexTextField)component).getInvalidMsgOrientation();
    else if( propertyName.equals(RegexTextField.PROPERTY_INVALID_MSG) ) 
      return ((RegexTextField)component).getInvalidMsg();
    else if( propertyName.equals(RegexTextField.PROPERTY_INVALID_MSG_BACKGROUND) ) 
      return ((RegexTextField)component).getInvalidMsgBackground();
    else if( propertyName.equals(RegexTextField.PROPERTY_INVALID_MSG_FOREGROUND) ) 
      return ((RegexTextField)component).getInvalidMsgForeground();
    else if( propertyName.equals(RegexTextField.PROPERTY_INVALID_BACKGROUND) ) 
      return ((RegexTextField)component).getInvalidBackground();
    else if( propertyName.equals(RegexTextField.PROPERTY_INVALID_FOREGROUND) ) 
      return ((RegexTextField)component).gettInvalidForeground();
    else if( propertyName.equals(RegexTextField.PROPERTY_INVALID_MSG_WIDTH) ) 
      return ((RegexTextField)component).getInvalidMsgWidth();
    else if( propertyName.equals(RegexTextField.PROPERTY_INVALID_MSG_ALIGNMENT) ) 
      return ((RegexTextField)component).getInvalidMsgAlignment();
    else if( propertyName.equals(RegexTextField.PROPERTY_INVALID_MSG_FONT) ) 
      return ((RegexTextField)component).getInvalidMsgFont();
    else if( propertyName.equals(RegexTextField.PROPERTY_ISVALID) ) 
      return ((RegexTextField)component).isValid();
    else if( propertyName.equals(RegexTextField.PROPERTY_FILTER) ) 
      return ((RegexTextField)component).getFilter();
    return super.getOutputProperty(context, component, propertyName, propertyIndex);
  }
}
