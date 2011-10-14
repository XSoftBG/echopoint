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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import nextapp.echo.app.Component;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.google.chart.Sparkline;
import echopoint.google.chart.model.ChartData;
import echopoint.google.chart.model.LineStyle;
import echopoint.google.chart.model.ShapeMarker;
import echopoint.google.chart.model.Title;

/**
 * Unit test suite for the {@link echopoint.google.chart.Sparkline} component
 * wrapper.
 *
 * @author Rakesh Vidyadharan 2008-08-20
 * @version $Id: SparklineTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class SparklineTest extends GoogleChartTest<Sparkline<Integer>>
{
  @BeforeClass
  public static void init()
  {
    set( new Sparkline<Integer>() );
    setData( new ChartData<Integer>() );
  }

  @Test
  public void renderId()
  {
    final String id = "echopointUnitTestSimpleSparkline";
    getComponent().setRenderId( id );
    assertEquals( "Ensuring render id is same", id, getComponent().getRenderId() );
  }

  @Test
  public void data()
  {
    final Integer[] array = new Integer[] { 30,60,70,90,95,110 };
    final List<Integer> xdata = Arrays.asList( array );
    final int xmax = 120;

    getData().setXdata( xdata );
    getData().setXmax( xmax );
  }

  @Test
  public void markers()
  {
    final ArrayList<ShapeMarker> markers = new ArrayList<ShapeMarker>();
    markers.add( new ShapeMarker( "o", "ff3333", 5 ) );
    getData().setMarkers( markers );

    assertFalse( "Ensuring markers set", getData().getMarkers().isEmpty() );
  }

  @Test
  public void color()
  {
    final String color = "00ff00";
    getData().setColor( color );
    assertEquals( "Ensuring color set", color, getData().getColor() );
  }

  @Test
  public void title()
  {
    final Title title = new Title();
    title.add( "Sparkline" );
    getComponent().setTitle( title );

    assertEquals( "Ensuring title set", title, getComponent().getTitle() );
  }

  @Test
  public void lineStyles()
  {
    final Collection<LineStyle> styles = new ArrayList<LineStyle>();
    styles.add( new LineStyle( 3, 6, 3 ) );

    getComponent().setLineStyles( styles );
    assertNotNull( "Ensuring line styles set", getComponent().getLineStyles() );
  }

  @AfterClass
  @SuppressWarnings( {"unchecked"} )
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();

    final Sparkline<Integer> chart = (Sparkline<Integer>) get();
    final ArrayList<ChartData<Integer>> collection = new ArrayList<ChartData<Integer>>();
    collection.add( getData() );
    chart.setData( collection );
    assertNotNull( "Ensuring that data is set", chart.getData() );

    content.add( chart );
  }
}