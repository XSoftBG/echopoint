package echopoint;

import static org.junit.Assert.assertEquals;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Label;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test suite for the {@link echopoint.NumberTextField} component.
 *
 * @author Rakesh Vidyadharan 2009-03-07
 * @version $Id: NumberTextFieldTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class NumberTextFieldTest extends AbstractTest<NumberTextField>
{
  @BeforeClass
  public static void init()
  {
    set( new NumberTextField() );
  }

  @Test
  public void renderId()
  {
    final String renderId = "echopointUnitTestNumberTextField";
    getComponent().setRenderId( renderId );
    assertEquals( "Ensure renderId set", renderId, getComponent().getRenderId() );
  }

  @Test
  public void width()
  {
    final Extent size = new Extent( 100 );
    getComponent().setWidth( size );
    assertEquals( "Ensure width set", size, getComponent().getWidth() );
  }

  @Test
  public void precision()
  {
    final int precision = 4;
    getComponent().setPrecision( precision );
    assertEquals( "Ensure precision set", precision, getComponent().getPrecision() );
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
    final NumberTextField tf = (NumberTextField) get();
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
    label.setRenderId( "echopointUnitTestNumberTextFieldLabel" );
    label.setText( "Try entering characters" );

    return label;
  }

  private static Component createNote()
  {
    final HtmlLabel label =  new HtmlLabel();
    label.setRenderId( "echopointUnitTestNumberTextFieldNote" );
    label.setText(
        "Note that the field will allow only the number characters " +
      "0-9 and the period (.).  Also note that only one period " +
      "character is allowed by the field." );

    return label;
  }

  private static Label createResponse()
  {
    final Label label = new Label();
    label.setRenderId( "echopointUnitTestNumberTextFieldResponse" );
    label.setText( "Label to echo characters entered in field" );

    return label;
  }

  private static void addListener( final NumberTextField field, final Label label )
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
