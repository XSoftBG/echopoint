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
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * A {@code DefaultMutableTreeNode} is a general-purpose node in a tree
 * data structure. A tree node may have at most one parent and 0 or more
 * children. {@code DefaultMutableTreeNode} provides operations for
 * examining and modifying a node's parent and children and also operations
 * for examining the tree that the node is a part of. A node's tree is the set
 * of all nodes that can be reached by starting at the node and following all
 * the possible links to parents and children. A node with no parent is the
 * root of its tree; a node with no children is a leaf. A tree may consist of
 * many subtrees, each node acting as the root for its own subtree.
 * <p/>
 * This class provides enumerations for efficiently traversing a tree or
 * subtree in various orders or for following the path between two nodes.
 * <p/>
 * A {@code DefaultMutableTreeNode} may also hold a reference to a user
 * object, the use of which is left to the user. Asking a
 * {@code DefaultMutableTreeNode} for its string representation with
 * {@code toString()} returns the string representation of its user
 * object.
 * <p/>
 * <b>This is not a thread safe class.</b>If you intend to use a
 * DefaultMutableTreeNode (or a tree of TreeNodes) in more than one thread,
 * you need to do your own synchronizing. A good convention to adopt is
 * synchronizing on the root node of a tree.
 * <p/>
 * While DefaultMutableTreeNode implements the MutableTreeNode interface and
 * will allow you to add in any implementation of MutableTreeNode not all of
 * the methods in DefaultMutableTreeNode will be applicable to all
 * MutableTreeNodes implementations. Especially with some of the enumerations
 * that are provided, using some of these methods assumes the
 * DefaultMutableTreeNode contains only DefaultMutableNode instances. All of
 * the TreeNode/MutableTreeNode methods will behave as defined no matter what
 * implementations are added.
 * <p/>
 *
 * @author Brad Baker, Rakesh Vidyadharan 2009-05-29
 * @version $Id: DefaultMutableTreeNode.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 * @since 3.0.0b3
 */
@SuppressWarnings( { "OverlyComplexClass", "ClassWithTooManyMethods" } )
public class DefaultMutableTreeNode<M> implements MutableTreeNode<M>
{
  private static final long serialVersionUID = 1L;

  /** this node's action command */
  protected String actionCommand;

  /** this node's parent, or null if this node has no parent */
  protected MutableTreeNode parent;

  /** Array of children, may be null if this node has no children */
  protected Vector<TreeNode> children;

  /** optional user object */
  transient protected M userObject;

  /** true if the node is able to have children */
  protected boolean allowsChildren;

  /**
   * An enumeration that is always empty. This is used when an enumeration of
   * a leaf node's children is requested.
   */
  public static final Enumeration<TreeNode> EMPTY_ENUMERATION = new EmptyEnumeration();

  /**
   * Creates a tree node that has no parent and no children, but which allows
   * children.
   */
  public DefaultMutableTreeNode() {}

  /**
   * Creates a tree node with no parent, no children, but which allows
   * children, and initializes it with the specified user object.
   *
   * @param userObject an Object provided by the user that constitutes the
   * node's data
   */
  public DefaultMutableTreeNode( final M userObject )
  {
    this( userObject, true );
  }

  /**
   * Creates a tree node with no parent, no children, initialized with the
   * specified user object, and that allows children only if specified.
   *
   * @param userObject an Object provided by the user that constitutes the
   * node's data
   * @param allowsChildren if true, the node is allowed to have child nodes --
   * otherwise, it is always a leaf node
   */
  public DefaultMutableTreeNode( final M userObject,
      final boolean allowsChildren )
  {
    super();
    parent = null;
    this.allowsChildren = allowsChildren;
    this.userObject = userObject;
  }

  /**
   * Removes {@code newChild} from its parent and makes it a child of
   * this node by adding it to the end of this node's child array.
   *
   * @param newChild The child node to add.
   * @throws IllegalArgumentException if {@code newChild} is null
   * @throws IllegalStateException if this node does not allow children
   */
  public void add( final MutableTreeNode newChild )
  {
    if ( newChild != null && newChild.getParent() == this )
    {
      insert( newChild, getChildCount() - 1 );
    }
    else
    {
      insert( newChild, getChildCount() );
    }
  }

