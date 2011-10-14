package echopoint.jquery;

import nextapp.echo.app.ImageReference;
import echopoint.ContainerEx;

/**
 * CarouselContainer is a component that can be positioned anywhere on the screen with an specified size attributes.
 * The components put inside will be able to navigate between in a carousel-style widget.
 * This component is built around the jQuery project jCarousel Lite: http://www.gmarwaha.com/jquery/jcarousellite
 *
 * This component is a PaneContainer and hence can have components that implement Pane as a child.
 * @author HansH 2009-07-09
 * @version $Id: CarouselContainer.java,v 1.2 2010-12-06 10:11:53 perxi Exp $
 */
public class CarouselContainer extends ContainerEx {

    public static final String PROPERTY_LEFT_ICON = "leftIcon";
    public static final String PROPERTY_RIGHT_ICON = "rightIcon";
    public static final String PROPERTY_LEFT_ICON_OVER = "leftIconOver";
    public static final String PROPERTY_RIGHT_ICON_OVER = "rightIconOver";
    public static final String PROPERTY_VISIBLE = "visible";
    public static final String PROPERTY_CIRCULAR = "circular";
    public static final String PROPERTY_SPEED = "speed";


    public CarouselContainer() {
        super();

        set(PROPERTY_CIRCULAR, false);
    }

    /**
     * Returns the left icon displayed in the button.
     *
     * @return the icon
     */
    public ImageReference getLeftIcon() {
        return (ImageReference) get(PROPERTY_LEFT_ICON);
    }

    /**
     * Sets the left icon displayed in the button.
     *
     * @param newValue the new icon
     */
    public void setLeftIcon(ImageReference newValue) {
        set(PROPERTY_LEFT_ICON, newValue);
    }

    /**
     * Returns the right icon displayed in the button.
     *
     * @return the icon
     */
    public ImageReference getRightIcon() {
        return (ImageReference) get(PROPERTY_RIGHT_ICON);
    }

    /**
     * Sets the right icon displayed in the button.
     *
     * @param newValue the new icon
     */
    public void setRightIcon(ImageReference newValue) {
        set(PROPERTY_RIGHT_ICON, newValue);
    }

    /**
     * Returns the left icon displayed in the button.
     *
     * @return the icon
     */
    public ImageReference getLeftMouseOverIcon() {
        return (ImageReference) get(PROPERTY_LEFT_ICON_OVER);
    }

    /**
     * Sets the left icon displayed in the button.
     *
     * @param newValue the new icon
     */
    public void setLeftMouseOverIcon(ImageReference newValue) {
        set(PROPERTY_LEFT_ICON_OVER, newValue);
    }

    /**
     * Returns the right icon displayed in the button.
     *
     * @return the icon
     */
    public ImageReference getRightMouseOverIcon() {
        return (ImageReference) get(PROPERTY_RIGHT_ICON_OVER);
    }

    /**
     * Sets the right icon displayed in the button.
     *
     * @param newValue the new icon
     */
    public void setRightMouseOverIcon(ImageReference newValue) {
        set(PROPERTY_RIGHT_ICON_OVER, newValue);
    }

    /**
     * Returns the number of visible items
     * @return
     */
    public int getVisible() {
        return get(PROPERTY_VISIBLE, 3);
    }

    /**
     * Sets the number of visible items.
     * @param newVisible
     */
    public void setVisible(int newVisible) {
        set(PROPERTY_VISIBLE, newVisible);
    }


    /**
     * Returns true if the carousel is circular
     * @return
     */
    public boolean isCircular() {
        return get(PROPERTY_CIRCULAR, false);
    }

    /**
     * Sets whether the carousel should be circular or not
     * @param isCircular
     */
    public void setCircular(boolean isCircular) {
        set(PROPERTY_CIRCULAR, isCircular);
    }

    /**
     * Returns the scroll speed of the carousel
     * @return
     */
    public boolean getSpeed() {
        return get(PROPERTY_SPEED, false);
    }

    /**
     * Sets the scroll speed of the carousel
     * @param scrollSpeed
     */
    public void setSpeed(int scrollSpeed) {
        set(PROPERTY_SPEED, scrollSpeed);
    }

    
}
