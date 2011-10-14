/** The name of the CarouselContainer component. */
echopoint.constants.CAROUSELCONTAINER = "echopoint.jquery.CarouselContainer";

/**
 * A component to display any content put into the container as a carousel-viewer.
 */
echopoint.CarouselContainer = Core.extend(Echo.Component,
{

    $load: function()
    {
        Echo.ComponentFactory.registerType(echopoint.constants.CAROUSELCONTAINER, this);
    },

    componentType: echopoint.constants.CAROUSELCONTAINER


});