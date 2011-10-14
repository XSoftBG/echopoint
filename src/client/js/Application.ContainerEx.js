/** The name of the RuleLine component. */
echopoint.constants.CONTAINEREX = "echopoint.ContainerEx";

/**
 * ContainerEx is a component that can be positioned anywhere on the screen with an specified size attributes.
 * @version $Id: Application.ContainerEx.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.ContainerEx = Core.extend(Echo.Component,
{
    $load: function()
    {
        Echo.ComponentFactory.registerType(echopoint.constants.CONTAINEREX, this);
    },

    componentType: echopoint.constants.CONTAINEREX

});