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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * A model object that represents a clickable polygon section in an {@link
 * echopoint.ImageMap}.
 *
 * <p><b>Note:</b> Development of this component was sponsored by
 * <a href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author Rakesh 2008-10-19
 * @version $Id: PolygonSection.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
@XStreamAlias( "polygonSection" )
public class PolygonSection extends MapSection
{
  private static final long serialVersionUID = 1l;

  /**
   * The collection of points that represent the corners of the polygon.
   */
  private Collection<Point> vertices = new ArrayList<Point>();

  /** Default constructor. */
  public PolygonSection() {}

  /**
   * Create a new instance using the specified values.
   *
   * @param values The {@link #actionCommand} and {@link
   *   #altText} values.  Note that unless {@link #actionCommand} is
   *   specified the section will not be saved in the image map.
   */
  public PolygonSection( final String... values )
  {
    if ( values.length > 0 ) setActionCommand( values[0] );
    if ( values.length > 1 ) setAltText( values[1] );
  }

  /**
   * Create a new instance using the specified values.
   *
   * @param vertices The array of vertices that represent the polygon.  Note
   *   that each vertex is represented by a pair of integer values.
   * @param values Optionally the {@link #actionCommand} and {@link
   *   #altText} values.  Note that unless {@link #actionCommand} is
   *   specified the section will not be saved in the image map.
   */
  public PolygonSection( final int[] vertices, final String... values )
  {
    this( values );

    for ( int i = 0; i < vertices.length; ++i )
    {
      if ( ( i % 2 ) == 0 )
      {
        if ( ( i + 1 ) >= vertices.length ) break;
        this.vertices.add( new Point( vertices[i], vertices[i + 1] ) );
      }
    }
  }

  public PolygonSection( final Collection<Point> vertices, final String... values )
  {
    this( values );
    setVertices( vertices );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals( final Object o )
  {
    if ( this == o ) return true;
    if ( o == null || getClass() != o.getClass() ) return false;
    if ( !super.equals( o ) ) return false;

    PolygonSection that = (PolygonSection) o;

    return super.equals( o ) &&
        !( vertices != null ? !vertices.equals( that.vertices ) : that.vertices != null );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode()
  {
    int result = super.hashCode();
    result = 31 * result + ( vertices != null ? vertices.hashCode() : 0 );
    return result;
  }

  /**
   * Accessor for property 'vertices'.
   *
   * @return Value for property 'vertices'.
   */
  public Collection<Point> getVertices()
  {
    return Collections.unmodifiableCollection( vertices );
  }

  /**
   * Mutator for property 'vertices'.
   *
   * @param vertices Value to set for property 'vertices'.
   */
  public void setVertices( final Collection<Point> vertices )
  {
    this.vertices.clear();
    this.vertices.addAll( vertices );
  }
}
