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

package echopoint.style.echo.extras;

import static echopoint.style.echo.extras.ResourceImages.MenuBarBackground;
import static echopoint.style.echo.extras.ResourceImages.MenuBarMenuBackground;
import static echopoint.style.echo.extras.ResourceImages.MenuBarSelectionBackground;
import static echopoint.util.ColorKit.makeColor;
import static nextapp.echo.app.Component.PROPERTY_FOREGROUND;
import static nextapp.echo.extras.app.MenuBarPane.PROPERTY_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.MenuBarPane.PROPERTY_BORDER;
import static nextapp.echo.extras.app.MenuBarPane.PROPERTY_MENU_BACKGROUND;
import static nextapp.echo.extras.app.MenuBarPane.PROPERTY_MENU_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.MenuBarPane.PROPERTY_MENU_BORDER;
import static nextapp.echo.extras.app.MenuBarPane.PROPERTY_MENU_OPACITY;
import static nextapp.echo.extras.app.MenuBarPane.PROPERTY_SELECTION_BACKGROUND;
import static nextapp.echo.extras.app.MenuBarPane.PROPERTY_SELECTION_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.MenuBarPane.PROPERTY_SELECTION_FOREGROUND;
import static nextapp.echo.extras.app.menu.AbstractMenuComponent.PROPERTY_ANIMATION_TIME;
import nextapp.echo.app.Border;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import echopoint.style.AbstractStyle;

/**
 * The default style to apply for {@link nextapp.echo.extras.app.MenuBarPane}
 * components.  Sub-classes should over-ride either the root {@link #init}
 * method, or the various property group configuration methods.
 *
 * @author Rakesh Vidyadharan 2009-05-26
 * @version $Id: MenuBarPaneStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class MenuBarPaneStyle extends AbstractStyle
{
  private static final long serialVersionUID = 1L;

  /**
   * The default menu animation time.
   *
   * {@value}
   */
  public static final int ANIMATION_TIME = 150;

  /** {@inheritDoc} */
  @Override
  protected void init()
  {
    super.init();

    setGeneralStyles();
    setMenuStyles();
    setSelectionStyles();
  }

  /** Set general styles (base component styles) and animation time. */
  protected void setGeneralStyles()
  {
    set( PROPERTY_ANIMATION_TIME, ANIMATION_TIME );
    set( PROPERTY_BACKGROUND_IMAGE, new FillImage( MenuBarBackground ) );
    set( PROPERTY_BORDER,
        new Border( 0, makeColor( "#000000" ), Border.STYLE_SOLID ) );
    set( PROPERTY_FOREGROUND, makeColor( "#ffffff" ) );
  }

  /** Set styles for the menu in the menu bar component. */
  protected void setMenuStyles()
  {
    set( PROPERTY_MENU_BACKGROUND, makeColor( "#000000" ) );
    set( PROPERTY_MENU_BACKGROUND_IMAGE,
        new FillImage( MenuBarMenuBackground ) );
    set( PROPERTY_MENU_OPACITY, 92 );

    final Border.Side[] sides = new Border.Side[4];
    sides[Border.SIDE_TOP] = new Border.Side(
        1, makeColor( "#1f1f1f" ), Border.STYLE_SOLID );
    sides[Border.SIDE_RIGHT] = sides[Border.SIDE_TOP];
    sides[Border.SIDE_BOTTOM] = new Border.Side(
        1, makeColor( "#3f3f3f" ), Border.STYLE_SOLID );
    sides[Border.SIDE_LEFT] = sides[Border.SIDE_BOTTOM];

    set( PROPERTY_MENU_BORDER, new Border( sides ) );
  }

  /** Set styles for selection of menu compnents. */
  protected void setSelectionStyles()
  {
    set( PROPERTY_SELECTION_BACKGROUND, makeColor( "#fffac1" ) );

    final Extent extent = new Extent( 50, Extent.PERCENT );
    set( PROPERTY_SELECTION_BACKGROUND_IMAGE, new FillImage(
        MenuBarSelectionBackground, extent, extent, FillImage.REPEAT ) );

    set( PROPERTY_SELECTION_FOREGROUND, makeColor( "#000000" ) );
  }
}
