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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Column;
import nextapp.echo.app.Component;
import nextapp.echo.app.Label;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.event.TagEvent;
import echopoint.model.Tag;

/**
 * Unit test suite for the {@link TagCloud} component.
 *
 * @author Rakesh 2008-07-20
 * @version $Id: TagCloudTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class TagCloudTest extends AbstractTest<TagCloud>
{
  static Color background;
  static Color foreground;

  @BeforeClass
  public static void init()
  {
    final TagCloud tagCloud = new TagCloud();
    tagCloud.setRenderId( "echopointUnitTestTagCloud" );
    background = new Color( 0xa1a1a1 );
    foreground = new Color( 0xc1c1c1 );
    set( tagCloud );
  }

  @Test
  public void enabled()
  {
    getComponent().setRolloverEnabled( true );
    assertTrue( "Ensuring enabled set", getComponent().getRolloverEnabled() );
  }

  @Test
  public void background()
  {
    getComponent().setRolloverBackground( background );
    assertEquals( "Ensuring background set",
        getComponent().getRolloverBackground(), background );
  }

  @Test
  public void foreground()
  {
    getComponent().setRolloverForeground( foreground );
    assertEquals( "Ensuring foreground set",
        getComponent().getRolloverForeground(), foreground );
  }

  @Test
  public void data()
  {
    getComponent().setTags( createTags() );
    assertEquals( "Ensuring same size of tags", getComponent().getTags().size(), 10 );
  }

  @Test
  public void add()
  {
    try
    {
      getComponent().add( new TagCloud() );
      fail( "TagCloudStyle cannot contain children" );
    }
    catch ( Throwable t ) {}
  }

  @Test
  public void listener()
  {
    getComponent().addActionListener( new ActionListener()
    {
      public void actionPerformed( final ActionEvent event )
      {
        final TagEvent te = (TagEvent) event;
        if ( te.getTag() != null )
        {
          final Component content = Application.getContent().getTestArea();
          content.add( new Label( "Clicked Tag: " + te.getTag().getName() ) );
        }
      }
    });
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();

    final Row row = new Row();
    final TagCloud tagCloud = (TagCloud) get();
    row.add( tagCloud );

    final Column column = new Column();
    column.add( row );
    column.add( createUpdate() );
    content.add( column );
  }

  private static Collection<Tag> createTags()
  {
    final Collection<Tag> tags = new ArrayList<Tag>( 10 );
    final Random random = new Random( System.currentTimeMillis() );

    for ( int i = 0; i < 10; ++i )
    {
      tags.add( new Tag( "Tag " + i, random.nextInt( 10 ) ) );
    }

    return tags;
  }

  private static Row createUpdate()
  {
    final Row row = new Row();

    final Button button = new Button( "Update" );
    button.setRenderId( "echopointUnitTestTagCloudUpdate" );
    button.addActionListener(
    new ActionListener()
    {
      private static final long serialVersionUID = 1l;

      public void actionPerformed( final ActionEvent event )
      {
        final TagCloud tagCloud =  (TagCloud) get();
        tagCloud.setTags( createTags() );
        final Component parent = tagCloud.getParent();
        final int index = parent.indexOf( tagCloud );
        parent.remove( tagCloud );
        parent.add( tagCloud, index );
      }
    });

    row.add( button );
    return row;
  }
}
