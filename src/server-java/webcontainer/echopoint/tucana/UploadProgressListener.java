package echopoint.tucana;

import org.apache.commons.fileupload.ProgressListener;

/**
 * A progress listener implementation to update the progress with the number
 * of bytes that have been read.
 *
 * @author Rakesh Vidyadharan 2008-11-17
 * @version $Id: UploadProgressListener.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
final class UploadProgressListener implements ProgressListener
{
  private final UploadProgress progress;

  UploadProgressListener( final UploadProgress progress )
  {
    this.progress = progress;
  }

  public void update( final long pBytesRead, final long pContentLength,
      final int pItems )
  {
    progress.setBytesRead( pBytesRead );
  }
}
