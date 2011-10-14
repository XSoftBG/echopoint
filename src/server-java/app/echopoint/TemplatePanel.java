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

import java.util.HashMap;
import java.util.Map;

import nextapp.echo.app.Component;
import nextapp.echo.app.PaneContainer;
import echopoint.template.TemplateDataSource;

/**
 * <code>TemplatePanel</code> is a container that uses a
 * <code>TemplateLayoutData</code> to render a template of content.
 * <p>
 * This layout data can itself contained "named" Components and "named" Text
 * Substitutions.
 * <p>
 * A singleton <code>TemplateDataSource</code> can be used for more than one
 * <code>TemplatePanel</code> and hence the memory footprint is reduced if
 * used in this way.
 * <p>
 * <h3>Supplied Implementation</h3>
 * <p>
 * The supplied template implementation reads XHTML template data from a number
 * of different sources including Files, Strings, Resources and JSP pages.
 * <p>
 * The markup :
 *
 * <pre><code>
 *
 *
 *   &lt;component name=&quot;xxx&quot; /&gt;
 *
 *
 * </code></pre>
 *
 * is used to indicate where in the template a named component will be placed.
 * <p>
 *
 * In EchopointNG it was possible to set style and css properties. This feature is not implemented yet.
 *
 */
public class TemplatePanel extends Component implements PaneContainer {


	public static final String PROPERTY_TEMPLATE_DATA_SOURCE = "templateDataSource";
    public static final String PROPERTY_COMPONENT_MAPPING = "componentmapping";

    /**
     * Constructs a <code>TemplatePanel</code> with no template data as yet.
     */
    public TemplatePanel() {
        super();
        set(PROPERTY_COMPONENT_MAPPING, new HashMap());
    }

    /**
     * Constructs a <code>TemplatePanel</code> with the specified
     * TemplateDataSource.
     *
     * @param tds -
     *                 the source for the template data.
     */
    public TemplatePanel(TemplateDataSource tds) {
        super();
		setTemplateDataSource(tds);
        set(PROPERTY_COMPONENT_MAPPING, new HashMap());
    }


	/**
	 * Sets the TemplateDataSource to be used
	 *
	 * @param templateDataSource
	 *            the TemplateDataSource to be used
	 */
	public void setTemplateDataSource(TemplateDataSource templateDataSource) {
		set(PROPERTY_TEMPLATE_DATA_SOURCE, templateDataSource);
	}

    /**
	 * Returns the TemplateDataSource which contains the template data.
	 *
	 * @return the TemplateDataSource in place
	 */
    public TemplateDataSource getTemplateDataSource()
    {
        return (TemplateDataSource) get(PROPERTY_TEMPLATE_DATA_SOURCE);
    }


   /**
	 * Adds a component to the <code>TemplatePanel</code> with the associated
	 * name which can the be references from the template data.
	 *
	 * @param component
	 *            the component to add
	 * @param componentName -
	 *            the name to use when referencing this component in the
	 *            template data.
	 */
    public void addNamedComponent(Component component, String componentName) {
        if (component == null)
            throw new IllegalArgumentException("component must be non null.");
        if (componentName == null)
            throw new IllegalArgumentException("componentName must be non null.");

        ((Map) get(PROPERTY_COMPONENT_MAPPING)).put(componentName, component);
        add(component);
    }

    Map getComponentMapping()
    {
        return (Map) get(PROPERTY_COMPONENT_MAPPING); 
    }

    /**
     * Returns the name associated with the component or null if it cant be
     * found.
     *
     * @param component -
     *                  the component associated with the name
     * @return a name associated with component
     */
    public String getComponentName(Component component) {
        String componentNames[] = getNamedComponents();
        for (int i = 0; i < componentNames.length; i++) {
            Component c = getNamedComponent(componentNames[i]);
            if (component == c) {
                return componentNames[i];
            }
        }
        return null;
    }


    /**
	 * Returns a component associated with the name or null if it cant be found.
	 *
	 * @param componentName -
	 *            the name associated with the component
	 * @return a component associated with componentName
	 */
	public Component getNamedComponent(String componentName) {
		return (Component) ((Map) get(PROPERTY_COMPONENT_MAPPING)).get((componentName));
	}

	/**
	 * @return an array of all the component names in the
	 *         <code>TemplatePanel</code>.
	 */
	public String[] getNamedComponents() {
        Map componentNameMap = ((Map) get(PROPERTY_COMPONENT_MAPPING));
		return (String[]) componentNameMap.keySet().toArray(new String[componentNameMap.keySet().size()]);
	}


    /**
	 * @see nextapp.echo.app.Component#remove(nextapp.echo.app.Component)
	 */
	public void remove(Component c) {
		String componentName = getComponentName(c);
		if (componentName != null) {
			((Map) get(PROPERTY_COMPONENT_MAPPING)).remove(componentName);
		}
		super.remove(c);
	}

	/**
	 * Removes a named component from the <code>TemplatePanel</code> that was
	 * previously added via the <code>addNamedComponent()</code> method.
	 *
	 * @param componentName -
	 *            the name of the component
	 */
	public void removeNamedComponent(String componentName) {
		if (componentName == null)
			throw new IllegalArgumentException("componentName must be non null.");

		Component child = getNamedComponent(componentName);
		((Map) get(PROPERTY_COMPONENT_MAPPING)).remove(componentName);
		if (child != null) {
			remove(child);
		}
	}

}
