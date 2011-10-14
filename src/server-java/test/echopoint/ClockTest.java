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

import echopoint.jquery.Clock;

/**
 * Test case for the {@link Clock} component.
 *
 * @author Hans Holmlund 2009-04-29
 * @version $Id: ClockTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class ClockTest extends AbstractTest<Clock>
{
    @BeforeClass
    public static void init()
    {
        set( new Clock() );
    }

    @Test
    public void renderId()
    {
        final String renderId = "echopointUnitTestClock";
        getComponent().setRenderId( renderId );
        assertEquals( "Ensuring renderId set", renderId, getComponent().getRenderId() );
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
        final Font font = new Font( Font.HELVETICA, Font.BOLD, new Extent( 12 ) );
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
