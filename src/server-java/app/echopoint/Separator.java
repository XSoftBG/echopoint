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

import nextapp.echo.app.Color;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.MutableStyle;
import nextapp.echo.app.Style;
import echopoint.able.Insetable;

/**
 * The <code>Separator</code> class is a <code>Component</code>
 * that provides a simple separator within menus or between
 * other <code>Components</code>
 * <p>
 * It consists of a top line and bottom line, that can have width
 * and color values.  It also has an Inset value around it.
 *
 * @author Brad Baker <p>Modified by HansH 2009-04-03</p>
 * @version $Id: Separator.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */

public class Separator extends ComponentEx implements Insetable {

	public static final String PROPERTY_BOTTOM_SIZE = "bottomSize";
	public static final String PROPERTY_BOTTOM_COLOR = "bottomColor";
	public static final String PROPERTY_TOP_SIZE = "topSize";
	public static final String PROPERTY_TOP_COLOR = "topColor";

	/** the default bottom color */
	public static final Color DEFAULT_BOTTOM_COLOR 	= new Color(0xF0,0xF0,0xF0);
	/** the default insets are {4,2} */
	public static final Insets DEFAULT_INSETS 		= new Insets(4,2);
	/** the default top color */
	public static final Color DEFAULT_TOP_COLOR 	= new Color(0x90,0x90,0x90);
	/** the default top size is 1 */
	public static final Extent DEFAULT_TOP_SIZE		= new Extent(1);
	/** the default bottom size is 1 */
	public static final Extent DEFAULT_BOTTOM_SIZE	= new Extent(1);

    public static final Style DEFAULT_STYLE;


	static {
		MutableStyle style = new MutableStyle();
		style.set(PROPERTY_BOTTOM_COLOR,DEFAULT_BOTTOM_COLOR);
		style.set(PROPERTY_BOTTOM_SIZE,DEFAULT_BOTTOM_SIZE);
		style.set(PROPERTY_TOP_COLOR,DEFAULT_TOP_COLOR);
		style.set(PROPERTY_TOP_SIZE,DEFAULT_TOP_SIZE);
		style.set(PROPERTY_INSETS,DEFAULT_INSETS);
		style.set(PROPERTY_OUTSETS,DEFAULT_OUTSETS);
		DEFAULT_STYLE = style;
	}

	/**
	 * Constructs a <code>Separator</code>.
	 *
	 */
	public Separator() {
		super();
        setStyle(DEFAULT_STYLE);
	}
	/**
	 * The color of the bottom separator line
	 * @return The color of the bottom separator line
	 */
	public Color getBottomColor() {
		return (Color) get(this, PROPERTY_BOTTOM_COLOR, DEFAULT_BOTTOM_COLOR);
	}

	/**
	 * The size of the bottom separator line
	 * @return The size of the bottom separator line
	 */
	public Extent getBottomSize() {
		return (Extent) get(this, PROPERTY_BOTTOM_SIZE, DEFAULT_BOTTOM_SIZE);
	}

	/**
	 * The color of the top separator line
	 * @return The color of the top separator line
	 */
	public Color getTopColor() {
		return (Color) get(this, PROPERTY_TOP_COLOR, DEFAULT_TOP_COLOR);
	}

	/**
	 * The size of the top separator line
	 * @return The color of the top separator line
	 */
	public Extent getTopSize() {
		return (Extent) get(this, PROPERTY_TOP_SIZE, DEFAULT_TOP_SIZE);
	}

	public void setBottomColor(Color color) {
		set(PROPERTY_BOTTOM_COLOR,color);
	}

	public void setBottomSize(Extent newValue) {
		set(PROPERTY_BOTTOM_SIZE,newValue);
	}

	public void setTopColor(Color newValue) {
		set(PROPERTY_TOP_COLOR,newValue);
	}

	public void setTopSize(Extent newValue) {
		set(PROPERTY_TOP_SIZE,newValue);
	}
	/**
	 * @see echopoint.able.Insetable#getInsets()
	 */
	public Insets getInsets() {
		return (Insets) get(this, PROPERTY_INSETS, DEFAULT_INSETS);
	}
	/**
	 * @see echopoint.able.Insetable#getOutsets()
	 */
	public Insets getOutsets() {
		return (Insets) get(this, PROPERTY_OUTSETS, DEFAULT_OUTSETS);
	}
	/**
	 * @see echopoint.able.Insetable#setInsets(nextapp.echo.app.Insets)
	 */
	public void setInsets(Insets newValue) {
		set(PROPERTY_INSETS,newValue);
	}
	/**
	 *  @see echopoint.able.Insetable#setOutsets(nextapp.echo.app.Insets)
	 */
	public void setOutsets(Insets newValue) {
		set(PROPERTY_OUTSETS,newValue);
	}
}
