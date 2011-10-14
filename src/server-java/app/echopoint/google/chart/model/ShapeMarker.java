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
 * A model object that represents a shape marker for data points on a graph.
 * See <a href='http://code.google.com/apis/chart/#shape_markers2'>Shape
 * markers</a> specifications.
 *
 * @author Rakesh 2008-08-10
 * @version $Id: ShapeMarker.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class ShapeMarker extends Marker
{
  private static final long serialVersionUID = 1l;

  /** The size of the marker. */
  private int size;

  /** The priority for drawing the marker. */
  private int priority;

  /**
   * Create a new instance using the specified values.
   *
   * @param markerType The {@link #markerType} to use.
   * @param color The {@link #color} to use.
   * @param size The {@link #size} to use.
   * @param priority The {@link #priority} to use.
   */
  public ShapeMarker( final String markerType, final String color,
      final int size, final int... priority )
  {
    this.markerType = markerType;
    this.color = color;
    this.size = size;
    this.priority = ( priority.length == 0 ) ? 0 : priority[0];
  }

  /**
   * Accessor for property 'size'.
   *
   * @return Value for property 'size'.
   */
  public int getSize()
  {
    return size;
  }

  /**
   * Accessor for property 'priority'.
   *
   * @return Value for property 'priority'.
   */
  public int getPriority()
  {
    return priority;
  }
}
