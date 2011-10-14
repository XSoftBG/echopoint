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
 * A model object that represents a rectangular clickable section on an {@link
 * echopoint.ImageMap}.
 *
 * <p><b>Note:</b> Development of this component was sponsored by
 * <a href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author Rakesh 2008-10-19
 * @version $Id: RectangleSection.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
@XStreamAlias( "rectangleSection" )
public class RectangleSection extends MapSection
{
  private static final long serialVersionUID = 1l;

  /** The bottom-left corner (origin) of the rectangle. */
  private Point bottom;

  /** The top-right (end) of the rectangle. */
  private Point top;

  /** Default constructor. */
  public RectangleSection() {}

  /**
   * Create a new instance using the specified values.
   *
   * @param bottomx The x-coordinate of the origin of the rectangle.
   * @param bottomy The y-coordinate of the origin of the rectangle.
   * @param topx The x-coordinate of the end of the rectangle.
   * @param topy The y-coordinate of the end of the rectangle.
   * @param values Optionally the {@link #actionCommand} and {@link
   *   #altText} values.  Note that unless {@link #actionCommand} is
   *   specified the section will not be saved in the image map.
   */
  public RectangleSection( final int bottomx, final int bottomy,
      final int topx, final int topy, final String... values )
  {
    this( new Point( bottomx, bottomy ), new Point( topx, topy ), values );
  }

  /**
   * Designated constructor.  Create a new instance with the specified values.
   *
   * @param bottom The bottom corner of the rectangle.
   * @param top The top corner of the rectangle.
   * @param values Optionally the {@link #actionCommand} and {@link
   *   #altText} values.  Note that unless {@link #actionCommand} is
   *   specified the section will not be saved in the image map.
   */
  public RectangleSection( final Point bottom, final Point top,
      final String... values )
  {
    setBottom( bottom );
    setTop( top );

    if ( values.length > 0 ) setActionCommand( values[0] );
    if ( values.length > 1 ) setAltText( values[1] );
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals( final Object o )
  {
    if ( this == o ) return true;
    if ( o == null || getClass() != o.getClass() ) return false;
    if ( !super.equals( o ) ) return false;

    RectangleSection that = (RectangleSection) o;

    return super.equals( o ) &&
        !( bottom != null ? !bottom.equals( that.bottom ) : that.bottom != null ) &&
        !( top != null ? !top.equals( that.top ) : that.top != null );
  }

  public int hashCode()
  {
    int result = super.hashCode();
    result = 31 * result + ( bottom != null ? bottom.hashCode() : 0 );
    result = 31 * result + ( top != null ? top.hashCode() : 0 );
    return result;
  }

  /**
   * Accessor for property 'bottom'.
   *
   * @return Value for property 'bottom'.
   */
  public Point getBottom()
  {
    return bottom;
  }

  /**
   * Mutator for property 'bottom'.
   *
   * @param bottom Value to set for property 'bottom'.
   */
  public void setBottom( final Point bottom )
  {
    this.bottom = bottom;
  }

  /**
   * Accessor for property 'top'.
   *
   * @return Value for property 'top'.
   */
  public Point getTop()
  {
    return top;
  }

  /**
   * Mutator for property 'top'.
   *
   * @param top Value to set for property 'top'.
   */
  public void setTop( final Point top )
  {
    this.top = top;
  }
}
