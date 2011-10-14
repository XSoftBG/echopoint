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

import static echopoint.style.echo.extras.ResourceImages.BlueGrey;
import static echopoint.style.echo.extras.ResourceImages.LightBlueLine;
import static echopoint.util.ColorKit.makeColor;
import static nextapp.echo.app.Border.STYLE_SOLID;
import static nextapp.echo.extras.app.ContextMenu.PROPERTY_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.ContextMenu.PROPERTY_BORDER;
import static nextapp.echo.extras.app.ContextMenu.PROPERTY_DISABLED_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.ContextMenu.PROPERTY_SELECTION_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.menu.AbstractMenuComponent.PROPERTY_ANIMATION_TIME;
import nextapp.echo.app.Border;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.Border.Side;
import echopoint.style.AbstractStyle;

/**
 * The default style to apply to {@link nextapp.echo.extras.app.ContextMenu}
 * components.
 *
 * @author Rakesh Vidyadharan 2009-08-23
 * @version $Id: ContextMenuStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class ContextMenuStyle extends AbstractStyle
{
  private static final long serialVersionUID = 1L;

  @Override
  protected void init()
  {
    super.init();

    set( PROPERTY_ANIMATION_TIME, 350 );
    set( PROPERTY_BACKGROUND_IMAGE, new FillImage( LightBlueLine ) );

    final Side[] sides = new Side[]
        {
            new Side( 1, makeColor( "#dfdfef" ), STYLE_SOLID ),
            new Side( 1, makeColor( "#dfdfef" ), STYLE_SOLID ),
            new Side( 1, makeColor( "#7f7f8f" ), STYLE_SOLID ),
            new Side( 1, makeColor( "#7f7f8f" ), STYLE_SOLID )
        };
    set( PROPERTY_BORDER, new Border( sides ) );
    
    set( PROPERTY_DISABLED_BACKGROUND_IMAGE, new FillImage( BlueGrey ) );
    set( PROPERTY_SELECTION_BACKGROUND_IMAGE, new FillImage( BlueGrey ) );
  }
}
