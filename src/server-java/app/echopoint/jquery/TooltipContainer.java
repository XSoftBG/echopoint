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
package echopoint.jquery;


import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Color;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.Insets;
import nextapp.echo.app.PaneContainer;
import echopoint.AbleComponent;
import echopoint.able.Alignable;
import echopoint.able.BackgroundImageable;
import echopoint.able.Positionable;
import echopoint.able.ScrollBarProperties;
import echopoint.able.Scrollable;

/**
 * TooltipContainer is a component that can be positioned anywhere on the screen with an specified size attributes.
 * A tooltip can be set for the whole container.
 * This component is built around the jQuery project qTip: http://craigsworks.com/projects/qtip/
 *
 * This component is a PaneContainer and hence can have components that implement Pane as a child.
 * @author HansH 2009-04-28
 * @version $Id: TooltipContainer.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class TooltipContainer extends AbleComponent implements Alignable, PaneContainer, Positionable, Scrollable, BackgroundImageable {

    public static final String PROPERTY_LAYOUT_STYLE = "layoutStyle";
    public static final String PROPERTY_POSITION_TOOLTIP = "positionTooltip";
    public static final String PROPERTY_POSITION_TARGET = "positionTarget";
    public static final String PROPERTY_TOOLTIP_BORDER_COLOR = "tooltipBorderColor";
    public static final String PROPERTY_TOOLTIP_BORDER_WIDTH = "tooltipBorderWidth";
    public static final String PROPERTY_TOOLTIP_BORDER_RADIUS = "tooltipBorderRadius";
    public static final String PROPERTY_TOOLTIP_BACKGROUND_COLOR = "tooltipBackground";
    public static final String PROPERTY_TOOLTIP_FOREGROUND_COLOR = "tooltipForeground";
    public static final String PROPERTY_TOOLTIP_INSETS = "tooltipInsets";
    public static final String PROPERTY_TOOLTIP_ALIGNMENT = "tooltipAlignment";
    public static final String PROPERTY_TOOLTIP_STYLENAME = "tooltipStyle";
    public static final String PROPERTY_TOOLTIP_THUMBNAIL = "thumbnail";
    public static final String PROPERTY_TOOLTIP_VIDEO = "video";
    public static final String PROPERTY_SOLO = "solo";
    public static final String PROPERTY_HIDE_DELAY = "hideDelay";

    public static final String TOPLEFT = "topLeft";
    public static final String TOPMIDDLE = "topMiddle";
    public static final String TOPRIGHT = "topRight";
    public static final String LEFTMIDDLE = "leftMiddle";
    public static final String RIGHTMIDDLE = "rightMiddle";
    public static final String BOTTOMLEFT = "bottomLeft";
    public static final String BOTTOMMIDDLE = "bottomMiddle";
    public static final String BOTTOMRIGHT = "bottomRight";


    public static final String ALIGN_CENTER = "center";
    public static final String ALIGN_LEFT = "left";
    public static final String ALIGN_RIGHT = "right";


    public static final int DEFAULT_LAYOUT = 0;
    public static final int COLUMN_LAYOUT = 1;
    public static final int ROW_LAYOUT = 2;


    /**
     * Creates a new <code>TooltipContainer</code>.
     */
    public TooltipContainer() {
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
     * Sets the background image.
     *
     * @param newValue the new background image
     */
    public void setBackgroundImage(FillImage newValue) {
        set(PROPERTY_BACKGROUND_IMAGE, newValue);
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
     * Returns the tool tip text (displayed when the mouse cursor is hovered
     * over the ToolTipable).
     *
     * @return the tool tip text
     */
    public String getToolTipText() {
        return (String) get(PROPERTY_TOOL_TIP_TEXT);
    }

    /**
     * Sets the tool tip text (displayed when the mouse cursor is hovered over
     * the ToolTipable).
     *
     * @param newValue
     *            the new tool tip text
     */
    public void setToolTipText(String newValue) {
        set(PROPERTY_TOOL_TIP_TEXT, newValue);
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

    public int getLayoutStyle() {
        return get(PROPERTY_LAYOUT_STYLE, DEFAULT_LAYOUT);
    }

    public void setLayoutStyle(int layoutStyle) {
        set(PROPERTY_LAYOUT_STYLE, layoutStyle);
    }

    /**
     * Returns the delay in milliseconds before the tooltip is hidden
     * @return
     */
    public int getHideDelay() {
        return (Integer) get(PROPERTY_HIDE_DELAY);
    }

    /**
     * Sets the delay in milliseconds before the tooltip is hidden
     * @param hideDelay
     */
    public void setHideDelay(int hideDelay) {
        set(PROPERTY_HIDE_DELAY, hideDelay);
    }

    /**
     * Sets whether or not the tooltip will be shown in conjunction with other tooltips or on its own.
     * Does not apply when {@link #getVideoURL()} is in use.
     * @return solo
     */
    public boolean getSolo() {
        return (Boolean) get(PROPERTY_SOLO);
    }

    /**
     * Returns whether or not the tooltip will be shown in conjunction with other tooltips or on its own.
     * Does not apply when {@link #getVideoURL()} is in use.
     * @param solo
     */
    public void setSolo(boolean solo) {
        set(PROPERTY_SOLO, solo);
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
     * @param newValue of the new alignment
     */
    public void setAlignment(Alignment newValue) {
        set(PROPERTY_ALIGNMENT, newValue);
    }

    /**
     * Returns the tooltip-"arrow" position.
     *
     * @return the tooltip position
     */
    public String getPositionTooltip() {
        return (String) get(PROPERTY_POSITION_TOOLTIP);
    }

    /**
     * Sets the tooltip-"arrow" position.
     *
     * @param newPosition of the tooltip-arrow
     */
    public void setPositionTooltip(String newPosition) {
        set(PROPERTY_POSITION_TOOLTIP, newPosition);
    }

    /**
     * Returns the tooltip-targetposition.
     *
     * @return the target position
     */
    public String getPositionTarget() {
        return (String) get(PROPERTY_POSITION_TARGET);
    }

    /**
     * Sets the tooltip-target position. this is normally the opposite part of the positionTarget
     *
     * @param newPosition of the tooltip-target
     */
    public void setPositionTarget(String newPosition) {
        set(PROPERTY_POSITION_TARGET, newPosition);
    }

    /**
     * Returns the tooltip-border color.
     *
     * @return the border color
     */
    public Color getTooltipBorderColor() {
        return (Color) get(PROPERTY_TOOLTIP_BORDER_COLOR);
    }

    /**
     * Sets the tooltip-border color.
     *
     * @param newColor of the tooltip-border
     */
    public void setTooltipBorderColor(Color newColor) {
        set(PROPERTY_TOOLTIP_BORDER_COLOR, newColor);
    }

    /**
     * Returns the tooltip-border width.
     *
     * @return the border width
     */
    public int getTooltipBorderWidth() {
        return (Integer) get(PROPERTY_TOOLTIP_BORDER_WIDTH);
    }

    /**
     * Sets the tooltip-border width.
     *
     * @param newWidth of the tooltip-border
     */
    public void setTooltipBorderWidth(int newWidth) {
        set(PROPERTY_TOOLTIP_BORDER_WIDTH, newWidth);
    }

    /**
     * Returns the tooltip-border radius.
     *
     * @return the border radius
     */
    public int getTooltipBorderRadius() {
        return (Integer) get(PROPERTY_TOOLTIP_BORDER_RADIUS);
    }

    /**
     * Sets the tooltip-border radius.
     *
     * @param newRadius of the tooltip-border
     */
    public void setTooltipBorderRadius(int newRadius) {
        set(PROPERTY_TOOLTIP_BORDER_RADIUS, newRadius);
    }

    /**
     * Returns the tooltip background color.
     *
     * @return the background color
     */
    public Color getTooltipBackground() {
        return (Color) get(PROPERTY_TOOLTIP_BACKGROUND_COLOR);
    }

    /**
     * Sets the tooltip background color.
     *
     * @param newColor of the background
     */
    public void setTooltipBackground(Color newColor) {
        set(PROPERTY_TOOLTIP_BACKGROUND_COLOR, newColor);
    }

    /**
     * Returns the tooltip foreground color.
     *
     * @return the foreground color
     */
    public Color getTooltipForeground() {
        return (Color) get(PROPERTY_TOOLTIP_FOREGROUND_COLOR);
    }

    /**
     * Sets the tooltip foreground color.
     *
     * @param newColor of the foreground
     */
    public void setTooltipForeground(Color newColor) {
        set(PROPERTY_TOOLTIP_FOREGROUND_COLOR, newColor);
    }

    /**
     * Returns the tooltip insets.
     *
     * @return the tooltip insets
     */
    public Color getTooltipInsets() {
        return (Color) get(PROPERTY_TOOLTIP_INSETS);
    }

    /**
     * Sets the tooltip insets.
     *
     * @param newValue of the insets
     */
    public void setTooltipInsets(Color newValue) {
        set(PROPERTY_TOOLTIP_INSETS, newValue);
    }

    /**
     * Returns the tooltip text alignment.
     *
     * @return the tooltip text alignment
     */
    public String getTooltipAlignment() {
        return (String) get(PROPERTY_TOOLTIP_ALIGNMENT);
    }

    /**
     * Sets the tooltip text alignment.
     *
     * @param newValue of the tooltip text alignment
     */
    public void setTooltipAlignment(String newValue) {
        set(PROPERTY_TOOLTIP_ALIGNMENT, newValue);
    }

    /**
     * Returns the tooltip style name.
     *
     * @return the tooltip style name
     */
    public String getTooltipStyle() {
        return (String) get(PROPERTY_TOOLTIP_STYLENAME);
    }

    /**
     * Sets the tooltip style name.
     *
     * @param newValue of the tooltip style name
     */
    public void setTooltipStyle(String newValue) {
        set(PROPERTY_TOOLTIP_STYLENAME, newValue);
    }

    /**
     * Returns the tooltip thumbnail URL.
     *
     * @return the tooltip thumbnail URL
     */
    public String getThumbnailURL() {
        return (String) get(PROPERTY_TOOLTIP_THUMBNAIL);
    }

    /**
     * Sets the tooltip thumbnail URL.
     * If you set the URL to for example www.amazon.com you will see a thumbnail of the amazon site.
     *
     * This feature is now hardcoded with the websnapr-service at: http://www.websnapr.com
     *
     * @param newValue of the tooltip thumbnail URL
     */
    public void setThumbnailURL(String newValue) {
        set(PROPERTY_TOOLTIP_THUMBNAIL, newValue);
    }

    /**
     * Returns the tooltip video URL.
     *
     * @return the tooltip video URL
     */
    public String getVideoURL() {
        return (String) get(PROPERTY_TOOLTIP_VIDEO);
    }

    /**
     * Sets the tooltip video URL.
     * You could use this feature to display a youtube video.
     * Example: setVideoURL("http://www.youtube.com/v/i_pcYd-9svs")
     *
     * @param newValue of the tooltip video URL
     */
    public void setVideoURL(String newValue) {
        set(PROPERTY_TOOLTIP_VIDEO, newValue);
    }

    public void setTooltip(String tooltip) {
        if (tooltip != null && tooltip.length() > 0) {
            setToolTipText(tooltip);
        }
    }


}

