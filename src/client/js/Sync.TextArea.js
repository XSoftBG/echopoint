/**
 * Base component rendering peer for text components.  Exposes event
 * handling functions for sub-classes.
 *
 * @author ASchild 2009-12-28
 * @version: $Id: Sync.TextArea.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.internal.TextAreaSync = Core.extend( Echo.Sync.TextArea,
{
  $abstract: true,

  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.TEXT_AREA, this );
  },

  $virtual:
  {
    /** The default text for the input.  Will be cleared after first use. */
    defaultText: null,
    /** We store initial height here, to not shrink the textarea below this */
    initialHeight: null,
    /** Don't print out debug stuff for this component */
    doDebug: false,
    /** Default to false */
    autoResize: false,

    /** Set the default text if applicable. */
    setDefaultText: function()
    {
      this.defaultText = this.component.render(
          echopoint.internal.TextField.DEFAULT_TEXT );
      var value = this.component.render( "text" );

      if ( this.defaultText && ( ( ! value ) || ( value == '' ) ) )
      {
        Echo.Sync.Color.render(
            Echo.Sync.getEffectProperty( this.component, "foreground",
                "disabledForeground", true ),  this.input, "color" );
        this.input.setAttribute( "value", this.defaultText );
      }
      else
      {
        this.defaultText = null;
      }
    }
  },

  $construct: function()
  {
    Echo.Sync.TextArea.call( this );

  },

  renderAdd: function( update, parentElement )
  {
    Echo.Sync.TextArea.prototype.renderAdd.call(
        this, update, parentElement );
    if (this.component.get("wrap"))
        this.input.setAttribute("wrap", this.component.get("wrap"));
    if (this.component.get("autoResize"))
    {
        if (this.doDebug)
        {
            Core.Debug.consoleWrite("Has autoResize property, is: "+this.component.get("autoResize"));
        }
        this.autoResize= this.component.get("autoResize");
    }

    this.setDefaultText();

  },

  /** The keyUp function implementation. */
  clientKeyUp : function( event )
  {
    if (this.autoResize )
    {
        if (this.doDebug)
        {
            Core.Debug.consoleWrite("autoResize IS active");
            Core.Debug.consoleWrite("input.value in keyUpEvent is: "+this.input.value);
            Core.Debug.consoleWrite("Core.Web.Measure._hEm "+ Core.Web.Measure._hEm);
            Core.Debug.consoleWrite("Core.Web.Measure._vEm "+ Core.Web.Measure._vEm);
            Core.Debug.consoleWrite("input.style.width: "+this.input.style.width);
            Core.Debug.consoleWrite("input.style.height: "+this.input.style.height);
        }
        if (this.initialHeight == null)
        {
            this.initialHeight= this.input.style.height;
            this.initialHeight= this.initialHeight.substring(0, this.initialHeight.length -2);
        }
        var colPX= this.input.style.width;
        var rowPX= this.input.style.height;
        colPX= this.input.style.width.substring(0, colPX.length-2);
        rowPX= this.input.style.height.substring(0, rowPX.length-2);
        if (this.doDebug)
        {
            Core.Debug.consoleWrite("colPX: "+colPX);
            Core.Debug.consoleWrite("rowPX: "+rowPX);
        }

        var styleCols= Math.round((colPX / Core.Web.Measure._hEm)+0.5);
        var styleRows= Math.round((rowPX / Core.Web.Measure._vEm)+0.5);
        if (this.doDebug)
        {
            Core.Debug.consoleWrite("input.style.width: "+styleCols);
            Core.Debug.consoleWrite("input.style.height: "+styleRows);
        }
        var wrapOff= false;
        if (this.component.get("wrap"))
        {
            wrapOff= this.component.get("wrap") == "off";
        }
        var a = this.input.value.split('\n');
        var b=1;
        if (!wrapOff) // Don't look at line length when wrap off
        {
            for (x=0;x < a.length; x++)
            {
                if (a[x].length >= styleCols) b+= Math.round((a[x].length/styleCols)+0.5);
            }
        }
        b+= a.length;
        if (this.doDebug)
        {
            Core.Debug.consoleWrite("autoResize to "+b+" rows ("+b*Core.Web.Measure._vEm+") old value is "+styleRows);
        }
        var newHeight= b*Math.round(Core.Web.Measure._vEm+0.5)*1.2; // Worarround too small height
        if (newHeight < this.initialHeight)
        {
            newHeight= this.initialHeight;
        }
        this.input.style.height= newHeight+"px";
        if (this.doDebug)
        {
            Core.Debug.consoleWrite("AFTER SET this.input.style.height "+ this.input.style.height);
        }
    }
    else
    {
        if (this.doDebug)
        {
            Core.Debug.consoleWrite("autoResize is not active");
        }
    }
    return true;
  }

});
