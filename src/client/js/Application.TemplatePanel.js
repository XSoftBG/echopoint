/** The name of the TemplatePanel component. */
echopoint.constants.TEMPLATEPANEL = "echopoint.TemplatePanel";

/**
 * TemplatePanel is a container that uses a TemplateLayoutData to render a template of content. 
 */
echopoint.TemplatePanel = Core.extend(Echo.Component,
{
    $load: function()
    {
        Echo.ComponentFactory.registerType(echopoint.constants.TEMPLATEPANEL, this);
    },

    componentType: echopoint.constants.TEMPLATEPANEL
});