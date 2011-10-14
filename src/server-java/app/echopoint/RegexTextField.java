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

import java.util.EventListener;

import echopoint.event.ValidationEvent;
import echopoint.event.ValidationListener;
import echopoint.internal.TextField;
import nextapp.echo.app.Font;
import nextapp.echo.app.Alignment;
import nextapp.echo.app.Color;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



/**
 * A text field that enforces a user supplied regular expression on a key
 * press.  Allows creation of serial number field, character only field etc.
 *
 * <p>The following shows sample use of this class.</p>
 * <pre>
 *  import nextapp.echo.app.TextField;
 *  import echopoint.RegexTextField;
 *
 *    ...
 *    // Create a field that allow real numbers with two digit fractional part
 *    final TextField tf = new RegexTextField( "^[\\d]+[.]{0,1}[\\d]{1,2}$" );
 * </pre>
 *
 * <p><b>Note:</b> It is recommended that you test the regular expression
 * using a simple test html page in your browser of choice before you set
 * it for the component.  This is to ensure that the expression you specify
 * is valid and behaves the way you want it to work.</p>
 *
 * <pre>
 * &lt;html&gt;
 *   &lt;head&gt;
 *     &lt;title&gt;RegExp Test&lt;/title&gt;
 *     &lt;script type='text/javascript'&gt;
 *       function check( event )
 *       {
 *         event = (event) ? event : window.event;
 *         var charCode = (event.which) ? event.which : event.keyCode;
 *         var status = true;
 *
 *         if ( charCode &lt;= 31 )
 *         {
 *           status = true;
 *           return status;
 *         }
 *
 *         var regexString = "^[A-Z]{1,1}[a-z]*$";
 *         var regex = new RegExp( regexString );
 *         var input = document.getElementById( 'textField' );
 *         var value = input.value + String.fromCharCode( charCode );
 *         status = regex.test( value );
 *
 *         return status;
 *       }
 *     &lt;/script&gt;
 *   &lt;/head&gt;
 *   &lt;body&gt;
 *     &lt;input id='textField' type='text' onkeypress='return check(event)' /&gt;
 *   &lt;/body&gt;
 * &lt;/html&gt;
 * </pre>
 *
 * @author Rakesh 2009-03-08
 * @version $Id: RegexTextField.java,v 1.10 2011/10/14 10:05:50 perxi Exp $
 */
public class RegexTextField extends TextField
{
  private static final long serialVersionUID = 1l;
	private String regexPattern = "";    
  private Pattern pattern;

	public static final String INVALID_VALUE_EVENT = "expInvalid";
	public static final String VALID_VALUE_EVENT = "expValid";

  /**
   * The regular expression to specify.  Note that the string value is used
   * to create a new {@code RegExp} JavaScript object and hence the expression
   * must be escaped as you would when compiling a Java regex pattern.
   * This property may also be styled.
   */
  public static final String PROPERTY_REGEX = "regex";
  public static final String PROPERTY_INVALID_MSG_ORIENTATION = "invalid_msg_orientation";
  public static final String PROPERTY_INVALID_MSG = "invalid_msg";
  public static final String PROPERTY_INVALID_MSG_BACKGROUND = "invalid_msg_background";  
  public static final String PROPERTY_INVALID_MSG_FOREGROUND = "invalid_msg_foreground";  
  public static final String PROPERTY_INVALID_BACKGROUND = "invalid_background";  
  public static final String PROPERTY_INVALID_FOREGROUND = "invalid_foreground";  
  public static final String PROPERTY_INVALID_MSG_ALIGNMENT = "invalid_msg_alignment";  
  public static final String PROPERTY_INVALID_MSG_WIDTH = "invalid_msg_width";
  public static final String PROPERTY_INVALID_MSG_FONT = "invalid_msg_font";
  public static final String PROPERTY_FILTER = "filter";
  public static final String PROPERTY_ISVALID = "isValid";
  public static final String PROPERTY_FILTER_MSG = "invalid_filter_msg";

