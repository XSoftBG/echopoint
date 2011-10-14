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

package echopoint;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nextapp.echo.app.Component;
import nextapp.echo.app.IllegalChildException;
import echopoint.internal.AbstractContainer;

/**
 * An implementation of {@link java.awt.BorderLayout} using a HTML table with
 * three rows, and three columns.  Note that similar to pane containers, this
 * component supports only one child component per region.
 *
 * <p>The following shows sample use of this container component.</p>
 * <pre>
 *  import echopoint.BorderLayout;
 *  import echopoint.HtmlLabel;
 *  import nextapp.echo.app.Alignment;
 *  import nextapp.echo.app.Color;
 *  import nextapp.echo.app.Extent;
 *  import nextapp.echo.app.layout.TableLayoutData;
 *
 *    ...
 *    final BorderLayout container = new BorderLayout();
 *    container.setWidth( new Extent( 500 ) );
 *
 *    HtmlLabel label = new HtmlLabel( "North" );
 *    TableLayoutData layout = new TableLayoutData();
 *    layout.setAlignment( Alignment.ALIGN_CENTER );
 *    layout.setBackground( new Color( 0x0000ff ) );
 *    label.setLayoutData( layout );
 *    container.add( label, BorderLayout.Region.north );
 *
 *    ...
 *
 *    parent.add( container );
 * </pre>
 *
 * @author Rakesh 2009-04-02
 * @version $Id: BorderLayout.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 * @since 3.0.0a11
 */
public class BorderLayout extends AbstractContainer
{
  private static final long serialVersionUID = 1L;

  /** Enumeration for the regions in a border layout. */
  public enum Region
  {
    /** The region that represents the north (top) area of the layout container. */
    north,

    /** The region that represents the west (left) area of the layout container. */
    west,

    /** The region that represents the centre (default) area of the layout container. */
    center,

    /** The region that represents the east (right) area of the layout container. */
    east,

    /** The region that represents the south (bottom) area of the layout container. */
    south
  }

  /** A map used to maintain the regions to the component indices. */
  private Map<Region,Integer> regionToIndex = new LinkedHashMap<Region,Integer>( 5 );

  /** A map used to maintain the mapping of component index to region. */
  private Map<Integer,Region> indexToRegion = new LinkedHashMap<Integer,Region>( 5 );

  /** Default constructor.  No special actions required. */
  public BorderLayout() {}

  /**
   * Create a new instance using the specified list of child components in
   * order {@link Region#north}, {@link Region#west}, {@link Region#center},
   * {@link Region#east}, and {@link Region#south}.  If the specified list has
   * only one child, it is added to the {@link Region#center} area.
   *
   * @param list The list of child components.
   * @throws IllegalArgumentException If the list contains more than 5 components.
   */
  public BorderLayout( final List<Component> list )
  {
    if ( list.size() > 5 )
    {
      throw new IllegalArgumentException(
          "BorderLayout can contain only 5 child components" );
    }

    if ( list.size() == 1 )
    {
      add( list.get( 0 ) );
    }
    else
    {
      for ( int i = 0; i < list.size(); ++i )
      {
        Region region;

        switch ( i )
        {
          case 0:
            region = Region.north;
            break;
          case 1:
            region = Region.west;
            break;
          case 3:
            region = Region.east;
            break;
          case 4:
            region = Region.south;
            break;
          default:
            region = Region.center;
            break;
        }

        add( list.get( i ), region );
      }
    }
  }

  /**
   * Add the specified child component to the specified region.
   *
   * @param component The child component to add to the layout container.
   * @param region The region to which the component is to be added.
   * @throws IllegalArgumentException If a component already exists at the
   *   specified region index.
   */
  public void add( final Component component, final Region region )
  {
    if ( regionToIndex.containsKey( region ) )
    {
      throw new IllegalArgumentException(
          "Component " + component + " illegally added to BorderLayout " +
              "at region: " + region );
    }

    int index = getComponentCount();
    super.add( component, index );
    regionToIndex.put( region, index );
    indexToRegion.put( index, region );
  }

  /**
   * Over-ridden to add the component to {@link Region#center}.
   *
   * {@inheritDoc}
   * @throws IllegalArgumentException If invoked more than once or if the
   *   index is greater than 0.
   */
  @Override
  public void add( final Component component, final int index )
      throws IllegalChildException
  {
    if ( index > 0 )
    {
      throw new IllegalArgumentException(
          "Component " + component + " illegally added to BorderLayout " +
              "at region: center" );
    }

    add( component, Region.center );
  }

  /**
   * Over-ridden to add the component to {@link Region#center}.
   *
   * {@inheritDoc}
   * @throws IllegalArgumentException If invoked more than once.
   */
  @Override
  public void add( final Component component )
  {
    add( component, Region.center );
  }

  /**
   * Return the child component for the specified region.
   *
   * @param region The region for which the child component is to be retrieved.
   * @return Returns the child component or {@code null} if no child exists.
   */
  public Component getComponent( final Region region )
  {
    if ( ! regionToIndex.containsKey( region ) ) return null;
    return getComponent( regionToIndex.get( region ) );
  }

  /**
   * Over-ridden to clear the maps used to maintain region to child index
   * relationships.
   *
   * {@inheritDoc}
   */
  @Override
  public void dispose()
  {
    regionToIndex.clear();
    indexToRegion.clear();
    super.dispose();
  }

  /**
   * Return the mappings for the region to child index.
   *
   * @return The map with the region as key.
   */
  Map<Region,Integer> getRegionToIndex()
  {
    return regionToIndex;
  }

  /**
   * Return the mappings for the child index to region.
   *
   * @return The map with the child index as key
   */
  Map<Integer,Region> getIndexToRegion()
  {
    return indexToRegion;
  }
}
