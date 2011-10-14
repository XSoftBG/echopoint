package echopoint.jquery;


import java.util.ArrayList;
import java.util.List;

import nextapp.echo.app.ImageReference;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import echopoint.internal.AbstractContainer;

/**
 *  A component to display a Mac-style dock menu (fisheye menu).
 *
 * The menu consists of a set of images that expand on mouse rollover. Along with
 * the image a label can be displayed when the image is expanded.
 *
 * This component is using the jqDock project: http://www.wizzud.com/jqDock
 * 
 * @author Mikael S\u00f6derman 2009-06-03
 * @version $Id: DockMenu.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class DockMenu extends AbstractContainer {

    private List<ImageReference> inactiveImages;
    //private List<ImageReference> activeImages; //todo
    private List<ImageReference> rolloverImages;
    private List<String> texts;
    private List<String> actionCommands;
    private List model;

    public static final String MODEL_CHANGED_PROPERTY = "model";
    public static final String BUTTON_PRESSED_PROPERTY = "itemid";
    public static final String PROPERTY_DOCK_WIDTH = "dockWidth";
    public static final String PROPERTY_MINOR_AXIS_SIZE = "minorSize";
    public static final String PROPERTY_SHOW_LABELS = "labels";
    public static final String PROPERTY_DURATION = "duration";
    public static final String PROPERTY_ALIGN = "align";
    public static final String PROPERTY_COEFFICIENT = "coefficient";
    public static final String PROPERTY_DISTANCE = "distance";


    public static final String LABELS_BOTTOM_CENTER = "bc";
    public static final String LABELS_TOP_LEFT = "tl";
    public static final String LABELS_TOP_CENTER = "tc";
    public static final String LABELS_TOP_RIGHT = "tr";
    public static final String LABELS_MIDDLE_LEFT = "ml";
    public static final String LABELS_MIDDLE_CENTER = "mc";
    public static final String LABELS_MIDDLE_RIGHT = "mr";
    public static final String LABELS_BOTTOM_LEFT = "bl";
    public static final String LABELS_BOTTOM_RIGHT = "br";

    public static final String ALIGN_TOP = "top";
    public static final String ALIGN_MIDDLE = "middle";
    public static final String ALIGN_BOTTOM = "bottom";
    public static final String ALIGN_LEFT = "left";
    public static final String ALIGN_CENTER = "center";
    public static final String ALIGN_RIGHT = "right";

    private int dockWidth = 0;

    /**
     * Creates a new DockMenu instance.
     *
     * @param minorAxisSize The size (height for horizontal menus and width for vertical menus) for the item at rest
     * @param displayLabels If set to null no labels will be displayed.
     * @param duration  The duration of the expansion and shrinkage.
     */
    public DockMenu(int minorAxisSize, String displayLabels, int duration) {
        this.model = new ArrayList();
        this.inactiveImages = new ArrayList<ImageReference>();
        //this.activeImages = new ArrayList<ImageReference>();
        this.rolloverImages = new ArrayList<ImageReference>();
        this.texts = new ArrayList<String>();
        this.actionCommands = new ArrayList<String>();
        this.model.add(texts);
        this.model.add(actionCommands);
        this.model.add(inactiveImages);
        this.model.add(rolloverImages);
       // this.model.add(activeImages);
        set(PROPERTY_MINOR_AXIS_SIZE, minorAxisSize);
        set(PROPERTY_DURATION, duration);
        set(PROPERTY_ALIGN, ALIGN_BOTTOM);
        set(PROPERTY_DISTANCE, new Integer(140));
        set(PROPERTY_COEFFICIENT, new Float(1.1));

        if (displayLabels != null)
            set(PROPERTY_SHOW_LABELS, displayLabels);
        else
            set(PROPERTY_SHOW_LABELS, false);

    }

    /**
     * Adding an item to the menu. The item consists of an icon and possibly a label. The label
     * is displayed when the menu item is expanded (i.e. on mouse rollover) but only if the
     * DockMenu has been configured to display labels.
     *
     * @param text The text of the label.
     * @param actionCommand The command to send in the ActionEvent when the item has been clicked
     * @param imageWidth The width of the image when the menu item is fully expanded
     * @param imageHeight The height of the image when the menu item is fully expanded
     * @param icon The image to be displayed
     * @param expanded The image to show at expanded state
     */
    public void addMenuItem(String text, String actionCommand, int imageWidth, int imageHeight,
                            ImageReference icon, ImageReference expanded)
    {
        double resizeCoefficient = (double) imageHeight / (Integer) get(PROPERTY_MINOR_AXIS_SIZE);
        int width = (int) (imageWidth / resizeCoefficient);
        dockWidth = dockWidth + width;
        set(PROPERTY_DOCK_WIDTH, dockWidth);

        inactiveImages.add(icon);
        rolloverImages.add(expanded);
        texts.add(text);
        actionCommands.add(actionCommand);
    }

    /**
     * Returns the model
     *
     * @return the model
     */
    public List getModel() {
        return model;
    }

    public int getDockWidth()
    {
        return (Integer) get(PROPERTY_DOCK_WIDTH);
    }

    public String getDisplayLabels()
    {
        return (String) get(PROPERTY_SHOW_LABELS);
    }

    public Integer getDuration()
    {
        return (Integer) get(PROPERTY_DURATION);
    }

    public void setCoefficient(Float coefficient)
    {
        set(PROPERTY_COEFFICIENT, coefficient);
    }

    public Float getCoefficient()
    {
        return (Float) get(PROPERTY_COEFFICIENT);
    }

    public Integer getDistance()
    {
        return (Integer) get(PROPERTY_DISTANCE);
    }

    public void setDistance(Integer distance)
    {
        set(PROPERTY_DISTANCE, distance);
    }

    public void setAlign(String align)
    {
        set(PROPERTY_ALIGN, align);
    }

    public String getAlign()
    {
        return (String) get(PROPERTY_ALIGN);
    }

    public void processInput(String inputName, Object inputValue) {
        super.processInput(inputName, inputValue);
        if (BUTTON_PRESSED_PROPERTY.equals(inputName)) {
            String itemid = (String) inputValue;
            fireActionPerformed(new ActionEvent(this, itemid));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addActionListener(final ActionListener listener) {
        super.addActionListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void removeActionListener(final ActionListener listener) {
        super.removeActionListener(listener);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean hasActionListeners() {
        return super.hasActionListeners();
    }


}
