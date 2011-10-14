/**
 * Component rendering peer: AdvancedChart
 *
 * @author Rakesh 2008-08-18
 * @version: $Id: Sync.AdvancedChart.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.google.chart.internal.AdvancedChartSync = Core.extend(
    echopoint.google.chart.internal.SimpleChartSync,
{
  $abstract: true,

  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.ADVANCED_CHART, this );
  },

  $virtual:
  {
    /**
     * Return the axis labels for the chart.  Special handling to parse JSON
     * data structure from the server.
     *
     * @see #_processArrays
     */
    getAxisLabels: function()
    {
      return this._processArrays( echopoint.google.chart.internal.AdvancedChart.AXIS_LABELS );
    },

    /**
     * Return the label positions for the chart.  Special handling to parse JSON
     * data structure from the server.
     *
     * @see #_processArrays
     */
    getLabelPositions: function()
    {
      return this._processArrays( echopoint.google.chart.internal.AdvancedChart.LABEL_POSITIONS );
    },

    /**
     * Return the axis ranges for the chart.  Special handling to parse JSON
     * data structure from the server.
     */
    getAxisRanges: function()
    {
      var key = echopoint.google.chart.internal.AdvancedChart.AXIS_RANGES;
      var _ranges = this._objectMap[key];
      if ( _ranges ) return _ranges;

      var value = this.component.get( key );
      if ( ! value ) return null;

      if ( value instanceof Array )
      {
        this._objectMap[key] = value;
      }
      else
      {
        var json = eval( "(" + value + ")" );
        var array = new Array();

        for ( var i = 0; i < json.list.length; ++i )
        {
          array[i] = new echopoint.google.chart.model.Range(
              json.list[i].minimum, json.list[i].maximum );
        }

        this._objectMap[key] = array;
      }

      return this._objectMap[key];
    },

    /**
     * Return the line styles for the chart.  Special handling to parse JSON
     * data structure from the server.
     */
    getLineStyles: function()
    {
      var key = echopoint.google.chart.internal.AdvancedChart.LINE_STYLES;
      var _styles = this._objectMap[key];
      if ( _styles ) return _styles;

      var value = this.component.get( key );
      if ( ! value ) return null;

      if ( value instanceof Array )
      {
        this._objectMap[key] = value;
      }
      else
      {
        var json = eval( "(" + value + ")" );
        var array = new Array();

        for ( var i = 0; i < json.list.length; ++i )
        {
          array[i] = new echopoint.google.chart.model.LineStyle(
              json.list[i].thickness, json.list[i].segmentLength,
              json.list[i].blankSegmentLength );
        }

        this._objectMap[key] = array;
      }

      return this._objectMap[key];
    },

    /**
     * Return the range markers for the chart.  Special handling to parse JSON
     * data structure from the server.
     */
    getRangeMarkers: function()
    {
      var key = echopoint.google.chart.internal.AdvancedChart.RANGE_MARKERS;
      var _markers = this._objectMap[key];
      if ( _markers ) return _markers;

      var value = this.component.get( key );
      if ( ! value ) return null;

      if ( value instanceof Array )
      {
        this._objectMap[key] = value;
      }
      else
      {
        var json = eval( "(" + value + ")" );
        var array = new Array();

        for ( var i = 0; i < json.list.length; ++i )
        {
          array[0] = new echopoint.google.chart.model.RangeMarker(
              json.list[i].markerType, json.list[i].color,
              json.list[i].startPoint, json.list[i].endPoint );
        }

        this._objectMap[key] = array;
      }

      return this._objectMap[key];
    },

    /**
     * Return the fill areas for the chart.  Special handling to parse JSON
     * data structure from the server.
     */
    getFillArea: function()
    {
      var key = echopoint.google.chart.internal.AdvancedChart.FILL_AREA;
      var _fill = this._objectMap[key];
      if ( _fill ) return _fill;

      var value = this.component.get( key );
      if ( ! value ) return null;

      if ( value instanceof Array )
      {
        this._objectMap[key] = value;
      }
      else
      {
        var json = eval( "(" + value + ")" );
        var array = new Array();

        for ( var i = 0; i < json.list.length; ++i )
        {
          array[0] = new echopoint.google.chart.model.FillArea(
              json.list[i].markerType, json.list[i].color,
              json.list[i].startIndex, json.list[i].endIndex );
        }

        this._objectMap[key] = array;
      }

      return this._objectMap[key];
    },

    /**
     * Over-ridden to set the axis labels.
     *
     * @see #setAxisType
     * @see #setLabels
     * @see #setLabelPositions
     * @see #setLegend
     * @see #setLegendPositions
     * @see #setAxisRange
     * @see #setAxisStyle
     * @see #setLineStyles
     * @see #setGridLines
     * @see #setMarkers
     * @param url The URL that is to be updated with axis label information.
     * @return The modified URL object.
     */
    setAdditionalParameters: function( url )
    {
      url = this.setAxisType( url );
      url = this.setLabels( url );
      url = this.setLabelPositions( url );
      url = this.setLegend( url );
      url = this.setLegendPosition( url );
      url = this.setAxisRange( url );
      url = this.setAxisStyle( url );
      url = this.setLineStyles( url );
      url = this.setGridLines( url );
      url = this.setMarkers( url );
      return url;
    },

    /** Set the axis type style property for the chart. */
    setAxisType: function( url )
    {
      var type = this.component.render( echopoint.google.chart.internal.AdvancedChart.AXIS_TYPE );
      if ( type ) url += "&chxt=" + type;
      return url;
    },

    /** Set the axis labels for the chart from an array of string arrays. */
    setLabels: function( url )
    {
      var array = this.getAxisLabels();
      if ( ! array ) return url;

      url += "&chxl=";
      var str = "&chxl=";

      for ( var i = 0; i < array.length; ++i )
      {
        str += i + ":|";
        var labels = array[i];

        for ( var j = 0; j < labels.length; ++j )
        {
          str += labels[j] + "|";
        }
      }

      url += str.substring( 0, str.length - 1 );
      return url;
    },

    /** Sets the axis label positions to allow non-uniform spacing. */
    setLabelPositions: function( url )
    {
      var array = this.getLabelPositions();
      if ( ! array ) return url;

      url += "&chxp=";

      for ( var i = 0; i < array.length; ++i )
      {
        var positions = array[i];
        if ( positions.length > 0 ) url += i + ",";

        for ( var j = 0; j < positions.length; ++j )
        {
          url += positions[j];

          if ( j < positions.length - 1 )
          {
            url += ",";
          }
          else
          {
            if ( i < array.length - 1 ) url += "|";
          }
        }
      }

      return this._trimPipes( url );
    },

    /** Sets the axis label positions to allow non-uniform spacing. */
    setAxisRange: function( url )
    {
      var array = this.getAxisRanges();
      if ( ! array ) return url;

      url += "&chxr=";

      for ( var i = 0; i < array.length; ++i )
      {
        var range = array[i];
        if ( range )
        {
          url += i + ",";
          url += range.minimum + "," + range.maximum + "|";
        }
      }

      return this._trimPipes( url );
    },

    /** Sets the styles for the axis labels. */
    setAxisStyle: function( url )
    {
      var style = this.component.render( echopoint.google.chart.internal.AdvancedChart.AXIS_STYLES );
      if ( style ) url += "&chxs=" + style;

      return url;
    },

    /** Set the line styles for the lines plotted. */
    setLineStyles: function( url )
    {
      var array = this.getLineStyles();
      if ( array )
      {
        url += "&chls=";

        for ( var i = 0; i < array.length; ++i )
        {
          var style = array[i];
          url += style.thickness + "," + style.segmentLength + "," +
                 style.blankSegmentLength + "|";
        }
      }

      return this._trimPipes( url );
    },

    /** Sets the grid lines for the graph. */
    setGridLines: function( url )
    {
      var style = this.component.render( echopoint.google.chart.internal.AdvancedChart.GRID_LINES );
      if ( style ) url += "&chg=" + style;

      return url;
    },

    /**
     * Set the shape and range markers for the data points being plotted.
     *
     * @see #setShapeMarkers
     * @see #setRangeMarkers
     */
    setMarkers: function( url )
    {
      var data = this.getData();
      var rangeMarkers = this.getRangeMarkers();
      var fillArea = this.getFillArea();

      var hasMarkers = false;
      if ( rangeMarkers ) hasMarkers = true;
      if ( fillArea ) hasMarkers = true;
      var i;

      if ( ! hasMarkers )
      {
        for ( i = 0; i < data.length; ++i )
        {
          if ( data[i].markers ) hasMarkers = true;
        }
      }

      if ( ! hasMarkers ) return url;

      url += "&chm=";

      for ( i = 0; i < data.length; ++i )
      {
        url = this.setShapeMarkers( url, data[i], i );
      }

      url = this.setRangeMarkers( url, rangeMarkers );
      url = this.setFillArea( url, fillArea );
      return this._trimPipes( url );
    },

    /** Set the shape markers for the data set. */
    setShapeMarkers: function( url, data, index )
    {
      var markers = data.markers;
      var marker;

      if ( markers )
      {
        if ( markers.length == 1 )
        {
          marker = markers[0];
          url += marker.markerType + "," + marker.color + "," + index + ",-1," +
                 marker.size + "," + marker.priority + "|";
        }
        else
        {
          for ( var i = 0; i < markers.length; ++i )
          {
            marker = markers[i];

            if ( marker )
            {
              url += marker.markerType + "," + marker.color + "," + index + "," +
                     i + "," + marker.size + "," + marker.priority + "|";
            }
          }
        }
      }

      return url;
    },

    /** Set the range markers for the data set. */
    setRangeMarkers: function( url, markers )
    {
      if ( markers )
      {
        for ( var i = 0; i < markers.length; ++i )
        {
          var marker = markers[i];
          url += marker.markerType + "," + marker.color + ",0," +
                 marker.startPoint + "," + marker.endPoint + "|";
        }
      }

      return url;
    },

    /** Set the fill areas between plotted line(s). */
    setFillArea: function( url, array )
    {
      if ( array )
      {
        for ( var i = 0; i < array.length; ++i )
        {
          var fillArea = array[i];
          url += fillArea.markerType + "," + fillArea.color + "," +
                 fillArea.startIndex + "," + fillArea.endIndex + ",0|";
        }
      }

      return url;
    }
  },

  /**
   * Common method for processing data types that are array of array of
   * simple types.
   *
   * @param key The key to use to fetch the property.
   */
  _processArrays: function( key )
  {
    var _labels = this._objectMap[key];
    if ( _labels ) return _labels;

    var value = this.component.get( key );
    if ( ! value ) return null;

    if ( value instanceof Array )
    {
      this._objectMap[key] = value;
    }
    else
    {
      var json = eval( "(" + value + ")" );
      var array = new Array();

      for ( var i = 0; i < json.list.length; ++i )
      {
        var labels = new Array();
        var jsonLabels = json.list[i];

        for ( var j = 0; j < jsonLabels.length; ++j )
        {
          labels[j] = jsonLabels[j];
        }

        array[i] = labels;
      }

      this._objectMap[key] = array;
    }

    return this._objectMap[key];
  },

  /** Trim all trailing pipe characters from the input string */
  _trimPipes: function( url )
  {
    return url.replace( /\|+$/g, "" );
  }
});
