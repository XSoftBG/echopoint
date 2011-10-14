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
 * A model object used to represent the range of a chart axis (and data).
 *
 * @author Rakesh 2008-08-10
 * @version $Id: Range.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class Range implements Serializable
{
  private static final long serialVersionUID = 1l;

  /** The minimum (staring) number for this range. */
  public final int minimum;

  /** The maximum (ending) number for this range. */
  public final int maximum;

  /**
   * Create a new instance using the specified values.
   *
   * @param minimum The {@link #minimum} value to use.
   * @param maximum The {@link #maximum} value to use.
   */
  public Range( final int minimum, final int maximum )
  {
    this.minimum = minimum;
    this.maximum = maximum;
  }
}
