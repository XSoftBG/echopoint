/*
 * This file is part of the Echo Point Project.  This project is a
 * collection of Components that have extended the Echo Web Application
 * Framework Version 3.
 *
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */
package echopoint;

import java.util.Map;

import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;
import echopoint.internal.AbstractPeer;
import echopoint.template.TemplateDataSource;
import echopoint.template.ui.TemplateCompiler;
import echopoint.template.ui.TemplateCompilerLoader;

/**
 * <code>TemplatePanelPeer</code> is a peer for <code>TemplatePanel</code>
 * @version $Id: TemplatePanelPeer.java,v 1.1.1.1 2010-04-01 09:47:32 perxi Exp $
 */
public class TemplatePanelPeer extends AbstractPeer {

    /**
     * The component name for which this class is a peer.
     */
    private static final String COMPONENT_NAME = TemplatePanel.class.getName();


    /**
     * The JS service files to load.
     */
    private static final String[] SERVICE_FILES =
            {
                    "resource/js/xbImportNode.js",
                    "resource/js/Application.TemplatePanel.js",
                    "resource/js/Sync.TemplatePanel.js"
            };


    /**
     * The service for the client side peer for this component.
     */
    private static final Service COMPONENT_SERVICE =
            JavaScriptService.forResources(COMPONENT_NAME, SERVICE_FILES);

    static {
        WebContainerServlet.getServiceRegistry().add(COMPONENT_SERVICE);
    }

    public String getClientComponentType(boolean b) {
        return COMPONENT_NAME;
    }

    public Class getComponentClass() {
        return TemplatePanel.class;
    }

    public void init(final Context context, final Component component) {
        super.init(context, component);
        final ServerMessage serverMessage =
                (ServerMessage) context.get(ServerMessage.class);
        serverMessage.addLibrary(COMPONENT_NAME);
    }

    public Object getOutputProperty(final Context context,
                                    final Component component, final String propertyName,
                                    final int propertyIndex) {
        if (TemplatePanel.PROPERTY_COMPONENT_MAPPING.equals(propertyName)) {
           Map<String, Component> componentMap = ((TemplatePanel) component).getComponentMapping();
            StringBuffer xmlStr = new StringBuffer("<mapping>");
            for (String s : componentMap.keySet()) {
                    Component c = componentMap.get(s);
                    xmlStr.append("<component name=\"");
                    xmlStr.append(s);
                    xmlStr.append("\">");
                    xmlStr.append(c.getRenderId());
                    xmlStr.append("</component>");
                }
            xmlStr.append("</mapping>");
            return xmlStr.toString();
        }
        else if (TemplatePanel.PROPERTY_TEMPLATE_DATA_SOURCE.equals(propertyName))
        {
            TemplateDataSource tds = ((TemplatePanel) component).getTemplateDataSource();
            TemplateCompilerLoader loader = TemplateCompilerLoader.forClassLoader(Thread.currentThread().getContextClassLoader());
		    TemplateCompiler compiler = loader.getTemplateCompiler(tds.getContentType());
		    if (compiler == null) {
			    throw new IllegalStateException("A TemplateCompiler cannot be found for content type : " + tds.getContentType());
		    }
            try {
                return compiler.templateDataAsString(WebContainerServlet.getActiveConnection(), tds);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return super.getOutputProperty(
                context, component, propertyName, propertyIndex);
    }

}

