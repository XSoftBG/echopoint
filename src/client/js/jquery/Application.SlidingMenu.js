/** The name of the SlidingMenu component. */
echopoint.constants.SLIDINGMENU = "echopoint.jquery.SlidingMenu";

/**
 * SlidingMenu is a todo   
 */
echopoint.SLIDINGMENU = Core.extend(Echo.Component,
{
    $load: function()
    {
        Echo.ComponentFactory.registerType(echopoint.constants.SLIDINGMENU, this);
    },

    componentType: echopoint.constants.SLIDINGMENU
});