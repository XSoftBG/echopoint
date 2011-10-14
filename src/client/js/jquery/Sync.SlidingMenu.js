echopoint.constants.SLIDINGMENU = "echopoint.jquery.SlidingMenu";

/**
 * Component rendering peer: echopoint.SlidingMenu
 */

echopoint.SLIDINGMENU = Core.extend(Echo.Render.ComponentSync, {

    /** Properties defined for this component. */
    $static:
    {
        // Style attributes
        POSITION: "position",
        WIDTH: "width",
        BUTTONWIDTH: "buttonWidth",
        SLIDERWIDTH: "sliderWidth",
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
        BACKGROUND: "background",
        FOREGROUND: "foreground",
        BACKGROUNDIMAGE: "backgroundImage",
        ZINDEX: "zIndex",
        ROLLOVERICON: "rolloverIcon",
        PRESSEDICON: "pressedIcon",
        ICON: "icon",
        ALIGNMENT: "alignment"
    },

    $load: function()
    {

        Echo.Render.registerPeer( echopoint.constants.SLIDINGMENU, this );
    },

    /**
     * Outermost/top-level container element.
     * @type Element
     */
    _containerDiv: null,
    slidingMenuLI: null,
    defaultContentLI: null,
    sliderDiv: null,
    slidingMenuState: null,
    imgElement: null,
    scrollSpeed: null,
    defaultIcon: null,
    rolloverIcon: null,
    pressedIcon: null,


    /** @see Echo.Render.ComponentSync#renderAdd */
    renderAdd: function(update, parentElement) {
        this._containerDiv = document.createElement("div");
        this._containerDiv.style.outlineStyle = "none";

        this._containerDiv.id = this.component.renderId;
        var i, postition = this.component.render(echopoint.SLIDINGMENU.POSITION);
        if (postition == 1) {
            this._containerDiv.style.position = "static";
        }
        else if (postition == 2) {
            this._containerDiv.style.position = "absolute";
        }
        else if (postition == 8) {
                this._containerDiv.style.position = "fixed";
            }
            else { // if (postition == 4) {
                this._containerDiv.style.position = "relative";
            }
        var width = this.component.render(echopoint.SLIDINGMENU.WIDTH);
        var buttonWidth = this.component.render(echopoint.SLIDINGMENU.BUTTONWIDTH);
        var height = this.component.render(echopoint.SLIDINGMENU.HEIGHT);
        if (!width) {
            width = "300px";
        }
        var sliderWidth = this.component.render(echopoint.SLIDINGMENU.SLIDERWIDTH);
        if (!sliderWidth) {
            sliderWidth = "270px";
        }
        this._containerDiv.style.width = width;
        if (!height) {
            height = "300px";
        }
        this._containerDiv.style.height = height;
        var top = this.component.render(echopoint.SLIDINGMENU.TOP);
        if (top) {
            this._containerDiv.style.top = top;
        }
        var right = this.component.render(echopoint.SLIDINGMENU.RIGHT);
        if (right) {
            this._containerDiv.style.right = right;
        }
        var bottom = this.component.render(echopoint.SLIDINGMENU.BOTTOM);
        if (bottom) {
            this._containerDiv.style.bottom = bottom;
        }
        var left = this.component.render(echopoint.SLIDINGMENU.LEFT);
        if (left) {
            this._containerDiv.style.left = left;
        }
        var zIndex = this.component.render(echopoint.SLIDINGMENU.ZINDEX);
        if (zIndex) {
            this._containerDiv.style.zIndex = zIndex;
        }
        else {
            this._containerDiv.style.zIndex = 0;
        }
        var scrollBarPolicy = this.component.render(echopoint.SLIDINGMENU.SCROLLBARPOLICY);
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

        this.layoutStyle = this.component.render(echopoint.SLIDINGMENU.LAYOUTSTYLE);
        var background = this.component.render(echopoint.SLIDINGMENU.BACKGROUND);
        var foreground = this.component.render(echopoint.SLIDINGMENU.FOREGROUND);
        var backgroundImage = this.component.render(echopoint.SLIDINGMENU.BACKGROUNDIMAGE);
        if (!backgroundImage && background) {
            Echo.Sync.Color.renderClear(background, this._containerDiv, "backgroundColor");
        }
        Echo.Sync.FillImage.render(backgroundImage, this._containerDiv, flags);

        Echo.Sync.Insets.render(this.component.render(echopoint.SLIDINGMENU.INSETS), this._containerDiv, "padding");
        Echo.Sync.Insets.render(this.component.render(echopoint.SLIDINGMENU.OUTSETS), this._containerDiv, "margin");
        Echo.Sync.Border.render(this.component.render(echopoint.SLIDINGMENU.BORDER), this._containerDiv);
        Echo.Sync.Color.renderClear(foreground, this._containerDiv, "color");

        Echo.Sync.Font.renderClear(this.component.render(echopoint.SLIDINGMENU.FONT), this._containerDiv);

        parentElement.appendChild(this._containerDiv);

        var table = document.createElement("table");
        table.style.borderCollapse = "collapse";
        this._containerDiv.appendChild(table);

        var tbody = document.createElement("tbody");
        table.appendChild(tbody);

        var containerTr = document.createElement("tr");
        tbody.appendChild(containerTr);
        Echo.Sync.Alignment.render(this.component.render(echopoint.SLIDINGMENU.ALIGNMENT), table, true, this.component);


        var buttonTd = document.createElement("td");
        Echo.Sync.Alignment.render(this.component.render(echopoint.SLIDINGMENU.ALIGNMENT), buttonTd, true, this.component);
        containerTr.appendChild(buttonTd);

        this.slidingMenuState = false;

        var buttonDiv = document.createElement("div");
        //        buttonDiv.id = "slidingMenuButton";
        buttonTd.appendChild(buttonDiv);
        buttonDiv.style.width = buttonWidth;
        buttonDiv.style.height = "100%";

        this.imgElement = document.createElement("img");
        this.defaultIcon = this.component.render(echopoint.SLIDINGMENU.ICON);
        this.rolloverIcon = this.component.render(echopoint.SLIDINGMENU.ROLLOVERICON);
        this.pressedIcon = this.component.render(echopoint.SLIDINGMENU.PRESSEDICON);
        Echo.Sync.ImageReference.renderImg(this.defaultIcon, this.imgElement);
        buttonDiv.appendChild(this.imgElement);
        Core.Web.Event.add(this.imgElement, "click", Core.method(this, this._processClick), false);
        Core.Web.Event.add(this.imgElement, "mouseover", Core.method(this, this._processMouseOver), false);
        Core.Web.Event.add(this.imgElement, "mouseout", Core.method(this, this._processMouseOut), false);

        var sliderTd = document.createElement("td");
        containerTr.appendChild(sliderTd);

        this.sliderDiv = document.createElement("div");
        this.sliderDiv.id = this.component.renderId + "slider";
        sliderTd.appendChild(this.sliderDiv);
        this.sliderDiv.style.width = sliderWidth;
        this.sliderDiv.style.height = height;
        this.sliderDiv.style.overflow = "hidden";

        var sliderUL = document.createElement("ul");
        this.sliderDiv.appendChild(sliderUL);
        sliderUL.style.listStyle = "none";
        sliderUL.style.width = sliderWidth;
        sliderUL.style.height = height;
        sliderUL.style.overflow = "hidden";
        sliderUL.style.margin = "0px";
        sliderUL.style.padding = "0px";

        this.slidingMenuLI = document.createElement("li");
        this.slidingMenuLI.style.listStyle = "none";
        this.slidingMenuLI.style.width = sliderWidth;
        this.slidingMenuLI.style.height = height;
        this.slidingMenuLI.style.overflow = "hidden";
        this.slidingMenuLI.style.margin = "0px";
        this.slidingMenuLI.style.padding = "0px";
        sliderUL.appendChild(this.slidingMenuLI);

        this.defaultContentLI = document.createElement("li");
        this.defaultContentLI.style.listStyle = "none";
        this.defaultContentLI.style.width = sliderWidth;
        this.defaultContentLI.style.height = height;
        this.defaultContentLI.style.overflow = "hidden";
        this.defaultContentLI.style.margin = "0px";
        this.defaultContentLI.style.padding = "0px";
        sliderUL.appendChild(this.defaultContentLI);

        this._renderRequired = true;
        this.firstComponent = true;

        var componentCount = this.component.getComponentCount();
        for (i = 0; i < componentCount; ++i) {
            var child = this.component.getComponent(i);
            this._renderAddChild(update, child);
        }

    },

    _processClick: function(e) {
        if (!this.client || !this.client.verifyInput(this.component)) {
            return true;
        }
        this.moveMenu();
    },

    moveMenu:  function(e) {
        obj = jQuery("#"+this.sliderDiv.id.replace('.', '\\.'));
        var s = jQuery("li", obj).length;
        var w = obj.width();
        var h = obj.height();
        var ts = s-1;
        var t = 0;
        jQuery("ul", obj).css('width',s*w);
        jQuery("li", obj).css('float','left');

        if (this.slidingMenuState) {
            Echo.Sync.ImageReference.renderImg(this.pressedIcon, this.imgElement);
            this.slidingMenuState = false;
            t = (t<=0) ? 0 : t-1;
//            if (Core.Web.Env.BROWSER_INTERNET_EXPLORER && Core.Web.Env.BROWSER_VERSION_MAJOR == 6) {
            if (Core.Web.Env.BROWSER_INTERNET_EXPLORER) {
                this.defaultContentLI.style.visibility = "hidden";
                this.slidingMenuLI.style.visibility = "visible";
            }
        }
        else {
            Echo.Sync.ImageReference.renderImg(this.defaultIcon, this.imgElement);
            this.slidingMenuState = true;
            t = (t>=ts) ? ts : t+1;
//            if (Core.Web.Env.BROWSER_INTERNET_EXPLORER && Core.Web.Env.BROWSER_VERSION_MAJOR == 6) {
            if (Core.Web.Env.BROWSER_INTERNET_EXPLORER) {
                this.slidingMenuLI.style.visibility = "hidden";
                this.defaultContentLI.style.visibility = "visible";
            }
        }

        p = (t*w*-1);
        jQuery("ul",obj).animate(
        { marginLeft: p },
                this.scrollSpeed
                );
    },

    _processMouseOver: function(e) {
        if (!this.client || !this.client.verifyInput(this.component)) {
            return;
        }
        Echo.Sync.ImageReference.renderImg(this.rolloverIcon, e.target);
    },

    _processMouseOut: function(e) {
        if (!this.client || !this.client.verifyInput(this.component)) {
            return;
        }
        if (this.slidingMenuState) {
            Echo.Sync.ImageReference.renderImg(this.defaultIcon, e.target);
        }
        else {
            Echo.Sync.ImageReference.renderImg(this.pressedIcon, e.target);            
        }
    },

    renderDisplay: function() {
        if (this._renderRequired) {
            this._renderRequired = false;
            this.scrollSpeed = 0;
            this.moveMenu();
            this.scrollSpeed = 500;
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
        if (this.firstComponent) {
            this.firstComponent = false;
            Echo.Render.renderComponentAdd(update, child, this.slidingMenuLI);
        }
        else {
            Echo.Render.renderComponentAdd(update, child, this.defaultContentLI);
        }
    },

    /** @see Echo.Render.ComponentSync#renderDispose */
    renderDispose: function(update) {
        this._containerDiv = null;
        this.slidingMenuLI = null;
        this.defaultContentLI = null;
        this.sliderDiv = null;
        this.slidingMenuState = null;
        this.imgElement = null;
        this.scrollSpeed = null;
        this.defaultIcon = null;
        this.rolloverIcon = null;
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
            containerElement.removeChild(element);
            Echo.Render.renderComponentDispose(update, update.parent);
            this.renderAdd(update, containerElement);
        }

        return fullRender;
    }

});