package echopoint.model;

/**
 * Enumeration of the standard CSS2 cursors.  Note that the {@link #toString}
 * method should be used in component sync peers to ensure that the proper
 * cursor style values are used.
 *
 * @author Rakesh Vidyadharan 2009-12-18
 * @version $Id: Cursor.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
@SuppressWarnings( { "ClassWithTooManyFields" } )
public enum Cursor
{
  /** The preferred cursor type for the browser. */
  auto,

  /** The cross-hair cursor. */
  crosshair,

  /** The right pointing arrow cursor. */
  east { public String toString() { return "e-resize"; } },

  /** The question mark cursor */
  help,

  /** The move cursor */
  move,

  /** The north pointing cursor */
  north { public String toString() { return "n-resize"; } },

  /** The north-east pointing cursor */
  northEast { public String toString() { return "ne-resize"; } },

  /** The north-west pointing cursor */
  northWest { public String toString() { return "nw-resize"; } },

  /** The hand/pointer cursor. */
  pointer,

  /** A cursor indicating that an activity is in progress. */
  progress,

  /** The south pointing cursor */
  south { public String toString() { return "s-resize"; } },

  /** The south-east pointing cursor */
  southEast { public String toString() { return "se-resize"; } },

  /** The south-west pointing cursor */
  southWest { public String toString() { return "sw-resize"; } },

  /** The text (I-beam) cursor */
  text,

  /** The west pointing cursor */
  west { public String toString() { return "w-resize"; } },

  /** The wait (hour-glass) cursor */
  wait
}
