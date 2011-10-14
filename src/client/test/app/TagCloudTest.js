/**
 * A test class for the echopoint.TagCloud client-side component.
 * Displays tags with randomly assigned values in a row.
 *
 * @author Rakesh 2008-07-20
 * @version $Id: TagCloudTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.TagCloudTest = Core.extend(
{
  $construct: function( testArea )
  {
    testArea.add( this._createRow() );
    this.testArea = testArea;
  },

  tagcloud: null,
  testArea: null,

  _createRow: function()
  {
    var row = new Echo.Row( { style: "Default" } );
    this.tagcloud = new echopoint.TagCloud(
    {
      renderId: "echopointUnitTestTagCloud",
      styleName:  "Default",
      tags: this._createTags(),
      events:
      {
        action: Core.method( this, this._tagAction )
      }
    } );
    row.add( this.tagcloud );

    var column = new Echo.Column( { styleName: "Default" } );
    column.add( row );
    column.add( this._createUpdate() );
    return column;
  },

  _createTags: function()
  {
    var tags = new Array();

    for ( var i = 0; i < 10; ++i )
    {
      tags[i] = new echopoint.model.Tag( "Tag " + i,
          Math.floor( ( Math.random() * 10 ) + 1 ) );
    }

    return tags;
  },

  _createUpdate: function()
  {
    var row = new Echo.Row( { styleName: "Default" } );

    var button = new Echo.Button(
    {
      renderId: "echopointUnitTestTagCloudUpdate",
      styleName: "Default",
      text: "Update",
      events:
      {
        action: Core.method( this, this._buttonAction )
      }
    } );

    row.add( button );
    return row;
  },

  _tagAction: function( event )
  {
    this.testArea.add(
        new Echo.Label(
        {
          styleName: "Default",
          text: "Tag: " + event.data
        } ) );
  },

  _buttonAction: function( event )
  {
    this.tagcloud.set( echopoint.TagCloud.TAGS, this._createTags() );
  }
} );