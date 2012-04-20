/*
 * Copyright (C) 2011 XSoft Ltd. (info@xsoftbg.com)
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
 * @author Miroslav Yozov
 * @version $Id: Ckeditor.java,v 1.5 2011-02-03 17:01:07 yozov Exp $
 */
package echopoint;

import java.util.Properties;

import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;

public class Ckeditor extends Component
{
  public static final String Version = "0.1b";
  private static final long serialVersionUID = 20101126L;

  public static final String PROPERTY_DEBUG = "debug";

  public static final String PROPERTY_CKEDITOR_URL = "ckeditorURL";
  public static final String PROPERTY_CKEDITOR_CONFIG_URL = "ckeditorConfigURL";
  public static final String PROPERTY_CKEDITOR_CSS_URL = "ckeditorCssURL";

  public static final String PROPERTY_FULLPAGE = "fullPage";
  public static final String PROPERTY_TOOLBAR_CAN_COLLAPSE = "toolbarCanCollapse";
  public static final String PROPERTY_TOOLBAR_LOCATION = "toolbarLocation";
  
  public static final String PROPERTY_THEME = "theme";

  public static final String PROPERTY_MAX_HEIGHT = "resize_maxHeight";
  public static final String PROPERTY_MAX_WIDTH  = "resize_maxWidth";

  public static final String PROPERTY_MIN_HEIGHT = "resize_minHeight";
  public static final String PROPERTY_MIN_WIDTH  = "resize_minWidth";

  public static final String PROPERTY_TEXT = "text";
  public static final String PROPERTY_WIDTH = "width";
  public static final String PROPERTY_HEIGHT = "height";
  public static final String PROPERTY_CONFIG = "config";
  public static final String PROPERTY_TOOLBAR = "toolbar";
  public static final String PROPERTY_RESIZABLE = "resizable";
  public static final String PROPERTY_LANGUAGE = "language";

  public static final String PROPERTY_AUTO_MAXIMIZE = "auto_maximize";

  
  private String text; // Holding the editor content
  
  public Ckeditor()
  {
    super();
  }

  public Ckeditor(String baseUrl)
  {
    super();
		setCkeditorURL(baseUrl);
  }

  public void setCkeditorURL(String newValue)
  {
    set(PROPERTY_CKEDITOR_URL, newValue);
  }


  public void setCkeditorConfigURL(String newValue)
  {
    set(PROPERTY_CKEDITOR_CONFIG_URL, newValue);
  }

  public void setCkeditorCssURL(String newValue)
  {
    set(PROPERTY_CKEDITOR_CSS_URL, newValue);
  }


  public void setFullPage(boolean newValue)
  {
    set(PROPERTY_FULLPAGE, Boolean.valueOf(newValue));
  }

  public boolean isFullPage()
  {
    Object property = get(PROPERTY_FULLPAGE);
    return null == property ? true : ((Boolean) property).booleanValue();
  }

  public void setToolbarCanCollapse(boolean newValue)
  {
    set(PROPERTY_TOOLBAR_CAN_COLLAPSE, Boolean.valueOf(newValue));
  }

  public boolean isToolbarCanCollapse()
  {
     Object property = get(PROPERTY_TOOLBAR_CAN_COLLAPSE);
    return null == property ? true : ((Boolean) property).booleanValue();
  }

  public void setToolbarLocation(String newValue)
  {
    set(PROPERTY_TOOLBAR_LOCATION, newValue);
  }

  public String getToolbarLocation()
  {
    return (String) get(PROPERTY_TOOLBAR_LOCATION);
  }

  public void setTheme(String newValue)
  {
    set(PROPERTY_THEME, newValue);
  }

  public String getTheme()
  {
    return (String) get(PROPERTY_THEME);
  }

  public void setMaxHeight(Extent height)
  {
    set(PROPERTY_MAX_HEIGHT, height);
  }

  public void setMaxWidth(Extent width)
  {
    set(PROPERTY_MAX_WIDTH, width);
  }

  public void setMinHeight(Extent height)
  {
    set(PROPERTY_MIN_HEIGHT, height);
  }

  public void setMinWidth(Extent width)
  {
    set(PROPERTY_MIN_WIDTH, width);
  }

  public void setText(String newValue)
  {
      String oldValue= this.text;
      this.text = newValue;
      firePropertyChange(PROPERTY_TEXT, oldValue, newValue);
  }

  public String getText()
  {
    return this.text;
  }

  public void setWidth(Extent width)
  {
    set(PROPERTY_WIDTH, width);
  }

  public Extent getWidth()
  {
    return (Extent) get(PROPERTY_WIDTH);
  }

  public void setHeight(Extent height)
  {
    set(PROPERTY_HEIGHT, height);
  }

  public Extent getHeight()
  {
    return (Extent) get(PROPERTY_HEIGHT);
  }

  public void setConfig(Properties newValue)
  {
      set(PROPERTY_CONFIG, newValue);
  }

  public void setToolbar(String newValue)
  {
    //System.out.println(newValue);
    set(PROPERTY_TOOLBAR, newValue);
  }

  public String getToolbar()
  {
    return (String) get(PROPERTY_TOOLBAR);
  }

  public void setResizable(boolean newValue)
  {
    set(PROPERTY_RESIZABLE, Boolean.valueOf(newValue));
  }

  public boolean isResizable()
  {
    Object property = get(PROPERTY_RESIZABLE);
    return null == property ? true : ((Boolean) property).booleanValue();
  }

  public void setLanguage(String newValue)
  {
    set(PROPERTY_LANGUAGE, newValue);
  }

  public String getLanguage()
  {
    return (String) get(PROPERTY_LANGUAGE);
  }

  public void autoMaximize()
  {
    set(PROPERTY_AUTO_MAXIMIZE, true);
  }

  public void setDebug(boolean newValue)
  {
    set(PROPERTY_DEBUG, newValue);
  }

  /**
   * This is the method which received updates from the client and stores it on the local object
   *
   * @see nextapp.echo.app.Component#processInput(java.lang.String, java.lang.Object)
   */
  
  @Override
  public void processInput(String inputName, Object inputValue)
  {
    //System.out.println("processInput: " + inputName + " | " +inputValue);

    super.processInput(inputName, inputValue);

    if (PROPERTY_TEXT.equals(inputName))
    {
      if (inputValue != null)
          this.text = inputValue.toString();
      else
          this.text= null;
    }
  }
}
