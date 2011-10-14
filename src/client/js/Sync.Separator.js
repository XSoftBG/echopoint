echopoint.constants.SEPARATOR = "echopoint.Separator";

/**
 * Component rendering peer: echopoint.Separator
 * @author HansH 2009-04-28
 * @version $Id: Sync.Separator.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */

echopoint.SeparatorSync = Core.extend(Echo.Render.ComponentSync, {
    $load: function()
    {
        Echo.Render.registerPeer( echopoint.constants.SEPARATOR, this );
    },

    /** Properties defined for this component. */
    $static:
    {
        // Style attributes
        BOTTOMSIZE: "bottomSize",
        BOTTOMCOLOR: "bottomColor",
        TOPSIZE: "topSize",
        TOPCOLOR: "topColor",
        OUTSETS: "outsets",
        INSETS: "insets"
    },

    /**
     * Outermost/top-level separator element.
     * @type Element
     */
    _separatorDiv: null,

    /** @see Echo.Render.ComponentSync#renderAdd */
    renderAdd: function(update, parentElement) {
        this._separatorDiv = document.createElement("div");
        this._separatorDiv.id = this.component.renderId;

        Echo.Sync.Insets.render(this.component.render(echopoint.SeparatorSync.INSETS), this._separatorDiv, "padding");
        Echo.Sync.Insets.render(this.component.render(echopoint.SeparatorSync.OUTSETS), this._separatorDiv, "margin");

        var topDiv = document.createElement("div");
        this._separatorDiv.appendChild(topDiv);

        var topSize = this.component.render(echopoint.SeparatorSync.TOPSIZE);
        if (topSize) {
            topDiv.style.height = topSize;
        }
        Echo.Sync.Color.renderClear(this.component.render(echopoint.SeparatorSync.TOPCOLOR), topDiv, "backgroundColor");

        var bottomDiv = document.createElement("div");
        this._separatorDiv.appendChild(bottomDiv);

        var bottomSize = this.component.render(echopoint.SeparatorSync.BOTTOMSIZE);
        if (bottomSize) {
            bottomDiv.style.height = bottomSize;
        }
        Echo.Sync.Color.renderClear(this.component.render(echopoint.SeparatorSync.BOTTOMCOLOR), bottomDiv, "backgroundColor");

        parentElement.appendChild(this._separatorDiv);
    },


    /** @see Echo.Render.ComponentSync#renderDispose */
    renderDispose: function(update) {
        this._separatorDiv = null;
    },

    /** @see Echo.Render.ComponentSync#renderUpdate */
    renderUpdate: function(update) {
        var containerElement = this._separatorDiv.parentNode;
        Echo.Render.renderComponentDispose(update, update.parent);
        containerElement.removeChild(this._separatorDiv);
        this.renderAdd(update, containerElement);
        return true;
    }

});