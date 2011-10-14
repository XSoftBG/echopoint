/** The name of the BorderLayout component. */
echopoint.constants.BORDER_LAYOUT = "echopoint.BorderLayout";

/**
 * A layout container that mimics a <code>java.awt.BorderLayout</code>
 * container.  Note that this component functions similar to most pane
 * containers in that only one child component is supported per region.
 *
 * <p><b>Note:</b> Do not use the <code>Component.add</code> methods to
 * add child components.  That will lead to child components not being
 * displayed.  Use only the <code>addToRegion</code> method.</p>
 *
 * @author Rakesh 2009-03-31
 * @version $Id: Application.BorderLayout.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.BorderLayout = Core.extend( echopoint.internal.AbstractContainer,
{
  $static:
  {
    NORTH: "north", // Constant that indicates the north (top) area
    WEST: "west", // Constant that indicate the west (left) area
    CENTER: "center", // Constant that indicates the center area
    EAST: "east", // Constant that indicates the east (right) area
    SOUTH: "south" // Constant that indicates the south (bottom) area
  },

  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.BORDER_LAYOUT, this );
  },

  $construct: function()
  {
    echopoint.internal.AbstractContainer.call( this );
    this._regionToIndex = new Array();
    this._indexToRegion = new Array();
  },

  componentType: echopoint.constants.BORDER_LAYOUT,

  /** A map used to map the region index to the child index. */
  _regionToIndex: null,

  /** A map used to map the child index to region index. */
  _indexToRegion: null,

  /** Add the child component to the specified region. */
  addToRegion: function( child, area )
  {
    if ( this._regionToIndex[area] )
    {
      throw "Attempt to add additional child to area: " + area;
    }

    if ( ! area ) area = echopoint.BorderLayout.CENTER;

    var index = this.getComponentCount();
    this.add( child );
    this._regionToIndex[area] = index;
    this._indexToRegion[index] = area;
  },

  /** Return the child at the specified region. */
  getChildAtRegion: function( region )
  {
    return this.getComponent( this._regionToIndex[region] );
  },

  /** Return the region for the specified child index. */
  getRegionForIndex: function( index )
  {
    return this._indexToRegion[index];
  },

  /** Set the region-index mapping using the JSON string. */
  setRegionToIndex: function( json )
  {
    this._regionToIndex = new Array();
    var data = eval( "(" + json + ")" );

    for ( var i = 0; i < data.map.length; ++i )
    {
      var entry = data.map[i];
      this._regionToIndex[ entry[0] ] = entry[1];
    }
  },

  /** Set the index-region mapping using the JSON string. */
  setIndexToRegion: function( json )
  {
    this._indexToRegion = new Array();
    var data = eval( "(" + json + ")" );

    for ( var i = 0; i < data.map.length; ++i )
    {
      var entry = data.map[i];
      this._indexToRegion[ entry[0] ] = entry[1];
    }
  }
});