  public static final String VALIDATION_LISTENERS_CHANGED_PROPERTY = "validationListeners";
  /** Default constructor.  Not particularly useful. */
  public RegexTextField() {	}

  /**
   * Create a new text field with the specified regex pattern.
   *
   * @param regex The regular expression to use.
   */
  public RegexTextField( final String regex )
  {
    setRegex( regex );
  }

  /**
   * Create a new text field with the specified regex pattern.
   *
   * @param regex The regular expression to use.
	 * @param filter_regex The regular expression for the filter.
   */
  public RegexTextField( final String regex, final String filter_regex )
  {
    setRegex( regex );
		setFilter( filter_regex );
  }


	public void addValidationListener(ValidationListener l){
			getEventListenerList().addListener(ValidationListener.class, l);
			// Notification of empty listener changes is provided due to 
			// existence of hasEmptyListeners() method. 
			firePropertyChange(VALIDATION_LISTENERS_CHANGED_PROPERTY, null, l);
	}

	/**
		* Fires a expValid event to all listeners.
		*/
	private void fireInvalidValueEvent() {
			if (!hasEventListenerList()) {
					return;
			}
			EventListener[] listeners = getEventListenerList().getListeners(ValidationListener.class);
			for (int i = 0; i < listeners.length; ++i) {
					((ValidationListener) listeners[i]).expInvalid(new ValidationEvent(this));
			}
	}

	/**
		* Fires a expInvalid event to all listeners.
		*/
	private void fireValidValueEvent() {
			if (!hasEventListenerList()) {
					return;
			}
			EventListener[] listeners = getEventListenerList().getListeners(ValidationListener.class);
			for (int i = 0; i < listeners.length; ++i) {
					((ValidationListener) listeners[i]).expValid(new ValidationEvent(this));
			}
	}

	/**
		* Removes an <code>ValidationListener</code> from the <code>RegexTextField</code>.
		* 
		* @param l the <code>ValidationListener</code> to remove
		*/
	public void removeValidationListener(ValidationListener l) {
			if (!hasEventListenerList()) {
					return;
			}
			getEventListenerList().removeListener(ValidationListener.class, l);
			// Notification of validation listener changes is provided due to 
			// existence of hasValidationsListeners() method. 
			firePropertyChange(VALIDATION_LISTENERS_CHANGED_PROPERTY, l, null);
	}    

	/**
		* Determines if any <code>ValidationListener</code>s are registered.
		* 
		* @return true if any validation listeners are registered
		*/
	public boolean hasValidationListeners() {
			return hasEventListenerList() && getEventListenerList().getListenerCount(ValidationListener.class) != 0;
	}
    

	/**
		* @see nextapp.echo.app.Component#processInput(java.lang.String, java.lang.Object)
		*/
	public void processInput(String inputName, Object inputValue) {			
			super.processInput(inputName, inputValue);
			if (VALID_VALUE_EVENT.equals(inputName)) {
					fireValidValueEvent();
			} else if (INVALID_VALUE_EVENT.equals(inputName)) {
					fireInvalidValueEvent();
			}
	}

  /**
   * Return the regular expression in use for the field ({@link
   * #PROPERTY_REGEX}).
   *
   * @return The regular expression in use.
   */
  public String getRegex()
  {
    return (String) get( PROPERTY_REGEX );
  }

  /**
   * Set the regular expression pattern ({@link #PROPERTY_REGEX}) to use to
   * restrict input into this * field.  This value may also be styled.
   *
   * @param regex The regular expression to use.
   */
  public void setRegex( final String regex )
  {
    final String old_regex = getRegex();
		regexPattern = regex;
    pattern = Pattern.compile(regex);
    set( PROPERTY_REGEX, regex );
    firePropertyChange(PROPERTY_REGEX, old_regex, regex);
  }

