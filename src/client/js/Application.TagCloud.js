/** The name of the TagCloud component. */
echopoint.constants.TAG_CLOUD = "echopoint.TagCloud";

/**
 * TagCloud component.
 *
 * @sp {#Color} rolloverBackground the rollover background color
 * @sp {Boolean} rolloverEnabled boolean flag indicating whether rollover
 *   effects are enabled
 * @sp {#Color} rolloverForeground the rollover foreground
 * @sp tags An array containing the {#Tag} data objects to display.
 * @version $Id: Application.TagCloud.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.TagCloud = Core.extend( echopoint.internal.AbstractContainer,
{
  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.TAG_CLOUD, this );
  },

  /** Properties defined for this component. */
  $static:
  {
    ACTION_COMPLETE: "action",
    ROLLOVER_ENABLED: "rolloverEnabled",
    ROLLOVER_BACKGROUND: "rolloverBackground",
    ROLLOVER_FOREGROUND: "rolloverForeground",
    TAGS: "tags"
  },

  componentType: echopoint.constants.TAG_CLOUD,

  /** Perform when a tag is clicked on. */
  doAction: function( tag )
  {
    this.fireEvent( { type: echopoint.TagCloud.ACTION_COMPLETE,
    source: this, data: tag.name } );
  }
} );

/** The data object for the TagCloud component. */
echopoint.model.Tag = Core.extend(
{
  /** The name (title) for the tag. */
  name: null,

  /**
   * The count for this tag. This is usually the number of occurances of
   * the name represented in this tag.
   */
  count: 0,

  $construct: function( title, occurances )
  {
    this.name = title;
    this.count = occurances;
  },

  /** Return the string representation of this tag. */
  toString: function()
  {
    return this.name;
  }
} );

