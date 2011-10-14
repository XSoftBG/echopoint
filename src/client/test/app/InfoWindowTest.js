/**
 * A test class for the echopoint.InfoWindow client-side component.
 * Displays a sample info window over a button.
 *
 * @author Rakesh 2008-10-22
 * @version $Id: InfoWindowTest.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.test.InfoWindowTest = Core.extend(
{
  infoWindow: null,

  $construct: function( testArea )
  {
    testArea.add( this._createInfoWindow() );
  },

  _createInfoWindow: function()
  {
    this.infoWindow = new echopoint.InfoWindow(
    {
      renderId: "echopointUnitTestInfoWindow",
      styleName: "Default",
      prefix: "Integer in eros nec arcu blandit adipiscing. Phasellus tempor ligula et odio. Aliquam " +
      "erat volutpat. Proin sed nisi. Phasellus pretium, ipsum ornare pellentesque pellentesque, " +
       "quam enim consequat lectus, non consectetuer tortor risus quis neque. Nam eget odio. " +
      "Cras gravida ipsum eu lectus. Duis vitae tortor in ante tincidunt volutpat. Maecenas " +
      "interdum mollis mi. Proin feugiat purus sed elit. Ut in augue. Duis pede lectus, " +
      "volutpat non, ullamcorper id, tincidunt sed, libero. Pellentesque luctus ligula vel " +
      "dolor. Integer elementum lorem in ligula. Aliquam id dolor a leo laoreet egestas. In " +
      "nec nibh porta eros consequat condimentum. " +
      "Nam elementum magna vitae justo. Nam velit est, vestibulum a, " +
      "aliquam non, sollicitudin vitae, risus. " +
      "Curabitur interdum dictum sapien. Nulla faucibus tellus vel erat. Aliquam posuere " +
      "mi et dolor faucibus gravida. In sit amet lorem at mi tempus egestas. Phasellus " +
      "rhoncus erat id est. nibh, rutrum ac, imperdiet ac, elementum nec, ",
      text: "Hover over here",
      postfix: "orci. Proin a ipsum vitae dui luctus congue. Nullam viverra iaculis nulla. Duis " +
      "sagittis eros eu quam. Nam elementum magna vitae justo. Nam velit est, vestibulum a, " +
      "aliquam non, sollicitudin vitae, risus.",
      title: "Header",
      content: "Donec risus purus, elementum ut, porttitor sed, tincidunt at, neque. " +
            "Sed gravida turpis ac neque. Nulla pede lorem, imperdiet bibendum, " +
            "varius id, adipiscing ut, metus. Nulla tincidunt vehicula ipsum. " +
            "Vestibulum a eros. Phasellus urna. Sed tempus, lectus eu vulputate tristique"
    } );

    return this.infoWindow;
  }
} );
