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

import echopoint.tucana.DownloadCommand;

/**
 * A base download event class used to indicate a file/content download
 * process.
 *
 * @author Rakesh 2008-11-11
 * @version $Id: DownloadEvent.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public abstract class DownloadEvent extends EventObject
{
  private static final long serialVersionUID = 1l;

  /** The file name (if any) of the content being downloaded. */
  protected final String fileName;

  /** The total size in bytes of the content that is being downloaded. */
  protected final long contentLength;

  /** The mime type of the content that is being downloaded. */
  protected final String contentType;

  /**
   * Constructs a new download event with the specified content attributes.
   *
   * @param source The object on which the Event initially occurred.
   * @param fileName The name of the file that is being downnloaded.
   * @param contentLength The size in bytes of the content.
   * @param contentType The mime type of the content.
   * @throws IllegalArgumentException if source is null.
   */
  public DownloadEvent( final DownloadCommand source, final String fileName,
      final long contentLength, String contentType )
    throws IllegalArgumentException
  {
    super( source );
    this.fileName = fileName;
    this.contentLength = contentLength;
    this.contentType = contentType;
  }

  /**
   * Accessor for property 'fileName'.
   *
   * @return Value for property 'fileName'.
   */
  public String getFileName()
  {
    return fileName;
  }

  /**
   * Accessor for property 'contentLength'.
   *
   * @return Value for property 'contentLength'.
   */
  public long getContentLength()
  {
    return contentLength;
  }

  /**
   * Accessor for property 'contentType'.
   *
   * @return Value for property 'contentType'.
   */
  public String getContentType()
  {
    return contentType;
  }
}
