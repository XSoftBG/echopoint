package echopoint;

import static org.junit.Assert.assertEquals;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Label;
import nextapp.echo.app.SelectField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for the {@link echopoint.PushButton} component.
 *
 * @author Rakesh Vidyadharan 2009-02-24
 * @version $Id: PushButtonTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class PushButtonTest extends AbstractTest<PushButton>
{
  static int count;
  static final String command = "actionCommand";

  @BeforeClass
  public static void init()
  {
    count = 0;
    set( new PushButton() );
  }

  @Test
  public void renderId()
  {
    final String renderId = "echopointUnitTestPushButton";
    getComponent().setRenderId( renderId );
    assertEquals( "Ensuring renderId set", renderId,
        getComponent().getRenderId() );
  }

  @Test
  public void actionCommand()
  {
    getComponent().setActionCommand( command );
    assertEquals( "Ensuring action command set", command,
        getComponent().getActionCommand() );
  }

  @Test
  public void width()
  {
    final int width = 500;
    getComponent().setWidth( new Extent( width ) );
    assertEquals( "Ensuring that width is set", width,
        getComponent().getWidth().getValue() );
  }

  @Test
  public void height()
  {
    final int height = 300;
    getComponent().setHeight( new Extent( height ) );
    assertEquals( "Ensuring that height is set", height,
        getComponent().getHeight().getValue() );
  }

  @Test
  public void text()
  {
    final String text = "Click Me!";
    getComponent().setText( text );
    assertEquals( "Ensuring that text is set", text, getComponent().getText() );
  }

  @Test
  public void toolTip()
  {
    final String text = "Click to test!";
    getComponent().setToolTipText( text );
    assertEquals( "Ensuring that tool tip text is set", text,
        getComponent().getToolTipText() );
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();
    final Label label = new Label( "Action command area" );

    ( (PushButton) get() ).addActionListener( new ActionListener() {
      private static final long serialVersionUID = 1l;

      public void actionPerformed( final ActionEvent event )
      {
        label.setText( "Button clicked: " + ++count );
        assertEquals( "Ensuring that action command is set",
            command, event.getActionCommand() );
      }
    });

    content.add( get() );
    content.add( createSelectField() );
    content.add( label );
  }

  private static SelectField createSelectField()
  {
    final String[] values = { "Enabled", "Disabled" };
    final SelectField select = new SelectField( values );
    select.addActionListener( new ActionListener()
    {
      public void actionPerformed( final ActionEvent event )
      {
        PushButton button = (PushButton) get();
        if ( 0 == select.getSelectedIndex() )
        {
          button.setEnabled( true );
        }
        else
        {
          button.setEnabled( false );
        }
      }
    });
    
    return select;
  }
}
