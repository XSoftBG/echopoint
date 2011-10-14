echopoint.constants.DATEFIELD = "echopoint.jquery.DateField";

/**
 * Component rendering peer: DateField.
 * @author HansH 2009-04-28
 * @version $Id: Sync.DateField.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.DateField = Core.extend(Echo.Render.ComponentSync, {

    /** Properties defined for this component. */
    $static:
    {
        // Style attributes
        WIDTH: "width",
        HEIGHT: "height",
        INSETS: "insets",
        BORDER: "border",
        FONT: "font",
        BACKGROUND: "background",
        FOREGROUND: "foreground",
        BUTTONICON: "icon",
        ALIGNMENT: "alignment",
        DATEFORMAT: "dateFormat",
        USETIME: "useTime",
        CSS: "css",
        LANGUAGE: "language",
        INPUTWIDTH: "inputWidth",
        INPUTHEIGHT: "inputHeight",
        SHOWWEEKS: "showWeeks",
        FIRSTDAYOFWEEK: "firstDayOfWeek"
    },

    $load: function()
    {

        Echo.Render.registerPeer(echopoint.constants.DATEFIELD, this);
    },

    /**
     * Outermost/top-level container element.
     * @type Element
     */
    _dateTimediv: null,
    _dateFormatPattern: null,
    _calObject: null, // Holds the calendar object, needed for destroying it

    /** @see Echo.Render.ComponentSync#renderAdd */
    renderAdd: function(update, parentElement) {
        this._dateTimediv = document.createElement("div");
        this._dateTimediv.id = this.component.renderId;

        Echo.Sync.Insets.render(this.component.render(echopoint.DateField.INSETS), this._dateTimediv, "padding");
        Echo.Sync.Border.render(this.component.render(echopoint.DateField.BORDER), this._dateTimediv);
        Echo.Sync.Alignment.render(this.component.render(echopoint.DateField.ALIGNMENT), this._dateTimediv, true, this.component);
        //this._dateTimediv.style.overflow = "visible";
        //this._dateTimediv.style.position = "relative";

        var width = this.component.render(echopoint.DateField.WIDTH);
        var height = this.component.render(echopoint.DateField.HEIGHT);
        if (width) {
            this._dateTimediv.style.width = width;
        }
        if (height) {
            this._dateTimediv.style.height = height;
        }

        this._inputElem = document.createElement("input");
        //this._inputElem.style.overflow = "visible";
        this._inputElem.type= "text";
        if (!this.component.isRenderEnabled()) {
            this._inputElem.disabled = "disabled";
        }
        var inputWidth = this.component.render(echopoint.DateField.INPUTWIDTH);
        var inputHeight = this.component.render(echopoint.DateField.INPUTHEIGHT);
        if (inputWidth) {
            this._inputElem.style.width = inputWidth;
        }
        if (inputHeight) {
            this._inputElem.style.height = inputHeight;
        }

        var font = this.component.render(echopoint.DateField.FONT);
        if (font) {
            Echo.Sync.Font.renderClear(font, this._inputElem);
        }
        this._dateTimediv.appendChild(this._inputElem);
        // jQuery does not like ID's with a dot in the name
        this._inputElem.id = (this.component.renderId + "dateTime").replace('.', '_');



        var dateStr = this.component.get("date");
        if (dateStr) {
            this._inputElem.value = dateStr;
        }

        // We must also look for changes in the input field itself, if the
        // user modifies the date via the input field
         Core.Web.Event.add(this._inputElem, "change",
                Core.method(this, this._processChange), false);

        if (this.component.isRenderEnabled())
        {   // Only render img when component enabled
            var imgElement = document.createElement("img");
            imgElement.id = this._inputElem.id+"Button";
            imgElement.style["margin"] = "0px 0px 0px 2px";
            // imgElement.style.overflow = "visible";
            Echo.Sync.ImageReference.renderImg(this.component.render(echopoint.DateField.BUTTONICON), imgElement);
            this._dateTimediv.appendChild(imgElement);
        }

        parentElement.appendChild(this._dateTimediv);
        this._renderRequired = true;
    },

    /** @see Echo.Render.ComponentSync#renderDispose */
    renderDispose: function(update) {
        Core.Web.Event.removeAll(this._inputElem);
        this._dateTimediv = null;
        this._dateFormatPattern = null;
        if (this._calObject != undefined)
        {
            // object only exists when calendar has "popped up"
            this._calObject.destroy(); // Also remove me from dom tree
        }
    },

    /**
     * Stores the selected date in the <code>Echo.Component</code> instance.
     */
    _storeValue: function(theCalendar) {
        //Core.Debug.consoleWrite("Calendar store value called");
        this.component.set("date", theCalendar.date.print(this._dateFormatPattern));
        this.component.doAction();
    },

    /**
     * User did modify the input field manually
     */
    _processChange: function(theCalendar) {
        //Core.Debug.consoleWrite("Calendar processChange called: "+this._inputElem.value);
        this.component.set("date", this._inputElem.value);
        this.component.doAction();
    },


    renderDisplay: function() {
        if (this._renderRequired) {
            this._renderRequired = false;

            if (jQuery("#dateFieldCss").length == 0) {
                var stylesheet = this.component.render(echopoint.DateField.CSS);
                jQuery("head").append("<style type=\"text/css\" id=\"dateFieldCss\">"+stylesheet+"</style>");
            }

            var foreground = this.component.render(echopoint.DateField.FOREGROUND);
            var background = this.component.render(echopoint.DateField.BACKGROUND);
            this._dateFormatPattern = this.component.render(echopoint.DateField.DATEFORMAT);

            if (this.component.isRenderEnabled()) {
                var useTime = this.component.render(echopoint.DateField.USETIME, false );
                var showWeeks= this.component.render(echopoint.DateField.SHOWWEEKS, false );
                var firstDayOfWeekValue= this.component.render(echopoint.DateField.FIRSTDAYOFWEEK, 0 );
                var options = {
                    onUpdate: jQuery.context(this).callback(this._storeValue),
                    showsTime: useTime,
                    weekNumbers: showWeeks,
                    firstDay: firstDayOfWeekValue,
                    ifFormat: this._dateFormatPattern,
                    button: ".next()", // Use this as the open/close trigger (img)
                    echoComponent: this // We need to pass ourself to the calendar
                                        // so the forceRedraw has a handle to the client object
                };

                jQuery(this._inputElem).dynDateTime(options); // Bind to image
            }

            Echo.Sync.Color.renderClear(foreground, this._dateTimediv, "color");
            Echo.Sync.Color.renderClear(background, this._dateTimediv, "backgroundColor");
        }
    },

    /** @see Echo.Render.ComponentSync#renderUpdate */
    renderUpdate: function(update) {
        var fullRender = update.hasUpdatedProperties();
        if (fullRender) {
            var element = this._dateTimediv;
            var containerElement = element.parentNode;
            containerElement.removeChild(element);
            Echo.Render.renderComponentDispose(update, update.parent);
            this.renderAdd(update, containerElement);
        }
        return fullRender;
    },

    /** Used to force a redraw */
    _redrawScreen: function(myself) {
        // this is not this object but rather the calendar object.
        // For this reason we had to pass a pointer to ourself for the redraw
        myself.client.forceRedraw();
    }
});
