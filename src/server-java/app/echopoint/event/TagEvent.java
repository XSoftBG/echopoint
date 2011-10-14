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

import nextapp.echo.app.event.ActionEvent;
import echopoint.TagCloud;
import echopoint.model.Tag;

/**
 * An action event that is raised when a user clicks on a {@link
 * echopoint.model.Tag} in a {@link echopoint.TagCloud} component.
 *
 * @author Rakesh 2009-02-19
 * @version $Id: TagEvent.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class TagEvent extends ActionEvent
{
  private static final long serialVersionUID = 1l;

  /** The tag that was clicked leading to the event being raised. */
  private final Tag tag;

  /**
   * @param source The object from which the event originated
   * @param command The action command string describing the action
   * @param tag The tag that was selected.
   */
  public TagEvent( final TagCloud source, final String command, final Tag tag )
  {
    super( source, command );
    this.tag = tag;
  }

  /**
   * Return the tag that was clicked to generate the action event.
   *
   * @return The tag that was selected.
   */
  public Tag getTag()
  {
    return tag;
  }

  /**
   * Over-ridden to return the properly type-cast component source from
   * which the event was triggered.
   *
   * @return The parent tag cloud component.
   */
  @Override
  public TagCloud getSource()
  {
    return (TagCloud) super.getSource();
  }
}
