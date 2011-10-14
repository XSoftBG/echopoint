package echopoint;

import nextapp.echo.app.Border;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Font;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import echopoint.tucana.ButtonDisplay;
import echopoint.tucana.ButtonMode;
import echopoint.tucana.DownloadButton;
import echopoint.tucana.FileUploadSelector;
import echopoint.tucana.ProgressBar;
import echopoint.tucana.event.DefaultUploadCallback;
import echopoint.tucana.event.DownloadCallbackAdapter;
import echopoint.tucana.event.InvalidContentTypeEvent;
import echopoint.tucana.event.UploadCancelEvent;
import echopoint.tucana.event.UploadFailEvent;
import echopoint.tucana.event.UploadFinishEvent;
import echopoint.tucana.event.UploadProgressEvent;
import echopoint.tucana.event.UploadStartEvent;

import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Unit test suite for the {@link FileUploadSelector}
 * component.
 *
 * @author Rakesh Vidyadharan 2008-11-4
 * @version $Id: FileUploadSelectorTest.java,v 1.2 2011-05-28 13:24:15 perxi Exp $
 */
public class FileUploadSelectorTest extends AbstractTest<FileUploadSelector>
{
  @BeforeClass
  public static void init()
  {
    set( new FileUploadSelector() );
  }

  @Test
  public void buttonMode()
  {
    final ButtonMode mode = ButtonMode.image;
    getComponent().setButtonMode( mode );
    assertEquals( "Ensure button mode set", mode,
        getComponent().getButtonMode() );
  }

  @Test
  public void buttonDisplay()
  {
    final ButtonDisplay display = ButtonDisplay.none;
    getComponent().setButtonDisplayMode( display );
    assertEquals( "Ensure button display set", display,
        getComponent().getButtonDisplayMode() );
  }

  @Test
  public void inputSize()
  {
    final int size = 17;
    getComponent().setInputSize( size );
    assertEquals( "Ensure input size set", size, getComponent().getInputSize() );
  }

  @Test
  public void background()
  {
    final Color color = new Color( 0xa1a1a1 );
    getComponent().setBackground( color );
    assertEquals( "Ensure foreground set", color, getComponent().getBackground() );
  }

  @Test
  public void border()
  {
    final Border border = new Border( 1, Color.BLUE, Border.STYLE_GROOVE );
    getComponent().setBorder( border );
    assertEquals( "Ensure border set", border, getComponent().getBorder() );
  }

  @Test
  public void font()
  {
    final Font font = new Font( Font.HELVETICA, Font.PLAIN, new Extent( 12 ) );
    getComponent().setFont( font );
    assertEquals( "Ensure font set", font, getComponent().getFont() );
  }

  @Test
  public void height()
  {
    final Extent height = new Extent( 47 );
    getComponent().setHeight( height );
    assertEquals( "Ensure height set", height, getComponent().getHeight() );
  }

  @Test
  public void insets()
  {
    final Insets insets = new Insets( new Extent( 2 ) );
    getComponent().setInsets( insets );
    assertEquals( "Ensure insets set", insets, getComponent().getInsets() );
  }

  @Test
  public void progressBar()
  {
    final ProgressBar bar = new ProgressBar();
    bar.setWidth( new Extent( 298 ) );
    bar.setPattern( "#bytes# of #length# Kb @ #rate# Kb/s" );
    getComponent().setProgressBar( bar );
    assertEquals( "Ensure that progress bar is set",
        bar, getComponent().getProgressBar() );
    bar.setVisible( false );
  }

  @Test
  public void pollingInterval()
  {
    final int interval = 100;
    getComponent().setPollingInterval( interval );
    assertEquals( "Ensuring polling interval set", interval,
        getComponent().getPollingInterval() );
  }

  @Test
  public void filter()
  {
    final HashSet<String> set = new HashSet<String>();
    set.add( "image/gif" );
    set.add( "image/jpeg" );
    set.add( "image/pjpeg" );
    set.add( "image/png" );
    set.add( "image/x-png" );
    getComponent().setContentTypeFilter( set );
    assertEquals( "Ensure content type filter set", set,
        getComponent().getContentTypeFilter() );
  }

