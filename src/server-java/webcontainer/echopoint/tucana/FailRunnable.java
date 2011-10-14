package echopoint.tucana;

import echopoint.tucana.event.UploadFailEvent;

/**
 * A runnable used to execute the upload fail event call back handler.
 *
 * @author Rakesh Vidyadharan 2009-2-3
 * @version $Id: FailRunnable.java,v 1.2 2011-05-30 14:45:01 perxi Exp $
 */
class FailRunnable extends AbstractRunnable
{
  private static final long serialVersionUID = 1l;
  private final Exception e;

  FailRunnable( final FileUploadSelector uploadSelect,
      final String uploadIndex, final String fileName,
      final String contentType, final Exception e, final UploadProgress progress )
  {
    super( uploadSelect, uploadIndex, fileName, contentType, progress );
    this.e = e;
  }

  public void run()
  {
    progress.setStatus( Status.failed );
    uploadSelect.notifyCallback( new UploadFailEvent( uploadSelect,
        uploadIndex, fileName, contentType, progress.getContentLength(), e ) );
  }
}
