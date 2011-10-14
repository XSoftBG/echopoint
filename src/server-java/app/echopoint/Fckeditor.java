/*
 * Copyright (C) 2009 Andre Schild (a.schild@aarboard.ch)
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or the
 * GNU Lesser General Public License Version 2.1 or later (the "LGPL"), in which
 * case the provisions of the GPL or the LGPL are applicable instead of those
 * above. If you wish to allow use of your version of this file only under the
 * terms of either the GPL or the LGPL, and not to allow others to use your
 * version of this file under the terms of the MPL, indicate your decision by
 * deleting the provisions above and replace them with the notice and other
 * provisions required by the GPL or the LGPL. If you do not delete the
 * provisions above, a recipient may use your version of this file under the
 * terms of any one of the MPL, the GPL or the LGPL.
 *
 * @author Andre Schild Aarboard 2009-06-17
 * @version $Id: Fckeditor.java,v 1.3 2011-05-28 13:22:07 perxi Exp $
 */
package echopoint;

import java.util.Properties;

import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;

public class Fckeditor extends Component
{

    public static final String PROPERTY_FCKEDITOR_URL = "fckeditorURL";
    public static final String PROPERTY_FCKEDITOR_CONFIG_URL = "fckeditorConfigURL";
    public static final String PROPERTY_FCKEDITOR_CSS_URL = "fckeditorCssURL";
    public static final String PROPERTY_WIDTH = "width";
    public static final String PROPERTY_HEIGHT = "height";
    public static final String PROPERTY_CONFIG = "config";
    public static final String PROPERTY_TOOLBAR = "toolbar";
    public static final String PROPERTY_DEBUG = "debug";
    public static final String PROPERTY_TOOLBAR_COLLAPSED = "toolbarCollapsed";
    public static final String PROPERTY_AUTOCOLLAPSE_TOOLBAR = "toolbarAutocollapse";
    public static final String TEXT_CHANGED_PROPERTY = "text";

    public static final String Version = "0.5";
    
    private String text;    // Holding the editor content

    /**
     * Works (at least) with Fckeditor versions 2.6.4 and 2.6.5
     */
    public Fckeditor()
    {
        super();
    }

    public void setFckeditorURL(String newValue)
    {
        set(PROPERTY_FCKEDITOR_URL, newValue);
    }


    public void setFckeditorConfigURL(String newValue)
    {
        set(PROPERTY_FCKEDITOR_CONFIG_URL, newValue);
    }

    public void setFckeditorCssURL(String newValue)
    {
        set(PROPERTY_FCKEDITOR_CSS_URL, newValue);
    }

    /**
     * Set the width of the editor
     *
     * @param width
     */
    public void setWidth(Extent width)
    {
        set(PROPERTY_WIDTH, width);
    }

    /**
     * Set the height of the editor
     * 
     * @param height
     */
    public void setHeight(Extent height)
    {
        set(PROPERTY_HEIGHT, height);
    }

    /**
     * Set content of editor
     *
     * @param newValue
     */
    public void setText(String newValue)
    {
        String oldValue= this.text;
        this.text = newValue;
        firePropertyChange(TEXT_CHANGED_PROPERTY, oldValue, newValue);
    }

    /**
     * Get content of editor
     * 
     * @return
     */
    public String getText()
    {
        return this.text;
    }

    /**
     * This is the method which received updates from the client and stores it on the local object
     * 
     * @see nextapp.echo.app.Component#processInput(java.lang.String, java.lang.Object)
     */
    @Override
    public void processInput(String inputName, Object inputValue)
    {
        if (TEXT_CHANGED_PROPERTY.equals(inputName))
        {
            if (inputValue != null)
            {
                this.text = inputValue.toString();
            }
            else
            {
                this.text= null;
            }
        }
    }

    /**
     * Set editor configuration options
     * 
     * @param newValue
     */
    public void setConfig(Properties newValue)
    {
        set(PROPERTY_CONFIG, newValue);
    }

    /**
     * Use the toolbar with this name
     * 
     * @param newValue
     */
    public void setToolbar(String newValue)
    {
        set(PROPERTY_TOOLBAR, newValue);
    }

    /**
     * When turned on we write many log messages to the client side log window.
     * To see the window just add ?debug to the url
     * 
     * @param newValue
     */
    public void setDebug(boolean newValue)
    {
        set(PROPERTY_DEBUG, newValue);
    }

    /**
     * Should the toolbar be shown in collapsed mode or not
     * 
     * @param newValue True = Toolbar collapsed
     * 
     */
    public void setToolbarCollapsed(boolean newValue)
    {
        set(PROPERTY_TOOLBAR_COLLAPSED, newValue);
    }

    /**
     * When set to true the toolbar is collapsed when the editor does NOT have
     * the focus.
     * When the editor receives the focus the toolbar is expanded (and on loss
     * of focus collapsed again)
     *
     * @param newValue 
     */
    public void setToolbarAutocollapse(boolean newValue)
    {
        set(PROPERTY_AUTOCOLLAPSE_TOOLBAR, newValue);
    }
}
