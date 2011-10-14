/** The name of the DockMenu component. */
echopoint.constants.DOCKMENU = "echopoint.jquery.DockMenu";

/**
 * A component to display a Mac-style dock menu (or fisheye menu).
 * The component is using the jqDock menu
 */
echopoint.DockMenu = Core.extend(Echo.Component,
{
    /** Properties defined for this component. */
    $static:
    {
        FONT: "font",
        FOREGROUND: "foreground",
        DOCKWIDTH: "dockWidth",
        MINORSIZE: "minorSize",
        ALIGN: "align",
        DISTANCE: "distance",
        COEFFICIENT: "coefficient",
        DURATION: "duration",
        LABELS: "labels"
    },

    $load: function()
    {
        Echo.ComponentFactory.registerType(echopoint.constants.DOCKMENU, this);
    },

    componentType: echopoint.constants.DOCKMENU,

    doAction: function(actionCommand)
    {
        this.set("itemid", actionCommand);
        this.fireEvent({ type: "action", source: this, actionCommand: actionCommand });
    }

});