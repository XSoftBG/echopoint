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

import nextapp.echo.app.ApplicationInstance;
import nextapp.echo.webcontainer.Connection;

import echopoint.tucana.event.UploadStartEvent;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.util.Set;

/**
 * {@link UploadSPI} implementation that uses the
 * <a href='http://jakarta.apache.org/commons/fileupload' target='_blank'>Jakarta
 * Commons FileUpload</a> library.
 *
 * @author Echo File Transfer Library
 * @version $Id: JakartaCommonsFileUploadProvider.java,v 1.2 2011-05-30 14:45:01 perxi Exp $
 */
public class JakartaCommonsFileUploadProvider extends AbstractFileUploadProvider
{
  /**
   * @see UploadSPI#handleUpload(nextapp.echo.webcontainer.Connection ,
   *      FileUploadSelector, String, UploadProgress)
   */
  @SuppressWarnings( { "ThrowableInstanceNeverThrown" } )
  public void handleUpload( final Connection conn,
      final FileUploadSelector uploadSelect, final String uploadIndex,
      final UploadProgress progress ) throws Exception
  {
    final ApplicationInstance app = conn.getUserInstance().getApplicationInstance();

    final DiskFileItemFactory itemFactory = new DiskFileItemFactory();
    itemFactory.setRepository( getDiskCacheLocation() );
    itemFactory.setSizeThreshold( getMemoryCacheThreshold() );

    String encoding = conn.getRequest().getCharacterEncoding();
    if ( encoding == null )
    {
      encoding = "UTF-8";
    }

    final ServletFileUpload upload = new ServletFileUpload( itemFactory );
    upload.setHeaderEncoding( encoding );
    upload.setProgressListener( new UploadProgressListener( progress ) );

    long sizeLimit = uploadSelect.getUploadSizeLimit();
    if ( sizeLimit == 0 ) sizeLimit = getFileUploadSizeLimit();
    if ( sizeLimit != NO_SIZE_LIMIT ) { upload.setSizeMax( sizeLimit ); }

    String fileName = null;
    String contentType = null;

    try
    {
      final FileItemIterator iter = upload.getItemIterator( conn.getRequest() );
      if ( iter.hasNext() )
      {
        final FileItemStream stream = iter.next();

        if ( !stream.isFormField() )
        {
          fileName = FilenameUtils.getName( stream.getName() );
          contentType = stream.getContentType();

          final Set<String> types = uploadSelect.getContentTypeFilter();
          if ( !types.isEmpty() )
          {
            if ( ! types.contains( contentType ) )
            {
              app.enqueueTask( uploadSelect.getTaskQueue(),
                  new InvalidContentTypeRunnable( uploadSelect, uploadIndex,
                      fileName, contentType, progress ) );
              return;
            }
          }

          progress.setStatus( Status.inprogress );
          final FileItem item = itemFactory.createItem( fileName,
              contentType, false, stream.getName() );
          item.getOutputStream(); // initialise DiskFileItem internals

          uploadSelect.notifyCallback( new UploadStartEvent( uploadSelect,
              uploadIndex, fileName, contentType, item.getSize() ) );

          IOUtils.copy( stream.openStream(), item.getOutputStream() );

          app.enqueueTask( uploadSelect.getTaskQueue(), new FinishRunnable(
              uploadSelect, uploadIndex, fileName, item, progress ) );

          return;
        }
      }

      app.enqueueTask( uploadSelect.getTaskQueue(), new FailRunnable(
          uploadSelect, uploadIndex, fileName, contentType,
          new RuntimeException( "No multi-part content!" ), progress ) );
    }
    catch ( final FileUploadBase.SizeLimitExceededException e )
    {
      app.enqueueTask( uploadSelect.getTaskQueue(), new FailRunnable(
          uploadSelect, uploadIndex, fileName, contentType,
          new UploadSizeLimitExceededException( e ), progress ) );
    }
    catch ( final FileUploadBase.FileSizeLimitExceededException e )
    {
      app.enqueueTask( uploadSelect.getTaskQueue(), new FailRunnable(
          uploadSelect, uploadIndex, fileName, contentType,
          new UploadSizeLimitExceededException( e ), progress ) );
    }
    catch ( final MultipartStream.MalformedStreamException e )
    {
      app.enqueueTask( uploadSelect.getTaskQueue(), new CancelRunnable(
          uploadSelect, uploadIndex, fileName, contentType, e, progress ) );
    }
  }
}
