package echopoint.style.echo;

import nextapp.echo.app.ImageReference;
import nextapp.echo.app.ResourceImageReference;

/**
 * A utility class that maintains a cache of common image resources used
 * to style commonly used components.
 *
 * @author Rakesh Vidyadharan 2009-05-24
 * @version $Id: ResourceImages.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
@SuppressWarnings( { "ClassWithTooManyFields" } )
public final class ResourceImages
{
  /** The image used as background for input fields. */
  public static final ImageReference InputFieldBackground =
      new ResourceImageReference(
          "resource/images/InputFieldBackground.png" );

  /** The image used as pressed background for buttons. */
  public static final ImageReference InputFieldBackgroundPressed =
      new ResourceImageReference(
          "resource/images/InputFieldBackgroundPressed.png" );

  /** The image used as background for highlighted input fields. */
  public static final ImageReference InputFieldBackgroundHightlight =
      new ResourceImageReference(
          "resource/images/InputFieldBackgroundHighlight.png" );

  /** The background image for the title bar of window pane. */
  public static final ImageReference Header =
      new ResourceImageReference(
          "resource/images/window/Header.png" );

  /** The top left border image for window pane. */
  public static final ImageReference BorderTopLeft =
      new ResourceImageReference(
          "resource/images/window/BorderTopLeft.png" );

  /** The top border image for window pane. */
  public static final ImageReference BorderTop =
      new ResourceImageReference(
          "resource/images/window/BorderTop.png" );

  /** The top right border image for window pane. */
  public static final ImageReference BorderTopRight =
      new ResourceImageReference(
          "resource/images/window/BorderTopRight.png" );

  /** The left border image for window pane. */
  public static final ImageReference BorderLeft =
      new ResourceImageReference(
          "resource/images/window/BorderLeft.png" );

  /** The right border image for window pane. */
  public static final ImageReference BorderRight =
      new ResourceImageReference(
          "resource/images/window/BorderRight.png" );

  /** The bottom left border image for window pane. */
  public static final ImageReference BorderBottomLeft =
      new ResourceImageReference(
          "resource/images/window/BorderBottomLeft.png" );

  /** The bottom border image for window pane. */
  public static final ImageReference BorderBottom =
      new ResourceImageReference(
          "resource/images/window/BorderBottom.png" );

  /** The bottom right border image for window pane. */
  public static final ImageReference BorderBottomRight =
      new ResourceImageReference(
          "resource/images/window/BorderBottomRight.png" );

  /** The tab close icon image. */
  public static final ImageReference TabCloseIcon =
      new ResourceImageReference(
          "resource/images/icon/Icon16TabClose.png" );

  /** The tab close rollover icon image. */
  public static final ImageReference TabCloseRolloverIcon =
      new ResourceImageReference(
          "resource/images/icon/Icon16TabCloseRollover.png" );

  /** The tab rollover background image. */
  public static final ImageReference GradientBlue =
      new ResourceImageReference(
          "resource/images/fill/GradientBlue.png" );

  /** The tab inactive background image. */
  public static final ImageReference LightedSilver =
      new ResourceImageReference(
          "resource/images/fill/LightedSilver.png" );


//  /** The close icon image for window pane. */
//  public static final ImageReference WindowPaneCloseIcon =
//      new ResourceImageReference(
//          "resource/images/window/ControlClose.png" );
//
//  /** The close icon rollover image for window pane. */
//  public static final ImageReference WindowPaneCloseIconRollover =
//      new ResourceImageReference(
//          "resource/images/window/ControlCloseRollover.png" );
//
//  /** The maximise icon image for window pane. */
//  public static final ImageReference WindowPaneMaximise =
//      new ResourceImageReference(
//          "resource/images/window/ControlMaximize.png" );
//
//  /** The maximise icon rollover image for window pane. */
//  public static final ImageReference WindowPaneMaximiseRollover =
//      new ResourceImageReference(
//          "resource/images/window/ControlMaximizeRollover.png" );
//
//  /** The minimise icon image for window pane. */
//  public static final ImageReference WindowPaneMinimise =
//      new ResourceImageReference(
//          "resource/images/window/ControlMinimize.png" );
//
//  /** The minimise icon rollover image for window pane. */
//  public static final ImageReference WindowPaneMinimiseRollover =
//      new ResourceImageReference(
//          "resource/images/window/ControlMinimizeRollover.png" );

  private ResourceImages() {}
}
