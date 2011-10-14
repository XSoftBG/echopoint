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

import echopoint.google.chart.internal.AdvancedChart;

/**
 * Component wrapper for a
 * <a href='http://code.google.com/apis/chart/#radar'>Radar chart</a>
 * provided by <a href='http://code.google.com/apis/chart/'>Google Chart
 * API</a>.
 *
 * <p>The following code shows sample use of this component:</p>
 * <pre>
 *   import echopoint.google.chart.RadarChart;
 *   import echopoint.google.chart.model.ChartData;
 *
 *     ...
 *     final ChartData&lt;Integer&gt; data = new ChartData&lt;Integer&gt;();
 *     final Integer[] array = new Integer[] { 30, 60, 70, 90, 95, 110 };
 *     final List&lt;Integer&gt; xdata = Arrays.asList( array );
 *     final int xmax = 120;
 *
 *     data.setXdata( xdata );
 *     data.setXmax( xmax );
 *
 *     final RadarChart&lt;Integer&gt; chart = new RadarChart&lt;Integer&gt;();
 *     chart.setLineStyle( RadarChart.LineStyle.rs );
 *     final ArrayList&lt;ChartData&lt;Integer&gt;&gt; collection = new ArrayList&lt;ChartData&lt;Integer&gt;&gt;();
 *     collection.add( data );
 *     chart.setData( collection );
 * </pre>
 *
 * @author Rakesh Vidyadharan 2008-08-24
 * @version $Id: RadarChart.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class RadarChart<N extends Number> extends AdvancedChart<N>
{
  private static final long serialVersionUID = 1l;

  /** The enumeration of line styles supported by the chart. */
  public enum LineStyle { r, rs }

  /**
   * The line style for the chart.  This proeprty is best styled.  Note that
   * the values must correspond to {@link LineStyle}.
   */
  public static final String PROPERTY_LINE_STYLE = "lineStyle";

  /**
   * Return the {@link #PROPERTY_LINE_STYLE} property value.
   *
   * @return The value of the property.
   */
  public String getLineStyle()
  {
    return (String) get( PROPERTY_LINE_STYLE );
  }

  /**
   * Set the value of the {@link #PROPERTY_LINE_STYLE} property.  Direct
   * use of this method is not recommended.
   *
   * @see #setLineStyle( LineStyle )
   * @param style The line style to use for the chart.
   */
  public void setLineStyle( final String style )
  {
    set( PROPERTY_LINE_STYLE, style );
  }

  /**
   * Set the value of the {@link #PROPERTY_LINE_STYLE} property.  This is
   * the preferred mutator method for this proeprty.
   *
   * @param lineStyle The line style to use.
   */
  public void setLineStyle( final LineStyle lineStyle )
  {
    setLineStyle( lineStyle.toString() );
  }
}