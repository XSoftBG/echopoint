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

package echopoint.google.chart;

import echopoint.google.chart.internal.AbstractChart;

/**
 * Component wrapper for a
 * <a href='http://code.google.com/apis/chart/#qrcodes'>QR codes</a>
 * provided by <a href='http://code.google.com/apis/chart/'>Google Chart
 * API</a>.
 *
 * <p>The following code shows sample use of this component:</p>
 * <pre>
 *   import echopoint.google.chart.QRCode;
 *
 *     ...
 *     QRCode chart = new QRCode();
 *     chart.setText( text );
 * </pre>
 *
 * @author Rakesh Vidyadharan 2008-08-28
 * @version $Id: QRCode.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class QRCode extends AbstractChart<Integer>
{
  private static final long serialVersionUID = 1l;

  /**
   * The property used to specify the text that is to be encoded as a QR code.
   */
  public static final String PROPERTY_TEXT = "text";

  /**
   * The property used to specify the output encoding for the QR code text.
   * This is optional and may be styled.  Google defaults to UTF-8.
   */
  public static final String PROPERTY_ENCODING = "encoding";

  /**
   * Return the {@link #PROPERTY_TEXT} property value.
   *
   * @return The text value to be encoded as a QR code.
   */
  public String getText()
  {
    return (String) get( PROPERTY_TEXT );
  }

  /**
   * Set the value of the {@link #PROPERTY_TEXT} property.
   *
   * @param text The value to set.
   */
  public void setText( final String text )
  {
    set( PROPERTY_TEXT, text );
  }

  /**
   * Return the {@link #PROPERTY_ENCODING} property value.
   *
   * @return The output encoding that is to be used.
   */
  public String getEncoding()
  {
    return (String) get( PROPERTY_ENCODING );
  }

  /**
   * Set the value of the {@link #PROPERTY_ENCODING} property.
   *
   * @param encoding The value to set.
   */
  public void setEncoding( final String encoding )
  {
    set( PROPERTY_ENCODING, encoding );
  }
}