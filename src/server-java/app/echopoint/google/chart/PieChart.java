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

package echopoint.google.chart;

import java.util.Collection;

import echopoint.google.chart.internal.SimpleChart;

/**
 * Component wrapper for a
 * <a href='http://code.google.com/apis/chart/#pie_charts'>Pie chart</a>
 * provided by <a href='http://code.google.com/apis/chart/'>Google Chart
 * API</a>.
 *
 * <p>The following code shows sample use of this component:</p>
 * <pre>
 *   import echopoint.google.chart.PieChart;
 *   import echopoint.google.chart.model.ChartData;
 *
 *     ...
 *     final ChartData&lt;Integer&gt; data = new ChartData&lt;Integer&gt;();
 *     final Integer[] array = new Integer[]
 *        { 31, 28, 31, 30, 31, 31, 31, 31, 30, 31, 30, 31 };
 *     data.setXdata( Arrays.asList( array ) );
 *
 *     final PieChart&lt;Integer&gt; chart = new PieChart&lt;Integer&gt;();
 *     chart.setDimensions( PieChart.Dimensions.p3 );
 *     final ArrayList&lt;ChartData&lt;Integer&gt;&gt; collection = new ArrayList&lt;ChartData&lt;Integer&gt;&gt;();
 *     collection.add( data );
 *     chart.setData( collection );
 * </pre>
 *
 * @author Rakesh Vidyadharan 2008-08-21
 * @version $Id: PieChart.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class PieChart<N extends Number> extends SimpleChart<N>
{
  private static final long serialVersionUID = 1l;

  /** An enumeration used to represent the pie chart dimensions. */
  public enum Dimensions { p, p3 }

  /**
   * The property used to configure 2 or 3-dimensional charts.  This
   * property is best styled.  Defaults to 2-d.
   */
  public static final String PROPERTY_DIMENSIONS = "dimensions";

  /**
   * The property that holds the collection of string labels to associate with
   * the segments in the pie chart.  Please see
   * <a href='http://code.google.com/apis/chart/#pie_labels'>Pie Labels</a>
   * notes regarding size requirements when using labels.
   */
  public static final String PROPERTY_LABELS = "labels";

  /**
   * Return the {@link #PROPERTY_DIMENSIONS} property value.
   *
   * @return The value that indicates the chart dimensions.
   */
  public Dimensions getDimensions()
  {
    return (Dimensions) get( PROPERTY_DIMENSIONS );
  }

  /**
   * Set the value of the {@link #PROPERTY_DIMENSIONS} property.
   *
   * @param dimension The value to set.
   */
  public void setDimensions( final Dimensions dimension )
  {
    set( PROPERTY_DIMENSIONS, dimension );
  }

  /**
   * Return the {@link #PROPERTY_LABELS} property value.
   *
   * @return The labels value encoded as required by the chart api.
   */
  @SuppressWarnings( {"unchecked"} )
  public Collection<String> getLabels()
  {
    return (Collection<String>) get( PROPERTY_LABELS );
  }

  /**
   * Set the value of the {@link #PROPERTY_LABELS} property.
   *
   * @param labels The value to set.
   */
  public void setLabels( final Collection<String> labels )
  {
    set( PROPERTY_LABELS, labels );
  }
}