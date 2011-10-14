/**
 * The test class used to test echopoint.LightBox component.
 *
 * @author Rakesh 2009-03-06
 * @version $Id: LightBoxTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.LightBoxTest = Core.extend(
{
  $construct: function( testArea )
  {
    var lightBox = this._createComponent();

    lightBox.add( this._createImage() );
    lightBox.add( new echopoint.Strut() );
    lightBox.add( this._createControl( lightBox ) );
    lightBox.add( new echopoint.Strut() );
    lightBox.add( this._createRemove( testArea, lightBox ) );

    testArea.add( this._createLabel() );
    testArea.add( new echopoint.Strut() );
    testArea.add( this._createTextField() );
    testArea.add( new echopoint.Strut() );
    testArea.add( this._createOpen( lightBox ) );
    testArea.add( lightBox );
  },

  /** Create the component that is to be tested. */
  _createComponent: function()
  {
    return new echopoint.LightBox(
    {
      renderId: "echopointUnitTestLightBox",
      parentOnly: true,
      hidden: false
    } );
  },

  _createLabel: function()
  {
    return new Echo.Label(
    {
      renderId: "echopointUnitTestLightBoxLabel",
      styleName: "Default",
      text: "Label 1"
    });
  },

  _createTextField: function()
  {
    return new Echo.TextField(
    {
      renderId: "echopointUnitTestLightBoxTextField",
      styleName: "Default",
      text: "Sample TextField"
    });
  },

  _createImage: function()
  {
    return new echopoint.ImageIcon(
    {
      renderId: "echopointUnitTestLightBoxImageIcon",
      styleName: "Default"
    });
  },

  _createControl: function( lightBox )
  {
    return new Echo.Button(
    {
      renderId: "echopointUnitTestLightBoxClose",
      styleName: "Default",
      text: "Close",
      events:
      {
        action: function()
        {
          lightBox.set( echopoint.LightBox.HIDDEN, true );
        }
      }
    });
  },

  _createOpen: function( lightBox )
  {
    return new Echo.Button(
    {
      renderId: "echopointUnitTestLightBoxOpen",
      styleName: "Default",
      text: "Toggle",
      events:
      {
        action: function()
        {
          lightBox.set( echopoint.LightBox.HIDDEN, false );
        }
      }
    });
  },

  _createRemove: function( testArea, lightBox )
  {
    return new Echo.Button(
    {
      renderId: "echopointUnitTestLightBoxRemove",
      styleName: "Default",
      text: "Remove",
      events:
      {
        action: function()
        {
          testArea.remove( lightBox );
        }
      }
    });
  }
});
