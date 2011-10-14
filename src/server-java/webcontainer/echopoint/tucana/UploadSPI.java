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

import java.io.File;
import java.io.IOException;

import nextapp.echo.webcontainer.Connection;
import nextapp.echo.webcontainer.WebContainerServlet;

/**
 * Service provider interface (SPI) which allows pluggable selection of the
 * library to be used to parse file uploads from <code>HttpServletRequest</code>.
 * <p/>
 * <p>Custom <code>UploadSPI</code> implementations may be configured by
 * calling {@link UploadProviderFactory#setUploadProvider(UploadSPI)} from
 * within an application entry point.</p>
 *
 * @author Echo File Transfer Library
 * @version $Id: UploadSPI.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public interface UploadSPI extends WebContainerServlet.MultipartRequestWrapper
{
  /** Constant indicating that there is no size limit. */
  public static final short NO_SIZE_LIMIT = -1;

  /**
   * Gets the directory to be used for storing file uploads that exceed the
   * memory cache threshold. Implementations should ensure that the returned
   * location exists. Implementations that do not support this should ignore
   * calls to this method.
   *
   * @return location to store uploaded files.
   * @throws java.io.IOException if an I/O error occurs during this
   * operation
   */
  public File getDiskCacheLocation() throws IOException;

  /**
   * Gets the maximum size, in bytes, of file uploads to be accepted.
   * Uploads that exceed the specified size are terminated or handled in a
   * manner that minimizes the risk of a Denial of Service attack against
   * the application. Implementations that do not impose a size limit should
   * return {@link #NO_SIZE_LIMIT}.
   *
   * @return the size threshold after which a file upload is rejected or
   *         {@link #NO_SIZE_LIMIT} if there is no size limit.
   */
  public long getFileUploadSizeLimit();

  /**
   * Gets the size, in bytes, that the file upload must exceed before being
   * cached to disk rather than in-memory. Implementations that do not
   * support this should ignore calls to this method.
   *
   * @return the maximum size in bytes of the file upload before it will be
   *         cached to disk.
   */
  public int getMemoryCacheThreshold();

  /**
   * Handles the upload of a file.
   *
   * @param conn provides access to the request and response objects
   * @param uploadSelect provides access to additional settings
   * @param uploadIndex the upload index
   * @param progress used to communicate progress
   * @throws Exception If errors are encountered.
   */
  public void handleUpload( final Connection conn,
      final FileUploadSelector uploadSelect, final String uploadIndex,
      final UploadProgress progress ) throws Exception;
}
