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

package echopoint.internal;

import echopoint.event.ContentChangedEvent;
import echopoint.event.ContentChangedListener;
import java.util.EventListener;
import nextapp.echo.app.Component;

/**
 * An abstract super-class for components that display raw HTML text.
 *
 * @author Rakesh 2008-03-22
 * @version $Id: AbstractHtmlComponent.java,v 1.3 2010-11-24 13:12:32 yozov Exp $
 */
public abstract class AbstractHtmlComponent extends AbstractContainer
{
  private static final long serialVersionUID = 1l;

  /** The property for specifying the content displayed in the component. */
  public static final String PROPERTY_TEXT = "text";

  /** The property for specifying the target for anchor tags in the content. */
  public static final String PROPERTY_TARGET = "target";

  /** something */
  public static final String CONTENT_CHANGED_EVENT              = "content_changed";
  public static final String PROCESS_FORMS_PROPERTY             = "process_forms";
  public static final String CONTENT_CHANGED_LISTENERS_PROPERTY = "content_changed_listeners";

  /** Default constructor.  Create a new instance with empty text. */
  public AbstractHtmlComponent()
  { 
    this( "" );
    set( PROCESS_FORMS_PROPERTY, false);
  }

  /**
   * Create a new instance enclosing the specified HTML text.
   *
   * @param text The HTML text that is to be displayed in this component.
   */
  public AbstractHtmlComponent( final String text )
  {
    setText( text );
    set( PROCESS_FORMS_PROPERTY, false );
  }

  /**
   * Over-ridden to return <code>false</code> always as this component does
   * not support child components.
   *
   * {@inheritDoc}
   */
  @Override
  public boolean isValidChild( final Component component )
  {
    return false;
  }

  /**
   * A convenience method to append the given string to the end of the
   * existing content of this component.  This method is safe to use even
   * if no <code>text</code> has been associated with this component.
   *
   * @see #getText
   * @see #setText
   * @param text The additional text to add.  Note that no line breaks etc.
   *   are added to the existing content.
   */
  public void append( final String text )
  {
    String content = getText();
    if ( content == null ) content = "";
    setText( content + text );
  }

  /**
   * Return the HTML text displayed within this component.
   *
   * @return The HTML text.
   */
  public String getText()
  {
    return (String) get( PROPERTY_TEXT );
  }

  /**
   * Set the value of the HTML displayed within this component.
   *
   * @param text The value to set.
   */
  public void setText( final String text )
  {
    set( PROPERTY_TEXT, text );
  }

  /**
   * Return the target attribute for anchor tags embedded in the content
   * displayed in the component.
   *
   * @return The target value.
   */
  public String getTarget()
  {
    return (String) get( PROPERTY_TARGET );
  }

  /**
   * Set the value for the target attribute to be applied to all anchor tags
   * embedded in the content displayed in the component.  Note that the
   * implementation only adds a target attribute if no attribute value exists
   * in the anchor tags.
   *
   * @param target The value to set.
   */
  public void setTarget( final String target )
  {
    set( PROPERTY_TARGET, target );
  }

  /**
   * something
   */
  public void processForms()
  {
    set( PROCESS_FORMS_PROPERTY, true );
  }
  
  @Override
  public void processInput(String inputName, Object inputValue)
  {
    //System.out.println("processInput: " + inputName + " : " + inputValue);
    
    super.processInput(inputName, inputValue);
    
    if (CONTENT_CHANGED_EVENT.equals(inputName))
      fireContentChangedEvent();
    else if(PROPERTY_TEXT.equals(inputName))
      setText((String) inputValue);
	}

  public void addContentChangedListener(ContentChangedListener l)
  {
			getEventListenerList().addListener(ContentChangedListener.class, l);
			// Notification of empty listener changes is provided due to
			// existence of hasEmptyListeners() method.
			firePropertyChange(CONTENT_CHANGED_LISTENERS_PROPERTY, null, l);
  }

  public void removeContentChangedListener(ContentChangedListener l) {
			if (!hasEventListenerList()) {
					return;
			}
			getEventListenerList().removeListener(ContentChangedListener.class, l);
			// Notification of validation listener changes is provided due to
			// existence of hasValidationsListeners() method.
			firePropertyChange(CONTENT_CHANGED_LISTENERS_PROPERTY, l, null);
	}

  private void fireContentChangedEvent()
  {
    if (!hasEventListenerList())
      return;

    EventListener[] listeners = getEventListenerList().getListeners(ContentChangedListener.class);
    for (int i = 0; i < listeners.length; ++i)
        ((ContentChangedListener) listeners[i]).contentChanged((new ContentChangedEvent(this)));
	}

  /**
		* Determines if any <code>ValidationListener</code>s are registered.
		*
		* @return true if any validation listeners are registered
		*/
	public boolean hasContentChangedListeners() {
			return hasEventListenerList() && getEventListenerList().getListenerCount(ContentChangedListener.class) != 0;
	}
}
