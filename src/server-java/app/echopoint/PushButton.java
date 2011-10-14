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
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import echopoint.internal.AbstractContainer;

/**
 * A button component that by default appears like the system and browser
 * default.  Style configuration allows it to appear more like a {@link
 * nextapp.echo.app.Button} (although without supporting a background image).
 *
 * <p>The following code shows sample usage of this component.</p>
 * <pre>
 *   import echopoint.PushButton;
 *
 *     ...
 *     final PushButton button = new PushButton( "Push Button" );
 *     button.setToolTipText( "Push button to see effect." );
 *     button.setWidth( 200 );
 *     button.setActionCommand( "buttonClicked" );
 *     button.addActionListener( ... );
 *
 *     final Column column = new Columnn();
 *     column.add( button );
 * </pre>
 *
 * @author Rakesh 2009-02-24
 * @version $Id: PushButton.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class PushButton extends AbstractContainer
{
  private static final long serialVersionUID = 1l;

  /**
   * The property for storing the action command associated with an
   * action event for the image.
   */
  public static final String PROPERTY_ACTION_COMMAND = "actionCommand";

  /** The property for the text displayed in the button. */
  public static final String PROPERTY_TEXT = "text";

  /** The property for the tooltip displayed when hovering over the button. */
  public static final String PROPERTY_TOOL_TIP_TEXT = "toolTipText";

  /** Default constructor.  No special actions needed. */
  public PushButton() {}

  /**
   * Create a new button instance with the specified text.
   *
   * @param text The text to display in the button.
   */
  public PushButton( final String text )
  {
    setText( text );
  }

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
   * Return the value of {@link #PROPERTY_TEXT} property used to display
   * text in the button.
   *
   * @return The action command value.
   */
  public String getText()
  {
    return (String) get( PROPERTY_TEXT );
  }

  /**
   * Set the value of {@link #PROPERTY_TEXT} property.
   *
   * @param command The action command value to set.
   */
  public void setText( final String command )
  {
    set( PROPERTY_TEXT, command );
  }

  /**
   * Return the value of {@link #PROPERTY_TOOL_TIP_TEXT} property used to display
   * text in the button.
   *
   * @return The action command value.
   */
  public String getToolTipText()
  {
    return (String) get( PROPERTY_TOOL_TIP_TEXT );
  }

  /**
   * Set the value of {@link #PROPERTY_TOOL_TIP_TEXT} property.
   *
   * @param command The action command value to set.
   */
  public void setToolTipText( final String command )
  {
    set( PROPERTY_TOOL_TIP_TEXT, command );
  }

  /**
   * Over-ridden to always return {@code null} since this component does not
   * support background images.
   *
   * @return Returns {@code null}.
   */
  @Override
  public ImageReference getBackgroundImage() { return null; }

  /**
   * Over-ridden to not do anything since this component does not support
   * background images.
   *
   * @param backgroundImage The backgroundImage style to apply.
   */
  @Override
  public void setBackgroundImage( final ImageReference backgroundImage )
  {
    // noop
  }

  /**
   * Always returns {@code false} since push button does not allow child
   * components.
   *
   * @param component The component to check.
   * @return Returns {@code false}.
   */
  @Override
  public boolean isValidChild( final Component component )
  {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public void addActionListener( final ActionListener listener )
  {
    super.addActionListener( listener );
  }

  /** {@inheritDoc} */
  @Override
  public void removeActionListener( final ActionListener listener )
  {
    super.removeActionListener( listener );
  }

  /** {@inheritDoc} */
  @Override
  public void processInput( final String inputName, final Object inputValue )
  {
    super.processInput( inputName, inputValue );
    fireActionPerformed( new ActionEvent( this, getActionCommand() ) );
  }
}
