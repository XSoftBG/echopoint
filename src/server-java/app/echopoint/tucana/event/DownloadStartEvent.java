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
 * A download event that indicates that the client has started a download
 * process.
 *
 * @author Rakesh 2008-11-11
 * @version $Id: DownloadStartEvent.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class DownloadStartEvent extends DownloadEvent
{
  private static final long serialVersionUID = 1l;

  /**
   * Construct a new download event with the specified parameters.
   *
   * @param source The object on which the Event initially occurred.
   * @param provider The download provider with information about the
   *   content that was downloaded.
   * @throws IllegalArgumentException if source is null.
   */
  public DownloadStartEvent( final DownloadCommand source,
      final DownloadProvider provider ) throws IllegalArgumentException
  {
    super( source, provider.getFileName(), provider.getSize(),
        provider.getContentType() );
  }
}
