package echopoint;


import static org.junit.Assert.assertEquals;
import nextapp.echo.app.Component;
import nextapp.echo.app.Label;
import nextapp.echo.app.Row;
import nextapp.echo.app.Extent;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test suite for the {@link TextArea} component.
 *
 * @author Andre Schild
 * @version $Id: TextAreaTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class TextAreaTest extends AbstractTest<TextArea>
{
  @BeforeClass
  public static void init()
  {
      TextArea tA= new TextArea("Initial Text", 40, 10);
      tA.setAutoResize(true);
      tA.setWrap(false);
      set( tA );
  }

  @Test
  public void renderId()
  {
    final String renderId = "echopointUnitTestTextArea";
    getComponent().setRenderId( renderId );
    assertEquals( "Ensure renderId set", renderId, getComponent().getRenderId() );
  }

  @Test
  public void defaultText()
  {
    final String text = "Abcdef";
    getComponent().setDefaultText( text );
    assertEquals( "Ensure default text set", text, getComponent().getDefaultText() );
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
    final TextArea tf = (TextArea) get();
    addListener( tf, label );

    content.add( new Strut() );
    content.add( row );
    content.add( new Strut() );
    content.add( createNote() );
    content.add( new Strut() );
    content.add( label );
    content.add( new Strut() );
    final TextArea tf2 = new TextArea();
    tf2.setAutoResize(true);
    tf2.setWrap(true);
    tf2.setWidth(new Extent(40, Extent.EM));
    content.add(tf2);
    content.add( new Label("Textarea with autoResize = ON and wrap = on") );
    final TextArea tf3 = new TextArea();
    tf3.setAutoResize(false);
    tf3.setWrap(true);
    tf3.setWidth(new Extent(40, Extent.EM));
    content.add(tf3);
    content.add( new Label("Textarea with autoResize = OFF and wrap = on") );
  }

  private static Component createLabel()
  {
    final Label label = new Label();
    label.setRenderId( "echopointUnitTestTextAreaLabel" );
    label.setText( "Try entering characters" );

    return label;
  }

  private static Component createNote()
  {
    final HtmlLabel label =  new HtmlLabel();
    label.setRenderId( "echopointUnitTestTextAreaNote" );
    label.setText(
        "The text area should expand as new lines are entered." );

    return label;
  }

  private static Label createResponse()
  {
    final Label label = new Label();
    label.setRenderId( "echopointUnitTestTextAreaResponse" );
    label.setText( "Label to echo characters entered in field."+
            "All newlines are replaced by a \u00b0 character"
            );

    return label;
  }

  private static void addListener( final TextArea field, final Label label )
  {
    field.addActionListener( new ActionListener()
    {
      private static final long serialVersionUID = 1L;

      public void actionPerformed( final ActionEvent event )
      {
          String taText= field.getText();
          label.setText(taText.replace('\n', '\u00b0'));
      }
    });
  }
}