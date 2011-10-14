/*
 * This file is part of the Echo Point Project.  This project is a
 * collection of Components that have extended the Echo Web Application
 * Framework Version 3.
 *
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */

package echopoint;

import nextapp.echo.app.Component;
import nextapp.echo.app.ImageReference;
import nextapp.echo.app.Pane;
import nextapp.echo.app.PaneContainer;

/**
 * The {@code LightBox} is a component that covers all visible content
 * with a translucent image. This can be used to give a visual clue to the
 * user that content cannot be interacted with.  {@code LightBox} supports
 * child components that may be used to present visually modal content to
 * the user.  {@code LightBox} components are designed to be added as the
 * only child of {@link nextapp.echo.app.WindowPane} or {@link
 * nextapp.echo.app.SplitPane} components.
 *
 * @author Brad Baker, Rakesh 2009-03-06
 * @version $Id: LightBox.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class LightBox extends Component implements Pane
{
  private static final long serialVersionUID = 1L;

  /** The property for the image to use as the translucent overlay. */
  public static final String PROPERTY_TRANSLUCENT_IMAGE = "translucentImage";

  /** The property used to toggle the display status of the light box. */
  public static final String PROPERTY_HIDDEN = "hidden";

  /**
   * The property used to indicate whether the light box should cover the
   * entire browser window or just the pane (windowpane, split pane...).
   */
  public static final String PROPERTY_PARENT_ONLY = "parentOnly";

  /**
   * Return the image that is used as the overlay to hide content.
   *
   * @return The overlay image.
   */
  public ImageReference getTranslucentImage()
  {
    return (ImageReference) get( PROPERTY_TRANSLUCENT_IMAGE );
  }

  /**
   * Sets the image to be used as the background of the light box.
   * This image should be a translucent image such as a PNG and it
   * will be used to cover any current content on the client when the
   * lightbox is shown.
   *
   * @param image The image to use as the over lay.
   */
  public void setTranslucentImage( final ImageReference image )
  {
    set( PROPERTY_TRANSLUCENT_IMAGE, image );
  }

  /**
   * Return the visiable status of the light box.
   *
   * @return Return {@code true} if the light box will be visible when
   *   added to the component hierarchy.
   */
  public boolean getHidden()
  {
    return (Boolean) get( PROPERTY_HIDDEN );
  }

  /**
   * Set the visible status of the light box.  Use in event listeners to
   * display or hide the light box.  The usual action is to hide the light
   * box after some processing, but may also be used to re-display a
   * previously hidden light box (reuse the same light box).
   *
   * @param hidden The visible status indicator to set.
   */
  public void setHidden( final boolean hidden )
  {
    set( PROPERTY_HIDDEN, hidden );
  }

  /**
   * Return the indicator that specifies whether the light box will cover
   * the entire browser window or just its container pane.
   *
   * @return Return {@code true} if only the container pane is covered.
   */
  public boolean getParentOnly()
  {
    return (Boolean) get( PROPERTY_PARENT_ONLY );
  }

  /**
   * Set the indicator that specified whether the light box covers the
   * entire browser window or just its parent container pane.
   *
   * @param parentOnly The indicator to set.
   */
  public void setParentOnly( final boolean parentOnly )
  {
    set( PROPERTY_PARENT_ONLY, parentOnly );
  }

  /**
   * Over-ridden to allow only {@link nextapp.echo.app.PaneContainer}s as
   * parents.
   *
   * @param component The component to test.
   * @return Return {@code true} if the component is a pane container.
   */
  @Override
  public boolean isValidParent( final Component component )
  {
    return ( component instanceof PaneContainer );
  }
}
