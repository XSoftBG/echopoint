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
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.google.chart.Map;
import echopoint.google.chart.model.ChartData;
import echopoint.google.chart.model.Title;

/**
 * Unit test suite for the {@link echopoint.google.chart.Map} component
 * wrapper.
 *
 * @author Rakesh Vidyadharan 2008-08-25
 * @version $Id: MapTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class MapTest extends GoogleChartTest<Map>
{
  @BeforeClass
  public static void init()
  {
    set( new Map() );
    setData( new ChartData<Integer>() );
  }

  @Test
  public void renderId()
  {
    final String id = "echopointUnitTestSimpleMap";
    getComponent().setRenderId( id );
    assertEquals( "Ensuring render id is same", id, getComponent().getRenderId() );
  }

  @Test
  public void region()
  {
    getComponent().setGeographicalArea( Map.Regions.africa );
    assertEquals( "Ensuring region set", getComponent().getGeographicalArea(),
        Map.Regions.africa );
  }

  @Test
  public void data()
  {
    final Integer[] array = new Integer[] { 0, 5, 9 };
    getData().setXdata( Arrays.asList( array ) );
  }

  @Test
  public void codes()
  {
    final String codes = "MGKETN";
    getComponent().setCodes( codes );
    assertEquals( "Ensuring codes set", codes, getComponent().getCodes() );
  }

  @Test
  public void color()
  {
    final String color = "00ff00";
    getData().setColor( color );
    assertEquals( "Ensuring color set", color, getData().getColor() );
  }

  @Test
  public void fill()
  {
    final String fill = "bg,s,efefef|c,lg,45,ffffff,0,76a4fb,0.75";
    getComponent().setFill( fill );
    assertEquals( "Ensuring fill set", fill, getComponent().getFill() );
  }

  @Test
  public void title()
  {
    final Title title = new Title();
    title.add( "Map" );
    getComponent().setTitle( title );
    assertEquals( "Ensuring title set", title, getComponent().getTitle() );
  }

  @Test
  public void height()
  {
    getComponent().setHeight( new Extent( 200 ) );
    assertNotNull( "Ensuring height set", getComponent().getHeight() );
  }

  @Test
  public void width()
  {
    getComponent().setWidth( new Extent( 400 ) );
    assertNotNull( "Ensuring width set", getComponent().getWidth() );
  }

  @Test
  public void insets()
  {
    getComponent().setInsets( new Insets( 10 ) );
    assertNotNull( "Ensuring insets set", getComponent().getInsets() );
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();

    final Map map = (Map) get();

    final ArrayList<ChartData<Integer>> collection = new ArrayList<ChartData<Integer>>();
    collection.add( getData() );
    map.setData( collection );
    assertNotNull( "Ensuring that data is set", map.getData() );

    content.add( map );
  }
}