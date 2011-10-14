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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nextapp.echo.webcontainer.Connection;
import nextapp.echo.webcontainer.ContentType;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.UserInstance;

/**
 * Base service for file uploads.
 *
 * @author Echo File Transfer Library
 * @version $Id: BaseUploadService.java,v 1.4 2011/06/13 12:19:58 perxi Exp $
 */
public abstract class BaseUploadService implements Service
{
 // public String getUrl( final Connection conn,
 //     final AbstractUploadSelect uploadSelect )
 // {
   // return conn.getUserInstance().getServiceUri( this,  new String[]{ "i" },
     //   new String[]{ conn.getUserInstance().getClientRenderId( uploadSelect ) } );
  //}

  /**
   * Validates if the request contains a valid FileUploadSelector render id
   * and index.
   *
   * @see Service#service(Connection)
   */
  public void service( final Connection conn ) throws IOException
  {
    final UserInstance userInstance = conn.getUserInstance();
    if ( userInstance == null )
    {
      serviceBadRequest( conn, "No user instance available." );
      return;
    }

    final HttpServletRequest request = conn.getRequest();
    final String renderId = request.getParameter( "i" );

    if ( renderId == null )
    {
      serviceBadRequest( conn, "FileUploadSelector id not specified." );
      return;
    }

    final FileUploadSelector uploadSelect = (FileUploadSelector)
        userInstance.getComponentByClientRenderId( renderId );

    if ( uploadSelect == null )
    {
      serviceBadRequest( conn,
          "FileUploadSelector id is not valid: " + renderId );
      return;
    }

    final String uploadIndexParam = request.getParameter( "x" );
    if ( uploadIndexParam == null )
    {
      serviceBadRequest( conn,
          "FileUploadSelector upload index not specified." );
      return;
    }

    service( conn, uploadSelect, uploadIndexParam );
  }

  /**
   * Performs the actual service of the request.
   *
   * @param conn The connection to the application.
   * @param uploadSelect The file upload selector instance.
   * @param uploadIndex The unique index of the current upload for the instance.
   * @throws java.io.IOException If errors are encountered.
   */
  public abstract void service( final Connection conn,
      final FileUploadSelector uploadSelect, final String uploadIndex )
    throws IOException;

  /**
   * Serves a bad request message.
   *
   * @param conn The connection to the application.
   * @param message The message to display for the bad request.
   */
  protected static void serviceBadRequest( final Connection conn,
      final String message )
  {
    conn.getResponse().setStatus( HttpServletResponse.SC_BAD_REQUEST );
    conn.setContentType( ContentType.TEXT_PLAIN );
    conn.getWriter().write( message );
  }
}
