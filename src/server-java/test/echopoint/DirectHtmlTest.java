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

package echopoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Label;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.extras.app.RichTextArea;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test case for the {@link DirectHtml} component.  Exercises getting and
 * setting the display text.
 *
 * @author Rakesh 2008-06-25
 * @version $Id: DirectHtmlTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class DirectHtmlTest extends AbstractTest<DirectHtml>
{
  /** The simple text to display in the test component. */
  private static final String SIMPLE_TEXT =
      "<br/><br/><b>DirectHtml</b> simple test content.<br/><br/>";

  /** The complex text to display in the test component. */
  private static final String COMPLEX_TEXT =
      "<h2>DirectHtml Without Target</h2>" +
          "<p><b>First</b> paragraph content.</p>" +
          "<p><b>Second</b> paragraph content.</p>" +
          "<ol><li><b>First</b> line.</li>" +
          "<li><b>Second</b> line.</li>" +
          "<li><b>Third</b> line.</li></ol>" +
          "<p>Clicking <a href='http://wiki.nextapp.com/echowiki/EchoPoint'>link</a>" +
          " should open in the same browser window/tab.</p>";

  /** The text with links to test {@link DirectHtml#setTarget} manually. */
  private static final String LINK_TEXT =
      "<h2>DirectHtml With Target Set</h2>" +
          "<p>Clicking <a href='http://wiki.nextapp.com/echowiki/EchoPoint'>link</a>" +
          " should open in a new browser window/tab.</p>" +
          "<p>Clicking <a href='http://wiki.nextapp.com/echowiki/EchoPoint' target=''>link</a>" +
          " should open in the same browser window/tab.</p>";

  @BeforeClass
  public static void init()
  {
    final Component content = Application.getContent().getTestArea();
    final DirectHtml comp = new DirectHtml();
    comp.setRenderId( "echopointUnitTestDirectHtml" );
    content.removeAll();
    content.add( comp );

    assertEquals( "Ensuring test component added to container",
        content.getComponentCount(), 1 );
    set( comp );
  }

  @Test
  public void simple()
  {
    getComponent().setText( SIMPLE_TEXT );

    assertEquals( "Ensuring getText returns simple",
        getComponent().getText(), SIMPLE_TEXT );
  }

  @Test
  public void complex()
  {
    getComponent().setText( COMPLEX_TEXT );

    assertEquals( "Ensuring getText returns complex",
        getComponent().getText(), COMPLEX_TEXT );
  }

  @Test
  public void add()
  {
    try
    {
      getComponent().add( new Label( "Test label" ) );
      fail( "Label added to DirectHtml" );
    }
    catch ( Throwable t ) {}
  }

  @Test
  public void append()
  {
    getComponent().setText( null );
    assertNull( "Ensuring getText returns null", getComponent().getText() );

    getComponent().append( SIMPLE_TEXT );
    getComponent().append( COMPLEX_TEXT );
    assertEquals( "Ensuring append returns simple + complex",
        getComponent().getText(), SIMPLE_TEXT + COMPLEX_TEXT );
  }

  @Test
  public void target()
  {
    final String target = "_new";
    getComponent().setTarget( target );
    assertEquals( "Ensuring target returned is same",
        getComponent().getTarget(), target );
  }

  /**
   * Display an RTA and display button to let user interact with the
   * {@link DirectHtml} component.
   *
   * <p>RTA disabled for the moment due to some issues with loading the RTA.</p>
   */
  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();

    final RichTextArea rta = new RichTextArea();
    rta.setRenderId( "echopointUnitTestDirectHtmlRTA" );
    rta.setText( COMPLEX_TEXT );
    content.add( rta );

    final Border border = new Border( new Extent( 2 ),
        new Color( 0xcfdfff ), Border.STYLE_GROOVE );
    final Extent height = new Extent( 100 );
    final Extent width = new Extent( 500 );
    final DirectHtml complex = new DirectHtml( COMPLEX_TEXT );
    complex.setRenderId( "echopointUnitTestDirectHtmlNoTarget" );
    complex.setBorder( border );
    complex.setHeight( height );
    complex.setWidth( width );
    set( complex );

    final Row row = new Row();
    row.add( createButton( rta ) );
    content.add( row );
    content.add( complex );

    final DirectHtml simple = new DirectHtml( LINK_TEXT, "_new" );
    simple.setRenderId( "echopointUnitTestDirectHtmlTarget" );
    simple.setBorder( border );
    simple.setHeight( height );
    simple.setWidth( width );
    content.add( simple );
  }

  /**
   * Create the control button that copies the text from the RTA to the HTML
   * component.
   *
   * @param rta The RTA to be passed in to the action listener for the button.
   * @return The properly initialised button component.
   */
  private static Button createButton( final RichTextArea rta )
  {
    final Button button = new Button( "Display" );
    button.setRenderId( "echopointUnitTestDirectHtmlDisplay" );

    final Color color = Color.BLACK;
    final Border border = new Border( 1, color, Border.STYLE_OUTSET );
    button.setBorder( border );

    final Border pressed = new Border( 1, color, Border.STYLE_INSET );
    button.setPressedBorder( pressed );

    button.addActionListener( new Listener( rta, (DirectHtml) get() ) );
    return button;
  }

  /**
   * The action listener that copies the contents of the RTA to the HTML
   * component.
   */
  static class Listener implements ActionListener
  {
    private static final long serialVersionUID = 1l;

    final RichTextArea rta;
    final DirectHtml directHtml;

    Listener( final RichTextArea rta, final DirectHtml directHtml )
    {
      this.rta = rta;
      this.directHtml = directHtml;
    }

    public void actionPerformed( final ActionEvent event )
    {
      directHtml.setText( rta.getText() );
    }
  }
}
