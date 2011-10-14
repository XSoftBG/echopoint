echopoint.constants.CONTAINEREX = "echopoint.ContainerEx";

/**
 * Component rendering peer: echopoint.ContainerEx
 * @author HansH 2009-04-28
 * @version $Id: Sync.ContainerEx.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */

echopoint.ContainerExSync = Core.extend(Echo.Render.ComponentSync, {

    /** Properties defined for this component. */
    $static:
    {
        // Style attributes
        POSITION: "position",
        WIDTH: "width",
        HEIGHT: "height",
        INSETS: "insets",
        OUTSETS: "outsets",
        BORDER: "border",
        TOP: "top",
        RIGHT: "right",
        LEFT: "left",
        BOTTOM: "bottom",
        FONT: "font",
        SCROLLBARPOLICY: "scrollBarPolicy",
        LAYOUTSTYLE: "layoutStyle",
        BACKGROUND: "background",
        FOREGROUND: "foreground",
        BACKGROUNDIMAGE: "backgroundImage",
        ZINDEX: "zIndex",
        ALIGNMENT: "alignment",
        HEIGHTSTRETCHED: "heightStretched",
        MINHEIGHTSTRETCHED: "minimumStretchedHeight",
        MAXHEIGHTSTRETCHED: "maximumStretchedHeight"
    },

    $load: function()
    {

        Echo.Render.registerPeer( echopoint.constants.CONTAINEREX, this );
    },

    /**
     * Outermost/top-level container element.
     * @type Element
     */
    _containerDiv: null,
    _containerTr: null,


    /** @see Echo.Render.ComponentSync#renderAdd */
    renderAdd: function(update, parentElement) {
        this._containerDiv = document.createElement("div");
        this._containerDiv.style.outlineStyle = "none";

        this._containerDiv.id = this.component.renderId;
        var i, postition = this.component.render(echopoint.ContainerExSync.POSITION);
        if (postition == 1) {
            this._containerDiv.style.position = "static";
        }
        else if (postition == 2) {
            this._containerDiv.style.position = "absolute";
        }
        else if (postition == 8) {
                this._containerDiv.style.position = "fixed";
            }
            else {
                this._containerDiv.style.position = "relative";
            }
        var width = this.component.render(echopoint.ContainerExSync.WIDTH);
        var height = this.component.render(echopoint.ContainerExSync.HEIGHT);
        if (width) {
            this._containerDiv.style.width = width;
        }
        //        else {
        //            this._containerDiv.style.width = "100%";
        //        }
        if (height) {
            this._containerDiv.style.height = height;
        }
        //        else {
        //            this._containerDiv.style.height = "100%";
        //        }
        var top = this.component.render(echopoint.ContainerExSync.TOP);
        if (top) {
            this._containerDiv.style.top = top;
        }
        var right = this.component.render(echopoint.ContainerExSync.RIGHT);
        if (right) {
            this._containerDiv.style.right = right;
        }
        var bottom = this.component.render(echopoint.ContainerExSync.BOTTOM);
        if (bottom) {
            this._containerDiv.style.bottom = bottom;
        }
        var left = this.component.render(echopoint.ContainerExSync.LEFT);
        if (left) {
            this._containerDiv.style.left = left;
        }
        var zIndex = this.component.render(echopoint.ContainerExSync.ZINDEX);
        if (zIndex) {
            this._containerDiv.style.zIndex = zIndex;
        }
        else {
            this._containerDiv.style.zIndex = 0;
        }
        var scrollBarPolicy = this.component.render(echopoint.ContainerExSync.SCROLLBARPOLICY);
        if (scrollBarPolicy == 1) {  // NEVER
            this._containerDiv.style.overflow = "visible";
        }
        if (scrollBarPolicy == 2) {  // ALWAYS
            this._containerDiv.style.overflow = "scroll";
        }
        if (scrollBarPolicy == 4) {  // AUTO
            this._containerDiv.style.overflow = "auto";
        }
        if (scrollBarPolicy == 8) {  // CLIPHIDE
            this._containerDiv.style.overflow = "hidden";
        }

        Echo.Sync.renderComponentDefaults(this.component, this._containerDiv);

        var flags = this.component.render("ieAlphaRenderBorder") ?
                    Echo.Sync.FillImage.FLAG_ENABLE_IE_PNG_ALPHA_FILTER : 0;

        this.layoutStyle = this.component.render(echopoint.ContainerExSync.LAYOUTSTYLE);
        var background = this.component.render(echopoint.ContainerExSync.BACKGROUND);
        var foreground = this.component.render(echopoint.ContainerExSync.FOREGROUND);
        var backgroundImage = this.component.render(echopoint.ContainerExSync.BACKGROUNDIMAGE);
        if (!backgroundImage && background) {
            Echo.Sync.Color.renderClear(background, this._containerDiv, "backgroundColor");
        }
        Echo.Sync.FillImage.render(backgroundImage, this._containerDiv, flags);

        Echo.Sync.Insets.render(this.component.render(echopoint.ContainerExSync.INSETS), this._containerDiv, "padding");
        Echo.Sync.Insets.render(this.component.render(echopoint.ContainerExSync.OUTSETS), this._containerDiv, "margin");
        Echo.Sync.Border.render(this.component.render(echopoint.ContainerExSync.BORDER), this._containerDiv);
        Echo.Sync.Color.renderClear(foreground, this._containerDiv, "color");

        Echo.Sync.Font.renderClear(this.component.render(echopoint.ContainerExSync.FONT), this._containerDiv);

        parentElement.appendChild(this._containerDiv);
        if (this.layoutStyle == 2) {  // Row layout
            var table = document.createElement("table");
            table.style.borderCollapse = "collapse";
            this._containerDiv.appendChild(table);

            var tbody = document.createElement("tbody");
            tbody.style.paddingBottom = "0px";
            tbody.style.paddingLeft = "0px";
            tbody.style.paddingTop = "0px";
            tbody.style.paddingRight = "0px";
            table.appendChild(tbody);

            this._containerTr = document.createElement("tr");
            tbody.appendChild(this._containerTr);
            Echo.Sync.Alignment.render(this.component.render(echopoint.ContainerExSync.ALIGNMENT), table, true, this.component);
        }
        else {
            Echo.Sync.Alignment.render(this.component.render(echopoint.ContainerExSync.ALIGNMENT), this._containerDiv, true, this.component);
        }

        var componentCount = this.component.getComponentCount();
        for (i = 0; i < componentCount; ++i) {
            var child = this.component.getComponent(i);
            this._renderAddChild(update, child);
        }

        this._renderRequired = true;

        // do they want us to stretch outselves

    },


    renderDisplay: function() {
        if (this._renderRequired) {
            this._renderRequired = false;
            var heightStretched = this.component.render(echopoint.ContainerExSync.HEIGHTSTRETCHED, false );
            if (heightStretched) {
                var minHeight = this.component.render(echopoint.ContainerExSync.MINHEIGHTSTRETCHED);
                var maxHeight = this.component.render(echopoint.ContainerExSync.MAXHEIGHTSTRETCHED);
                this.stretch(minHeight, maxHeight);
            }
        }
    },


    /**
     * Renders the addition of a child component.
     *
     * @param {Echo.Update.ComponentUpdate} the update
     * @param {Echo.Component} child the child component to
     * add
     */
    _renderAddChild: function(update, child) {
        if (this.layoutStyle == 2) {    // Row
            var childTd = document.createElement("td");
            childTd.style.paddingBottom = "0px";
            childTd.style.paddingLeft = "0px";
            childTd.style.paddingTop = "0px";
            childTd.style.paddingRight = "0px";
            Echo.Sync.Alignment.render(this.component.render(echopoint.ContainerExSync.ALIGNMENT), childTd, false, this.component);

            Echo.Render.renderComponentAdd(update, child, childTd);
            this._containerTr.appendChild(childTd);
        }
        else if (this.layoutStyle == 1) {    // Column
            var childDiv = document.createElement("div");
            Echo.Sync.Alignment.render(this.component.render(echopoint.ContainerExSync.ALIGNMENT), childDiv, true, this.component);
            Echo.Render.renderComponentAdd(update, child, childDiv);
            this._containerDiv.appendChild(childDiv);
        }
        else {
            Echo.Render.renderComponentAdd(update, child, this._containerDiv);
        }
    },

    /** @see Echo.Render.ComponentSync#renderDispose */
    renderDispose: function(update) {
        this._containerDiv = null;
        this._containerTr = null;
    },

    /** @see Echo.Render.ComponentSync#renderUpdate */
    renderUpdate: function(update) {
        var i, fullRender = false;
        if (update.hasUpdatedProperties() || update.hasUpdatedLayoutDataChildren()) {
            // Full render
            fullRender = true;
        } else {
            var removedChildren = update.getRemovedChildren();
            if (removedChildren) {
                fullRender = true;
            }
            var addedChildren = update.getAddedChildren();
            if (addedChildren && !fullRender) {
                //  Add children.
                for (i = 0; i < addedChildren.length; ++i) {
                    this._renderAddChild(update, addedChildren[i], this.component.indexOf(addedChildren[i]));
                }
            }
        }
        if (fullRender) {
            var element = this._containerDiv;
            var containerElement = element.parentNode;
            Echo.Render.renderComponentDispose(update, update.parent);
            containerElement.removeChild(element);
            this.renderAdd(update, containerElement);
        }

        return fullRender;
    },

    /**
     * Returns the height of the content of parentE.
     */
    getContentHeight : function(parentE) {
        var contentHeight = 0;
        for (var i = 0; i < parentE.childNodes.length; i++) {
            var node = parentE.childNodes[i];
            // Ignore text, floated, and absolutely positioned nodes
            if (node.nodeType === 1) {
                var s = node.style;
                if (!s.styleFloat && !s.cssFloat && s.position != "absolute" && s.position != "fixed") {
                    contentHeight += node.offsetHeight;
                }
            }
        }
        return contentHeight;
    },

    stretch: function(minHeight, maxHeight) {
        this.minHeight = parseInt(minHeight, 10);
        this.maxHeight = parseInt(maxHeight, 10);
        if (! isNaN(this.minHeight) && ! isNaN(this.maxHeight)) {
            this.minHeight = Math.min(this.minHeight,this.maxHeight);
            this.maxHeight = Math.max(this.minHeight,this.maxHeight);
        }
        var element = this._containerDiv;
        if (! element) {
            return;
        }
        // Find nearest parent with a specified height,
        // or absolute positioning (could have top & bottom specified)
        for (var parentE = element.parentNode; parentE; parentE = parentE.parentNode) {
            var s = parentE.style;
            if (s.height || ((s.position == "absolute" || s.position == "fixed") && s.top && s.bottom)) {
                break;
            }
        }

        if (! parentE) {
            return;
        }
        var parentStyle = parentE.style;

        // Temporarily override overflow to stop scrollbar flash
        var eStyle = element.style;
        var saveOverflow = eStyle.overflow;
        var saveParentOverflow = parentStyle.overflow;
        eStyle.overflow = parentStyle.overflow = "hidden";

        // Ensure we are using pixels
        if (!Core.Web.VirtualPosition._verifyPixelValue(eStyle.height)) {
            eStyle.height = element.clientHeight + "px";
        }

        // Determine parentE's available height and current height of all child content
        var availableHeight = parentE.clientHeight;
        var contentHeight = this.getContentHeight(parentE);

        // Adjust the child element's height to the "gap" between available and content height
        var newHeight = Math.max(0, parseInt(eStyle.height, 10) + availableHeight - contentHeight);

        // respect max and min height if they are present
        var respectedLimits = false;
        if (this.minHeight && ! isNaN(this.minHeight) && newHeight < this.minHeight) {
            newHeight = this.minHeight;
            respectedLimits = true;
        }
        if (this.maxHeight && ! isNaN(this.maxHeight) && newHeight > this.maxHeight) {
            newHeight = this.maxHeight;
            respectedLimits = true;
        }
        //
        // we allow users to "customise" the desired height "before" we set it.  This do this
        // by providing a optional "stretchCallback" function
        //        if (this.stretchBeforeCallbackFunction) {
        //            newHeight = this.stretchBeforeCallbackFunction(newHeight, this.stretchBeforeCallbackContext, this);
        //        }
        if (newHeight > 0) {
            //
            // now set the height into the element
            eStyle.height = newHeight + "px";

            if (! respectedLimits) {
                // Firefox sometimes does not include margins in offsetHeight so adjust again if we created some overflow
                eStyle.height = Math.max(0, newHeight + parentE.offsetHeight - parentE.scrollHeight) + "px";
            }
        }
        //
        // restore the over flow back to the previous values
        eStyle.overflow = saveOverflow;
        parentStyle.overflow = saveParentOverflow;
    }

});