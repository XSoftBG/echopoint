package echopoint;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nextapp.echo.app.Component;
import nextapp.echo.app.Label;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.model.AutoLookupSelectFieldModel;

/***
 * Unit test for the {@link AutoLookupSelectField} component
 * @author Aníbal Rivero 2009-11-22
 *
 */
public class AutoLookupSelectFieldTest extends AbstractTest<AutoLookupSelectField> {
	public static class AutoLookupSelectModelTest implements AutoLookupSelectFieldModel{
		public HashMap<String, EntryTest> entries;
		
		public static class EntryTest implements EntrySelect{
			private String key;
			private String searchVal;
			private String value;
			public EntryTest(Integer key, String searchVal, String title) {
				this.key = key.toString();
				this.searchVal = searchVal;
				this.value = title;
			}
			
			@Override
			public String getKey() {
				return key;
			}
			@Override
			public String getSearchVal() {
				
				return searchVal;
			}
			@Override
			public String getValue() {
				
				return value;
			}
		}
		
		public AutoLookupSelectModelTest() {
				entries = new HashMap<String, EntryTest>();
				entries.put("a", new EntryTest(1, "a", "1:A"));
				entries.put("aaa", new EntryTest(2, "aaa", "2:Aaa"));
				entries.put("aba", new EntryTest(3, "aba", "3:Aba"));
				entries.put("cabinet", new EntryTest(4, "cab", "4:Cab"));
				entries.put("nosense", new EntryTest(5, "nosense", "5:No sense (search as 'nosense')"));
		}
		
		
		
		@Override
		public List<EntrySelect> searchEntries(String searchString) {
			List<EntrySelect> result = new ArrayList<EntrySelect>();
			for(String key : entries.keySet()){
				if(key.startsWith(searchString)){
					result.add(entries.get(key));
				}
			}
			return result;
		}
		
		@Override
		public List<EntrySelect> getAllEntries() {
			return new ArrayList<EntrySelect>(entries.values());
		}
	}
	
	
	
	@BeforeClass
	public static void init(){
		AutoLookupSelectField autoLookupSelectField = new AutoLookupSelectField();
		autoLookupSelectField.setAutoLookupFieldModel(new AutoLookupSelectModelTest());
		set(autoLookupSelectField);
	}
	
	@Test
	public void renderId(){
		final String renderId = "echopointUnitTestAutoLookupSelectField";
	    getComponent().setRenderId( renderId );
	    assertEquals( "Ensure renderId set", renderId, getComponent().getRenderId() );
	}
	
	@AfterClass
	public static void finish(){
		final Component content = Application.getContent().getTestArea();
	    content.removeAll();
	    final Row row = new Row();
	    row.add( createLabel() );
	    row.add( new Strut() );
	    row.add( get() );

	    final Label label = createResponse();
	    final AutoLookupSelectField alsf = (AutoLookupSelectField) get();
	    addListener( alsf, label );

	    content.add( new Strut() );
	    content.add( row );
	    content.add( new Strut() );
	    content.add( createNote() );
	    content.add( new Strut() );
	    content.add( label );
	  }

	  private static Component createLabel()
	  {
	    final Label label = new Label();
	    label.setRenderId( "echopointUnitTestAutolookupSelectFieldLabel" );
	    label.setText( "Try entering characters" );

	    return label;
	  }

	  private static Component createNote()
	  {
	    final HtmlLabel label =  new HtmlLabel();
	    label.setRenderId( "echopointUnitTestAutolookupSelectFieldNote" );
	    label.setText(
	        "The label should show the different elements of the entry selected.");
	    return label;
	  }

	  private static Label createResponse()
	  {
	    final Label label = new Label();
	    label.setRenderId( "echopointUnitTestAutolookupSelectFieldResponse" );
	    label.setText( "Label to echo data of the selected entry" );

	    return label;
	  }
	  
	  private static void addListener( final AutoLookupSelectField field, final Label label )
	  {
	    field.addActionListener( new ActionListener()
	    {
	      private static final long serialVersionUID = 1L;

	      public void actionPerformed( final ActionEvent event )
	      { 
	    	String selected = ((AutoLookupSelectModelTest)field.getAutoLookupModel()).entries.get(field.getSearchVal()).getValue();
	        label.setText( 
	        		"Selected object: " + selected +
	        		"| key: " + field.getKey() +
	        		"| search value: " + field.getSearchVal()
	        );
	        field.setText(field.getSearchVal());
	      }
	    });
	  }
}
