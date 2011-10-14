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

import nextapp.echo.app.ApplicationInstance;
import nextapp.echo.app.Button;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import echopoint.tucana.event.DownloadCallback;
import echopoint.tucana.event.DownloadCallbackAdapter;

import java.io.File;
import java.io.InputStream;

/**
 * A convenience component to display a {@link DownloadCommand} as a button
 * with a pre-configured {@link nextapp.echo.app.event.ActionListener}.
 *
 * <p>The following shows sample use of this component:</p>
 * <pre>
 *   import echopoint.Strut;
 *   import echopoint.tucana.DownloadButton;
 *   import nextapp.echo.app.Row;
 *   import java.io.File;
 *
 *     ...
 *     final File file = new File( "/path/tofile" );
 *     final Row row = new Row();
 *     final DownloadButton button = new DownloadButton( file );
 *     button.setText( "Download File" );
 *     row.add( new Strut() );
 *     row.add( button );
 *     row.add( new Strut() );
 * </pre>
 *
 * @author Rakesh 2008-11-11
 * @version $Id: DownloadButton.java,v 1.2 2011-05-30 14:44:45 perxi Exp $
 */
public class DownloadButton extends Button
{
  private static final long serialVersionUID = 1L;

  /** The download command instance that is to be enqueued. */
  private DownloadProvider provider;

  /**
   * The callback handler to use with the download command.  Defaults to
   * {@link echopoint.tucana.event.DownloadCallbackAdapter}.
   */
  private DownloadCallback callback;

  /**
   * Create a new instance for the specified download provider.  This is the
   * designated constructor.
   *
   * @param provider The download provider to use with the {@link DownloadCommand}
   */
  public DownloadButton( final DownloadProvider provider )
  {
    setProvider( provider );
    setText( "Download" );
    setDownloadCallback( new DownloadCallbackAdapter() );
    super.addActionListener( new DownloadListener() );
  }

  /**
   * Create a new instance for the specified file (using a {@link
   * FileDownloadProvider}.
   *
   * @param file The file to be enqueued for download.
   */
  public DownloadButton( final File file )
  {
    this( new FileDownloadProvider( file ) );
  }

  /**
   * Create a new instance using the input stream to feed a {@link
   * InputStreamDownloadProvider}.
   *
   * @param stream The input stream to use as the data source.
   */
  public DownloadButton( final InputStream stream )
  {
    this( new InputStreamDownloadProvider( stream ) );
  }

  /**
   * Accessor for property 'provider'.
   *
   * @return Value for property 'provider'.
   */
  public DownloadProvider getProvider()
  {
    return provider;
  }

  /**
   * Over-ridden to not allow any other listeners than the default.  Does not
   * throw any exception, just ignores the call.
   *
   * @param listener The listener to add.
   */
  @Override
  public void addActionListener( final ActionListener listener )
  {
    // noop
  }

  /**
   * Mutator for property 'provider'.
   *
   * @param provider Value to set for property 'provider'.
   */
  public void setProvider( final DownloadProvider provider )
  {
    this.provider = provider;
  }

  /**
   * Mutator for property 'provider'.
   *
   * @param file The file to be enqueued for download.
   */
  public void setProvider( final File file )
  {
    setProvider( new FileDownloadProvider( file ) );
  }

  /**
   * Accessor for property 'callback'.
   *
   * @return Value for property 'callback'.
   */
  public DownloadCallback getDownloadCallback()
  {
    return callback;
  }

  /**
   * Mutator for property 'callback'.
   *
   * @param callback Value to set for property 'callback'.
   */
  public void setDownloadCallback( final DownloadCallback callback )
  {
    this.callback = callback;
  }

  /**
   * The standard action listener to associate with the download button.
   */
  private class DownloadListener implements ActionListener
  {
    private static final long serialVersionUID = 1l;

    /**
     * Create a new {@link DownloadCommand} and enqueue it for download.
     *
     * @param event The event object that was triggered.
     */
    public void actionPerformed( final ActionEvent event )
    {
      final DownloadCommand command = new DownloadCommand( provider );
      command.setCallback( callback );
      ApplicationInstance.getActive().enqueueCommand( command );
    }
  }
}
