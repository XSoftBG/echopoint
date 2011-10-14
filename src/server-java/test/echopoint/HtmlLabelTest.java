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
import nextapp.echo.app.Color;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Label;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test case for the {@link HtmlLabel} component.  Exercises getting and
 * setting the display text.
 *
 * @author Rakesh 2008-06-25
 * @version $Id: HtmlLabelTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class HtmlLabelTest extends AbstractTest<HtmlLabel>
{
  /** The simple text to display in the test component. */
  private static final String SIMPLE_TEXT =
      "<br/><br/><b>HtmlLabel</b> simple test content.<br/><br/>";

  @BeforeClass
  public static void init()
  {
    final Border border = new Border( new Extent( 2 ),
        new Color( 0xcfdfff ), Border.STYLE_GROOVE );

    final MainContent content = Application.getContent();
    final HtmlLabel comp = new HtmlLabel();
    comp.setRenderId( "echoPointUnitTestHtmlLabel" );
    comp.setBorder( border );
    content.getTestArea().removeAll();
    content.getTestArea().add( comp );

    assertEquals( "Ensuring test component added to container",
        content.getTestArea().getComponentCount(), 1 );
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

    final String modification = "modified";
    getComponent().append( SIMPLE_TEXT );
    getComponent().append( modification );

    assertEquals( "Ensuring getText returns simple + modification",
        getComponent().getText(), SIMPLE_TEXT + modification );
  }
}
