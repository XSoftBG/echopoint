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

package echopoint.style.echo;

import static echopoint.style.DefaultFont.fontWithStyle;
import static echopoint.style.echo.ResourceImages.BorderBottom;
import static echopoint.style.echo.ResourceImages.BorderBottomLeft;
import static echopoint.style.echo.ResourceImages.BorderBottomRight;
import static echopoint.style.echo.ResourceImages.BorderLeft;
import static echopoint.style.echo.ResourceImages.BorderRight;
import static echopoint.style.echo.ResourceImages.BorderTop;
import static echopoint.style.echo.ResourceImages.BorderTopLeft;
import static echopoint.style.echo.ResourceImages.BorderTopRight;
import static echopoint.style.echo.ResourceImages.Header;
import static echopoint.util.ColorKit.makeColor;
import static nextapp.echo.app.Component.PROPERTY_BACKGROUND;
import static nextapp.echo.app.WindowPane.PROPERTY_BORDER;
import static nextapp.echo.app.WindowPane.PROPERTY_CONTROLS_INSETS;
import static nextapp.echo.app.WindowPane.PROPERTY_MAXIMIZE_ENABLED;
import static nextapp.echo.app.WindowPane.PROPERTY_TITLE_BACKGROUND;
import static nextapp.echo.app.WindowPane.PROPERTY_TITLE_BACKGROUND_IMAGE;
import static nextapp.echo.app.WindowPane.PROPERTY_TITLE_FONT;
import static nextapp.echo.app.WindowPane.PROPERTY_TITLE_FOREGROUND;
import static nextapp.echo.app.WindowPane.PROPERTY_TITLE_INSETS;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.FillImageBorder;
import nextapp.echo.app.Insets;
import echopoint.style.AbstractStyle;

/**
 * The default style to apply to {@link nextapp.echo.app.WindowPane}
 * components.
 *
 * @author Rakesh Vidyadharan 2009-05-24
 * @version $Id: WindowPaneStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class WindowPaneStyle extends AbstractStyle
{
  private static final long serialVersionUID = 1L;

  /** {@inheritDoc} */
  @Override
  protected void init()
  {
    super.init();

    set( PROPERTY_BACKGROUND, makeColor( "#cfdfff" ) );
    setBorderStyle();
    setControlsStyles();
    setTitleStyles();
  }

  /** Set the fill images used as border for the window pane. */
  protected void setBorderStyle()
  {
    final FillImageBorder border = new FillImageBorder();
    border.setBorderInsets( new Insets( 20, 34, 20, 20 ) );
    border.setContentInsets( new Insets( 4, 4, 6, 4 ) );

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

    set( PROPERTY_BORDER, border );
  }

  /** Set the styles for the control buttons on title bar. */
  protected void setControlsStyles()
  {
    set( PROPERTY_MAXIMIZE_ENABLED, true );
    set( PROPERTY_CONTROLS_INSETS, new Insets( 2, 10 ) );
  }

  /** Set styles for the title bar of the window pane. */
  protected void setTitleStyles()
  {
    set( PROPERTY_TITLE_BACKGROUND, makeColor( "#2f2f4f" ) );
    set( PROPERTY_TITLE_BACKGROUND_IMAGE, new FillImage( Header,
        new Extent( 0 ), new Extent( 50, Extent.PERCENT ),
        FillImage.REPEAT_HORIZONTAL ) );

    set( PROPERTY_TITLE_BACKGROUND_IMAGE, makeColor( "#2f2f4f" ) );
    set( PROPERTY_TITLE_FONT, fontWithStyle( "BOLD" ) );
    set( PROPERTY_TITLE_FOREGROUND, makeColor( "#ffffff" ) );
    set( PROPERTY_TITLE_INSETS, new Insets( 5, 10 ) );
  }
}
