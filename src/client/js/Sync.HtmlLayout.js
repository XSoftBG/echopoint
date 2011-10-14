/**
 * Component rendering peer: HtmlLayout
 *
 * @author Simon Lei 2009-03-16
 * @version $Id: Sync.HtmlLayout.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.HtmlLayoutSync = Core.extend( echopoint.internal.AbstractHtmlComponentSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.HTML_LAYOUT, this );
  },

  /** The container element for this component */
  containerType: "div",

  /** A map for render id's of children to their DOM elements. */
  _childToComponent: new Array(),

  /** A map for render id's of children to their parent. */
  _childToParent: new Array(),

  renderAdd: function( update, parentElement )
  {
    if ( ! this.component.render( echopoint.internal.AbstractHtmlComponent.TEXT ) )
    {
      throw new Error( "HtmlLayout must have html layout definition!" );
    }

    this.constructor.$super.prototype.renderAdd.call(
        this, update, parentElement );

    var childCount = this.component.getComponentCount();
    for ( var i = 0; i < childCount; ++i )
    {
      this._renderAddChild( update, this.component.getComponent( i ) );
    }
  },

  _renderAddChild : function( update, child )
  {
    var layoutData = child.render(
        echopoint.internal.AbstractContainer.LAYOUT_DATA );

    if ( !layoutData )
    {
      throw new Error( "Children of HtmlLayout must specify HtmlLayoutData" );
    }

    var className = layoutData["containerId"];
    var container = this._findChildById( this._container, className );
    if ( ! container )
    {
      throw new Error( "Cannot find any element named as " + className
          + " under the HTML text definition" );
    }

    Echo.Render.renderComponentAdd( update, child, container );

    this._childToComponent[child.renderId] = container.lastChild;
    this._childToParent[child.renderId] = container;

  },

  _findChildById : function( parentElement, id )
  {
    var element = parentElement.firstChild;

    while ( element )
    {
      if ( ( element.nodeType == 1 ) && ( element.id == id ) )
      {
        return element;
      }

      var elem = this._findChildById( element, id );
      if ( elem ) return elem;

      element = element.nextSibling;
    }

    return null;
  },

  _renderRemoveChild : function( child )
  {
    var paraEle = this._childToParent[child.renderId];
    var chilEle = this._childToComponent[child.renderId];

    if ( paraEle && chilEle )
    {
      paraEle.removeChild(chilEle);
    }

    delete this._childToComponent[child.renderId];
    delete this._childToParent[child.renderId];
  },

  renderUpdate : function( update )
  {
    var fullRender = false;

    if ( update.hasUpdatedProperties()
        || update.hasUpdatedLayoutDataChildren() )
    {
      // Full render
      fullRender = true;
    }
    else
    {
      var removedChildren = update.getRemovedChildren();
      if ( removedChildren )
      {
        // Remove children.
        for ( var i = 0; i < removedChildren.length; ++i )
        {
          var child = removedChildren[ i ];
          this._renderRemoveChild( child );
        }
      }

      var addedChildren = update.getAddedChildren();
      if ( addedChildren )
      {
        // Add children.
        for ( var i = 0; i < addedChildren.length; ++i )
        {
          this._renderAddChild(update, addedChildren[i]);
        }
      }
    }

    if ( fullRender )
    {
      var element = this._container;
      var containerElement = element.parentNode;
      Echo.Render.renderComponentDispose( update, update.parent );
      containerElement.removeChild( element );
      this.renderAdd( update, containerElement );
    }

    return fullRender;
  },

  renderDispose: function( update )
  {
    this.constructor.$super.prototype.renderDispose.call(
        this, update );
    this._childToComponent = null;
    this._childToParent = null;
  }
});
