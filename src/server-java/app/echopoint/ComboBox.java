package echopoint;

import nextapp.echo.app.ResourceImageReference;
import echopoint.model.AutoLookupSelectFieldModel;
import echopoint.model.ComboBoxModel;
import echopoint.model.DefaultComboBoxModel;


public class ComboBox extends AutoLookupSelectField 
{
	private static final long serialVersionUID = 22091226L;
  protected static final String PROPERTY_COMBO_LIST_CHANGED = "comboListChanged";
  private static final String PROPERTY_COMBOBOX_MODE  = "comboboxMode";
  public  static final String PROPERTY_CASE_SENSITIVE = "caseSensitive";
  public  static final String PROPERTY_ADDABLE_MODE   = "addableMode";
  public  static final String PROPERTY_LAZY_MODE      = "lazyMode";
  public static final ResourceImageReference default_popup_icon = new ResourceImageReference( "/resource/images/Decrement.gif" );

  private class ComboBoxModelListner implements ComboBoxModel.Listener
  {
    public void entriesListChanged()
    {
    	firePropertyChange(PROPERTY_COMBO_LIST_CHANGED, null, null);
    }
  }
  
  private final ComboBoxModelListner combo_model_listener = new ComboBoxModelListner();

	/**
	 * Creates a new <code>ComboBox</code> with the specified model.
	 */
  public ComboBox(ComboBoxModel model)  
  { 
    super(); 
    this.setComboBoxModel(model);
    this.setPopupIcon(default_popup_icon); 
    this.setNoMatchingOptionText("");
    this.setSearchBarSearchingText("");
    this.set(PROPERTY_COMBOBOX_MODE, Boolean.TRUE);
    this.set(PROPERTY_CASE_SENSITIVE, Boolean.TRUE);
    this.set(PROPERTY_ADDABLE_MODE, Boolean.TRUE);
  }
  
  /**
    * Creates a new <code>ComboBox</code> with the default model.
    */
  public ComboBox()
  {
    this( new DefaultComboBoxModel() );
  }
    
  public boolean getCaseSensitive() { return ( (Boolean)get(PROPERTY_CASE_SENSITIVE) ).booleanValue(); }
  public void    setCaseSensitive(boolean b) { set(PROPERTY_CASE_SENSITIVE, Boolean.valueOf(b)); }

  /**
    * If you switch off the addable mode you can not add new entries.
    */
  public void    setAddableMode(boolean b) { set(PROPERTY_ADDABLE_MODE, Boolean.valueOf(b)); }
  public boolean getAddableMode() { return ((Boolean)get(PROPERTY_ADDABLE_MODE)).booleanValue(); }

  /**
    * The "portion" parameter determins on what portions the calculated elements should be visualized in the menu.
    * Using this mechanism you may load unlimited count of elements in the menu.
    * Null value for the "portion" parameter disable this mechanism.
    */
  public void    setLazyMode(Integer portion) { set(PROPERTY_LAZY_MODE, portion); }
  public Integer getLazyMode() { return (Integer)get(PROPERTY_LAZY_MODE); }

	public ComboBoxModel getComboBoxModel() { return (ComboBoxModel) get(PROPERTY_AUTO_LOOKUP_MODEL); }

	public void setComboBoxModel(ComboBoxModel model) 
  {
    ComboBoxModel old_model = getComboBoxModel();
    if( old_model != null ) old_model.removeComboBoxModelListener( combo_model_listener );
    model.addComboBoxModelListener( combo_model_listener );
		set(PROPERTY_AUTO_LOOKUP_MODEL, model);
	}

  public AutoLookupSelectFieldModel getAutoLookupFieldModel() {  throw new UnsupportedOperationException("Not supported for ComboBox: use getComboBoxModel()."); }
  public void setAutoLookupFieldModel(AutoLookupSelectFieldModel autoLookupModel) {  throw new UnsupportedOperationException("Not supported for ComboBox: use setComboBoxModel(...).");  }

  public void init() 
  { 
    super.init();  
    ComboBoxModel model = getComboBoxModel();
    if( model != null ) model.addComboBoxModelListener( combo_model_listener );
  }

  public void dispose() 
  {
    super.dispose();  
    ComboBoxModel model = getComboBoxModel();
    if( model != null ) model.removeComboBoxModelListener( combo_model_listener );
  }
}
