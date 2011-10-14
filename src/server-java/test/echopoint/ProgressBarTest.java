package echopoint;

import static org.junit.Assert.assertEquals;
import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.TaskQueueHandle;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test suite for the {@link echopoint.ProgressBar} component.
 *
 * @author Rakesh Vidyadharan 2008-11-12
 * @version $Id: ProgressBarTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class ProgressBarTest extends AbstractTest<ProgressBar>
{
  @BeforeClass
  public static void init()
  {
    set( new ProgressBar() );
    ProgressBar bar = (ProgressBar) get();
    bar.setPercentage(45); // Set initial value to 45%
    bar.setText("Initial 45%");
  }

  @Test
  public void renderId()
  {
    final String renderId = "echopointUnitTestProgressBar";
    getComponent().setRenderId( renderId );
    assertEquals( "Ensuring renderId set", renderId, getComponent().getRenderId() );
  }

  @Test
  public void background()
  {
    final Color background = new Color( 0xcfcfcf );
    getComponent().setBackground( background );
    assertEquals( "Ensuring background set", background, getComponent().getBackground() );
  }

  @Test
  public void foreground()
  {
    final Color foreground = new Color( 0xffffff );
    getComponent().setForeground( foreground );
    assertEquals( "Ensuring foreground set", foreground, getComponent().getForeground() );
  }

  @Test
  public void barBackground()
  {
    final Color barBackground = new Color( 0x1a428a);
    getComponent().setBarBackground( barBackground );
    assertEquals( "Ensuring barBackground set", barBackground, getComponent().getBarBackground() );
  }

  @Test
  public void border()
  {
    final Border border = new Border( 1, Color.BLACK, Border.STYLE_INSET );
    getComponent().setBorder( border );
    assertEquals( "Ensuring border set", border, getComponent().getBorder() );
  }

  @Test
  public void insets()
  {
    final Insets insets = new Insets( 1 );
    getComponent().setInsets( insets );
    assertEquals( "Ensuring insets set", insets, getComponent().getInsets() );
  }

  @Test
  public void width()
  {
    final Extent width = new Extent( 500 );
    getComponent().setWidth( width );
    assertEquals( "Ensuring width set", width, getComponent().getWidth() );
  }

  @Test
  public void height()
  {
    final Extent height = new Extent( 25 );
    getComponent().setHeight( height );
    assertEquals( "Ensuring height set", height, getComponent().getHeight() );
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();
    content.add( get() );

    final Button button = new Button( "Start" );
    button.addActionListener( new Listener() );
    content.add( button );
  }

  private static class Listener implements ActionListener
  {
    private static final long serialVersionUID = 1l;

    public void actionPerformed( final ActionEvent event )
    {
      final Application app = Application.getApplication();
      final TaskQueueHandle handle = app.createTaskQueue();
      new Worker( app, handle ).start();
    }
  }

  private static class Worker extends Thread
  {
    private final Application app;
    private int percent = 0;
    private final TaskQueueHandle handle;

    Worker( final Application app, final TaskQueueHandle handle )
    {
      this.app = app;
      this.handle = handle;
    }

    public void run()
    {
      while ( percent < 100 )
      {
        percent += 10;
        app.enqueueTask( handle, new Runner( app, handle, percent ) );

        try
        {
          Thread.sleep( 250 );
        }
        catch ( Throwable t )
        {
          t.printStackTrace();
        }
      }
    }
  }

  private static class Runner implements Runnable
  {
    private final int percent;
    private final TaskQueueHandle handle;
    private final Application app;

    Runner( final Application app, final TaskQueueHandle handle, final int percent )
    {
      this.app = app;
      this.handle = handle;
      this.percent = percent;
    }

    public void run()
    {
      final ProgressBar bar = (ProgressBar) get();
      bar.setPercentage( percent );
      bar.setText( "Completed " + percent + " of 100" );

      if ( percent >= 100 ) app.removeTaskQueue( handle );
    }
  }
}
