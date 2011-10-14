/**
 * Component rendering peer: AbstractChart
 *
 * <p>All chart implementations must implement the {@link #getChartType}
 * abstract method.</p>
 *
 * @author Rakesh 2008-08-08
 * @version: $Id: Sync.AbstractChart.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.google.chart.internal.AbstractChartSync = Core.extend(
    echopoint.internal.AbstractContainerSync,
{
  $abstract: true,

  $static:
  {
    ALT_CONTENT: "Echopoint Chart",
    ALT: "alt",

    // An array of colours that will be sequentially applied to each data
    // set defined in the DATA property.
    COLORS: [ "ff0033", "66ffff", "00ff33", "ffcc00", "ff00ff",
      "3399ff", "996666", "ff3333", "9933ff", "ffff33" ],

    DEFAULT_HEIGHT: "600",
    DEFAULT_WIDTH: "500"
  },

  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.ABSTRACT_CHART, this );
  },

  $virtual:
  {
    /** The encoding matrix used to encode numbers. */
    _simpleEncoding: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",

    /** The container element that is used to render the chart. */
    _chart: null,

    /** A map maintained to cache parsed JSON content. */
    _objectMap: null,

    /**
     * Abstract method that is used to return the chart type value to set.
     * Implementations <b>must</b> implement this method to return the
     * chart type appropriate to it.
     */
    getChartType: function() { throw "getChartType must be implemented"; },

    /**
     * The function used to encode the chart data using simple encoding
     * using sample code from Google.
     *
     * @param data The ChartData model object that represents a data set that
      *  is to be plotted.  The numbers are converted to simple encoded format.
     * @return The encoded string that represents the array of numbers.
     */
    encode: function( data )
    {
      if ( ! data ) return "";

      var chartData = new Array();
      this._encodeXData( data, chartData );
      this._encodeYData( data, chartData );
      this.encodeSize( data, chartData );

      return chartData.join( "" );
    },

    /**
     * Abstract method that encodes the special size array used by scatter
     * plots.
     */
    encodeSize: function( data, chartData ) {},

    /**
     * Return the data array.  Special handling to parse JSON data structure
     * from the server.
     */
    getData: function()
    {
      var key = echopoint.google.chart.internal.AbstractChart.DATA;
      var _data = this._objectMap[key];
      if ( _data ) return _data;

      var value = this.component.get( key );

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
          var chartData = json.list[i];
          var data = new echopoint.google.chart.model.ChartData(
              chartData.xdata, chartData.xmax );

          if ( chartData.ydata.length > 0 ) data.ydata = chartData.ydata;
          var ymax = chartData.ymax;
          if ( ymax ) data.ymax = ymax;

          if ( chartData.size && chartData.size.length > 0 )
          {
            data.size = chartData.size;
          }

          data.color = chartData.color;
          data.legend = chartData.legend;

          var markers = new Array();
          var mks = chartData.markers;
          if ( mks )
          {
            for ( var j = 0; j < mks.length; ++j )
            {
              var marker = new echopoint.google.chart.model.ShapeMarker(
                  mks[j].markerType, mks[j].color, mks[j].size );
              marker.priority = mks[j].priority;
              markers[j] = marker;
            }
          }
          data.markers = markers;

          array[i] = data;
        }

        this._objectMap[key] = array;
      }

      return this._objectMap[key];
    },

    /**
     * Over-ridden to return only the dimension with any units.
     */
    getHeight: function()
    {
      var height = this.component.render( echopoint.internal.AbstractContainer.HEIGHT );
      return ( ( height ) ? this._stripPixels( height ) : this.getDefaultHeight() );
    },

    /**
     * Over-ridden to return only the dimension with any units.
     */
    getWidth: function()
    {
      var width = this.component.render( echopoint.internal.AbstractContainer.WIDTH );
      return ( ( width ) ? this._stripPixels( width ) : this.getDefaultWidth() );
    },

    /**
     * Return the title for the chart.  Special handling to parse JSON data
     * structure from the server.
     */
    getTitle: function()
    {
      var key = echopoint.google.chart.internal.AbstractChart.TITLE;
      var _title = this._objectMap[key];
      if ( _title ) return _title;

      var value = this.component.get( key );
      if ( ! value ) return null;

      if ( value instanceof echopoint.google.chart.model.Title )
      {
        this._objectMap[key] = value;
      }
      else
      {
        var json = eval( "(" + value + ")" );
        var title = new echopoint.google.chart.model.Title();

        for ( var i = 0; i < json.Title.title.length; ++i )
        {
          title.add( json.Title.title[i] );
        }

        this._objectMap[key] = title;
      }

      return this._objectMap[key];
    },

    /**
     * Create the Google Chart API URL to use with this chart.
     *
     * @see #getChartType
     * @see #encode
     * @see #setColors
     * @see #setLegend
     * @see #setLegendPosition
     * @see #setFill
     * @see #setTitle
     * @see #setFont
     * @see #setAltContent
     */
    getUrl: function()
    {
      var data = this.getData();

      var url = "http://chart.apis.google.com/chart?chs=";
      url += this.getWidth() + "x" + this.getHeight();
      url += "&cht=" + this.getChartType();
      url += "&chd=s:";

      for ( var i = 0; i < data.length; ++i )
      {
        if ( i > 0 ) url += ",";
        url += this.encode( data[i] );
      }

      url = this.setColors( url );
      url = this.setFill( url );
      url = this.setTitle( url );
      url = this.setFont( url );
      url = this.setAltContent( url );
      url = this.setAdditionalParameters( url );

      return url;
    },

    /**
     * An optional funtion that can be used to configure additional parameters
     * for the chart.  Sub-classes may implement this method to configure
     * additional properties that are supported by different chart types.
     * The default implementation performs no actions.
     *
     * @param url The url that is to be modified.
     * @return The modified url object.
     */
    setAdditionalParameters: function( url ) { return url; },

    /** Return the alt content to use for the chart and image. */
    getAltContent: function()
    {
      var alt = this.component.get( echopoint.google.chart.internal.AbstractChart.ALT );
      return ( ( alt ) ? alt :
                         echopoint.google.chart.internal.AbstractChartSync.ALT_CONTENT );
    },

    /**
     * Set the <code>alt</code> attribute for the image.
     *
     * @see #getAltContent
     */
    setAltContent: function( url )
    {
      url += "&alt=" + this.getAltContent();
      return url;
    },

    /**
     * Add the colours to set to plot data set.
     *
     * @param The URL that will be updated.
     * @return The modified URL object.
     */
    setColors: function( url )
    {
      var data = this.getData();
      url += "&chco=";
      var index = 0;
      var size = echopoint.google.chart.internal.AbstractChartSync.COLORS.length;

      for ( var i = 0; i < data.length; ++i )
      {
        if ( data[i].color )
        {
          url += data[i].color;
        }
        else
        {
          index = i % size;
          url += echopoint.google.chart.internal.AbstractChartSync.COLORS[index];
        }

        if ( i != data.length - 1 ) url += ",";
      }

      return url;
    },

    /** Set the fill to apply to the chart. */
    setFill: function( url )
    {
      var fill = this.component.render( echopoint.google.chart.internal.AbstractChart.FILL );
      if ( fill ) url += "&chf=" + fill;

      return url;
    },

    /** Set the font style to apply to the chart title. */
    setFont: function( url )
    {
      url += "&chts=";

      var foreground = this.component.render(
          echopoint.internal.AbstractContainer.FOREGROUND );
      if ( foreground && foreground.charAt( 0 ) == '#' )
      {
        foreground = foreground.substr( 1 );
      }
      url += ( foreground ) ? foreground : "000000";

      var font = this.component.render( echopoint.internal.AbstractContainer.FONT );
      if ( font )
      {
        url += "," + font.size;
      }

      return url;
    },

    /** Return the title for the chart. */
    setTitle: function( url )
    {
      var title = this.getTitle();
      if ( title ) url += "&chtt=" + title.getText();
      return url;
    }
  },

  renderAdd: function( update, parentElement )
  {
    this._objectMap = new Array();
    this._chart = document.createElement( "img" );
    this._chart.id = this.component.renderId;
    this.renderStyle( this._chart );

    this._chart.setAttribute( "src", this.getUrl() );
    this._chart.setAttribute( "alt", this.getAltContent() );

    parentElement.appendChild( this._chart );
  },

  renderDispose: function( update )
  {
    this._chart = null;
    this._objectMap = null;
  },

  renderUpdate: function( update )
  {
    var parent = this._chart.parentNode;
    this.renderDispose( update );
    this.renderAdd( update, parent );
  },

  /** Over-ridden to return the value to use. */
  getDefaultHeight: function() { return echopoint.google.chart.internal.AbstractChartSync.DEFAULT_HEIGHT; },

  /** Over-ridden to return the value to use. */
  getDefaultWidth: function() { return echopoint.google.chart.internal.AbstractChartSync.DEFAULT_WIDTH; },

  /** Encode the {@link echopoint.google.chart.model.ChartData#xdata} array of values. */
  _encodeXData: function( data, chartData )
  {
    var xmax = data.getXMax();

    for ( var i = 0; i < data.xdata.length; i++ )
    {
      var currentValue = data.xdata[i];

      if ( !isNaN( currentValue ) && currentValue >= 0 )
      {
        chartData.push( this._simpleEncoding.charAt(
            Math.round( ( this._simpleEncoding.length - 1 ) *
                        currentValue / xmax ) ) );
      }
      else
      {
        chartData.push( "_" );
      }
    }
  },

  /** Encode the {@link echopoint.google.chart.model.ChartData#ydata} array of values. */
  _encodeYData: function( data, chartData )
  {
    if ( data.ydata )
    {
      chartData.push( "," );
      var ymax = data.getYMax();

      for ( var i = 0; i < data.ydata.length; i++ )
      {
        var currentValue = data.ydata[i];

        if ( !isNaN( currentValue ) && currentValue >= 0 )
        {
          chartData.push( this._simpleEncoding.charAt(
              Math.round( ( this._simpleEncoding.length - 1 ) *
                          currentValue / ymax ) ) );
        }
        else
        {
          chartData.push( "_" );
        }
      }
    }
  },

  /** Strip px from extent value. */
  _stripPixels: function( dimension )
  {
    var str = "" + dimension;
    var index = str.indexOf( "px" );
    if ( index != -1 )
    {
      str = str.substring( 0, index );
    }

    return str;
  }
});
