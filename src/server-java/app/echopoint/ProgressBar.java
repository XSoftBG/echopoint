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

import nextapp.echo.app.Color;
import echopoint.internal.AbstractContainer;

/**
 * A simple colour based progress bar component.  Unlike the original EPNG
 * progress bar component, this is very light-weight and does not require
 * compositing images on the server.
 *
 * <p><b>Note:</b> Development of this component was sponsored by <a
 * href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author Rakesh Vidyadharan 2008-11-6
 * @version $Id: ProgressBar.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class ProgressBar extends AbstractContainer
{
  private static final long serialVersionUID = 1l;

  /**
   * The property used to set the background colour of the variable width
   * progress bar element.
   */
  public static final String PROPERTY_BAR_BACKGROUND = "barBackground";

  /** The property used to indicate the progress percentage value. */
  public static final String PROPERTY_PERCENTAGE = "percentage";

  /** The property used to display an optional text value in the bar. */
  public static final String PROPERTY_TEXT = "text";

  /**
   * Return the value of {@link #PROPERTY_BAR_BACKGROUND} property.
   *
   * @return The background colour for the variable width bar.
   */
  public Color getBarBackground()
  {
    return (Color) get( PROPERTY_BAR_BACKGROUND );
  }

  /**
   * Set the value of the {@link #PROPERTY_BAR_BACKGROUND} property.
   *
   * @param background The background colour for the variable width bar.
   */
  public void setBarBackground( final Color background )
  {
    set( PROPERTY_BAR_BACKGROUND, background );
  }

  /**
   * Return the value of {@link #PROPERTY_PERCENTAGE} property.
   *
   * @return The percentage complete to be represented.
   */
  public int getPercentage()
  {
    return (Integer) get( PROPERTY_PERCENTAGE );
  }

  /**
   * Set the value of the {@link #PROPERTY_PERCENTAGE} property.
   *
   * @param percentage The percentage complete that is to be depicted.
   */
  public void setPercentage( final int percentage )
  {
    set( PROPERTY_PERCENTAGE, percentage );
  }

  /**
   * Return the value of {@link #PROPERTY_TEXT} property.
   *
   * @return The optional text to be displayed in the progress bar.
   */
  public String getText()
  {
    return (String) get( PROPERTY_TEXT );
  }

  /**
   * Set the value of the {@link #PROPERTY_TEXT} property.
   *
   * @param text The optional text to be displayed in the progress bar.
   */
  public void setText( final String text )
  {
    set( PROPERTY_TEXT, text );
  }
}
