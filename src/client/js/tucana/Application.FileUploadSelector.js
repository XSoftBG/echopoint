/** The name of the file upload selector component. */
echopoint.constants.FILE_UPLOAD_SELECTOR = "echopoint.tucana.FileUploadSelector";

/**
 * The client-side representation of the tucana file upload seletor component.
 *
 * @author Rakesh 2008-10-30
 * @version $Id: Application.FileUploadSelector.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.tucana.FileUploadSelector = Core.extend( echopoint.internal.AbstractContainer,
{
  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.FILE_UPLOAD_SELECTOR, this );
  },

  /** Properties defined for the component. */
  $static:
  {
    ACTION_START: "start",
    ACTION_COMPLETE: "complete",
    BUTTON_TEXT_UPLOAD: "buttonTextUpload",
    BUTTON_TEXT_CANCEL : "buttonTextCancel",
    BUTTON_TEXT_WAIT : "buttonTextWait",
    BUTTON_IMAGE_UPLOAD: "buttonImageUpload",
    BUTTON_IMAGE_CANCEL: "buttonImageCancel",
    BUTTON_IMAGE_WAIT: "buttonImageWait",
    BUTTON_MODE : "buttonMode",
    BUTTON_DISPLAY: "buttonDisplay",
    CANCEL_ENABLED: "cancelEnabled",

    INPUT_SIZE: "inputSize",
    DEFAULT_INPUT_SIZE: "19",

    UPLOAD_INDEX: "uploadIndex",
    UPLOAD_CANCELLED: "uploadCancelled",

    DEFAULT_BUTTON_MODE: "submit",
    DEFAULT_BUTTON_DISPLAY: "auto",
    DEFAULT_CANCEL_ENABLED: true,
    DEFAULT_IMAGE_UPLOAD: "images/upload.png",
    DEFAULT_IMAGE_CANCEL: "images/cancel.png",
    DEFAULT_IMAGE_WAIT: "images/wait.png",

    /** The default upload button text */
    DEFAULT_UPLOAD_TEXT: "Upload",

    /** The default cancel button text. */
    DEFAULT_CANCEL_TEXT: "Cancel",

    /** The default wait button text. */
    DEFAULT_WAIT_TEXT: "Wait...",

    /**
     * The interval (in milliseconds) at which the progress server will
     * be polled for updates (completion, cancel etc.).
     */
    POLLING_INTERVAL: "pollingInterval",
    DEFAULT_POLLING_INTERVAL: 250,

    /** The property in the embedded progress bar used to specify the text pattern. */
    PATTERN: "pattern"
  },

  /**
   * Fire an action event that indicates that the file upload has started.
   * This is used to start a task queue in the server-side component to
   * assist implementing UI logic in callback handlers.
   *
   * @param uploadId
   */
  notifyStart: function( uploadId )
  {
    this.fireEvent(
    {
      source: this,
      type: echopoint.tucana.FileUploadSelector.ACTION_START,
      actionCommand: uploadId
    });
  },

  /**
   * Fire an action event that indicates that the file upload has finished.
   * This is useful to perform UI updates without having to recourse to
   * task queues.
   *
   * @param uploadId
   */
  notifyComplete: function( uploadId )
  {
    this.fireEvent(
    {
      source: this,
      type: echopoint.tucana.FileUploadSelector.ACTION_COMPLETE,
      actionCommand: uploadId
    });
  },

  componentType: echopoint.constants.FILE_UPLOAD_SELECTOR
});

/**
 * A value object that represents the response from polling the progress
 * service.
 */
echopoint.tucana.UploadProgress = Core.extend(
{
  /** The total bytes that have been read so far. */
  bytesRead: 0,

  /** The content length of the uploading file.*/
  contentLength: 0,

  /** The percentage of the file that has been uploaded so far. */
  percentComplete: 0,

  /** The transfer rate that represents the speed of the upload. */
  transferRate: 0,

  /** The estimated time remaining for the transfer to end. */
  estimatedTimeLeft: 0,

  /** The status of the current upload process. */
  status: null,

  /** An optional message indicating errors sent back by service. */
  message: null,

  /**
   * Create a new instance using the XML response from the service.
   *
   * @param json The JSON data structure to be parsed
   */
  $construct: function( json )
  {
    if ( json )
    {
      var data = eval( "(" + json + ")" );

      this.bytesRead = data.r;
      this.contentLength = data.cl;
      this.percentComplete = data.pc;
      this.transferRate = data.tr;
      this.estimatedTimeLeft = data.tl;
      this.status = data.s;
      var m = data.m;
      if ( m && m != "null" && m != "" ) this.message = m;
    }
  }
});
