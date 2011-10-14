/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package echopoint.fcktest;

import nextapp.echo.app.Extent;
import nextapp.echo.app.WindowPane;

/**
 *
 * @author a.schild
 */
public abstract class ATestWindow extends WindowPane
{
    public ATestWindow(String windowTitle, Extent e1, Extent e2)
    {
        super(windowTitle, e1, e2);
    }
}
