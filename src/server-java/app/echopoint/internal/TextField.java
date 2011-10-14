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

/**
 * A base class for text field extensions.
 *
 * @author Rakesh 2009-03-08
 * @version $Id: TextField.java,v 1.4 2011/10/14 10:05:40 perxi Exp $
 */
public abstract class TextField extends nextapp.echo.app.TextField
{
  /** The default text to display in the text field. */
  public static final String PROPERTY_DEFAULT_TEXT = "defaultText";

  /**
   * Returns the default text to display in the component.
   *
   *  @return The {@link #PROPERTY_DEFAULT_TEXT} value.
   */
  public String getDefaultText()
  {
    return (String) get( PROPERTY_DEFAULT_TEXT );
  }

  /**
   * Set the default text value to display in the component.
   *
   * @param defaultText The {@link #PROPERTY_DEFAULT_TEXT} value to set.
   */
  public void setDefaultText( final String defaultText )
  {
    set( PROPERTY_DEFAULT_TEXT, defaultText );
  }
}