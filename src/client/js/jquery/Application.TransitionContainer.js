/** The name of the TransitionContainer component. */
echopoint.constants.TRANSITIONCONTAINER = "echopoint.TransitionContainer";

/**
 * A container displaying an effect when changing contents
 */
echopoint.TransitionContainer = Core.extend(Echo.Component,
{
    $static: {

        WIDTH: "width",
        HEIGHT: "height",
        
        /**
         * Default duration time (550ms).
         */
        DEFAULT_DURATION: 550,

        /**
         * Default transition type (immediate replace).
         */
        DEFAULT_TYPE: 0,

        TYPE_IMMEDIATE_REPLACE: 0,
        TYPE_BLIND: 1,
        TYPE_BOUNCE: 2,
        TYPE_CLIP: 3,
        TYPE_DROP: 4,
        TYPE_EXPLODE: 5,
        TYPE_FOLD: 6,
        TYPE_HIGHLIGHT: 7,
        TYPE_PUFF: 8,
        TYPE_PULSATE: 9,
        TYPE_SCALE: 10,
        TYPE_SHAKE: 11,
        TYPE_SIZE: 12,
        TYPE_SLIDE: 13
    },

    $load: function()
    {
        Echo.ComponentFactory.registerType(echopoint.constants.TRANSITIONCONTAINER, this);
    },

    componentType: echopoint.constants.TRANSITIONCONTAINER
});