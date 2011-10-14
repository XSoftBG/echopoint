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

import static echopoint.style.echo.extras.ResourceImages.CalendarSelectBackground;
import static echopoint.style.echo.extras.ResourceImages.CalendarSelectHeader;
import static echopoint.style.echo.extras.ResourceImages.CalendarSelectSelectedDate;
import static echopoint.util.ColorKit.makeColor;
import static nextapp.echo.app.Component.PROPERTY_BACKGROUND;
import static nextapp.echo.extras.app.CalendarSelect.PROPERTY_DATE_BACKGROUND;
import static nextapp.echo.extras.app.CalendarSelect.PROPERTY_DATE_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.CalendarSelect.PROPERTY_HEADER_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.CalendarSelect.PROPERTY_HEADER_FOREGROUND;
import static nextapp.echo.extras.app.CalendarSelect.PROPERTY_ROLLOVER_DATE_BACKGROUND;
import static nextapp.echo.extras.app.CalendarSelect.PROPERTY_SELECTED_DATE_BACKGROUND;
import static nextapp.echo.extras.app.CalendarSelect.PROPERTY_SELECTED_DATE_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.CalendarSelect.PROPERTY_SELECTED_DATE_BORDER;
import nextapp.echo.app.Border;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import echopoint.style.AbstractStyle;

/**
 * The default style class for use with {@link
 * nextapp.echo.extras.app.CalendarSelect} components.
 *
 * @author Rakesh Vidyadharan 2009-05-26
 * @version $Id: CalendarSelectStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class CalendarSelectStyle extends AbstractStyle
{
  private static final long serialVersionUID = 1l;

  @Override
  protected void init()
  {
    setGeneralStyles();
    setDateStyles();
    setSelectedDateStyles();
  }

  /** Set general styles for the component. */
  protected void setGeneralStyles()
  {
    set( PROPERTY_BACKGROUND, makeColor( "#6f87af" ) );
    set( PROPERTY_HEADER_BACKGROUND_IMAGE, new FillImage(
        CalendarSelectHeader, new Extent( 100, Extent.PERCENT ),
        new Extent( 50, Extent.PERCENT ), FillImage.REPEAT ) );
    set( PROPERTY_HEADER_FOREGROUND, makeColor( "#ffffff" ) );
  }

  /** Set the styles for the date cells. */
  protected void setDateStyles()
  {
    set( PROPERTY_DATE_BACKGROUND, makeColor( "#bcbcbc" ) );
    set( PROPERTY_DATE_BACKGROUND_IMAGE, new FillImage(
        CalendarSelectBackground, new Extent( 0 ),
        new Extent( 0 ), FillImage.REPEAT_HORIZONTAL ) );
    set( PROPERTY_ROLLOVER_DATE_BACKGROUND, makeColor( "#ffffff" ) );
  }

  /** Set the styles for the selected date cell. */
  protected void setSelectedDateStyles()
  {
    set( PROPERTY_SELECTED_DATE_BACKGROUND, makeColor( "#1579c7" ) );
    set( PROPERTY_SELECTED_DATE_BACKGROUND_IMAGE,
        new FillImage( CalendarSelectSelectedDate ) );

    final Border.Side[] sides = new Border.Side[4];
    sides[Border.SIDE_TOP] =
        new Border.Side( 1, makeColor( "#0e4f82" ), Border.STYLE_SOLID );
    sides[Border.SIDE_RIGHT] = sides[Border.SIDE_TOP];
    sides[Border.SIDE_BOTTOM] =
        new Border.Side( 1, makeColor( "#1472bc" ), Border.STYLE_SOLID );
    sides[Border.SIDE_LEFT] = sides[Border.SIDE_BOTTOM];
    set( PROPERTY_SELECTED_DATE_BORDER, new Border( sides ) );
  }
}
