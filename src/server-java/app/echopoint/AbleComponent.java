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
package echopoint;

import nextapp.echo.app.Border;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import echopoint.able.AccessKeyable;
import echopoint.able.Borderable;
import echopoint.able.Insetable;
import echopoint.able.MouseCursorable;
import echopoint.able.Sizeable;
import echopoint.able.ToolTipable;

/** 
 * <code>AbleComponent</code> is an abstract Component that implements
 * a number of the 'able interfaces and serves as the base for other components
 */

public abstract class AbleComponent extends ComponentEx implements AccessKeyable, Borderable, MouseCursorable, Insetable, Sizeable, ToolTipable {

	/**
	 * @see echopoint.able.AccessKeyable#getAccessKey()
	 */
	public String getAccessKey() {
		return (String) get(PROPERTY_ACCESS_KEY);
	}

	/**
	 * 
	 * @see echopoint.able.Borderable#getBorder()
	 */
	public Border getBorder() {
		return (Border) get(PROPERTY_BORDER);
	}

	/**
	 * @see echopoint.able.Heightable#getHeight()
	 */
	public Extent getHeight() {
		return (Extent) get(PROPERTY_HEIGHT);
	}

	/**
	 * @see echopoint.able.Insetable#getInsets()
	 */
	public Insets getInsets() {
		return (Insets) get(PROPERTY_INSETS);
	}

	/**
	 * @see echopoint.able.MouseCursorable#getMouseCursor()
	 */
	public int getMouseCursor() {
	    return get(PROPERTY_MOUSE_CURSOR,CURSOR_AUTO);
	}

	/**
	 * @see echopoint.able.MouseCursorable#getMouseCursorUri()
	 */
	public String getMouseCursorUri() {
	    return (String) get(PROPERTY_MOUSE_CURSOR_URI);
	}

	/**
	 * @see echopoint.able.Insetable#getOutsets()
	 */
	public Insets getOutsets() {
		return (Insets) get(PROPERTY_OUTSETS);
	}

	/**
	 * @see echopoint.able.Widthable#getWidth()
	 */
	public Extent getWidth() {
		return (Extent) get(PROPERTY_WIDTH);
	}

	/**
	 * @see echopoint.able.AccessKeyable#setAccessKey(java.lang.String)
	 */
	public void setAccessKey(String newValue) {
		set(PROPERTY_ACCESS_KEY,newValue);
	}

	/**
	 * @see echopoint.able.Borderable#setBorder(nextapp.echo.app.Border)
	 */
	public void setBorder(Border newValue) {
		set(PROPERTY_BORDER,newValue);
	}

	/**
	 * @see echopoint.able.Heightable#setHeight(nextapp.echo.app.Extent)
	 */
	public void setHeight(Extent newValue) {
		set(PROPERTY_HEIGHT,newValue);
	}

	/**
	 * @see echopoint.able.Insetable#setInsets(nextapp.echo.app.Insets)
	 */
	public void setInsets(Insets newValue) {
		set(PROPERTY_INSETS,newValue);
	}

	/**
	 * @see echopoint.able.MouseCursorable#setMouseCursor(int)
	 */
	public void setMouseCursor(int mouseCursor) {
	    set(PROPERTY_MOUSE_CURSOR,mouseCursor);
	}

	/**
	 * @see echopoint.able.MouseCursorable#setMouseCursorUri(java.lang.String)
	 */
	public void setMouseCursorUri(String mouseCursorURI) {
	    set(PROPERTY_MOUSE_CURSOR_URI,mouseCursorURI);
	}

	/**
	 *  @see echopoint.able.Insetable#setOutsets(nextapp.echo.app.Insets)
	 */
	public void setOutsets(Insets newValue) {
		set(PROPERTY_OUTSETS,newValue);
	}

	/**
	 * @see echopoint.able.Widthable#setWidth(nextapp.echo.app.Extent)
	 */
	public void setWidth(Extent newValue) {
		set(PROPERTY_WIDTH,newValue);
	}

	/**
	 * @see echopoint.able.ToolTipable#getToolTipText()
	 */
	public String getToolTipText() {
		return (String) get(PROPERTY_TOOL_TIP_TEXT);
	}

	/**
	 * @see echopoint.able.ToolTipable#setToolTipText(java.lang.String)
	 */
	public void setToolTipText(String newValue) {
		set(PROPERTY_TOOL_TIP_TEXT, newValue);
	
	}
}
