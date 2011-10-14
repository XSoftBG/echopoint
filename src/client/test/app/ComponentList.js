/**
 * A component used to display a list of control components used to display
 * the echopoint.Xxx component being tested.  Add components to be tested that
 * have an associated echopoint.test.&lt;ComponentName&gt;Test class to the
 * {@link COMPONENTS} array.
 *
 * @author Rakesh 2008-06-26
 * @version $Id: ComponentList.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.ComponentList = Core.extend( Echo.Column,
{
  $static:
  {
    COMPONENTS: new Array(
        "Anchor",
        "BarChart",
        "BorderLayout",
        "Clock",
        "DirectHtml",
        //"FileUpload",
        "HtmlLabel",
        "HtmlLayout",
        "HttpPane",
        "ImageIcon",
        "ImageMap",
        "InfoWindow",
        //"KeystrokeTextField",
        "LightBox",
        "LineChart",
        "Map",
        "Meter",
        "NumberTextField",
        "PieChart",
        "ProgressBar",
        "PushButton",
        "QRCode",
        "RadarChart",
        "RegexTextField",
        "ScatterPlot",
        "Sparkline",
        "Strut",
        "TagCloud",
        "TextArea",
        "TooltipContainer",
        "VennDiagram"
      )
  },

  $construct: function()
  {
    Echo.Column.call( this );

    for ( var i = 0; i < echopoint.test.ComponentList.COMPONENTS.length; ++i )
    {
      this.add( this._createButton(
          echopoint.test.ComponentList.COMPONENTS[i] ) );
    }
  },

  /**
   * Create the control used to display the component being tested.
   *
   * @param component The class name (not fully qualified) of the component
   *   to be tested.
   */
  _createButton: function( component )
  {
    return new echopoint.test.Button( component );
  }
});