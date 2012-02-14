/**
 * Command exeecution peer: Download
 */

echopoint.constants.DOWNLOAD_MODE_DEFAULT= 0;
echopoint.constants.DOWNLOAD_MODE_NEW_TAB = 1;

echopoint.tucana.DownloadService =
{  
  execute: function(client, commandData)
  {
    if(!commandData.uri)
      throw new Error( "uri not specified in DownloadCommand." );

    if(document.createEvent // works with firefox and webkit based browsers
       && commandData.downloadMode == echopoint.constants.DOWNLOAD_MODE_NEW_TAB)
    {
      var anchor = document.createElement("a");
      var body = document.getElementsByTagName("body")[0];
      var evObj = document.createEvent('MouseEvents');
      evObj.initEvent("click", true, false);
      anchor.href = commandData.uri;
      anchor.target = "_blank";
      body.appendChild(anchor);
      anchor.dispatchEvent(evObj);
      body.removeChild(anchor);
    }
    else
      top.location = commandData.uri;
  }
};

Echo.RemoteClient.CommandExecProcessor.registerPeer("echopoint.tucana.DownloadCommand", echopoint.tucana.DownloadService);