  //@Test
  public void uploadSizeLimit()
  {
    //final long size = UploadSPI.NO_SIZE_LIMIT;
    final long size = 100;
    getComponent().setUploadSizeLimit( size );
    assertEquals( "Ensuring upload size limit set", size,
        getComponent().getUploadSizeLimit() );
  }

  @Test
  public void callback()
  {
    final Callback callback = new Callback( getComponent(),
        new File( "/tmp" ) );
    callback.setLevel( Level.INFO );
    getComponent().setUploadCallback( callback );
    assertEquals( "Ensuring callback set", callback, getComponent().getUploadCallback() );
  }

  @Test
  public void actionListener()
  {
    final FileUploadSelector component = getComponent();

    getComponent().addActionListener( new ActionListener()
    {
      private static final long serialVersionUID = 1L;

      public void actionPerformed( final ActionEvent event )
      {
        Logger.getAnonymousLogger().info(
            "Action command: " + event.getActionCommand() );
        if ( component.getUploadCallback().getEvent() instanceof UploadStartEvent )
        {
          component.getProgressBar().setVisible( true );
        }
      }
    });
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();
    content.add( get() );
  }

  private static class Callback extends DefaultUploadCallback
  {
    private static final long serialVersionUID = 1l;

    private final FileUploadSelector upload;

    private Callback( final FileUploadSelector upload, final File file )
    {
      super( file );
      this.upload = upload;
    }

    @Override
    public void uploadStarted( final UploadStartEvent event )
    {
      super.uploadStarted( event );
      assertTrue( "Ensure contentLength set", event.getContentLength() != -1 );
    }

    @Override
    public void uploadProgressed( final UploadProgressEvent event )
    {
      super.uploadProgressed( event );
      assertTrue( "Ensure contentLength set", event.getContentLength() != -1 );
    }

    @Override
    public void uploadSucceeded( final UploadFinishEvent event )
    {
      logger.info( "Running succeeded task" );
      final ProgressBar bar = (ProgressBar) upload.getProgressBar();
      bar.setText( "Finished upload!" );
      upload.getParent().add( displayDownload( event ) );
      super.uploadSucceeded( event );
    }

    @Override
    public void uploadFailed( final UploadFailEvent event )
    {
      final ProgressBar bar = (ProgressBar) upload.getProgressBar();
      bar.setText( "Upload failed!" );
      displayError();
      super.uploadFailed( event );
    }

    @Override
    public void uploadDisallowed( final InvalidContentTypeEvent event )
    {
      final ProgressBar bar = (ProgressBar) upload.getProgressBar();
      bar.setText( "Upload disallowed!" );
      super.uploadDisallowed( event );
    }

    @Override
    public void uploadCancelled( final UploadCancelEvent event )
    {
      final ProgressBar bar = (ProgressBar) upload.getProgressBar();
      bar.setText( "Upload cancelled!" );
      super.uploadCancelled( event );
    }

    private Component displayDownload( final UploadFinishEvent event )
    {
      final StringBuilder builder = new StringBuilder( 128 );
      builder.append( "Upload of file: <b>" );
      builder.append( event.getFileName() );
      builder.append( "</b> succeeded.  File size is: <i>");
      builder.append( event.getFileSize() / 1000 );
      builder.append( "</i> kilobytes." );

      final Row row = new Row();
      final File file = new File ( "/tmp/" + event.getFileName() );
      final DownloadButton button = new DownloadButton( file );
      ( (DownloadCallbackAdapter) button.getDownloadCallback() ).setLevel( Level.INFO );
      row.add( button );
      row.add( new Strut() );
      row.add( new DirectHtml( builder.toString() ) );

      return row;
    }

    private void displayError()
    {
      final StringBuilder builder = new StringBuilder( 128 );
      builder.append( "Upload " );
      if ( getEvent() != null )
      {
        builder.append( " of file: <b>" );
        builder.append( getEvent().getFileName() );
        builder.append( "</b>" );
      }

      builder.append( " failed/cancelled." );
      upload.getParent().add( new DirectHtml( builder.toString() ) );
    }
  }
}
