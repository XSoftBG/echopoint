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
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.google.chart.QRCode;
import echopoint.google.chart.model.Title;

/**
 * Unit test suite for the {@link echopoint.google.chart.QRCode} component
 * wrapper.
 *
 * @author Rakesh Vidyadharan 2008-08-28
 * @version $Id: QRCodeTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class QRCodeTest extends GoogleChartTest<QRCode>
{
  @BeforeClass
  public static void init()
  {
    set( new QRCode() );
  }

  @Test
  public void renderId()
  {
    final String id = "echopointUnitTestSimpleQRCode";
    getComponent().setRenderId( id );
    assertEquals( "Ensuring render id is same", id, getComponent().getRenderId() );
  }

  @Test
  public void fill()
  {
    final String fill = "bg,s,efefef|c,lg,45,ffffff,0,76a4fb,0.75";
    getComponent().setFill( fill );
    assertEquals( "Ensuring fill set", fill, getComponent().getFill() );
  }

  @Test
  public void title()
  {
    final Title title = new Title();
    title.add( "QR Codes" );
    getComponent().setTitle( title );

    assertEquals( "Ensuring title set", title, getComponent().getTitle() );
  }

  @Test
  public void height()
  {
    getComponent().setHeight( new Extent( 300 ) );
    assertNotNull( "Ensuring height set", getComponent().getHeight() );
  }

  @Test
  public void width()
  {
    getComponent().setWidth( new Extent( 400 ) );
    assertNotNull( "Ensuring width set", getComponent().getWidth() );
  }

  @Test
  public void insets()
  {
    getComponent().setInsets( new Insets( 10 ) );
    assertNotNull( "Ensuring insets set", getComponent().getInsets() );
  }

  @Test
  public void text()
  {
    final String text = "EchoPoint text to be encoded";
    getComponent().setText( text );
    assertEquals( "Ensuring text set", text, getComponent().getText() );
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();
    content.add( get() );
  }
}