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

package echopoint.google.chart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * A model object that represents a data set that is to be plotted.  Note that
 * the data models are designed to correspond to the way Google expecte data
 * and hence does not provide a more logical <code>Point</code> class.  A
 * point class would not work naturally for bar charts either.
 *
 * @author Rakesh 2008-08-10
 * @version $Id: ChartData.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class ChartData<N extends Number> implements Serializable
{
  private static final long serialVersionUID = 1l;

  /**
   * The collection of numbers to be displayed along the x-axis.  This is
   * mandatory.
   */
  private Collection<N> xdata = new ArrayList<N>();

  /**
   * The maximum value to be plotted along the x-axis.  This is used to ensure
   * a gap between the edge of the graph and the highest data point.
   */
  private int xmax = -1;

  /**
   * The optional collection of numbers to be displayed along the y-axis.  This
   * in conjunction with {@link #xdata} precisely defines the co-ordinate of
   * a point to be plotted.
   */
  private Collection<N> ydata = new ArrayList<N>();

  /**
   * The optional maximum value to be plotted along the y-axis.  This is
   * used to ensure a gap between the edge of the graph and the highest data
   * point.
   */
  private int ymax = -1;

  /**
   * The colour to apply to the data set.  If no values are specified, default
   * colours will be assigned sequentially from <code>
   * echopoint.google.internal.AbstractChart#COLORS</code> client side array.
   */
  private String color;

  /**
   * The legend to display for this data set.  Note that if a legend is
   * specified for one data set in the collection of data sets being plotted
   * on the same graph, all sets must be assigned one.
   */
  private String legend;

  /**
   * The marker styles to assign to the data points.  If the collection has
   * only one element, all points are assigned the same marker.  If not, the
   * size of the collection must equal the size of {@link #xdata} and {@link
   * #ydata}.
   */
  private Collection<ShapeMarker> markers = new ArrayList<ShapeMarker>();

  /** Default constructor. */
  public ChartData() {}

  /**
   * Create a new instance using the mandatory field values.
   *
   * @param xdata The {@link #xdata} to use.
   * @param xmax The {@link #xmax} to use.
   */
  public ChartData( final Collection<N> xdata, final int xmax )
  {
    setXdata( xdata );
    setXmax( xmax );
  }

  /**
   * Accessor for property 'xdata'.  Returns an unmodifiable view of the
   * data.
   *
   * @return Value for property 'xdata'.
   */
  public Collection<N> getXdata()
  {
    return Collections.unmodifiableCollection( xdata );
  }

  /**
   * Mutator for property 'xdata'.
   *
   * @param xdata Value to set for property 'xdata'.
   */
  public void setXdata( final Collection<N> xdata )
  {
    this.xdata.clear();
    this.xdata.addAll( xdata );
    xmax = getXmax();
  }

  /**
   * Accessor for property 'xmax'. If {@link #xmax} is not defined, return
   * the maximum value from {@link #xdata}.
   *
   * @return Value for property 'xmax'.
   */
  public int getXmax()
  {
    if ( xmax > 0 ) return xmax;

    double max = 0.0;
    for ( N value : xdata )
    {
      if ( ( value != null ) && ( value.doubleValue() > max ) )
      {
        max = value.doubleValue();
      }
    }

    return (int) max + 5;
  }

  /**
   * Mutator for property 'xmax'.
   *
   * @param xmax Value to set for property 'xmax'.
   */
  public void setXmax( final int xmax )
  {
    this.xmax = xmax;
  }

  /**
   * Accessor for property 'ydata'.  Returns an unmodifiable view of the data.
   *
   * @return Value for property 'ydata'.
   */
  public Collection<N> getYdata()
  {
    return Collections.unmodifiableCollection( ydata );
  }

  /**
   * Mutator for property 'ydata'.
   *
   * @param ydata Value to set for property 'ydata'.
   */
  public void setYdata( final Collection<N> ydata )
  {
    this.ydata.clear();
    this.ydata.addAll( ydata );
    ymax = getYmax();
  }

  /**
   * Accessor for property 'ymax'.
   *
   * @return Value for property 'ymax'.
   */
  public int getYmax()
  {
    if ( ymax != -1 ) return ymax;

    double max = 0.0;
    for ( N value : ydata )
    {
      if ( ( value != null ) && ( value.doubleValue() > max ) )
      {
        max = value.doubleValue();
      }
    }

    return (int) max + 5;
  }

  /**
   * Mutator for property 'ymax'.
   *
   * @param ymax Value to set for property 'ymax'.
   */
  public void setYmax( final int ymax )
  {
    this.ymax = ymax;
  }

  /**
   * Accessor for property 'color'.
   *
   * @return Value for property 'color'.
   */
  public String getColor()
  {
    return color;
  }

  /**
   * Mutator for property 'color'.
   *
   * @param color Value to set for property 'color'.
   */
  public void setColor( final String color )
  {
    this.color = color;
  }

  /**
   * Accessor for property 'legend'.
   *
   * @return Value for property 'legend'.
   */
  public String getLegend()
  {
    return legend;
  }

  /**
   * Mutator for property 'legend'.
   *
   * @param legend Value to set for property 'legend'.
   */
  public void setLegend( final String legend )
  {
    this.legend = legend;
  }

  /**
   * Accessor for property 'markers'.
   *
   * @return Value for property 'markers'.
   */
  public Collection<ShapeMarker> getMarkers()
  {
    return markers;
  }

  /**
   * Mutator for property 'markers'.
   *
   * @param markers Value to set for property 'markers'.
   */
  public void setMarkers( final Collection<ShapeMarker> markers )
  {
    this.markers.clear();
    this.markers.addAll( markers );
  }
}
