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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * The model object for use with {@link echopoint.google.chart.ScatterPlot}s.
 *
 * @author Rakesh Vidyadharan 2008-08-23
 * @version $Id: ScatterPlotData.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class ScatterPlotData<N extends Number> extends ChartData<N>
{
  private static final long serialVersionUID = 1l;

  /**
   * The collection of values that determines the sizes of the points that
   * are displayed in the scatter plot.
   */
  private Collection<Integer> size = new ArrayList<Integer>();

  /**
   * Accessor for property 'size'.  Returns a read-only view of the data.
   *
   * @return Value for property 'size'.
   */
  public Collection<Integer> getSize()
  {
    return Collections.unmodifiableCollection( size );
  }

  /**
   * Mutator for property 'size'.  Resets the contents of the collection.
   *
   * @param size Value to set for property 'size'.
   */
  public void setSize( final Collection<Integer> size )
  {
    this.size.clear();
    this.size.addAll( size );
  }
}
