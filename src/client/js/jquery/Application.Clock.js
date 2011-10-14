/** The name of the Clock component. */
echopoint.constants.CLOCK = "echopoint.jquery.Clock";

/**
 * A component to display the current time
 */
echopoint.Clock = Core.extend(Echo.Component,
{
    $load: function()
    {
        Echo.ComponentFactory.registerType(echopoint.constants.CLOCK, this);
    },

    componentType: echopoint.constants.CLOCK
});