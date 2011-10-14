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

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.ImageReference;
import nextapp.echo.app.Insets;
import nextapp.echo.app.ResourceImageReference;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import echopoint.able.Alignable;
import echopoint.able.Sizeable;


/**
 * <code>DateField</code> is a drop down component that contains a text field and a drop down calendar.
 * The text field is updated with the contents of the DateField calendar.
 * DateField is both a date- and time picker.
 * If the component should be used as a time-picker the DateFormat must include an hour field, e.g. setDateFormat("dd.MM.yyyy HH:mm")
 * It is based on the jQuery plugin DynDateTime
 *
 * @author Hans Holmlund - 2009-04-03
 * @version $Id: DateField.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $ 
 */
public class DateField extends Component implements Sizeable, Alignable
{

    /** The logger to use to log the download progress. */
    protected static final Logger logger = Logger.getAnonymousLogger();

    private static final String USETIME_PROPERTY = "useTime";
    public static final String DATE_CHANGED_PROPERTY = "date";
    public static final String PROPERTY_EDITABLE = "editable";    
    public static final String PROPERTY_DATEFORMAT = "dateFormat";
    public static final String PROPERTY_BORDER = "border";
    public static final String PROPERTY_CALENDAR_ICON = "icon";
    public static final String PROPERTY_CSS = "css";
    public static final String PROPERTY_INSETS = "insets";
    public static final String PROPERTY_LANGUAGE = "language";
    public static final ImageReference imageReference = new ResourceImageReference("/resource/images/calendar.gif");
    public static final String cssReference = getFileAsString("resource/js/jquery/calendar-win2k-cold-2.css");
    public static final String PROPERTY_INPUT_WIDTH = "inputWidth";
    public static final String PROPERTY_INPUT_HEIGHT = "inputHeight";
    public static final String PROPERTY_SHOW_WEEKS = "showWeeks";
    public static final String PROPERTY_FIRST_DAY_OF_WEEK = "firstDayOfWeek";
    public static final String ACTION_LISTENERS_CHANGED_PROPERTY = "actionListeners";
    public static final String ACTION_COMMAND_CHANGED_PROPERTY = "actionCommand";
    public static final String INPUT_ACTION = "action";
    private String actionCommand;


    /**
     * The selected date.
     */
    private Date date;
    private String dateFormatPattern = "yyyy-MM-dd HH:mm";
    private SimpleDateFormat dateFormatter;

    /**
     * Creates a new <code>CalendarSelect</code>.
     */
    public DateField() {
        this((Date)null);
    }

    public DateField(Calendar calendar) {
        this(calendar.getTime());
    }

    /**
     * Creates a new <code>CalendarSelect</code>.
     *
     * @param date the initially selected date
     */
    public DateField(Date date) {
        super();
        this.date = date;
        setDateFormat(dateFormatPattern);
        setIcon(imageReference);
        setCSS(cssReference);
//        set(PROPERTY_LANGUAGE, defaultLanguage);
    }

    private void appendPattern(StringBuffer datePattern, char currentChar, int numberOfThisChar) {
        switch (currentChar) {
            case 'y': {
                if (numberOfThisChar >= 3) {
                    datePattern.append("%").append("Y");
                }
                else {
                    datePattern.append("%").append("y");
                }
                break;
            }
            case 'M': {
                if (numberOfThisChar >= 4) {
                    datePattern.append("%").append("B");
                }
                if (numberOfThisChar == 3) {
                    datePattern.append("%").append("b");
                }
                else {
                    datePattern.append("%").append("m");
                }
                break;
            }
            case 'w': {
                datePattern.append("%").append("W");
                break;
            }
            case 'G': {
                throw new IllegalArgumentException("Era designator is not allowed");
            }
            case 'W': {
                throw new IllegalArgumentException("Week in month is not allowed");
            }
            case 'D': {
                datePattern.append("%").append("j");
                break;
            }
            case 'd': {
                datePattern.append("%").append("d");
                break;
            }
            case 'F': {
                throw new IllegalArgumentException("Day of week in month is not allowed");
            }
            case 'E': {
                if (numberOfThisChar > 3) {
                    datePattern.append("%").append("A");
                }
                else {
                    datePattern.append("%").append("a");
                }
                break;
            }
            case 'a': {
                datePattern.append("%").append("p");
                break;
            }
            case 'H': {
                datePattern.append("%").append("H");
                break;
            }
            case 'k': {
                throw new IllegalArgumentException("Hour in day (1-24) is not allowed. Use 'H' instead.");
            }
            case 'K': {
                throw new IllegalArgumentException("Hour in am/pm (0-11) is not allowed. Use 'h' instead.");
            }
            case 'h': {
                datePattern.append("%").append("l");
                break;
            }
            case 'm': {
                datePattern.append("%").append("M");
                break;
            }
            case 's': {
                datePattern.append("%").append("S");
                break;
            }
            case 'S': {
                throw new IllegalArgumentException("Milliseconds is not allowed..");
            }
            case 'z':
            case 'Z':{
                throw new IllegalArgumentException("Time zone is not allowed..");
            }
            default:
                datePattern.append(currentChar);
        }
    }

