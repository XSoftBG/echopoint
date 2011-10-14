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

import echopoint.google.chart.internal.AbstractChart;

/**
 * Component wrapper for a
 * <a href='http://code.google.com/apis/chart/#gom'>Google-o-meter</a>
 * provided by <a href='http://code.google.com/apis/chart/'>Google Chart
 * API</a>.
 *
 * <p>The following code shows sample use of this component:</p>
 * <pre>
 *   import echopoint.google.chart.Meter;
 *   import echopoint.google.chart.model.ChartData;
 *
 *     ...
 *     final ChartData&lt;Integer&gt; data = new ChartData&lt;Integer&gt;();
 *     final Integer[] array = new Integer[] { 70 };
 *     data.setXdata( Arrays.asList( array ) );
 *     data.setXmax( 100 );
 *
 *     final Meter chart = new Meter();
 *     final ArrayList&lt;ChartData&lt;Integer&gt;&gt; collection = new ArrayList&lt;ChartData&lt;Integer&gt;&gt;();
 *     collection.add( data );
 *     chart.setData( collection );
 *     chart.setLabel( "70 %" );
 * </pre>
 *
 * @author Rakesh Vidyadharan 2008-08-27
 * @version $Id: Meter.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class Meter extends AbstractChart<Integer>
{
  private static final long serialVersionUID = 1l;

  /**
   * The property used to specify colour and colour gradients for countries or
   * states displayed in the map.  Colour specifications are different for
   * maps as compared to regular charts.  This property is best styled.
   */
  public static final String PROPERTY_LABEL = "label";

  /**
   * Return the {@link #PROPERTY_LABEL} property value.
   *
   * @return The label value encoded as required by the chart api.
   */
  public String getLabel()
  {
    return (String) get( PROPERTY_LABEL );
  }

  /**
   * Set the value of the {@link #PROPERTY_LABEL} property.
   *
   * @param label The value to set.
   */
  public void setLabel( final String label )
  {
    set( PROPERTY_LABEL, label );
  }
}