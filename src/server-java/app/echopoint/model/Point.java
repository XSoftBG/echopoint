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
package echopoint.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * A model object that represents a point in cartesian co-ordinate system.
 *
 * <p><b>Note:</b> Development of this component was sponsored by
 * <a href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author Rakesh Vidyadharan 2008-10-20
 * @version $Id: Point.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
@XStreamAlias( "point" )
public class Point implements Serializable
{
  private static final long serialVersionUID = 1l;

  /** The x co-ordinate of the point. */
  public final int x;

  /** The y co-ordinate of the point. */
  public final int y;

  public Point( final int x, final int y )
  {
    this.x = x;
    this.y = y;
  }

  /**
   * Compare the specified object with this for equality.
   *
   * @param o The object that is to be compared.
   * @return Returns <code>true</code> if the objects are equivalent.
   */
  @Override
  public boolean equals( final Object o )
  {
    if ( this == o ) return true;
    if ( o == null || getClass() != o.getClass() ) return false;

    Point point = (Point) o;

    return x == point.x && y == point.y;
  }

  /**
   * Compute a hash code for this object.
   *
   * @return The hash code value.
   */
  @Override
  public int hashCode()
  {
    int result;
    result = x;
    result = 31 * result + y;
    return result;
  }

  /**
   * Accessor for property 'y'.
   *
   * @return Value for property 'y'.
   */
  public int getY()
  {
    return y;
  }

  /**
   * Accessor for property 'x'.
   *
   * @return Value for property 'x'.
   */
  public int getX()
  {
    return x;
  }
}
