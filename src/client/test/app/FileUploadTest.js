/**
 * A test class for the echopoint.tucana.FileUploadSelector client-side component.
 *
 * @author Rakesh 2008-11-02
 * @version $Id: FileUploadTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.FileUploadTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createFileUpload() );
  },

  _createFileUpload: function()
  {
    return new echopoint.tucana.FileUploadSelector(
    {
      renderId: "echopointUnitTestFileUpload",
      buttonTextUpload: "Upload",
      width: "200px",
      background: "#a1a1a1",
      inputSize: "8"
    } );
  }
} );
