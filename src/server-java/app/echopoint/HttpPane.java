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
import echopoint.internal.AbstractContainer;

/**
 * A component that uses a <code>iframe</code> to dislay the contents of a
 * user specified URI.  Note that unlike the EPNG
 * <a href='http://docs.rakeshv.org/java/echopointng/echopointng/HttpPaneEx.html'>HttpPaneEx</a>
 * component, this component is not a subclass of {@link
 * nextapp.echo.app.ContentPane} and hence may be embedded anywhere within
 * your application component hierarchy.
 *
 * <p>The following code sample shows usage of this component:</p>
 * <pre>
 *   import echopoint.HttpPane;
 *   import nextapp.echo.app.Column;
 *
 *     ...
 *     final Column column = new Column();
 *     final String uri = "https://echopoint.dev.java.net/";
 *     final HttpPane pane = new HttpPane( uri );
 *     column.add( pane );
 * </pre>
 *
 * @author Brad Baker.  Modified by Rakesh 2008-07-13
 * @version $Id: HttpPane.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class HttpPane extends AbstractContainer
{
  private static final long serialVersionUID = 1l;

  /** The URI to display in this component. */
  public static final String PROPERTY_URI = "uri";

  /** Constructs a new instance that loads a blank page. */
  public HttpPane()
  {
    this( "javascript:void" );
  }

  /**
   * Constructs a new instance that loads the contents of the specified URI.
   * Note that the URI needs to specify the protocal (eg. http, https, etc.)
   * for the iframe to load the contents properly.
   *
   * @param uri The URI to load in this component.
   */
  public HttpPane( final String uri )
  {
    setUri( uri );
  }

  /**
   * Return the URI that is currently loaded in this component.
   *
   * @return The URI being displayed
   */
  public String getUri()
  {
    return (String) get( PROPERTY_URI );
  }

  /**
   * Sets the URI to display in this component.
   *
   * @param uri The URI to load in this component.
   */
  public void setUri( final String uri )
  {
    set( PROPERTY_URI, uri );
  }

  /**
   * Over-ridden to unconditionally return <code>false</code> as no children
   * are allowed.
   *
   * {@inheritDoc}
   */
  @Override
  public boolean isValidChild( final Component child )
  {
    return false;
  }
}
