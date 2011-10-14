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

/**
 * A model object used to represent the style for a line drawn.  See
 * <a href='http://code.google.com/apis/chart/#line_styles'>Line styles</a>
 * documentation for specifications.
 *
 * @author Rakesh 2008-08-10
 * @version $Id: LineStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class LineStyle implements Serializable
{
  private static final long serialVersionUID = 1l;

  /** The thickness of the line to draw. */
  public final int thickness;

  /** The length of a line segement to draw.  Specify 1 for solid lines. */
  public final int segmentLength;

  /** The length of the blank segment.  Specify 0 for solid lines. */
  public final int blankSegmentLength;

  /**
   * Create a new instance using the specified values.  Use this constructor
   * to create a solid line.
   *
   * @param thickness The {@link #thickness} value to set.
   * @param optional Optionally specify {@link #segmentLength} and {@link
   *   #blankSegmentLength} in sequence.
   */
  public LineStyle( final int thickness, int... optional )
  {
    this.thickness = thickness;
    this.segmentLength = ( optional.length > 0 ) ? optional[0] : 1;
    this.blankSegmentLength = ( optional.length > 1 ) ? optional[1] : 0;
  }
}
