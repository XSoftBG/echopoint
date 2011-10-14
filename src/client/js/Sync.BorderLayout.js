/**
 * Component rendering peer: BorderLayout
 *
 * @author Rakesh 2009-03-31
 * @version $Id: Sync.BorderLayout.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.BorderLayoutSync = Core.extend( echopoint.internal.AbstractContainerSync,
{
  $static:
  {
    REGION_TO_INDEX: "regionToIndex",
    INDEX_TO_REGION: "indexToRegion"
  },

  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.BORDER_LAYOUT, this );
  },

  /** The parent container used to hold the various child sections. */
  container: null,

  /** The container used to hold the north (top) section. */
  north: null,

  /** The container used to hold the west (left) section. */
  west: null,

  /** The container used to hold the centre section. */
  centre: null,

  /** The container used to hold the east (right) section. */
  right: null,

  /** The container used to hold the south (bottom) section. */
  bottom: null,

  renderAdd: function( update, parentElement )
  {
    this._createContainers();
    parentElement.appendChild( this.container );

    this._setMaps();


    var componentCount = this.component.getComponentCount();

    if ( componentCount > 5 ) throw "Too many children added to BorderLayout";

    for ( var i = 0; i < componentCount; ++i )
    {
      var region = this.component.getRegionForIndex( i );
      var child = this.component.getComponent( i );

      switch ( region )
      {
      case echopoint.BorderLayout.NORTH:
        Echo.Render.renderComponentAdd( update, child, this.north );
        this.renderLayoutData( this.north,
            child.render( echopoint.internal.AbstractContainer.LAYOUT_DATA ) );
        break;
      case echopoint.BorderLayout.WEST:
        Echo.Render.renderComponentAdd( update, child, this.west );
        this.renderLayoutData( this.west,
            child.render( echopoint.internal.AbstractContainer.LAYOUT_DATA ) );
        break;
      case echopoint.BorderLayout.CENTER:
        Echo.Render.renderComponentAdd( update, child, this.centre );
        this.renderLayoutData( this.centre,
            child.render( echopoint.internal.AbstractContainer.LAYOUT_DATA ) );
        break;
      case echopoint.BorderLayout.EAST:
        Echo.Render.renderComponentAdd( update, child, this.east );
        this.renderLayoutData( this.east,
            child.render( echopoint.internal.AbstractContainer.LAYOUT_DATA ) );
        break;
      case echopoint.BorderLayout.SOUTH:
        Echo.Render.renderComponentAdd( update, child, this.south );
        this.renderLayoutData( this.south,
            child.render( echopoint.internal.AbstractContainer.LAYOUT_DATA ) );
        break;
      }
    }
  },

  renderDispose: function( update )
  {
    this.north = null;
    this.west = null;
    this.centre = null;
    this.right = null;
    this.bottom = null;
    this.container = null;
  },

  renderUpdate: function( update )
  {
    var parentElement = this.container.parent;
    Echo.Render.renderComponentDispose( update, update.parent );
    parentElement.removeChild( this.container );
    this.renderAdd( update, parentElement );
  },

  /** Set the region-index and index-region mappings for server side binding. */
  _setMaps: function()
  {
    var regionToIndex = this.component.get( echopoint.BorderLayoutSync.REGION_TO_INDEX );
    if ( regionToIndex )
    {
      this.component.setRegionToIndex( regionToIndex );
    }

    var indexToRegion = this.component.get( echopoint.BorderLayoutSync.INDEX_TO_REGION );
    if ( indexToRegion )
    {
      this.component.setIndexToRegion( indexToRegion );
    }
  },

  /** Create the containers used to render child components. */
  _createContainers: function()
  {
    this.container = document.createElement( "table" );
    this.container.id = this.component.renderId;
    this.container.style.emptyCells = "hide";
    this.renderStyle( this.container );

    this._createNorth();

    var row = document.createElement( "tr" );
    this._createWest( row );
    this._createCenter( row );
    this._createEast( row );
    this.container.appendChild( row );

    this._createSouth();
  },

  /** Create the north area component. */
  _createNorth: function()
  {
    var row = document.createElement( "tr" );

    this.north = document.createElement( "td" );
    this.north.id = this.component.renderId + "|North";
    this.north.colSpan = 3;

    row.appendChild( this.north );

    this.container.appendChild( row );
  },

  /** Create the west area component. */
  _createWest: function( row )
  {
    this.west = document.createElement( "td" );
    this.west.id = this.component.renderId + "|West";
    row.appendChild( this.west );
  },

  /** Create the centre area component. */
  _createCenter: function( row )
  {
    this.centre = document.createElement( "td" );
    this.centre.id = this.component.renderId + "|Centre";
    this.centre.style.width = "100%";
    row.appendChild( this.centre );
  },

  /** Create the east area component. */
  _createEast: function( row )
  {
    this.east = document.createElement( "td" );
    this.east.id = this.component.renderId + "|East";
    row.appendChild( this.east );
  },

  /** Create the south area component. */
  _createSouth: function()
  {
    var row = document.createElement( "tr" );

    this.south = document.createElement( "td" );
    this.south.id = this.component.renderId + "|South";
    this.south.colSpan = 3;
    row.appendChild( this.south );

    this.container.appendChild( row );
  }
});
