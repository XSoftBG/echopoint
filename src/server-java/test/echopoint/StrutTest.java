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
import static org.junit.Assert.fail;
import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Column;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Label;
import nextapp.echo.app.Row;
import nextapp.echo.app.TextField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test suite for the Strut component.
 *
 * @author Rakesh 2008-07-20
 * @version $Id: StrutTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class StrutTest extends AbstractTest<Strut>
{
  @BeforeClass
  public static void init()
  {
    set( new Strut() );
  }

  @Test
  public void width()
  {
    final int width = 50;
    getComponent().setWidth( new Extent( width ) );
    assertEquals( getComponent().getWidth().getValue(), width );
  }

  @Test
  public void height()
  {
    final int height = 25;
    getComponent().setHeight( new Extent( height ) );
    assertEquals( getComponent().getHeight().getValue(), height );
  }

  @Test
  public void nochild()
  {
    try
    {
      getComponent().add( new Strut() );
      fail( "Strut cannot hold children" );
    }
    catch ( Throwable t ) {}
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();

    content.add( createRow() );
    content.add( createColumn() );
  }

  private static Row createRow()
  {
    final Row row = new Row();
    row.add( new Label( "Label 1" ) );

    final Strut strut = new Strut( new Extent( 200, Extent.PX ),
        new Extent( 20, Extent.PX ) );
    strut.setBorder( new Border( 1, Color.BLACK, Border.STYLE_SOLID ) );
    strut.setRenderId( "echopointUnitTestStrutRow" );
    row.add( strut );

    row.add( new Label( "Label 2" ) );
    row.add( new Strut() );

    final TextField textField = new TextField();
    row.add( textField );

    row.add( new Strut() );
    row.add( createButton( "Change Width",
        Strut.PROPERTY_WIDTH, strut, textField ) );

    return row;
  }

  private static Column createColumn()
  {
    final Column column = new Column();
    column.add( new Label( "Label 3" ) );
    final Strut strut = new Strut( new Extent( 200, Extent.PX ),
        new Extent( 100, Extent.PX ) );
    strut.setBorder( new Border( 1, Color.BLACK, Border.STYLE_SOLID ) );
    strut.setRenderId( "echopointUnitTestStrutColumn" );
    column.add( strut );
    column.add( new Label( "Label 4" ) );

    column.add( new Strut() );

    final TextField textField = new TextField();
    column.add( textField );

    column.add( new Strut() );

    final Row row = new Row();
    row.add( createButton( "Change Height",
        Strut.PROPERTY_HEIGHT, strut, textField ) );
    column.add( row );

    return column;
  }

  private static Button createButton( final String title,
      final String property, final Strut strut, final TextField textField )
  {
    final Button button = new Button( title );
    button.addActionListener( new ActionListener()
    {
      private static final long serialVersionUID = 1l;

      public void actionPerformed( final ActionEvent event )
      {
        final int value = Integer.parseInt( textField.getText() );
        final Extent extent = new Extent( value, Extent.PX );
        strut.set( property, extent );
      }
    });

    return button;
  }
}
