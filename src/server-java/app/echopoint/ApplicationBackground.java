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

import nextapp.echo.app.Component;
import nextapp.echo.app.ImageReference;

/**
 * This component sets the background attribute for the body tag.
 * This is needed in some cases when you want a background image to cover the whole page, whatever you put into it.
 * @author MikaelS 2009-04-28
 * @version $Id: ApplicationBackground.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class ApplicationBackground extends Component {
     /** The image reference for the component.  This property may be styled. */
  public static final String PROPERTY_IMAGE = "url";

    public ApplicationBackground(ImageReference image) {
        setImage(image);
    }

    public ImageReference getImage() {
        return (ImageReference) get( PROPERTY_IMAGE );
    }

    public void setImage(ImageReference image) {

    set( PROPERTY_IMAGE, image );
    }
}
