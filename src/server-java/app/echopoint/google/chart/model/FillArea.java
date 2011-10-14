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
 * A model object used to represent a fill that represents an area between
 * two sets of lines plotted.  See
 * <a href='http://code.google.com/apis/chart/colors.html#fill_area_marker'>Fill
 * area</a> specifications.
 *
 * @author Rakesh 2008-08-10
 * @version $Id: FillArea.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class FillArea extends Marker
{
  private static final long serialVersionUID = 1l;

  /** The starting index of the line from which to fill. */
  private int startIndex;

  /** The ending index of the line till which to fill. */
  private int endIndex;

  /**
   * Create a new instance using the specified values.
   *
   * @param markerType The {@link #markerType} value to use.
   * @param color The {@link #color} value to use.
   * @param startIndex The {@link #startIndex} value to use.
   * @param endIndex The {@link #endIndex} value to use.
   */
  public FillArea( final String markerType, final String color,
      final int startIndex, final int endIndex )
  {
    this.markerType = markerType;
    this.color = color;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  /**
   * Accessor for property 'startIndex'.
   *
   * @return Value for property 'startIndex'.
   */
  public int getStartIndex()
  {
    return startIndex;
  }

  /**
   * Accessor for property 'endIndex'.
   *
   * @return Value for property 'endIndex'.
   */
  public int getEndIndex()
  {
    return endIndex;
  }
}
