package echopoint.tucana;

import java.io.Serializable;

/**
 * A base runnable used to execute callback handlers.
 *
 * @author Rakesh Vidyadharan 2009-2-3
 * @version $Id: AbstractRunnable.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
abstract class AbstractRunnable implements Runnable, Serializable
{
  private static final long serialVersionUID = 1l;

  final FileUploadSelector uploadSelect;
  final String uploadIndex;
  final String fileName;
  final String contentType;
  final UploadProgress progress;

  AbstractRunnable( final FileUploadSelector uploadSelect,
      final String uploadIndex, final String fileName,
      final String contentType, final UploadProgress progress )
  {
    this.uploadSelect = uploadSelect;
    this.uploadIndex = uploadIndex;
    this.fileName = fileName;
    this.contentType = contentType;
    this.progress = progress;
  }
}
