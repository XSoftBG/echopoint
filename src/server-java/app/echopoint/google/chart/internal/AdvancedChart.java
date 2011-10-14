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

import java.util.Collection;

import echopoint.google.chart.model.FillArea;
import echopoint.google.chart.model.LineStyle;
import echopoint.google.chart.model.Range;
import echopoint.google.chart.model.RangeMarker;

/**
 * An abstract base class for charts that support most of the configuration
 * options supported by
 * <a href='http://code.google.com/apis/chart/'>Google Chart API</a>.
 *
 * @author Rakesh Vidyadharan 2008-08-20
 * @version $Id: AdvancedChart.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class AdvancedChart<N extends Number> extends SimpleChart<N>
{
  private static final long serialVersionUID = 1l;

  /**
   * The axis type specification for the chart.  This property can be
   * styled.  See <a href='http://code.google.com/apis/chart/#axis_type'>Axis type</a>
   * documentation.
   */
  public static final String PROPERTY_AXIS_TYPE = "axisType";

  /**
   * The collection of collection of labels (string) for the axes.  This must
   * have as many child collections as there are axis types defined in
   * AXIS_TYPE.  See
   * <a href='http://code.google.com/apis/chart/#axes_labels'>Axis labels</a>
   * documentation.  This property cannot be styled.
   */
  public static final String PROPERTY_AXIS_LABELS = "axisLabels";

  /**
   * The label positions for the axis labels.  This can be used to present
   * labels that are non-unoformly distributed along the axis.  Similar to
   * AXIS_LABELS, this is specified as a collection of collection of label
   * positions (numbers).  This property cannot be styled.  See
   * <a href='http://code.google.com/apis/chart/#axes_label_positions'>Axis
   * positions</a> documentation.
   */
  public static final String PROPERTY_LABEL_POSITIONS = "labelPositions";

  /**
   * The ranges for the axes defined for the chart.  The value is expressed
   * as a collection of {@link echopoint.google.chart.model.Range} object instances
   * with the collection size being equal to the number of axes defined for
   * the chart. This property cannot be styled.
   * See <a href='http://code.google.com/apis/chart/#axis_range'>Axis
   * ranges</a> documentation.
   */
  public static final String PROPERTY_AXIS_RANGES = "axisRanges";

  /**
   * The styles to apply for the axis labels.  The value is expressed as
   * a string with the specified format without the <code>&amp;chxs=</code>
   * prefix.  This property is best set as a style.
   * See <a href='http://code.google.com/apis/chart/#axes_styles'>Axis
   * styles</a> documentation for specification.
   */
  public static final String PROPERTY_AXIS_STYLES = "axisStyles";

  /**
   * The line styles for the data sets plotted.  Value is expressed as a
   * collection of {@link echopoint.google.chart.model.LineStyle} objects.  This
   * property is not styleable.
   */
  public static final String PROPERTY_LINE_STYLES = "lineStyles";

  /**
   * Style that controls display of grid lines.  See
   * <a href='http://code.google.com/apis/chart/#grid'>Grid lines</a>
   * documentation for specification.  Express the values without the
   * <code>&amp;chls=</code> prefix in the style sheet.
   */
  public static final String PROPERTY_GRID_LINES = "gridLines";

  /**
   * Range markers to display on the graph.  Value is specified as a collection
   * of {@link echopoint.google.chart.model.RangeMarker} objects.  This property
   * is not styleable.
   */
  public static final String PROPERTY_RANGE_MARKERS = "rangeMarkers";

  /**
   * A collection of {@link echopoint.google.chart.model.FillArea} instances that
   * represent the areas between lines that are to be filled.  This property
   * is not styleable.
   */
  public static final String PROPERTY_FILL_AREA = "fillArea";

  /**
   * Return the value of the {@link #PROPERTY_AXIS_TYPE} property.
   *
   * @return The property value.
   */
  public String getAxisType()
  {
    return (String) get( PROPERTY_AXIS_TYPE );
  }

  /**
   * Set the value of the {@link #PROPERTY_AXIS_TYPE} property.
   *
   * @param type The value of the property to set.
   */
  public void setAxisType( final String type )
  {
    set( PROPERTY_AXIS_TYPE, type );
  }

  /**
   * Return the value of the {@link #PROPERTY_AXIS_LABELS} property.
   *
   * @return The property value.
   */
  @SuppressWarnings( {"unchecked"} )
  public Collection<Collection<String>> getAxisLabels()
  {
    return (Collection<Collection<String>>) get( PROPERTY_AXIS_LABELS );
  }

  /**
   * Set the value of the {@link #PROPERTY_AXIS_LABELS} property using the
   * specified collection of collection of strings.
   *
   * @param labels The value to set.
   */
  public void setAxisLabels( final Collection<Collection<String>> labels )
  {
    set( PROPERTY_AXIS_LABELS, labels );
  }

  /**
   * Return the value of the {@link #PROPERTY_LABEL_POSITIONS} property.
   *
   * @return The property value.
   */
  @SuppressWarnings( {"unchecked"} )
  public Collection<Collection<N>> getLabelPositions()
  {
    return (Collection<Collection<N>>) get( PROPERTY_LABEL_POSITIONS );
  }

  /**
   * Set the value of the {@link #PROPERTY_LABEL_POSITIONS} property using the
   * collection of collection of numbers.
   *
   * @param positions The value to set after converting to JSON.
   */
  public void setLabelPositions( final Collection<Collection<N>> positions )
  {
    set( PROPERTY_LABEL_POSITIONS, positions );
  }

  /**
   * Return the value of the {@link #PROPERTY_AXIS_RANGES} property.
   *
   * @return The property value.
   */
  @SuppressWarnings( {"unchecked"} )
  public Collection<Range> getAxisRanges()
  {
    return (Collection<Range>) get( PROPERTY_AXIS_RANGES );
  }

  /**
   * Set the value of the {@link #PROPERTY_AXIS_RANGES} property using the
   * collection of range values.
   *
   * @param ranges The value to set.
   */
  public void setAxisRanges( final Collection<Range> ranges )
  {
    set( PROPERTY_AXIS_RANGES, ranges );
  }

  /**
   * Return the value of the {@link #PROPERTY_AXIS_STYLES} property.
   *
   * @return The property value.
   */
  public String getAxisStyles()
  {
    return (String) get( PROPERTY_AXIS_STYLES );
  }

  /**
   * Set the value of the {@link #PROPERTY_AXIS_STYLES} property.
   *
   * @param styles The value of the property to set.
   */
  public void setAxisStyles( final String styles )
  {
    set( PROPERTY_AXIS_STYLES, styles );
  }

  /**
   * Return the value of the {@link #PROPERTY_LINE_STYLES} property.
   *
   * @return The property value.
   */
  @SuppressWarnings( {"unchecked"} )
  public Collection<LineStyle> getLineStyles()
  {
    return (Collection<LineStyle>) get( PROPERTY_LINE_STYLES );
  }

  /**
   * Set the value of the {@link #PROPERTY_LINE_STYLES} property.
   *
   * @param styles The value of the property to set.
   */
  public void setLineStyles( final Collection<LineStyle> styles )
  {
    set( PROPERTY_LINE_STYLES, styles );
  }

  /**
   * Return the value of the {@link #PROPERTY_GRID_LINES} property.
   *
   * @return The property value.
   */
  public String getGridLines()
  {
    return (String) get( PROPERTY_GRID_LINES );
  }

  /**
   * Set the value of the {@link #PROPERTY_GRID_LINES} property.
   *
   * @param gridLines The value of the property to set.
   */
  public void setGridLines( final String gridLines )
  {
    set( PROPERTY_GRID_LINES, gridLines );
  }

  /**
   * Return the value of the {@link #PROPERTY_RANGE_MARKERS} property.
   *
   * @return The property value.
   */
  @SuppressWarnings( {"unchecked"} )
  public Collection<RangeMarker> getRangeMarkers()
  {
    return (Collection<RangeMarker>) get( PROPERTY_RANGE_MARKERS );
  }

  /**
   * Set the value of the {@link #PROPERTY_RANGE_MARKERS} property.
   *
   * @param markers The value of the property to set.
   */
  public void setRangeMarkers( final Collection<RangeMarker> markers )
  {
    set( PROPERTY_RANGE_MARKERS, markers );
  }

  /**
   * Return the value of the {@link #PROPERTY_FILL_AREA} property.
   *
   * @return The property value.
   */
  @SuppressWarnings( {"unchecked"} )
  public Collection<FillArea> getFillArea()
  {
    return (Collection<FillArea>) get( PROPERTY_FILL_AREA );
  }

  /**
   * Set the value of the {@link #PROPERTY_FILL_AREA} property using the
   * collection of area instances.
   *
   * @param area The value of the property to set.
   */
  public void setFillArea( final Collection<FillArea> area )
  {
    set( PROPERTY_FILL_AREA, area );
  }
}