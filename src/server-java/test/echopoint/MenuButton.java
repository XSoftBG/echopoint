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

import nextapp.echo.app.Border;
import nextapp.echo.app.Color;

/**
 * A custom styled button used to display the controls for testing the
 * various echopoint components.
 *
 * @author Rakesh 2008-06-24
 * @version $Id: MenuButton.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class MenuButton extends nextapp.echo.app.Button
{
  private static final long serialVersionUID = 1l;

  /**
   * Create a new instance of the button with the specified title.  Note
   * that the title should match the name of that class to be tested.
   *
   * @param componentName The name of the component to test.
   */
  public MenuButton( final String componentName )
  {
    super( componentName );
    setRenderId( "echopointUnitTestButton" + componentName );
  }

  /**
   * Life-cycle method invoked when the component is added to the hierarchy.
   * Sets the stylistic features for the button.
   */
  @Override
  public void init()
  {
    super.init();

    final Color color = Color.BLACK;
    final Border border = new Border( 1, color, Border.STYLE_OUTSET );
    setBorder( border );

    final Border pressed = new Border( 1, color, Border.STYLE_INSET );
    setPressedBorder( pressed );
    addActionListener( new TestListener() );
  }
}
