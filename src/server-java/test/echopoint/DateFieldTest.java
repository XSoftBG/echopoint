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

import java.util.Date;

import nextapp.echo.app.Color;
import nextapp.echo.app.Column;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Label;
import nextapp.echo.app.Row;
import nextapp.echo.app.SelectField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.jquery.DateField;

/**
 * Test case for the {@link echopoint.jquery.DateField} component.
 *
 * @author Hans Holmlund 2009-04-29
 * @version $Id: DateFieldTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class DateFieldTest extends AbstractTest<DateField> {

    protected static Label dateLabel;
    protected static DateField dateField2;
    protected static DateField dateField3;

    @BeforeClass
    public static void init()
    {
       
        ContainerEx containerEx = new ContainerEx();
        containerEx.setLayoutStyle(ContainerEx.COLUMN_LAYOUT);
        containerEx.setBackground(Color.GREEN);
        containerEx.setHeight(new Extent(350));
        containerEx.setWidth(new Extent(700));
        DateField dateField = new DateField();
        dateField.setBackground(Color.LIGHTGRAY);
        containerEx.add(dateField);
        DateField dateField2 = new DateField();
        dateField2.setBackground(Color.YELLOW);
        dateField2.setHeight(new Extent(300));
        dateField2.setWidth(new Extent(600));
        containerEx.add(dateField2);
        set(containerEx);
    }

  @Test
  public void simple()
  {
      // Dummytest
  }


    /*
    @Test
    public void renderId()
    {
        final String renderId = "echopointUnitTestDateField";
        getComponent().setRenderId( renderId );
        assertEquals( "Ensuring renderId set", renderId, getComponent().getRenderId() );
    }
     */

    /*
    @Test
    public void alignment()
    {
        final Alignment alignment = Alignment.ALIGN_LEFT;
        getComponent().setAlignment( alignment );
        assertEquals( "Ensuring alignment set", alignment, getComponent().getAlignment() );
    }
     */

    /*
    @Test
    public void insets()
    {
        final Insets insets = new Insets( 10 );
        getComponent().setInsets( insets );
        assertEquals( "Ensuring insets set", insets, getComponent().getInsets() );
    }
     */

/*
    @Test
    public void font()
    {
        final Font font = new Font( Font.HELVETICA, Font.PLAIN, new Extent( 11 ) );
        getComponent().setFont( font );
        assertEquals( "Ensuring Font set", font, getComponent().getFont() );
    }
*/
    
/*
    @Test
    public void foreground()
    {
        final Color color = new Color( 0x2f2f4f );
        getComponent().setForeground( color );
        assertEquals( "Ensuring Foreground set", color, getComponent().getForeground() );
    }
*/

/*
    @Test
    public void width()
    {
        final Extent width = new Extent( 600 );
        getComponent().setWidth( width );
        assertEquals( "Ensuring width set", width, getComponent().getWidth() );
    }
*/
    /*
    @Test
    public void dateFormat()
    {
        final String dateFormat = "yyyy/MM/dd HH:mm";
        getComponent().setDateFormat( dateFormat );
        assertEquals( "Ensuring dateformat set", dateFormat, getComponent().getDateFormat() );
    }
     */

    @AfterClass
    public static void finish()
    {
        final Component content = Application.getContent().getTestArea();
        content.removeAll();
        final Column column = new Column();
        column.add( get() );
        // The following rows are used to test the Z-index behaviour
        Row d1row= new Row();
        dateField2= new DateField();
        dateField2.setDateFormat("dd.MM.yyyy");
        dateField2.setFirstDayOfWeek(1);
        dateField2.setInputWidth(new Extent(8, Extent.EM));
        dateField2.setShowWeeks(true);
        d1row.add( dateField2 );
        d1row.add(new Label("The following rows are used to test the Z-index behaviour"));
        column.add( d1row );
        Row d2row= new Row();
        dateField3= new DateField();
        dateField3.setDate(new Date());
        d2row.add( dateField3 );
        d2row.add(new Label("Field initialised with date"));
        column.add(d2row);
        column.add( new SelectField() );

        Row d3row= new Row();
        PushButton pButton= new PushButton("Get Date value from second date field");
        d3row.add(pButton);
        pButton.addActionListener(
                new ActionListener() {
              private static final long serialVersionUID = 1l;

              public void actionPerformed( final ActionEvent event )
              {
                  Date myDate= dateField2.getDate();
                  if (myDate != null)
                  {
                        dateLabel.setText( myDate.toLocaleString() );
                  }
                  else
                  {
                      dateLabel.setText("Date value is null");
                  }
              }
            }
                );
        dateLabel= new Label();
        d3row.add(dateLabel);

        column.add(d3row);

        content.add( column );
    }
    
}
