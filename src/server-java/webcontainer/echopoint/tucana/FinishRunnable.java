package echopoint.tucana;

import org.apache.commons.fileupload.FileItem;

import echopoint.tucana.event.UploadFinishEvent;

/**
 * A runnable used to execute the upload finish event call back handler.
 *
 * @author Rakesh Vidyadharan 2009-2-3
 * @version $Id: FinishRunnable.java,v 1.2 2011-05-30 14:45:01 perxi Exp $
 */
class FinishRunnable extends AbstractRunnable
{
  private static final long serialVersionUID = 1L;
  private final FileItem item;

  FinishRunnable( final FileUploadSelector uploadSelect,
      final String uploadIndex, final String fileName,
      final FileItem item, final UploadProgress progress )
  {
    super( uploadSelect, uploadIndex, fileName, item.getContentType(), progress );
    this.item = item;
  }

  public void run()
  {
    uploadSelect.notifyCallback( new UploadFinishEvent( uploadSelect,
        uploadIndex, fileName, item.getContentType(),
        progress.getContentLength(), item ) );
    progress.setStatus( Status.completed );
  }
}
