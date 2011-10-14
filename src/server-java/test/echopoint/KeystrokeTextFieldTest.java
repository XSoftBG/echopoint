package echopoint;

import static org.junit.Assert.assertEquals;
import nextapp.echo.app.Component;
import nextapp.echo.app.Label;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test suite for the {@link echopoint.KeystrokeTextFieldTest} component.
 *
 */
public class KeystrokeTextFieldTest extends AbstractTest<KeystrokeTextField>
{
  @BeforeClass
  public static void init()
  {
    set( new KeystrokeTextField(2000) );
  }

  @Test
  public void renderId()
  {
    final String renderId = "echopointUnitTestKeystrokeTextField";
    getComponent().setRenderId( renderId );
    assertEquals( "Ensure renderId set", renderId, getComponent().getRenderId() );
  }



  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();

    final Row row = new Row();
    row.add( createLabel() );
    row.add( new Strut() );
    row.add( get() );

    final Label label = createResponse();
    final KeystrokeTextField tf = (KeystrokeTextField) get();
    addListener( tf, label );

    content.add( new Strut() );
    content.add( row );
    content.add( new Strut() );
    content.add( createNote() );
    content.add( new Strut() );
    content.add( label );
  }

  private static Component createLabel()
  {
    final Label label = new Label();
    label.setRenderId( "echopointUnitTestKeystrokeTextFieldLabel" );
    label.setText( "Try entering characters" );

    return label;
  }

  private static Component createNote()
  {
    final HtmlLabel label =  new HtmlLabel();
    label.setRenderId( "echopointUnitTestKeystrokeTextFieldNote" );
    label.setText(
        "The label should show the content of the input field."+
        "As soon as you stop typing for longer than 2 seconds,"+
        "or when you press enter.");

    return label;
  }

  private static Label createResponse()
  {
    final Label label = new Label();
    label.setRenderId( "echopointUnitTestKeystrokeTextFieldResponse" );
    label.setText( "Label to echo characters entered in field" );

    return label;
  }

  private static void addListener( final KeystrokeTextField field, final Label label )
  {
    field.addActionListener( new ActionListener()
    {
      private static final long serialVersionUID = 1L;

      public void actionPerformed( final ActionEvent event )
      {
        label.setText( field.getText() );
      }
    });
  }
}
