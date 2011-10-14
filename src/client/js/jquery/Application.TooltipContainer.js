/** The name of the TooltipContainer component. */
echopoint.constants.TOOLTIPCONTAINER = "echopoint.TooltipContainer";

/**
 * TooltipContainer is a component that can be positioned anywhere on the screen with an specified size attributes.
 * A tooltip can be tied to the whole container area.
 */
echopoint.TooltipContainer = Core.extend(Echo.Component,
{
    $load: function()
    {
        Echo.ComponentFactory.registerType(echopoint.constants.TOOLTIPCONTAINER, this);
    },

    componentType: echopoint.constants.TOOLTIPCONTAINER
});