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
 * <a href='http://code.google.com/apis/chart/#line_charts'>Line chart</a>
 * provided by <a href='http://code.google.com/apis/chart/'>Google Chart
 * API</a>.
 *
 * <p>The following code shows sample use of this component:</p>
 * <pre>
 *   import echopoint.google.chart.LineChart;
 *   import echopoint.google.chart.model.ChartData;
 *
 *     ...
 *     final ChartData&lt;Integer&gt; data = new ChartData&lt;Integer&gt;();
 *     final Integer[] array = new Integer[] { 30,60,70,90,95,110 };
 *     final List&lt;Integer&gt; xdata = Arrays.asList( array );
 *     final int xmax = 120;
 *
 *     data.setXdata( xdata );
 *     data.setXmax( xmax );
 *
 *     final LineChart&lt;Integer&gt; chart = new LineChart&lt;Integer&gt;();
 *     final ArrayList&lt;ChartData&lt;Integer&gt;&gt; collection = new ArrayList&lt;ChartData&lt;Integer&gt;&gt;();
 *     collection.add( data );
 *     chart.setData( collection );
 * </pre>
 *
 * @author Rakesh Vidyadharan 2008-08-10
 * @version $Id: LineChart.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class LineChart<N extends Number> extends AdvancedChart<N>
{
  private static final long serialVersionUID = 1l;
}
