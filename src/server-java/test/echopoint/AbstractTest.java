package echopoint;

import nextapp.echo.app.Component;

/**
 * An abstract base class for all test suites.  This class enforces thread
 * safety for all the test suites by keeping the test component as a {@link
 * ThreadLocal} variable.  The unit test suites keep a single <code>static</code>
 * component to facilitate running a suite of tests on the same instance.  This
 * makes the unit test application non-thread safe that can confuse users
 * who happen to deploy it on a central server for testing.
 *
 * @author Rakesh Vidyadharan 2008-11-12
 * @version $Id: AbstractTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class AbstractTest<C extends Component>
{
  private static final ThreadLocal<Component> component =
      new ThreadLocal<Component>();

  /**
   * Set the component being tested.
   *
   * @param component The component being tested.
   */
  protected static void set( final Component component )
  {
    AbstractTest.component.set( component );
  }

  /**
   * Return the component being tested in the current thread.
   *
   * @return The component being tested.
   */
  protected static Component get()
  {
    return component.get();
  }

  /**
   * Return the component being tested in the current thread.
   *
   * @return The component being tested.
   */
  @SuppressWarnings( {"unchecked"} )
  protected C getComponent()
  {
    return (C) component.get();
  }
}
