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

import static org.junit.Assert.assertEquals;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Label;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.model.Cursor;

/**
 * Unit test case for the {@link echopoint.ImageIcon} component.
 *
 * @author Rakesh Vidyadharan 2008-10-20
 * @version $Id: ImageIconTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class ImageIconTest extends AbstractTest<ImageIcon>
{
  static int count;
  static final String command = "actionCommand";

  @BeforeClass
  public static void init()
  {
    count = 0;
    set( new ImageIcon( "image/imagemap.gif" ) );
  }

  @Test
  public void renderId()
  {
    final String renderId = "echopointUnitTestImageIcon";
    getComponent().setRenderId( renderId );
    assertEquals( "Ensuring renderId set", renderId,
        getComponent().getRenderId() );
  }

  @Test
  public void actionCommand()
  {
    getComponent().setActionCommand( command );
    assertEquals( "Ensuring action command set", command,
        getComponent().getActionCommand() );
  }

  @Test
  public void cursor()
  {
    final Cursor cursor = Cursor.crosshair;
    getComponent().setCursor( cursor );
    assertEquals( "Ensuring cursor set", cursor, getComponent().getCursor() );
  }

  @Test
  public void text()
  {
    final String text = "Image Icon";
    getComponent().setText( text );
    assertEquals( "Ensuring text set", text, getComponent().getText() );
  }

  @Test
  public void toolTipText()
  {
    final String text = "Image Icon";
    getComponent().setToolTipText( text );
    assertEquals( "Ensuring tool-tip text set", text, getComponent().getToolTipText() );
  }

  @Test
  public void width()
  {
    final int width = 500;
    getComponent().setWidth( new Extent( width ) );
    assertEquals( "Ensuring that width is set", width,
        getComponent().getWidth().getValue() );
  }

  @Test
  public void height()
  {
    final int height = 300;
    getComponent().setHeight( new Extent( height ) );
    assertEquals( "Ensuring that height is set", height,
        getComponent().getHeight().getValue() );
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();
    final Label label = new Label( "Action command area" );

    ( (ImageIcon) get() ).addActionListener( new ActionListener() {
      private static final long serialVersionUID = 1l;

      public void actionPerformed( final ActionEvent event )
      {
        label.setText( "Button clicked: " + ++count );
        assertEquals( "Ensuring that action command is set",
            command, event.getActionCommand() );
      }
    });

    content.add( get() );
    content.add( label );
  }
}
