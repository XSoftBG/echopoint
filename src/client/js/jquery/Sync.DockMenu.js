echopoint.constants.DOCKMENU = "echopoint.jquery.DockMenu";

/**
 * Component rendering peer: echopoint.DockMenu
 * @version $Id: Sync.DockMenu.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */

echopoint.DockMenuSync = Core.extend(Echo.Render.ComponentSync,
{
    $load: function()
    {
        Echo.Render.registerPeer(echopoint.constants.DOCKMENU, this);
    },

    $static:
    {
    },


    menuModel: null,

    dumpProps : function(obj, parent) {
        // Go through all the properties of the passed-in object
        for (var i in obj) {
            // if a parent (2nd parameter) was passed in, then use that to
            // build the message. Message includes i (the object's property name)
            // then the object's property value on a new line
            var msg;
            if (parent) {
                msg = parent + "." + i + "\n" + obj[i];
            } else {
                msg = i + "\n" + obj[i];
            }
            // Display the message. If the user clicks "OK", then continue. If they
            // click "CANCEL" then quit this level of recursion
            if (!confirm(msg)) {
                return;
            }
            // If this property (i) is an object, then recursively process the object
            if (typeof obj[i] == "object") {
                if (parent) {
                    this.dumpProps(obj[i], parent + "." + i);
                } else {
                    this.dumpProps(obj[i], i);
                }
            }
        }
    },

    isEmpty: function(obj) {
        for (var prop in obj) {
            if (obj.hasOwnProperty(prop))
                return false;
        }

        return true;
    },



    renderAdd: function(update, parentElement)
    {
        this.menuModel = this.component.get("model");
        this._renderRequired = true;

        this._maindiv = document.createElement("div");
        this._maindiv.id = this.component.renderId;

        parentElement.appendChild(this._maindiv);

        if (this.menuModel)
        {
            var json = eval("(" + this.menuModel + ")");

            var textList = json.list[0];
            var commandList = json.list[1];
            var inactiveList = json.list[2];
            var rolloverList = json.list[3];
            //var activeList = json.list[4];

            for (var i = 0; i < textList.length; i++)
            {
                var text = textList[i];

                var imagetag = document.createElement("img");
                imagetag.id = commandList[i];

                if (text.length > 0)
                {
                    imagetag.title = text;
                }

                if (rolloverList[i] != null && rolloverList[i].uri != null)
                {
                    imagetag.alt = Echo.Sync.ImageReference.getUrl(rolloverList[i].uri);
                }

                Echo.Sync.ImageReference.renderImg(inactiveList[i].uri, imagetag);
                this._maindiv.appendChild(imagetag);
            }
        }


        var css = "div.jqDockLabel {";

        var foreground = this.component.render(echopoint.DockMenu.FOREGROUND);
        if (foreground)
        {
            css += " color: " + foreground + ";";
        }

        var font = this.component.render(echopoint.DockMenu.FONT);
        if (font)
        {

            if (font.typeface)
            {
                css += " font-family: " + ( font.typeface instanceof Array ) ?
                       font.typeface.join(",") : font.typeface;
                css += ";";
            }

            if (font.size)
            {
                css += " font-size: " + Echo.Sync.Extent.toCssValue(font.size) + ";";
            }


            if (font.bold)
            {
                css += " font-weight: bold;";
            }
            if (font.italic)
            {
                css += " font-style: italic;";
            }

            if (font.underline)
            {
                css += " text-decoration: underline;";
            }
            else if (font.overline)
            {
                css += " text-decoration: overline;";
            }
            else if (font.lineThrough)
                {
                    css += " text-decoration: line-through;";
                }
                else
                {
                    css += " text-decoration: none;";
                }


        }
        css += "}";

        var pa = document.getElementsByTagName('head')[0] ;
        var el = document.createElement('style');
        el.type = 'text/css';
        el.media = 'screen';
        if (el.styleSheet) el.styleSheet.cssText = css;// IE method
        else el.appendChild(document.createTextNode(css));// others
        pa.appendChild(el);

        this._redrawScreen();

    },

    renderDispose: function(update)
    {

    },

    clickHandler: function(e)
    {
        e.data.echoThis.component.doAction(jQuery(this).attr("id"));
        return false;
    },

    renderDisplay: function() {
        if (this._renderRequired) {

            this._renderRequired = false;

            var opts = {
                labels: this.component.render(echopoint.DockMenu.LABELS),
                align: this.component.render(echopoint.DockMenu.ALIGN),
                distance: this.component.render(echopoint.DockMenu.DISTANCE),
                coefficient: this.component.render(echopoint.DockMenu.COEFFICIENT),
                size: this.component.render(echopoint.DockMenu.MINORSIZE)  ,
                duration: this.component.render(echopoint.DockMenu.DURATION)
            };

            jQuery("#" + this._maindiv.id.replace('.', '\\.')).jqDock(opts);

            var echoThis = this;
            jQuery('#' + this._maindiv.id.replace('.', '\\.') + '>img').each(function(i) {
                jQuery(this).bind("click", {echoThis: echoThis}, echoThis.clickHandler);
            });


            var dockWidth = this.component.render(echopoint.DockMenu.DOCKWIDTH);

            jQuery("#" + this._maindiv.id.replace('.', '\\.')).css({
                "position" : "absolute",
                "left" : "50%",
                "marginLeft" : "-" + dockWidth / 2 + "px"
            });

        }

    },

    renderUpdate: function(update)
    {
        if (!update.isUpdatedPropertySetIn({itemid: true}))
        {
            var modelUpdate = update.getUpdatedProperty("model");
            if (modelUpdate) {
                this.menuModel = modelUpdate.newValue;
            }

            var element = this._maindiv;
            var containerElement = element.parentNode;
            Echo.Render.renderComponentDispose(update, update.parent);
            containerElement.removeChild(element);
            this.renderAdd(update, containerElement);

        }
        
        return true;
    },

    /** Used to force a redraw */
    _redrawScreen: function() {
        this.client.forceRedraw();
    }
});