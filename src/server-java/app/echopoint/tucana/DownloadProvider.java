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
import java.io.OutputStream;
import java.io.Serializable;

/**
 * An interface to be implemented by the class providing the data to be
 * downloaded.
 *
 * @author Echo File Transfer Library
 * @version $Id: DownloadProvider.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public interface DownloadProvider extends Serializable
{
  /**
   * Returns the content type, for example "text/plain".
   *
   * @return the content type.
   */
  String getContentType();

  /**
   * Returns the value of the disposition-type in the Content-Disposition
   * parameter of the response header.  {@code null} indicates that a
   * Content-Disposition parameter should not be included in the reponse
   * header.  See RFC 2183 for more info.  The following values are most
   * commonly used:
   *
   * <ul>
   *   <li>{@code inline} - Indicates that the browser should attempt to
   *     display the content.</li>
   *   <li>{@code attachment} - Usually the default action.  The browser
   *     will display a save content dialogue.</li>
   * </ul>
   *
   * @return The file's Content-Disposition
   */
  String getContentDisposition();

  /**
   * Returns the file name, for example "my-file.txt".
   *
   * @return the file name.
   */
  String getFileName();

  /**
   * Returns the size of the data to be downloaded.
   *
   * @return the size of the data to be downloaded.
   */
  long getSize();

  /**
   * Writes the file data to the output stream.
   *
   * @param out the output stream to which the file data must be written.
   * @throws IOException If errors are encountered while writing to the
   *   output stream.
   */
  void writeFile( final OutputStream out ) throws IOException;

  /**
   * Return the status of the download action.
   *
   * @return A status indicator regarding progress of the download.
   */
  Status getStatus();
}
