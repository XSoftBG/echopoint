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

/**
 * Defines the requirements for a tree node object that can change -- by
 * adding or removing child nodes, or by changing the contents of a user
 * object stored in the node.
 *
 * @param <M> The user object backing the node.
 * @author Brad Baker, Rakesh Vidyadharan 2009-05-29
 * @version $Id: MutableTreeNode.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 * @since 3.0.0b3
 */
public interface MutableTreeNode<M> extends TreeNode
{
  /**
   * Adds {@code child} to the receiver at {@code index}.
   * {@code child} will be messaged with {@link #setParent}.
   *
   * @param child The child to add.
   * @param index The index at which to add the child.
   */
  void insert( final MutableTreeNode child, final int index );

  /**
   * Removes the child at {@code index} from the receiver.  {@link #setParent}
   * will be messaged on the node being removed.
   *
   * @param index The index of the child to remove.
   */
  void remove( final int index );

  /**
   * Removes {@code node} from the receiver. {@link #setParent} will
   * be messaged on {@code node}.
   *
   * @param node The node to remove.
   */
  void remove( final MutableTreeNode node );

  /** Removes the receiver from its parent. */
  void removeFromParent();

  /**
   * Sets the parent of the receiver to {@code newParent}.
   *
   * @param newParent The new parent for the node.
   */
  void setParent( final MutableTreeNode newParent );

  /**
   * Resets the user object of the receiver to {@code object}.
   *
   * @param object The user object of the receiver
   */
  void setUserObject( final M object );
}
