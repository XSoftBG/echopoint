echopoint.constants.CLOCK = "echopoint.jquery.Clock";

/**
 * Component rendering peer: echopoint.jquery.Clock
 *
 * @version $Id: Sync.Clock.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */

echopoint.ClockSync = Core.extend(Echo.Render.ComponentSync, {

    /** Properties defined for this component. */
    $static:
    {
        // Style attributes
        WIDTH: "width",
        HEIGHT: "height",
        ALIGNMENT: "alignment",
        INSETS: "insets",
        BORDER: "border",
        BACKGROUND: "background",
        FOREGROUND: "foreground",
        FONT: "font",
        FORMAT: "format",
        DEFAULT_FORMAT: "%H:%M:%S"
    },

    $load: function()
    {
        Echo.Render.registerPeer( echopoint.constants.CLOCK, this );
    },

    /**
     * Outermost/top-level container element.
     * @type Element
     */
    _clockdiv: null,


    /** @see Echo.Render.ComponentSync#renderAdd */
    renderAdd: function(update, parentElement) {
        this._clockdiv = document.createElement("div");
        this._clockdiv.id = this.component.renderId;

        var width = this.component.render(echopoint.ClockSync.WIDTH);
        var height = this.component.render(echopoint.ClockSync.HEIGHT);
        this._clockdiv.style.width = width ? width : "100%";
        this._clockdiv.style.height = height ? height : "100%";
        
        Echo.Sync.Alignment.render(this.component.render(echopoint.ClockSync.ALIGNMENT), this._clockdiv, true, this.component);
        Echo.Sync.Insets.render(this.component.render(echopoint.ClockSync.INSETS), this._clockdiv, "padding");
        Echo.Sync.Border.render(this.component.render(echopoint.ClockSync.BORDER), this._clockdiv);
        Echo.Sync.Color.renderClear( this.component.render(echopoint.ClockSync.FOREGROUND), this._clockdiv, "color");
        Echo.Sync.Color.renderClear( this.component.render(echopoint.ClockSync.BACKGROUND), this._clockdiv, "backgroundColor");
        Echo.Sync.Font.renderClear( this.component.render(echopoint.ClockSync.FONT), this._clockdiv);

        var options = { format: echopoint.ClockSync.DEFAULT_FORMAT };
        options.format = this.component.render('format', echopoint.ClockSync.DEFAULT_FORMAT);
        $(this._clockdiv).jclock(options);

        parentElement.appendChild(this._clockdiv);
    },

    /** @see Echo.Render.ComponentSync#renderDispose */
    renderDispose: function(update) {
        this._clockdiv.parentNode.removeChild(this._clockdiv);
        this._clockdiv = null;
    },

    /** @see Echo.Render.ComponentSync#renderUpdate */
    renderUpdate: function(update) {
        var element = this._clockdiv;
        var containerElement = element.parentNode;
        this.renderDispose(update);
        containerElement.removeChild(element);
        this.renderAdd(update, containerElement);
        return false; // Child elements not supported: safe to return false.
    }
});
