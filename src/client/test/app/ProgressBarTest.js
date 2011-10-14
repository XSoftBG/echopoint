/**
 * A test class for the echopoint.ProgressBar client-side component.
 * Displays simple progress bar that updates itself in 10 steps.
 *
 * @author Rakesh 2008-11-12
 * @version $Id: ProgressBarTest.js,v 1.1.1.1 2010-04-01 09:47:34 perxi Exp $
 */
echopoint.test.ProgressBarTest = Core.extend(
{
  progress: null,
  _pollingInterval: 250,
  _width: 0,

  $construct: function( testArea )
  {
    testArea.add( this._createProgressBar() );
  },

  _createProgressBar: function()
  {
    this.progress =  new echopoint.ProgressBar(
    {
      renderId: "echopointUnitTestProgressBar",
      styleName: "Default"
    } );

    Core.Web.Scheduler.run(
        Core.method( this, this._poll ), this._pollingInterval, false );

    return this.progress;
  },

  _poll: function()
  {
    this._width += 10;

    if ( this._width <= 100 )
    {
      var text = "Completed " + this._width + " of 100";
      this.progress.set( echopoint.ProgressBar.TEXT, text );

      this.progress.set( echopoint.ProgressBar.PERCENTAGE, this._width );
      this.progress.peer.renderUpdate();

      Core.Web.Scheduler.run(
          Core.method( this, this._poll ), this._pollingInterval, false );
    }
  }
} );
