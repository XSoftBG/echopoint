/**
 * Component rendering peer: FileUploadSelector
 * Merged from Echo3 FileTransfer and old Tucana FileTransfer libraries.
 *
 * @author Rakesh 2008-10-30
 * @version $Id: Sync.FileUploadSelector.js,v 1.2 2011-05-30 14:44:34 perxi Exp $
 */
echopoint.tucana.FileUploadSelectorSync = Core.extend( echopoint.internal.AbstractContainerSync,
{
  $load: function()
  {
    Echo.Render.registerPeer(echopoint.constants.FILE_UPLOAD_SELECTOR, this);
  },

  $static:
  {
    _STAGE_LOADED: 1,
    _STAGE_QUEUED: 2,
    _STAGE_UPLOADING: 3,

    /**
     * Service URIs.
     */
    _PROGRESS_SERVICE: "?sid=echopoint.tucana.UploadProgressService",
    _RECEIVER_SERVICE: "?sid=echopoint.tucana.UploadReceiverService",

    /** The default width for the file selection dialogue. */
    DEFAULT_WIDTH: "300px",

    /** the default height for the component. */
    DEFAULT_HEIGHT: "42px"
  },

  /** The container element in which the iframe is hidden. */
  _hidden: null,

  /** The parent container in which the upload select interface is rendered. */
  _div: null,

  /** The table used to render the UI. */
  _table: null,

  /** The upload id that represents the serial number for upload requests. */
  _uploadIndex: null,

  /** The array of iframes created to manage file upload requests. */
  _frames: new Array(),

  /** The form that is used to submit the selected file. */
  _form: null,

  renderAdd: function( update, parentElement )
  {
    this._uploadIndex = this._createUploadIndex();
    this._createHidden();
    this._createFrame();
    parentElement.appendChild( this._createParent( update ) );

    if ( this.component.getComponentCount() > 0 )
    {
      this.renderAddChild( update, this.component.getComponent( 0 ) );
    }
  },

  renderAddChild: function( update, child )
  {
    Echo.Render.renderComponentAdd( update, child, this._div );
  },

  renderDispose: function()
  {
    for ( var uploadId in this._frames )
    {
      if ( uploadId != 'remove' && uploadId != 'contains' && uploadId != 'indexOf' )
      {
        this._frames[uploadId]._dispose();
      }
    }

    this._table._renderDispose();

    var progress = this.component.getComponent( 0 );
    if ( progress && progress.peer && progress.peer.displayed )
    {
      var progressElement = progress.peer._div ;
      if ( progressElement ) Core.Web.DOM.removeNode( progressElement );
    }

    this._getBody().removeChild( this._hidden );
    this._hidden = null;
    this._div = null;
    this._table = null;
    this._frames = new Array();
    this._form = null;
  },

  renderUpdate: function( update )
  {
    if ( update )
    {
      var canceledUploadsUpdate = update.getUpdatedProperty(
          echopoint.tucana.FileUploadSelector.UPLOAD_CANCELLED );

      if ( canceledUploadsUpdate )
      {
        var canceledUploads = canceledUploadsUpdate.newValue.split( "," );

        for ( var i = 0; i < canceledUploads.length; i++ )
        {
          var frame = this._frames[canceledUploads[i]];
          if ( frame ) frame._processCancel();
        }

        return false;
      }
    }

    var element = this._div;
    var containerElement = element.parentNode;
    this.renderDispose( update );
    containerElement.removeChild( element );
    this.renderAdd( update, containerElement );
    return false;
  },

  /** Over-ridden to return the custom default height. */
  getDefaultHeight: function()
  {
    return echopoint.tucana.FileUploadSelectorSync.DEFAULT_HEIGHT;
  },

  /** Over-ridden to return the custom default width. */
  getDefaultWidth: function()
  {
    return echopoint.tucana.FileUploadSelectorSync.DEFAULT_WIDTH;
  },

  /** Create a unique upload index value. */
  _createUploadIndex: function()
  {
    return Math.floor( Math.random() * 1073741824 ).toString( 36 )
        + new Date().getTime().toString( 36 );
  },

  /** Create the hidden container element that holds the iframe for submission. */
  _createHidden: function()
  {
    this._hidden = document.createElement( "div" );
    this._hidden.style.position = "absolute";
    this._hidden.style.top = "0";
    this._hidden.style.marginLeft = "-10000px";
    this._getBody().appendChild( this._hidden );
  },

  /** Create a new iframe to handle file upload and cancel. */
  _createFrame: function()
  {
    var frame = new echopoint.tucana.FileUploadSelectorSync.Frame(
        this, this._uploadIndex );
    frame._renderAdd( this._hidden );
    this._frames[this._uploadIndex] = frame;
    return frame;
  },

  /** Create the parent container that holds the upload select interface. */
  _createParent: function()
  {
    this._div = document.createElement( "div" );
    this._div.id = this.component.renderId;
    this.renderStyle( this._div );

    var form = this._createForm();
    this._createTable( form );
    this._div.appendChild( form );
    return this._div;
  },

  /** Create the form used to select file and submit for uploading. */
  _createForm: function()
  {
    var version = this._getIEVersion();

    if ( Core.Web.Env.BROWSER_INTERNET_EXPLORER && version < 9.0 )
    {
      this._form = document.createElement( "<form enctype='multipart/form-data'/>" );
    }
    else
    {
      this._form = document.createElement( "form" );
      this._form.enctype = "multipart/form-data";
    }

    this._form.id = this.component.renderId + "|Form|" + this._uploadIndex;
    this._form.method = "POST";
    this._form.style.margin = "0px";
    this._form.style.padding = "0px";
    this._form.style.position = "relative";
    this._form.action = this._createUploadUrl();

    var frame = this._frames[this._uploadIndex];
    Core.Web.Event.add( this._form, "submit",
        Core.method( frame, frame._processSubmit ), false );

    return this._form;
  },

  /** Create the table that holds the button used to submit/cancel the upload. */
  _createTable: function( parentElement )
  {
    this._table = new echopoint.tucana.FileUploadSelectorSync.Table( this );
    this._table._renderAdd( parentElement );

    return this._table;
  },

  _createUploadUrl: function()
  {
    return echopoint.tucana.FileUploadSelectorSync._RECEIVER_SERVICE + "&i=" + this.component.renderId +
           (this.client._uiid !== null ? "&uiid="+this.client._uiid : "") + "&x=" + this._uploadIndex;
  },

  /**
   * Starts one of the queued uploads if there are any.
   *
   * @return <tt>true</tt> if an upload was started.
   * @type Boolean
   */
  _startNextUpload: function()
  {
    for ( var uploadId in this._frames )
    {
      var frame = this._frames[uploadId];
      if ( frame._loadStage == echopoint.tucana.FileUploadSelectorSync._STAGE_QUEUED )
      {
        frame._startUpload();
        return true;
      }
    }
    return false;
  },

  /**
   * Removes the given upload frame.
   *
   * @param frame {echopoint.tucana.FileUploadSelectorSync}
   */
  _removeFrame: function( frame )
  {
    delete this._frames[frame._uploadIndex];
    frame._dispose();
  },

  /**
   * Removes the specified frame and creates a new one.
   *
   * @param frame {echopoint.tucana.FileUploadSelectorSync}
   */
  _refreshFrame: function( frame )
  {
    this._removeFrame( frame );
    this._table._renderDispose();
    this._div.removeChild( this._form );

    this._uploadIndex = this._createUploadIndex();
    this._createFrame();
    var form = this._createForm();
    this._createTable( form );
    this._div.appendChild( form );

    var progress = this.component.getComponent( 0 );
    if ( progress && progress.peer && progress.peer.displayed )
    {
      var child = progress.peer._div;
      this._div.removeChild( child );

      progress.peer.renderUpdate();

      this._div.appendChild( child );
    }
  },

  /** Return the body of the main browser frame. */
  _getBody: function()
  {
    return document.getElementsByTagName( "body" ).item( 0 );
  },

  /** Return IE version.  9 and above is more standards compliant. */
  _getIEVersion : function()
  {
    var rv = -1; // Return value assumes failure or other kind of a browser

    if ( navigator.appName == 'Microsoft Internet Explorer' )
    {
      var ua = navigator.userAgent;
      var re  = new RegExp( 'MSIE ([0-9]{1,}[\.0-9]{0,})' );
      if ( re.exec(ua) != null ) rv = parseFloat( RegExp.$1 );
    }

    return rv;
  }
});

