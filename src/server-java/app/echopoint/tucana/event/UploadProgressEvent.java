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
package echopoint.tucana.event;

import echopoint.tucana.FileUploadSelector;
import echopoint.tucana.UploadProgress;

/**
 * An event indicating that a file upload has progressed.
 *
 * <p><b>Note:</b> Development of this component was sponsored by <a
 * href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author Rakesh Vidyadharan 2008-11-4
 * @version $Id: UploadProgressEvent.java,v 1.2 2011-05-30 14:44:50 perxi Exp $
 */
public class UploadProgressEvent extends UploadEvent
{
  private static final long serialVersionUID = 1l;

  /** The progress object that encapsulates the file upload progress. */
  private final UploadProgress progress;

  /**
   * Short form of the constructor that populates only the mandatory fields.
   *
   * @param source the source of the event
   * @param index the index of the upload
   * @param contentLength The content length http header value.
   * @param progress The progress of the file upload.
   */
  public UploadProgressEvent( final FileUploadSelector source,
      final String index, final long contentLength, final UploadProgress progress )
  {
    this( source, index, null, null, contentLength, progress );
  }

  /**
   * Creates a new {@link UploadEvent}.  This is the designated constructor.
   *
   * @param source the source of the event
   * @param index the index of the upload
   * @param fileName the name of the file, may not contain path information
   * @param contentType the content type of the uploaded file
   * @param contentLength The content length http header value.
   * @param progress The progress of the file upload.
   */
  public UploadProgressEvent( final FileUploadSelector source,
      final String index, final String fileName,
      final String contentType, final long contentLength,
      final UploadProgress progress )
  {
    super( source, index, fileName, contentType, contentLength );
    this.progress = progress;
  }

  /**
   * Returns the progress of the file upload.
   *
   * @return the progress of the file upload.
   */
  public UploadProgress getProgress()
  {
    return progress;
  }
}
