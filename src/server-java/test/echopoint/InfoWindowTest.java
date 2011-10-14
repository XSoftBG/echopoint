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
import nextapp.echo.app.Alignment;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Font;
import nextapp.echo.app.Insets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test suite for the {@link echopoint.InfoWindow} component.
 *
 * @author Rakesh Vidyadharan 2008-10-25
 * @version $Id: InfoWindowTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class InfoWindowTest extends AbstractTest<InfoWindow>
{
  @BeforeClass
  public static void init()
  {
    set( new InfoWindow() );
  }

  @Test
  public void renderId()
  {
    final String renderId = "echopointUnitTestInfoWindow";
    getComponent().setRenderId( renderId );
    assertEquals( "Ensuring renderId set", renderId, getComponent().getRenderId() );
  }

  @Test
  public void content()
  {
    final String  content = "Donec risus purus, elementum ut, porttitor sed, tincidunt at, neque. " +
        "Sed gravida turpis ac neque. Nulla pede lorem, imperdiet bibendum, " +
        "varius id, adipiscing ut, metus. Nulla tincidunt vehicula ipsum. " +
        "Vestibulum a eros. Phasellus urna. Sed tempus, lectus eu vulputate tristique";
    getComponent().setContent( content );
    assertEquals( "Ensuring content set", content, getComponent().getContent() );
  }

  @Test
  public void title()
  {
    final String title = "Header";
    getComponent().setTitle( title );
    assertEquals( "Ensuring title set", title, getComponent().getTitle() );
  }

  @Test
  public void titleAlignment()
  {
    final Alignment alignment = Alignment.ALIGN_LEFT;
    getComponent().setTitleAlignment( alignment );
    assertEquals( "Ensuring titleAlignment set", alignment, getComponent().getTitleAlignment() );
  }

  @Test
  public void titleForeground()
  {
    final Color color = new Color( 0xffffff );
    getComponent().setTitleForeground( color );
    assertEquals( "Ensuring titleForeground set", color, getComponent().getTitleForeground() );
  }

  @Test
  public void titleBackground()
  {
    final Color color = new Color( 0x9f9f9f );
    getComponent().setTitleBackground( color );
    assertEquals( "Ensuring titleBackground set", color, getComponent().getTitleBackground() );
  }

  @Test
  public void titleFont()
  {
    final Font font = new Font( Font.HELVETICA, Font.BOLD, new Extent( 12 ) );
    getComponent().setTitleFont( font );
    assertEquals( "Ensuring titleFont set", font, getComponent().getTitleFont() );
  }

  @Test
  public void titleInsets()
  {
    final Insets insets = new Insets( 5 );
    getComponent().setTitleInsets( insets );
    assertEquals( "Ensuring titleInsets set", insets, getComponent().getTitleInsets() );
  }

  @Test
  public void prefix()
  {
    final String prefix = "Integer in eros nec arcu blandit adipiscing. Phasellus tempor ligula et odio. Aliquam " +
        "erat volutpat. Proin sed nisi. Phasellus pretium, ipsum ornare pellentesque pellentesque, " +
        "quam enim consequat lectus, non consectetuer tortor risus quis neque. Nam eget odio. " +
        "Cras gravida ipsum eu lectus. Duis vitae tortor in ante tincidunt volutpat. Maecenas " +
        "interdum mollis mi. Proin feugiat purus sed elit. Ut in augue. Duis pede lectus, " +
        "volutpat non, ullamcorper id, tincidunt sed, libero. Pellentesque luctus ligula vel " +
        "dolor. Integer elementum lorem in ligula. Aliquam id dolor a leo laoreet egestas. In " +
        "nec nibh porta eros consequat condimentum. " +
        "Nam elementum magna vitae justo. Nam velit est, vestibulum a, " +
        "aliquam non, sollicitudin vitae, risus. " +
        "Curabitur interdum dictum sapien. Nulla faucibus tellus vel erat. Aliquam posuere " +
        "mi et dolor faucibus gravida. In sit amet lorem at mi tempus egestas. Phasellus " +
        "rhoncus erat id est. nibh, rutrum ac, imperdiet ac, elementum nec, ";
    getComponent().setPrefix( prefix );
    assertEquals( "Ensuring prefix set", prefix, getComponent().getPrefix() );
  }

  @Test
  public void text()
  {
    final String text = "Hover over here";
    getComponent().setText( text );
    assertEquals( "Ensuring text set", text, getComponent().getText() );
  }

  @Test
  public void postfix()
  {
    final String postfix = "orci. Proin a ipsum vitae dui luctus congue. Nullam viverra iaculis nulla. Duis " +
        "sagittis eros eu quam. Nam elementum magna vitae justo. Nam velit est, vestibulum a, " +
        "aliquam non, sollicitudin vitae, risus.";
    getComponent().setPostfix( postfix );
    assertEquals( "Ensuring postfix set", postfix, getComponent().getPostfix() );
  }

  @Test
  public void textForeground()
  {
    final Color color = new Color( 0x2f2f4f );
    getComponent().setTextForeground( color );
    assertEquals( "Ensuring textForeground set", color, getComponent().getTextForeground() );
  }

  @Test
  public void textBackground()
  {
    final Color color = new Color( 0xcfdfff );
    getComponent().setTextBackground( color );
    assertEquals( "Ensuring textBackground set", color, getComponent().getTextBackground() );
  }

  @Test
  public void textFont()
  {
    final Font font = new Font( Font.HELVETICA, Font.BOLD, new Extent( 12 ) );
    getComponent().setTextFont( font );
    assertEquals( "Ensuring textFont set", font, getComponent().getTextFont() );
  }

  @Test
  public void textInsets()
  {
    final Insets insets = new Insets( 3, 8 );
    getComponent().setTextInsets( insets );
    assertEquals( "Ensuring textInsets set", insets, getComponent().getTextInsets() );
  }

  @Test
  public void otherTextForeground()
  {
    final Color color = new Color( 0 );
    getComponent().setOtherTextForeground( color );
    assertEquals( "Ensuring otherTextForeground set", color, getComponent().getOtherTextForeground() );
  }

  @Test
  public void otherTextFont()
  {
    final Font font = new Font( Font.HELVETICA, Font.PLAIN, new Extent( 11 ) );
    getComponent().setOtherTextFont( font );
    assertEquals( "Ensuring otherTextFont set", font, getComponent().getOtherTextFont() );
  }

  @Test
  public void otherTextInsets()
  {
    final Insets insets = new Insets( 2, 5 );
    getComponent().setOtherTextInsets( insets );
    assertEquals( "Ensuring otherTextInsets set", insets, getComponent().getOtherTextInsets() );
  }

  @Test
  public void alignment()
  {
    final Alignment alignment = Alignment.ALIGN_CENTER;
    getComponent().setAlignment( alignment );
    assertEquals( "Ensuring alignment set", alignment, getComponent().getAlignment() );
  }

  @Test
  public void insets()
  {
    final Insets insets = new Insets( 10 );
    getComponent().setInsets( insets );
    assertEquals( "Ensuring insets set", insets, getComponent().getInsets() );
  }

  @Test
  public void font()
  {
    final Font font = new Font( Font.HELVETICA, Font.PLAIN, new Extent( 11 ) );
    getComponent().setFont( font );
    assertEquals( "Ensuring Font set", font, getComponent().getFont() );
  }

  @Test
  public void foreground()
  {
    final Color color = new Color( 0x2f2f4f );
    getComponent().setForeground( color );
    assertEquals( "Ensuring Foreground set", color, getComponent().getForeground() );
  }

  @Test
  public void width()
  {
    final Extent width = new Extent( 250 );
    getComponent().setWidth( width );
    assertEquals( "Ensuring width set", width, getComponent().getWidth() );
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();
    content.add( get() );
  }
}
