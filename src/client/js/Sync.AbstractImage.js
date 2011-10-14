/**
 * Component rendering peer: AbstractImage
 *
 * @author Rakesh 2009-12-18
 * @version $Id: Sync.AbstractImage.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.internal.AbstractImageSync = Core.extend( echopoint.internal.AbstractContainerSync,
{
  $abstract: true,

  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.ABSTRACT_IMAGE, this );
  },

  $virtual:
  {
    /** The image that is to be rendered. */
    image: null,

    createImage: function()
    {
      this.image = document.createElement( "img" );
      this.image.id = this.component.renderId;
      Echo.Sync.ImageReference.renderImg(
          this.component.render( echopoint.internal.AbstractImage.URL ), this.image );
      Core.Web.Event.add( this.image, "click",
          Core.method( this, this.processClick ), false );

      this.renderStyle( this.image );
      this.renderImageStyle();
      return this.image;
    },

    renderImageStyle: function( update )
    {
      if ( ! update )
      {
        this.image.alt = this.component.render(
            echopoint.internal.AbstractImage.TEXT,
            echopoint.constants.ABSTRACT_IMAGE );
        this.image.title = this.component.render(
            echopoint.internal.AbstractImage.TOOL_TIP_TEXT, "" );
        this.image.style.cursor = this.component.render(
            echopoint.internal.AbstractImage.CURSOR, "auto" );
        return;
      }

      var property = update.getUpdatedProperty( echopoint.internal.AbstractImage.TEXT );
      if ( property )
      {
        this.image.alt = property.newValue;
      }

      property = update.getUpdatedProperty( echopoint.internal.AbstractImage.TOOL_TIP_TEXT );
      if ( property )
      {
        this.image.title = property.newValue;
      }

      property = update.getUpdatedProperty( echopoint.internal.AbstractImage.CURSOR );
      if ( property )
      {
        this.image.style.cursor = property.newValue;
      }
    },

    processClick: function()
    {
      if ( !this.client || !this.client.verifyInput( this.component ) ) return true;

      this.component.application.setFocusedComponent( this.component );
      this.component.doAction();
    }
  }
});
