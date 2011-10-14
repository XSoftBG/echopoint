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

import static java.util.UUID.randomUUID;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

/**
 * A download provider implementation that streams the contents of the
 * specified input stream to the client request output stream on demand.
 *
 * @author Rakesh 2009-08-21
 * @version $Id: InputStreamDownloadProvider.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class InputStreamDownloadProvider extends AbstractDownloadProvider
{
  private static final long serialVersionUID = 1L;

  /**
   * The input stream to use as the data source to write to the client
   * output stream.
   */
  @SuppressWarnings( { "TransientFieldNotInitialized" } )
  private final transient InputStream stream;

  /**
   * Create a new download provider instance for the specified input
   * stream.
   *
   * @param stream The input stream from which to stream the output.
   */
  public InputStreamDownloadProvider( final InputStream stream )
  {
    this.stream = stream;
  }

  public String getContentType()
  {
    return ( contentType == null ) ? "binary/octet-stream" : contentType;
  }

  public void writeFile( final OutputStream out ) throws IOException
  {
    status = Status.inprogress;

    try
    {
      IOUtils.copy( stream, out );
      out.flush();
    }
    catch ( IOException e )
    {
      status = Status.failed;
      throw e;
    }

    status = Status.completed;
  }

  @Override
  public String getFileName()
  {
    return ( fileName == null ) ? randomUUID().toString() : fileName;
  }
}
