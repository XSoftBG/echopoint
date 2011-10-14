/**
 * Component rendering peer: echopoint.jquery.EditableLabel
 *
 * @author Rakesh 2009-09-30
 * @version $Id: Sync.EditableLabel.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.jquery.EditableLabelSync = Core.extend( echopoint.internal.AbstractContainerSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.EDITABLE_LABEL, this );
  },

  /** The parent div that represents the editable label. */
  _label: null,

  /** A flag used to indicate whether full render is required. */
  _renderRequired: true,

  renderAdd: function( update, parentElement )
  {
    this._createLabel( update );
    parentElement.appendChild( this._label );
    this._renderRequired = true;
  },

  renderUpdate: function( update )
  {
    this.renderStyle( this._label, update );
    this._label.innerHTML = this.component.render(
        echopoint.jquery.EditableLabel.TEXT, "" );
  },

  renderDispose: function()
  {
    Core.Web.Event.removeAll( this._label );
    this._label = null;
  },

  _createLabel: function( update )
  {
    this._label = document.createElement( "div" );
    this._label.id = this.component.renderId;
    this.renderUpdate( update );
  }
});
