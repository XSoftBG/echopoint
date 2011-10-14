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

/**
 * An upload event that represents the case where an upload was terminated
 * by the server due to the uploaded content-type not being allowed by the
 * service.
 *
 * <p><b>Note:</b> Development of this component was sponsored by <a
 * href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author Rakesh 2008-11-17
 * @version $Id: InvalidContentTypeEvent.java,v 1.2 2011-05-30 14:44:50 perxi Exp $
 */
public class InvalidContentTypeEvent extends UploadEvent
{
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new {@link UploadEvent}.
   *
   * @param source the source of the event
   * @param index the index of the upload
   * @param fileName the name of the file, may not contain path information
   * @param contentType the content type of the uploaded file
   * @param contentLength The content length as reported by upload
   */
  public InvalidContentTypeEvent( final FileUploadSelector source,
      final String index, final String fileName, final String contentType,
      final long contentLength )
  {
    super( source, index, fileName, contentType, contentLength );
  }
}
