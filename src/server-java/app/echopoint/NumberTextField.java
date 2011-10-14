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

/**
 * A simple extension of {@link RegexTextField} that allows only
 * numeric characters and one period ({@code .}) to be entered.  The  precision
 * (number of fractional digits may be controlled using the {@link
 * #setPrecision} method.
 *
 * <p>The following shows sample use of this component:</p>
 * <pre>
 *  import nextapp.echo.app.TextField;
 *  import echopoint.NumberTextField;
 *
 *    ...
 *    final TextField tf = new NumberTextField( 2 );
 *    grid.add( tf );
 * </pre>
 *
 * @author Rakesh 2009-03-07
 * @version $Id: NumberTextField.java,v 1.3 2011/10/14 10:05:45 perxi Exp $
 */
public class NumberTextField extends RegexTextField
{
  private static final long serialVersionUID = 1L;

  public static final String PROPERTY_PRECISION = "precision";
	public static final String PROPERTY_NEGATIVE = "negative";

  /** Default constructor.  No actions required. */
  public NumberTextField() {}

  /**
   * Create a new instance with the specified precision value.
   *
   * @param precision The maximum number of fractional digits allowed.
   */
  public NumberTextField( final int precision )
  {
    setPrecision( precision );
  }

  /**
   * Return the value of the {@link #PROPERTY_PRECISION} property.  This
   * indicates the number of fractional digits allowed in the number.
   *
   * @return The precision or {@code -1} if not set.
   */
  public int getPrecision()
  {
    int precision = -1;

    final Object value = get( PROPERTY_PRECISION );
    if ( value != null )
    {
      precision = (Integer) value;
    }

    return precision;
  }

  /**
   * Return the value of the {@link #PROPERTY_PRECISION} property.  This
   * indicates the number of fractional digits allowed in the number.
   *
   * @return The precision or {@code -1} if not set.
   */
  public Boolean getNegative()
  {
    return (Boolean) get( PROPERTY_NEGATIVE );
  }

  /**
   * Set the value of the {@link #PROPERTY_PRECISION} property.
   *
   * @param precision The maximum number of fractional digits.
   */
  public void setPrecision( final int precision )
  {
    final int old_prec  = getPrecision();
    set( PROPERTY_PRECISION, precision );
    firePropertyChange(PROPERTY_REGEX, old_prec, precision);
  }

  /**
   * Over-ridden to do nothing.  This component does not allow custom
   * regular expressions.
   *
   * @param regex The regular expression to use.
   */
  @Override
  public void setRegex( final String regex )
  {
    // No-op
  }

	public void setNegative( Boolean negative )
	{
    final Boolean old_negative = getNegative();
		set( PROPERTY_NEGATIVE, negative );
    firePropertyChange(PROPERTY_NEGATIVE, old_negative, negative);
	}
}
