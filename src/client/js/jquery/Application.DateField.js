/** The name of the DateField component. */
echopoint.constants.DATEFIELD = "echopoint.jquery.DateField";

/**
 * DateField is a drop down component that contains a text field
 * and a drop down calendar.
 */
echopoint.DATEFIELD = Core.extend(Echo.Component,
{
    $load: function()
    {
        Echo.ComponentFactory.registerType(echopoint.constants.DATEFIELD, this);
    },
    
    doAction: function() {
        this.fireEvent({type: "action", source: this, actionCommand: this.get("actionCommand")});
    },

    componentType: echopoint.constants.DATEFIELD
});