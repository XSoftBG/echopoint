/**
 * Component rendering peer: NumberTextField
 *
 * @author Rakesh 2009-03-07
 * @version: $Id: Sync.NumberTextField.js,v 1.2 2010-11-09 10:33:03 ivan Exp $
 */
echopoint.NumberTextFieldSync = Core.extend( echopoint.RegexTextFieldSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.NUMBER_TEXT_FIELD, this );
  },

  $construct: function()
  {
    echopoint.RegexTextFieldSync.call( this );
  },

  _initRegexpFilter: function( precision )
  {
    if( precision )
    {
      var str = echopoint.NumberTextField.FRACTION_REGEX + precision + "}$";
      this.regexp_filter = new RegExp(str);
      this.component.set( echopoint.RegexTextField.REGEX, str );
    }
    else
    {
      this.regexp_filter = new RegExp( echopoint.NumberTextField.NUMBER_REGEX );
      this.component.set( echopoint.RegexTextField.REGEX, echopoint.NumberTextField.NUMBER_REGEX );
    }
		var negative = this.component.render("negative", false);
		if(negative)
		{			
			this.regexp_filter = /^(-){0,1}([\d]+[.]{0,1}[\d]*){0,1}$/;
		}
  },

  // Override the RegexTextField method
  renderAdd: function( update, parentElement )
  {
    echopoint.RegexTextFieldSync.prototype.renderAdd.call(
        this, update, parentElement );

    this._initRegexpFilter( this.component.render( echopoint.NumberTextField.PRECISION ) );
  },

  // Override the RegexTextField method
  renderUpdate: function(update) 
  {
    var rv   = echopoint.RegexTextFieldSync.prototype.renderUpdate.call(this, update);
    var prec = update.getUpdatedProperty( echopoint.NumberTextField.PRECISION );
    if(prec)
      this._initRegexpFilter(prec.newValue);
    return rv;
  }
});
