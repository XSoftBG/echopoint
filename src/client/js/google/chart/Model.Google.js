/**
 * Model objects for the various components wrapping Google API objects.
 *
 * @author Rakesh 2008-08-09
 * @version $Id: Model.Google.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */

/**
 * The model object that is used to represent the data to be plotted.
 * Each object contains a mandatory <code>xdata</code> array that contains
 * the array of numbers to be plotted along the x-axis.  An optional
 * <code>ydata</code> array may be specified that contains the corresponding
 * y-axis values.  The <code>xmax</code> is mandatory is used to ensure a
 * gap between the chart boundary and maximum chart data value.  The
 * <code>ymax</code> is optional and should be specified if <code>ydata</code>
 * is specified.  Each chart object takes an array of these model objects
 * allowing plotting multiple data data sequences in the same chart.
 */
echopoint.google.chart.model.ChartData = Core.extend(
{
  /** The mandatory array of numbers to be displayed along the x-axis. */
  xdata: null,

  /** The mandatory maximum value to use for numbers plotted along x-axis. */
  xmax: null,

  /** The optional array of numbers to be displayed along the y-axis. */
  ydata: null,

  /** The maximum value to use for the numbers plotted long y-axie. */
  ymax: null,

  /**
   * The array of sizes for each point represented on the plot.  If specified
   * they size of this array must equal that of xdata and ydata.  This is
   * used only for Scatter Plots.
   */
  size: null,

  /**
   * The colour to apply to the data set.  Default values will be assigned
   * sequentially from the colour set defined in {@link
   * echopoint.google.chart.internal.AbstractChartSync#COLORS}.
   */
  color: null,

  /**
   * The legend text to apply to the data set.  If legend is specified, it
   * must be specified for all instances in the data array.
   */
  legend: null,

  /**
   * The marker styles to use for the points in this data set.  Value is
   * specified as an array of {@link ShapeMarker} objects.  If the array size
   * is 1, then the same marker style is applied to all the data points.
   * If not, then size of the array should equal the size of {@link #xdata}.
   */
  markers: null,

  $construct: function( xvalues, xmaximum )
  {
    this.xdata = xvalues;
    this.xmax = xmaximum;
  },

  /**
   * Return the {@link #xmax} value.  If {@link #xmax} is not defined, return
   * the maximum value from {@link #xdata}.
   */
  getXMax: function()
  {
    if ( this.xmax ) return this.xmax;

    var maxValue = -1;
    for ( var i = 0; i < this.xdata.length; ++i )
    {
      var currentValue = this.xdata[i];
      if ( currentValue > maxValue ) maxValue = currentValue;
    }

    return maxValue;
  },

  /**
   * Return the {@link #ymax} value.  If {@link ymax} is not defined, return
   * the maximum value from {@link #ydata}.
   */
  getYMax: function()
  {
    if ( this.ymax ) return this.ymax;

    var maxValue = -1;
    for ( var i = 0; i < this.ydata.length; ++i )
    {
      var currentValue = this.ydata[i];
      if ( currentValue > maxValue ) maxValue = currentValue;
    }

    return maxValue;
  },

  /**
   * Return the maximum value in the size {@link #array}.
   */
  getSizeMax: function()
  {
    var maxValue = -1;
    for ( var i = 0; i < this.size.length; ++i )
    {
      var currentValue = this.size[i];
      if ( currentValue > maxValue ) maxValue = currentValue;
    }

    return maxValue;
  }
});

/** The model object used to represent the title of a chart. */
echopoint.google.chart.model.Title = Core.extend(
{
  /** An array that is used to hold the lines of text in the title. */
  _title: null,

  /** Create a new instance with a single line text */
  $construct: function( text )
  {
    this._title = new Array();
    if ( text ) this._title.push( text );
  },

  /** Add a line of text to the title. */
  add: function( line )
  {
    this._title.push( line );
  },

  /** Return the title text as represented in this object. */
  getText: function()
  {
    var text =  this._title.join( "|" );
    return text.replace( /\s+/g, "+" );
  },

  /** Return a string representation of this object.  Returns {@link #getText}. */
  toString: function()
  {
    return this.getText();
  }
});

