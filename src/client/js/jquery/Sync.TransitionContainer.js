echopoint.constants.TRANSITIONCONTAINER = "echopoint.TransitionContainer";

/**
 * Component rendering peer: echopoint.TransitionContainer
 * @version $Id: Sync.TransitionContainer.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */

echopoint.TransitionContainerSync = Core.extend(Echo.Render.ComponentSync, {


    $load: function()
    {

        Echo.Render.registerPeer(echopoint.constants.TRANSITIONCONTAINER, this);
    },




    /**
     * Outermost/top-level container element.
     * @type Element
     */
    _containerDiv: null,

    /**
     * Content element, contains oldChildDiv/childDiv elements.
     * @type Element
     */
    contentDiv: null,

    /**
     * The transition type value (retrieved from the component).
     * @type Number
     */
    type: null,

    transitionname: null,

    duration: echopoint.TransitionContainer.DEFAULT_DURATION,


    /**
     * The element containing the old child element, which is being transitioned FROM.
     * @type Element
     */
    oldChildDiv: null,

    /**
     * The element containing the current/new child element, which is being transitioned TO.
     * @type Element
     */
    childDiv: null,

    /**
     * Flag indicating whether initial content has been loaded (no transition effect is used on the first load).
     * @type Boolean
     */
    _initialContentLoaded: false,

    startTransition: false,

    indexCounter: 0,

    oldChildDivIndex: 0,

    childDivIndex: 0,

    /**
     * Performs an immediate transition between old content and new content with no animated effect.
     */
    doImmediateTransition: function() {
        this.removeOldContent();
        if (this.childDiv) {
            this.showContent();
        }
    },

    _loadTransition: function() {
        this.type = this.component.render("type");
        this.duration = this.component.render("duration");

        switch (this.type) {

            case echopoint.TransitionContainer.TYPE_BLIND:
                this.transitionname = "blind";
                break;
            case echopoint.TransitionContainer.TYPE_BOUNCE:
                this.transitionname = "bounce";
                break;
            case echopoint.TransitionContainer.TYPE_CLIP:
                this.transitionname = "clip";
                break;
            case echopoint.TransitionContainer.TYPE_DROP:
                this.transitionname = "drop";
                break;
            case echopoint.TransitionContainer.TYPE_EXPLODE:
                this.transitionname = "explode";
                break;
            case echopoint.TransitionContainer.TYPE_FOLD:
                this.transitionname = "fold";
                break;
            case echopoint.TransitionContainer.TYPE_HIGHLIGHT:
                this.transitionname = "highlight";
                break;
            case echopoint.TransitionContainer.TYPE_PUFF:
                this.transitionname = "puff";
                break;
            case echopoint.TransitionContainer.TYPE_PULSATE:
                this.transitionname = "pulsate";
                break;
            case echopoint.TransitionContainer.TYPE_SCALE:
                this.transitionname = "scale";
                break;
            case echopoint.TransitionContainer.TYPE_SHAKE:
                this.transitionname = "shake";
                break;
            case echopoint.TransitionContainer.TYPE_SIZE:
                this.transitionname = "size";
                break;
            case echopoint.TransitionContainer.TYPE_SLIDE:
                this.transitionname = "slide";
                break;
            default:
                this._transitionClass = null;
        }
    },

    /**
     * Removes old content: remove oldChildDiv from parent, set oldChildDiv to null.
     */
    removeOldContent: function() {
        if (this.oldChildDiv) {
            this.contentDiv.removeChild(this.oldChildDiv);
            this.oldChildDiv = null;
        }
    },

    /**
     * Shows new content.
     */
    showContent: function() {
        if (this.childDiv) {
            this.childDiv.style.display = "inline";
        }
    },

    /** @see Echo.Render.ComponentSync#renderAdd */
    renderAdd: function(update, parentElement) {

        this._containerDiv = document.createElement("div");
        this._containerDiv.id = this.component.renderId;
        this._containerDiv.style.cssText = "top:0;left:0;width:100%;height:100%;";
        this._containerDiv.style.overflow = "hidden";

        var width = this.component.render(echopoint.TransitionContainer.WIDTH);
        var height = this.component.render(echopoint.TransitionContainer.HEIGHT);
        if (width) {
            this._containerDiv.style.width = width;
        }
        if (height) {
            this._containerDiv.style.height = height;
        }

        this.contentDiv = document.createElement("div");
        this.contentDiv.id = "transdiv-" + this._containerDiv.id;
        this.contentDiv.style.cssText = "top:0;left:0;width:100%;height:100%;";
        this._containerDiv.appendChild(this.contentDiv);

        parentElement.appendChild(this._containerDiv);
        if (this.component.children.length > 0) {
            this._renderAddChild(update);
        }
    },

    /**
     * Renders new content (a new child) added in an update.  Starts the transition.
     *
     * @param {Echo.Update.ComponentUpdate} the update
     */
    _renderAddChild: function(update) {
        this._loadTransition();
        this.childDiv = document.createElement("div");
        this.childDiv.style.cssText = "top:0;left:0;width:100%;height:100%;";
        this.childDiv.id = this._containerDiv.id + "-" + this.indexCounter;
        this.indexCounter++;

        var componentCount = this.component.getComponentCount();
        for (i = 0; i < componentCount; ++i) {
            var child = this.component.getComponent(i);
            Echo.Render.renderComponentAdd(update, child, this.childDiv);
        }


        if (this._initialContentLoaded) {
            this.childDiv.style.display = "none";
            this.startTransition = true;
        } else {
            this._initialContentLoaded = true;
        }

        this.contentDiv.appendChild(this.childDiv);
    },


    renderDisplay: function() {

        if (this.startTransition)
        {
            this._transitionStart();
            this.startTransition = false;
        }
    },

    /** @see Echo.Render.ComponentSync#renderDispose */
    renderDispose: function(update) {
        this._initialContentLoaded = false;
        this._childDiv = null;
        this.contentDiv = null;
        this._containerDiv = null;
    },

    /** @see Echo.Render.ComponentSync#renderUpdate */
    renderUpdate: function(update) {
        var fullRender = false;
        if (update.hasUpdatedLayoutDataChildren()) {
            fullRender = true;
        } else if (update.hasUpdatedProperties()) {
            // Property updates
            var propertyNames = update.getUpdatedPropertyNames();
            if (!(propertyNames.length == 1 && propertyNames[0] == "type")) {
                // Properties other than 'type' have changed.
                fullRender = true;
            }
        }

        if (fullRender) {
            var contentDiv = this._containerDiv;
            var containerElement = contentDiv.parentNode;
            Echo.Render.renderComponentDispose(update, update.parent);
            containerElement.removeChild(contentDiv);
            this.renderAdd(update, containerElement);
        } else {

            var removedChildren = update.getRemovedChildren();
            if (removedChildren) {
                // Remove children.
                this.oldChildDiv = this.childDiv;
                this.childDiv = null;
            }
            var addedChildren = update.getAddedChildren();
            if (update.parent.children > 1) {
                throw new Error("Cannot render more than one child in a TransitionPane.");
            }

            if (addedChildren) {
                // Add children.
                this._renderAddChild(update);
            }
        }

        return fullRender;
    },

    /**
     * Initiates the animated transition effect.
     */
    _transitionStart: function() {

        if (this.type == echopoint.TransitionContainer.TYPE_IMMEDIATE_REPLACE)
            this.doImmediateTransition();


        var options = {};

        jQuery("#" + this.oldChildDiv.id.replace('.', '\\.')).hide(this.transitionname, options, this.duration, jQuery.context(this).callback(this._transitionFinish));
    },
    _test: function() {
        alert("test");
    },

    /**
     * Completes the animated transition effect.
     */
    _transitionFinish: function(abort) {

        // Remove content which was transitioned from.
        this.removeOldContent();

        jQuery("#" + this.childDiv.id.replace('.', '\\.')).show(this.transitionname, {}, this.duration);

        // Refocus current focused component if it is within TransitionPane.
        if (this.component && this.component.application) {
            var focusedComponent = this.component.application.getFocusedComponent();
            if (focusedComponent != null && this.component.isAncestorOf(focusedComponent)) {
                Echo.Render.updateFocus(this.client);
            }
        }
    }
});