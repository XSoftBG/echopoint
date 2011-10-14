/**
 * Component rendering peer: AbstractHtmlComponent
 *
 * @author Rakesh 2008-06-20
 * @version: $Id: Sync.AbstractHtmlComponent.js,v 1.5 2010-12-10 08:37:17 perxi Exp $
 */
echopoint.internal.AbstractHtmlComponentSync = Core.extend( echopoint.internal.AbstractContainerSync,
{
  $abstract: true,

  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.ABSTRACT_HTML_COMPONENT, this );
  },

  $virtual:
  {
    /** The type of container to use.  Sub-classes must set this. */
    containerType: null
  },

  /** The container element for this component */
  _container: null,
  _new_html: null,

  renderAdd: function( update, parentElement )
  {
    this._container = document.createElement( this.containerType );
    this._container.id = this.component.renderId;
    this.renderStyle( this._container );
    this._renderStyle();
		
    this._container.innerHTML = this.component.render( echopoint.internal.AbstractHtmlComponent.TEXT, "" );
    this._processLinks();

    parentElement.appendChild( this._container );
  },

  renderDispose: function()
  {
    this._container = null;
  },

  renderUpdate: function( update )
  {
    this.renderStyle( this._container, update );

    var process_forms_property = update.getUpdatedProperty("process_forms");
    if(process_forms_property)
      this._processForms();

    var property = update.getUpdatedProperty( echopoint.internal.AbstractHtmlComponent.TEXT );
    if ( property )
    {
      this._container.innerHTML = property.newValue;
      this._processLinks();
    }
  },

  /** Set additional styles for the component. */
  _renderStyle: function()
  {
    this._container.style.overflow = "auto";
  },

  /**
   * Process the <code>target</code> property and if set, set the attribute
   * for all anchor tags in the <code>text</code> content.  If a target
   * attribute already exists, then no action is taken.
   */
  _processLinks: function()
  {
    var target = this.component.get( "target" );

    if ( target )
    {
      try
      {
        var anchors = this._container.getElementsByTagName( "a" );
        for ( var i = 0; i < anchors.length; ++i )
        {
          var attrib = anchors[i].attributes.getNamedItem( "target" );
          if ( ! attrib )
          {
            anchors[i].setAttribute( "target", target );
          }
        }
      }
      catch ( exception )
      {
        this._container.innerHTML = this.component.render( echopoint.internal.AbstractHtmlComponent.TEXT, "" );
        var message = "XML parsing error for html content: " +
            this.component.get( echopoint.internal.AbstractHtmlComponent.TEXT ) +
                      Core.Debug.toString( exception );
        Core.Debug.consoleWrite( message );
      }
    }
  },

  _processForms: function()
  {
    var open_tag = '<p>';
    var close_tag = '</p>';
    var scope = document.getElementById(this.component.renderId);
    scope = jQuery(scope);
    jQuery(':input', scope).each(
      function()
      {
        var elem = jQuery(this);
        var value = elem.val();
        elem.replaceWith(open_tag + value + close_tag);
      }
    );

    this._new_html = scope.html();

    if (!this.client.verifyInput(this.component))
      this.client.registerRestrictionListener(this.component, Core.method(this, this._processRestrictionsClear));
    else
    {
      this.component.set(echopoint.internal.AbstractHtmlComponent.TEXT, this._new_html, true);
      this.component.fireEvent({type: "content_changed", source: this.component});
    }
  },

  _processRestrictionsClear: function()
  {
    if (!this.client)
      return; // Component has been disposed, do nothing.
    this.component.set(echopoint.internal.AbstractHtmlComponent.TEXT, this._new_html, true);
    this.component.fireEvent({type: "content_changed", source: this.component});
  }
});
