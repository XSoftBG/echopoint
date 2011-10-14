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

import static echopoint.style.Background.BACKGROUND_KEY;
import static echopoint.style.Background.setBackground;
import static echopoint.style.DefaultFont.SIZE_KEY;
import static echopoint.style.DefaultFont.STYLE_KEY;
import static echopoint.style.DefaultFont.TYPEFACE_KEY;
import static echopoint.style.DefaultFont.setSize;
import static echopoint.style.DefaultFont.setStyle;
import static echopoint.style.DefaultFont.setTypeFace;

import javax.servlet.ServletException;

import nextapp.echo.webcontainer.WebContainerServlet;

/**
 * A servlet class that can be used to set up the default font properties
 * for the application using init parameters.  An example configuration of
 * the servlet would be as follows:
 *
 * <pre>
  &lt;servlet&gt;
    &lt;servlet-name&gt;servlet&lt;/servlet-name&gt;
    &lt;servlet-class&gt;echopoint.Servlet&lt;/servlet-class&gt;
    &lt;init-param&gt;
      &lt;param-name&gt;echopoint.style.DefaultFont.typeface&lt;/param-name&gt;
      &lt;param-value&gt;'Verdana, Times New Roman, Lucida Grande'&lt;/param-value&gt;
    &lt;/init-param&gt;
    &lt;init-param&gt;
      &lt;param-name&gt;echopoint.style.DefaultFont.style&lt;/param-name&gt;
      &lt;param-value&gt;PLAIN&lt;/param-value&gt;
    &lt;/init-param&gt;
    &lt;init-param&gt;
      &lt;param-name&gt;echopoint.style.DefaultFont.size&lt;/param-name&gt;
      &lt;param-value&gt;10pt&lt;/param-value&gt;
    &lt;/init-param&gt;
  &lt;/servlet&gt;
 * </pre>
 *
 * @author Rakesh 2009-05-13
 * @version $Id: Servlet.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public abstract class Servlet extends WebContainerServlet
{
  /**
   * Set up default properties configured as init parameters.
   *
   * {@inheritDoc}
   */
  @Override
  public void init() throws ServletException
  {
    super.init();
    configureFont();
  }

  /** Configure {@link echopoint.style.DefaultFont} properties. */
  protected void configureFont()
  {
    final String typeface = getInitParameter( TYPEFACE_KEY );
    if ( typeface != null ) setTypeFace( typeface );

    final String style = getInitParameter( STYLE_KEY );
    if ( style != null ) setStyle( style );

    final String size = getInitParameter( SIZE_KEY );
    if ( size != null ) setSize( size );

    final String background = getInitParameter( BACKGROUND_KEY );
    if ( background != null ) setBackground( background );
  }
}
