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
import nextapp.echo.app.Component;
import nextapp.echo.app.ImageReference;
import nextapp.echo.app.TaskQueueHandle;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import echopoint.ProgressBar;
import echopoint.internal.AbstractContainer;
import echopoint.tucana.event.DefaultUploadListener;
import echopoint.tucana.event.InvalidContentTypeEvent;
import echopoint.tucana.event.UploadCallback;
import echopoint.tucana.event.UploadCancelEvent;
import echopoint.tucana.event.UploadEvent;
import echopoint.tucana.event.UploadFailEvent;
import echopoint.tucana.event.UploadFinishEvent;
import echopoint.tucana.event.UploadProgressEvent;
import echopoint.tucana.event.UploadStartEvent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The file upload selector component.  This component is a re-implementation
 * of the original <i>tucana file upload selector</i> component for Echo2.
 *
 * <p><b>Note:</b> It is critical that this component be set absolute height
 * and width properties.  Percentage based values can cause problems if using
 * a progress bar.</p>
 *
 * <p>The following code shows sample usage of this component:</p>
 * <pre>
 *  import nextapp.echo.app.Border;
 *  import nextapp.echo.app.Color;
 *  import echopoint.tucana.ButtonMode;
 *  import echopoint.tucana.ButtonDisplay;
 *  import echopoint.tucana.FileUploadSelector;
 *  import echopoint.tucana.ProgressBar;
 *  import static echopoint.tucana.UploadSPI;
 *  import echopoint.tucana.event.DefaultUploadCallback;
 *
 *    ...
 *    final FileUploadSelector selector = new FileUploadSelector();
 *    selector.setButtonMode( ButtonMode.image );
 *    selector.setButtonDisplayMode( ButtonDisplay.right );
 *    selector.setInputSize( 20 );
 *    selector.setUploadSizeLimit( NO_SIZE_LIMIT );
 *    selector.setBackground( new Color( 0xa1a1a1 ) );
 *    selector.setBorder( new Border( 1, Color.BLUE, Border.STYLE_GROOVE ) );
 *    selector.setProgressBar( new ProgressBar() );
 *    selector.setUploadCallback( new DefaultUploadCallback( new File( "/tmp" ) ) );
 *    selector.addActionListener( ... );
 *
 *    // Allow only the following types of files to be uploaded.
 *    final HashSet&lt;String&gt; set = new HashSet&lt;String&gt;();
 *    set.add( "image/gif" );
 *    set.add( "image/jpeg" );
 *    set.add( "image/png" );
 *    selector.setContentTypeFilter( set );
 *
 *    parent.add( selector );
 * </pre>
 *
 * <p>There are two different (equivalent) ways to process a completed upload:
 * <ol>
 *   <li>{@link echopoint.tucana.event.UploadCallback} based.  It is best
 *     to use a sub-class of either {@link echopoint.tucana.event.DefaultUploadCallback}
 *     or {@link echopoint.tucana.event.UploadCallbackAdapter} since they
 *     ensure removal of the task queue used to enqueue processing from the
 *     call back methods to the UI thread.  If you sub-class please note that
 *     calls to {@code super.uploadXxx} methods should be invoked at the end
 *     of your over-ridden implementation and not at the top.  The super class
 *     implementations destroys the queue and sets it to {@code null}.
 *     If you implement your own handler
 *     please make sure that you invoke {@link FileUploadSelector#removeTaskQueue()}
 *     at the end of your handler methods.  The queue will be automatically
 *     cleaned up when the component is removed from the hierarchy, so it may
 *     be acceptable to not invoke {@code removeTaskQueue} depending upon how
 *     your application logic works.</li>
 *   <li>{@link nextapp.echo.app.event.ActionListener} based.  Two types of
 *     events are recieved by the event handler:
 *     <ol>
   *     <li>{@link #START_ACTION} - The action command that indicates that
 *         the file upload has commenced.</li>
 *     <li>{@link #COMPLETE_ACTION} - The action command that indicates that
 *         the file upload has finished.</li>
 *     </ol>
 *     <p>Please note that it is safest to check on the command value rather
 *     than check one and default action for the other value.</p>
 *   </li>
 * </ol>
 *
 * <p>The following callback class and associated runnable may be used to
 * update the UI after an upload.</p>
 * <pre>
 *  import nextapp.echo.app.ApplicationInstance;
 *  import nextapp.echo.app.Component;
 *  import nextapp.echo.app.TaskQueueHandle;
 *  import echopoint.DirectHtml;
 *  import echopoint.tucana.event.UploadCallbackAdapter;
 *
 *  public class UploadCallbackImpl extends UploadCallbackAdapter
 *  {
 *    private static final long serialVersionUID = 1l;
 *    private final Component parent;
 *
 *    private UploadCallbackImpl( final Component parent )
 *    {
 *      this.parent = parent;
 *    }
 *
 *    &#64;Override
 *    public void uploadSucceeded( final UploadFinishEvent event )
 *    {
 *      final StringBuilder builder = new StringBuilder( 128 );
 *      builder.append( "Upload of file: &lt;b&gt;" );
 *      builder.append( event.getFileName() );
 *      builder.append( "&lt;/b&gt; succeeded.  File size is: &lt;i&gt;");
 *      builder.append( event.getFileSize() / 1000 );
 *      builder.append( "&lt;/i&gt; kilobytes." );
 *      final DirectHtml html = new DirectHtml( builder.toString() );
 *      parent.add( child );
 *      super.uploadSucceeded( event );
 *    }
 *
 *    &#64;Override
 *    public void uploadFailed( final UploadFailEvent event )
 *    {
 *      final StringBuilder builder = new StringBuilder( 128 );
 *      builder.append( "File upload failed." );
 *      if ( event.getFileName() != null )
 *      {
 *        builder.append( "  Failed file: &lt;i&gt;" );
 *        builder.append( event.getFileName() );
 *        builder.append( "&lt;/i&gt;." );
 *      }
 *
 *      if ( event.getException() != null )
 *      {
 *        builder.append( "Exception: &lt;p&gt;&lt;pre&gt;" );
 *        builder.append( event.getException().toString() );
 *        builder.append( "&lt;/pre&gt;&lt;/p&gt;" );
 *      }
 *
 *      final DirectHtml html = new DirectHtml( builder.toString() );
 *      parent.add( child );
 *      super.uploadFailed( event );
 *    }
 * </pre>
 *
 * <p>Processing UI updates after upload completion is much simpler (and
 * more network friendly) when using an action listener.  A simple action
 * listener may be configured as follows:</p>
 * <pre>
 *  import nextapp.echo.app.Component;
 *  import nextapp.echo.app.event.ActionEvent;
 *  import nextapp.echo.app.event.ActionListener;
 *  import echopoint.tucana.FileUploadSelector;
 *  import echopoint.tucana.event.UploadCallback;
 *  import echopoint.tucana.event.UploadFinishEvent;
 *  import echopoint.DirectHtml;
 *
 *  public class FinishListener implements ActionListener
 *  {
 *    private static final long serialVersionUID = 1l;
 *
 *    public void actionPerformed( final ActionEvent event )
 *    {
 *      final FileUploadSelector upload = ( FileUploadSelector) event.getSource();
 *      final UploadCallback callback = upload.getUploadCallback();
 *      if ( callback != null )
 *      {
 *        final StringBuilder builder = new StringBuilder( 128 );
 *        final boolean success = ( callback.getEvent() instanceof UploadFinishEvent );
 *
 *        if ( success )
 *        {
 *          builder.append( "Upload of file: &lt;b&gt;" );
 *          builder.append( callback.getEvent().getFileName() );
 *          builder.append( "&lt;/b&gt; succeeded.  File size is: &lt;i&gt;");
 *          builder.append( callback.getEvent().getFileSize() / 1000 );
 *          builder.append( "&lt;/i&gt; kilobytes." );
 *        }
 *        else
 *        {
 *          builder.append( "Upload " );
 *          if ( callback.getEvent() != null )
 *          {
 *            builder.append( " of file: &lt;b&gt;" );
 *            builder.append( callback.getEvent().getFileName() );
 *            builder.append( "&lt;/b&gt;" );
 *          }
 *
 *          builder.append( " failed/cancelled." );
 *        }
 *
 *        upload.getParent().add( new DirectHtml( builder.toString() ) );
 *
 *        if ( upload.getProgressBar() != null )
 *        {
 *          upload.getProgressBar().setText( ( success ) ?
 *              "Finished upload!" : "Cancelled upload!" );
 *        }
 *      }
 *    }
 *  }
 * </pre>
 *
 * <p><b>Note:</b> Development of this component was sponsored by <a
 * href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author jvolkman (Echo2), Rakesh 2008-11-2
 * @version $Id: FileUploadSelector.java,v 1.2 2011-05-30 14:44:45 perxi Exp $
 */
@SuppressWarnings( { "ClassWithTooManyMethods", "UnusedDeclaration" } )
public class FileUploadSelector extends AbstractContainer
{
  private static final long serialVersionUID = 1l;

  public static final String PROPERTY_BUTTON_TEXT_UPLOAD = "buttonTextUpload";

  public static final String PROPERTY_BUTTON_TEXT_CANCEL = "buttonTextCancel";

  public static final String PROPERTY_BUTTON_TEXT_WAIT = "buttonTextWait";

  public static final String PROPERTY_BUTTON_IMAGE_UPLOAD = "buttonImageUpload";

  public static final String PROPERTY_BUTTON_IMAGE_CANCEL = "buttonImageCancel";

  public static final String PROPERTY_BUTTON_IMAGE_WAIT = "buttonImageWait";

  public static final String PROPERTY_BUTTON_MODE = "buttonMode";

  public static final String PROPERTY_BUTTON_DISPLAY = "buttonDisplay";

  public static final String PROPERTY_CANCEL_ENABLED = "cancelEnabled";

  /**
   * The size (indicates number of characters) for the input field.
   * This is the same as the old <code>PROPERTY_WIDTH_SIZE</code>.
   * This property is best styled.
   */
  public static final String PROPERTY_INPUT_SIZE = "inputSize";

  /**
   * The interval (in milliseconds) at which the progress service is
   * to be polled for updates.  Note that upload completion and cancellation
   * are inferred through the response from the service.  This property
   * is best styled.
   */
  public static final String PROPERTY_POLLING_INTERVAL = "pollingInterval";

  /** The maximum size of file that is allowed to be uploaded. */
  public static final String PROPERTY_UPLOAD_SIZE_LIMIT = "uploadSizeLimit";

  /**
   * The name of the action that is fired by the client upon completion
   * (regardless of complete or cancel) of the upload process.
   *
   * {@value}
   */
  public static final String COMPLETE_ACTION = "complete";

  /**
   * The name of the action that is fired by the client upon start
   * of the upload process.
   *
   * {@value}
   */
  public static final String START_ACTION = "start";

  /**
   * The callback handler that will be notified of the progress of the file
   * upload process.
   */
  private UploadCallback callback = null;

  /**
   * The allowed content-type(s) for the upload.  Uploads of other types of
   * files will be rejected.
   */
  private final Set<String> contentTypeFilter = new HashSet<String>();

  /**
   * A task queue that may be used to perform UI updates from call back
   * handlers.  Automatically cleaned up in {@link #dispose}.
   */
  private TaskQueueHandle taskQueue;

  /**
   * Default constructor.  Add {@link echopoint.tucana.event.DefaultUploadListener}
   * as a listener for this component to initialise the {@link #taskQueue}.
   */
  public FileUploadSelector()
  {
    this( new DefaultUploadListener() );
  }

  /**
   * Create an upload selector component with the specified upload
   * listener instance.
   *
   * @param listener The upload listener to use with the selector.
   */
  public FileUploadSelector( final DefaultUploadListener listener )
  {
    addActionListener( listener );
  }

  /**
   * Sets the upload button image.
   *
   * @param image The new upload button image.
   */
  public void setButtonUploadImage( final ImageReference image )
  {
    set( PROPERTY_BUTTON_IMAGE_UPLOAD, image );
  }

  /**
   * Gets the current upload button image.
   *
   * @return The current upload button image.
   */
  public ImageReference getButtonUploadImage()
  {
    return (ImageReference) get( PROPERTY_BUTTON_IMAGE_UPLOAD );
  }

  /**
   * Sets the cancel button image.
   *
   * @param image The new cancel button image.
   */
  public void setButtonCancelImage( final ImageReference image )
  {
    set( PROPERTY_BUTTON_IMAGE_CANCEL, image );
  }

  /**
   * Gets the current cancel button image.
   *
   * @return The current cancel button image.
   */
  public ImageReference getButtonCancelImage()
  {
    return (ImageReference) get( PROPERTY_BUTTON_IMAGE_CANCEL );
  }

  /**
   * Sets the wait button image.
   *
   * @param image The new wait button image.
   */
  public void setButtonWaitImage( final ImageReference image )
  {
    set( PROPERTY_BUTTON_IMAGE_WAIT, image );
  }

  /**
   * Gets the current wait button image.
   *
   * @return The current wait button image.
   */
  public ImageReference getButtonWaitImage()
  {
    return (ImageReference) get( PROPERTY_BUTTON_IMAGE_WAIT );
  }

  /**
   * Sets the upload button text.
   *
   * @param text The new upload button text.
   */
  public void setButtonUploadText( final String text )
  {
    set( PROPERTY_BUTTON_TEXT_UPLOAD, text );
  }

  /**
   * Gets the current upload button text.
   *
   * @return The current upload button text.
   */
  public String getButtonUploadText()
  {
    return (String) get( PROPERTY_BUTTON_TEXT_UPLOAD );
  }

  /**
   * Sets the cancel button text.
   *
   * @param text The new cancel button text.
   */
  public void setButtonCancelText( final String text )
  {
    set( PROPERTY_BUTTON_TEXT_CANCEL, text );
  }

  /**
   * Gets the current cancel button text.
   *
   * @return The current cancel button text.
   */
  public String getButtonCancelText()
  {
    return (String) get( PROPERTY_BUTTON_TEXT_CANCEL );
  }

  /**
   * Sets the wait button text.
   *
   * @param text The new wait button text.
   */
  public void setButtonWaitText( final String text )
  {
    set( PROPERTY_BUTTON_TEXT_WAIT, text );
  }

  /**
   * Gets the current wait button text.
   *
   * @return The current wait button text.
   */
  public String getButtonWaitText()
  {
    return (String) get( PROPERTY_BUTTON_TEXT_WAIT );
  }

  /**
   * Sets the upload button display mode
   *
   * @param mode The new upload button display mode
   */
  public void setButtonMode( final ButtonMode mode )
  {
    set( PROPERTY_BUTTON_MODE, mode );
  }

  /**
   * Get the current upload button display mode.
   *
   * @return The current display mode, or {@link ButtonMode#submit} if not set.
   */
  public ButtonMode getButtonMode()
  {
    final ButtonMode mode = (ButtonMode) get( PROPERTY_BUTTON_MODE );
    return ( mode == null ) ? ButtonMode.submit : mode;
  }

  /**
   * Set the button display mode for the input submit button.
   *
   * @param mode Set to configure the location of the submit button.
   */
  public void setButtonDisplayMode( final ButtonDisplay mode )
  {
    set( PROPERTY_BUTTON_DISPLAY, mode );
  }

  /**
   * Get the configuration for display of the submit button.
   *
   * @return The current mode, or {@link ButtonDisplay#auto} if not set.
   */
  public ButtonDisplay getButtonDisplayMode()
  {
    final ButtonDisplay display = (ButtonDisplay) get( PROPERTY_BUTTON_DISPLAY );
    return ( display == null ) ? ButtonDisplay.auto : display;
  }

  /**
   * Mutator for property 'cancelEnabled'.
   *
   * @param enabled Value to set for property 'cancelEnabled'.
   */
  public void setCancelEnabled( final boolean enabled )
  {
    set( PROPERTY_CANCEL_ENABLED, enabled );
  }

  /**
   * Accessor for property 'cancelEnabled'.
   *
   * @return Value for property 'cancelEnabled'.
   */
  public boolean isCancelEnabled()
  {
    return ( (Boolean) get( PROPERTY_CANCEL_ENABLED ) );
  }

  /**
   * Return the value of the {@link #PROPERTY_INPUT_SIZE} property.
   *
   * @return The size of the input text field.
   */
  public int getInputSize()
  {
    return (Integer) get( PROPERTY_INPUT_SIZE );
  }

  /**
   * Set the value of the {@link #PROPERTY_INPUT_SIZE} property.
   *
   * @param size The size of the input text field.
   */
  public void setInputSize( final int size )
  {
    set( PROPERTY_INPUT_SIZE, size );
  }

  /**
   * Return the value of the {@link #PROPERTY_POLLING_INTERVAL} property.
   *
   * @return The time interval at which the progress service will be polled
   */
  public int getPollingInterval()
  {
    return (Integer) get( PROPERTY_POLLING_INTERVAL );
  }

  /**
   * Set the value of the {@link #PROPERTY_POLLING_INTERVAL} property.
   *
   * @param interval The time interval at which the progress service will be polled
   */
  public void setPollingInterval( final int interval )
  {
    set( PROPERTY_POLLING_INTERVAL, interval );
  }

  /**
   * Return the value of the {@link #PROPERTY_UPLOAD_SIZE_LIMIT} property.
   * If not set the implementation defaults to
   * {@link echopoint.tucana.AbstractFileUploadProvider#DEFAULT_UPLOAD_SIZE_LIMIT}.
   *
   * @return The maximum size in bytes that is allowed to be uploaded.
   */
  public long getUploadSizeLimit()
  {
    final Object obj = get( PROPERTY_UPLOAD_SIZE_LIMIT );
    return ( obj == null ) ? 0 : (Long) obj;
  }

  /**
   * Set the value of the {@link #PROPERTY_UPLOAD_SIZE_LIMIT} property.
   * Specify {@link echopoint.tucana.UploadSPI#NO_SIZE_LIMIT} to prevent
   * size checking.
   *
   * @param limit The maximum size in bytes that is allowed.
   */
  public void setUploadSizeLimit( final long limit )
  {
    set( PROPERTY_UPLOAD_SIZE_LIMIT, limit );
  }

  /**
   * Set the callback used when a file is uploaded.
   *
   * @param callback The upload callback.
   */
  public void setUploadCallback( final UploadCallback callback )
  {
    this.callback = callback;
  }

  /**
   * Get the callback used when a file is uploaded.
   *
   * @return The upload callback.
   */
  public UploadCallback getUploadCallback()
  {
    return callback;
  }

  /**
   * Return the progress bar component configured for this component.
   *
   * @return The progress bar if one was added or <code>null</code>.
   */
  public ProgressBar getProgressBar()
  {
    return ( getComponentCount() > 0 ) ? (ProgressBar) getComponent( 0 ) : null;
  }

  /**
   * Add the specified progress bar to this component.
   *
   * @param progressBar The progress bar component to add.
   */
  public void setProgressBar( final ProgressBar progressBar )
  {
    add( progressBar, 0 );
  }

  /**
   * Accessor for property 'contentTypeFilter'.  Returns the content-types
   * that are allowed to be uploaded by this component.
   *
   * @return Value for property 'contentTypeFilter'.
   */
  public Set<String> getContentTypeFilter()
  {
    return Collections.unmodifiableSet( contentTypeFilter );
  }

  /**
   * Mutator for property 'contentTypeFilter'.
   *
   * @param contentTypeFilter Value to set for property 'contentTypeFilter'.
   */
  public void setContentTypeFilter( final Set<String> contentTypeFilter )
  {
    this.contentTypeFilter.clear();

    if ( contentTypeFilter != null )
    {
      this.contentTypeFilter.addAll( contentTypeFilter );
    }
  }

  /**
   * The only allowed sub-component is a {@link echopoint.ProgressBar}.
   *
   * @param child The child component that is to be added if allowed.
   * @return Return <code>true</code> if child is an instance of {@link
   *   echopoint.ProgressBar} and a progress bar has not already been
   *   assigned.
   */
  @Override
  public boolean isValidChild( final Component child )
  {
    final boolean result = ( child instanceof ProgressBar );
    return result && ( getComponentCount() < 2 );
  }

  /**
   * Return the task queue that may be used to enqueue asynchronous tasks
   * (usually from callback handlers).
   *
   * @return Value for property 'taskQueue'.
   */
  public TaskQueueHandle getTaskQueue()
  {
    return taskQueue;
  }

  /**
   * Set the task queue for the component.
   *
   * @param taskQueue Value to set for property 'taskQueue'.
   */
  public void setTaskQueue( final TaskQueueHandle taskQueue )
  {
    this.taskQueue = taskQueue;
  }

  /**
   * Remove the current task queue.  It is strongly recommended that this
   * method be used rather than wait for {@link #dispose}.
   */
  public void removeTaskQueue()
  {
    if ( taskQueue != null )
    {
      ApplicationInstance.getActive().removeTaskQueue( taskQueue );
      taskQueue = null;
    }
  }

  /**
   * Notifies the listener that the given event has occurred.
   *
   * @param e the event
   */
  protected void notifyCallback( final UploadEvent e )
  {
    if ( callback == null ) return;

    if ( e instanceof UploadCancelEvent )
    {
      callback.uploadCancelled( (UploadCancelEvent) e );
    }
    if ( e instanceof InvalidContentTypeEvent )
    {
      callback.uploadDisallowed( (InvalidContentTypeEvent) e );
    }
    else if ( e instanceof UploadFailEvent )
    {
      callback.uploadFailed( (UploadFailEvent) e );
    }
    else if ( e instanceof UploadFinishEvent )
    {
      callback.uploadSucceeded( (UploadFinishEvent) e );
    }
    else if ( e instanceof UploadProgressEvent )
    {
      callback.uploadProgressed( (UploadProgressEvent) e );
    }
    else if ( e instanceof UploadStartEvent )
    {
      callback.uploadStarted( (UploadStartEvent) e );
    }
  }

  /** {@inheritDoc} */
  @Override
  public void addActionListener( final ActionListener listener )
  {
    super.addActionListener( listener );
  }

  /** {@inheritDoc} */
  @Override
  public void removeActionListener( final ActionListener listener )
  {
    super.removeActionListener( listener );
  }

  /**
   * Over-ridden to fire action events with action commands that are set to
   * {@link #START_ACTION} or {@link #COMPLETE_ACTION} depending upon whether
   * the event represents start of the upload or completion of the upload.
   *
   * @see nextapp.echo.app.Component#processInput(String, Object)
   */
  @Override
  public void processInput( final String name, final Object value )
  {
    super.processInput( name, value );

    if ( COMPLETE_ACTION.equals( name ) )
    {
      fireActionPerformed( new ActionEvent( this, name ) );
    }
    else if ( START_ACTION.equals( name ) )
    {
      fireActionPerformed( new ActionEvent( this, name ) );
    }
  }

  /**
   * Over-ridden to clean up {@link #taskQueue} if not already cleaned up.
   *
   * @see #removeTaskQueue()
   * @see nextapp.echo.app.Component#dispose()
   */
  @Override
  public void dispose()
  {
    removeTaskQueue();
  }
}
