/**
 * Script file with the component definitions for the TextComponent extensions.
 *
 * @author Rakesh 2009-03-07
 * @version $Id: Application.TextField.js,v 1.2 2010-11-18 16:13:04 ivan Exp $
 */

/** The name of the place holder text field component. */
echopoint.constants.TEXT_FIELD = "echopoint.internal.TextField";

/** The name of the NumberTextField component. */
echopoint.constants.NUMBER_TEXT_FIELD = "echopoint.NumberTextField";

/** The name of the RegexTextField component. */
echopoint.constants.REGEX_TEXT_FIELD = "echopoint.RegexTextField";

/** The name of the KeystrokeTextField component. */
echopoint.constants.KEYSTROKE_TEXT_FIELD = "echopoint.KeystrokeTextField";

/** The name of the TextArea component. */
echopoint.constants.TEXT_AREA = "echopoint.TextArea";

/**
 * Place holder base class for all text field extensions.
 */
echopoint.internal.TextField = Core.extend( Echo.TextField,
{
  $abstract: true,

  /** Additional properties for the component. */
  $static:
  {
    DEFAULT_TEXT: "defaultText" // default text to display
  },

  $load: function()
  {
    Echo.ComponentFactory.registerType(
        echopoint.constants.TEXT_FIELD, this );
  },

  componentType: echopoint.constants.TEXT_FIELD
});

/**
 * Component that uses a regex to pre-filter out invalid characters (or
 * patterns) from being input into a text field.
 */
echopoint.RegexTextField = Core.extend( echopoint.internal.TextField,
{
  $load: function()
  {
    Echo.ComponentFactory.registerType(
        echopoint.constants.REGEX_TEXT_FIELD, this );
  },

  $static:
  {
    REGEX: "regex" // property used to specify the regex to use.
  },

	$virtual:
	{
		fireExpValid: function()
		{		
			this.fireEvent({type: "expValid", source: this});
		},
	
		fireExpInvalid: function()
		{		
			this.fireEvent({type: "expInvalid", source: this});
		}
	},
  componentType: echopoint.constants.REGEX_TEXT_FIELD
});

/**
 * Component for allowing only numeric values into a text field.
 */
echopoint.NumberTextField = Core.extend( echopoint.RegexTextField,
{
  $load: function()
  {
    Echo.ComponentFactory.registerType(
        echopoint.constants.NUMBER_TEXT_FIELD, this );
  },

  $static:
  {
    // The default regex for numbers
    NUMBER_REGEX: "^[\\d]+[.]{0,1}[\\d]*$",

    // The regex to use for numbers with limitation on fractional part
    FRACTION_REGEX: "^[\\d]+[.]{0,1}[\\d]{0,",

    // The property used to set the maximum number of fractional digits
    PRECISION: "precision"
  },

  componentType: echopoint.constants.NUMBER_TEXT_FIELD
}),

/**
 * Component that uses a regex to pre-filter out invalid characters (or
 * patterns) from being input into a text field.
 */
echopoint.KeystrokeTextField = Core.extend( echopoint.internal.TextField,
{
  $load: function()
  {
    Echo.ComponentFactory.registerType(
        echopoint.constants.KEYSTROKE_TEXT_FIELD, this );
  },

  componentType: echopoint.constants.KEYSTROKE_TEXT_FIELD
}),

/**
 * Component that uses a regex to pre-filter out invalid characters (or
 * patterns) from being input into a text field.
 */
echopoint.TextArea = Core.extend( Echo.TextArea,
{
  $load: function()
  {
    Echo.ComponentFactory.registerType(
        echopoint.constants.TEXT_AREA, this );
  },

  componentType: echopoint.constants.TEXT_AREA
});

