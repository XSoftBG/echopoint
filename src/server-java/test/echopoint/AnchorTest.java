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
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Font;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test suite for the {@link echopoint.Anchor} getComponent().
 *
 * @author Rakesh Vidyadharan 2008-10-24
 * @version $Id: AnchorTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class AnchorTest extends AbstractTest<Anchor>
{
  @BeforeClass
  public static void init()
  {
    set( new Anchor() );
  }

  @Test
  public void renderId()
  {
    final String renderId = "echopointUnitTestAnchor";
    getComponent().setRenderId( renderId );
    assertEquals( "Ensuring renderId set", renderId, getComponent().getRenderId() );
  }

  @Test
  public void uri()
  {
    final String uri = "https://echopoint.dev.java.net/";
    getComponent().setUri( uri );
    assertEquals( "Ensuring uri set", uri, getComponent().getUri() );
  }

  @Test
  public void foreground()
  {
    final Color color = new Color( 0x2f2f4f );
    getComponent().setForeground( color );
    assertEquals( "Ensuring colour set", color, getComponent().getForeground() );
  }

  @Test
  public void font()
  {
    final Font font = new Font( Font.HELVETICA, Font.BOLD, new Extent( 10 ) );
    getComponent().setFont( font );
    assertEquals( "Ensuring font set", font, getComponent().getFont() );
  }

  @Test
  public void target()
  {
    final Anchor.Target target = Anchor.Target._blank;
    getComponent().setTarget( target );
    assertEquals( "Ensuring target set", target.toString(),
        getComponent().getTarget() );
  }

  @Test
  public void text()
  {
    final String text = "A HTML Anchor tag";
    getComponent().setText( text );
    assertEquals( "Ensuring text set", text, getComponent().getText() );
  }

  @Test
  public void toolTip()
  {
    final String tooltip = "Click the link";
    getComponent().setToolTipText( tooltip );
    assertEquals( "Ensuring tool tip set", tooltip, getComponent().getToolTipText() );
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();
    content.add( get() );
  }
}
