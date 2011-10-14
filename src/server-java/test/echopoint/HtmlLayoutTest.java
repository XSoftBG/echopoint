package echopoint;

import static org.junit.Assert.assertEquals;
import nextapp.echo.app.Button;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Label;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.layout.HtmlLayoutData;

/**
 * Unit test suite for the {@link HtmlLayout} container component.
 *
 * @author Rakesh Vidyadharan 2009-04-06
 * @version $Id: HtmlLayoutTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class HtmlLayoutTest extends AbstractTest<HtmlLayout>
{
  @BeforeClass
  public static void init() throws Exception
  {
    //set( new HtmlLayout( new FileInputStream( "/tmp/test.html" ), "UTF-8" ) );
    set( new HtmlLayout() );
  }

  @Test
  public void text()
  {
    final String text = "<table border='1'>" +
        "<tr>" +
        "<td id='one' colspan='2'>" +
        "</td>" +
        "</tr>" +
        "<tr>" +
        "<td id='two'>" +
        "</td>" +
        "<td id='three'>" +
        "</td>" +
        "</tr>" +
        "</table>";
    getComponent().setText( text );
    assertEquals( "Ensure text set", text, getComponent().getText() );
  }

  @Test
  public void one()
  {
    final Button child = new Button( "Button One" );
    final HtmlLayoutData layout = new HtmlLayoutData( "one" );
    child.setLayoutData( layout );
    getComponent().add( child );
  }

  @Test
  public void two()
  {
    final Label child = new Label( "Label One" );
    final HtmlLayoutData layout = new HtmlLayoutData( "two" );
    child.setLayoutData( layout );
    getComponent().add( child );
  }

  @Test
  public void three()
  {
    final Label child = new Label( "Label Two" );
    final HtmlLayoutData layout = new HtmlLayoutData( "three" );
    child.setLayoutData( layout );
    getComponent().add( child );
  }

  @Test
  public void width()
  {
    final Extent width = new Extent( 500 );
    getComponent().setWidth( width );
    assertEquals( "Ensure width set", width, getComponent().getWidth() );
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();
    content.add( get() );
  }
}
