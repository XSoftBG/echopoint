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

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * An enumeration that follows the path from {@code ancestor} to the node.
 * The enumeration's {@code nextElement()} method first returns
 * {@code ancestor}, then the child of {@code ancestor} that is an ancestor
 * of the node, and so on, and finally returns this node. Creation of the
 * enumeration is O(m) where m is the number of nodes between this node and
 * {@code ancestor}, inclusive. Each {@code nextElement()} message is O(1).
 *
 * @author Brad Baker, Rakesh Vidyadharan 2009-05-29
 * @version $Id: PathBetweenNodesEnumeration.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 * @since 3.0.0b3
 */
public final class PathBetweenNodesEnumeration
    implements Enumeration<TreeNode>, Serializable
{
  private static final long serialVersionUID = 1l;

  protected Stack<TreeNode> stack = new Stack<TreeNode>();

  /**
   * Create a new enumeration from the specified ancestor the destination.
   *
   * @param ancestor The start of the enumeration.
   * @param descendant The destination of the enumeration.
   */
  public PathBetweenNodesEnumeration( final TreeNode ancestor,
      final TreeNode descendant )
  {
    if ( ancestor == null || descendant == null )
    {
      throw new IllegalArgumentException( "argument is null" );
    }

    stack.push( descendant );
    TreeNode current = descendant;

    while ( current != ancestor )
    {
      current = current.getParent();

      if ( current == null && ( descendant != ancestor ) )
      {
        throw new IllegalArgumentException( "node " + ancestor
            + " is not an ancestor of " + descendant );
      }

      stack.push( current );
    }
  }

  /** {@inheritDoc} */
  public boolean hasMoreElements()
  {
    return stack.size() > 0;
  }

  /** {@inheritDoc} */
  public TreeNode nextElement()
  {
    try
    {
      return stack.pop();
    }
    catch ( final EmptyStackException e )
    {
      throw new NoSuchElementException( "No more elements" );
    }
  }
}
