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


import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Color;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.Insets;
import nextapp.echo.app.PaneContainer;
import echopoint.able.Alignable;
import echopoint.able.BackgroundImageable;
import echopoint.able.Positionable;
import echopoint.able.ScrollBarProperties;
import echopoint.able.Scrollable;
import echopoint.able.Stretchable;

/**
 * ContainerEx is a component that can be positioned anywhere on the screen with an specified size attributes.
 * By default, the children of ContainerEx are layed out one after the other, left to right and without any
 * other specified processing. Therefore to get more precise layout within the ContainerEx, you may want to
 * consider using a Column/Row/Grid as the child of this component or you can associated a DisplayLayoutData object
 * with each child component and use that to position the children where you want.
 *
 * This component is a PaneContainer and hence can have components that implement Pane as a child.
 * However many Panes, such as SplitPane, require a definite height to be set in order to work properly.
 * So make sure you call setHeight() if one of the children implements Pane
 * @author Brad Baker <p>Modified by Hans Holmlund 2009-04-20</p>
 * @version $Id: ContainerEx.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class ContainerEx extends AbleComponent implements Alignable, PaneContainer, Positionable, Scrollable, BackgroundImageable, Stretchable {

    private static final Extent PX_0 = new Extent(0);
    private static final Extent SCROLL_BOTTOM = new Extent(-1);

    public static final String PROPERTY_HORIZONTAL_SCROLL = "horizontalScroll";
    public static final String PROPERTY_VERTICAL_SCROLL = "verticalScroll";
    public static final String PROPERTY_LAYOUT_STYLE = "layoutStyle";

    public static final int DEFAULT_LAYOUT = 0;
    public static final int COLUMN_LAYOUT = 1;
    public static final int ROW_LAYOUT = 2;


    /**
     * Creates a new <code>ContentPane</code>.
     */
    public ContainerEx() {
        super();
    }

    /**
     * This sets all the positioning attributes (left,top,right,bottom,z-index)
     * to null or zero.
     */
    public void clear() {

    }

    /**
     * Returns the bottom Y position of the component
     */
    public Extent getBottom() {
        return (Extent) get(PROPERTY_BOTTOM);
    }

    /**
     * Returns the left X position of the component
     */
    public Extent getLeft() {
        return (Extent) get(PROPERTY_LEFT);
    }

    /**
     * This can be one of :
     * <ul>
     * <li>POSITIONING_STATIC</li>
     * <li>POSITIONING_RELATIVE</li>
     * <li>POSITIONING_ABSOLUTE</li>
     * <li>POSITIONING_FIXED</li>
     * </ul>
     */
    public int getPosition() {
        return get(PROPERTY_POSITION, RELATIVE);        
    }

    /**
     * Returns the right X position of the component
     */
    public Extent getRight() {
        return (Extent) get(PROPERTY_RIGHT);
    }

    /**
     * Returns the top Y position of the component
     */
    public Extent getTop() {
        return (Extent) get(PROPERTY_TOP);
    }

    /**
     * Returns the z-index of the component
     */
    public int getZIndex() {
        return get(PROPERTY_Z_INDEX,Integer.MIN_VALUE);
    }

    /**
     * This returns true if any positioning is in place other than
     * normal flow ie. STATIC.
     *
     */
    public boolean isPositioned() {
        return getPosition() != STATIC;
    }

    /**
     * Sets the bottom Y position of the component
     */
    public void setBottom(Extent newValue) {
        set(PROPERTY_BOTTOM,newValue);
    }

    /**
     * Set the left X position of the component
     */
    public void setLeft(Extent newValue) {
        set(PROPERTY_LEFT,newValue);        
    }

    /**
     * Sets the position of the component
     *
     * This can be one of :
     * <ul>
     * <li>POSITIONING_STATIC</li>
     * <li>POSITIONING_RELATIVE</li>
     * <li>POSITIONING_ABSOLUTE</li>
     * <li>POSITIONING_FIXED</li>
     * </ul>
     */
    public void setPosition(int newPositioning) {
        set(PROPERTY_POSITION,newPositioning);        
    }

    /**
     * Sets the right X position of the component
     */
    public void setRight(Extent newValue) {
        set(PROPERTY_RIGHT,newValue);
    }

    /**
     * Sets the top Y position of the component
     */
    public void setTop(Extent newValue) {
        set(PROPERTY_TOP,newValue);
    }

    /**
     * Sets the z-index of the component
     */
    public void setZIndex(int newValue) {
        set(PROPERTY_Z_INDEX,newValue);        
    }

    /**
     * Returns the <code>Border</code> that encloses the entire <code>Clock</code>.
     *
     * @return the border
     */
    public Border getBorder() {
        return (Border) get(PROPERTY_BORDER);
    }

         /**
     * Sets the <code>Border</code> that encloses the entire <code>Clock</code>.
     *
     * @param newValue the new border
     */
    public void setBorder(Border newValue) {
        set(PROPERTY_BORDER, newValue);
    }


    /**
     * Returns the background image.
     *
     * @return the background image
     */
    public FillImage getBackgroundImage() {
        return (FillImage) get(PROPERTY_BACKGROUND_IMAGE);
    }

    /**
     * Returns the horizontal scrollbar position.   todo
     *
     * @return the horizontal scrollbar position
     */
    public Extent getHorizontalScroll() {
        return (Extent) get(PROPERTY_HORIZONTAL_SCROLL);
    }

    /**
     * Returns the inset margin of the content.
     * Note that <code>FloatingPane</code>s, such as
     * <code>WindowPane</code>s, will NOT be constrained by
     * this margin.
     * Values may only be specified in pixel-based units.
     *
     * @return newValue the inset margin
     */
    public Insets getInsets() {
        return (Insets) get(PROPERTY_INSETS);
    }

    /**
     * Returns the vertical scrollbar position.  todo
     *
     * @return the vertical scrollbar position
     */
    public Extent getVerticalScroll() {
        return (Extent) get(PROPERTY_VERTICAL_SCROLL);
    }


    /**
     * @see nextapp.echo.app.Component#processInput(java.lang.String, java.lang.Object)
     */
    public void processInput(String inputName, Object inputValue) {
        if (PROPERTY_HORIZONTAL_SCROLL.equals(inputName)) {
            setHorizontalScroll((Extent) inputValue);
        } else if (PROPERTY_VERTICAL_SCROLL.equals(inputName)) {
            setVerticalScroll((Extent) inputValue);
        }
    }

    /**
     * Sets the background image.
     *
     * @param newValue the new background image
     */
    public void setBackgroundImage(FillImage newValue) {
        set(PROPERTY_BACKGROUND_IMAGE, newValue);
    }

    /**
     * Sets the horizontal scrollbar position.
     * Values must be in pixel units.
     * A value of -1px indicates that the scrollbar should be positioned
     * at the end of the range.
     *
     * @param newValue the new horizontal scrollbar position
     */
    public void setHorizontalScroll(Extent newValue) {
        set(PROPERTY_HORIZONTAL_SCROLL, newValue);
    }

    /**
     * Sets the inset margin of the content.
     * Note that <code>FloatingPane</code>s, such as
     * <code>WindowPane</code>s, will NOT be constrained by
     * this margin.
     * Values may only be specified in pixel-based units.
     *
     * @param newValue the new inset margin
     */
    public void setInsets(Insets newValue) {
        set(PROPERTY_INSETS, newValue);
    }

    /**
     * Sets the vertical scrollbar position.
     * Values must be in pixel units.
     * A value of -1px indicates that the scrollbar should be positioned
     * at the end of the range.
     *
     * @param newValue the new vertical scrollbar position
     */
    public void setVerticalScroll(Extent newValue) {
        if (SCROLL_BOTTOM.equals(newValue)) {
            set(PROPERTY_VERTICAL_SCROLL, PX_0);
        }
        set(PROPERTY_VERTICAL_SCROLL, newValue);
    }

    /**
     * Returns the height extent of container.
     *
     * @return the height extent of container.
     */
    public Extent getHeight() {
        return (Extent) get(PROPERTY_HEIGHT);
    }

    /**
     * @return the Outsets in use or null if here are none
     */
    public Insets getOutsets() {
        return (Insets) get(PROPERTY_OUTSETS);
    }

    /**
     * Returns the width extent of the container.
     * @return the width extent of the container.
     */
    public Extent getWidth() {
        return (Extent) get(PROPERTY_WIDTH);
    }

    /**
     * Sets the height extent of the container.
     *
     * @param newValue - the new height extent of the container
     */
    public void setHeight(Extent newValue) {
        set(PROPERTY_HEIGHT,newValue);
    }

    /**
     * Sets the Outsets in play. The Outsets control the extra space around the
     * outside of a container.
     *
     * @param newValue - the Ousets to use
     */
    public void setOutsets(Insets newValue) {
        set(PROPERTY_OUTSETS,newValue);
    }

    /**
     * Sets the width extent of the container.
     *
     * @param newValue - the new width extent of the container
     */
    public void setWidth(Extent newValue) {
        set(PROPERTY_WIDTH,newValue);
    }

    /**
     * Returns the ScrollBarPolicy in place
     *
     * This can be one of :
     * <ul>
     * <li>NONE</li>
     * <li>ALWAYS</li>
     * <li>AUTO</li>
     * <li>CLIPHIDE</li>
     * </ul>
     */
    public int getScrollBarPolicy() {
        return get(PROPERTY_SCROLL_BAR_POLICY, AUTO);
    }

    /**                                           todo
     * Returns the base color of the ScrollBarProperties associated with this <code>Scrollable</code>
     * @return the base color of the ScrollBarProperties associated with this <code>Scrollable</code>
     */
    public Color getScrollBarBaseColor() {
        return (Color) get(PROPERTY_SCROLL_BAR_BASE_COLOR);        
    }

    /**                                          todo
     * Returns the ScrollBarProperties associated with this <code>Scrollable</code>
     * @return the ScrollBarProperties associated with this <code>Scrollable</code>
     */
    public ScrollBarProperties getScrollBarProperties() {
        return (ScrollBarProperties) get(Scrollable.PROPERTY_SCROLL_BAR_PROPERTIES);
    }

    /**
     * Sets the scroll bar policy of the component
     *
     * This can be one of :
     * <ul>
     * <li>SCOLLBARS_NONE</li>
     * <li>SCOLLBARS_ALWAYS</li>
     * <li>SCOLLBARS_AUTO</li>
     * <li>CLIPHIDE</li>
     * </ul>
     */
    public void setScrollBarPolicy(int newScrollBarPolicy) {
        set(PROPERTY_SCROLL_BAR_POLICY,newScrollBarPolicy);
    }

    /**                                    todo
     * Sets the base color of the ScrollBarProperties associated with this <code>Scrollable</code>.
     * If no  ScrollBarProperties is available, then a new one should be created.
     *
     * @param newValue - the new base color of ScrollBarProperties to use
     */
    public void setScrollBarBaseColor(Color newValue) {
        set(PROPERTY_SCROLL_BAR_BASE_COLOR,newValue);
    }

    /**                                    todo
     * Sets the ScrollBarProperties associated with this <code>Scrollable</code>
     * @param newValue - the new ScrollBarProperties to use
     */
    public void setScrollBarProperties(ScrollBarProperties newValue) {
        set(Scrollable.PROPERTY_SCROLL_BAR_PROPERTIES,newValue);
    }

    /**
     * @return true if the height should be stretched to use all available space
     *         of its parent.
     */
    public boolean isHeightStretched() {
        return get(PROPERTY_HEIGHT_STRETCHED, false);
    }

    /**
     * Set to true if the height should be stretched to use all available space
     * of its parent.
     *
     * @param newValue -
     *            a boolean flag indicating whether the height should be
     *            stretched to use all available space of its parent or not.
     */
    public void setHeightStretched(boolean newValue) {
        set(PROPERTY_HEIGHT_STRETCHED, newValue);
    }

    /**
     * @return - the minimum height that the component should stretch itself
     *         down to in pixels.
     */
    public Extent getMinimumStretchedHeight() {
        return (Extent) get(PROPERTY_MINIMUM_STRETCHED_HEIGHT) ;
    }

    /**
     * Sets the minimum height that the component should stretch itself down to
     * in pixels.
     *
     * @param newValue -
     *            a new Extent value that MUST be in pixel units.
     */
    public void setMinimumStretchedHeight(Extent newValue) {
        Extent.validate(newValue, Extent.PX);
		set(PROPERTY_MAXIMUM_STRETCHED_HEIGHT, newValue);

    }

    /**
     * @return - the maximum height that the component should stretch itself up
     *         to in pixels.
     */
    public Extent getMaximumStretchedHeight() {
		return (Extent) get(PROPERTY_MAXIMUM_STRETCHED_HEIGHT) ;
	}

    /**
     * Sets the maximum height that the component should stretch itself up to in
     * pixels.
     *
     * @param newValue -
     *            a new Extent value that MUST be in pixel units.
     */
    public void setMaximumStretchedHeight(Extent newValue) {
        Extent.validate(newValue, Extent.PX);
		set(PROPERTY_MAXIMUM_STRETCHED_HEIGHT, newValue);
	}


    public int getLayoutStyle() {
        return get(PROPERTY_LAYOUT_STYLE, DEFAULT_LAYOUT);
    }

    public void setLayoutStyle(int layoutStyle) {
        set(PROPERTY_LAYOUT_STYLE, layoutStyle);
    }

    /**
     * Returns the alignment of the container.
     *
     * @return the alignment
     */
    public Alignment getAlignment() {
        return (Alignment) get(PROPERTY_ALIGNMENT);
    }

    /**
     * Sets the alignment of the container.
     *
     * @param newValue the new alignment
     */
    public void setAlignment(Alignment newValue) {
        set(PROPERTY_ALIGNMENT, newValue);
    }


}
