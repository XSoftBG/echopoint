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
package echopoint.tucana;

import nextapp.echo.app.Border;
import nextapp.echo.app.Color;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;

/**
 * A custom progress bar component with default styles to use with the file
 * upload component.
 *
 * <p><b>Note:</b> Similar to the {@link echopoint.ProgressBar} super-class,
 * this component requires fixed size width and height.  Percentage based
 * values will lead to display issues with the progress bar.</p>
 *
 * <p><b>Note:</b> Development of this component was sponsored by <a
 * href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author Rakesh 2008-11-6
 * @version $Id: ProgressBar.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class ProgressBar extends echopoint.ProgressBar
{
  private static final long serialVersionUID = 1l;

  /**
   * The pattern to use to display the text message in the bar.  The following
   * encoded fields may be embedded within the specified text to be replaced
   * by the corresponding values:
   *
   * <ul>
   *   <li><code>#bytes#</code> - This encoded value will be replaced by
   *     the actual kilo bytes that have been transferred so far.</li>
   *   <li><code>#length#</code> - This encoded value will be replaced
   *     by actual size of the file being uploaded.</li>
   *   <li><code>#percent#</code> - This encoded value will be replaced
   *     by the upload completion ratio.</li>
   *   <li><code>#rate#</code> - This encoded value will be replaced
   *     by the rate at which kilo bytes are being uploaded per second.</li>
   *   <li><code>#time#</code> - This encoded value will be replaced
   *     by the estimated time (in seconds) for completion of the upload.</li>
   * </ul>
   *
   * The following is a sample pattern expressed using the encoded fields:
   * <code>Uploaded #bytes# of #length#Kb at #rate#...</code>
   *
   * This property may be styled.
   */
  public static final String PROPERTY_PATTERN = "pattern";

  /** Create a default styled progress bar. */
  public ProgressBar()
  {
    setBackground( new Color( 0xd6d5d4 ) );
    setForeground( new Color( 0xffffff ) );
    setBarBackground( new Color( 0x1a428a ) );
    setBorder( new Border( 1, Color.BLACK, Border.STYLE_INSET ) );
    setInsets( new Insets( new Extent( 1 ) ) );
  }

  /**
   * Return the value of the {@link #PROPERTY_PATTERN} property.
   *
   * @return The pattern used to display the text.
   */
  public String getPattern()
  {
    return (String) get( PROPERTY_PATTERN );
  }

  /**
   * Set the value of the {@link #PROPERTY_PATTERN} property.  See the
   * property notes for list of encoded values that may be specified in the
   * pattern.
   *
   * @param pattern The pattern to use to display the text.
   */
  public void setPattern( final String pattern )
  {
    set( PROPERTY_PATTERN, pattern );
  }
}
