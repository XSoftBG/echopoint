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
import static echopoint.style.echo.extras.ResourceImages.BlueGreyHighlight;
import static echopoint.util.ColorKit.makeColor;
import static echopoint.util.ExtentKit.makeExtent;
import static nextapp.echo.app.Border.STYLE_SOLID;
import static nextapp.echo.app.FillImage.REPEAT;
import static nextapp.echo.extras.app.AccordionPane.PROPERTY_TAB_BACKGROUND;
import static nextapp.echo.extras.app.AccordionPane.PROPERTY_TAB_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.AccordionPane.PROPERTY_TAB_BORDER;
import static nextapp.echo.extras.app.AccordionPane.PROPERTY_TAB_FOREGROUND;
import static nextapp.echo.extras.app.AccordionPane.PROPERTY_TAB_ROLLOVER_BACKGROUND;
import static nextapp.echo.extras.app.AccordionPane.PROPERTY_TAB_ROLLOVER_BACKGROUND_IMAGE;
import static nextapp.echo.extras.app.AccordionPane.PROPERTY_TAB_ROLLOVER_ENABLED;
import nextapp.echo.app.Border;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.Border.Side;
import echopoint.style.AbstractStyle;

/**
 * The default style to apply to {@link nextapp.echo.extras.app.AccordionPane}
 * components.
 *
 * @author Rakesh Vidyadharan 2009-05-26
 * @version $Id: AccordionPaneStyle.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class AccordionPaneStyle extends AbstractStyle
{
  private static final long serialVersionUID = 1L;

  /** {@inheritDoc} */
  @Override
  protected void init()
  {
    super.init();

    set( PROPERTY_TAB_BACKGROUND, makeColor( "#514f58" ) );
    set( PROPERTY_TAB_BACKGROUND_IMAGE,
        new FillImage( BlueGrey, makeExtent( "0px" ), makeExtent( "50%" ), REPEAT ) );
    set( PROPERTY_TAB_FOREGROUND, makeColor( "#ffffff" ) );

    setBorder();
    setRollover();
  }

  protected void setRollover()
  {
    set( PROPERTY_TAB_ROLLOVER_BACKGROUND, makeColor( "#86899a" ) );
    set( PROPERTY_TAB_ROLLOVER_BACKGROUND_IMAGE,
        new FillImage( BlueGreyHighlight, makeExtent( "0px" ),
            makeExtent( "50%" ), REPEAT ) );
    set( PROPERTY_TAB_ROLLOVER_ENABLED, true );
  }

  /** Set the tab border style. */
  protected void setBorder()
  {
    set( PROPERTY_TAB_BORDER, new Border( new Side[]
        {
            new Side( 1, makeColor( "#817f88" ), STYLE_SOLID ),
            new Side( 0, makeColor( "#817f88" ), STYLE_SOLID ),
            new Side( 1, makeColor( "#312f38" ), STYLE_SOLID ),
            new Side( 0, makeColor( "#312f38" ), STYLE_SOLID )
        } ) );
  }
}
