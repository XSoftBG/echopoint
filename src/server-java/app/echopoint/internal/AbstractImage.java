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

package echopoint.internal;

import nextapp.echo.app.HttpImageReference;
import nextapp.echo.app.ImageReference;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import echopoint.model.Cursor;

/**
 * An abstract base class for components that render images.
 *
 * @author Rakesh 2009-12-18
 * @version $Id: AbstractImage.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public abstract class AbstractImage extends AbstractContainer
{
  /**
   * The property for storing the action command associated with an
   * action event for the image.
   */
  public static final String PROPERTY_ACTION_COMMAND = "actionCommand";

  /** The image reference for the component.  This property may be styled. */
  public static final String PROPERTY_IMAGE = "url";

  /** The cursor to display when hovering over the image. */
  public static final String PROPERTY_CURSOR = "cursor";

  /** The alternate text to display is the image cannot be rendered. */
  public static final String PROPERTY_TEXT = "text";

  /** The tool tip text to display is the image cannot be rendered. */
  public static final String PROPERTY_TOOL_TIP_TEXT = "toolTipText";

  /**
   * Return the value of {@link #PROPERTY_ACTION_COMMAND} property.
   *
   * @return The action command value.
   */
  public String getActionCommand()
  {
    return (String) get( PROPERTY_ACTION_COMMAND );
  }

  /**
   * Set the value of {@link #PROPERTY_ACTION_COMMAND} property.
   *
   * @param command The action command value to set.
   */
  public void setActionCommand( final String command )
  {
    set( PROPERTY_ACTION_COMMAND, command );
  }

  /**
   * Return the value of {@link #PROPERTY_IMAGE} property.
   *
   * @return The image reference for the image.
   */
  public ImageReference getImage()
  {
    return (ImageReference) get( PROPERTY_IMAGE );
  }

  /**
   * Set the value of {@link #PROPERTY_IMAGE} property.
   *
   * @param image The image reference to set.
   */
  public void setImage( final ImageReference image )
  {
    set( PROPERTY_IMAGE, image );
  }

  /**
   * Set the value of the {@link #PROPERTY_IMAGE} property.
   *
   * @param url The URL for the image to use as the map region.
   */
  public void setImage( final String url )
  {
    setImage( new HttpImageReference( url ) );
  }

  /**
   * Return the value of {@link #PROPERTY_CURSOR} property.
   *
   * @return The cursor style for the image.
   */
  public Cursor getCursor()
  {
    return (Cursor) get( PROPERTY_CURSOR );
  }

  /**
   * Set the value of {@link #PROPERTY_CURSOR} property.
   *
   * @param cursor The cursor style to set.
   */
  public void setCursor( final Cursor cursor )
  {
    set( PROPERTY_CURSOR, cursor );
  }

  /**
   * Return the value of {@link #PROPERTY_TEXT} property.
   *
   * @return The alternate text for the image.
   */
  public String getText()
  {
    return (String) get( PROPERTY_TEXT );
  }

  /**
   * Set the value of {@link #PROPERTY_TEXT} property.
   *
   * @param text The alternate text to set.
   */
  public void setText( final String text )
  {
    set( PROPERTY_TEXT, text );
  }

  /**
   * Return the value of {@link #PROPERTY_TOOL_TIP_TEXT} property.
   *
   * @return The tool tip text for the image.
   */
  public String getToolTipText()
  {
    return (String) get( PROPERTY_TOOL_TIP_TEXT );
  }

  /**
   * Set the value of {@link #PROPERTY_TOOL_TIP_TEXT} property.
   *
   * @param text The tool tip text to set.
   */
  public void setToolTipText( final String text )
  {
    set( PROPERTY_TOOL_TIP_TEXT, text );
  }

  /**
   * {@inheritDoc}
   * @see #fireActionPerformed()
   */
  @Override
  public void processInput( final String inputName, final Object inputValue )
  {
    super.processInput( inputName, inputValue );
    fireActionPerformed();
  }

  /** {@inheritDoc} */
  @Override
  public void addActionListener( final ActionListener listener )
  {
    super.addActionListener( listener );
  }

  /** Notifies all listeners that have registered for this event type. */
  protected void fireActionPerformed()
  {
    fireActionPerformed( new ActionEvent( this, getActionCommand() ) );
  }

  /** {@inheritDoc} */
  @Override
  public void removeActionListener( final ActionListener listener )
  {
    super.removeActionListener( listener );
  }

  /** {@inheritDoc} */
  public boolean hasActionListeners()
  {
    return super.hasActionListeners();
  }

  /**
   * Over-ridden to be a {@code noop} since images do not support a
   * background image.
   *
   * @param backgroundImage The backgroundImage to apply.
   */
  @Override
  public void setBackgroundImage( final ImageReference backgroundImage )
  {
    // noop
  }
}
