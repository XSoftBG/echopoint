package echopoint.google.chart.model;

import java.io.Serializable;

/**
 * A simple model object that may be used to represent the parameters that
 * control bar chart size.  Use of this model object is entirely optional,
 * since the {@link echopoint.google.chart.BarChart#setSize(String)} method may
 * be used directly using the string value as required by Google API.
 *
 * @author Rakesh 2008-08-20
 * @version $Id: BarChartSize.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class BarChartSize implements Serializable
{
  private static final long serialVersionUID = 1l;

  /** The width in pixels of a bar in the chart. */
  private final int width;

  /** The optional space in pixels between bars in a group. */
  private int groupSpace = -1;

  /** The optional space in pixels between groups. */
  private int space = -1;

  /**
   * Create a new instance using the specified width.
   *
   * @param width The width of a bar in the chart.
   */
  public BarChartSize( final int width )
  {
    this.width = width;
  }

  /**
   * Create a new instance using the specified width and intra-group spacing.
   *
   * @param width The width of a bar in the chart.
   * @param groupSpace The intra-group spacing for the chart.
   */
  public BarChartSize( final int width, final int groupSpace )
  {
    this( width );
    this.groupSpace = groupSpace;
  }

  /**
   * Create a new instance using the specified parameters.
   *
   * @param width The width of a bar in the chart.
   * @param groupSpace The intra-group spacing for the chart.
   * @param space The space between groups in the chart.
   */
  public BarChartSize( final int width, final int groupSpace, final int space )
  {
    this( width, groupSpace );
    this.space = space;
  }

  /**
   * Return a string representation of the data encapsulated in this object.
   * The string value corresponds to the encoding used to represent this
   * value in the Google API.
   *
   * @return The string representation of this object.
   */
  @Override
  public String toString()
  {
    final StringBuilder builder = new StringBuilder( 16 );
    builder.append( width );

    if ( groupSpace != -1 )
    {
      builder.append( "," ).append( groupSpace );
    }

    if ( space != -1 )
    {
      builder.append( "," ).append( space );
    }

    return builder.toString();
  }

  /**
   * Accessor for property 'width'.
   *
   * @return Value for property 'width'.
   */
  public int getWidth()
  {
    return width;
  }

  /**
   * Accessor for property 'groupSpace'.
   *
   * @return Value for property 'groupSpace'.
   */
  public int getGroupSpace()
  {
    return groupSpace;
  }

  /**
   * Accessor for property 'space'.
   *
   * @return Value for property 'space'.
   */
  public int getSpace()
  {
    return space;
  }
}
