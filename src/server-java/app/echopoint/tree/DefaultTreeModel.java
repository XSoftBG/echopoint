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

import nextapp.echo.extras.app.tree.AbstractTreeModel;

/**
 * A simple tree model that uses {@link TreeNode}s.
 *
 * @author Brad Baker, Rakesh 2009-05-29
 * @version $Id: DefaultTreeModel.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 * @since 3.0.0b3
 */
public class DefaultTreeModel extends AbstractTreeModel
{
  private static final long serialVersionUID = 1l;

  /** Root of the tree. */
  protected TreeNode root;

  /**
   * Creates a tree with the specified root node.
   *
   * @param root a TreeNode object that is the root of the tree
   */
  public DefaultTreeModel( final TreeNode root )
  {
    this.root = root;
  }

  /**
   * Returns the child of <I>parent </I> at index <I>index </I> in the
   * parent's child array. <I>parent </I> must be a node previously obtained
   * from this data source. This should not return null if <i>index </i> is a
   * valid index for <i>parent </i> (that is <i>index </i>>= 0 && <i>index
   * </i>< getChildCount( <i>parent </i>)).
   *
   * @param parent a node in the tree, obtained from this data source
   * @return the child of <I>parent </I> at index <I>index </I>
   */
  public Object getChild( final Object parent, final int index )
  {
    return ( (TreeNode) parent ).getChildAt( index );
  }

  /**
   * Returns the number of children of <I>parent </I>. Returns 0 if the node
   * is a leaf or if it has no children. <I>parent </I> must be a node
   * previously obtained from this data source.
   *
   * @param parent a node in the tree, obtained from this data source
   * @return the number of children of the node <I>parent </I>
   */
  public int getChildCount( final Object parent )
  {
    return ( (TreeNode) parent ).getChildCount();
  }

  /** {@inheritDoc} */
  public int getColumnCount()
  {
    // TODO Auto-generated method stub
    return 1;
  }

  /** Returns the index of child in parent. */
  public int getIndexOfChild( final Object parent, final Object child )
  {
    if ( parent == null || child == null ) return 0;
    return ( (TreeNode) parent ).getIndex( (TreeNode) child );
  }

  /**
   * Returns the root of the tree. Returns null only if the tree has no
   * nodes.
   *
   * @return the root of the tree
   */
  public Object getRoot()
  {
    return root;
  }

  /** {@inheritDoc} */
  public Object getValueAt( final Object node, final int column )
  {
    return node.toString();
  }

  /**
   * Returns whether the specified node is a leaf node. The way the test is
   * performed depends on the <code>askAllowsChildren</code> setting.
   */
  public boolean isLeaf( final Object node )
  {
    return ( (TreeNode) node ).isLeaf();
  }

  public void addChild( final DefaultMutableTreeNode parent,
      DefaultMutableTreeNode child )
  {
    parent.add( child );
    fireTreeNodesInserted( parent, parent.getPath(),
        new int[] { parent.getIndex( child ) }, new Object[] { child } );
  }

  public void removeChild( final DefaultMutableTreeNode parent,
      DefaultMutableTreeNode child )
  {
    final int index = parent.getIndex( child );
    if ( index == -1 ) return;

    parent.remove( child );
    fireTreeNodesRemoved( parent, parent.getPath(),
        new int[] { index }, new Object[] { child } );
  }
}
