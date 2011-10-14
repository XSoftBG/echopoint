package echopoint.jquery;

import static echopoint.internal.AbstractContainer.ACTION_LISTENERS_CHANGED_PROPERTY;
import static nextapp.echo.webcontainer.WebContainerServlet.getServiceRegistry;
import nextapp.echo.app.Component;
import nextapp.echo.app.update.ClientUpdateManager;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.AbstractComponentSynchronizePeer;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.service.JavaScriptService;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

/**
 * Rendering peer for the {@link DockMenu} component.
 *
 * @author Mikael S\u00f6derman 2009-06-03
 * @version $Id: DockMenuPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class DockMenuPeer extends JQueryAbstractPeer
{
  /** The component name for which this class is a peer. */
  private static final String COMPONENT_NAME = DockMenu.class.getName();

    protected static final XStream xstream;

  /** The JS service files to load. */
  private static final String[] SERVICE_FILES =
      {
          "resource/js/jquery/jquery.jqDock.js",
          "resource/js/jquery/jquery-CallbackContext.js",
          "resource/js/jquery/Application.DockMenu.js",
          "resource/js/jquery/Sync.DockMenu.js"
      };

  /** The service for the client side peer for this component. */
  private static final Service COMPONENT_SERVICE =
      JavaScriptService.forResources( COMPONENT_NAME, SERVICE_FILES );

  /** Register the services and resources. */
  static
  {
    getServiceRegistry().add( COMPONENT_SERVICE );

        xstream = new XStream( new JsonHierarchicalStreamDriver() );
        xstream.setMode( XStream.NO_REFERENCES );
  }

    public DockMenuPeer() {
        super();
        addOutputProperty(DockMenu.MODEL_CHANGED_PROPERTY);

        addEvent(new AbstractComponentSynchronizePeer.EventPeer("action", ACTION_LISTENERS_CHANGED_PROPERTY, String.class) {
            public boolean hasListeners(Context context, Component component) {
                return ((DockMenu) component).hasActionListeners();
            }
        });

        //addEvent( new DefaultEventPeer(
        //INPUT_ACTION, ACTION_LISTENERS_CHANGED_PROPERTY) );
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
    return DockMenu.class;
  }

  /**
   * {@inheritDoc}
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#getClientComponentType
   */
  public String getClientComponentType( final boolean shortType )
  {
    return COMPONENT_NAME;
  }

    public Object getOutputProperty(Context context, Component component, String propertyName, int propertyIndex) {
        DockMenu menu = (DockMenu)component;
        if (DockMenu.MODEL_CHANGED_PROPERTY.equals(propertyName)) {
            return xstream.toXML(menu.getModel());
        }
        return super.getOutputProperty(context, component, propertyName, propertyIndex);
    }

    public Class getInputPropertyClass(String propertyName) {
           if (DockMenu.BUTTON_PRESSED_PROPERTY.equals(propertyName)) {
               return String.class;
           }
            return super.getInputPropertyClass( propertyName );
       }

       public void storeInputProperty(Context context, Component component,
               String propertyName, int propertyIndex, Object newValue) {
           if (propertyName.equals(DockMenu.BUTTON_PRESSED_PROPERTY)) {
               ClientUpdateManager clientUpdateManager =
                       (ClientUpdateManager) context.get(ClientUpdateManager.class);
               clientUpdateManager.setComponentProperty(component,
                       DockMenu.BUTTON_PRESSED_PROPERTY, newValue);
           }
       }

}
