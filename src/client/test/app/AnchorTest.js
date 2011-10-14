/**
 * A test class for the echopoint.Anchor client-side component.
 * Displays a sample anchor tag with default styles.
 *
 * @author Rakesh 2008-10-23
 * @version $Id: AnchorTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.AnchorTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createAnchor() );
  },

  _createAnchor: function()
  {
    return new echopoint.Anchor(
    {
      renderId: "echopointUnitTestAnchor",
      styleName: "Default"
    } );
  }
} );
