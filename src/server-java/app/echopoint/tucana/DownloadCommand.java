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

import java.io.Serializable;

import nextapp.echo.app.ApplicationInstance;
import nextapp.echo.app.Command;
import nextapp.echo.app.RenderIdSupport;
import echopoint.tucana.event.DownloadCallback;
import echopoint.tucana.event.DownloadEvent;
import echopoint.tucana.event.DownloadFailEvent;
import echopoint.tucana.event.DownloadFinishEvent;
import echopoint.tucana.event.DownloadStartEvent;

/**
 * A command used to enqueue a file (or similar) to the client for downlaoding
 * from the server.
 *
 * <p>The following code shows the simplest form of using this command:</p>
 * <pre>
 *   import java.io.File;
 *   import echopoint.tucana.DownloadButton
 *
 *     ...
 *     final File file = new File( "/tmp/test.txt" );
 *     final DownloadButton button = new DownloadButton( file );
 *     button.setText( "Download file" );
 *     button.setStyleName( "mystyle" );
 *     parent.add( button );
 * </pre>
 *
 * @see DownloadButton
 * @author Echo File Transfer Library, Rakesh 2008-11-10
 * @version $Id: DownloadCommand.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class DownloadCommand implements Command, RenderIdSupport, Serializable
{
  public static final int MODE_DEFAULT = 0;
  public static final int MODE_OPEN_IN_NEW_TAB = 1;
  public static final int MODE_PRINT = 2;
  
  private static final long serialVersionUID = 1L;  

  /** The render id for the command. */
  private String id;
  
  private int mode = MODE_DEFAULT;

  /** The download provider implementation for the command. */
  private DownloadProvider provider;

  /**
   * The callback handler that will be notified of the progress of the file
   * download process.
   */
  private DownloadCallback callback = null;

  /** Constructs a new download command. */
  public DownloadCommand() {}

  /**
   * Constructs a new download command, whose data will be taken from the
   * passed {@link DownloadProvider}.
   *
   * @param provider the provider from which to get the data.
   */
  public DownloadCommand( final DownloadProvider provider )
  {
    this.provider = provider;
  }

  /**
   * Returns the download provider.
   *
   * @return the download provider.
   */
  public DownloadProvider getProvider()
  {
    return provider;
  }

  /**
   * Sets the download provider from which to get the data.
   *
   * @param newValue the download provider from which to get the data.
   */
  public void setProvider( final DownloadProvider newValue )
  {
    this.provider = newValue;
  }

  /**
   * Defines if the download url should be opened in new tab.
   */
  public void setDownloadMode(int mode)
  {
    this.mode = mode;
  }
  
  /**
   * @return the download mode
   */
  public int getDownloadMode()
  {
    return mode;
  }
  
  /**
   * Returns the render id.
   *
   * @return the render id.
   */
  public String getRenderId()
  {
    if ( id == null )
    {
      id = ApplicationInstance.generateSystemId();
    }
    return id;
  }

  /**
   * Accessor for property 'callback'.
   *
   * @return Value for property 'callback'.
   */
  public DownloadCallback getCallback()
  {
    return callback;
  }

  /**
   * Mutator for property 'callback'.
   *
   * @param callback Value to set for property 'callback'.
   */
  public void setCallback( final DownloadCallback callback )
  {
    this.callback = callback;
  }

  /**
   * Notify the download progress callback handler of updates to the download
   * process.
   *
   * @param event The progress event.
   */
  protected void notifyCallback( final DownloadEvent event )
  {
    if ( callback == null ) return;

    if ( event instanceof DownloadStartEvent )
    {
      callback.downloadStarted( (DownloadStartEvent) event );
    }
    if ( event instanceof DownloadFinishEvent )
    {
      callback.downloadFinished( (DownloadFinishEvent) event );
    }
    else if ( event instanceof DownloadFailEvent )
    {
      callback.downloadFailed( (DownloadFailEvent) event );
    }
  }
}