/** The model object used to represent the range of a chart axis (and data). */
echopoint.google.chart.model.Range = Core.extend(
{
  /** The minimum (starting) value of the range. */
  minimum: null,

  /** The maximum (ending) value of the range. */
  maximum: null,

  /** Create a new instance with the values specified. */
  $construct: function( min, max )
  {
    this.minimum = min;
    this.maximum = max;
  }
});

/**
 * A model object used to represent the style for a line drawn.  See
 * <a href='http://code.google.com/apis/chart/#line_styles'>Line styles</a>
 * documentation for specifications.
 */
echopoint.google.chart.model.LineStyle = Core.extend(
{
  /** The thickness of the line to plot. */
  thickness: null,

  /** The size of a line segment.  Set to 1 for solid lines. */
  segmentLength: null,

  /** The length of a blank segment.  Set to 0 for solid lines. */
  blankSegmentLength: null,

  $construct: function( thick, segment, blank )
  {
    this.thickness = ( thick ) ? thick : 1;
    this.segmentLength = ( segment ) ? segment : 1;
    this.blankSegmentLength = ( blank ) ? blank : 0;
  }
});

/**
 * An abstract base class used to represent markers for data points or ranges
 * displayed on graphs.  See
 * <a href='http://code.google.com/apis/chart/#shape_markers'>Shape markers</a>
 * for specifications.
 */
echopoint.google.chart.internal.Marker = Core.extend(
{
  $abstract: true,

  $virtual:
  {
    /**
     * The marker type indicator.  For text (t) markers also specify the
     * value to display using the encoding specified.
     */
    markerType: null,

    /**
     * The colour for the marker.  Colour is expressed in <code>RRGGBB</code>
     * hexadecimal format.
     */
    color: null
  }
});

/**
 * A model object that represents a shape marker for data points on a graph.
 * See <a href='http://code.google.com/apis/chart/#shape_markers2'>Shape
 * markers</a> specifications.
 */
echopoint.google.chart.model.ShapeMarker = Core.extend(
    echopoint.google.chart.internal.Marker,
{
  /** The size of the marker in pixels.  Specify only raw number value. */
  size: null,

  /** The priority for drawing the marker. */
  priority: null,

  $construct: function( type, colour, dimension, order )
  {
    this.markerType = type;
    this.color = colour;
    this.size = dimension;
    this.priority = ( order ) ? order : 0;
  }
});

/**
 * A model object that represents a range marker for the graph.  See
 * <a href='http://code.google.com/apis/chart/#hor_line_marker'>Range
 * marker</a> specifications.
 */
echopoint.google.chart.model.RangeMarker = Core.extend(
    echopoint.google.chart.internal.Marker,
{
  /** The start point for the marked region. Betweem 0.0 and 1.0. */
  startPoint: null,

  /** The end point for the marked region. Betweem 0.0 and 1.0. */
  endPoint: null,

  $construct: function( type, colour, start, end )
  {
    this.markerType = type;
    this.color = colour;
    this.startPoint = start;
    this.endPoint = end;
  }
});

/**
 * A model object used to represent a fill that represents an area between
 * two sets of lines plotted.  See
 * <a href='http://code.google.com/apis/chart/#fill_area_marker'>Fill area</a>
 * specifications.
 */
echopoint.google.chart.model.FillArea = Core.extend(
    echopoint.google.chart.internal.Marker,
{
  /** The index of the starting line from which to fill. */
  startIndex: null,

  /** The index of the ending line till which to fill. */
  endIndex: null,

  $construct: function( type, colour, start, end )
  {
    this.markerType = type;
    this.color = colour;
    this.startIndex = start;
    this.endIndex = end;
  }
});