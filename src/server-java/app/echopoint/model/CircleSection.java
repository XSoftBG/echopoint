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

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * A model object that represents a circular clickable section in a {@link
 * echopoint.ImageMap}.
 *
 * <p><b>Note:</b> Development of this component was sponsored by
 * <a href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author Rakesh 2008-10-19
 * @version $Id: CircleSection.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
@XStreamAlias( "circleSection" )
public class CircleSection extends MapSection
{
  private static final long serialVersionUID = 1l;

  /** The point that represents the centroid of the circle. */
  private Point centre;

  /** The radius of the circular section. */
  private int radius;

  /** Default constructor. */
  public CircleSection() {}

  /**
   * Create a new instance using the specified values.
   *
   * @param x The x-coordinate of the centroid of the circular section.
   * @param y The y-coordinate of the centroid of the circular section.
   * @param radius The radius of the circular section.
   * @param values Optionally the {@link #actionCommand} and {@link
   *   #altText} values.  Note that unless {@link #actionCommand} is
   *   specified the section will not be saved in the image map.
   */
  public CircleSection( final int x, final int y, final int radius,
      final String... values )
  {
    this( new Point( x, y ), radius, values );
  }

  /**
   * Designated constructor.  Create a new circular section using the
   * specified values.
   *
   * @param centre The point that represents the centroid of the circle.
   * @param radius The radius of the circular section.
   * @param values Optionally the {@link #actionCommand} and {@link
   *   #altText} values.  Note that unless {@link #actionCommand} is
   *   specified the section will not be saved in the image map.
   */
  public CircleSection( final Point centre, final int radius,
      final String... values )
  {
    setCentre( centre );
    setRadius( radius );

    if ( values.length > 0 ) setActionCommand( values[0] );
    if ( values.length > 1 ) setAltText( values[1] );
  }

  /**
   * Compare the specified object with this instance for equality.
   *
   * @param o The object that is to be compared.
   * @return Return <code>true</code> if the specified object is equivalent.
   */
  @Override
  public boolean equals( final Object o )
  {
    if ( this == o ) return true;
    if ( o == null || getClass() != o.getClass() ) return false;
    if ( !super.equals( o ) ) return false;

    CircleSection that = (CircleSection) o;

    return ( super.equals( o ) && radius == that.radius &&
        !( centre != null ? !centre.equals( that.centre ) : that.centre != null ) );
  }

  /**
   * Compute a hash code for this instance.
   *
   * @return The hash code value for this instance.
   */
  @Override
  public int hashCode()
  {
    int result = super.hashCode();
    result = 31 * result + ( centre != null ? centre.hashCode() : 0 );
    result = 31 * result + radius;
    return result;
  }

  /**
   * Accessor for property 'centre'.
   *
   * @return Value for property 'centre'.
   */
  public Point getCentre()
  {
    return centre;
  }

  /**
   * Mutator for property 'centre'.
   *
   * @param centre Value to set for property 'centre'.
   */
  public void setCentre( final Point centre )
  {
    this.centre = centre;
  }

  /**
   * Accessor for property 'radius'.
   *
   * @return Value for property 'radius'.
   */
  public int getRadius()
  {
    return radius;
  }

  /**
   * Mutator for property 'radius'.
   *
   * @param radius Value to set for property 'radius'.
   */
  public void setRadius( final int radius )
  {
    this.radius = radius;
  }
}
