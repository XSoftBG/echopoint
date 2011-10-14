package echopoint.google.chart.internal;

import java.io.Serializable;

/**
 * An abstract base class used to represent markers for data points or ranges
 * displayed on graphs.  See
 * <a href='http://code.google.com/apis/chart/#shape_markers'>Shape markers</a>
 * for specifications.
 *
 * @author Rakesh Vidyadharan 2008-08-10
 * @version $Id: Marker.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public abstract class Marker implements Serializable
{
  private static final long serialVersionUID = 1l;

  /**
   * The marker type indicator.  For text (t) markers also specify the
   * value to display using the encoding specified.
   */
  protected String markerType;

  /**
   * The colour for the marker.  Colour is expressed in <code>RRGGBB</code>
   * hexadecimal format.
   */
  protected String color;

  /**
   * Accessor for property 'markerType'.
   *
   * @return Value for property 'markerType'.
   */
  public String getMarkerType()
  {
    return markerType;
  }

  /**
   * Accessor for property 'color'.
   *
   * @return Value for property 'color'.
   */
  public String getColor()
  {
    return color;
  }
}
