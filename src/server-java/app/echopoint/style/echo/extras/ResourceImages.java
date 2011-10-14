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

import nextapp.echo.app.ImageReference;
import nextapp.echo.app.ResourceImageReference;

/**
 * A utility class used to cache resource images used in styles.
 *
 * @author Rakesh Vidyadharan 2009-05-27
 * @version $Id: ResourceImages.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public final class ResourceImages
{
  /** The calendar select background image. */
  public static final ImageReference CalendarSelectBackground =
      new ResourceImageReference(
          "resource/images/extras/CalendarSelectGradient.png" );

  /** The calendar select header background image. */
  public static final ImageReference CalendarSelectHeader =
      new ResourceImageReference(
          "resource/images/extras/CalendarHeaderBackground.png" );

  /** The calendar select selected date background image. */
  public static final ImageReference CalendarSelectSelectedDate =
      new ResourceImageReference(
          "resource/images/extras/CalendarSelectSelectedDateFill.png" );

  /** The menu bar pane background image. */
  public static final ImageReference MenuBarBackground =
      new ResourceImageReference(
          "resource/images/extras/PulldownMenuBackground.png" );

  /** The menu bar pane menu background image. */
  public static final ImageReference MenuBarMenuBackground =
      new ResourceImageReference(
          "resource/images/extras/GreyMenuBackground.png" );

  /** The menu bar pane selection background image. */
  public static final ImageReference MenuBarSelectionBackground =
      new ResourceImageReference(
          "resource/images/extras/BeigeLightedBackground.png" );

  /** The background image for drop-down menu components. */
  public static final ImageReference LightBlueLine =
      new ResourceImageReference(
          "resource/images/extras/LightBlueLine.png" );

  /** The background image for selected items in drop-down menu and accordion pane. */
  public static final ImageReference BlueGrey =
      new ResourceImageReference(
          "resource/images/extras/BlueGrey.png" );

  /** The accordion pane tab rollover background image. */
  public static final ImageReference BlueGreyHighlight =
      new ResourceImageReference(
          "resource/images/extras/BlueGreyHighlight.png" );

  private ResourceImages() {}
}
