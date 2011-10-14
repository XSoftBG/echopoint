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

/**
 * Enumeration of values for setting {@link FileUploadSelector#PROPERTY_BUTTON_MODE}
 *
 * <p><b>Note:</b> Development of this component was sponsored by <a
 * href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author Rakesh 2008-11-9
 * @version $Id: ButtonMode.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public enum ButtonMode
{
  /**
   * Indicates that a regular &lt;input type='submit'&gt; type control is
   * to be displayed to submit the form.
   */
  submit,

  /**
   * Indicates that a custom image is to be displayed instead of a text submit
   * button.
   */
  image
}
