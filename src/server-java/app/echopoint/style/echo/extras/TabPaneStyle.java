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

import static echopoint.style.echo.ResourceImages.BorderBottom;
import static echopoint.style.echo.ResourceImages.BorderBottomLeft;
import static echopoint.style.echo.ResourceImages.BorderBottomRight;
import static echopoint.style.echo.ResourceImages.BorderLeft;
import static echopoint.style.echo.ResourceImages.BorderRight;
import static echopoint.style.echo.ResourceImages.BorderTop;
import static echopoint.style.echo.ResourceImages.BorderTopLeft;
import static echopoint.style.echo.ResourceImages.BorderTopRight;
import static echopoint.style.echo.ResourceImages.GradientBlue;
import static echopoint.style.echo.ResourceImages.LightedSilver;
import static echopoint.style.echo.ResourceImages.TabCloseIcon;
import static echopoint.style.echo.ResourceImages.TabCloseRolloverIcon;
import static echopoint.util.ColorKit.makeColor;
import static echopoint.util.ExtentKit.makeExtent;
import static nextapp.echo.app.Component.PROPERTY_BACKGROUND;
import static nextapp.echo.extras.app.TabPane.PROPERTY_IMAGE_BORDER;
import static nextapp.echo.extras.app.TabPane.PROPERTY_INSETS;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_ACTIVE_BACKGROUND;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_ACTIVE_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_ACTIVE_BACKGROUND_INSETS;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_ACTIVE_INSETS;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_CLOSE_ICON;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_CLOSE_ICON_ROLLOVER_ENABLED;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_ICON_TEXT_MARGIN;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_INACTIVE_BACKGROUND;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_INACTIVE_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_INACTIVE_BACKGROUND_INSETS;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_INACTIVE_INSETS;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_ROLLOVER_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_ROLLOVER_CLOSE_ICON;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_ROLLOVER_ENABLED;
import static nextapp.echo.extras.app.TabPane.PROPERTY_TAB_ROLLOVER_FOREGROUND;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.FillImageBorder;
import nextapp.echo.app.Insets;
import echopoint.style.AbstractStyle;

/**
 * The default style to use for {@link nextapp.echo.extras.app.TabPane}
 * components.
 *
 * @author Rakesh Vidyadharan 2009-11-19
 * @version $Id: TabPaneStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class TabPaneStyle extends AbstractStyle
{
  private static final long serialVersionUID = 1L;

  @Override
  protected void init()
  {
    super.init();

    setImageBorder();
    setImages();
    setInsets();
    setSpacing();
    setColor();
    setRollover();
  }

  protected void setImageBorder()
  {
    final FillImageBorder border = new FillImageBorder();
    border.setContentInsets( new Insets( 4, 4, 6, 4 ) );
    border.setBorderInsets( new Insets( 17, 23, 23, 17 ) );
    border.setFillImage( FillImageBorder.TOP_LEFT,
        new FillImage( BorderTopLeft ) );
    border.setFillImage( FillImageBorder.TOP,
        new FillImage( BorderTop ) );
    border.setFillImage( FillImageBorder.TOP_RIGHT,
        new FillImage( BorderTopRight ) );

    border.setFillImage( FillImageBorder.LEFT,
        new FillImage( BorderLeft ) );
    border.setFillImage( FillImageBorder.RIGHT,
        new FillImage( BorderRight ) );

    border.setFillImage( FillImageBorder.BOTTOM_LEFT,
        new FillImage( BorderBottomLeft ) );
    border.setFillImage( FillImageBorder.BOTTOM,
        new FillImage( BorderBottom ) );
    border.setFillImage( FillImageBorder.BOTTOM_RIGHT,
        new FillImage( BorderBottomRight ) );

    set( PROPERTY_IMAGE_BORDER, border );
  }


  protected void setImages()
  {
    set( PROPERTY_TAB_CLOSE_ICON, TabCloseIcon );
    set( PROPERTY_TAB_CLOSE_ICON_ROLLOVER_ENABLED, true );
    set( PROPERTY_TAB_ROLLOVER_CLOSE_ICON, TabCloseRolloverIcon );
    set( PROPERTY_TAB_ACTIVE_BACKGROUND_IMAGE,
        new FillImage( LightedSilver, makeExtent( "0px" ),
            makeExtent( "53%" ), FillImage.REPEAT_HORIZONTAL ) );
  }

  protected void setInsets()
  {
    set( PROPERTY_INSETS, new Insets( 0 ) );
    set( PROPERTY_TAB_ACTIVE_INSETS, new Insets( 4, 10 ) );
    set( PROPERTY_TAB_INACTIVE_INSETS, new Insets( 4, 10 ) );
    set( PROPERTY_TAB_ACTIVE_BACKGROUND_INSETS, new Insets( 8, 14, 0, 8 ) );
    set( PROPERTY_TAB_INACTIVE_BACKGROUND_INSETS, new Insets( 8, 14, 1, 8 ) );
  }

  protected void setSpacing()
  {
    set( PROPERTY_TAB_ICON_TEXT_MARGIN, new Extent( 3 ) );
  }

  protected void setColor()
  {
    set( PROPERTY_BACKGROUND, makeColor( "#ffffff" ) );
    set( PROPERTY_TAB_ACTIVE_BACKGROUND, makeColor( "#ffffff" ) );
    set( PROPERTY_TAB_INACTIVE_BACKGROUND, makeColor( "#e7e7e7" ) );
  }

  protected void setRollover()
  {
    set( PROPERTY_TAB_ROLLOVER_ENABLED, true );
    set( PROPERTY_TAB_ROLLOVER_FOREGROUND, makeColor( "#ffffff" ) );

    final FillImage inactive = new FillImage( LightedSilver,
        new Extent( 0 ), new Extent( 53, Extent.PERCENT ),
        FillImage.REPEAT_HORIZONTAL );
    set( PROPERTY_TAB_INACTIVE_BACKGROUND_IMAGE, inactive );

    final FillImage rollover = new FillImage( GradientBlue,
        makeExtent( "0px" ), makeExtent( "50%" ), FillImage.REPEAT );
    set( PROPERTY_TAB_ROLLOVER_BACKGROUND_IMAGE, rollover );
  }
}