    private String convertDateFormat(String simpleDateFormat) {
        StringBuffer datePattern = new StringBuffer();
        char currentChar = 0;
        int numberOfThisChar = 0;
        for (char ch : simpleDateFormat.toCharArray()) {
            if (currentChar == 0) {
                currentChar = ch;
                numberOfThisChar = 1;
            }
            else if (ch == currentChar) {
                numberOfThisChar++;
            }
            else {
                appendPattern(datePattern, currentChar, numberOfThisChar);
                currentChar = ch;
                numberOfThisChar = 1;
            }
        }
        appendPattern(datePattern, currentChar, numberOfThisChar);
        return datePattern.toString();
    }

    /**
     * Sets the date format for this calendar.
     * Depending on the date format the USETIME property will be automatically set.
     * @param dateFormat
     * @throws IllegalArgumentException
     */
    public void setDateFormat(String dateFormat) throws IllegalArgumentException {
        String jsDateFormatPattern = convertDateFormat(dateFormat);
        set(PROPERTY_DATEFORMAT, jsDateFormatPattern);
        dateFormatPattern = dateFormat;
        dateFormatter = new SimpleDateFormat(dateFormatPattern);
        if (jsDateFormatPattern.contains("%H") || jsDateFormatPattern.contains("%l")) {
            set(USETIME_PROPERTY, true);
        }
        else {
            set(USETIME_PROPERTY, false);
        }
    }

    public String getDateFormat() {
        return dateFormatPattern;
    }

    /**
     * Sets the inset margin of the content.
     * Values may only be specified in pixel-based units.
     *
     * @param newValue the new inset margin
     */
    public void setInsets(Insets newValue) {
        set(PROPERTY_INSETS, newValue);
    }

    /**
     * Returns the inset margin of the content.
     *
     * @return newValue the inset margin
     */
    public Insets getInsets() {
        return (Insets) get(PROPERTY_INSETS);
    }

    /**
     * Sets the icon displayed in the calendar button.
     *
     * @param newValue the new icon
     */
    public void setIcon(ImageReference newValue) {
        set(PROPERTY_CALENDAR_ICON, newValue);
    }

    /**
     * Returns the cascading style sheet for this calendar.
     *
     * @return the cascading style sheet
     */
    public String getCSS() {
        return (String) get(PROPERTY_CSS);
    }

    /**
     * Sets the cascading style sheet for this calendar.
     *
     * @param newValue the new icon
     */
    public void setCSS(String newValue) {
        set(PROPERTY_CSS, newValue);
    }

    /**
     * Returns the alignment of the DateField component.
     *
     * @return the alignment
     */
    public Alignment getAlignment() {
        return (Alignment) get(PROPERTY_ALIGNMENT);
    }

    /**
     * Sets the alignment of the DateField component.
     *
     * @param newValue the new alignment
     */
    public void setAlignment(Alignment newValue) {
        set(PROPERTY_ALIGNMENT, newValue);
    }

    /**
     * Returns the icon displayed in the calendar button.
     *
     * @return the icon
     */
    public ImageReference getIcon() {
        return (ImageReference) get(PROPERTY_CALENDAR_ICON);
    }

    /**
     * Returns the border surrounding the entire component.
     *
     * @return the border
     */
    public Border getBorder() {
        return (Border) get(PROPERTY_BORDER);
    }

    /**
     * Sets the width extent of the DateField component.
     *
     * @param newValue - the new width extent of the DateField component
     */
    public void setWidth(Extent newValue) {
        set(PROPERTY_WIDTH,newValue);
    }

    /**
     * Returns the width extent of the DateField component.
     * @return the width extent of the DateField component.
     */
    public Extent getWidth() {
        return (Extent) get(PROPERTY_WIDTH);
    }

    /**
     * Sets the height extent of the DateField component.
     *
     * @param newValue - the new height extent of the DateField component
     */
    public void setHeight(Extent newValue) {
        set(PROPERTY_HEIGHT,newValue);
    }

    /**
     * Returns the height extent of DateField component.
     *
     * @return the height extent of DateField component.
     */
    public Extent getHeight() {
        return (Extent) get(PROPERTY_HEIGHT);
    }

    /**
     * Returns the selected date.
     *
     * @return the selected date
     */
    public Date getDate() {
        return date;
    }

    public String getDateStr() {
        if (date == null) return null;
        return dateFormatter.format(date);
    }

    /**
     * @see nextapp.echo.app.Component#processInput(java.lang.String, java.lang.Object)
     */
    @Override
    public void processInput(String inputName, Object inputValue) {
        if (DATE_CHANGED_PROPERTY.equals(inputName)) {
            if (inputValue != null) {
                try {
                    setDate(dateFormatter.parse((String)inputValue));
                } catch (ParseException e) {
                    // todo
                }
            }
            else
            {
                setDate(null); // Set date to null when empty string returned from client side
            }
        }else if(INPUT_ACTION.equals(inputName)) {
            fireAction();
        }
    }

