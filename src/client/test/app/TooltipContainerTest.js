/**
 * A test class for the echopoint.jquery.TooltipContainer client-side component.
 *
 * @author OPehnke 2009-05-26
 * @version $Id: TooltipContainerTest.js
 */
echopoint.test.TooltipContainerTest = Core.extend(
{
  $construct: function( testArea )
  {
  	var tooltipContainer = this._createTooltipContainer();
  	tooltipContainer.add(this._createTestLabel());
  	
    testArea.add( tooltipContainer );
  },

  _createTooltipContainer: function()
  {
    return new echopoint.TooltipContainer(
    {
      renderId: "echopointUnitTestSimpleTooltipContainer",
      styleName: "Default",
      toolTipText: "The Tooltip Text"
    });
  },
  _createTestLabel: function() 
  {
  	return new Echo.Label({
  		renderId: "echopointUnitTestTooltipContainerLabel",
  		styleName: "Default",
  		text: "TooltipLabel"	
  	});
  }
});