  /**
   * Creates and returns an enumeration that traverses the subtree rooted at
   * this node in breadth-first order. The first node returned by the
   * enumeration's {@code nextElement()} method is this node.
   * <p/>
   * Modifying the tree by inserting, removing, or moving a node invalidates
   * any enumerations created before the modification.
   *
   * @return an enumeration for traversing the tree in breadth-first order
   */
  public Enumeration<TreeNode> breadthFirstEnumeration()
  {
    return new BreadthFirstEnumeration( this );
  }

  /**
   * Creates and returns a forward-order enumeration of this node's children.
   * Modifying this node's child array invalidates any child enumerations
   * created before the modification.
   *
   * @return an Enumeration of this node's children
   */
  public Enumeration<TreeNode> children()
  {
    if ( children == null )
    {
      return EMPTY_ENUMERATION;
    }
    else
    {
      return children.elements();
    }
  }

  /**
   * Creates and returns an enumeration that traverses the subtree rooted at
   * this node in depth-first order. The first node returned by the
   * enumeration's {@code nextElement()} method is the leftmost leaf.
   * This is the same as a postorder traversal.
   * <p/>
   * Modifying the tree by inserting, removing, or moving a node invalidates
   * any enumerations created before the modification.
   *
   * @return an enumeration for traversing the tree in depth-first order
   */
  public Enumeration<TreeNode> depthFirstEnumeration()
  {
    return postorderEnumeration();
  }

  /** Returns the Action command string associated with this node */
  public String getActionCommand()
  {
    return actionCommand;
  }

  /**
   * Returns true if this node is allowed to have children.
   *
   * @return true if this node allows children, else false
   */
  public boolean getAllowsChildren()
  {
    return allowsChildren;
  }

