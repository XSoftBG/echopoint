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

import java.util.EventObject;

import echopoint.tucana.FileUploadSelector;

/**
 * A base class that represents an upload event.

 * <p><b>Note:</b> Development of this component was sponsored by <a
 * href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author jvolkman (Echo2), Rakesh 2008-11-2
 * @version $Id: UploadEvent.java,v 1.2 2011-05-30 14:44:50 perxi Exp $
 */
public abstract class UploadEvent extends EventObject
{
  private static final long serialVersionUID = 1l;

  /** The unique upload index for the upload event. */
  private final String index;

  /** The content type for the file that was uploaded. */
  private final String contentType;

  /** The name of the file that was uploaded. */
  private final String fileName;

  /** The content length header as specified by the upload request. */
  private final long contentLength;

  /**
   * Creates a new {@link UploadEvent}.
   *
   * @param source the source of the event
   * @param index the index of the upload
   * @param fileName the name of the file, may not contain path information
   * @param contentType the content type of the uploaded file
   * @param contentLength The content length http header value.
   */
  public UploadEvent( final FileUploadSelector source, final String index,
      final String fileName, final String contentType, final long contentLength )
  {
    super( source );
    this.index = index;
    this.fileName = fileName;
    this.contentType = contentType;
    this.contentLength = contentLength;
  }

  /**
   * Returns the index of the file.
   *
   * @return the index of the file.
   */
  public String getIndex()
  {
    return index;
  }

  /**
   * Return the content type of the uploaded file.
   *
   * @return The content type property of the uploaded file.
   */
  public String getContentType()
  {
    return contentType;
  }

  /**
   * The name of the file that was uploaded.
   *
   * @return The file name.
   */
  public String getFileName()
  {
    return fileName;
  }

  /** @return The content length http header value. */
  public long getContentLength()
  {
    return contentLength;
  }
}
