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

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import nextapp.echo.app.ApplicationInstance;
import nextapp.echo.webcontainer.Connection;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import echopoint.tucana.event.UploadCallback;
import echopoint.tucana.event.UploadCancelEvent;
import echopoint.tucana.event.UploadFailEvent;

/**
 * Processes a file upload HTTP request from the client.
 *
 * @author Echo File Transfer Library
 * @version $Id: UploadReceiverService.java,v 1.2 2011-05-30 14:45:01 perxi Exp $
 */
public class UploadReceiverService extends BaseUploadService
{
  private static final Service INSTANCE = new UploadReceiverService();

  /** A flag used to track whether multi-part wrapper has been set. */
  private static boolean installed = false;

  static
  {
    WebContainerServlet.getServiceRegistry().add( INSTANCE );
  }

  private UploadReceiverService() {}

  /** Installs this service if not already installed. */
  public static void install()
  {
    if ( ! installed )
    {
      installed = true;
      WebContainerServlet.setMultipartRequestWrapper(
          UploadProviderFactory.getUploadProvider() );
    }
  }

  /** @see nextapp.echo.webcontainer.Service#getId() */
  public String getId()
  {
    return "echopoint.tucana.UploadReceiverService";
  }

  /** @see nextapp.echo.webcontainer.Service#getVersion() */
  public int getVersion()
  {
    return DO_NOT_CACHE;
  }

  /**
   * @see BaseUploadService#service(nextapp.echo.webcontainer.Connection ,
   *      FileUploadSelector, String )
   */
  public void service( final Connection conn,
      final FileUploadSelector uploadSelect, final String uploadIndex )
    throws IOException
  {
    final HttpServletRequest request = conn.getRequest();
    final ApplicationInstance app = conn.getUserInstance().getApplicationInstance();
    if ( !ServletFileUpload.isMultipartContent( request ) )
    {
      serviceBadRequest( conn, "Request must contain multipart content." );
      return;
    }

    final String contentLengthHeader = request.getHeader( "Content-Length" );
    final long contentLength;
    if ( contentLengthHeader != null )
    {
      contentLength = Math.max( Long.parseLong( contentLengthHeader ), -1 );
    }
    else
    {
      contentLength = -1;
    }

    final UploadRenderState renderState =
        FileUploadSelectorPeer.getRenderState( uploadSelect,
            conn.getUserInstance() );

    final UploadCallback callback = uploadSelect.getUploadCallback();
    final String fileName = ( ( callback != null ) && ( callback.getEvent() != null ) ) ?
        callback.getEvent().getFileName() : null;
    try
    {
      final UploadProgress progress = new UploadProgress( contentLength );
      renderState.setProgress( uploadIndex, progress );
      renderState.uploadStarted( uploadIndex );

      UploadProviderFactory.getUploadProvider().handleUpload(
          conn, uploadSelect, uploadIndex, progress );
    }
    catch ( IllegalStateException e )
    {
      app.enqueueTask( uploadSelect.getTaskQueue(),
          new Cancel( uploadSelect, uploadIndex, fileName, contentLength, e ) );
    }
    catch ( Exception e )
    {
      app.enqueueTask( uploadSelect.getTaskQueue(),
          new Fail( uploadSelect, uploadIndex, contentLength, e ) );
    }
    finally
    {
      renderState.uploadEnded( uploadIndex );
    }
  }

  /**
   * A runnable used to enque a cancel event to the call back handler.
   */
  private class Cancel implements Runnable
  {
    private final FileUploadSelector uploadSelect;
    private final String uploadIndex;
    private final String fileName;
    private final long contentLength;
    private final Exception e;

    private Cancel( final FileUploadSelector uploadSelect,
        final String uploadIndex, final String fileName,
        final long contentLength,
        final Exception e )
    {
      this.uploadSelect = uploadSelect;
      this.uploadIndex = uploadIndex;
      this.fileName = fileName;
      this.contentLength = contentLength;
      this.e = e;
    }

    public void run()
    {
      uploadSelect.notifyCallback( new UploadCancelEvent( uploadSelect,
          uploadIndex, fileName, null, contentLength, e ) );
    }
  }

  /**
   * A runnable used to enqueue a fail event to the call back handlers.
   */
  private class Fail implements Runnable
  {
    private final FileUploadSelector uploadSelect;
    private final String uploadIndex;
    private final long contentLength;
    private final Exception e;

    private Fail( final FileUploadSelector uploadSelect,
        final String uploadIndex, final long contentLength, final Exception e )
    {
      this.uploadSelect = uploadSelect;
      this.uploadIndex = uploadIndex;
      this.contentLength = contentLength;
      this.e = e;
    }

    public void run()
    {
      uploadSelect.notifyCallback(
          new UploadFailEvent( uploadSelect, uploadIndex, null, null, contentLength, e ) );
    }
  }
}
