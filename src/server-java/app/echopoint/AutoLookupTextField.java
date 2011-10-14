package echopoint;

import echopoint.model.AutoLookupModel;

/**
 * A text field that attempts to fetch matching entries from the server.
 *
 * @author Christoff Spinner 2009-12-07
 * @version $Id: AutoLookupTextField.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class AutoLookupTextField extends KeystrokeTextField
{
  private static final long serialVersionUID = 1L;

  public static final String PROPERTY_AUTO_LOOKUP_MODEL = "autoLookupModel";
  public static final String PROPERTY_SEARCH_BAR_ICON = "searchBarIcon";
  public static final String PROPERTY_SEARCH_BAR_SEARCHING_ICON = "searchBarSearchingIcon";
  public static final String PROPERTY_SEARCH_BAR_TEXT = "searchBarText";
  public static final String PROPERTY_SEARCH_BAR_SHOWN = "searchBarShown";
  public static final String PROPERTY_SEARCH_BAR_SEARCHING_TEXT = "searchBarSearchingText";
  public static final String PROPERTY_NO_MATCHING_OPTION_TEXT = "noMatchingOptionText";


  public AutoLookupModel getAutoLookupModel()
  {
    return (AutoLookupModel) get( PROPERTY_AUTO_LOOKUP_MODEL );
  }

  public void setAutoLookupModel( final AutoLookupModel autoLookupModel )
  {
    set( PROPERTY_AUTO_LOOKUP_MODEL, autoLookupModel );
  }
}
