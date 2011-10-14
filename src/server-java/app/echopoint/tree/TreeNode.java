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
import java.util.Enumeration;

/**
 * The design paradigm and class name used within have been taken directly
 * from the {@link javax.swing} package has been retro-fitted to work with the
 * NextApp Echo web framework.
 *
 * <p>This file was made part of the EchoPoint project on the 25/07/2002.</p>
 *
 * @author Brad Baker, Rakesh 2009-05-29
 * @version $Id: TreeNode.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 * @since 3.0.0b3
 */
public interface TreeNode extends Serializable
{
  /** @return The children of the reciever as a enumeration. */
  Enumeration<TreeNode> children();

  /** @return The action command string associated with this node */
  String getActionCommand();

  /** @return {@code true} if the receiver allows children. */
  boolean getAllowsChildren();

  /**
   * Returns the child {@code TreeNode} at index {@code childIndex}.
   *
   * @param childIndex The index into the children at which the child exists.
   * @return The child tree node.
   */
  TreeNode getChildAt( final int childIndex );

  /**
   * @return The number of children {@code TreeNode}s the receiver contains.
   */
  int getChildCount();

  /**
   * Returns the index of {@code node} in the receivers children. If the
   * receiver does not contain {@code node}, -1 will be returned.
   *
   * @param node The node whose index is to be returned.
   * @return The index in the children collection for the node.
   */
  int getIndex( final TreeNode node );

  /** @return The parent {@code TreeNode} of the receiver. */
  TreeNode getParent();

  /** @return {@code true} if the receiver is a leaf. */
  boolean isLeaf();
}
