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
 * An asbtract base class for model objects that represent the clickable
 * sections in a {@link echopoint.ImageMap}.  Concrete sub-classes exist that
 * represent the different types of shapes that are supported by HTML image
 * maps.
 *
 * <p><b>Note:</b> Development of this component was sponsored by
 * <a href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author Rakesh 2008-10-19
 * @version $Id: MapSection.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
@XStreamAlias( "mapSection" )
public abstract class MapSection implements Serializable
{
  private static final long serialVersionUID = 1l;

  /** The action command to associate with this clickable section. */
  protected String actionCommand;

  /** The alternate text (title) to associate with the clickable section. */
  protected String altText;

  /** Default constructor. */
  protected MapSection() {}

  /**
   * Create a new instance using the specified action command and title.
   *
   * @param actionCommand The action command to associated with this section.
   * @param altText The title to associate with this section.
   */
  protected MapSection( final String actionCommand, final String altText )
  {
    setActionCommand( actionCommand );
    setAltText( altText );
  }

  /**
   * Compare the specified object with this instance for equality.
   *
   * @param o The object to be compared.
   * @return Return <code>true</code> if the two objects are equivalent.
   */
  @Override
  public boolean equals( final Object o )
  {
    if ( this == o ) return true;
    if ( o == null || getClass() != o.getClass() ) return false;

    MapSection that = (MapSection) o;
    return !( actionCommand != null ? !actionCommand.equals( that.actionCommand ) :
        that.actionCommand != null ) && !( altText != null ?
        !altText.equals( that.altText ) : that.altText != null );
  }

  /**
   * Compute a hash code for this instance.
   *
   * @return The hash code for this instance.
   */
  @Override
  public int hashCode()
  {
    int result;
    result = ( actionCommand != null ? actionCommand.hashCode() : 0 );
    result = 31 * result + ( altText != null ? altText.hashCode() : 0 );
    return result;
  }

  /**
   * Accessor for property 'actionCommand'.
   *
   * @return Value for property 'actionCommand'.
   */
  public String getActionCommand()
  {
    return actionCommand;
  }

  /**
   * Mutator for property 'actionCommand'.
   *
   * @param actionCommand Value to set for property 'actionCommand'.
   */
  public void setActionCommand( final String actionCommand )
  {
    this.actionCommand = actionCommand;
  }

  /**
   * Accessor for property 'altText'.
   *
   * @return Value for property 'altText'.
   */
  public String getAltText()
  {
    return altText;
  }

  /**
   * Mutator for property 'altText'.
   *
   * @param altText Value to set for property 'altText'.
   */
  public void setAltText( final String altText )
  {
    this.altText = altText;
  }
}
