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

import echopoint.internal.TextField;

/**
 * The {@code KeystrokeTextField} allows you to be notified about each keystroke
 * in the text field component.
 * This can for example be used to do a incremental filetring of a server
 * database and then display the matching records as you type.
 *
 * Please use the following component with care, it creates a request for every
 * keystroke per default. You can configure a keystrokeDelay which synchronizes
 * only after the specified delay. So someone who is typing rapidly doesn't
 * create lots of requests, instead the synchronization is triggered only when
 * the user stops typing for the specified amount of time (=keystrokeDelay).
 *
 * @author André Schild, 299-06-05, Based on code posted in the Echo wiki
 * @version $Id: KeystrokeTextField.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class KeystrokeTextField extends TextField
{
  private static final long serialVersionUID = 1l;

  /**
   */
  public static final String PROPERTY_KEYSTROKE_DELAY = "keystrokeDelay";

  /**
   * Default constructor.
   * Uses a keystroke delay of 0ms. Not a good idea in most cases. Instead
   * use the constructor with a keystrokeDelay of for example 250ms
   *
   */
  public KeystrokeTextField() {}

  /**
   * Create a new text field with the specified keystroke delay
   *
   * @param keystrokeDelay The keystroke delay to be used in ms
   */
  public KeystrokeTextField( final int keystrokeDelay )
  {
    setKeystrokeDelay( keystrokeDelay );
  }

  /**
   * Set the keystroke delay
   *
   * @param keystrokeDelay The keystroke delay to be used in ms
   */
  public void setKeystrokeDelay( final int keystrokeDelay )
  {
    set( PROPERTY_KEYSTROKE_DELAY, keystrokeDelay );
  }
}

