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

import echopoint.google.chart.internal.Marker;

/**
 * A model object that represents a range marker for the graph.  See
 * <a href='http://code.google.com/apis/chart/#hor_line_marker'>Range
 * marker</a> specifications.
 *
 * @author Rakesh Vidyadharan 2008-08-10
 * @version $Id: RangeMarker.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class RangeMarker extends Marker
{
  private static final long serialVersionUID = 1l;

  /** The start point for the marked region. Betweem 0.0 and 1.0. */
  private double startPoint;

  /** The end point for the marked region. Betweem 0.0 and 1.0. */
  private double endPoint;

  /**
   * Create a new instance using the specified values.
   *
   * @param markerType The {@link #markerType} to use.
   * @param color The {@link #color} to use.
   * @param startPoint The {@link #startPoint} to use.
   * @param endPoint The {@link #endPoint} to use.
   */
  public RangeMarker( final String markerType, final String color,
      final double startPoint, final double endPoint )
  {
    this.markerType = markerType;
    this.color = color;
    this.startPoint = startPoint;
    this.endPoint = endPoint;
  }

  /**
   * Accessor for property 'startPoint'.
   *
   * @return Value for property 'startPoint'.
   */
  public double getStartPoint()
  {
    return startPoint;
  }

  /**
   * Accessor for property 'endPoint'.
   *
   * @return Value for property 'endPoint'.
   */
  public double getEndPoint()
  {
    return endPoint;
  }
}
