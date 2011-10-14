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
import static org.junit.Assert.assertNotNull;
import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Rakesh 2008-07-13
 * @version $Id: HttpPaneTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class HttpPaneTest extends AbstractTest<HttpPane>
{
  /** The default URI to load in the test component. */
  private static final String DEFAULT_URI =
      "https://echopoint.dev.java.net/";

  @BeforeClass
  public static void init()
  {
    final Border border = new Border( new Extent( 2 ),
        new Color( 0xcfdfff ), Border.STYLE_GROOVE );
    final Extent height = new Extent( 500 );
    final Extent width = new Extent( 600 );

    final Component content = Application.getContent().getTestArea();
    final HttpPane comp = new HttpPane();
    comp.setRenderId( "echopointUnitTestHttpPane" );
    comp.setBorder( border );
    comp.setHeight( height );
    comp.setWidth( width );

    content.removeAll();
    content.add( comp );

    assertEquals( "Ensuring test component added to container",
        content.getComponentCount(), 1 );
    set( comp );
  }

  @Test
  public void getUri()
  {
    assertNotNull( "Ensuring that uri is not null", getComponent().getUri() );
  }

  @Test
  public void set()
  {
    getComponent().setUri( DEFAULT_URI );
    assertEquals( "Ensuring setting URI succeeded",
        getComponent().getUri(), DEFAULT_URI );
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();

    final TextField textField = createTextField();
    content.add( textField );
    content.add( createButton( textField ) );
    content.add( get() );
  }

  private static TextField createTextField()
  {
    final TextField field = new TextField();
    field.setText( DEFAULT_URI );
    field.setStyleName( "Default" );
    return field;
  }

  private static Button createButton( final TextField field )
  {
    final Button button = new Button( "Load" );
    button.setStyleName( "Default" );

    button.addActionListener( new ActionListener()
    {
      private static final long serialVersionUID = 1l;

      public void actionPerformed( final ActionEvent event )
      {
        ( (HttpPane) get() ).setUri( field.getText() );
      }
    });

    return button;
  }
}
