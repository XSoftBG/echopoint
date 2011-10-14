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

import java.util.ArrayList;
import java.util.Collection;

import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Label;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.model.CircleSection;
import echopoint.model.Cursor;
import echopoint.model.MapSection;
import echopoint.model.Point;
import echopoint.model.PolygonSection;
import echopoint.model.RectangleSection;

/**
 * Unit test suite for the {@link echopoint.ImageMap} component.
 *
 * @author Rakesh 2008-10-19
 * @version $Id: ImageMapTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class ImageMapTest extends AbstractTest<ImageMap>
{

  @BeforeClass
  public static void init()
  {
    set( new ImageMap( "image/imagemap.gif" ) );
  }

  @Test
  public void renderId()
  {
    final String renderId = "echopointUnitTestImageMap";
    getComponent().setRenderId( renderId );
    assertEquals( "Ensuring renderId set", renderId,
        getComponent().getRenderId() );
  }

  @Test
  public void cursor()
  {
    final Cursor cursor = Cursor.crosshair;
    getComponent().setCursor( cursor );
    assertEquals( "Ensuring cursor set", cursor, getComponent().getCursor() );
  }

  @Test
  public void text()
  {
    final String text = "Image Map";
    getComponent().setText( text );
    assertEquals( "Ensuring text set", text, getComponent().getText() );
  }

  @Test
  public void toolTipText()
  {
    final String text = "Image Map";
    getComponent().setToolTipText( text );
    assertEquals( "Ensuring tool-tip text set", text, getComponent().getToolTipText() );
  }

  @Test
  public void width()
  {
    final int width = 500;
    getComponent().setWidth( new Extent( width ) );
    assertEquals( "Ensuring that width is set", width,
        getComponent().getWidth().getValue() );
  }

  @Test
  public void height()
  {
    final int height = 300;
    getComponent().setHeight( new Extent( height ) );
    assertEquals( "Ensuring that height is set", height,
        getComponent().getHeight().getValue() );
  }

  @Test
  public void sections()
  {
    final Collection<MapSection> sections = new ArrayList<MapSection>( 3 );

    final CircleSection circle = new CircleSection(
        new Point( 70, 84 ), 51, "circle", "Circular section" );
    sections.add( circle );

    final RectangleSection rectangle = new RectangleSection(
        new Point( 25, 180 ), new Point( 125, 280 ),
        "rectangle", "Rectangular section" );
    sections.add( rectangle );
    getComponent().addSections( sections );
    assertEquals( "Ensuring circle and rectangle added", sections.size(),
        getComponent().getSections().size() );

    final int[] vertices = { 153, 106, 186, 225, 340, 193, 315, 81, 304, 167 };
    final PolygonSection polygon = new PolygonSection( vertices,
        "polygon", "Polygon section" );
    sections.add( polygon );
    getComponent().addSection( polygon );
    assertEquals( "Ensuring polygon added", sections.size(),
        getComponent().getSections().size() );
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();

    final Label label = new Label( "Action command area" );
    ( (ImageMap) get() ).addActionListener( new ActionListener() {
      public void actionPerformed( final ActionEvent event )
      {
        if ( "circle".equals( event.getActionCommand() ) )
        {
          label.setText( "Circular section" );
        }
        else if ( "rectangle".equals( event.getActionCommand() ) )
        {
          label.setText( "Rectangular section" );
        }
        else if ( "polygon".equals( event.getActionCommand() ) )
        {
          label.setText( "Polygon section" );
        }
      }
    });

    content.add( get() );
    content.add( label );
  }
}
