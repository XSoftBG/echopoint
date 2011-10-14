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

import echopoint.internal.AbstractHtmlComponent;

/**
 * DirectHtml is a very lightweight component that will insert HTML text
 * directly onto the client.  The inserted text is contained within a parent
 * <code>&lt;div&gt;</code> element.  This component will <b>not</b> validate
 * this HTML text.
 *
 * <p>This component differs from {@link HtmlLabel} in that
 * this compoonent is designed to hold more complex HTML content while
 * {@link HtmlLabel} is designed to hold simple (phrase) HTML content.</p>
 *
 * <p><b>Note:</b> Be careful of your use of id attributes in the HTML
 * text as they may clash with Echo3 generated ones.  Also note that this
 * component does not support child components.</p>
 *
 * <p>The following shows sample use of this component:</p>
 * <pre>
 *   import nextapp.echo.app.Column;
 *   import echopoint.DirectHtml;
 *
 *     ...
 *     final Column column = new Column();
 *     final String text = "&lt;p&gt;First line.&lt;/p&gt;" +
 *         "&lt;p&gt;Second &lt;b&gt;line&lt;/b&gt;&lt;/p&gt;" +
 *         "&lt;p&gt;&lt;b&gt;&lt;i&gt;Third&lt;/i&gt; line&lt;/b&gt;&lt;/p&gt;";
 *     final DirectHtml html = new DirectHtml( text );
 *     html.setStyleName( "Default.DirectHtml" );
 *     html.setTarget( "_new" );
 *     column.add( html );
 * </pre>
 *
 * @author Rakesh 2008-03-22
 * @version $Id: DirectHtml.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class DirectHtml extends AbstractHtmlComponent
{
  private static final long serialVersionUID = 1l;

  /** Default constructor.  Create a new instance with empty text. */
  public DirectHtml() {}

  /**
   * Create a new instance enclosing the specified HTML text.
   *
   * @param text The HTML text that is to be displayed in this component.
   */
  public DirectHtml( final String text )
  {
    super( text );
  }

  /**
   * Create a new instance enclosing the specified HTML text and setting the
   * <code>target</code> attribute for all anchor tags to the value specified.
   *
   * @param text The HTML text that is to be displayed in this component.
   * @param target The target attribute.  Note that target attributes that
   *   already exist in anchor tags are left untouched.
   */
  public DirectHtml( final String text, final String target )
  {
    this( text );
    setTarget( target );
  }
}
