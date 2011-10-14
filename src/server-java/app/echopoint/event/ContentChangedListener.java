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

import java.io.Serializable;
import java.util.EventListener;

/**
 * The listener interface for receiving Validation events.
 */
public interface ContentChangedListener extends EventListener, Serializable {
    
    /**
     * Invoked when an action occurs.
     *
     * @param e the fired <code>ValidationEvent</code>
     */
    public void contentChanged(ContentChangedEvent e);
}
