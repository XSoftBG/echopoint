/**
 * A test class for the echopoint.jquery.Clock client-side component.
 *
 * @author OPehnke 2009-05-26
 * @version $Id: ClockTest.js
 */
echopoint.test.ClockTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createClock() );
  },

  _createClock: function()
  {
    return new echopoint.Clock(
    {
      renderId: "echopointUnitTestSimpleClock",
      styleName: "SimpleClock"
    });
  }
});
