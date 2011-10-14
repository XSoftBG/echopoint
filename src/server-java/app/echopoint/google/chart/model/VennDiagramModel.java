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

/**
 * A simple model object that represents the data for a {@link
 * echopoint.google.chart.VennDiagram}.  This bean is purely for convenience since
 * the data may be set similar to regular charts.
 *
 * @author Rakesh Vidyadharan 2008-08-22
 * @version $Id: VennDiagramModel.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class VennDiagramModel implements Serializable
{
  private static final long serialVersionUID = 1l;

  /** The field that represents the value of circle A. */
  public final int circleA;

  /** The field that represents the value of circle B. */
  public final int circleB;

  /** The field that represents the value of circle C. */
  public final int circleC;

  /** The field that represents the intersection of circles A and B. */
  public final int intersectAB;

  /** The field that represents the intersection of circles A and C. */
  public final int intersectAC;

  /** The field that represents the intersection of circles B and C. */
  public final int intersectBC;

  /** The field that represents the intersection of circles A, B and C. */
  public final int intersectABC;

  /**
   * Create a new instance for a diagram that has no intersections.
   *
   * @param circleA The {@link #circleA} value to use.
   * @param circleB The {@link #circleB} value to use.
   * @param circleC The {@link #circleC} value to use.
   */
  public VennDiagramModel( final int circleA, final int circleB,
      final int circleC )
  {
    this( circleA, circleB, circleC, 0, 0, 0, 0 );
  }

  /**
   * Create a new instance for a diagram with the specified values.
   *
   * @param circleA The {@link #circleA} value to use.
   * @param circleB The {@link #circleB} value to use.
   * @param circleC The {@link #circleC} value to use.
   * @param intersectAB The {@link #intersectAB} value to use.
   * @param intersectAC The {@link #intersectAC} value to use.
   * @param intersectBC The {@link #intersectBC} value to use.
   * @param intersectABC The {@link #intersectABC} value to use.
   */
  public VennDiagramModel( final int circleA, final int circleB,
      final int circleC, final int intersectAB, final int intersectAC,
      final int intersectBC, final int intersectABC )
  {
    this.circleA = circleA;
    this.circleB = circleB;
    this.circleC = circleC;
    this.intersectAB = intersectAB;
    this.intersectAC = intersectAC;
    this.intersectBC = intersectBC;
    this.intersectABC = intersectABC;
  }

  /**
   * Accessor for property 'circleA'.
   *
   * @return Value for property 'circleA'.
   */
  public int getCircleA()
  {
    return circleA;
  }

  /**
   * Accessor for property 'circleB'.
   *
   * @return Value for property 'circleB'.
   */
  public int getCircleB()
  {
    return circleB;
  }

  /**
   * Accessor for property 'circleC'.
   *
   * @return Value for property 'circleC'.
   */
  public int getCircleC()
  {
    return circleC;
  }

  /**
   * Accessor for property 'intersectAB'.
   *
   * @return Value for property 'intersectAB'.
   */
  public int getIntersectAB()
  {
    return intersectAB;
  }

  /**
   * Accessor for property 'intersectAC'.
   *
   * @return Value for property 'intersectAC'.
   */
  public int getIntersectAC()
  {
    return intersectAC;
  }

  /**
   * Accessor for property 'intersectBC'.
   *
   * @return Value for property 'intersectBC'.
   */
  public int getIntersectBC()
  {
    return intersectBC;
  }

  /**
   * Accessor for property 'intersectABC'.
   *
   * @return Value for property 'intersectABC'.
   */
  public int getIntersectABC()
  {
    return intersectABC;
  }
}
