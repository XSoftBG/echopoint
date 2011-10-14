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

import nextapp.echo.extras.app.AccordionPane;
import nextapp.echo.extras.app.CalendarSelect;
import nextapp.echo.extras.app.ContextMenu;
import nextapp.echo.extras.app.MenuBarPane;
import nextapp.echo.extras.app.TabPane;

import echopoint.style.echo.EchoStyleSheet;

/**
 * A style sheet that includes default styles for Echo Extras components.
 *
 * @author Rakesh Vidyadharan 2009-05-26
 * @version $Id: ExtrasStyleSheet.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
@SuppressWarnings( { "ClassNamePrefixedWithPackageName" } )
public class ExtrasStyleSheet extends EchoStyleSheet
{
  private static final long serialVersionUID = 1L;

  /** {@inheritDoc} */
  @Override
  protected void init()
  {
    super.init();

    addAccordionPaneStyles();
    addCalendarSelectStyles();
    addMenuBarPaneStyles();
    addContextMenuStyles();
    addTabPaneStyles();
  }

  /** Add default styles for {@link nextapp.echo.extras.app.AccordionPane}. */
  protected void addAccordionPaneStyles()
  {
    final AccordionPaneStyle style = new AccordionPaneStyle();
    addStyle( AccordionPane.class, "", style );
    addStyle( AccordionPane.class, "Default", style );
  }

  /** Add default styles for {@link nextapp.echo.extras.app.CalendarSelect}. */
  protected void addCalendarSelectStyles()
  {
    final CalendarSelectStyle style = new CalendarSelectStyle();
    addStyle( CalendarSelect.class, "", style );
    addStyle( CalendarSelect.class, "Default", style );
  }

  /** Add default styles for {@link nextapp.echo.extras.app.MenuBarPane}. */
  protected void addMenuBarPaneStyles()
  {
    final MenuBarPaneStyle style = new MenuBarPaneStyle();
    addStyle( MenuBarPane.class, "", style );
    addStyle( MenuBarPane.class, "Default", style );
  }

  /** Add default styles for {@link nextapp.echo.extras.app.ContextMenu}. */
  protected void addContextMenuStyles()
  {
    final ContextMenuStyle style = new ContextMenuStyle();
    addStyle( ContextMenu.class, "", style );
    addStyle( ContextMenu.class, "Default", style );
  }

  /** Add default styles for {@link nextapp.echo.extras.app.TabPane}. */
  protected void addTabPaneStyles()
  {
    final TabPaneStyle style = new TabPaneStyle();
    addStyle( TabPane.class, "", style );
    addStyle( TabPane.class, "Default", style );
  }
}
