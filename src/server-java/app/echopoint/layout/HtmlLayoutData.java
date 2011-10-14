package echopoint.layout;

import nextapp.echo.app.LayoutData;

/**
 * A layout data implementation for child components that are added to the
 * {@link echopoint.HtmlLayout} layout container.
 *
 * @author Simon Lei 2009-03-16
 * @version $Id: HtmlLayoutData.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 * @since 3.0.0a11
 */
public class HtmlLayoutData implements LayoutData
{
  private static final long serialVersionUID = 1l;

  /** The DOM id for the container to which the child component is being added. */
  private String containerId;

  /**
   * Create a new layout data instance using the specified DOM id.
   *
   * @param containerId The DOM node id for the container.  Note that an
   *   element with the specified id must exist in the layout HTML code.
   */
  public HtmlLayoutData( String containerId )
  {
    this.setContainerId( containerId );
  }

  /**
   * Return the DOM node id for the parent element of the component to which
   * this layout applies.
   *
   * @return The parent node id.
   */
  public String getContainerId()
  {
    return containerId;
  }

  /**
   * Set the DOM node id for the parent element of the component to which
   * this layout applies.
   *
   * @param containerId The parent node id to use.
   */
  public void setContainerId( String containerId )
  {
    this.containerId = containerId;
  }
}
