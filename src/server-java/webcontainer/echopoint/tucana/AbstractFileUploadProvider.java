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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Base {@link UploadSPI} implementation.
 *
 * @author Echo File Transfer Library
 * @version $Id: AbstractFileUploadProvider.java,v 1.2 2011-05-30 14:45:01 perxi Exp $
 */
public abstract class AbstractFileUploadProvider implements UploadSPI
{
  static final int DEFAULT_MEMORY_CACHE_THRESHOLD = 16 * 1024; // 16 KB
  static final File DEFAULT_TEMP_LOCATION = new File( System.getProperty( "java.io.tmpdir", "." ) );
  static final int DEFAULT_UPLOAD_SIZE_LIMIT = 128 * 1024 * 1024; // 128 MB

  /** @see UploadSPI#getDiskCacheLocation() */
  public File getDiskCacheLocation() throws IOException
  {
    return DEFAULT_TEMP_LOCATION;
  }

  /** @see UploadSPI#getFileUploadSizeLimit() */
  public long getFileUploadSizeLimit()
  {
    return DEFAULT_UPLOAD_SIZE_LIMIT;
  }

  /** @see UploadSPI#getMemoryCacheThreshold() */
  public int getMemoryCacheThreshold()
  {
    return DEFAULT_MEMORY_CACHE_THRESHOLD;
  }

  /** @see nextapp.echo.webcontainer.WebContainerServlet.MultipartRequestWrapper#getWrappedRequest(javax.servlet.http.HttpServletRequest) */
  public HttpServletRequest getWrappedRequest( final HttpServletRequest request )
      throws IOException, ServletException
  {
    return request;
  }
}
