/**
 * Command execution peer: Browser Open Window
 */
Echo.RemoteClient.CommandExec.JavaScriptEval = Core.extend(Echo.RemoteClient.CommandExec, {

    $static: {

        /** @see Echo.RemoteClient.CommandExecProcessor#execute */
        execute: function(client, commandData) {
            if (!commandData.script) {
                throw new Error("Script not specified in javaScriptEval.");
            }

            eval(commandData.script);

        }
     },

     $load: function() {
        Echo.RemoteClient.CommandExecProcessor.registerPeer("echopoint.command.JavaScriptEval", this);
     }
});

