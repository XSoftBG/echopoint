/**
 * A test class for the echopoint.ImageIcon client-side component.
 * Displays a sample image and applies standard styles.
 *
 * @author Rakesh 2008-10-20
 * @version $Id: ImageIconTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.ImageIconTest = Core.extend(
{
  imageIcon: null,
  label: null,
  select: null,
  count: 0,

  $construct: function( testArea )
  {
    testArea.add( this._createImageIcon() );
    testArea.add( this._createLabel() );
    testArea.add( this._createSelect() );
  },

  _createImageIcon: function()
  {
    this.imageIcon = new echopoint.ImageIcon(
    {
      renderId: "echopointUnitTestImageIcon",
      styleName: "Default",
      events:
      {
        action: Core.method( this, this._actionPerformed )
      }
    } );

    return this.imageIcon;
  },

  _createLabel: function()
  {
    this.label = new Echo.Label(
    {
      renderId: "echopointUnitTestImageIconLabel",
      style: "Default",
      text: "Action Label"
    } );

    return this.label;
  },

  _createSelect: function()
  {
    this.select = new Echo.SelectField(
    {
      renderId: "echopointUnitTestImageIconSelect",
      style: "Default",
      items:
      [
        { id: "auto", text: "Browser Default" },
        { id: "crosshair", text: "Cross Hair" },
        { id: "e-resize", text: "Right Arrow" },
        { id: "help", text: "Help" },
        { id: "move", text: "Move" },
        { id: "n-resize", text: "Up Arrow" },
        { id: "ne-resize", text: "NE Arrow" },
        { id: "nw-resize", text: "NW Arrow" },
        { id: "pointer", text: "Pointer" },
        { id: "progress", text: "Progress" },
        { id: "s-resize", text: "Down Arrow" },
        { id: "se-resize", text: "SE Arrow" },
        { id: "sw-resize", text: "SW Arrow" },
        { id: "text", text: "Text" },
        { id: "w-resize", text: "Left Arrow" },
        { id: "wait", text: "Wait" }
      ],
      events:
      {
        action: Core.method( this, this._cursorSelected )
      }
    } );

    return this.select;
  },

  _actionPerformed: function()
  {
    this.label.set( "text", "Button clicked: " + ++this.count );
  },

  _cursorSelected: function()
  {
    this.imageIcon.set( echopoint.internal.AbstractImage.CURSOR,
        this.select.get( "selectedId" ) );
  }
});
