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
 * @version $Id: FckeditorPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
package echopoint;

import nextapp.echo.app.Component;
import nextapp.echo.app.update.ClientUpdateManager;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.AbstractComponentSynchronizePeer;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

public class FckeditorPeer extends AbstractComponentSynchronizePeer
{

    private static final String FCK_EDITOR = "Aar.Fckeditor";

    static
    {
        WebContainerServlet.getServiceRegistry().add(
                JavaScriptService.forResource(FCK_EDITOR,
                "resource/js/Aar.Fckeditor.js"));
    }

    public FckeditorPeer()
    {
        super();
        addOutputProperty(Fckeditor.TEXT_CHANGED_PROPERTY);
    }

    /**
     * Used for client->server updates
     * 
     * @param context
     * @param component
     * @param propertyName
     * @param propertyIndex
     * @return
     */
    @Override
    public Object getOutputProperty(Context context, Component component,
            String propertyName, int propertyIndex) {
        if (propertyName.equals(Fckeditor.TEXT_CHANGED_PROPERTY)) {
            Fckeditor rta = (Fckeditor) component;
            return rta.getText();
        } else {
            return super.getOutputProperty(context, component, propertyName, propertyIndex);
        }
    }

    @Override
    public void init(Context context, Component component)
    {
        super.init(context, component);
        ServerMessage serverMessage = (ServerMessage) context.get(ServerMessage.class);
        serverMessage.addLibrary(FCK_EDITOR);

        // Initialize a default-configuration
        Fckeditor richTextArea = (Fckeditor) component;
    }

    @Override
    public Class getComponentClass()
    {
        return Fckeditor.class;
    }

    @Override
    public String getClientComponentType(boolean shortType)
    {
        return FCK_EDITOR;
    }

    /**
     * What class is used to send back text changes to the server component
     *
     * @param propertyName
     * @return
     */
    @Override
    public Class getInputPropertyClass(String propertyName)
    {
        if (Fckeditor.TEXT_CHANGED_PROPERTY.equals(propertyName))
        {
            return String.class;
        }
        return null;
    }

    /**
     * Store the client updated value on the server side
     *
     * @param context
     * @param component
     * @param propertyName
     * @param propertyIndex
     * @param newValue
     */
    @Override
    public void storeInputProperty(Context context, Component component,
            String propertyName, int propertyIndex, Object newValue)
    {
        if (propertyName.equals(Fckeditor.TEXT_CHANGED_PROPERTY))
        {
            ClientUpdateManager clientUpdateManager =
                    (ClientUpdateManager) context.get(ClientUpdateManager.class);
            clientUpdateManager.setComponentProperty(component,
                    Fckeditor.TEXT_CHANGED_PROPERTY, newValue);
        }
    }
}
