/**
 * Command execution peer: Close the browser
 */
Echo.RemoteClient.CommandExec.RestartApp = Core.extend(Echo.RemoteClient.CommandExec, {
  $static:  {
    /** @see Echo.RemoteClient.CommandExecProcessor#execute */
    execute: function(client, commandData) {
      var cookies = document.cookie.split(";");
      var cookiesToRemove = commandData.cookiesToRemove.split(";");
      var path = window.location.href;	

      var start = path.indexOf('/', path.indexOf('://') + 3);
      var end = path.indexOf('/', start + 1);
      
      if(end < 0) {
        end = path.length - 1;
      }
      
      var app = path.substring(start, end);

      for(var cookie in cookies) {        
        var name = cookies[cookie].split("=")[0].replace(/^\s+|\s+$/g, '');
        var idx = cookiesToRemove.indexOf(name);      
        if(idx >= 0) {
            document.cookie = name + '=a; expires=Monday, 19-Aug-1996 05:00:00 GMT; Path=' + app;
        }
      }
      window.location.href = path;
    }
  },
  
  $load: function() {
    Echo.RemoteClient.CommandExecProcessor.registerPeer("echopoint.command.RestartApp", this);
  }
});