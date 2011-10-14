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

import java.util.ArrayList;
import java.util.Collection;

import nextapp.echo.app.Component;
import echopoint.google.chart.model.ChartData;
import echopoint.google.chart.model.Title;
import echopoint.internal.AbstractContainer;

/**
 * The abstract base class for the components that wrap the charts provided
 * by <a href='http://code.google.com/apis/chart/'>Google Chart API</a>.
 *
 * @author Rakesh 2008-08-10
 * @version $Id: AbstractChart.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public abstract class AbstractChart<N extends Number> extends AbstractContainer
{
  private static final long serialVersionUID = 1l;

  /**
   * The alternate text to display for the image and chart.  This property
   * may be styled.
   */
  public static final String PROPERTY_ALT = "alt";

  /**
   * The colour fill property for the chart.  Refer to the colour fill
   * and linear gradient notes for the Google Chart API to determine the
   * proper formatted string values that may be specified for charts.
   * This property is best styled using the same string values as documented
   * by the chart api.
   */
  public static final String PROPERTY_FILL = "fill";

  /**
   * An array of {@link echopoint.google.chart.model.ChartData} model objects that
   * are to be plotted.  Note that all elements of the array should have the
   * same type of model.  Either all model elements must be simple (only xdata),
   * or should have both xdata and ydata.  This property cannot be styled.
   */
  public static final String PROPERTY_DATA = "data";

  /**
   * The title to display for chart.  Must be of type {@link
   * echopoint.google.chart.model.Title}.  This property cannot be styled.
   */
  public static final String PROPERTY_TITLE = "title";

  /**
   * <b>AbstractChart</b> is <i>NOT</i> allowed to have any children.
   *
   * @see nextapp.echo.app.Component#isValidChild(nextapp.echo.app.Component)
   */
  @Override
  public boolean isValidChild( final Component child )
  {
    return false;
  }

  /**
   * Get the value of the {@link #PROPERTY_ALT} property.
   *
   * @return The value of the {@link #PROPERTY_ALT} property.
   */
  public String getAlt()
  {
    return (String) get( PROPERTY_ALT );
  }

  /**
   * Set the value of the {@link #PROPERTY_ALT} property.
   *
   * @param alt The value to set for the property.
   */
  public void setAlt( final String alt )
  {
    set( PROPERTY_ALT, alt );
  }

  /**
   * Get the value of the {@link #PROPERTY_FILL} property.
   *
   * @return The value of the {@link #PROPERTY_FILL} property.
   */
  public String getFill()
  {
    return (String) get( PROPERTY_FILL );
  }

  /**
   * Set the value of the {@link #PROPERTY_FILL} property.
   *
   * @param fill The value to set for the property.
   */
  public void setFill( final String fill )
  {
    set( PROPERTY_FILL, fill );
  }

  /**
   * Get the value of the {@link #PROPERTY_DATA} property.
   *
   * @return The value of the {@link #PROPERTY_DATA} property.
   */
  @SuppressWarnings( {"unchecked"} )
  public Collection<ChartData<N>> getData()
  {
    return (Collection<ChartData<N>>) get( PROPERTY_DATA );
  }

  /**
   * Set the value of the {@link #PROPERTY_DATA} property.
   *
   * @param data The collection of model objects.
   */
  public void setData( final Collection<ChartData<N>> data )
  {
    final Collection<ChartData<N>> collection =
        new ArrayList<ChartData<N>>( data );
    set( PROPERTY_DATA, collection );
  }

  /**
   * Set the value of the {@link #PROPERTY_DATA} property using the specified
   * single data model object instance.
   *
   * @see #setData( Collection )
   * @param data The value to set for the property.
   */
  public void setData( final ChartData<N> data )
  {
    final Collection<ChartData<N>> collection = new ArrayList<ChartData<N>>();
    collection.add( data );
    setData( collection );
  }

  /**
   * Get the value of the {@link #PROPERTY_TITLE} property.
   *
   * @return The value of the {@link #PROPERTY_TITLE} property.
   */
  public Title getTitle()
  {
    return (Title) get( PROPERTY_TITLE );
  }

  /**
   * Set the value of the {@link #PROPERTY_TITLE} property.
   *
   * @param title The value to set for the property.
   */
  public void setTitle( final Title title )
  {
    set( PROPERTY_TITLE, title );
  }
}