/**
 * Represents an upload frame that is used to manage the file upload process.
 */
echopoint.tucana.FileUploadSelectorSync.Frame = Core.extend(
{
  /** The rendering peer that instantiates this instance. */
  peer: null,

  /** The component associated with the rendering peer. */
  component: null,

  /** The upload index for which this frame is created. */
  _uploadIndex: null,

  /** A value that indicates the upload stage. */
  _loadStage: null,

  /** The frame that this object encapsulates. */
  _frameElement: null,

  /** The Http connection used to poll the upload progress. */
  _connection: null,

  /** The progress interval to use to poll the progress service. */
  _pollingInterval: 0,

  /**
   * @param uploadSelectPeer {Echo.Render.ComponentSync}
   * @param uploadId {Number} the upload index
   */
  $construct: function( uploadSelectPeer, uploadId )
  {
    this.peer = uploadSelectPeer;
    this.component = uploadSelectPeer.component;
    this._uploadIndex = uploadId;
    this._submitListenerBound = false;

    this._pollingInterval = this.component.render(
        echopoint.tucana.FileUploadSelector.POLLING_INTERVAL,
        echopoint.tucana.FileUploadSelector.DEFAULT_POLLING_INTERVAL );
  },

  _renderAdd: function( parentElement )
  {
    //var processLoad = Core.method( this, this._processLoad );
    var frameId = this.component.renderId + "|Frame|" + this._uploadIndex;
    var srcUrl = this.peer.client.getResourceUrl( "Echo", "resource/Blank.html" );

    if ( Core.Web.Env.BROWSER_INTERNET_EXPLORER )
    {
      parentElement.innerHTML = "<iframe id=\"" + frameId
          + "\" name=\"" + frameId + "\" src=\"" + srcUrl + "\" "
          + "frameborder='0' style='width:0px;height:0px;border:0;visibility:hidden;'"
          + "scrolling=\"no\"></iframe>";
      this._frameElement = parentElement.firstChild;
      //Core.Web.Event.add( this._frameElement, "load", processLoad, false );
    }
    else
    {
      this._frameElement = document.createElement( "iframe" );
      this._frameElement.id = frameId;
      this._frameElement.name = frameId;
      this._frameElement.src = srcUrl;
      this._frameElement.scrolling = "no";
      this._frameElement.setAttribute( "style", "width:0px;height:0px;border:0;visibility:hidden;" );
      //this._frameElement.onload = processLoad;
      parentElement.appendChild( this._frameElement );
    }
  },

  /**
   * Starts this upload and starts the progress poller.
   */
  _startUpload: function()
  {
    this._loadStage = echopoint.tucana.FileUploadSelectorSync._STAGE_UPLOADING;
    this.peer._form.target = this._frameElement.id;
    this.peer._form.submit();

    if ( this.peer._table._submit )
    {
      this.peer._table._submit._renderButton( true, this.peer._table );
    }

    if ( !Core.Web.Env.BROWSER_SAFARI && !Core.Web.Env.BROWSER_CHROME )
    {
      // WebKit browsers refuses to upload when file element is disabled
      this.peer._table._input.disabled = true;
    }

    this.component.notifyStart( this._uploadIndex );
    this._startProgressPoller();
  },

  /**
   * Instructs this frame that the upload has ended.
   */
  _uploadEnded: function()
  {
    this._stopProgressPoller();
    this.component.notifyComplete( this._uploadIndex );
    if ( this._connection ) this._connection.dispose();
    this.peer._refreshFrame( this );
  },

  _pollProgress: function()
  {
    if ( !this._enableProgressPoll )
    {
      return;
    }

    this._connection = new Core.Web.HttpConnection(
        this._createProgressUrl(), "GET", null, null );
    this._connection.addResponseListener(
        Core.method( this, this._processProgressResponse ) );
    this._connection.connect();
  },

  _processProgressResponse: function( e )
  {
    if ( e.source.getStatus() != 200 )
    {
      Core.Debug.consoleWrite( "Invalid response: " + e.source.getResponseText() );
    }

    Core.Debug.consoleWrite( "Response: " + e.source.getResponseText() );
    var json = e.source.getResponseText();
    var uploadProgress = new echopoint.tucana.UploadProgress( json );
    var text = parseInt( uploadProgress.bytesRead / 1000 ) +
               " of " + parseInt( uploadProgress.contentLength / 1000 ) + "Kb";

    if ( this.component && this.component.getComponentCount() > 0 )
    {
      var progressBar = this.component.getComponent( 0 );
      if ( progressBar.peer && progressBar.peer.displayed )
      {
        var pattern = progressBar.render(
            echopoint.tucana.FileUploadSelector.PATTERN );
        if ( pattern )
        {
          pattern = pattern.replace( "#bytes#",
              parseInt(uploadProgress.bytesRead / 1000 ) );
          pattern = pattern.replace( "#length#",
              parseInt( uploadProgress.contentLength / 1000 ) );
          pattern = pattern.replace( "#rate#",
              parseInt( uploadProgress.transferRate / 1000 ) );
          pattern = pattern.replace( "#percent#", uploadProgress.percentComplete );
          pattern = pattern.replace( "#time#", uploadProgress.estimatedTimeLeft );
          progressBar.set( echopoint.ProgressBar.TEXT, pattern );
        }
        else
        {
          progressBar.set( echopoint.ProgressBar.TEXT, text );
        }

        progressBar.set( echopoint.ProgressBar.PERCENTAGE, uploadProgress.percentComplete );
        progressBar.peer.renderUpdate();
      }
    }
    else
    {
      Core.Debug.consoleWrite( text );
    }

    var status = uploadProgress.status;
    if ( status == "completed" || status == "failed" || status == "cancelled"  || status == "disallowed" )
    {
      if ( uploadProgress.message )
      {
        if ( progressBar && progressBar.peer.displayed )
          progressBar.set( echopoint.ProgressBar.TEXT, uploadProgress.message );
        else
        if ( status != "completed" )
          alert( uploadProgress.message );
      }

      this._uploadEnded();
    }

    if ( this._enableProgressPoll )
    {
      this._startProgressPoller();
    }
  },

  _startProgressPoller: function()
  {
    this._enableProgressPoll = true;
    Core.Web.Scheduler.run(
        Core.method( this, this._pollProgress ), this._pollingInterval, false );
  },

  _stopProgressPoller: function()
  {
    this._enableProgressPoll = false;
  },

  _processCancel: function()
  {
    Core.Debug.consoleWrite( "File upload cancelled!" );
    this._uploadEnded();
  },

  _processSubmit: function( e )
  {
    if ( e )
    {
      Core.Web.DOM.preventEventDefault( e );
    }

    if ( ! this.peer._table._input ) return;

    this._loadStage = echopoint.tucana.FileUploadSelectorSync._STAGE_QUEUED;
    // remove listener before document gets disposed
    Core.Web.Event.remove( this.peer._form,
        "submit", Core.method( this, this._processSubmit ), false );
    this._submitListenerBound = false;

    if ( this.component.render( "queueEnabled" ) )
    {
      this.component.peer._createFrame();
    }

    this.peer._startNextUpload();
  },

  _processLoad: function()
  {
    if ( this._loadStage )
    {
      this._uploadEnded();
      return;
    }

    this._loadStage = echopoint.tucana.FileUploadSelectorSync._STAGE_LOADED;
  },

  _createProgressUrl: function()
  {
    return echopoint.tucana.FileUploadSelectorSync._PROGRESS_SERVICE + "&i=" + this.component.renderId +
           (this.peer.client._uiid !== null ? "&uiid="+this.peer.client._uiid : "") + "&x=" + this._uploadIndex;
  },

  _dispose: function()
  {
    this._stopProgressPoller();

    if ( this._submitListenerBound )
    {
      Core.Web.Event.remove( this.peer._form,
          "submit", Core.method( this, this._processSubmit ), false);
      this._submitListenerBound = false;
    }

    if ( this._loadStage == echopoint.tucana.FileUploadSelectorSync._STAGE_UPLOADING )
    {
      // gracefully stop upload
      var frameWindow = this._frameElement.contentWindow;
      if ( frameWindow.stop )
      {
        frameWindow.stop();
      }
      else if ( frameWindow.document && frameWindow.document.execCommand )
      {
        frameWindow.document.execCommand( "Stop" );
      }
      else
      {
        frameWindow.location.href =
            this.peer.client.getResourceUrl( "Echo", "resource/Blank.html" );
      }
    }

    if ( Core.Web.Env.BROWSER_MOZILLA )
    {
      // bypass waiting forever bug
      var frame = this._frameElement;
      setTimeout( function()
      {
        if ( frame && frame.parentNode )
        {
          frame.parentNode.removeChild( frame );
        }
        else
        {
          Core.Debug.consoleWrite( "No parent node for frame" );
        }
      }, 0 );
    }
    else
    if ( this._frameElement )
    {
      Core.Web.DOM.removeNode( this._frameElement );
    }

    this.component = null;
    this._uploadIndex = null;
    this._loadStage = null;
    this._frameElement = null;
  }
});

