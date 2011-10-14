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

package echopoint.google.chart;

import java.util.ArrayList;

import echopoint.google.chart.internal.SimpleChart;
import echopoint.google.chart.model.ChartData;
import echopoint.google.chart.model.VennDiagramModel;

/**
 * Component wrapper for a
 * <a href='http://code.google.com/apis/chart/#venn'>Venn Diagram</a>
 * provided by <a href='http://code.google.com/apis/chart/'>Google Chart
 * API</a>.
 *
 * <p>The following code shows sample use of this component:</p>
 * <pre>
 *   import echopoint.google.chart.VennDiagram;
 *
 *     ...
 *     final VennDiagram chart = new VennDiagram();
 *     final VennDiagramModel model = new VennDiagramModel( 100, 80, 60, 30, 30, 30, 10 );
 *     chart.setData( model );
 * </pre>
 *
 * @author Rakesh Vidyadharan 2008-08-22
 * @version $Id: VennDiagram.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class VennDiagram extends SimpleChart<Integer>
{
  private static final long serialVersionUID = 1l;

  /**
   * Set the data for the venn diagram using the custom model object.
   *
   * @see #setData( ChartData )
   * @param model The model object to use to set the data for the diagram.
   */
  public void setData( final VennDiagramModel model )
  {
    final ArrayList<Integer> list = new ArrayList<Integer>( 7 );
    list.add( 0, model.circleA );
    list.add( 1, model.circleB );
    list.add( 2, model.circleC );
    list.add( 3, model.intersectAB );
    list.add( 4, model.intersectAC );
    list.add( 5, model.intersectBC );
    list.add( 6, model.intersectABC );

    final ChartData<Integer> data = new ChartData<Integer>();
    data.setXdata( list );
    setData( data );
  }
}