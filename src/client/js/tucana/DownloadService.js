/**
 * Command exeecution peer: Download
 */
echopoint.tucana.DownloadService =
{
  execute: function( client, commandData )
  {
    if ( ! commandData.uri )
    {
      throw new Error( "uri not specified in DownloadCommand." );
    }

    top.location = commandData.uri;
  }
};

Echo.RemoteClient.CommandExecProcessor.registerPeer(
    "echopoint.tucana.DownloadCommand", echopoint.tucana.DownloadService );
