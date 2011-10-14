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
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import echopoint.able.Alignable;
import echopoint.able.Sizeable;

/**
 * The <code>Clock</code> class is a <code>Component</code>
 * that presents the current time.
 * It is based on the jQuery plugin jClock
 *
 * @author Hans Holmlund - 2009-03-23
 * @version $Id: Clock.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class Clock extends Component implements Sizeable, Alignable {

    public static final String PROPERTY_INSETS = "insets";
    public static final String PROPERTY_BORDER = "border";
    public static final String PROPERTY_FORMAT = "format";

    /**
     * Returns the <code>Border</code> that encloses the entire <code>Clock</code>.
     *
     * @return the border
     */
    public Border getBorder() {
        return (Border) get(PROPERTY_BORDER);
    }

    /**
     * Returns the default inset between the border and cells of the
     * <code>Clock</code>. This value will be overridden for a child
     * component if a setting is specified in its <code>ColumnLayoutData</code>.
     *
     * @return the inset
     */
    public Insets getInsets() {
        return (Insets) get(PROPERTY_INSETS);
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
     * Sets the inset between the border and cells of the <code>Clock</code>.
     * This value will be overridden for a child component if a setting is
     * specified in its <code>ColumnLayoutData</code>.
     *
     * @param newValue the new inset
     */
    public void setInsets(Insets newValue) {
        set(PROPERTY_INSETS, newValue);
    }

    /**
     * Sets the width extent of the Clock component.
     *
     * @param newValue - the new width extent of the Clock component
     */
    public void setWidth(Extent newValue) {
        set(PROPERTY_WIDTH,newValue);
    }

    /**
     * Returns the width extent of the Clock component.
     * @return the width extent of the Clock component.
     */
    public Extent getWidth() {
        return (Extent) get(PROPERTY_WIDTH);
    }

    /**
     * Sets the height extent of the Clock component.
     *
     * @param newValue - the new height extent of the Clock component
     */
    public void setHeight(Extent newValue) {
        set(PROPERTY_HEIGHT,newValue);
    }

    /**
     * Returns the height extent of Clock component.
     *
     * @return the height extent of Clock component.
     */
    public Extent getHeight() {
        return (Extent) get(PROPERTY_HEIGHT);
    }

    /**
     * Returns the alignment of the Clock component.
     *
     * @return the alignment
     */
    public Alignment getAlignment() {
        return (Alignment) get(PROPERTY_ALIGNMENT);
    }

    /**
     * Sets the alignment of the Clock component.
     *
     * @param newValue the new alignment
     */
    public void setAlignment(Alignment newValue) {
        set(PROPERTY_ALIGNMENT, newValue);
    }


    /**
     * Returns the display format of the Clock component.
     *
     * @return the format
     */
    public String getFormat() {
        return (String) get(PROPERTY_FORMAT);
    }

    /**
     * Sets the display format of the Clock component (default value: %H:%M:%S).
     *  %a - day names,
     *  %A - full day names,
     *  %b - month names,
     *  %B - full month names,
     *  %d - day: range 01 to 31,
     *  %H - hour as a decimal number using a 24-hour clock (range 00 to 23),
     *  %I - hour as a decimal number using a 12-hour clock (range 01 to 12), 
     *  %m - month number,
     *  %M - minute as a decimal number,
     *  %p - either `am' or `pm' according to the given time value,
     *  %P - either `AM' or `PM' according to the given time value,
     *  %S - second as a decimal number,
     *  %y - two-digit year,
     *  %Y - full year.
     *
     * @param newValue the new format
     */
    public void setFormat(String newValue) {
        set(PROPERTY_FORMAT, newValue);
    }
}
