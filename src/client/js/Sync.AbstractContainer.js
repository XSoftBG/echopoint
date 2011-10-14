/**
 * Component rendering peer: AbstractContainer
 *
 * @author Rakesh 2008-07-20
 * @version: $Id: Sync.AbstractContainer.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.internal.AbstractContainerSync = Core.extend( Echo.Render.ComponentSync,
{
  $abstract: true,

  $static:
  {
    DEFAULT_WIDTH: "100%",
    DEFAULT_HEIGHT: "100%"
  },

  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.ABSTRACT_CONTAINER, this );
  },

  $virtual:
  {
    /**
     * The default width property to use.  Sub-classes should over-ride as
     * necessary.
     */
    getDefaultWidth: function()
    {
      return echopoint.internal.AbstractContainerSync.DEFAULT_WIDTH;
    },

    /**
     * Return the width to use for the component.  If no width property has
     * been specified, returns {@link #getDefaultWidth}.
     */
    getWidth: function()
    {
      var width = this.component.render( echopoint.internal.AbstractContainer.WIDTH );
      return  ( ( width ) ? width : this.getDefaultWidth() );
    },

    /**
     * The default height property to use.  Sub-classes should over-ride as
     * necessary.
     */
    getDefaultHeight: function()
    {
      return echopoint.internal.AbstractContainerSync.DEFAULT_HEIGHT;
    },

    /**
     * Return the height to use for the component.  If no height property
     * has been specified, returns {@link #getDefaultHeight}.
     */
    getHeight: function()
    {
      var height = this.component.render( echopoint.internal.AbstractContainer.HEIGHT );
      return  ( ( height ) ? height : this.getDefaultHeight() );
    },

    /**
     * The method used to render the full style properties for the container.
     * This may also be invoked from {@link #renderUpdate} to selectively
     * re-apply the styles that have changed.
     *
     * @param container The element to which the styles are to be applied.
     * @param update The update object that will be queried for style updates.
     *   If this is not specified or is <code>null</code>, then the styles
     *   will be applied unconditionally.  This helps to use this optional
     *   parameter to drive use from {@link #renderAdd} or {@link #renderUpdate}.
     */
    renderStyle: function( container, update )
    {
      this.renderAlignment( container, update );
      this.renderBorder( container, update );
      this.renderFB( container, update );
      this.renderFont( container, update );
      this.renderBackgroundImage( container, update );
      this.renderInsets( container, update );
      this.renderWidth( container, update );
      this.renderHeight( container, update );
    },

    /**
     * Render the <code>alignment</code> style property for the component.
     * This may be used to render update of the alignment property alone from
     * {@link #renderUpdate}.
     *
     * @param container The element to which the style will be applied.
     * @param update The update object that will be queried for style change.
     */
    renderAlignment: function( container, update )
    {
      if ( update )
      {
        var property = update.getUpdatedProperty(
            echopoint.internal.AbstractContainer.ALIGNMENT );
        if ( property )
        {
          Echo.Sync.Alignment.render(
              property.newValue, container, false, null );
        }
      }
      else
      {
        Echo.Sync.Alignment.render( this.component.render(
            echopoint.internal.AbstractContainer.ALIGNMENT ),
            container, false, null );
      }
    },

    /**
     * Render the <code>backgroundImage</code> style property for the component.
     * This may be used to render update of the background image property alone from
     * {@link #renderUpdate}.
     *
     * @param container The element to which the style will be applied.
     * @param update The update object that will be queried for style change.
     */
    renderBackgroundImage: function( container, update )
    {
      if ( update )
      {
        var property = update.getUpdatedProperty(
            echopoint.internal.AbstractContainer.BACKGROUND_IMAGE );
        if ( property )
        {
          Echo.Sync.FillImage.render( property.newValue, container );
        }
      }
      else
      {
        Echo.Sync.FillImage.render( this.component.render(
            echopoint.internal.AbstractContainer.BACKGROUND_IMAGE ), container );
      }
    },

    /**
     * Render the <code>border</code> style property for the component.
     * This may be used to render update of the border property alone from
     * {@link #renderUpdate}.
     *
     * @param container The element to which the style will be applied.
     * @param update The update object that will be queried for style change.
     */
    renderBorder: function( container, update )
    {
      if ( update )
      {
        var property = update.getUpdatedProperty(
            echopoint.internal.AbstractContainer.BORDER );
        if ( property )
        {
          Echo.Sync.Border.render( property.newValue, container );
        }
      }
      else
      {
        Echo.Sync.Border.render( this.component.render(
            echopoint.internal.AbstractContainer.BORDER ), container );
      }
    },

    /**
     * Render the <code>foreground</code> and <code>background</code> style
     * properties the component.
     * This may be used to render update of the fore/background properties
     * alone from {@link #renderUpdate}.
     *
     * @param container The element to which the style will be applied.
     * @param update The update object that will be queried for style change.
     */
    renderFB: function( container, update )
    {
      if ( update )
      {
        var foreground = update.getUpdatedProperty(
            echopoint.internal.AbstractContainer.FOREGROUND );
        var background = update.getUpdatedProperty(
            echopoint.internal.AbstractContainer.BACKGROUND );
        if ( foreground || background )
        {
          Echo.Sync.Color.renderFB( this.component, container );
        }
      }
      else
      {
        Echo.Sync.Color.renderFB( this.component, container );
      }
    },

    /**
     * Render the <code>font</code> style property for the component.
     * This may be used to render update of the font property alone from
     * {@link #renderUpdate}.
     *
     * @param container The element to which the style will be applied.
     * @param update The update object that will be queried for style change.
     */
    renderFont: function( container, update )
    {
      if ( update )
      {
        var property = update.getUpdatedProperty(
            echopoint.internal.AbstractContainer.FONT );
        if ( property )
        {
          Echo.Sync.Font.render( property.newValue, container );
        }
      }
      else
      {
        Echo.Sync.Font.render( this.component.render(
            echopoint.internal.AbstractContainer.FONT ), container );
      }
    },

    /**
     * Render the <code>insets</code> style property for the component.
     * This may be used to render update of the insets property alone from
     * {@link #renderUpdate}.
     *
     * @param container The element to which the style will be applied.
     * @param update The update object that will be queried for style change.
     */
    renderInsets: function( container, update )
    {
      if ( update )
      {
        var property = update.getUpdatedProperty(
            echopoint.internal.AbstractContainer.INSETS );
        if ( property )
        {
          Echo.Sync.Insets.render( property.newValue, container, "padding" );
        }
      }
      else
      {
        Echo.Sync.Insets.render( this.component.render(
            echopoint.internal.AbstractContainer.INSETS ), container, "padding" );
      }
    },

    /**
     * Render the <code>width</code> style property for the component.
     * This may be used to render update of the width property alone from
     * {@link #renderUpdate}.
     *
     * @param container The element to which the style will be applied.
     * @param update The update object that will be queried for style change.
     */
    renderWidth: function( container, update )
    {
      if ( update )
      {
        var property = update.getUpdatedProperty(
            echopoint.internal.AbstractContainer.WIDTH );
        if ( property )
        {
          container.style.width = Echo.Sync.Extent.toCssValue(
              property.newValue, true, true );
        }
      }
      else
      {
        container.style.width = Echo.Sync.Extent.toCssValue(
            this.getWidth(), true, true );
      }
    },

    /**
     * Render the <code>height</code> style property for the component.
     * This may be used to render update of the height property alone from
     * {@link #renderUpdate}.
     *
     * @param container The element to which the style will be applied.
     * @param update The update object that will be queried for style change.
     */
    renderHeight: function( container, update )
    {
      if ( update )
      {
        var property = update.getUpdatedProperty(
            echopoint.internal.AbstractContainer.HEIGHT );
        if ( property )
        {
          container.style.height = Echo.Sync.Extent.toCssValue(
              property.newValue, false, true );
        }
      }
      else
      {
        container.style.height = Echo.Sync.Extent.toCssValue(
            this.getHeight(), false, true );
      }
    },

    /**
     * Render the basic cell layout data for the specified component.
     * Sub-classes that implement their own layout data objects must over-ride
     * this method.
     *
     * @param container The element to which the layout data is to be applied.
     * @param layoutData The layout data to apply.
     */
    renderLayoutData: function( container, layoutData )
    {
      if ( layoutData )
      {
        Echo.Sync.Alignment.render(
            layoutData.alignment, container, true, this.component );
        Echo.Sync.FillImage.render( layoutData.backgroundImage, container );
        Echo.Sync.Insets.render( layoutData.insets, container, "padding" );
        Echo.Sync.Color.render( layoutData.background, container, "backgroundColor" );
      }
    }
  }
} );