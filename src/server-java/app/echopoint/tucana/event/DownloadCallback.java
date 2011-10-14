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
package echopoint.tucana.event;

import java.io.Serializable;
import java.util.EventListener;

/**
 * An interface that defines the methods that are notified as a client content
 * download command progresses.
 *
 * @author Rakesh 2008-11-11
 * @version $Id: DownloadCallback.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public interface DownloadCallback extends EventListener, Serializable
{
  /**
   * Indicate that a content download process has been started.
   *
   * @param event The download event object.
   */
  void downloadStarted( final DownloadStartEvent event );

  /**
   * Indicate that a content download process has ended successfully.
   *
   * @param event The download event object.
   */
  void downloadFinished( final DownloadFinishEvent event );

  /**
   * Indicate that a content download process failed, usually due to reasons
   * other than client cancellation.
   *
   * @param event The download fail event object.
   */
  void downloadFailed( final DownloadFailEvent event );
}
