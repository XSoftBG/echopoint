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
 * The model object for use in the {@link echopoint.TagCloud} component.
 *
 * @author Rakesh 2008-07-20
 * @version $Id: Tag.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
@XStreamAlias( "tag" )
public class Tag implements Serializable
{
  private static final long serialVersionUID = 1l;

  /** The name/title for the tag. */
  private String name;

  /** The number of occurances of the name represented in this tag. */
  private int count;

  /** Default constructor. */
  public Tag() {}

  /**
   * Create a new tag with the specified values.
   *
   * @param name The {@link #name} to use.
   * @param count The {@link #count} to use.
   */
  public Tag( final String name, final int count )
  {
    this.name = name;
    this.count = count;
  }

  /**
   * Compares the specified object with this instance for equality.
   *
   * @param object The object to be compared.
   * @return Returns <code>true</code> if the specified object is of the
   *   same type and has the same values.
   */
  @Override
  public boolean equals( final Object object )
  {
    if ( this == object ) return true;
    if ( object == null ) return false;

    boolean result = false;
    if ( object instanceof Tag )
    {
      final Tag tag = (Tag) object;
      result = ( this.name ==  tag.name ) || ( ( this.name != null ) &&
          this.name.equals( tag.name ) );
    }

    return result;
  }

  /**
   * Calculates a hash code for this object using the class fields.
   *
   * @return The hash code for this instance.
   */
  @Override
  public int hashCode()
  {
    int result = 31 * 7;
    result += ( name != null ? name.hashCode() : 0 );
    return result;
  }

  /**
   * Accessor for property 'name'.
   *
   * @return Value for property 'name'.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Mutator for property 'name'.
   *
   * @param name Value to set for property 'name'.
   */
  public void setName( final String name )
  {
    this.name = name;
  }

  /**
   * Accessor for property 'count'.
   *
   * @return Value for property 'count'.
   */
  public int getCount()
  {
    return count;
  }

  /**
   * Mutator for property 'count'.
   *
   * @param count Value to set for property 'count'.
   */
  public void setCount( final int count )
  {
    this.count = count;
  }
}