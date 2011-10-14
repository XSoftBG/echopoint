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

package echopoint.google.chart.internal;

/**
 * An abstract base class for charts (pie chart, venn diagram) that support
 * only a sub-set of the options supported by
 * <a href='http://code.google.com/apis/chart/'>Google Chart API</a>.
 *
 * @author Rakesh Vidyadharan 2008-08-21
 * @version $Id: SimpleChart.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class SimpleChart<N extends Number> extends AbstractChart<N>
{
  private static final long serialVersionUID = 1l;

  /**
   * The legend position for the chart.  Specify the values as defined by
   * the Google Chart API documentation.  This property is best styled.
   */
  public static final String PROPERTY_LEGEND_POSITION = "legendPosition";

  /**
   * Get the value of the {@link #PROPERTY_LEGEND_POSITION} property.
   *
   * @return The value of the {@link #PROPERTY_LEGEND_POSITION} property.
   */
  public String getLegendPosition()
  {
    return (String) get( PROPERTY_LEGEND_POSITION );
  }

  /**
   * Set the value of the {@link #PROPERTY_LEGEND_POSITION} property.
   *
   * @param legendPosition The value to set for the property.
   */
  public void setLegendPosition( final String legendPosition )
  {
    set( PROPERTY_LEGEND_POSITION, legendPosition );
  }
}