package echopoint.internal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.OutputKeys;

import nextapp.echo.app.util.DomUtil;
import nextapp.echo.webcontainer.Connection;
import nextapp.echo.webcontainer.ContentType;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.SynchronizationException;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.WindowHtmlService;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import echopoint.AutoLookupSelectField;
import echopoint.model.AutoLookupSelectFieldModel;
import echopoint.model.AutoLookupSelectModel;
import echopoint.model.AutoLookupSelectModel.EntrySelect;

public class AutoLookupSelectService implements Service {
	
	private static final AutoLookupSelectService INSTANCE;
	private java.util.Map interestedParties = new HashMap();
	public static final String SERVICE_ID = "echopoint.AutoLookupSelectService";
	
	public static final Properties OUTPUT_PROPERTIES = new Properties();
    
    static {
        // The XML declaration is omitted as Internet Explorer 6 will operate in quirks mode if it is present.
        OUTPUT_PROPERTIES.setProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        OUTPUT_PROPERTIES.putAll(DomUtil.OUTPUT_PROPERTIES_INDENT);
        OUTPUT_PROPERTIES.setProperty(OutputKeys.DOCTYPE_PUBLIC, WindowHtmlService.XHTML_1_0_TRANSITIONAL_PUBLIC_ID);
        OUTPUT_PROPERTIES.setProperty(OutputKeys.DOCTYPE_SYSTEM, WindowHtmlService.XHTML_1_0_TRANSITIONAL_SYSTEM_ID);
    }
    
	static {
		INSTANCE = new AutoLookupSelectService();
	}
	
	public static AutoLookupSelectService getInstance(){
		return INSTANCE;
	}
	
	public static void install() {
		WebContainerServlet.getServiceRegistry().add(INSTANCE);
	}
	
	private  AutoLookupSelectService(){
	}

	@Override
	public String getId() {
		return SERVICE_ID;
	}

	@Override
	public int getVersion() {
		return DO_NOT_CACHE;
	}

	@Override
	public void service(Connection conn) throws IOException {
		HttpServletRequest request = conn.getRequest();
    request.setCharacterEncoding("utf-8");

		String elementId = request.getParameter("elementId");
		String searchValue =  request.getParameter("searchValue");

		AutoLookupSelectField textFieldEx = (AutoLookupSelectField) interestedParties.get(elementId);
		if (textFieldEx == null) {
			System.out.println(interestedParties);
			throw new IllegalStateException("Can not found AutolookupSelectField:" + elementId + ".");
		}
		AutoLookupSelectModel autoLookupModel = textFieldEx.getAutoLookupModel();
		if (autoLookupModel == null)
      throw new IllegalStateException("Can not found AutolookupSelectModel for AutolookupSelectField:" + elementId + ".");

    Document doc = DomUtil.createDocument("xml", null, null, null);
 		Element dataElement = doc.getDocumentElement();
		Element autoLookupModelE = doc.createElement("autoLookupModel");

		List<EntrySelect> entries = searchValue == null ? autoLookupModel.getAllEntries() : ( (AutoLookupSelectFieldModel)autoLookupModel ).searchEntries(searchValue);
		if (entries != null) {
			for (EntrySelect entry : entries) {
				Element entryE = doc.createElement("entry");
				autoLookupModelE.appendChild(entryE);
				
				Element valueE = doc.createElement("value");
				valueE.setTextContent(entry.getValue());
				entryE.appendChild(valueE);
				
				Element keyE = doc.createElement("key");
				keyE.setTextContent(entry.getKey());
				entryE.appendChild(keyE);
				
				Element searchE = doc.createElement("searchVal");
				searchE.setTextContent(entry.getSearchVal());
				entryE.appendChild(searchE);
			}
		}
		dataElement.appendChild(autoLookupModelE);

		conn.setContentType(ContentType.TEXT_XML);
    try {
      DomUtil.save(doc, conn.getWriter(), OUTPUT_PROPERTIES);
    } catch (SAXException ex) {
        throw new SynchronizationException("Failed to write HTML document.", ex);
    }
	}
	
	public synchronized void register(AutoLookupSelectField selectFieldEx) {
		interestedParties.put("C." + selectFieldEx.getRenderId(), selectFieldEx);
	}
	
	public synchronized void deregister(AutoLookupSelectField selectFieldEx) {
		interestedParties.remove(selectFieldEx);
	}
}
