/**
 * Command execution peer: Close the browser
 */
Echo.RemoteClient.CommandExec.CloseBrowser = Core.extend(Echo.RemoteClient.CommandExec, 
{
  $static: 
  {
    /** @see Echo.RemoteClient.CommandExecProcessor#execute */
    execute: function(client, commandData) 
    {
      if( !window.closed )
      { 
        window.open("", "_self", "");
        window.close();
        if( navigator.appName == "Netscape" )
        {
          if( !window.closed ) 
            alert( "CloseBrowser command failed. It works with FireFox if you set the variable dom.allow_scripts_to_close_windows=true in your config (about:config)!" );
        }
      }
    }
  },
  
  $load: function() 
  {
    Echo.RemoteClient.CommandExecProcessor.registerPeer("echopoint.command.CloseBrowser", this);
  }
});
