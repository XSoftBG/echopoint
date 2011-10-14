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
 * Test case for the {@link echopoint.jquery.TooltipContainer} component.
 *
 * @author Hans Holmlund 2009-04-29
 * @version $Id: TooltipContainerTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class TooltipContainerTest extends AbstractTest<TooltipContainer> {

    @BeforeClass
    public static void init()
    {
        set( new TooltipContainer() );
    }

    @Test
    public void renderId()
    {
        final String renderId = "echopointUnitTestTooltipContainer";
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

    @Test
    public void tooltip()
    {
        final Extent width = new Extent( 250 );
        getComponent().setTooltip( "Lorem ipsum dolor" );
        assertEquals( "Ensuring tooltip set", width, getComponent().getWidth() );
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


        TooltipContainer testContainer = new TooltipContainer();
        testContainer.setWidth(new Extent(450));
        testContainer.setHeight(new Extent(150));
        testContainer.setBackground(new Color(218, 241, 166));
        testContainer.setAlignment(new Alignment(Alignment.RIGHT, Alignment.BOTTOM));
        Label text = new Label();
        text.setText("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        testContainer.add(text);
        testContainer.setInsets(new Insets(5,5,10,5));
        testContainer.setOutsets(new Insets(10,10,0,0));
        testContainer.setTooltipStyle("cream");
        testContainer.setToolTipText("Cicero's Finibus Bonorum et Malorum");
        testContainer.setPositionTarget(TooltipContainer.BOTTOMLEFT);
        cEx.add(testContainer);

        TooltipContainer testContainer2 = new TooltipContainer();
        testContainer2.setPosition(TooltipContainer.ABSOLUTE);
        testContainer2.setLeft(new Extent(380));
        testContainer2.setTop(new Extent(220));
        testContainer2.setBackground(new Color(203, 233, 245));
        testContainer2.setWidth(new Extent(300));
        testContainer2.setInsets(new Insets(5,5,5,5));
        testContainer2.setHeight(new Extent(70));
        testContainer2.setPositionTooltip(TooltipContainer.TOPMIDDLE);
        testContainer2.setPositionTarget(TooltipContainer.BOTTOMMIDDLE);
        testContainer2.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
        testContainer2.add(new Label("AIK is a football team from Stockholm. Last time they played in the UEFA Champions League was 1999."));
        testContainer2.setVideoURL("http://www.youtube.com/v/i_pcYd-9svs");
        testContainer2.setToolTipText("1999-09-14");
        testContainer2.setTooltipStyle("light");
        cEx.add(testContainer2);

        TooltipContainer testContainer4 = new TooltipContainer();
        testContainer4.setPosition(TooltipContainer.ABSOLUTE);
        testContainer4.setLeft(new Extent(10));
        testContainer4.setTop(new Extent(200));
        testContainer4.setBackground(new Color(250, 230, 251));
        testContainer4.setWidth(new Extent(200));
        testContainer4.setInsets(new Insets(5,5,5,5));
        testContainer4.setHeight(new Extent(70));
        testContainer4.setPositionTooltip(TooltipContainer.TOPMIDDLE);
        testContainer4.setPositionTarget(TooltipContainer.BOTTOMMIDDLE);
        testContainer4.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
        testContainer4.add(new Label("Lorem ipsum dolor sit amet, consectetur adipisicing elit"));
        testContainer4.setToolTipText("Lorem ipsum dolor");
        testContainer4.setTooltipStyle("light");
        cEx.add(testContainer4);

        TooltipContainer testContainer3 = new TooltipContainer();
        testContainer3.setPosition(TooltipContainer.ABSOLUTE);
        testContainer3.setLeft(new Extent(80));
        testContainer3.setTop(new Extent(330));
        testContainer3.setBackground(new Color(252, 252, 215));
        testContainer3.add(new Label("Amazon"));
        testContainer3.setWidth(new Extent(80));
        testContainer3.setHeight(new Extent(30));
        testContainer3.setInsets(new Insets(10,6,9,3));
        testContainer3.setOutsets(new Insets(10,6,9,3));
        testContainer3.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
        testContainer3.setThumbnailURL("www.amazon.com");
        cEx.add(testContainer3);
    }
}
