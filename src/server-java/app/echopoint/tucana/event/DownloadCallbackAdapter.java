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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An adapter class that logs the progress of a download command.
 *
 * @author Rakesh 2008-11-11
 * @version $Id: DownloadCallbackAdapter.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class DownloadCallbackAdapter implements DownloadCallback
{
  private static final long serialVersionUID = 1l;

  /** The logger to use to log the download progress. */
  protected static final Logger logger = Logger.getAnonymousLogger();

  /** The log level to use. */
  protected Level level;

  /**
   * Default constructor.  Sets {@link #level} to {@link java.util.logging.Level#FINE}
   */
  public DownloadCallbackAdapter()
  {
    level = Level.FINE;
  }

  /**
   * Create a new instance with the specified logging level.
   *
   * @param level The logging level to use.
   */
  public DownloadCallbackAdapter( final Level level )
  {
    this.level = level;
  }

  /**
   * Indicate that a content download process has been started.
   *
   * @param event The download event object.
   */
  public void downloadStarted( final DownloadStartEvent event )
  {
    logger.log( level, "Download of file: " + event.getFileName() +
        ", of size: " + event.getContentLength() +
        ", and contentType: " + event.getContentType() + " started" );
  }

  /**
   * Indicate that a content download process has ended successfully.
   *
   * @param event The download event object.
   */
  public void downloadFinished( final DownloadFinishEvent event )
  {
    logger.log( level, "Download of file: " + event.getFileName() +
        ", of size: " + event.getContentLength() +
        ", and contentType: " + event.getContentType() + " finished" );
  }

  /**
   * Indicate that a content download process failed, usually due to reasons
   * other than client cancellation.
   *
   * @param event The download fail event object.
   */
  public void downloadFailed( final DownloadFailEvent event )
  {
    logger.log( level, "Download of file: " + event.getFileName() +
        ", of size: " + event.getContentLength() +
        ", and contentType: " + event.getContentType() + " failed",
        event.getException() );
  }

  /**
   * Accessor for property 'level'.
   *
   * @return Value for property 'level'.
   */
  public Level getLevel()
  {
    return level;
  }

  /**
   * Mutator for property 'level'.
   *
   * @param level Value to set for property 'level'.
   */
  public void setLevel( final Level level )
  {
    this.level = level;
  }
}
