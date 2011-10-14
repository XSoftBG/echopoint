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

import nextapp.echo.app.HttpImageReference;
import nextapp.echo.app.ImageReference;
import echopoint.internal.AbstractImage;

/**
 * The <code>ImageIcon</code> class provides an component
 * that displays an {@link nextapp.echo.app.ImageReference}.  A height and width
 * value can be specified to overide what may be defined in the
 * {@link nextapp.echo.app.ImageReference} itself.  This allows images to be
 * "scaled" to different dimensions.
 *
 * <p>The advantage of <code>ImageIcon</code> over using a {@link
 * nextapp.echo.app.Label} is that you can scale {@link
 * nextapp.echo.app.ImageReference} objects that you may not
 * know the dimensions of, and it can be clicked on like a {@link
 * nextapp.echo.app.Button}.</p>
 *
 * <p>The following code shows sample usage of this component.</p>
 * <pre>
 *   import echopoint.ImageIcon;
 *
 *     ...
 *     final ImageIcon icon = new ImageIcon( "image/imagemap.gif" );
 *     icon.setWidth( 500 );
 *     icon.setHeight( 300 );
 *     icon.setActionCommand( "iconClicked" );
 *     icon.addActionListener( ... );
 *
 *     final Column column = new Columnn();
 *     column.add( icon );
 * </pre>
 *
 * @author Rakesh Vidyadharan 2008-10-20
 * @version $Id: ImageIcon.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class ImageIcon extends AbstractImage
{
  private static final long serialVersionUID = 1l;

  /** Default constructor. */
  public ImageIcon() {}

  /**
   * Create a new instance using the specified image reference.
   *
   * @param icon The icon to associate with this component.
   */
  public ImageIcon( final ImageReference icon )
  {
    setIcon( icon );
  }

  /**
   * Create a new instance using an image from the specified HTTP URI.
   *
   * @param url The URI for the image resource.
   */
  public ImageIcon( final String url )
  {
    this( new HttpImageReference( url ) );
  }

  /**
   * Return the value of {@link #PROPERTY_IMAGE} property.
   *
   * @deprecated Use {@link #getImage} instead.
   * @return The image reference for the icon.
   */
  @Deprecated
  public ImageReference getIcon()
  {
    return getImage();
  }

  /**
   * Set the value of {@link #PROPERTY_IMAGE} property.
   *
   * @deprecated Use {@link #setImage} instead.
   * @param icon The image reference to set.
   */
  @Deprecated
  public void setIcon( final ImageReference icon )
  {
    setImage( icon );
  }
}
