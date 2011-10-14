package echopoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.OutputKeys;

import nextapp.echo.app.util.DomUtil;
import nextapp.echo.webcontainer.Connection;
import nextapp.echo.webcontainer.ContentType;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.SynchronizationException;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.WindowHtmlService;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import echopoint.model.AutoLookupModel;
import echopoint.model.AutoLookupModel.Entry;

/**
 * The service to look for matching entries in the auto lookup model.
 *
 * @author Christoff Spinner 2009-12-07
 * @version $Id: AutoLookupService.java,v 1.1.1.1 2010-04-01 09:47:32 perxi Exp $
 */
public class AutoLookupService implements Service
{
  public static final String SERVICE_ID = "echopoint.AutoLookupService";
  public static final AutoLookupService INSTANCE = new AutoLookupService();
  public static final Properties OUTPUT_PROPERTIES = new Properties();

  static
  {
    // The XML declaration is omitted as Internet Explorer 6 will operate in quirks mode if it is present.
    OUTPUT_PROPERTIES.setProperty( OutputKeys.OMIT_XML_DECLARATION, "yes" );
    OUTPUT_PROPERTIES.putAll( DomUtil.OUTPUT_PROPERTIES_INDENT );
    OUTPUT_PROPERTIES.setProperty( OutputKeys.DOCTYPE_PUBLIC, WindowHtmlService.XHTML_1_0_TRANSITIONAL_PUBLIC_ID );
    OUTPUT_PROPERTIES.setProperty( OutputKeys.DOCTYPE_SYSTEM, WindowHtmlService.XHTML_1_0_TRANSITIONAL_SYSTEM_ID );
  }

  /** Installs the service in the registry. */
  public static void install()
  {
    WebContainerServlet.getServiceRegistry().add( INSTANCE );
  }

  /** Don't instantiate externally */
  private AutoLookupService() {}

  /** Returns the service id. */
  public String getId()
  {
    return SERVICE_ID;
  }

  /** Returns the service version. */
  public int getVersion()
  {
    return DO_NOT_CACHE;
  }

  /**
   * Handles a service request.
   *
   * @param conn the connection.
   */
  public void service( final Connection conn ) throws IOException
  {
    final HttpServletRequest request = conn.getRequest();

    final String elementId = request.getParameter( "elementId" );
    final String searchValue = request.getParameter( "searchValue" );
    final AutoLookupTextField textFieldEx = (AutoLookupTextField) interestedParties.get( elementId );
    if ( textFieldEx == null )
    {
      throw new IllegalStateException( "The TextFieldEx " + elementId + " could not be found." );
    }
    final AutoLookupModel autoLookupModel = textFieldEx.getAutoLookupModel();
    if ( autoLookupModel == null )
    {
      return; // nothing to do
    }

    final Document doc = DomUtil.createDocument( "xml", null, null, null );
    final Element dataElement = doc.getDocumentElement();
    final Element autoLookupModelE = doc.createElement( "autoLookupModel" );

    final List<Entry> entries = autoLookupModel.searchEntries( searchValue );
    if ( entries != null )
    {
      for ( final Entry entry : entries )
      {
        final Element entryE = doc.createElement( "entry" );
        autoLookupModelE.appendChild( entryE );

        final Element valueE = doc.createElement( "value" );
        valueE.setTextContent( entry.getValue() );
        entryE.appendChild( valueE );

        final Element xhtmlE = doc.createElement( "xhtml" );
        xhtmlE.setTextContent( entry.getValue() );  //XXX
        entryE.appendChild( xhtmlE );
      }
    }
    dataElement.appendChild( autoLookupModelE );

    conn.setContentType( ContentType.TEXT_XML );
    try
    {
      DomUtil.save( doc, conn.getWriter(), OUTPUT_PROPERTIES );
    }
    catch ( SAXException ex )
    {
      throw new SynchronizationException( "Failed to write HTML document.", ex );
    }
  }


  /**
   * Returns an instance for public use.
   *
   * @return an instance for public use.
   */
  public static AutoLookupService getInstance()
  {
    return INSTANCE;
  }


  private final Map interestedParties = new HashMap();

  /**
   * Registers the <code>AutoLookupTextFieldEx</code> with the service
   *
   * @param textFieldEx - a <code>AutoLookupTextFieldEx</code> to be notified
   *                    of external events.
   */
  @SuppressWarnings( {"unchecked"} )
  public synchronized void register( final AutoLookupTextField textFieldEx )
  {
    interestedParties.put( "C." + textFieldEx.getRenderId(), textFieldEx );
  }

  /**
   * Deregisters the <code>AutoLookupTextFieldEx</code> with the service
   *
   * @param textFieldEx - an <code>AutoLookupTextFieldEx</code> to be removed
   *                    from being notified of external events.
   */
  public synchronized void deregister( final AutoLookupTextField textFieldEx )
  {
    interestedParties.remove( textFieldEx );
  }
}
