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
 * HtmlLabel is a very lightweight component that will insert HTML text
 * directly onto the client.  The inserted text is contained within a parent
 * <code>&lt;span&gt;</code> element.
 *
 * <p><b>Note:</b> Be careful of your use of id attributes in the HTML
 * text as they may clash with Echo3 generated ones.  Also note that this
 * component does not support child components.</p>
 *
 * <p>The following shows sample use of this component:</p>
 * <pre>
 *   import nextapp.echo.app.Column;
 *   import echopoint.HtmlLabel;
 *
 *     ...
 *     final Column column = new Column();
 *     final String text = "&lt;b&gt;My&lt;/b&gt; &lt;i&gt;new&lt;/i&gt; &lt;code&gt;label&lt;/code&gt; &lt;sup&gt;*&lt;/sup&gt;" +
 *     final HtmlLabel html = new HtmlLabel( text );
 *     html.setStyleName( "Default.HtmlLabel" );
 *     column.add( html );
 * </pre>
 *
 * @author Rakesh 2008-03-22
 * @version $Id: HtmlLabel.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class HtmlLabel extends AbstractHtmlComponent
{
  private static final long serialVersionUID = 1l;

  /** Default constructor.  Create a new instance with empty text. */
  public HtmlLabel() {}

  /**
   * Create a new instance enclosing the specified HTML text.
   *
   * @param text The HTML text that is to be displayed in this component.
   */
  public HtmlLabel( final String text )
  {
    super( text );
  }
}
