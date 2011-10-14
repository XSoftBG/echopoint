package echopoint.externalevent;
/*
 * This file is part of the Echo Point Project.  This project is a collection
 * of Components that have extended the Echo Web Application Framework.
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
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
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */

import java.util.EventListener;

/**
 * The <code>ExternalEventListener</code> interface is used
 * to listen for ExternalEvents.
 *
 * @see echopoint.externalevent.ExternalEvent
 * @author Brad Baker <p>Modified by Mikael Soderman 2009-04-28</p>
 * @version $Id: ExternalEventListener.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public interface ExternalEventListener extends EventListener {

	/**
	 * This is called when an external event has been raised.
	 *
	 * @param externalEvent - contains the event parameters
	 *
	 * @see echopoint.externalevent.ExternalEvent
	 */
	public void externalEvent(ExternalEvent externalEvent);
}

