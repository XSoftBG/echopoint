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
 * A download event that indicates that a download operation by the client
 * has ended successfully.
 *
 * @author Rakesh 2008-11-11
 * @version $Id: DownloadFinishEvent.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class DownloadFinishEvent extends DownloadEvent
{
  private static final long serialVersionUID = 1l;

  /**
   * Create a new download event with the specified  parameters.
   * @param download The download command that has completed.
   * @param provider The provider object that contains details about the
   *   content that was downloaded.
   */
  public DownloadFinishEvent( final DownloadCommand download,
      final DownloadProvider provider )
  {
    super( download, provider.getFileName(), provider.getSize(),
        provider.getContentType() );
  }
}
