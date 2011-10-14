/** The name of the Strut component. */
echopoint.constants.STRUT = "echopoint.Strut";

/**
 * A simple component used to add space between other components.  Renders
 * an empty image of the specified size.
 *
 * @author Rakesh 2008-07-20
 * @version $Id: Application.Strut.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.Strut = Core.extend( Echo.Component,
{
  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.STRUT, this );
  },

  componentType: echopoint.constants.STRUT
});

