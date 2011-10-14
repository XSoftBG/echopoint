package echopoint;

import nextapp.echo.app.ImageReference;
import nextapp.echo.app.Color;
import nextapp.echo.app.Border;
import echopoint.model.AutoLookupSelectFieldModel;
import echopoint.model.AutoLookupSelectModel;


public class AutoLookupSelectField extends RegexTextField {

	private static final long serialVersionUID = 1L;
	private String key;
	private String searchVal;
	public static final String PROPERTY_KEY = "key";
	public static final String PROPERTY_SEARCH_VAL = "searchVal";
	public static final String PROPERTY_AUTO_LOOKUP_MODEL = "autoLookupModel";
	public static final String PROPERTY_SEARCH_BAR_SEARCHING_ICON = "searchBarSearchingIcon";
	public static final String PROPERTY_SEARCH_BAR_SEARCHING_TEXT = "searchBarSearchingText";
	public static final String PROPERTY_NO_MATCHING_OPTION_TEXT = "noMatchingOptionText";
  public static final String PROPERTY_AUTO_SELECT = "autoSelect";
  public static final String PROPERTY_POPUP_ICON = "popupIcon";
  public static final String PROPERTY_OPTIONS_MENU_BGCOLOR = "optMenuBG";
  public static final String PROPERTY_OPTIONS_MENU_BORDER = "optMenuBorder";
  public static final String PROPERTY_SELECTED_BG = "selectedBG";
  public static final String PROPERTY_SELECTED_FG = "selectedFG";
  public static final String PROPERTY_ACTION_CLICK = "actionClick";


  /**
   * Creates a new <code>AutoLookupSelectField</code>.
   */
  public AutoLookupSelectField() { super(); setActionClick(true); }
 

  /**
   * If ActionClick is true (default value), then after click on the option from list, action event will be started.
   */
  public void setActionClick(boolean b) { set( PROPERTY_ACTION_CLICK, Boolean.valueOf(b) ); }
  
  /**
   * Sets auto selected first option mode. 
   */
  public void setAutoSelect(boolean b) { set(PROPERTY_AUTO_SELECT, b); } 
  public boolean getAutoSelect() { return ( (Boolean)get(PROPERTY_AUTO_SELECT) ).booleanValue(); } 

  /**
   * Sets icon next to this text field, which gives capability to show <code>drop down menu<code> with all options.
   */
  public void setPopupIcon( ImageReference icon ) { set(PROPERTY_POPUP_ICON, icon); } 
  public ImageReference getPopupIcon() { return (ImageReference)get(PROPERTY_POPUP_ICON); } 

  public void setSearchBarSearchingIcon( ImageReference icon ) { set(PROPERTY_SEARCH_BAR_SEARCHING_ICON, icon); } 
  public ImageReference getSearchBarSearchingIcon() { return (ImageReference)get(PROPERTY_SEARCH_BAR_SEARCHING_ICON); } 

  /**
   * Sets a text that will appear when there is no option that matches.
   */
  public void setNoMatchingOptionText( String txt ) { set(PROPERTY_NO_MATCHING_OPTION_TEXT, txt); } 
  public String getNoMatchingOptionText() { return (String)get(PROPERTY_NO_MATCHING_OPTION_TEXT); } 

  public void setSearchBarSearchingText( String txt ) { set(PROPERTY_SEARCH_BAR_SEARCHING_TEXT, txt); } 
  public String getSearchBarSearchingText() { return (String)get(PROPERTY_SEARCH_BAR_SEARCHING_TEXT); } 

  /**
    * Sets the default background color of the <code>drop down menu</code>.
    * 
    * @param newValue the new background <code>Color</code>
    */
  public void setOptionsMenuBackground( Color color) { set(PROPERTY_OPTIONS_MENU_BGCOLOR, color); } 
  public Color getOptionsMenuBackground() { return (Color)get(PROPERTY_OPTIONS_MENU_BGCOLOR); } 

  /**
    * Sets the default border of the <code>drop down menu</code>.
    * 
    * @param newValue the new border<code>Border</code>
    */
  public void setOptionsMenuBorder( Border border) { set(PROPERTY_OPTIONS_MENU_BORDER, border); } 
  public Border getOptionsMenuBorder() { return (Border)get(PROPERTY_OPTIONS_MENU_BORDER); } 


  /**
    * Sets the default background color of the <code>selected option</code>.
    * 
    * @param newValue the new background <code>Color</code>
    */
  public void setSelectedOptionBackground( Color color ) { set(PROPERTY_SELECTED_BG, color); } 
  public Color getSelectedOptionBackground() { return (Color)get(PROPERTY_SELECTED_BG); } 

  /**
    * Sets the default foreground color of the <code>selected option</code>.
    * 
    * @param newValue the new foreground <code>Color</code>
    */
  public void setSelectedOptionForeground( Color color ) { set(PROPERTY_SELECTED_FG, color); }
  public Color getSelectedOptionForeground() { return (Color)get(PROPERTY_SELECTED_FG); }

	public AutoLookupSelectModel getAutoLookupModel() { return (AutoLookupSelectModel)get(PROPERTY_AUTO_LOOKUP_MODEL); }

  public AutoLookupSelectFieldModel getAutoLookupFieldModel() { return (AutoLookupSelectFieldModel)get(PROPERTY_AUTO_LOOKUP_MODEL); }

	public void setAutoLookupFieldModel(AutoLookupSelectFieldModel autoLookupModel) { set(PROPERTY_AUTO_LOOKUP_MODEL, autoLookupModel); }

	public void setKey(String key) {
		String oldValue = this.key;
		this.key = key;
		firePropertyChange(PROPERTY_KEY, oldValue, key);
	}

	public String getKey() { return key; }

	public void setSearchVal(String searchVal) {
		String oldValue = this.searchVal;
		this.searchVal = searchVal;
		firePropertyChange(PROPERTY_SEARCH_VAL, oldValue, searchVal);
	}

	public String getSearchVal() { return searchVal; }

	@Override
	public void processInput(String inputName, Object inputValue) {
	    super.processInput(inputName, inputValue);
      if (PROPERTY_KEY.equals(inputName)) 
        setKey((String)inputValue);
      else 
      if (PROPERTY_SEARCH_VAL.equals(inputName))
        setSearchVal((String)inputValue);
    }
}
