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

import java.util.logging.Level;
import java.util.logging.Logger;

import nextapp.echo.app.Component;
import nextapp.echo.app.FloatingPane;
import nextapp.echo.app.Label;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 * The action listener to invoke when a test control button is pressed.
 * Instantiates a test case named <code>Button.getTitle() + Test</code>
 * and adds to the component hierarchy.
 *
 * @author Rakesh 2008-06-24
 * @version $Id: TestListener.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
class TestListener implements ActionListener
{
  private static final long serialVersionUID = 1l;

  /** The logger to use to log errors. */
  protected static final Logger logger = Logger.getAnonymousLogger();

  /**
   * The action listener implementation.  Displays the component specified
   * as the title of the button.
   *
   * @see #test
   * @param event The action event that was generated.
   */
  public void actionPerformed( final ActionEvent event )
  {
    final MenuButton button = (MenuButton) event.getSource();
    try
    {
      test( button );
    }
    catch ( Throwable t )
    {
      logger.log( Level.SEVERE, "Error instantiating test", t );
    }
  }

  /**
   * Instantiate the test case for the component being tested.
   *
   * @param button The button that triggered the test.
   * @throws Exception If errors are encountered while initialising the
   *   test case.
   */
  protected void test( final MenuButton button ) throws Exception
  {
    final String testCaseName =
        getClass().getPackage().getName() + "." + button.getText() + "Test";
    final Class testCase = Class.forName( testCaseName );

    Application.getContent().getLogArea().removeAll();

    for ( int i = 0; i < Application.getContent().getComponentCount(); ++i )
    {
      final Component component = Application.getContent().getComponent( i );
      if ( component instanceof FloatingPane )
      {
        Application.getContent().remove( component );
      }
    }
    final JUnitCore unitCore = new JUnitCore();
    unitCore.addListener( new Listener() );
    unitCore.run( testCase );
  }

  /** A custom test run listener to trace progress of the test suite. */
  class Listener extends RunListener
  {
    /**
     * Update the {@link echopoint.MainContent#getLogArea()} with the
     * progress of the test case.
     */
    @Override
    public void testStarted( final Description description )
    {
      final Component logArea = Application.getContent().getLogArea();
      logArea.add( new Label( "Running test: " + description.toString() ) );
    }

    /**
     * Update the {@link echopoint.MainContent#getLogArea()} with the
     * error information caused by a failed test.
     */
    @Override
    public void testFailure( final Failure failure )
    {
      String trace = failure.getTrace();
      trace = trace.replace(  "\r\n", "<br/>" );
      trace = trace.replace(  "\n", "<br/>" );

      final String message = failure.getDescription().toString() +
          "<br/>" + trace;

      final Component logArea = Application.getContent().getLogArea();
      final DirectHtml component = new DirectHtml( message );
      logArea.add( component );
    }
  }
}
