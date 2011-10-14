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

/**
 * A component that represents a HTML anchor tag.  This component makes it
 * easier to create regular HTML links without having to configure a {@link
 * nextapp.echo.app.Button} component and an associated action listener.  You
 * can create a raw anchor tag using {@link echopoint.internal.AbstractHtmlComponent}
 * implementations, but may find configuring styles not as convenient.
 *
 * <p><b>Note:</b> Since anchor tags cannot be fully styled using in-line
 * styles, this component does not offer the ability to configure styles for
 * hover, active or visited.</p>
 *
 * <p>The following shows sample usage of this component:</p>
 * <pre>
 *   import echopoint.Anchor;
 *   import nextapp.echo.app.Color;
 *   import nextapp.echo.app.Extent;
 *   import nextapp.echo.app.Font;
 *
 *     ...
 *     final Anchor anchor = new Anchor();
 *     anchor.setUri( "https://echopoint.dev.java.net/" );
 *     anchor.setColor( new Color( 0x2f2f4f );
 *     anchor.setFont( new Font( Font.HELVETICA, Font.BOLD, new Extent( 10 ) ) );
 *
 *     container.add( anchor );
 * </pre>
 *
 * @author Rakesh 2008-10-23
 * @version $Id: Anchor.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class Anchor extends Component
{
  private static final long serialVersionUID = 1l;

  /** The options for specifying the {@link Anchor#PROPERTY_TARGET} property. */
  public enum Target { _blank, _parent, _self, _top }

  /** The target for the anchor tag.  Use to control target window/frame. */
  public static final String PROPERTY_TARGET = "target";

  /** The text that is to be hyper-linked. */
  public static final String PROPERTY_TEXT = "text";

  /** The tooltip (title) for the anchor tag. */
  public static final String PROPERTY_TOOL_TIP_TEXT = "toolTipText";

  /** The destination URI to which the anchor tag points. */
  public static final String PROPERTY_URI = "uri";

  /**
   * Return the target attribute for the anchor tag.
   *
   * @return The target value.
   */
  public String getTarget()
  {
    return (String) get( PROPERTY_TARGET );
  }

  /**
   * Set the value for the target attribute to be applied to the anchor tag.
   *
   * @see #setTarget(echopoint.Anchor.Target)
   * @param target The value to set.
   */
  public void setTarget( final String target )
  {
    set( PROPERTY_TARGET, target );
  }

  /**
   * Set the value for the target attribute to be applied to the anchor tag.
   * Use this method to specify standard target values.
   *
   * @param target The value to set.
   */
  public void setTarget( final Target target )
  {
    set( PROPERTY_TARGET, target.toString() );
  }

  /**
   * Return the text that is to be hyper-linked.
   *
   * @return The text that is to be hyper-linked.
   */
  public String getText()
  {
    return (String) get( PROPERTY_TEXT );
  }

  /**
   * Set the value of the text that is to be displayed as hyper-linked.
   *
   * @param text The value to set.
   */
  public void setText( final String text )
  {
    set( PROPERTY_TEXT, text );
  }

  /**
   * Return the tool tip text displayed for the anchor tag.
   *
   * @return The tool tip text that is to be displayed.
   */
  public String getToolTipText()
  {
    return (String) get( PROPERTY_TOOL_TIP_TEXT );
  }

  /**
   * Set the value of the tool tip text that is to be displayed.
   *
   * @param toolTipText The value to set.
   */
  public void setToolTipText( final String toolTipText )
  {
    set( PROPERTY_TOOL_TIP_TEXT, toolTipText );
  }

  /**
   * Return the URI to which the anchor tag points.
   *
   * @return The uri value.
   */
  public String getUri()
  {
    return (String) get( PROPERTY_URI );
  }

  /**
   * Set the value of the URI to which the the anchor tag points.
   *
   * @param uri The value to set.
   */
  public void setUri( final String uri )
  {
    set( PROPERTY_URI, uri );
  }
}
