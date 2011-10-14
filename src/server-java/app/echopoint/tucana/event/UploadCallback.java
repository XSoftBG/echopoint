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
 * An interface that represents a call back handler that will be notified when
 * an upload event completes or fails.

 * <p><b>Note:</b> Development of this component was sponsored by <a
 * href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author jvolkman (Echo2), Rakesh 2008-11-2
 * @version $Id: UploadCallback.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public interface UploadCallback extends EventListener, Serializable
{
  /**
   * Indicates a file upload has been started.
   *
   * @param event the event
   */
  void uploadStarted( final UploadStartEvent event );

  /**
   * Indicates a file upload has been canceled.
   *
   * @param event the event
   */
  void uploadCancelled( final UploadCancelEvent event );

  /**
   * Indicates that a file upload was rejected by the server since the client
   * attempted to send restricted content.
   *
   * @param event The event that was generated.
   */
  void uploadDisallowed( final InvalidContentTypeEvent event );

  /**
   * Indicates a file upload has progressed.
   *
   * @param event the event
   */
  void uploadProgressed( final UploadProgressEvent event );

  /**
   * Call back method invoked once an upload event completes.
   *
   * @param event The event that has completed.
   */
  void uploadSucceeded( final UploadFinishEvent event );

  /**
   * Call back method when an upload event fails.
   *
   * @param event The event that has failed.
   */
  void uploadFailed( final UploadFailEvent event );

  /**
   * A convenience method to return the last event received by the
   * callback handler.  Can be used to gain access to the input stream
   * (provided it has not been closed).
   *
   * @return The upload event that was last processed by the handler.
   */
  UploadEvent getEvent();
}
