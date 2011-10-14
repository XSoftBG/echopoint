/**
 * A test class for the echopoint.PushButton client-side component.
 * Displays a sample button and applies standard styles.
 *
 * @author Rakesh 2009-02-24
 * @version $Id: PushButtonTest.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.test.PushButtonTest = Core.extend(
{
  button: null,
  label: null,
  select: null,
  count: 0,

  $construct: function( testArea )
  {
    testArea.add( this._createPushButton() );
    testArea.add( this._createEnabled() );
    testArea.add( this._createLabel() );
  },

  _createPushButton: function()
  {
    this.button = new echopoint.PushButton(
    {
      renderId: "echopointUnitTestPushButton",
      styleName: "Default",
      events:
      {
        action: Core.method( this, this._actionPerformed )
      }
    } );

    return this.button;
  },

  _createEnabled: function()
  {
    this.select = new Echo.SelectField(
    {
      renderId: "echopointUnitTestPushButtonSelectField",
      style: "Default",
      items: [ "Enabled", "Disabled"],
      events:
      {
        action: Core.method( this, this._enabled )
      }
    });

    return this.select;
  },

  _createLabel: function()
  {
    this.label = new Echo.Label(
    {
      renderId: "echopointUnitTestPushButtonLabel",
      style: "Default",
      text: "Action Label"
    } );

    return this.label;
  },

  _actionPerformed: function()
  {
    this.label.set( "text", "Button clicked: " + ++this.count );
  },

  _enabled: function()
  {
    if ( 1 == this.select.get( "selection" ) )
    {
      this.button.setEnabled( false );
    }
    else
    {
      this.button.setEnabled( true );
    }
  }
});
