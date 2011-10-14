/**
 * A test class for the echopoint.ImageMap client-side component.
 * Displays a sample image map with clickable areas.
 *
 * @author Rakesh 2008-10-18
 * @version $Id: ImageMapTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.ImageMapTest = Core.extend(
{
  imageMap: null,
  label: null,

  $construct: function( testArea )
  {
    testArea.add( this._createImageMap() );
    testArea.add( this._createLabel() );
  },

  _createImageMap: function()
  {
    this.imageMap = new echopoint.ImageMap(
    {
      renderId: "echopointUnitTestImageMap",
      url: "image/imagemap.gif",
      height: 300,
      width: 500,
      text: "Image Map",
      toolTipText: "Image Map",
      cursor: "crosshair",
      events:
      {
        action: Core.method( this, this._actionPerformed )
      }
    } );

    this._createSections();
    return this.imageMap;
  },

  _createSections: function()
  {
    var circle = new echopoint.model.CircleSection(
        new echopoint.model.Point( 70, 84 ), 51,
        "circle", "Circular section" );
    this.imageMap.addSection( circle );

    var rectangle = new echopoint.model.RectangleSection(
        new echopoint.model.Point( 25, 180 ),
        new echopoint.model.Point( 125, 280 ),
        "rectangle", "Rectangular section" );
    this.imageMap.addSection( rectangle );

    var vertices = new Array(
        new echopoint.model.Point( 153, 106 ),
        new echopoint.model.Point( 186, 225 ),
        new echopoint.model.Point( 340, 193 ),
        new echopoint.model.Point( 315, 81 ),
        new echopoint.model.Point( 304, 167 ) );
    var polygon = new echopoint.model.PolygonSection( vertices,
        "polygon", "Polygon section" );
    this.imageMap.addSection( polygon );
  },

  _createLabel: function()
  {
    this.label = new Echo.Label(
    {
      renderId: "echopointUnitTestImageMapLabel",
      style: "Default",
      text: "Action Command Label"
    } );

    return this.label;
  },

  _actionPerformed: function( event )
  {
    switch ( event.actionCommand )
    {
      case "circle":
        this.label.set( "text", "Selected cicle" );
        break;
      case "rectangle":
        this.label.set( "text", "Selected rectangle" );
        break;
      case "polygon":
        this.label.set( "text", "Selected polygon" );
        break;
    }
  }
} );
