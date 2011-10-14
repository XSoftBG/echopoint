/** The name of the RuleLine component. */
echopoint.constants.SEPARATOR = "echopoint.Separator";

/**
 * The Separator class is a Component that provides a simple separator within menus or between
 * other Components.
 */
echopoint.Separator = Core.extend(Echo.Component,
{
    $load: function()
    {
        Echo.ComponentFactory.registerType(echopoint.constants.SEPARATOR, this);
    },

    componentType: echopoint.constants.SEPARATOR
});