  /**
   * Return the regular expression in use for the filter ({@link
   * #PROPERTY_FILTER}).
   *
   * @return The filter in use.
   */
  public String getFilter()
  {
    return (String) get( PROPERTY_FILTER );
  }

  /**
   * Set the regular expression filter pattern ({@link #PROPERTY_FILTER}) to use to
   * restrict input into this * field.  This value may also be styled.
   *
   * @param regex The regular expression filter to use.
   */
  public void setFilter( final String filter )
  {
    set( PROPERTY_FILTER, filter );
  }

	public boolean isValid() 
	{    
		String text = getText();
		
		// No RegEx pattern to test against, defined as valid
		if( regexPattern.isEmpty() )
				return true;
		
		// Must use 'find' and not 'matches' so that the Java validation
		//   behavior is as close to the JavaScript valication behavior
		//   as possible. Otherwise, we'd have to go to the client.
		
		Matcher matcher = pattern.matcher(text);
		return matcher.find();
  }

  public Alignment getInvalidMsgOrientation()
  {
    return (Alignment) get( PROPERTY_INVALID_MSG_ORIENTATION );
  }
  
  public String getInvalidMsg()
  {
    return (String) get( PROPERTY_INVALID_MSG );
  }

  public String getFilterMsg()
  {
    return (String) get( PROPERTY_FILTER_MSG);
  }

  public Color getInvalidMsgBackground()
  {  
    return (Color) get( PROPERTY_INVALID_MSG_BACKGROUND );
  }
 
  public Color getInvalidMsgForeground()
  {
    return (Color) get( PROPERTY_INVALID_MSG_FOREGROUND );
  }

  public Color getInvalidBackground()
  {
    return (Color) get( PROPERTY_INVALID_BACKGROUND );
  }

  public Color gettInvalidForeground()
  {
    return (Color) get( PROPERTY_INVALID_FOREGROUND );
  }

  public Alignment getInvalidMsgAlignment()
  {
    return (Alignment) get( PROPERTY_INVALID_MSG_ALIGNMENT );
  }

  public int getInvalidMsgWidth()
  {
    Integer value = (Integer) get(PROPERTY_INVALID_MSG_WIDTH);
    return value == null ? -1 : value.intValue();
  }

  public Font getInvalidMsgFont()
  {
    return (Font) get( PROPERTY_INVALID_MSG_FONT );
  }

  public void setInvalidMsgOrientation( Alignment orientation )
  {
    set( PROPERTY_INVALID_MSG_ORIENTATION, orientation  );
  }
  
  public void setInvalidMsg( String invalid_msg )
  {
    set( PROPERTY_INVALID_MSG, invalid_msg);
  }

	public void setFilterMsg( String filter_msg )
	{
    set( PROPERTY_FILTER_MSG, filter_msg);
	}

  public void setInvalidMsgBackground( Color inv_msg_background )
  {  
    set( PROPERTY_INVALID_MSG_BACKGROUND, inv_msg_background );
  }

  public void setInvalidMsgForeground( Color inv_msg_foreground )
  {
    set( PROPERTY_INVALID_MSG_FOREGROUND, inv_msg_foreground );
  }

  public void setInvalidBackground( Color inv_background )
  {
    set( PROPERTY_INVALID_BACKGROUND, inv_background );
  }

  public void setInvalidForeground( Color inv_foreground )
  {
    set( PROPERTY_INVALID_FOREGROUND, inv_foreground );
  }

  public void setInvalidMsgAlignment( Alignment align )
  {
    set( PROPERTY_INVALID_MSG_ALIGNMENT, align );
  }

  public void setInvalidMsgWidth( int width )
  {
    set( PROPERTY_INVALID_MSG_WIDTH, width );
  }

  public void setInvalidMsgFont( Font font )
  {
    set( PROPERTY_INVALID_MSG_FONT, font );
  }

}
