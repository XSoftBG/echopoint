/*
 * Copyright (C) 2011 XSoft Ltd. (info@xsoftbg.com)
 *
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
import java.util.logging.Level;
import java.util.logging.Logger;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;

/**
 * A component that represents a image gallery.
 *
 * @author sieskei 2011-03-14
 * @version $Id: Gallery.java,v 1.2 2011-03-18 15:52:29 yozov Exp $
 */
public class Gallery extends Component
{
  /** The logger to use to log the download progress. */
  protected static final Logger logger = Logger.getAnonymousLogger();

  private static final long serialVersionUID = 1l;

  /** The images for the anchor tag. */
  public static final String PROPERTY_IMAGES    = "images";
  public static final String PROPERTY_CSS       = "css";
  public static final String PROPERTY_WIDTH     = "width";
  public static final String PROPERTY_HEIGHT    = "height";
  public static final String PROPERTY_EMPTY_MSG = "empty_msg";
  public static final String cssReference    = getFileAsString("resource/js/jquery/bxGallery.css");

  public static String getFileAsString(String resource)
  {
    InputStreamReader in = null;
    StringBuilder sb = new StringBuilder();

    try
    {
      in = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(resource));
      if (in == null)
      {
        throw new IllegalArgumentException("Specified resource does not exist: " + resource + ".");
      }
      int character;
      while ((character = in.read()) != -1)
      {
        sb.append((char) character);
      }
    }
    catch (Exception e)
    {
      logger.log(Level.SEVERE, "Could not load resource <"+resource+">", e);
    }
    finally
    {
      if (in != null) { try { in.close(); } catch (IOException ex) { } }
    }
    return sb.toString();
  }

  public Gallery()
  {
    super();
    setCSS(cssReference);
  }

  /**
   * Return the images attribute for the gallery.
   *
   * @return The images value.
   */
  public String getImages()
  {
    return (String) get( PROPERTY_IMAGES );
  }

  /**
   * Set the value for the images attribute to be applied to the gallery.
   *
   * @see #setImages(echopoint.Gallery.Images)
   * @param images The value to set.
   */
  public void setImages(final String images )
  {
    set( PROPERTY_IMAGES, images );
  }

  /**
   * Returns the cascading style sheet for this calendar.
   *
   * @return the cascading style sheet
   */
  public String getCSS()
  {
    return (String) get(PROPERTY_CSS);
  }

  /**
   * Sets the cascading style sheet for this gallery.
   *
   * @param CSS the new style
   */
  public void setCSS(String CSS)
  {
    set(PROPERTY_CSS, CSS);
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

  public String getEmptyMSG()
  {
    return (String) get( PROPERTY_EMPTY_MSG );
  }

  public void setEmptyMSG(final String message )
  {
    set( PROPERTY_EMPTY_MSG, message );
  }
}