    /**
     * Sets the border surrounding the entire component.
     *
     * @param newValue the new border
     */
    public void setBorder(Border newValue) {
        set(PROPERTY_BORDER, newValue);
    }

    /**
     * Sets the selected date.
     *
     * @param newValue the new date
     */
    public void setDate(Date newValue) {
        Date oldValue = date;
        date = newValue;
        firePropertyChange(DATE_CHANGED_PROPERTY, (oldValue != null ? dateFormatter.format(oldValue) : null), (newValue != null ? dateFormatter.format(newValue) : null));
    }

    /**
     * Sets the editable state of this component.
     *
     * @param newValue the new editable state
     */
    public void setEditable(boolean newValue) {
        set(PROPERTY_EDITABLE, Boolean.valueOf(newValue));
    }

    /**
     * Determines the editable state of this component. 
     *
     * @return <code>true</code> if this component is editable
     */
    public boolean isEditable() {
        Object property = get(PROPERTY_EDITABLE);
        return null == property ? true : ((Boolean) property).booleanValue();
    }


    public static String getFileAsString(String resource) {
        InputStreamReader in = null;
        StringBuffer sb = new StringBuffer();

        try {
            in = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(resource));
            if (in == null) {
                throw new IllegalArgumentException("Specified resource does not exist: " + resource + ".");
            }
            int character;
            while ((character = in.read()) != -1) {
                sb.append((char) character);
            }
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Could not load resource <"+resource+">", e);
        }
        finally {
            if (in != null) { try { in.close(); } catch (IOException ex) { } }
        }
        return sb.toString();
    }

    /**
     * Returns the input field width.
     *
     * @return The input field width
     */
    public Extent getInputWidth() {
        return (Extent) get(PROPERTY_INPUT_WIDTH);
    }

    /**
     * Sets the width of the input field for the date.
     *
     * @param newValue the new width of the input field
     */
    public void setInputWidth(Extent newValue) {
        set(PROPERTY_INPUT_WIDTH, newValue);
    }

    /**
     * Returns the input field height.
     *
     * @return The input field height
     */
    public Extent getInputHeight() {
        return (Extent) get(PROPERTY_INPUT_HEIGHT);
    }

    /**
     * Sets the editable state of this component.
     *
     * @param newValue the new height of the input field
     */
    public void setInputHeight(Extent newValue) {
        set(PROPERTY_INPUT_HEIGHT, newValue);
    }

    /**
     * Returns the first day of the week
     *
     * @return The first day of the week. (0=Sunday, 1= Monday etc.)
     */
    public int getFirstDayOfWeek() {
        return (Integer) get(PROPERTY_FIRST_DAY_OF_WEEK);
    }

    /**
     * Sets the the first day of week. Used to calculate week numbers.
     * Usually 0 for US (Sunday) and 1 for europe (Monday)
     *
     * @param newValue  (0=Sunday, 1= Monday etc.)
     */
    public void setFirstDayOfWeek(int newValue) {
        set(PROPERTY_FIRST_DAY_OF_WEEK, newValue);
    }

    /**
     * Returns the status of week number display
     *
     * @return The week number display mode
     */
    public boolean getShowWeeks() {
        Object property = get(PROPERTY_SHOW_WEEKS);
        return null == property ? true : ((Boolean) property).booleanValue();
    }

    /**
     * Sets the display of week numbers
     *
     * @param newValue Should week numbers be displayed?
     */
    public void setShowWeeks(boolean newValue) {
        set(PROPERTY_SHOW_WEEKS, newValue);
    }
    
    public void addActionListener(ActionListener l) {
        getEventListenerList().addListener(ActionListener.class, l);
        firePropertyChange(ACTION_LISTENERS_CHANGED_PROPERTY, null, l);
    }

    public void removeActionListener(ActionListener l) {
        getEventListenerList().removeListener(ActionListener.class, l);
        firePropertyChange(ACTION_LISTENERS_CHANGED_PROPERTY, l, null);
    }
    
    public boolean hasActionListeners() {
        return hasEventListenerList() 
                && getEventListenerList().getListenerCount(ActionListener.class) > 0;
    }
    public String getActionCommand() {
        return actionCommand;
    }

    public void setActionCommand(String newValue) {
        String oldValue = actionCommand;
        actionCommand = newValue;
        firePropertyChange(ACTION_COMMAND_CHANGED_PROPERTY, oldValue, newValue);
    }
    
    private void fireAction() {
        EventListener[] actionListeners 
                = getEventListenerList().getListeners(ActionListener.class);
        ActionEvent e = new ActionEvent(this, getActionCommand());
        for (int i = 0; i < actionListeners.length; ++i) {
            ((ActionListener) actionListeners[i]).actionPerformed(e);
        }
    }
}
