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

import echopoint.tucana.DownloadCommand;
import echopoint.tucana.DownloadProvider;

/**
 * A download event that indicates that a download request from the client
 * failed (usually not due to user interaction).
 *
 * @author Rakesh 2008-11-11
 * @version $Id: DownloadFailEvent.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class DownloadFailEvent extends DownloadEvent
{
  private static final long serialVersionUID = 1l;

  /** The exception that was generated that indicates the cause of the failure. */
  private final Exception exception;

  /**
   * Constructs a new download event with the specified content attributes.
   *
   * @param source The object on which the Event initially occurred.
   * @param provider The download provider that contains information about the
   *   content that was downloaded.
   * @param exception The cause of the failure.
   * @throws IllegalArgumentException if source is null.
   */
  public DownloadFailEvent( final DownloadCommand source,
      final DownloadProvider provider, final Exception exception )
    throws IllegalArgumentException
  {
    super( source, provider.getFileName(), provider.getSize(),
        provider.getContentDisposition() );
    this.exception = exception;
  }

  /**
   * Accessor for property 'exception'.
   *
   * @return Value for property 'exception'.
   */
  public Exception getException()
  {
    return exception;
  }
}
