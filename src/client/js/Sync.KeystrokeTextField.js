/**
 * Component rendering peer: KeystrokeTextField
 *
 */
echopoint.KeystrokeTextFieldSync = Core.extend( echopoint.internal.TextFieldSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.KEYSTROKE_TEXT_FIELD, this );
  },

  /** The keyUp function implementation. */
  clientKeyUp : function( event )
  {
    //var newText = this.component.get("text");
    //Core.Debug.consoleWrite("text in keyUpEvent is: "+newText);
    //Core.Debug.consoleWrite("input.value in keyUpEvent is: "+this.input.value);

    event = (event) ? event : window.event;
    if(event.keyCode != 13)
    {
      var keystrokeDelay = this.component.render("keystrokeDelay", 0);
      if(keystrokeDelay > 0)
        this.scheduleSync(keystrokeDelay);
      else
        this.executeSync();
    }
    return true;
  },

  $construct: function()
  {
    echopoint.internal.TextFieldSync.call( this );
  },

  scheduleSync : function(delay) 
  {
    // Cancel pending sync before a new sync is scheduled,
    // so only the last sync in a row is executed
    // while someone is typing rapidly.
    // Core.Debug.consoleWrite("Schedulesync with "+delay+"ms");
    this.cancelPendingSync();
    this.pendingSync = Core.Web.Scheduler.run(Core.method(this, this.executeSync), delay, false);
  },

  cancelPendingSync : function() 
  {
    if (this.pendingSync !== undefined) 
    {
      Core.Web.Scheduler.remove(this.pendingSync);
      delete this.pendingSync;
    }
  },

  executeSync : function() 
  {
    if(this.component != null)
    {
      // Since the echo base text component stores the input value only
      // Before processing events, we have to force it do be done here.
      // Otherwise our doAction command will be processed without the last
      // updates to the text field. (Usually last character entered missing)
      var newText = this.input.value;
      var oldText = this._lastProcessedValue;
    
      this._storeSelection();
      this._storeValue(null);
    
      if(oldText != newText) 
      {
        this.component.doAction();
      }
      else 
      {
        // Core.Debug.consoleWrite("Execsync text not changed");
      }
    }
    else 
    {
      // Core.Debug.consoleWrite("Execsync component is null (removed from dom?)");
    }
  },

  // Cancel any pending syncs on dispose
  renderDispose : function(update) 
  {
    this.cancelPendingSync();
    echopoint.internal.TextFieldSync.prototype.renderDispose.call(this, update);
  }
});
