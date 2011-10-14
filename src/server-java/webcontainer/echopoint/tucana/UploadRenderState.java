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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import nextapp.echo.webcontainer.RenderState;

/**
 * <code>RenderState</code> implementation for <code>UploadSelect</code>
 * components. This class is thread-safe.
 *
 * @author Echo FileTransfer Library
 * @version $Id: UploadRenderState.java,v 1.2 2011-05-30 14:45:01 perxi Exp $
 */
public class UploadRenderState implements RenderState
{
  private static final long serialVersionUID = 1l;

  private String uploadIndex;
  private final Map<String, UploadProgress> progress;
  private final Map<String,String> ended;

  /** Creates a new <code>UploadRenderState</code>. */
  public UploadRenderState()
  {
    this.uploadIndex = String.valueOf( System.currentTimeMillis() );
    this.progress = new ConcurrentHashMap<String, UploadProgress>();
    this.ended = new ConcurrentHashMap<String,String>();
  }

  /**
   * Gets the maximum upload index currently in use. This index can be used to
   * prevent duplicate indices when performing client-side refreshes.
   *
   * @return the index or <code>-1</code> if no uploads indices are present.
   */
  public String getUploadIndex()
  {
    return uploadIndex;
  }

  /**
   * Announces that an upload with given index has started.
   *
   * @param uploadIndex the upload index
   */
  public void uploadStarted( final String uploadIndex )
  {
    this.uploadIndex = uploadIndex;
  }

  /**
   * Determines whether the upload with the specified index has ended.
   *
   * @param uploadIndex the upload index
   * @return <code>true</code> if the upload has ended.
   */
  public boolean isUploadEnded( final String uploadIndex )
  {
    return ended.containsKey( uploadIndex );
  }

  /**
   * Announces that an upload with given index has ended.
   *
   * @param uploadIndex The upload index for the current upload.
   */
  public void uploadEnded( final String uploadIndex )
  {
    ended.put( uploadIndex, uploadIndex );
  }

  /**
   * Gets the progress for the given upload index.
   *
   * @param uploadIndex the upload index
   * @return the progress if available, <code>null</code> otherwise.
   */
  public UploadProgress getProgress( final String uploadIndex )
  {
    return progress.get( uploadIndex );
  }

  /**
   * Sets the progress for the specified upload index.
   *
   * @param uploadIndex the upload index
   * @param progress the progress
   */
  public void setProgress( final String uploadIndex, final UploadProgress progress )
  {
    if ( this.progress.containsKey( uploadIndex ) )
    {
      throw new IllegalStateException( "UploadIndex: " + uploadIndex +
          " has already been used." );
    }

    this.progress.put( uploadIndex, progress );
  }
}
