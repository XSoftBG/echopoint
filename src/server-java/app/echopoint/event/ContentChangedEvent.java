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

package echopoint.event;

import java.util.EventObject;

/**
 * An event which occures when the value of a RegexTextField become valid or not.
 */
public class ContentChangedEvent extends EventObject {
    
    /** Serial Version UID. */
    private static final long serialVersionUID = 20101123L;

    /**
     * Creates a new <code>EventEvent</code>.
     * 
     * @param source the object from which the event originated
     */
    public ContentChangedEvent(Object source) { super(source); }
}