/**
 * A table that represents the user interface that is displayed to the user.
 */
echopoint.tucana.FileUploadSelectorSync.Table = Core.extend(
{
  /** The table element encapsulated by this class. */
  _table: null,

  /** The body element for the table. */
  _tbody: null,

  /** The input used to dislay the file selection dialogue. */
  _input: null,

  /**
   * The submit button used to initiate the upload.  This will be replaced
   * with a cancel button upon submission.
   */
  _submit: null,

  /** The table column used to display the submit button on the left. */
  _tdSubmitLeft: null,

  /** The table column used to display the submit button on the right. */
  _tdSubmitRight: null,

  /**
   * @param uploadSelectPeer {Echo.Render.ComponentSync}
   */
  $construct: function( uploadSelectPeer )
  {
    this.peer = uploadSelectPeer;
    this.component = uploadSelectPeer.component;
  },

  _renderAdd: function( parentElement )
  {
    parentElement.appendChild( this._createTable() );
    this._tbody = document.createElement( "tbody" );
    this._tbody.appendChild( this._createRow() );
    this._table.appendChild( this._tbody );
  },

  _renderDispose: function()
  {
    if ( this._submit ) this._submit._renderDispose();
    this._submit = null;
    this._input = null;
    this._tdSubmitLeft = null;
    this._tdSubmitRight = null;
    this._tbody = null;
    this._table = null;
  },

  _createTable: function()
  {
    this._table = document.createElement( "table" );
    this._table.id = this.component.renderId + "|Table";
    this._table.style.borderCollapse = "collapse";
    this._table.appendChild( document.createElement( "thead" ) );

    return this._table;
  },

  _createRow: function()
  {
    var tr = document.createElement( "tr" );

    this._tdSubmitLeft = document.createElement( "td" );
    this._tdSubmitLeft.style.display = "none";
    this._tdSubmitLeft.style.padding = "0px 2px 0px 0px";
    tr.appendChild( this._tdSubmitLeft );

    var tdInput = document.createElement( "td" );
    tdInput.style.padding = "0px 2px 0px 2px";
    tdInput.appendChild( this._createInput() );
    tr.appendChild( tdInput );

    this._tdSubmitRight = document.createElement( "td" );
    this._tdSubmitRight.style.display = "none";
    this._tdSubmitRight.style.padding = "0px 0px 0px 2px";
    this._tdSubmitRight.style.padding = "1px";
    tr.appendChild( this._tdSubmitRight );

    this._createSubmit();

    return tr;
  },

  _createInput: function()
  {
    this._input = document.createElement( "input" );
    this._input.id = this.component.renderId + "|Input|" + this.peer._uploadIndex;
    this._input.type = "file";
    this._input.name = "$$file$$";

    var size = Echo.Sync.Extent.toPixels( this.component.render(
        echopoint.tucana.FileUploadSelector.INPUT_SIZE,
        echopoint.tucana.FileUploadSelector.DEFAULT_INPUT_SIZE ) );
    this._input.setAttribute( "size", parseInt( size ) );

    Echo.Sync.Color.render( this.component.render(
        echopoint.internal.AbstractContainer.FOREGROUND ),
        this._input, "color");
    this.peer.renderInsets( this._input );
    this.peer.renderFont( this._input );

    return this._input;
  },

  _createSubmit: function()
  {
    var display = this.component.render(
        echopoint.tucana.FileUploadSelector.BUTTON_DISPLAY,
        echopoint.tucana.FileUploadSelector.DEFAULT_BUTTON_DISPLAY );

    if ( display != "none" )
    {
      this._submit = new echopoint.tucana.FileUploadSelectorSync.Button( this.peer );
      this._submit._renderAdd( this );
    }
    else
    {
      var instance = this.peer;
      this._input.onchange = function( e )
      {
        instance._frames[instance._uploadIndex]._processSubmit( e );
      };
    }

    return this._submit;
  }
});

