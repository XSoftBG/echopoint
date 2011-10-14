/** The name of the ApplicationBackground component. */
echopoint.constants.APPLICATIONBACKGROUND = "echopoint.ApplicationBackground";

/**
 * A simple component to set the background image of a website
 */
echopoint.ApplicationBackground = Core.extend(Echo.Component,
{
    $load: function()
    {
        Echo.ComponentFactory.registerType(echopoint.constants.APPLICATIONBACKGROUND, this);
    },

    $static:
  {
    URL: "url"
  },

    componentType: echopoint.constants.APPLICATIONBACKGROUND
});