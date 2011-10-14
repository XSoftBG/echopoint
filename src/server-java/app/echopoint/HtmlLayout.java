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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import nextapp.echo.app.Component;
import echopoint.internal.AbstractHtmlComponent;

/**
 * A container component that uses user specified XHTML layout to render
 * components.  Provides a more flexible and powerful alternative to
 * usual layout containers.
 *
 * <p>The following code shows sample use of this component:</p>
 * <pre>
 *  import echopoint.HtmlLayout;
 *  import echopoint.layout.HtmlLayoutData;
 *  import nextapp.echo.app.Button;
 *  import nextapp.echo.app.Component;
 *  import nextapp.echo.app.Label;
 *
 *    ...
 *    final String text = "&lt;table border='1'&gt;" +
 *        "&lt;tr&gt;" +
 *        "&lt;td colspan='2'&gt;" +
 *        "This is regular HTML text in layout component!" +
 *        "&lt;/td&gt;" +
 *        "&lt;/tr&gt;" +
 *        "&lt;tr&gt;" +
 *         "&lt;td id='one' colspan='2'&gt;&lt;/td&gt;" +
 *         "&lt;/tr&gt;" +
 *         "&lt;tr&gt;" +
 *         "&lt;td id='two'&gt;&lt;/td&gt;" +
 *         "&lt;td id='three'&gt;&lt;/td&gt;" +
 *         "&lt;/tr&gt;" +
 *         "&lt;/table&gt;";
 *    final HtmlLayout container = new HtmlLayout( text );
 *    Component child = new Button( "Button One" );
 *
 *    // Layout data that specified that component is child of td with id one
 *    HtmlLayoutData layout = new HtmlLayoutData( "one" );
 *    child.setLayoutData( layout );
 *    container.add( child );
 *
 *    child = new Label( "Label One" );
 *    layout = new HtmlLayoutData( "two" );
 *    child.setLayoutData( layout );
 *    container.add( child );
 *
 *    child = new Label( "Label Two" );
 *    layout = new HtmlLayoutData( "three" );
 *    child.setLayoutData( layout );
 *    container.add( child );
 *    ...
 *    parent.add( container );
 *    ...
 *    final HtmlLayout fromFile = new HtmlLayout( "/tmp/test.html", "UTF-8" );
 * </pre>
 *
 * @author Simon Lei 2009-03-16
 * @version $Id: HtmlLayout.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 * @since 3.0.0a11
 */
public class HtmlLayout extends AbstractHtmlComponent
{
  private static final long serialVersionUID = 1l;

  /**
   * Default constructor.  Not particularly useful.  You must invoke
   * {@link #setText} before the layout container is rendered to avoid a
   * client-side exception.
   */
  public HtmlLayout() {}

  /**
   * Create a new instance using the specified XHTML layout code.
   *
   * @param text The layout code to use to embed components.
   */
  public HtmlLayout( final String text )
  {
    super( text );
  }

  /**
   * Create a new instance using the XHTML layout data specified in the
   * input stream (file or resource usually).
   *
   * @param instream The input stream from which to read the XHTML layout data.
   * @param charset The character set to use to read the input stream.
   * @throws IOException If errors are encountered while reading the
   *   contents of the input stream.
   */
  public HtmlLayout( final InputStream instream, final String charset )
      throws IOException
  {
    final BufferedReader reader =
        new BufferedReader( new InputStreamReader( instream, charset ) );
    final StringBuilder builder = new StringBuilder( 1024 );
    String line;

    while ( ( line = reader.readLine() ) != null )
    {
      builder.append( line );
    }

    setText( builder.toString() );
  }

  /**
   * Over-ridden to return {@code true} since this component allows children.
   *
   * @param component The component to check.
   * @return Returns {@code true}.
   */
  @Override
  public boolean isValidChild( final Component component )
  {
    return true;
  }

  /** Over-ridden to make no-op. */
  @Override
  public void setTarget( final String target )
  {
    // no op
  }
}
