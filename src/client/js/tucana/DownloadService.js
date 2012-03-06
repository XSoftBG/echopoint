/**
 * Command exeecution peer: Download
 */

echopoint.constants.DOWNLOAD_MODE_DEFAULT= 0;
echopoint.constants.DOWNLOAD_MODE_NEW_TAB = 1;
echopoint.constants.DOWNLOAD_MODE_PRINT = 2;

uniquePrintId = 0;

echopoint.tucana.DownloadService =
{
  execute: function(client, commandData)
  {
    if(!commandData.uri)
      throw new Error( "uri not specified in DownloadCommand." );

    switch(commandData.downloadMode)
    {
      case echopoint.constants.DOWNLOAD_MODE_NEW_TAB:
      {
        if(document.createEvent)
          this._openInNewTabAction(commandData);
        else
          this._defautDownloadAction(commandData);
        break;
      }      
      case echopoint.constants.DOWNLOAD_MODE_PRINT:
      {
        this._printAction(commandData);
        break;
      }
      default:
      {
        this._defautDownloadAction(commandData);
        break;
      }
    }
  },
  
  _openInNewTabAction: function(commandData) {
    var anchor = document.createElement("a");
    var body = document.getElementsByTagName("body")[0];
    var evObj = document.createEvent('MouseEvents');
    evObj.initEvent("click", true, false);
    anchor.href = commandData.uri;
    anchor.target = "_blank";
    body.appendChild(anchor);
    anchor.dispatchEvent(evObj);
    body.removeChild(anchor);
  },
  
  _defautDownloadAction: function(commandData) {
    top.location = commandData.uri;
  },
  
  _printAction: function(commandData) {
    var printFrame = document.createElement('iframe');
		printFrame.name = "printFrame" + ++uniquePrintId;
		printFrame.style.display = 'none';
		printFrame.style.width = '0';
		printFrame.style.height = '0';
		printFrame.onload = function() {
      setTimeout(function(frame) { // give it some time to render the content if plugin is used (for example when printing pdf files)
       window.frames[frame.name].focus();
       window.frames[frame.name].print();
       document.getElementsByTagName('body')[0].removeChild(frame);
      }, 1000, this);
    };
		printFrame.src = commandData.uri;
    document.getElementsByTagName('body')[0].appendChild(printFrame);
  }
};

Echo.RemoteClient.CommandExecProcessor.registerPeer("echopoint.tucana.DownloadCommand", echopoint.tucana.DownloadService);
