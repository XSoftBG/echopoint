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

/**
 * An enumeration that traverses the subtree rooted at a node in postorder.
 * The first node returned by the enumeration's {@link #nextElement} method is
 * the leftmost leaf. This is the same as a depth-first traversal.
 *
 * @author Brad Baker, Rakesh Vidyadharan 2009-05-29
 * @version $Id: PostorderEnumeration.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 * @since 3.0.0b3
 */
public final class PostorderEnumeration implements Enumeration<TreeNode>
{
  protected TreeNode root;

  protected Enumeration<TreeNode> children;

  protected Enumeration<TreeNode> subtree;

  /**
   * Create a new enumeration for the specified node.
   *
   * @param rootNode The node for which the enumeration is to be created.
   */
  public PostorderEnumeration( final TreeNode rootNode )
  {
    super();
    root = rootNode;
    children = root.children();
    subtree = DefaultMutableTreeNode.EMPTY_ENUMERATION;
  }

  /** {@inheritDoc} */
  public boolean hasMoreElements()
  {
    return root != null;
  }

  /** {@inheritDoc} */
  public TreeNode nextElement()
  {
    TreeNode retval;

    if ( subtree.hasMoreElements() )
    {
      retval = subtree.nextElement();
    }
    else if ( children.hasMoreElements() )
    {
      subtree = new PostorderEnumeration( children.nextElement() );
      retval = subtree.nextElement();
    }
    else
    {
      retval = root;
      root = null;
    }

    return retval;
  }
}
