echopoint.constants.TOOLTIPCONTAINER = "echopoint.TooltipContainer";

/**
 * Component rendering peer: echopoint.TooltipContainer
 * @version $Id: Sync.TooltipContainer.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */

echopoint.TooltipContainerSync = Core.extend(Echo.Render.ComponentSync, {

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
        TOOLTIP: "toolTipText",
        POSITIONTOOLTIP: "positionTooltip",
        POSITIONTARGET: "positionTarget",
        TOOLTIPBORDERCOLOR: "tooltipBorderColor",
        TOOLTIPBORDERWIDTH: "tooltipBorderWidth",
        TOOLTIPBORDERRADIUS: "tooltipBorderRadius",
        TOOLTIPBACKGROUNDCOLOR: "tooltipBackground",
        TOOLTIPFOREGROUNDCOLOR: "tooltipForeground",
        TOOLTIPINSETS: "tooltipInsets",
        TOOLTIPALIGNMENT: "tooltipAlignment",
        TOOLTIPSTYLENAME: "tooltipStyle",
        THUMBNAIL: "thumbnail",
        VIDEO: "video",
        HIDEDELAY: "hideDelay",
        SOLO: "solo"
    },

    $load: function()
    {

        Echo.Render.registerPeer( echopoint.constants.TOOLTIPCONTAINER, this );
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
        var i, postition = this.component.render(echopoint.TooltipContainerSync.POSITION);
        if (postition == 1) {
            this._containerDiv.style.position = "static";
        }
        else if (postition == 2) {
            this._containerDiv.style.position = "absolute";
        }
        else if (postition == 4) {
                this._containerDiv.style.position = "relative";
            }
            else if (postition == 8) {
                    this._containerDiv.style.position = "fixed";
                }
        var width = this.component.render(echopoint.TooltipContainerSync.WIDTH);
        var height = this.component.render(echopoint.TooltipContainerSync.HEIGHT);
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
        var top = this.component.render(echopoint.TooltipContainerSync.TOP);
        if (top) {
            this._containerDiv.style.top = top;
        }
        var right = this.component.render(echopoint.TooltipContainerSync.RIGHT);
        if (right) {
            this._containerDiv.style.right = right;
        }
        var bottom = this.component.render(echopoint.TooltipContainerSync.BOTTOM);
        if (bottom) {
            this._containerDiv.style.bottom = bottom;
        }
        var left = this.component.render(echopoint.TooltipContainerSync.LEFT);
        if (left) {
            this._containerDiv.style.left = left;
        }
        var zIndex = this.component.render(echopoint.TooltipContainerSync.ZINDEX);
        if (zIndex) {
            this._containerDiv.style.zIndex = zIndex;
        }
        else {
            this._containerDiv.style.zIndex = 0;
        }
        var scrollBarPolicy = this.component.render(echopoint.TooltipContainerSync.SCROLLBARPOLICY);
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

        this.layoutStyle = this.component.render(echopoint.TooltipContainerSync.LAYOUTSTYLE);
        var background = this.component.render(echopoint.TooltipContainerSync.BACKGROUND);
        var foreground = this.component.render(echopoint.TooltipContainerSync.FOREGROUND);
        var backgroundImage = this.component.render(echopoint.TooltipContainerSync.BACKGROUNDIMAGE);
        if (!backgroundImage && background) {
            Echo.Sync.Color.renderClear(background, this._containerDiv, "backgroundColor");
        }
        Echo.Sync.FillImage.render(backgroundImage, this._containerDiv, flags);

        Echo.Sync.Insets.render(this.component.render(echopoint.TooltipContainerSync.INSETS), this._containerDiv, "padding");
        Echo.Sync.Insets.render(this.component.render(echopoint.TooltipContainerSync.OUTSETS), this._containerDiv, "margin");
        Echo.Sync.Border.render(this.component.render(echopoint.TooltipContainerSync.BORDER), this._containerDiv);
        Echo.Sync.Color.renderClear(foreground, this._containerDiv, "color");

        Echo.Sync.Font.renderClear(this.component.render(echopoint.TooltipContainerSync.FONT), this._containerDiv);

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
            Echo.Sync.Alignment.render(this.component.render(echopoint.TooltipContainerSync.ALIGNMENT), table, true, this.component);
        }
        else {
            Echo.Sync.Alignment.render(this.component.render(echopoint.TooltipContainerSync.ALIGNMENT), this._containerDiv, true, this.component);
            this._containerDiv.style.verticalAlign = Echo.Sync.Alignment.getVertical(this.component.render(echopoint.TooltipContainerSync.ALIGNMENT));
        }

        var componentCount = this.component.getComponentCount();
        for (i = 0; i < componentCount; ++i) {
            var child = this.component.getComponent(i);
            this._renderAddChild(update, child);
        }
        this._renderRequired = true;
    },

    _processMouseDown: function(e) {
        jQuery("#"+this._containerDiv.id.replace('.', '\\.')).qtip("hide");
    },

    renderDisplay: function() {
        if (this._renderRequired) {
            this._renderRequired = false;

            var content = this.component.render(echopoint.TooltipContainerSync.TOOLTIP);
            var thumbnail = this.component.render(echopoint.TooltipContainerSync.THUMBNAIL);
            var video = this.component.render(echopoint.TooltipContainerSync.VIDEO);
            var show = "";
            if (thumbnail) {
                content = '<img src="http://images.websnapr.com/?url=';
                content += thumbnail;
                content += '" alt="Loading thumbnail..." height="152" width="202" />';
            }
            else if (video) {
                var title = content;
                if (!content) title = " ";
                content = '<object width="425" height="264">';
                content += '<param name="movie" value="';
                content += video;
                content += '&fs=0&rel=0"></param>';
                content += '<param name="allowFullScreen" value="false"></param>';
                content += '<param name="allowscriptaccess" value="always"></param>';
                content += '<embed src="';
                content += video;
                content += '&fs=0&rel=0"';
                content += 'type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true"';
                content += 'width="425" height="264"></embed>';
                content += '</object>';
            }
            var style = this.component.render(echopoint.TooltipContainerSync.TOOLTIPSTYLENAME);
            var tooltipPos = this.component.render(echopoint.TooltipContainerSync.POSITIONTOOLTIP);
            if (!tooltipPos && !style) {
                tooltipPos = "topMiddle";
            }
            var target = this.component.render(echopoint.TooltipContainerSync.POSITIONTARGET);
            if (!target && !style) {
                target = "bottomMiddle";
            }
            var borderCol = this.component.render(echopoint.TooltipContainerSync.TOOLTIPBORDERCOLOR);
            if (!borderCol && !style) {
                borderCol = "#e6eaf0";
            }
            var borderWidth = this.component.render(echopoint.TooltipContainerSync.TOOLTIPBORDERWIDTH);
            if (!borderWidth && !style) {
                borderWidth = 1;
            }
            var hideDelay = this.component.render(echopoint.TooltipContainerSync.HIDEDELAY);
            if (!hideDelay) {
                hideDelay = 1000;
            }
            var soloTip = this.component.render(echopoint.TooltipContainerSync.SOLO);
            if (!soloTip) {
                solo = false;
            }
            var borderRadius = this.component.render(echopoint.TooltipContainerSync.TOOLTIPBORDERRADIUS);
            if (!borderRadius && !style) {
                borderRadius = 4;
            }
            var backgr = this.component.render(echopoint.TooltipContainerSync.TOOLTIPBACKGROUNDCOLOR);
            if (!backgr && !style) {
                backgr = "#140000";
            }
            var foregr = this.component.render(echopoint.TooltipContainerSync.TOOLTIPFOREGROUNDCOLOR);
            if (!foregr && !style) {
                foregr = "#d4f6a6";
            }
            var insets = this.component.render(echopoint.TooltipContainerSync.TOOLTIPINSETS);
            if (!insets && !style) {
                insets = 15;
            }
            var align = this.component.render(echopoint.TooltipContainerSync.TOOLTIPALIGNMENT);
            if (!align && !style) {
                align = "center";
            }
            if (video) {
                var videooptions = {
                    position: {
                        corner: {
                            tooltip: tooltipPos,
                            target: target
                        }
                    },
                    show: {
                        when: 'click', // Show it on click...
                        solo: true // ...and hide all others when its shown
                    },
                    hide: {
                        when: 'click' // 'inactive'
                    },
                    style: {
                        width: {
                            max: 500,
                            min: 400
                        },
                        border: {
                            color: borderCol,
                            width: borderWidth,
                            radius: borderRadius
                        },
                        title: {
                            background: backgr,
                            color: foregr
                        },
                        background: backgr,
                        color: foregr,
                        padding: insets,
                        textAlign: align,
                        tip: true, // Give it a speech bubble tip with automatic corner detection
                        name: style // Style it according to the preset 'cream' style
                    },
                    content: {
                        text: content,
                        title: {
                            text: title,
                            button: 'close'
                        }
                    }
                };
                jQuery("#"+this._containerDiv.id.replace('.', '\\.')).qtip(videooptions);                
            }
            else if (content) {
                var options = {
                    position: {
                        corner: {
                            tooltip: tooltipPos,
                            target: target
                        }
                    },
                    style: {
                        border: {
                            color: borderCol,
                            width: borderWidth,
                            radius: borderRadius
                        },
                        title: {
                            background: backgr,
                            color: foregr
                        },
                        background: backgr,
                        color: foregr,
                        padding: insets,
                        textAlign: align,
                        tip: true, // Give it a speech bubble tip with automatic corner detection
                        name: style // Style it according to the preset 'cream' style
                    },
                    content: content,
                    hide: {
                        when: 'inactive',
                        delay: hideDelay 
                    },
                    show: {
                    	solo: soloTip,
                        effect: {
                            type: 'grow',
                            length: 250
                        }
                    }
                };
                Core.Web.Event.add(this._containerDiv, "mousedown", Core.method(this, this._processMouseDown), false);
                jQuery("#"+this._containerDiv.id.replace('.', '\\.')).qtip(options);
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
            containerElement.removeChild(element);
            Echo.Render.renderComponentDispose(update, update.parent);
            this.renderAdd(update, containerElement);
        }

        return fullRender;
    }

});
