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
 * <a href='http://code.google.com/apis/chart/#maps'>Map</a>
 * provided by <a href='http://code.google.com/apis/chart/'>Google Chart
 * API</a>.
 *
 * <p>The following code shows sample use of this component:</p>
 * <pre>
 *   import echopoint.google.chart.Map;
 *   import echopoint.google.chart.model.ChartData;
 *
 *     ...
 *     final ChartData&lt;Integer&gt; data = new ChartData&lt;Integer&gt;();
 *     final Integer[] array = new Integer[] { 0, 5, 9 };
 *     data.setXdata( Arrays.asList( array ) );
 *
 *     final Map chart = new Map();
 *     final ArrayList&lt;ChartData&lt;Integer&gt;&gt; collection = new ArrayList&lt;ChartData&lt;Integer&gt;&gt;();
 *     collection.add( data );
 *     chart.setData( collection );
 *     chart.setCodes( "MGKETN" );
 * </pre>
 *
 * @author Rakesh Vidyadharan 2008-08-25
 * @version $Id: Map.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class Map extends AbstractChart<Integer>
{
  private static final long serialVersionUID = 1l;

  /** An enumeration used to represent the regions supported by the map. */
  public enum Regions
  { africa, asia, europe, middle_east, south_america, usa, world }

  /**
   * The property used to configure the map area.  This property may be
   * styled.
   */
  public static final String PROPERTY_GEOGRAPHICAL_AREA = "region";

  /**
   * The property used to specify colour and colour gradients for countries or
   * states displayed in the map.  Colour specifications are different for
   * maps as compared to regular charts.  This property is best styled.
   */
  public static final String PROPERTY_COLORS = "colors";

  /**
   * The property used to configure the countries or states in the map that
   * are to be coloured.  This property may be styled.
   */
  public static final String PROPERTY_CODES = "codes";

  /**
   * Return the {@link #PROPERTY_GEOGRAPHICAL_AREA} property value.
   *
   * @return The value that indicates the chart dimensions.
   */
  public Regions getGeographicalArea()
  {
    return (Regions) get( PROPERTY_GEOGRAPHICAL_AREA );
  }

  /**
   * Set the value of the {@link #PROPERTY_GEOGRAPHICAL_AREA} property.
   *
   * @param region The value to set.
   */
  public void setGeographicalArea( final Regions region )
  {
    set( PROPERTY_GEOGRAPHICAL_AREA, region );
  }

  /**
   * Return the {@link #PROPERTY_COLORS} property value.
   *
   * @return The colors value encoded as required by the chart api.
   */
  public String getColors()
  {
    return (String) get( PROPERTY_COLORS );
  }

  /**
   * Set the value of the {@link #PROPERTY_COLORS} property.
   *
   * @param colors The value to set.
   */
  public void setColors( final String colors )
  {
    set( PROPERTY_COLORS, colors );
  }

  /**
   * Return the {@link #PROPERTY_CODES} property value.
   *
   * @return The codes value encoded as required by the chart api.
   */
  public String getCodes()
  {
    return (String) get( PROPERTY_CODES );
  }

  /**
   * Set the value of the {@link #PROPERTY_CODES} property.
   *
   * @param codes The value to set.
   */
  public void setCodes( final String codes )
  {
    set( PROPERTY_CODES, codes );
  }
}