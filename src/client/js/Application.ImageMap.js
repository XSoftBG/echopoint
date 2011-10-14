/** The name of the ImageMap component. */
echopoint.constants.IMAGE_MAP = "echopoint.ImageMap";

/**
 * A component used to represent an HTML image map.  Specify the coordinates
 * for the image map using the value object and associate actions for each
 * section that is configured for the image.
 *
 * <p>Development of this component was sponsored by TCN Broadcasting.</p>
 *
 * @author Rakesh 2008-10-16
 * @version $Id: Application.ImageMap.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.ImageMap = Core.extend( echopoint.internal.AbstractImage,
{
  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.IMAGE_MAP, this );
  },

  /** Properties defined for this component. */
  $static:
  {
    // The property that holds the map of sections keyed by their action
    // command values.  Do not specify directly, instead use the methods
    // to add/remove sections.
    SECTIONS: "sections"
  },

  $virtual:
  {
    /**
     * Add a new section to the map of sections keyed by the action command
     * in the section specified.
     *
     * @param section The MapSection to add to the image map.
     */
    addSection: function( section )
    {
      if ( ! section instanceof echopoint.model.MapSection )
      {
        throw "Section must be a sub-class of MapSection";
      }

      var sections = this.get( echopoint.ImageMap.SECTIONS );
      if ( sections == null )
      {
        sections = new Core.Arrays.LargeMap();
        this.set( echopoint.ImageMap.SECTIONS, sections );
      }

      sections[section.actionCommand] = section;
    },

    /**
     * Remove the specfified section from the image map.
     *
     * @param section The section that is to be removed from the map.
     */
    removeSection: function( section )
    {
      var sections = this.get( echopoint.ImageMap.SECTIONS );
      if ( sections == null )
      {
        sections = new Core.Arrays.LargeMap();
        this.set( echopoint.ImageMap.SECTIONS, sections );
      }

      sections.remove( section.actionCommand );
    },

    /** Remove all the sections from the image map. */
    removeAllSections: function()
    {
      this.set( echopoint.ImageMap.SECTIONS, new Core.Arrays.LargeMap() );
    }
  },

  componentType: echopoint.constants.IMAGE_MAP
});

/**
 * A model object used to represent a point on the map.  A point using
 * regular cartesian co-ordinate system consists of an x co-ordinate value
 * and a y co-ordinate value.
 */
echopoint.model.Point = Core.extend(
{
  /** The x co-ordinate of the point. */
  x: 0,

  /** The y co-ordinate of the point. */
  y: 0,

  $construct: function( xcoord, ycoord )
  {
    this.x = xcoord;
    this.y = ycoord;
  }
} );

/**
 * The model object used to represent a co-ordinate set within the map that
 * represents a discrete area in the map that is clickable.  Concrete
 * sub-classes are used to represent circles, rectangles and polygons that
 * are typically used in image maps.
 */
echopoint.model.MapSection = Core.extend(
{
  $abstract: true,

  $virtual:
  {
    /** The action command associated with the section. */
    actionCommand: null,

    /** The alternate text to display for text mode browsers. */
    altText: null
  }
});

/** The model object used to represent a circular region on a map. */
echopoint.model.CircleSection = Core.extend( echopoint.model.MapSection,
{
  /** The point that represents the centroid of the circle. */
  centre: null,

  /** The radius of the circle. */
  radius: 0,

  /**
   * Create a new instance using the specified values.
   *
   * @param point The centroid of the circle.
   * @param rad The radius of the circle.
   * @param command Action command to associate with this section.
   * @param text Optional alternate text for the section.
   */
  $construct: function( point, rad, command, text )
  {
    this.centre = point;
    this.radius = rad;
    this.actionCommand = command;
    this.altText = text;
  },

  /**
   * Return a string representation of the coordinates and radius, suitable
   * for representing in a HTML area tag.
   */
  toString: function()
  {
    return ( "" + this.centre.x + "," + this.centre.y + "," + this.radius );
  }
});

/** The model object used to represent a rectangular region on a map. */
echopoint.model.RectangleSection = Core.extend( echopoint.model.MapSection,
{
  /** The point that represents the bottom-left corner of the rectangle. */
  bottom: null,

  /** The point that represents the top-right corner of the rectangle. */
  top: null,

  /**
   * Create a new instance using the specified values.
   *
   * @param b The point that represents the bottom-left corner.
   * @param t The point that represents the top-right corner.
   * @param command Action command to associate with this section.
   * @param text Optional alternate text for the section.
   */
  $construct: function( b, t, command, text )
  {
    this.bottom = b;
    this.top = t;
    this.actionCommand = command;
    this.altText = text;
  },

  /**
   * Return a string representation of the coordinates and radius, suitable
   * for representing in a HTML area tag.
   */
  toString: function()
  {
    return ( "" + this.bottom.x + "," + this.bottom.y + "," +
           this.top.x + "," + this.top.y );
  }
});

/** The model object used to represent a polygon region on a map. */
echopoint.model.PolygonSection = Core.extend( echopoint.model.MapSection,
{
  /** The array of points that represent the polygon. */
  vertices: null,

  /**
   * Create a new instance using the specified values.
   *
   * @param coords The array of points that represent the vertices of the polygon.
   * @param command Action command to associate with this section.
   * @param text Optional alternate text for the section.
   */
  $construct: function( coords, command, text )
  {
    this.vertices = coords;
    this.actionCommand = command;
    this.altText = text;
  },

  /**
   * Return a string representation of the coordinates and radius, suitable
   * for representing in a HTML area tag.
   */
  toString: function()
  {
    var text = "";
    var first = true;

    for ( var i = 0; i < this.vertices.length; ++i )
    {
      if ( first ) first = false;
      else text += ",";

      text += this.vertices[i].x + "," + this.vertices[i].y;
    }

    return text;
  }
});
