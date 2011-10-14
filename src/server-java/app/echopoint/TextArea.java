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

import echopoint.internal.TextField;
import nextapp.echo.app.Extent;

/**
 * The {@code TextArea}.
 *
 * @version $Id: TextArea.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class TextArea extends TextField
{
  private static final long serialVersionUID = 1l;

  public static final String PROPERTY_WRAP = "wrap";
  public static final String PROPERTY_AUTO_RESIZE = "autoResize";

  /**
   * Creates a new TextArea with a empty content
   *
   * @param document
   */
  public TextArea()
  {
  }

  /**
   * Creates a new TextArea with the specified initial text
   *
   * @param document
   */
  public TextArea(String text)
  {
      setText(text);
  }

  public TextArea(String text,
                int columns,
                int rows)
  {
      setText(text);
      setWidth(new Extent(columns, Extent.EM));
      setHeight(new Extent(rows, Extent.EM));
  };


    /**
     * Sets the wrap state of this component.
     *
     * wrap true:  Means that the textarea does a virtual wrap of the text,
     *             but does not insert <br/> or any other line breaks in the
     *             text itself.
     *
     * wrap false: Do not wrap text in the textarea, but instead show horizontal
     *             scrollbars if needed
     *
     * @param newValue the new wrap state
     */
    public void setWrap(boolean newValue) {
        if (newValue)
        {
            set(PROPERTY_WRAP, "virtual");
        }
        else
        {
            set(PROPERTY_WRAP, "off");
        }
    }

    /**
     * Sets the auto resize state of this component.
     *
     * AutoResize = true means that the text area expands as text is entered in the area
     *
     * @param newValue the new wrap state
     */
    public void setAutoResize(boolean newValue) {
        set(PROPERTY_AUTO_RESIZE, newValue);
    }
}