  /**
   * Returns the child in this node's child array that immediately follows
   * {@code aChild}, which must be a child of this node. If
   * {@code aChild} is the last child, returns null. This method performs
   * a linear search of this node's children for {@code aChild} and is
   * O(n) where n is the number of children; to traverse the entire array of
   * children, use an enumeration instead.
   *
   * @param aChild The child node from which the next node is to be retrieved.
   * @return the child of this node that immediately follows
   *         {@code aChild}
   * @throws IllegalArgumentException if {@code aChild} is null or is not
   * a child of this node
   */
  public TreeNode getChildAfter( final TreeNode aChild )
  {
    if ( aChild == null )
    {
      throw new IllegalArgumentException( "argument is null" );
    }

    final int index = getIndex( aChild ); // linear search

    if ( index == -1 )
    {
      throw new IllegalArgumentException( "node is not a child" );
    }

    if ( index < getChildCount() - 1 )
    {
      return getChildAt( index + 1 );
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns the child at the specified index in this node's child array.
   *
   * @param index an index into this node's child array
   * @return the TreeNode in this node's child array at the specified index
   * @throws ArrayIndexOutOfBoundsException if {@code index} is out of
   * bounds
   */
  public TreeNode getChildAt( final int index )
  {
    if ( children == null )
    {
      throw new ArrayIndexOutOfBoundsException( "node has no children" );
    }
    return children.elementAt( index );
  }

  /**
   * Returns the child in this node's child array that immediately precedes
   * {@code aChild}, which must be a child of this node. If
   * {@code aChild} is the first child, returns null. This method
   * performs a linear search of this node's children for {@code aChild}
   * and is O(n) where n is the number of children.
   *
   * @param aChild The node from which the node before is to be retrieved.
   * @return the child of this node that immediately precedes
   *         {@code aChild}
   * @throws IllegalArgumentException if {@code aChild} is null or is not
   * a child of this node
   */
  public TreeNode getChildBefore( final TreeNode aChild )
  {
    if ( aChild == null )
    {
      throw new IllegalArgumentException( "argument is null" );
    }

    final int index = getIndex( aChild ); // linear search

    if ( index == -1 )
    {
      throw new IllegalArgumentException( "argument is not a child" );
    }

    if ( index > 0 )
    {
      return getChildAt( index - 1 );
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns the number of children of this node.
   *
   * @return an int giving the number of children of this node
   */
  public int getChildCount()
  {
    if ( children == null )
    {
      return 0;
    }
    else
    {
      return children.size();
    }
  }

  /**
   * Returns the depth of the tree rooted at this node -- the longest distance
   * from this node to a leaf. If this node has no children, returns 0. This
   * operation is much more expensive than {@code getLevel()} because it
   * must effectively traverse the entire tree rooted at this node.
   *
   * @return the depth of the tree whose root is this node
   */
  public int getDepth()
  {
    Object last = null;
    final Enumeration enumeration = breadthFirstEnumeration();

    while ( enumeration.hasMoreElements() )
    {
      last = enumeration.nextElement();
    }

    if ( last == null )
    {
      throw new InternalError( "nodes should be null" );
    }

    return ( (DefaultMutableTreeNode) last ).getLevel() - getLevel();
  }

  /**
   * Returns this node's first child. If this node has no children, throws
   * NoSuchElementException.
   *
   * @return the first child of this node
   * @throws NoSuchElementException if this node has no children
   */
  public TreeNode getFirstChild()
  {
    if ( getChildCount() == 0 )
    {
      throw new NoSuchElementException( "node has no children" );
    }
    return getChildAt( 0 );
  }

  /**
   * Finds and returns the first leaf that is a descendant of this node --
   * either this node or its first child's first leaf. Returns this node if it
   * is a leaf.
   *
   * @return the first leaf in the subtree rooted at this node
   */
  public DefaultMutableTreeNode getFirstLeaf()
  {
    DefaultMutableTreeNode node = this;

    while ( !node.isLeaf() )
    {
      node = (DefaultMutableTreeNode) node.getFirstChild();
    }

    return node;
  }

  /**
   * Returns the index of the specified child in this node's child array. If
   * the specified node is not a child of this node, returns {@code -1}.
   * This method performs a linear search and is O(n) where n is the number of
   * children.
   *
   * @param aChild the TreeNode to search for among this node's children
   * @return an int giving the index of the node in this node's child array,
   *         or {@code -1} if the specified node is a not a child of this
   *         node
   * @throws IllegalArgumentException if {@code aChild} is null
   */
  public int getIndex( final TreeNode aChild )
  {
    if ( aChild == null )
    {
      throw new IllegalArgumentException( "argument is null" );
    }

    if ( !isNodeChild( aChild ) )
    {
      return -1;
    }
    return children.indexOf( aChild ); // linear search
  }

  /**
   * Returns this node's last child. If this node has no children, throws
   * NoSuchElementException.
   *
   * @return the last child of this node
   * @throws NoSuchElementException if this node has no children
   */
  public TreeNode getLastChild()
  {
    if ( getChildCount() == 0 )
    {
      throw new NoSuchElementException( "node has no children" );
    }
    return getChildAt( getChildCount() - 1 );
  }

  /**
   * Finds and returns the last leaf that is a descendant of this node --
   * either this node or its last child's last leaf. Returns this node if it
   * is a leaf.
   *
   * @return the last leaf in the subtree rooted at this node
   */
  public DefaultMutableTreeNode getLastLeaf()
  {
    DefaultMutableTreeNode node = this;

    while ( !node.isLeaf() )
    {
      node = (DefaultMutableTreeNode) node.getLastChild();
    }

    return node;
  }

  /**
   * Returns the total number of leaves that are descendants of this node. If
   * this node is a leaf, returns {@code 1}. This method is O(n) where n
   * is the number of descendants of this node.
   *
   * @return the number of leaves beneath this node
   */
  public int getLeafCount()
  {
    int count = 0;

    TreeNode node;
    final Enumeration enumeration = breadthFirstEnumeration(); // order matters
    // not

    while ( enumeration.hasMoreElements() )
    {
      node = (TreeNode) enumeration.nextElement();
      if ( node.isLeaf() )
      {
        count++;
      }
    }

    if ( count < 1 )
    {
      throw new InternalError( "tree has zero leaves" );
    }

    return count;
  }

  /**
   * Returns the number of levels above this node -- the distance from the
   * root to this node. If this node is the root, returns 0.
   *
   * @return the number of levels above this node
   */
  public int getLevel()
  {
    TreeNode ancestor;
    int levels = 0;

    ancestor = this;
    while ( ( ancestor = ancestor.getParent() ) != null )
    {
      levels++;
    }

    return levels;
  }

  /**
   * Returns the leaf after this node or null if this node is the last leaf in
   * the tree.
   * <p/>
   * In this implementation of the {@code MutableNode} interface, this
   * operation is very inefficient. In order to determine the next node, this
   * method first performs a linear search in the parent's child-list in order
   * to find the current node.
   * <p/>
   * That implementation makes the operation suitable for short traversals
   * from a known position. But to traverse all of the leaves in the tree, you
   * should use {@code depthFirstEnumeration} to enumerate the nodes in
   * the tree and use {@code isLeaf} on each node to determine which are
   * leaves.
   *
   * @return The leaf after this node or {@code null}.
   */
  public DefaultMutableTreeNode getNextLeaf()
  {
    DefaultMutableTreeNode nextSibling;
    final DefaultMutableTreeNode myParent = (DefaultMutableTreeNode) getParent();

    if ( myParent == null )
    {
      return null;
    }

    nextSibling = getNextSibling(); // linear search

    if ( nextSibling != null )
    {
      return nextSibling.getFirstLeaf();
    }

    return myParent.getNextLeaf(); // tail recursion
  }

  /**
   * Returns the node that follows this node in a preorder traversal of this
   * node's tree. Returns null if this node is the last node of the traversal.
   * This is an inefficient way to traverse the entire tree; use an
   * enumeration, instead.
   *
   * @return Return the node that follows this node.
   */
  public DefaultMutableTreeNode getNextNode()
  {
    if ( getChildCount() == 0 )
    {
      // No children, so look for nextSibling
      DefaultMutableTreeNode nextSibling = getNextSibling();

      if ( nextSibling == null )
      {
        DefaultMutableTreeNode aNode = (DefaultMutableTreeNode) getParent();

        do
        {
          if ( aNode == null )
          {
            return null;
          }

          nextSibling = aNode.getNextSibling();
          if ( nextSibling != null )
          {
            return nextSibling;
          }

          aNode = (DefaultMutableTreeNode) aNode.getParent();
        } while ( true );
      }
      else
      {
        return nextSibling;
      }
    }
    else
    {
      return (DefaultMutableTreeNode) getChildAt( 0 );
    }
  }

  /**
   * Returns the next sibling of this node in the parent's children array.
   * Returns null if this node has no parent or is the parent's last child.
   * This method performs a linear search that is O(n) where n is the number
   * of children; to traverse the entire array, use the parent's child
   * enumeration instead.
   *
   * @return Return the next sibling from the parent node.
   */
  public DefaultMutableTreeNode getNextSibling()
  {
    DefaultMutableTreeNode retval;

    final DefaultMutableTreeNode myParent = (DefaultMutableTreeNode) getParent();

    if ( myParent == null )
    {
      retval = null;
    }
    else
    {
      retval = (DefaultMutableTreeNode) myParent.getChildAfter( this );
      // linear search
    }

    if ( retval != null && !isNodeSibling( retval ) )
    {
      throw new InternalError( "child of parent is not a sibling" );
    }

    return retval;
  }

  /**
   * Returns this node's parent or null if this node has no parent.
   *
   * @return this node's parent TreeNode, or null if this node has no parent
   */
  public TreeNode getParent()
  {
    return parent;
  }

  /**
   * Returns the path from the root, to get to this node. The last element in
   * the path is this node.
   *
   * @return an array of TreeNode objects giving the path, where the first
   *         element in the path is the root and the last element is this
   *         node.
   */
  public TreeNode[] getPath()
  {
    return getPathToRoot( this, 0 );
  }

  /**
   * Builds the parents of node up to and including the root node, where the
   * original node is the last element in the returned array. The length of
   * the returned array gives the node's depth in the tree.
   *
   * @param aNode the TreeNode to get the path for
   * @param depth an int giving the number of steps already taken towards the
   * root (on recursive calls), used to size the returned array
   * @return an array of TreeNodes giving the path from the root to the
   *         specified node
   */
  protected TreeNode[] getPathToRoot( final TreeNode aNode, int depth )
  {
    TreeNode[] retNodes;

    /*
     * Check for null, in case someone passed in a null node, or they passed in
     * an element that isn't rooted at root.
     */
    if ( aNode == null )
    {
      if ( depth == 0 )
      {
        return null;
      }
      else
      {
        retNodes = new TreeNode[depth];
      }
    }
    else
    {
      depth++;
      retNodes = getPathToRoot( aNode.getParent(), depth );
      retNodes[retNodes.length - depth] = aNode;
    }
    return retNodes;
  }

  /**
   * Returns the leaf before this node or null if this node is the first leaf
   * in the tree.
   * <p/>
   * In this implementation of the {@code MutableNode} interface, this
   * operation is very inefficient. In order to determine the previous node,
   * this method first performs a linear search in the parent's child-list in
   * order to find the current node.
   * <p/>
   * That implementation makes the operation suitable for short traversals
   * from a known position. But to traverse all of the leaves in the tree, you
   * should use {@code depthFirstEnumeration} to enumerate the nodes in
   * the tree and use {@code isLeaf} on each node to determine which are
   * leaves.
   *
   * @return The previous leaf node based on the position of this node.
   */
  public DefaultMutableTreeNode getPreviousLeaf()
  {
    DefaultMutableTreeNode previousSibling;
    final DefaultMutableTreeNode myParent = (DefaultMutableTreeNode) getParent();

    if ( myParent == null )
    {
      return null;
    }

    previousSibling = getPreviousSibling(); // linear search

    if ( previousSibling != null )
    {
      return previousSibling.getLastLeaf();
    }

    return myParent.getPreviousLeaf(); // tail recursion
  }

  /**
   * Returns the node that precedes this node in a preorder traversal of this
   * node's tree. Returns null if this node is the first node of the traveral
   * -- the root of the tree. This is an inefficient way to traverse the
   * entire tree; use an enumeration, instead.
   *
   * @return The preivous node based on the position of this node.
   */
  public DefaultMutableTreeNode getPreviousNode()
  {
    DefaultMutableTreeNode previousSibling;
    final DefaultMutableTreeNode myParent = (DefaultMutableTreeNode) getParent();

    if ( myParent == null )
    {
      return null;
    }

    previousSibling = getPreviousSibling();

    if ( previousSibling != null )
    {
      if ( previousSibling.getChildCount() == 0 )
      {
        return previousSibling;
      }
      else
      {
        return previousSibling.getLastLeaf();
      }
    }
    else
    {
      return myParent;
    }
  }

  /**
   * Returns the previous sibling of this node in the parent's children array.
   * Returns null if this node has no parent or is the parent's first child.
   * This method performs a linear search that is O(n) where n is the number
   * of children.
   *
   * @return the sibling of this node that immediately precedes this node
   */
  public DefaultMutableTreeNode getPreviousSibling()
  {
    DefaultMutableTreeNode retval;

    final DefaultMutableTreeNode myParent = (DefaultMutableTreeNode) getParent();

    if ( myParent == null )
    {
      retval = null;
    }
    else
    {
      retval = (DefaultMutableTreeNode) myParent.getChildBefore( this );
      // linear search
    }

    if ( retval != null && !isNodeSibling( retval ) )
    {
      throw new InternalError( "child of parent is not a sibling" );
    }

    return retval;
  }

  /**
   * Returns the root of the tree that contains this node. The root is the
   * ancestor with a null parent.
   *
   * @return The root of the tree.
   */
  public TreeNode getRoot()
  {
    TreeNode ancestor = this;
    TreeNode previous;

    do
    {
      previous = ancestor;
      ancestor = ancestor.getParent();
    } while ( ancestor != null );

    return previous;
  }

  /**
   * Returns the nearest common ancestor to this node and {@code aNode}.
   * Returns null, if no such ancestor exists -- if this node and
   * {@code aNode} are in different trees or if {@code aNode} is
   * null. A node is considered an ancestor of itself.
   *
   * @param aNode  The node for which the common ancestor is to be retrieved.
   * @return The common ancestor of this node and the specified node.
   */
  public TreeNode getSharedAncestor( final DefaultMutableTreeNode aNode )
  {
    if ( aNode == this )
    {
      return this;
    }
    else if ( aNode == null )
    {
      return null;
    }

    int level1, level2, diff;
    TreeNode node1, node2;

    level1 = getLevel();
    level2 = aNode.getLevel();

    if ( level2 > level1 )
    {
      diff = level2 - level1;
      node1 = aNode;
      node2 = this;
    }
    else
    {
      diff = level1 - level2;
      node1 = this;
      node2 = aNode;
    }

    // Go up the tree until the nodes are at the same level
    while ( diff > 0 )
    {
      node1 = node1.getParent();
      diff--;
    }

    // Move up the tree until we find a common ancestor. Since we know
    // that both nodes are at the same level, we won't cross paths
    // unknowingly (if there is a common ancestor, both nodes hit it in
    // the same iteration).

    do
    {
      if ( node1 == node2 )
      {
        return node1;
      }
      node1 = node1.getParent();
      node2 = node2.getParent();
    } while ( node1 != null ); // only need to check one -- they're at the
    // same level so if one is null, the other is

    if ( node2 != null ) throw new InternalError( "nodes should be null" );

    return null;
  }

  /**
   * Returns the number of siblings of this node. A node is its own sibling
   * (if it has no parent or no siblings, this method returns
   * {@code 1}).
   *
   * @return the number of siblings of this node
   */
  public int getSiblingCount()
  {
    final TreeNode myParent = getParent();

    if ( myParent == null )
    {
      return 1;
    }
    else
    {
      return myParent.getChildCount();
    }
  }

  /** @return Returns this node's user object. */
  public M getUserObject()
  {
    return userObject;
  }

  /**
   * Returns the user object path, from the root, to get to this node. If some
   * of the TreeNodes in the path have null user objects, the returned path
   * will contain nulls.
   *
   * @return The array of user objects.
   */
  public Object[] getUserObjectPath()
  {
    final TreeNode[] realPath = getPath();
    final Object[] retPath = new Object[realPath.length];

    for ( int counter = 0; counter < realPath.length; counter++ )
    {
      retPath[counter] = ( (DefaultMutableTreeNode) realPath[counter] )
          .getUserObject();
    }
    return retPath;
  }

  /**
   * Removes {@code newChild} from its present parent (if it has a
   * parent), sets the child's parent to this node, and then adds the child to
   * this node's child array at index {@code childIndex}.
   * {@code newChild} must not be null and must not be an ancestor of
   * this node.
   */
  public void insert( final MutableTreeNode newChild, final int childIndex )
  {
    if ( !getAllowsChildren() )
    {
      throw new IllegalStateException( "node does not allow children" );
    }
    else if ( newChild == null )
    {
      throw new IllegalArgumentException( "new child is null" );
    }
    else if ( isNodeAncestor( newChild ) )
    {
      throw new IllegalArgumentException( "new child is an ancestor" );
    }

    final MutableTreeNode oldParent = (MutableTreeNode) newChild.getParent();

    if ( oldParent != null )
    {
      oldParent.remove( newChild );
    }
    newChild.setParent( this );
    if ( children == null )
    {
      children = new Vector<TreeNode>();
    }
    children.insertElementAt( newChild, childIndex );
  }

  /**
   * Returns true if this node has no children. To distinguish between nodes
   * that have no children and nodes that <i>cannot</i> have children (e.g. to
   * distinguish files from empty directories), use this method in conjunction
   * with {@code getAllowsChildren}
   */
  public boolean isLeaf()
  {
    return getChildCount() == 0;
  }

  /**
   * Returns true if {@code anotherNode} is an ancestor of this node --
   * if it is this node, this node's parent, or an ancestor of this node's
   * parent. (Note that a node is considered an ancestor of itself.) If
   * {@code anotherNode} is null, this method returns false. This
   * operation is at worst O(h) where h is the distance from the root to this
   * node.
   *
   * @param anotherNode The node to check.
   * @return {@code true} if the specified node is an ancestor.
   */
  public boolean isNodeAncestor( final TreeNode anotherNode )
  {
    if ( anotherNode == null )
    {
      return false;
    }

    TreeNode ancestor = this;

    do
    {
      if ( ancestor == anotherNode )
      {
        return true;
      }
    } while ( ( ancestor = ancestor.getParent() ) != null );

    return false;
  }

  /**
   * Returns true if {@code aNode} is a child of this node. If
   * {@code aNode} is null, this method returns false.
   *
   * @param aNode The node to check.
   * @return {@code true} if {@code aNode} is a child of this node;
   *   {@code false} if {@code aNode} is {@code null}
   */
  public boolean isNodeChild( final TreeNode aNode )
  {
    boolean retval;
    retval = aNode != null && getChildCount() != 0 && aNode.getParent() == this;
    return retval;
  }

  /**
   * Returns true if {@code anotherNode} is a descendant of this node --
   * if it is this node, one of this node's children, or a descendant of one
   * of this node's children. Note that a node is considered a descendant of
   * itself. If {@code anotherNode} is null, returns false. This
   * operation is at worst O(h) where h is the distance from the root to
   * {@code anotherNode}.
   *
   * @param anotherNode The node to check.
   * @return {@code true} is the node is a descendent of this node.
   */
  public boolean isNodeDescendant( final DefaultMutableTreeNode anotherNode )
  {
    return anotherNode != null && anotherNode.isNodeAncestor( this );
  }

  /**
   * Returns true if and only if {@code aNode} is in the same tree as
   * this node. Returns false if {@code aNode} is null.
   *
   * @param aNode The node to check.
   * @return {@code true} if the node is in the same tree.
   */
  public boolean isNodeRelated( final DefaultMutableTreeNode aNode )
  {
    return aNode != null && getRoot() == aNode.getRoot();
  }

  /**
   * Returns true if {@code anotherNode} is a sibling of (has the same
   * parent as) this node. A node is its own sibling. If
   * {@code anotherNode} is null, returns false.
   *
   * @param anotherNode node to test as sibling of this node
   * @return true if {@code anotherNode} is a sibling of this node
   */
  public boolean isNodeSibling( final TreeNode anotherNode )
  {
    boolean retval;

    if ( anotherNode == null )
    {
      retval = false;
    }
    else if ( anotherNode == this )
    {
      retval = true;
    }
    else
    {
      final TreeNode myParent = getParent();
      retval = myParent != null && myParent == anotherNode.getParent();

      if ( retval
          && !( (DefaultMutableTreeNode) getParent() ).isNodeChild( anotherNode ) )
      {
        throw new InternalError( "sibling has different parent" );
      }
    }

    return retval;
  }

  /**
   * Returns true if this node is the root of the tree. The root is the only
   * node in the tree with a null parent; every tree has exactly one root.
   *
   * @return true if this node is the root of its tree
   */
  public boolean isRoot()
  {
    return getParent() == null;
  }

  /**
   * Creates and returns an enumeration that follows the path from
   * {@code ancestor} to this node. The enumeration's
   * {@code nextElement()} method first returns {@code ancestor},
   * then the child of {@code ancestor} that is an ancestor of this node,
   * and so on, and finally returns this node. Creation of the enumeration is
   * O(m) where m is the number of nodes between this node and
   * {@code ancestor}, inclusive. Each {@code nextElement()} message
   * is O(1).
   * <p/>
   * Modifying the tree by inserting, removing, or moving a node invalidates
   * any enumerations created before the modification.
   *
   * @param ancestor The ancestor node from which the path is to be generated.
   * @return The enumeration of nodes.
   */
  public Enumeration<TreeNode> pathFromAncestorEnumeration(
      final TreeNode ancestor )
  {
    return new PathBetweenNodesEnumeration( ancestor, this );
  }

  /**
   * Creates and returns an enumeration that traverses the subtree rooted at
   * this node in postorder. The first node returned by the enumeration's
   * {@code nextElement()} method is the leftmost leaf. This is the same
   * as a depth-first traversal.
   * <p/>
   * Modifying the tree by inserting, removing, or moving a node invalidates
   * any enumerations created before the modification.
   *
   * @return The post order enumeration for this node.
   */
  public Enumeration<TreeNode> postorderEnumeration()
  {
    return new PostorderEnumeration( this );
  }

  /**
   * Creates and returns an enumeration that traverses the subtree rooted at
   * this node in preorder. The first node returned by the enumeration's
   * {@code nextElement()} method is this node.
   * <p/>
   * Modifying the tree by inserting, removing, or moving a node invalidates
   * any enumerations created before the modification.
   *
   * @return The enumeration of nodes.
   */
  public Enumeration<TreeNode> preorderEnumeration()
  {
    return new PreorderEnumeration( this );
  }

  /**
   * Removes the child at the specified index from this node's children and
   * sets that node's parent to null. The child node to remove must be a
   * {@code MutableTreeNode}.
   *
   * @param childIndex the index in this node's child array of the child to
   * remove
   * @throws ArrayIndexOutOfBoundsException if {@code childIndex} is out
   * of bounds
   */
  public void remove( final int childIndex )
  {
    final MutableTreeNode child = (MutableTreeNode) getChildAt( childIndex );
    children.removeElementAt( childIndex );
    child.setParent( null );
  }

  /**
   * Removes {@code aChild} from this node's child array, giving it a
   * null parent.
   *
   * @param aChild a child of this node to remove
   * @throws IllegalArgumentException if {@code aChild} is null or is not
   * a child of this node
   */
  public void remove( final MutableTreeNode aChild )
  {
    if ( aChild == null )
    {
      throw new IllegalArgumentException( "argument is null" );
    }

    if ( !isNodeChild( aChild ) )
    {
      throw new IllegalArgumentException( "argument is not a child" );
    }
    remove( getIndex( aChild ) ); // linear search
  }

  /**
   * Removes all of this node's children, setting their parents to null. If
   * this node has no children, this method does nothing.
   */
  public void removeAllChildren()
  {
    for ( int i = getChildCount() - 1; i >= 0; i-- )
    {
      remove( i );
    }
  }

  /**
   * Removes the subtree rooted at this node from the tree, giving this node a
   * null parent. Does nothing if this node is the root of its tree.
   */
  public void removeFromParent()
  {
    final MutableTreeNode parent = (MutableTreeNode) getParent();
    if ( parent != null )
    {
      parent.remove( this );
    }
  }

  /**
   * Sets this node's action command to {@code newActionCommand}
   *
   * @param newActionCommand this node's new action command
   */
  public void setActionCommand( final String newActionCommand )
  {
    actionCommand = newActionCommand;
  }

  /**
   * Determines whether or not this node is allowed to have children. If
   * {@code allows} is false, all of this node's children are removed.
   * <p/>
   * Note: By default, a node allows children.
   *
   * @param allows true if this node is allowed to have children
   */
  public void setAllowsChildren( final boolean allows )
  {
    if ( allows != allowsChildren )
    {
      allowsChildren = allows;
      if ( !allowsChildren )
      {
        removeAllChildren();
      }
    }
  }

  /**
   * Sets this node's parent to {@code newParent} but does not change the
   * parent's child array. This method is called from {@code insert()}
   * and {@code remove()} to reassign a child's parent, it should not be
   * messaged from anywhere else.
   *
   * @param newParent this node's new parent
   */
  public void setParent( final MutableTreeNode newParent )
  {
    parent = newParent;
  }

  /** Sets the user object for this node to {@code userObject}. */
  public void setUserObject( final M userObject )
  {
    this.userObject = userObject;
  }

  /**
   * Returns the result of sending {@code toString()} to this node's user
   * object, or {@code null} if this node has no user object.
   */
  @Override
  public String toString()
  {
    if ( userObject == null )
    {
      return null;
    }
    else
    {
      return userObject.toString();
    }
  }

  /** An empty enumeration for nodes that are leaves. */
  static class EmptyEnumeration implements Enumeration<TreeNode>
  {
    public boolean hasMoreElements()
    {
      return false;
    }

    public TreeNode nextElement()
    {
      throw new NoSuchElementException( "No more elements" );
    }
  }
}
