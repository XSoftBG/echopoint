package echopoint.tucana;

import static nextapp.echo.webcontainer.ClientProperties.BROWSER_INTERNET_EXPLORER;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import nextapp.echo.webcontainer.Connection;
import nextapp.echo.webcontainer.ContentType;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.UserInstance;
import nextapp.echo.webcontainer.WebContainerServlet;
import echopoint.tucana.event.DownloadFailEvent;
import echopoint.tucana.event.DownloadFinishEvent;
import echopoint.tucana.event.DownloadStartEvent;

/**
 * Services requests to download files.
 *
 * @author Echo File Transfer Library
 * @version $Id: DownloadService.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class DownloadService implements Service
{
  static final String SERVICE_ID = "echopoint.tucana.DownloadService";

  private static final String HTTPS = "https";

  private static final String PARAMETER_DOWNLOAD_UID = "duid";

  private static final String[] URL_PARAMETERS = new String[]{ PARAMETER_DOWNLOAD_UID };

  private static final DownloadService INSTANCE = new DownloadService();

  /** Installs the service in the registry. */
  public static void install()
  {
    WebContainerServlet.getServiceRegistry().add( INSTANCE );
  }

  /** Don't instantiate externally */
  private DownloadService() {}

  /**
   * Creates a URI from which to download the file.
   *
   * @param userInstance the user instance of the user downloading.
   * @param downloadId the id of the download command.
   * @return the download URI.
   */
  public String createUri( UserInstance userInstance, String downloadId )
  {
    return userInstance.getServiceUri(
        this, URL_PARAMETERS, new String[]{ downloadId } );
  }

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
  public void service( Connection conn ) throws IOException
  {
    final UserInstance userInstance = conn.getUserInstance();
    if ( userInstance == null )
    {
      serviceBadRequest( conn, "No container available." );
      return;
    }

    final String downloadId =
        conn.getRequest().getParameter( PARAMETER_DOWNLOAD_UID );
    if ( downloadId == null )
    {
      serviceBadRequest( conn, "Download UID not specified." );
      return;
    }

    final DownloadCommand download =
        DownloadCommandPeer.getAndRemoveDownload( downloadId );
    if ( download == null )
    {
      serviceBadRequest( conn, "Download UID is not valid." );
      return;
    }

    service( conn, download );
  }

  /**
   * Internal processing to handle the download request.
   *
   * @param conn the connection.
   * @param download the download command.
   * @throws IOException If errors are encountered while writing to the output
   * stream of the connection.
   */
  private void service( Connection conn, DownloadCommand download )
      throws IOException
  {
    final OutputStream out = conn.getOutputStream();
    final DownloadProvider provider = download.getProvider();
    final HttpServletResponse response = conn.getResponse();

    if ( provider.getFileName() == null )
    {
      response.setHeader(
          "Content-Disposition", provider.getContentDisposition() );
    }
    else
    {
      response.setHeader( "Content-Disposition",
          provider.getContentDisposition() +
              "; filename=\"" + provider.getFileName() + "\"" );
    }

    if ( provider.getSize() > 0 )
    {
      response.setHeader( "Content-Length", String.valueOf( provider.getSize() ) );
    }

    final String contentType = provider.getContentType();
    if ( contentType == null )
    {
      response.setContentType( "application/octet-stream" );
    }
    else
    {
      response.setContentType( provider.getContentType() );
    }

    try
    {
      download.notifyCallback( new DownloadStartEvent( download, provider ) );

      // Fix for IE and https connections
      if ( HTTPS.equals( conn.getRequest().getScheme() ) &&
        conn.getUserInstance().getClientProperties().getBoolean( BROWSER_INTERNET_EXPLORER ) )
      {
        response.setHeader( "Cache-Control", "" );
        response.setHeader( "Pragma", "" );
      }

      provider.writeFile( out );
      download.notifyCallback( new DownloadFinishEvent( download, provider ) );
    }
    catch ( Exception e )
    {
      download.notifyCallback( new DownloadFailEvent( download, provider, e ) );
    }
  }

  /**
   * Sets the response status indicating that a bad request was made to this
   * service.
   *
   * @param conn the connection.
   * @param message the error message.
   */
  private void serviceBadRequest( Connection conn, String message )
  {
    conn.getResponse().setStatus( HttpServletResponse.SC_BAD_REQUEST );
    conn.setContentType( ContentType.TEXT_PLAIN );
    conn.getWriter().write( message );
  }

  /**
   * Returns an instance for public use.
   *
   * @return an instance for public use.
   */
  public static DownloadService getInstance()
  {
    return INSTANCE;
  }
}
