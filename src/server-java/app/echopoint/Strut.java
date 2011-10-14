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

import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import echopoint.internal.AbstractContainer;

/**
 * <b>Strut</b> is a very simple component that can have a fixed width and height. It
 * is most commonly used to create precise spaces between components laid out
 * in a container component such as {@link nextapp.echo.app.Column}.
 *
 * <p>The following shows how to add a 50 px space between two labels in
 * a {@link nextapp.echo.app.Row} container.</p>
 * <pre>
 *   import echopoint.Strut;
 *   import nextapp.echo.app.Label;
 *   import nextapp.echo.app.Row;
 *
 *     ...
 *     final Row row = new Row();
 *     row.add( new Label( "Label 1" ) );
 *     row.add( new Strut( 50, 10 ) );
 *     row.add( new Label( "Label 2" ) );
 * </pre>
 *
 * @author Brad Baker <p>Modified by Rakesh 2008-07-20</p>
 * @version $Id: Strut.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class Strut extends AbstractContainer
{
  private static final long serialVersionUID = 1l;

  /** Constructs a <b>Strut</b> that is 10px wide by 10px high. */
  public Strut()
  {
    this( null, null );
  }

  /**
   * Constructs a <b>Strut</b> that is <code>width</code> pixels wide by
   * <code>height</code> pixels high.
   *
   * @param width The width in pixels for this component.
   * @param height The height in pixels for this component.
   */
  public Strut( final int width, final int height )
  {
    this( new Extent( width ), new Extent( height ) );
  }

  /**
   * Constructs a <b>Strut</b>.  This is the designated constructor.
   *
   * @param width The width of the Strut
   * @param height The height of the Strut
   */
  public Strut( final Extent width, final Extent height )
  {
    setWidth( width );
    setHeight( height );
  }

  /**
   * <b>Strut</b> is <i>NOT</i> allowed to have any children.
   *
   * @see nextapp.echo.app.Component#isValidChild(nextapp.echo.app.Component)
   */
  @Override
  public boolean isValidChild( final Component child )
  {
    return false;
  }
}
