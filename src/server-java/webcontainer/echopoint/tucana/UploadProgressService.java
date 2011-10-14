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

import nextapp.echo.webcontainer.Connection;
import nextapp.echo.webcontainer.ContentType;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import echopoint.tucana.event.UploadProgressEvent;

/**
 * Provides information about file upload progress.
 *
 * @author Echo File Transfer Library
 * @version $Id: UploadProgressService.java,v 1.2 2011-05-30 14:45:01 perxi Exp $
 */
public class UploadProgressService extends BaseUploadService
{
  private static final Service INSTANCE = new UploadProgressService();

  static
  {
    WebContainerServlet.getServiceRegistry().add( INSTANCE );
  }

  private UploadProgressService()
  {
    super();
  }

  /** Installs this service. */
  public static void install()
  {
    // Do nothing, simply ensure static directives are executed.
  }

  /** @see nextapp.echo.webcontainer.Service#getId() */
  public String getId()
  {
    return getClass().getName();
  }

  /** @see nextapp.echo.webcontainer.Service#getVersion() */
  public int getVersion()
  {
    return DO_NOT_CACHE;
  }

  /** {@inheritDoc} */
  public void service( final Connection conn,
      final FileUploadSelector uploadSelect, final String uploadIndex )
    throws IOException
  {
    final UploadRenderState renderState = FileUploadSelectorPeer.getRenderState(
        uploadSelect, conn.getUserInstance() );
    final UploadProgress progress = renderState.getProgress( uploadIndex );

    final StringBuilder buff = new StringBuilder( 128 );
    if ( progress != null && progress.getBytesRead() > 0 )
    {
      if ( !renderState.isUploadEnded( uploadIndex ) )
      {
        uploadSelect.notifyCallback( new UploadProgressEvent(
            uploadSelect, uploadIndex, progress.getContentLength(), progress ) );
      }

      buff.append( "{" );
      buff.append( "r:" ).append( progress.getBytesRead() ).append( "," );
      buff.append( "cl:" ).append( progress.getContentLength() ).append( "," );
      buff.append( "pc:" ).append( progress.getPercentCompleted() ).append( "," );
      buff.append( "tr:" ).append( progress.getTransferRate() ).append( "," );
      buff.append( "tl:" ).append( progress.getEstimatedTimeLeft() ).append( "," );
      buff.append( "s:'" ).append( progress.getStatus() ).append( "'," );
      buff.append( "m:'" ).append( progress.getMessage() ).append( "'" );
      buff.append( "}" );
    }
    else
    {
      buff.append( "{r:0,cl:0,pc:0,tr:0,tl:0,s:'failed',m:''}" );
    }

    conn.setContentType( ContentType.TEXT_PLAIN );
    conn.getWriter().write( buff.toString() );
    conn.getWriter().flush();
  }
}