/** A button class used to render the submit button for the upload. */
echopoint.tucana.FileUploadSelectorSync.Button = Core.extend(
{
  /** The input element that is used to trigger the form submission. */
  _submit: null,

  /** An indicator for using text or image based button. */
  _mode: null,

  /** The text to display for the upload button. */
  _uploadText: null,

  /** The text to display when the upload has been changed to cancel. */
  _cancelText: null,

  /** The text to display a wait message when no progress bar is shown. */
  _waitText: null,

  /** The image to use as the display for the upload button. */
  _uploadImage: null,

  /** The image to use to display the cancel button. */
  _cancelImage: null,

  /** The image to display the wait message when no progress bar is shown. */
  _waitImage: null,

  /** The rule (position or hide) to use for the button. */
  _display: null,

  /** A flag indicating whether the cancel button is to be displayed. */
  _cancel: true,

  /**
   * @param uploadSelectPeer {Echo.Render.ComponentSync}
   */
  $construct: function( uploadSelectPeer )
  {
    this.peer = uploadSelectPeer;
    this.component = uploadSelectPeer.component;
  },

  _renderAdd: function( parentElement )
  {
    this._submit = document.createElement( "input" );

    this._renderStyle();
    this._renderButton( false, parentElement );
  },

  _renderStyle: function()
  {
    this._setButtonMode();
    this._setUploadText();
    this._setCancelText();
    this._setWaitText();
    this._setUploadImage();
    this._setCancelImage();
    this._setWaitImage();
    this._setDisplay();
    this._setCancel();

    Echo.Sync.Font.render( this.component.render( "font" ), this._submit );
    Echo.Sync.Color.render(
        this.component.render( "foreground" ), this._submit, "color" );
  },

  _setButtonMode: function()
  {
    this._mode = this.component.render(
        echopoint.tucana.FileUploadSelector.BUTTON_MODE,
        echopoint.tucana.FileUploadSelector.DEFAULT_BUTTON_MODE );
  },

  _setUploadText: function()
  {
    this._uploadText = this.component.render(
        echopoint.tucana.FileUploadSelector.BUTTON_TEXT_UPLOAD,
        echopoint.tucana.FileUploadSelector.DEFAULT_UPLOAD_TEXT );
  },

  _setCancelText: function()
  {
    this._cancelText = this.component.render(
        echopoint.tucana.FileUploadSelector.BUTTON_TEXT_CANCEL,
        echopoint.tucana.FileUploadSelector.DEFAULT_CANCEL_TEXT );
  },

  _setWaitText: function()
  {
    this._waitText = this.component.render(
        echopoint.tucana.FileUploadSelector.BUTTON_TEXT_WAIT,
        echopoint.tucana.FileUploadSelector.DEFAULT_WAIT_TEXT );
  },

  _setUploadImage: function()
  {
    this._uploadImage = this.component.render(
        echopoint.tucana.FileUploadSelector.BUTTON_IMAGE_UPLOAD,
        this.peer.client.getResourceUrl( "echopoint",
            echopoint.tucana.FileUploadSelector.DEFAULT_IMAGE_UPLOAD ) );
  },

  _setCancelImage: function()
  {
    this._cancelImage = this.component.render(
        echopoint.tucana.FileUploadSelector.BUTTON_IMAGE_CANCEL,
        this.peer.client.getResourceUrl( "echopoint",
        echopoint.tucana.FileUploadSelector.DEFAULT_IMAGE_CANCEL ) );
  },

  _setWaitImage: function()
  {
    this._waitImage = this.component.render(
        echopoint.tucana.FileUploadSelector.BUTTON_IMAGE_WAIT,
        this.peer.client.getResourceUrl( "echopoint",
        echopoint.tucana.FileUploadSelector.DEFAULT_IMAGE_WAIT ) );
  },

  _setDisplay: function()
  {
    this._display = this.component.render(
        echopoint.tucana.FileUploadSelector.BUTTON_DISPLAY,
        echopoint.tucana.FileUploadSelector.DEFAULT_BUTTON_DISPLAY );
  },

  _setCancel: function()
  {
    this._cancel = this.component.render(
        echopoint.tucana.FileUploadSelector.CANCEL_ENABLED,
        echopoint.tucana.FileUploadSelector.DEFAULT_CANCEL_ENABLED );
  },

  /**
   * @param uploading A flag indicating whether an upload is in process.
   * @param parentElement {echopoint.tucana.FileUploadSelectorSync.Table}
   */
  _renderButton: function( uploading, parentElement )
  {
    var mode = this._mode;
    // Safari does not seem to work with submit images
    if ( Core.Web.Env.BROWSER_SAFARI )
    {
      mode = "submit";
      Core.Debug.consoleWrite( "Using text buttons for Safari as image does not seem to work!" );
    }

    this._submit.setAttribute( "type", mode );
    if ( mode == "image" )
    {
      var src = uploading ? (this._cancel ? this._cancelImage : this._waitImage) : this._uploadImage;
      this._submit.setAttribute( "src", src );
    }
    else
    {
      var text = uploading ? (this._cancel ? this._cancelText : this._waitText) : this._uploadText;
      if ( text == null )
      {
        this._submit.removeAttribute( "value" );
      }
      else
      {
        this._submit.setAttribute( "value", text );
      }

      Echo.Sync.Color.render( this.component.render(
          echopoint.internal.AbstractContainer.FOREGROUND ),
          this._submit, "color");
      this.peer.renderFont( this._submit );
    }

    this._submit.disabled = disabled;
    this.peer.renderInsets( this._submit );

    var displayType = this._display;
    if ( displayType == "auto" )
    {
      displayType = Core.Web.Env.BROWSER_SAFARI ? "left" : "right";
    }


    Core.Web.DOM.removeAllChildren( parentElement );

    switch ( displayType )
    {
      case "right":
        parentElement._tdSubmitRight.appendChild( this._submit );
        parentElement._tdSubmitRight.style.display = "block";
        parentElement._tdSubmitLeft.style.display = "none";
        break;
      case "left":
        parentElement._tdSubmitLeft.appendChild( this._submit );
        parentElement._tdSubmitLeft.style.display = "block";
        parentElement._tdSubmitRight.style.display = "none";
        break;
      default:
        parentElement._tdSubmitLeft.style.display = "none";
        parentElement._tdSubmitRight.style.display = "none";
        break;
    }
  }
});
