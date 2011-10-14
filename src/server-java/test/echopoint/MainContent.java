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

import nextapp.echo.app.Column;
import nextapp.echo.app.Component;
import nextapp.echo.app.ContentPane;
import nextapp.echo.app.Extent;
import nextapp.echo.app.SplitPane;

/**
 * The content pane for the test application.  Displays the various controls
 * to display and test the EchoPoint components.
 *
 * @author Rakesh 2008-06-24
 * @version $Id: MainContent.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class MainContent extends ContentPane
{
  private static final long serialVersionUID = 1l;

  /** The component in which the controls for launching tests are displayed. */
  private Component componentList;

  /** The component in which the components being tested are displayed. */
  private Component testArea;

  /** The container component in which test logs are displayed. */
  private Component logArea;

  /**
   * Life-cycle method invoked when the component is added to the hierarchy.
   * Lays out the split pane and other components.
   *
   * @see ComponentList
   */
  @Override
  public void init()
  {
    removeAll();
    super.init();

    componentList = new ComponentList();
    testArea = new Column();
    logArea = new Column();

    final SplitPane outer = createSplitPane();
    outer.add( componentList );

    final SplitPane content = createContent();
    content.add( testArea );
    content.add( logArea );
    outer.add( content );

    add( outer );
  }

  /**
   * Create the split pane used to display the component list and test
   * components.
   *
   * @return The properly initialised split pane component.
   */
  private SplitPane createSplitPane()
  {
    final SplitPane pane = new SplitPane();
    pane.setResizable( true );
    pane.setSeparatorWidth( new Extent( 2 ) );
    pane.setSeparatorPosition( new Extent( 300 ) );

    return pane;
  }

  /**
   * Create the split pane used to display the test component (and any
   * associated components) and the test log area.
   *
   * @return The properly initialised split pane component.
   */
  private SplitPane createContent()
  {
    final SplitPane pane = new SplitPane();
    pane.setResizable( true );
    pane.setOrientation( SplitPane.ORIENTATION_VERTICAL );
    pane.setSeparatorPosition( new Extent( 80, Extent.PERCENT ) );
    pane.setSeparatorHeight( new Extent( 2 ) );

    return pane;
  }

  /**
   * @return Return the component that lists the controls for launching tests.
   */
  public Component getComponentList()
  {
    return componentList;
  }

  /**
   * @return Returns the container component in which the components being
   *   tested are displayed.
   */
  public Component getTestArea()
  {
    return testArea;
  }

  /** @return Return the container comopnent in which test logs are displayed. */
  public Component getLogArea()
  {
    return logArea;
  }
}
