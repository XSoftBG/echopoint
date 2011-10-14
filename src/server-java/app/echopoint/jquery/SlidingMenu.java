package echopoint.jquery;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Color;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.ImageReference;
import nextapp.echo.app.Insets;
import nextapp.echo.app.PaneContainer;
import echopoint.AbleComponent;
import echopoint.able.Alignable;
import echopoint.able.BackgroundImageable;
import echopoint.able.Positionable;
import echopoint.able.ScrollBarProperties;
import echopoint.able.Scrollable;

/**
 * <code>SlidingMenu</code> consists of three parts, a button and two containers.
 * If you press the button the menu slides between the two containers (so one container is always hidden by the other).
 *
 *
 * @author Hans Holmlund - 2009-05-11
 * @version $Id: SlidingMenu.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */

public class SlidingMenu extends AbleComponent implements Alignable, PaneContainer, Positionable, Scrollable, BackgroundImageable {

    public static final String PROPERTY_ICON = "icon";
    public static final String PROPERTY_PRESSED_ICON = "pressedIcon";
    public static final String PROPERTY_ROLLOVER_ICON = "rolloverIcon";
    public static final String PROPERTY_BUTTON_WIDTH = "buttonWidth";
    public static final String PROPERTY_SLIDER_WIDTH = "sliderWidth";



    public static final int DEFAULT_LAYOUT = 0;
    public static final int COLUMN_LAYOUT = 1;
    public static final int ROW_LAYOUT = 2;


    /**
     * Creates a new <code>ContentPane</code>.
     */
    public SlidingMenu() {
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
        if (get(PROPERTY_SLIDER_WIDTH) == null) {
            Extent buttonWidth = getButtonWidth();
            if (buttonWidth != null) {
                Extent sliderWidth = new Extent(newValue.getValue() - buttonWidth.getValue());
                set(PROPERTY_SLIDER_WIDTH, sliderWidth);
            }
        }

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


    /**
     * Returns the icon displayed in the button.
     *
     * @return the icon
     */
    public ImageReference getIcon() {
        return (ImageReference) get(PROPERTY_ICON);
    }

    /**
     * Sets the icon displayed in the button.
     *
     * @param newValue the new icon
     */
    public void setIcon(ImageReference newValue) {
        set(PROPERTY_ICON, newValue);
    }

    /**
     * Sets the icon of the button that is displayed when the button is pressed.
     *
     * @param newValue the new icon
     */
    public void setPressedIcon(ImageReference newValue) {
        set(PROPERTY_PRESSED_ICON, newValue);
    }

    /**
     * Sets the icon of the button that is displayed when the mouse cursor is
     * inside its bounds.
     *
     * @param newValue the new icon
     */
    public void setRolloverIcon(ImageReference newValue) {
        set(PROPERTY_ROLLOVER_ICON, newValue);
    }

    /**
     * Returns the icon of the button that is displayed when the button is
     * pressed.
     *
     * @return the icon
     */
    public ImageReference getPressedIcon() {
        return (ImageReference) get(PROPERTY_PRESSED_ICON);
    }

    /**
     * Returns the icon of the button that is displayed when the mouse cursor is
     * inside its bounds.
     *
     * @return the icon
     */
    public ImageReference getRolloverIcon() {
        return (ImageReference) get(PROPERTY_ROLLOVER_ICON);
    }

    /**
     * Returns the button width
     */
    public Extent getButtonWidth() {
        return (Extent) get(PROPERTY_BUTTON_WIDTH);
    }

    /**
     * Sets the button width
     *
     * @param newValue the new button width
     */
    public void setButtonWidth(Extent newValue) {
        set(PROPERTY_BUTTON_WIDTH, newValue);
        if (get(PROPERTY_SLIDER_WIDTH) == null) {
            Extent totalWidth = (Extent) get(PROPERTY_WIDTH);
            if (totalWidth != null) {
                Extent sliderWidth = new Extent(totalWidth.getValue() - newValue.getValue());
                set(PROPERTY_SLIDER_WIDTH, sliderWidth);
            }
        }

    }
}
