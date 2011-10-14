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

package echopoint.google.chart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * A model object used to represent the title of a chart.
 *
 * @author Rakesh 2008-8-10
 * @version $Id: Title.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
@XStreamAlias( "Title" )
public class Title implements Serializable
{
  private static final long serialVersionUID = 1l;

  /**
   * A collection used to store the lines of text that comprise the title.
   */
  private Collection<String> title = new ArrayList<String>();

  /** Default constructor. */
  public Title() {}

  /**
   * Create a new instance with the single line title specified.
   *
   * @see #add
   * @param title The single line title to use.
   */
  public Title( final String title )
  {
    add( title );
  }

  /**
   * Add the specified line of text to the title.
   *
   * @param line The line of text to add.
   */
  public void add( final String line )
  {
    title.add( line );
  }
}
