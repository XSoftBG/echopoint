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
 * Enumeration of status values for the state of the upload progress.
 *
 * <p><b>Note:</b> Development of this component was sponsored by <a
 * href='http://tcnbroadcasting.com/index.jsp' target='_top'>TCN
 * Broadcasting</a>.  We are grateful for their support and sponsorship.</p>
 *
 * @author Rakesh 2008-11-09
 * @version $Id: Status.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public enum Status
{
  /** Indicator that a file upload/download has finished successfully. */
  completed,

  /** Indicator that file upload/download was cancelled. */
  cancelled,

  /** Indicator the file upload/download was disallowed by the server. */
  disallowed,

  /**
   * Indicator that a file upload/download failed due to reasons other than
   * user cancellation.
   */
  failed,

  /** Indicator that a file upload/download is in progress. */
  inprogress
}
