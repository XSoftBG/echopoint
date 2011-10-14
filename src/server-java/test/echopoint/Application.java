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

import nextapp.echo.app.ApplicationInstance;
import nextapp.echo.app.Window;

/**
 * The application instance implementation for the test application.
 *
 * @author Rakesh 2008-06-24
 * @version $Id: Application.java,v 1.2 2011-05-28 13:24:00 perxi Exp $
 */
public class Application extends ApplicationInstance
{
  private static final long serialVersionUID = 1l;

  /** The window instance that is maintained by this application. */
  private Window window;

  public Window init()
  {
    window = new Window();
    window.setTitle( "EchoPoint Test Application" );
    window.setContent( new MainContent() );
    return window;
  }

  /**
   * Return the type-cast current application instance.
   *
   * @return The {@link nextapp.echo.app.ApplicationInstance#getActive()}
   *   properly type-cast to this class.
   */
  public static Application getApplication()
  {
    return (Application) getActive();
  }

  /**
   * Return the current {@link MainContent} instance used by the currently
   * active application instance.
   *
   * @see nextapp.echo.app.Window#getContent()
   * @return The content pane for the current application.
   */
  public static MainContent getContent()
  {
    return (MainContent) getApplication().getWindow().getContent();
  }

  /**
   * Return the window instance managed by this application.
   *
   * @return The window instance.
   */
  public Window getWindow()
  {
    return window;
  }
}
