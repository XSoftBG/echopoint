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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.google.chart.PieChart;
import echopoint.google.chart.model.ChartData;
import echopoint.google.chart.model.Title;

/**
 * Unit test suite for the {@link echopoint.google.chart.PieChart} component
 * wrapper.
 *
 * @author Rakesh Vidyadharan 2008-08-20
 * @version $Id: PieChartTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class PieChartTest extends GoogleChartTest<PieChart<Integer>>
{
  @BeforeClass
  public static void init()
  {
    set( new PieChart<Integer>() );
    setData( new ChartData<Integer>() );
  }

  @Test
  public void dimensions()
  {
    getComponent().setDimensions( PieChart.Dimensions.p3 );
    assertEquals( "Ensuring chart type set", getComponent().getDimensions(),
        PieChart.Dimensions.p3 );
  }

  @Test
  public void renderId()
  {
    final String id = "echopointUnitTestSimplePieChart";
    getComponent().setRenderId( id );
    assertEquals( "Ensuring render id is same", id, getComponent().getRenderId() );
  }

  @Test
  public void data()
  {
    final Integer[] array = new Integer[]
        { 31, 28, 31, 30, 31, 31, 31, 31, 30, 31, 30, 31 };
    getData().setXdata( Arrays.asList( array ) );
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
    title.add( "PieChart" );
    getComponent().setTitle( title );
    assertEquals( "Ensuring title set", title, getComponent().getTitle() );
  }

  @Test
  public void labels()
  {
    final String[] labels = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
      "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    getComponent().setLabels( Arrays.asList( labels ) );
    assertNotNull( "Ensuring labels set", getComponent().getLabels() );
  }

  @Test
  public void height()
  {
    getComponent().setHeight( new Extent( 400 ) );
    assertNotNull( "Ensuring height set", getComponent().getHeight() );
  }

  @Test
  public void width()
  {
    getComponent().setWidth( new Extent( 650 ) );
    assertNotNull( "Ensuring width set", getComponent().getWidth() );
  }

  @AfterClass
  @SuppressWarnings( {"unchecked"} )
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();

    final PieChart<Integer> chart = (PieChart<Integer>) get();
    final ArrayList<ChartData<Integer>> collection = new ArrayList<ChartData<Integer>>();
    collection.add( getData() );
    chart.setData( collection );
    assertNotNull( "Ensuring that data is set", chart.getData() );

    content.add( chart );
  }
}