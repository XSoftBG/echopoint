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
import nextapp.echo.app.Label;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.jquery.TooltipContainer;

/**
 * Test case for the {@link echopoint.ContainerEx} component.
 *
 * @author Hans Holmlund 2009-05-14
 * @version $Id: ContainerExTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class ContainerExTest extends AbstractTest<ContainerEx> {

    @BeforeClass
    public static void init()
    {
        set( new ContainerEx() );
    }

    @Test
    public void renderId()
    {
        final String renderId = "echopointUnitTestContainerEx";
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
        ContainerEx cEx = new ContainerEx();
        content.add( cEx );

        cEx.setWidth(new Extent(600));
        cEx.setHeightStretched(true);


        ContainerEx testContainer = new ContainerEx();
        testContainer.setWidth(new Extent(450));
        testContainer.setHeight(new Extent(160));
        testContainer.setBackground(new Color(218, 241, 166));
        testContainer.setAlignment(new Alignment(Alignment.RIGHT, Alignment.BOTTOM));
        Label text = new Label();
        text.setText("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        testContainer.add(text);
        testContainer.setInsets(new Insets(5,5,10,5));
        testContainer.setOutsets(new Insets(10,10,0,0));
        cEx.add(testContainer);

        ContainerEx testContainer2 = new ContainerEx();
        testContainer2.setPosition(TooltipContainer.ABSOLUTE);
        testContainer2.setLeft(new Extent(300));
        testContainer2.setTop(new Extent(210));
        testContainer2.setBackground(new Color(203, 233, 245));
        testContainer2.setWidth(new Extent(300));
        testContainer2.setInsets(new Insets(5,5,5,5));
        testContainer2.setHeight(new Extent(70));
        testContainer2.add(new Label("Absolute positioned container: left=300, top=210"));
        cEx.add(testContainer2);

    }
}
