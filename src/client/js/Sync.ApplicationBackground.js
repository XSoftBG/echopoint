echopoint.constants.APPLICATIONBACKGROUND = "echopoint.ApplicationBackground";

/**
 * Component rendering peer: echopoint.ApplicationBackground
 * @author MikaelS 2009-04-28
 * @version $Id: Sync.ApplicationBackground.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */

echopoint.ApplicationBackgroundSync = Core.extend(Echo.Render.ComponentSync, {
    $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.APPLICATIONBACKGROUND, this );
  },

    /** @see Echo.Render.ComponentSync#renderAdd */
    renderAdd: function(update, parentElement) {

        this._body = document.getElementsByTagName('body')[0];

        this._body.background = this.component.render( echopoint.ApplicationBackground.URL );

    },

     /** @see Echo.Render.ComponentSync#renderDispose */
    renderDispose: function(update) {
        this._body.background = null;
    },

    /** @see Echo.Render.ComponentSync#renderUpdate */
    renderUpdate: function(update) {

        this._body = document.getElementsByTagName("body");

        this._body.background = this.component.render( echopoint.ApplicationBackground.URL );

        return true;
    }
});