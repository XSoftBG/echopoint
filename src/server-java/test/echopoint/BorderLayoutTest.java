package echopoint;

import static org.junit.Assert.assertEquals;
import nextapp.echo.app.Alignment;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.layout.TableLayoutData;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <p>&copy; Copyright 2009 <a href='http://sptci.com/' target='_top'>Sans
 * Pareil Technologies, Inc.</a></p>
 *
 * @author Rakesh Vidyadharan 2009-04-02
 * @version $Id: BorderLayoutTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class BorderLayoutTest extends AbstractTest<BorderLayout>
{
  @BeforeClass
  public static void init()
  {
    set( new BorderLayout() );
  }

  @Test
  public void width()
  {
    final Extent width = new Extent( 500 );
    getComponent().setWidth( width );
    assertEquals( "Ensure width set", width, getComponent().getWidth() );
  }

  @Test
  public void north()
  {
    final HtmlLabel label = new HtmlLabel();
    label.setText( "<b>This is the north content.  Should stretch across area.</b>" );

    final TableLayoutData layout = new TableLayoutData();
    layout.setAlignment( Alignment.ALIGN_CENTER );
    layout.setBackground( new Color( 0x0000ff ) );
    label.setLayoutData( layout );

    getComponent().add( label, BorderLayout.Region.north );
    assertEquals( "Ensure component added to north", label,
        getComponent().getComponent( BorderLayout.Region.north ) );
  }

  @Test
  public void west()
  {
    final HtmlLabel label = new HtmlLabel();
    label.setText( "<b>West content</b>" );

    final TableLayoutData layout = new TableLayoutData();
    layout.setBackground( new Color( 0x00ff00 ) );
    label.setLayoutData( layout );

    getComponent().add( label, BorderLayout.Region.west );
    assertEquals( "Ensure component added to west", label,
        getComponent().getComponent( BorderLayout.Region.west ) );
  }

  @Test
  public void centre()
  {
    final HtmlLabel label = new HtmlLabel();
    label.setText( "<b>This is the centre content.  Should fill most of central area.</b>" );

    final TableLayoutData layout = new TableLayoutData();
    layout.setAlignment( Alignment.ALIGN_CENTER );
    layout.setBackground( new Color( 0xa1a1a1 ) );
    label.setLayoutData( layout );

    getComponent().add( label, BorderLayout.Region.center );
    assertEquals( "Ensure component added to centre", label,
        getComponent().getComponent( BorderLayout.Region.center ) );
  }

  @Test
  public void east()
  {
    final HtmlLabel label = new HtmlLabel();
    label.setText( "<b>East content</b>" );

    final TableLayoutData layout = new TableLayoutData();
    layout.setBackground( new Color( 0xff0000 ) );
    label.setLayoutData( layout );

    getComponent().add( label, BorderLayout.Region.east );
    assertEquals( "Ensure component added to east", label,
        getComponent().getComponent( BorderLayout.Region.east ) );
  }

  @Test
  public void south()
  {
    final HtmlLabel label = new HtmlLabel();
    label.setText( "<b>This is the south content.  Should stretch across area.</b>" );

    final TableLayoutData layout = new TableLayoutData();
    layout.setAlignment( Alignment.ALIGN_CENTER );
    layout.setBackground( new Color( 0x2f2f4f ) );
    label.setLayoutData( layout );

    getComponent().add( label, BorderLayout.Region.south );
    assertEquals( "Ensure component added to south", label,
        getComponent().getComponent( BorderLayout.Region.south ) );
  }

  @AfterClass
  public static void finish()
  {
    final Component content = Application.getContent().getTestArea();
    content.removeAll();
    content.add( get() );
  }
}
