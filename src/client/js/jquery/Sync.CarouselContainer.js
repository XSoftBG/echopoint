echopoint.constants.CAROUSELCONTAINER = "echopoint.jquery.CarouselContainer";

/**
 * Component rendering peer: echopoint.CarouselContainerSync
 * @author HansH 2009-07-09
 * @version $Id: Sync.CarouselContainer.js,v 1.3 2010-12-21 18:07:55 perxi Exp $
 */

echopoint.CarouselContainerSync = Core.extend(Echo.Render.ComponentSync, {

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
        MAXHEIGHTSTRETCHED: "maximumStretchedHeight",
        LEFTICON: "leftIcon",
        RIGHTICON: "rightIcon",
        LEFTICONOVER: "leftIconOver",
        RIGHTICONOVER: "rightIconOver",
        VISIBLE: "visible",
        CIRCULAR: "circular",
        SPEED: "speed"
    },

    $load: function()
    {

        Echo.Render.registerPeer(echopoint.constants.CAROUSELCONTAINER, this);
    },

    /**
     * Outermost/top-level container element.
     * @type Element
     */
    _containerDiv: null,
    _containerUl: null,
    leftIconUrl: null,
    leftIconOverUrl: null,
    rightIconUrl: null,
    rightIconOverUrl: null,
    componentCounter: 0,


    /** @see Echo.Render.ComponentSync#renderAdd */
    renderAdd: function(update, parentElement) {
        this._containerDiv = document.createElement("div");
        this._containerDiv.style.outlineStyle = "none";

        //        this._containerDiv.id = this.component.renderId;
        var i, postition = this.component.render(echopoint.CarouselContainerSync.POSITION);
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
        var width = this.component.render(echopoint.CarouselContainerSync.WIDTH);
        var height = this.component.render(echopoint.CarouselContainerSync.HEIGHT);
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
        var top = this.component.render(echopoint.CarouselContainerSync.TOP);
        if (top) {
            this._containerDiv.style.top = top;
        }
        var right = this.component.render(echopoint.CarouselContainerSync.RIGHT);
        if (right) {
            this._containerDiv.style.right = right;
        }
        var bottom = this.component.render(echopoint.CarouselContainerSync.BOTTOM);
        if (bottom) {
            this._containerDiv.style.bottom = bottom;
        }
        var left = this.component.render(echopoint.CarouselContainerSync.LEFT);
        if (left) {
            this._containerDiv.style.left = left;
        }
        var zIndex = this.component.render(echopoint.CarouselContainerSync.ZINDEX);
        if (zIndex) {
            this._containerDiv.style.zIndex = zIndex;
        }
        else {
            this._containerDiv.style.zIndex = 0;
        }
        var scrollBarPolicy = this.component.render(echopoint.CarouselContainerSync.SCROLLBARPOLICY);
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

        this.layoutStyle = this.component.render(echopoint.CarouselContainerSync.LAYOUTSTYLE);
        var background = this.component.render(echopoint.CarouselContainerSync.BACKGROUND);
        var foreground = this.component.render(echopoint.CarouselContainerSync.FOREGROUND);
        var backgroundImage = this.component.render(echopoint.CarouselContainerSync.BACKGROUNDIMAGE);
        if (!backgroundImage && background) {
            Echo.Sync.Color.renderClear(background, this._containerDiv, "backgroundColor");
        }
        Echo.Sync.FillImage.render(backgroundImage, this._containerDiv, flags);

        Echo.Sync.Insets.render(this.component.render(echopoint.CarouselContainerSync.INSETS), this._containerDiv, "padding");
        Echo.Sync.Insets.render(this.component.render(echopoint.CarouselContainerSync.OUTSETS), this._containerDiv, "margin");
        Echo.Sync.Border.render(this.component.render(echopoint.CarouselContainerSync.BORDER), this._containerDiv);
        Echo.Sync.Color.renderClear(foreground, this._containerDiv, "color");

        Echo.Sync.Font.renderClear(this.component.render(echopoint.CarouselContainerSync.FONT), this._containerDiv);

        parentElement.appendChild(this._containerDiv);
        Echo.Sync.Alignment.render(this.component.render(echopoint.CarouselContainerSync.ALIGNMENT), this._containerDiv, true, this.component);

        var table = document.createElement("table");
        table.style.borderCollapse = "collapse";
        this._containerDiv.appendChild(table);

        var tbody = document.createElement("tbody");
        tbody.style.paddingBottom = "0px";
        tbody.style.paddingLeft = "0px";
        tbody.style.paddingTop = "0px";
        tbody.style.paddingRight = "0px";
        table.appendChild(tbody);

        var containerTr = document.createElement("tr");
        tbody.appendChild(containerTr);
        Echo.Sync.Alignment.render(this.component.render(echopoint.CarouselContainerSync.ALIGNMENT), table, true, this.component);

        var childTd1 = document.createElement("td");
        childTd1.style.paddingBottom = "0px";
        childTd1.style.paddingLeft = "0px";
        childTd1.style.paddingTop = "0px";
        childTd1.style.paddingRight = "0px";
        Echo.Sync.Alignment.render(this.component.render(echopoint.CarouselContainerSync.ALIGNMENT), childTd1, false, this.component);
        containerTr.appendChild(childTd1);


        var leftIcon = this.component.render(echopoint.CarouselContainerSync.LEFTICON);
        if (leftIcon) {
            this.imgElement = document.createElement("img");
            this.imgElement.id = this.component.renderId + "left";
            Echo.Sync.ImageReference.renderImg(leftIcon, this.imgElement);
            childTd1.appendChild(this.imgElement);
            this.leftIconUrl = Echo.Sync.ImageReference.getUrl(leftIcon);
        }
        else {
            var buttonLeft = document.createElement("button");
            buttonLeft.id = this.component.renderId + "left";
            childTd1.appendChild(buttonLeft);
            buttonLeft.appendChild(document.createTextNode("<<"));
        }

        var ulDiv = document.createElement("div");
        ulDiv.style.outlineStyle = "none";
        ulDiv.id = this.component.renderId;

        this._containerUl = document.createElement("ul");
        var childTd2 = document.createElement("td");
        childTd2.style.paddingBottom = "0px";
        childTd2.style.paddingLeft = "0px";
        childTd2.style.paddingTop = "0px";
        childTd2.style.paddingRight = "0px";
        Echo.Sync.Alignment.render(this.component.render(echopoint.CarouselContainerSync.ALIGNMENT), childTd2, false, this.component);
        containerTr.appendChild(childTd2);
        childTd2.appendChild(ulDiv);
        ulDiv.appendChild(this._containerUl);

        var childTd3 = document.createElement("td");
        childTd3.style.paddingBottom = "0px";
        childTd3.style.paddingLeft = "0px";
        childTd3.style.paddingTop = "0px";
        childTd3.style.paddingRight = "0px";
        Echo.Sync.Alignment.render(this.component.render(echopoint.CarouselContainerSync.ALIGNMENT), childTd3, false, this.component);
        containerTr.appendChild(childTd3);


        var rightIcon = this.component.render(echopoint.CarouselContainerSync.RIGHTICON);
        if (rightIcon) {
            this.imgElement = document.createElement("img");
            this.imgElement.id = this.component.renderId + "right";
            Echo.Sync.ImageReference.renderImg(rightIcon, this.imgElement);
            childTd3.appendChild(this.imgElement);
            this.rightIconUrl = Echo.Sync.ImageReference.getUrl(rightIcon);
        }
        else {
            var buttonRight = document.createElement("button");
            buttonRight.id = this.component.renderId + "right";
            childTd3.appendChild(buttonRight);
            buttonRight.appendChild(document.createTextNode(">>"));
        }

        var componentCount = this.component.getComponentCount();
        for (i = 0; i < componentCount; ++i) {
            var child = this.component.getComponent(i);
            this._renderAddChild(update, child);
        }

        var leftIconOver = this.component.render(echopoint.CarouselContainerSync.LEFTICONOVER);
        if (leftIconOver)
        {
            this.leftIconOverUrl = Echo.Sync.ImageReference.getUrl(leftIconOver);
        }

        var rightIconOver = this.component.render(echopoint.CarouselContainerSync.RIGHTICONOVER);
        if (rightIconOver)
        {
            this.rightIconOverUrl = Echo.Sync.ImageReference.getUrl(rightIconOver);
        }

        this._renderRequired = true;

        // do they want us to stretch outselves

    },


    renderDisplay: function() {
        if (this._renderRequired) {
            //            alert("CarouselContainer");
            this._renderRequired = false;
            var heightStretched = this.component.render(echopoint.CarouselContainerSync.HEIGHTSTRETCHED, false);
            if (heightStretched) {
                var minHeight = this.component.render(echopoint.CarouselContainerSync.MINHEIGHTSTRETCHED);
                var maxHeight = this.component.render(echopoint.CarouselContainerSync.MAXHEIGHTSTRETCHED);
                this.stretch(minHeight, maxHeight);
            }
            var visible = this.component.render(echopoint.CarouselContainerSync.VISIBLE);
            if (!visible) {
                visible = 3;
            }
                                                                                
            var circular = this.component.render(echopoint.CarouselContainerSync.CIRCULAR);

            if (this.leftIconOverUrl)
            {
                jQuery("#" + this.component.renderId.replace('.', '\\.') + "left").hover(
                    jQuery.context(this).callback(function() { jQuery("#" + this.component.renderId.replace('.', '\\.') + "left").attr( 'src', this.leftIconOverUrl ); }),
                    jQuery.context(this).callback(function() { jQuery("#" + this.component.renderId.replace('.', '\\.') + "left").attr( 'src', this.leftIconUrl ); })
                );

                if (this.componentCounter <= visible)
                    jQuery("#" + this.component.renderId.replace('.', '\\.') + "left").css('visibility', 'hidden');
                else
                    jQuery("#" + this.component.renderId.replace('.', '\\.') + "left").css('visibility', 'visible');
            }

            if (this.rightIconOverUrl)
            {
                jQuery("#" + this.component.renderId.replace('.', '\\.') + "right").hover(
                    jQuery.context(this).callback(function() { jQuery("#" + this.component.renderId.replace('.', '\\.') + "right").attr( 'src', this.rightIconOverUrl ); }),
                    jQuery.context(this).callback(function() { jQuery("#" + this.component.renderId.replace('.', '\\.') + "right").attr( 'src', this.rightIconUrl ); })
                );


                if (this.componentCounter <= visible)
                    jQuery("#" + this.component.renderId.replace('.', '\\.') + "right").hide();
                else
                    jQuery("#" + this.component.renderId.replace('.', '\\.') + "right").show();
            }

            var scrollSpeed = this.component.render(echopoint.CarouselContainerSync.SPEED);
            if (!scrollSpeed) {
                scrollSpeed = 200;
            }

            jQuery("#" + this.component.renderId.replace('.', '\\.')).jCarouselLite({
                btnNext: "#" + this.component.renderId.replace('.', '\\.') + "right",   
                btnPrev: "#" + this.component.renderId.replace('.', '\\.') + "left",
                visible: visible,
                circular: circular,
                speed: scrollSpeed,
                mouseWheel: true
            });
        }
    },


    /**
     * Renders the addition of a child component.
     *date
     * @param {Echo.Update.ComponentUpdate} update
     * @param {Echo.Component} child the child component to
     * add
     */
    _renderAddChild: function(update, child) {
        var childListItem = document.createElement("li");
        Echo.Render.renderComponentAdd(update, child, childListItem);
        this._containerUl.appendChild(childListItem);
        this.componentCounter++;
    },

    /** @see Echo.Render.ComponentSync#renderDispose */
    renderDispose: function(update) {
        this._containerDiv = null;
        this.componentCounter = 0;
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
                this.componentCounter = 0;
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
            this.minHeight = Math.min(this.minHeight, this.maxHeight);
            this.maxHeight = Math.max(this.minHeight, this.maxHeight);
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