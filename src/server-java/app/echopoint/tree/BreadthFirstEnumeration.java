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

package echopoint.tree;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * An enumeration that traverses the subtree rooted at
 * a node in breadth-first order. The first node returned by the
 * enumeration's {@code nextElement()} method is the node for which the
 * enumeration was created.
 *
 * @author Brad Baker, Rakesh Vidyadharan 2009-05-29
 * @version $Id: BreadthFirstEnumeration.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public final class BreadthFirstEnumeration implements Enumeration<TreeNode>
{
  protected Queue<Enumeration<TreeNode>> queue = new LinkedList<Enumeration<TreeNode>>();

  /**
   * Create a new enumeration for the specified tree node.
   *
   * @param rootNode The node for which the enumeration is created.
   */
  public BreadthFirstEnumeration( final TreeNode rootNode )
  {
    final Vector<TreeNode> v = new Vector<TreeNode>( 1 );
    v.addElement( rootNode );
    queue.offer( v.elements() );
  }

  /** {@inheritDoc} */
  public boolean hasMoreElements()
  {
    return ( ! queue.isEmpty() ) && queue.peek().hasMoreElements();
  }

  /** {@inheritDoc} */
  public TreeNode nextElement()
  {
    final Enumeration<TreeNode> enumer = queue.poll();
    final TreeNode node = enumer.nextElement();
    final Enumeration<TreeNode> children = node.children();

    if ( ! enumer.hasMoreElements() )
    {
      queue.poll();
    }

    if ( children.hasMoreElements() )
    {
      queue.offer( children );
    }

    return node;
  }
}
