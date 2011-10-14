package echopoint;

/* 
 * This file is part of the Echo Point Project.  This project is a collection
 * of Components that have extended the Echo Web Application Framework.
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
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
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nextapp.echo.app.Component;
import echopoint.able.Attributeable;

/**
 * <code>ComponentEx</code> is an abstract component that offers helper
 * methods for getting and setting properties, beyond what the base
 * <code>nextapp.echo2.app.Component</code> does.
 * <p>
 * The <code>hidden</code> property is an interesting one. This is a different
 * visual property to <code>visible</code>.
 * <p>
 * In Echo2 components that are not <code>visible</code> do not exist at all
 * as far as rendering is concerned. They get no property updates and will not
 * exist in the client. The <code>hidden</code> flag simple makes the
 * component "not shown" on the client. It still exists and can/will receive 
 * property updates.
 * <p>
 * The use of this flag can help the application become more efficient because
 * server to client messaging is reduced when a parent component is hidden and
 * then shown, as it may not redraw all sub components.
 * <p>
 * In CSS/XHTML terms, hidden is equivalent to display:none.
 *
 * @author Brad Baker
 * @version $Id: ComponentEx.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public abstract class ComponentEx extends Component implements Attributeable {

	public static final String PROPERTY_HIDDEN = "hidden";

	/**
	 * <code>ComponentEx</code> does not participate in focus traversal by
	 * default.
	 */
	public ComponentEx() {
		setFocusTraversalParticipant(false);
	}

	/**
	 * @return true if the <code>ComponentEx</code> is currently hidden
	 */
	public boolean isHidden() {
		return get(PROPERTY_HIDDEN, false);
	}

	/**
	 * Sets whether this ComponentEx (and its content) is hidden on the client.
	 * <p>
	 * This is a different visual property to <code>visible</code>. In Echo2
	 * components that are not <code>visible</code> do not exist at all as far
	 * as rendering is concerned. They get no property updates and will not
	 * exist in the client. The <code>hidden</code> flag simple makes the
	 * component "not shown" on the client. It still exists and can receive
	 * property updates.
	 * <p>
	 * The use of this flag can help the application become more efficient
	 * because server to client messaging is reduced when a parent component is
	 * hidden and then shown, as it may not redraw all sub components.
	 * <p>
	 * In CSS/XHTML terms, hidden is equivalent to display:none.
	 * 
	 * @param newValue -
	 *            the new value of the hidden flag
	 */
	public void setHidden(boolean newValue) {
		set(PROPERTY_HIDDEN, newValue);
	}

	/**
	 * Helper method to get 'Object' properties. Placed there for completeness
	 * since Component.getProperty() does exactly the same thing.
	 * 
	 * @return - the 'Object' property
	 * @see Component#get(String)
	 */
	public static Object get(Component c, String propertyName) {
		return c.get(propertyName);
	}

    /**
     * Helper method to get 'Object' properties, with a default value.
     * @param c
     * @param propertyName
     * @param defaultValue
     * @return - the 'Object' property
     */
	public static Object get(Component c, String propertyName, Object defaultValue) {
        Object obj = c.get(propertyName);
        return (obj == null ? defaultValue : obj);
	}

	/**
	 * Helper method to get 'boolean' properties, with a default value.
	 * 
	 * @return - the 'boolean' property or the default value if its null
	 * @see Component#get(String)
	 */
	public static boolean get(Component c, String propertyName, boolean defaultValue) {
		Boolean obj = (Boolean) c.get(propertyName);
		return (obj == null ? defaultValue : obj.booleanValue());
	}

	/**
	 * Helper method to get 'byte' properties, with a default value.
	 * 
	 * @return - the 'byte' property or the default value if its null
	 * @see Component#get(String)
	 */
	public static byte get(Component c, String propertyName, byte defaultValue) {
		Byte obj = (Byte) c.get(propertyName);
		return (obj == null ? defaultValue : obj.byteValue());
	}

	/**
	 * Helper method to get 'char' properties, with a default value.
	 * 
	 * @return - the 'char' property or the default value if its null
	 * @see Component#get(String)
	 */
	public static char get(Component c, String propertyName, char defaultValue) {
		Character obj = (Character) c.get(propertyName);
		return (obj == null ? defaultValue : obj.charValue());
	}

	/**
	 * Helper method to get 'double' properties, with a default value.
	 * 
	 * @return - the 'double' property or the default value if its null
	 * @see Component#get(String)
	 */
	public static double get(Component c, String propertyName, double defaultValue) {
		Double obj = (Double) c.get(propertyName);
		return (obj == null ? defaultValue : obj.doubleValue());
	}

	/**
	 * Helper method to get 'float' properties, with a default value.
	 * 
	 * @return - the 'float' property or the default value if its null
	 * @see Component#get(String)
	 */
	public static float get(Component c, String propertyName, float defaultValue) {
		Float obj = (Float) c.get(propertyName);
		return (obj == null ? defaultValue : obj.floatValue());
	}

	/**
	 * Helper method to get 'int' properties, with a default value.
	 * 
	 * @return - the 'int' property or the default value if its null
	 * @see Component#get(String)
	 */
	public static int get(Component c, String propertyName, int defaultValue) {
		Integer obj = (Integer) c.get(propertyName);
		return (obj == null ? defaultValue : obj.intValue());
	}

	/**
	 * Helper method to get 'long' properties, with a default value.
	 * 
	 * @return - the 'long' property or the default value if its null
	 * @see Component#get(String)
	 */
	public static long get(Component c, String propertyName, long defaultValue) {
		Long obj = (Long) c.get(propertyName);
		return (obj == null ? defaultValue : obj.longValue());
	}

	/**
	 * Helper method to get 'short' properties, with a default value.
	 * 
	 * @return - the 'short' property or the default value if its null
	 * @see Component#get(String)
	 */
	public static short get(Component c, String propertyName, short defaultValue) {
		Short obj = (Short) c.get(propertyName);
		return (obj == null ? defaultValue : obj.shortValue());
	}

	/**
	 * Helper method to get 'Object' render properties. Placed there for
	 * completeness since Component.getRenderProperty() does exactly the same
	 * thing.
	 * 
	 * @return - the 'Object' render property
	 * @see Component#getRenderProperty(String, Object)
	 */
	public static Object getRenderProperty(Component c, String propertyName) {
		return c.getRenderProperty(propertyName);
	}

	/**
	 * Helper method to get 'boolean' render properties, with a default value.
	 * 
	 * @return - the 'boolean' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public static boolean getRenderProperty(Component c, String propertyName, boolean defaultValue) {
		Boolean obj = (Boolean) c.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.booleanValue());
	}

	/**
	 * Helper method to get 'byte' render properties, with a default value.
	 * 
	 * @return - the 'byte' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public static byte getRenderProperty(Component c, String propertyName, byte defaultValue) {
		Byte obj = (Byte) c.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.byteValue());
	}

	/**
	 * Helper method to get 'char' render properties, with a default value.
	 * 
	 * @return - the 'char' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public static char getRenderProperty(Component c, String propertyName, char defaultValue) {
		Character obj = (Character) c.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.charValue());
	}

	/**
	 * Helper method to get 'double' render properties, with a default value.
	 * 
	 * @return - the 'double' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public static double getRenderProperty(Component c, String propertyName, double defaultValue) {
		Double obj = (Double) c.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.doubleValue());
	}

	/**
	 * Helper method to get 'float' render properties, with a default value.
	 * 
	 * @return - the 'float' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public static float getRenderProperty(Component c, String propertyName, float defaultValue) {
		Float obj = (Float) c.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.floatValue());
	}

	/**
	 * Helper method to get 'int' render properties, with a default value.
	 * 
	 * @return - the 'int' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public static int getRenderProperty(Component c, String propertyName, int defaultValue) {
		Integer obj = (Integer) c.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.intValue());
	}

	/**
	 * Helper method to get 'long' render properties, with a default value.
	 * 
	 * @return - the 'long' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public static long getRenderProperty(Component c, String propertyName, long defaultValue) {
		Long obj = (Long) c.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.longValue());
	}

	/**
	 * Helper method to get 'Object' render properties. Placed there for
	 * completeness since Component.getRenderProperty() does exactly the same
	 * thing.
	 * 
	 * @return - the 'Object' render property
	 * @see Component#getRenderProperty(String, Object)
	 */
	public static Object getRenderProperty(Component c, String propertyName, Object defaultValue) {
		return c.getRenderProperty(propertyName, defaultValue);
	}

	/**
	 * Helper method to get 'short' render properties, with a default value.
	 * 
	 * @return - the 'short' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public static short getRenderProperty(Component c, String propertyName, short defaultValue) {
		Short obj = (Short) c.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.shortValue());
	}

	/**
	 * Helper method to set 'boolean' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public static void set(Component c, String propertyName, boolean newValue) {
		c.set(propertyName, newValue ? Boolean.TRUE : Boolean.FALSE);
	}

	/**
	 * Helper method to set 'byte' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public static void set(Component c, String propertyName, byte newValue) {
		c.set(propertyName, new Byte(newValue));
	}

	/**
	 * Helper method to set 'char' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public static void set(Component c, String propertyName, char newValue) {
		c.set(propertyName, new Character(newValue));
	}

	/**
	 * Helper method to set 'double' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public static void set(Component c, String propertyName, double newValue) {
		c.set(propertyName, new Double(newValue));
	}

	/**
	 * Helper method to set 'float' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public static void set(Component c, String propertyName, float newValue) {
		c.set(propertyName, new Float(newValue));
	}

	/**
	 * Helper method to set 'int' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public static void set(Component c, String propertyName, int newValue) {
		c.set(propertyName, new Integer(newValue));
	}

	/**
	 * Helper method to set 'long' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public static void set(Component c, String propertyName, long newValue) {
		c.set(propertyName, new Long(newValue));
	}

	/**
	 * Helper method to set 'Object' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public static void set(Component c, String propertyName, Object newValue) {
		c.set(propertyName, newValue);
	}

	/**
	 * Helper method to set 'short' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public static void set(Component c, String propertyName, short newValue) {
		c.set(propertyName, new Short(newValue));
	}

	private Map attributeMap;

	/**
	 * @see echopoint.able.Attributeable#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String attributeName) {
		if (attributeMap != null) {
			return attributeMap.get(attributeName);
		}
		return null;
	}

	/**
	 * @see echopoint.able.Attributeable#getAttributeNames()
	 */
	public String[] getAttributeNames() {
		if (attributeMap == null) {
			return new String[0];
		}
		int count = 0;
		String[] attributeNames = new String[attributeMap.keySet().size()];
		for (Iterator iter = attributeMap.keySet().iterator(); iter.hasNext();) {
			attributeNames[count++] = (String) iter.next();
		}
		return attributeNames;
	}

	/**
	 * Helper method to get 'boolean' properties, with a default value.
	 * 
	 * @return - the 'boolean' property or the default value if its null
	 * @see Component#get(String)
	 */
	public boolean get(String propertyName, boolean defaultValue) {
		Boolean obj = (Boolean) super.get(propertyName);
		return (obj == null ? defaultValue : obj.booleanValue());
	}

	/**
	 * Helper method to get 'byte' properties, with a default value.
	 * 
	 * @return - the 'byte' property or the default value if its null
	 * @see Component#get(String)
	 */
	public byte get(String propertyName, byte defaultValue) {
		Byte obj = (Byte) super.get(propertyName);
		return (obj == null ? defaultValue : obj.byteValue());
	}

	/**
	 * Helper method to get 'char' properties, with a default value.
	 * 
	 * @return - the 'char' property or the default value if its null
	 * @see Component#get(String)
	 */
	public char get(String propertyName, char defaultValue) {
		Character obj = (Character) super.get(propertyName);
		return (obj == null ? defaultValue : obj.charValue());
	}

	/**
	 * Helper method to get 'double' properties, with a default value.
	 * 
	 * @return - the 'double' property or the default value if its null
	 * @see Component#get(String)
	 */
	public double get(String propertyName, double defaultValue) {
		Double obj = (Double) super.get(propertyName);
		return (obj == null ? defaultValue : obj.doubleValue());
	}

	/**
	 * Helper method to get 'float' properties, with a default value.
	 * 
	 * @return - the 'float' property or the default value if its null
	 * @see Component#get(String)
	 */
	public float get(String propertyName, float defaultValue) {
		Float obj = (Float) super.get(propertyName);
		return (obj == null ? defaultValue : obj.floatValue());
	}

	/**
	 * Helper method to get 'int' properties, with a default value.
	 * 
	 * @return - the 'int' property or the default value if its null
	 * @see Component#get(String)
	 */
	public int get(String propertyName, int defaultValue) {
		Integer obj = (Integer) super.get(propertyName);
		return (obj == null ? defaultValue : obj.intValue());
	}

	/**
	 * Helper method to get 'long' properties, with a default value.
	 * 
	 * @return - the 'long' property or the default value if its null
	 * @see Component#get(String)
	 */
	public long get(String propertyName, long defaultValue) {
		Long obj = (Long) super.get(propertyName);
		return (obj == null ? defaultValue : obj.longValue());
	}

	/**
	 * Helper method to get 'short' properties, with a default value.
	 * 
	 * @return - the 'short' property or the default value if its null
	 * @see Component#get(String)
	 */
	public short get(String propertyName, short defaultValue) {
		Short obj = (Short) super.get(propertyName);
		return (obj == null ? defaultValue : obj.shortValue());
	}

	/**
	 * Helper method to get 'boolean' render properties, with a default value.
	 * 
	 * @return - the 'boolean' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public boolean getRenderProperty(String propertyName, boolean defaultValue) {
		Boolean obj = (Boolean) super.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.booleanValue());
	}

	/**
	 * Helper method to get 'byte' render properties, with a default value.
	 * 
	 * @return - the 'byte' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public byte getRenderProperty(String propertyName, byte defaultValue) {
		Byte obj = (Byte) super.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.byteValue());
	}

	/**
	 * Helper method to get 'char' render properties, with a default value.
	 * 
	 * @return - the 'char' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public char getRenderProperty(String propertyName, char defaultValue) {
		Character obj = (Character) super.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.charValue());
	}

	/**
	 * Helper method to get 'double' render properties, with a default value.
	 * 
	 * @return - the 'double' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public double getRenderProperty(String propertyName, double defaultValue) {
		Double obj = (Double) super.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.doubleValue());
	}

	/**
	 * Helper method to get 'float' render properties, with a default value.
	 * 
	 * @return - the 'float' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public float getRenderProperty(String propertyName, float defaultValue) {
		Float obj = (Float) super.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.floatValue());
	}

	/**
	 * Helper method to get 'int' render properties, with a default value.
	 * 
	 * @return - the 'int' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public int getRenderProperty(String propertyName, int defaultValue) {
		Integer obj = (Integer) super.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.intValue());
	}

	/**
	 * Helper method to get 'long' render properties, with a default value.
	 * 
	 * @return - the 'long' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public long getRenderProperty(String propertyName, long defaultValue) {
		Long obj = (Long) super.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.longValue());
	}

	/**
	 * Helper method to get 'short' render properties, with a default value.
	 * 
	 * @return - the 'short' render property or the default value if its null
	 * @see Component#getRenderProperty(String, Object)
	 */
	public short getRenderProperty(String propertyName, short defaultValue) {
		Short obj = (Short) super.getRenderProperty(propertyName);
		return (obj == null ? defaultValue : obj.shortValue());
	}

	/**
	 * @see echopoint.able.Attributeable#setAttribute(java.lang.String,
	 *      java.lang.Object)
	 */
	public void setAttribute(String attributeName, Object attributeValue) {
		if (attributeMap == null) {
			attributeMap = new HashMap();
		}
		attributeMap.put(attributeName, attributeValue);
	}

	/**
	 * Helper method to set 'boolean' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public void set(String propertyName, boolean newValue) {
		super.set(propertyName, Boolean.valueOf(newValue));
	}

	/**
	 * Helper method to set 'byte' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public void set(String propertyName, byte newValue) {
		super.set(propertyName, new Byte(newValue));
	}

	/**
	 * Helper method to set 'char' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public void set(String propertyName, char newValue) {
		super.set(propertyName, new Character(newValue));
	}

	/**
	 * Helper method to set 'double' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public void set(String propertyName, double newValue) {
		super.set(propertyName, new Double(newValue));
	}

	/**
	 * Helper method to set 'float' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public void set(String propertyName, float newValue) {
		super.set(propertyName, new Float(newValue));
	}

	/**
	 * Helper method to set 'int' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public void set(String propertyName, int newValue) {
		super.set(propertyName, new Integer(newValue));
	}

	/**
	 * Helper method to set 'long' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public void set(String propertyName, long newValue) {
		super.set(propertyName, new Long(newValue));
	}

	/**
	 * Helper method to set 'short' property values
	 * 
	 * @see Component#set(String, Object)
	 */
	public void set(String propertyName, short newValue) {
		super.set(propertyName, new Short(newValue));
	}

}
