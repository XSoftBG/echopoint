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

import nextapp.echo.app.MutableStyleSheet;
import nextapp.echo.app.WindowPane;
import nextapp.echo.app.button.AbstractButton;
import nextapp.echo.app.list.AbstractListComponent;
import nextapp.echo.app.text.TextComponent;

/**
 * An extensible stylesheet that enforces a default look-and-feel for all
 * Echo components.  Can be used as the starting point for applications
 * built using the framework.  We hope this promotes an object-oriented
 * management of styles for applications.
 *
 * <p>It is recommended that you extend from {@link echopoint.Servlet} (in
 * the webcontainer area) since the base servlet takes care of initialising
 * default style properties from init parameters.</p>
 *
 * <p>This class is based upon the
 * <a href='http://sptci.com/docs/public/com/sptci/echo/style/StyleSheet.html'>StyleSheet</a>
 * implementation that <a href='http://sptci.com/'>SPT</a> has used as the
 * basis for building a number of Echo2/3 applications.</p>
 *
 * @author Rakesh Vidyadharan 2009-05-24
 * @version $Id: EchoStyleSheet.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class EchoStyleSheet extends MutableStyleSheet
{
  private static final long serialVersionUID = 1L;

  /**
   * Initialise the stylesheet.
   *
   * @see #init
   */
  public EchoStyleSheet()
  {
    init();
  }

  /**
   * Initialises the default styles for the various components.  Sub-classes
   * should over-ride this method (while still invoking {@code super.init()})
   * to add additional styles.  Alternatively, sub-classes may over-ride the
   * various component style setting methods as appropriate.
   */
  protected void init()
  {
    addButtonStyles();
    addListStyles();
    addTextComponentStyles();
    addWindowPaneStyles();
  }

  /** Add default styles for {@link nextapp.echo.app.button.AbstractButton}s */
  protected void addButtonStyles()
  {
    final AbstractButtonStyle style = new AbstractButtonStyle();
    addStyle( AbstractButton.class, "", style );
    addStyle( AbstractButton.class, "Default", style );
  }

  /** Add default styles for {@link nextapp.echo.app.list.AbstractListComponent}s */
  protected void addListStyles()
  {
    final AbstractListComponentStyle style = new AbstractListComponentStyle();
    addStyle( AbstractListComponent.class, "", style );
    addStyle( AbstractListComponent.class, "Default", style );
  }

  /** Add default styles for {@link nextapp.echo.app.text.TextComponent}s */
  protected void addTextComponentStyles()
  {
    final TextComponentStyle style = new TextComponentStyle();
    addStyle( TextComponent.class, "", style );
    addStyle( TextComponent.class, "Default", style );
  }

  /** Add default styles for {@link nextapp.echo.app.WindowPane}s. */
  protected void addWindowPaneStyles()
  {
    final WindowPaneStyle style = new WindowPaneStyle();
    addStyle( WindowPane.class, "", style );
    addStyle( WindowPane.class, "Default", style );
  }
}
