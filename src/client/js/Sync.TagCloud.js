/**
 * Synchronization peer for TagCloud component.
 *
 * @version $Id: Sync.TagCloud.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.TagCloudSync = Core.extend( echopoint.internal.AbstractContainerSync,
{
  $load: function()
  {
    Echo.Render.registerPeer(echopoint.constants.TAG_CLOUD, this);
  },

  _element: null,
  _tags: null,

  _getSpecificElement: function( target )
  {
    var element = this._element.firstChild;
    var i = 0;
    while ( element )
    {
      var tags = this._getTags();

      if ( ( element == target ) && tags )
      {
        return tags[i];
      }
      element = element.nextSibling;
      ++i;
    }
    return null;
  },

  _processRolloverEnter: function( e )
  {
    if ( e.target.nodeName.toLowerCase() != "span" )
    {
      return;
    }

    Echo.Sync.Color.renderClear(
        this.component.render(echopoint.TagCloud.ROLLOVER_FOREGROUND), e.target, "color");
    Echo.Sync.Color.renderClear(
        this.component.render(echopoint.TagCloud.ROLLOVER_BACKGROUND), e.target, "backgroundColor");
  },

  _processRolloverExit: function( e )
  {
    if ( e.target.nodeName.toLowerCase() != "span" )
    {
      return;
    }

    e.target.style.color = "";
    e.target.style.backgroundColor = "";
  },

  _processClick: function( e )
  {
    var tag = this._getSpecificElement(e.target);
    if ( tag )
    {
      this.component.doAction( tag );
    }
  },

  _getTags: function()
  {
    if ( this._tags ) return this._tags;
    var value = this.component.get( echopoint.TagCloud.TAGS );

    if ( value instanceof Array )
    {
      this._tags = value;
    }
    else
    {
      this._tags = this._parseJson( value );
    }

    return this._tags;
  },

  _parseJson: function( json )
  {
    var tags = new Array();
    var data = eval( "(" + json + ")" );

    for ( var i = 0; i < data.list.length; ++i )
    {
      tags[i] = new echopoint.model.Tag(
          data.list[i].name, data.list[i].count );
    }

    return tags;
  },

  _renderStyle: function()
  {
    this.renderStyle( this._element );
    
    if ( this.component.render( echopoint.TagCloud.ROLLOVER_ENABLED ) )
    {
      Core.Web.Event.add(this._element, "mouseover",
          Core.method( this, this._processRolloverEnter ), false);
      Core.Web.Event.add( this._element, "mouseout",
          Core.method( this, this._processRolloverExit ), false);
    }
  },

  _renderTags: function()
  {
    var tags = this._getTags();

    if ( tags )
    {
      var maxCount = 1; // Default to 1 to avoid divide by zero.
      var minCount = Number.MAX_VALUE;
      for ( var i = 0; i < tags.length; ++i )
      {
        if ( tags[i].count < minCount )
        {
          minCount = tags[i].count;
        }
        if ( tags[i].count > maxCount )
        {
          maxCount = tags[i].count;
        }
      }

      for ( i = 0; i < tags.length; ++i )
      {
        var countValue = Math.floor(
            ( tags[i].count - minCount) / (maxCount - minCount) * 100 ) + 100;
        var span = document.createElement("span");
        span.style.cssText = "cursor:pointer;font-size: " + countValue + "%";
        span.appendChild( document.createTextNode( tags[i].name ) );
        span.appendChild(document.createTextNode(" "));
        this._element.appendChild(span);
      }
    }
  },

  renderAdd: function( update, parentElement )
  {
    this._element = document.createElement("div");
    this._element.id = this.component.renderId;
    Core.Web.Event.add(this._element, "click",
        Core.method(this, this._processClick), false);

    this._renderTags();
    this._renderStyle();
    parentElement.appendChild(this._element);
  },

  renderDispose: function( update )
  {
    Core.Web.Event.removeAll( this._element );
    this._element = null;
    this._data = null;
  },

  renderUpdate: function( update )
  {
    var property = update.getUpdatedProperty( echopoint.TagCloud.TAGS );

    if ( property )
    {
      this._tags = null;

      if ( this._element.hasChildNodes() )
      {
        while( this._element.childNodes.length >= 1 )
        {
          this._element.removeChild( this._element.firstChild );
        }
      }

      this._renderTags();
    }

    this._renderStyle();
  }
});