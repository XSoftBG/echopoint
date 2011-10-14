/** The name of the ProgressBar component. */
echopoint.constants.PROGRESS_BAR = "echopoint.ProgressBar";

/**
 * A component used display a simple progress bar by varying the width of a
 * child div within a parent div.
 *
 * @author Rakesh 2008-10-29
 * @version $Id: Application.ProgressBar.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
echopoint.ProgressBar = Core.extend( echopoint.internal.AbstractContainer,
{
  $load: function()
  {
    Echo.ComponentFactory.registerType( echopoint.constants.PROGRESS_BAR, this );
  },

  $static:
  {
    /** The colour for the variable width bar element. */
    BAR_BACKGROUND: "barBackground",
    DEFAULT_BAR_BACKGROUND: "#68b8ff",

    /** The percentage complete to display in the progress bar. */
    PERCENTAGE: "percentage",

    /** Optional text to display in the progress bar */
    TEXT: "text"
  },

  componentType: echopoint.constants.PROGRESS_BAR
});
