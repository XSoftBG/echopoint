package echopoint;

import static org.junit.Assert.assertEquals;
import nextapp.echo.app.Alignment;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Font;
import nextapp.echo.app.HttpImageReference;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Label;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.jquery.CarouselContainer;

/**
 * Test case for the {@link echopoint.jquery.TooltipContainer} component.
 *
 * @author Hans Holmlund 2009-07-10
 * @version $Id: CarouselContainerTest.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class CarouselContainerTest extends AbstractTest<CarouselContainer> {

    @BeforeClass
    public static void init()
    {
        set( new CarouselContainer() );
    }

    @Test
    public void renderId()
    {
        final String renderId = "echopointUnitTestTooltipContainer";
        getComponent().setRenderId( renderId );
        assertEquals( "Ensuring renderId set", renderId, getComponent().getRenderId() );
    }

    @Test
    public void alignment()
    {
        final Alignment alignment = Alignment.ALIGN_CENTER;
        getComponent().setAlignment( alignment );
        assertEquals( "Ensuring alignment set", alignment, getComponent().getAlignment() );
    }

    @Test
    public void insets()
    {
        final Insets insets = new Insets( 10 );
        getComponent().setInsets( insets );
        assertEquals( "Ensuring insets set", insets, getComponent().getInsets() );
    }

    @Test
    public void font()
    {
        final Font font = new Font( Font.HELVETICA, Font.PLAIN, new Extent( 11 ) );
        getComponent().setFont( font );
        assertEquals( "Ensuring Font set", font, getComponent().getFont() );
    }

    @Test
    public void foreground()
    {
        final Color color = new Color( 0x2f2f4f );
        getComponent().setForeground( color );
        assertEquals( "Ensuring Foreground set", color, getComponent().getForeground() );
    }

    @Test
    public void width()
    {
        final Extent width = new Extent( 250 );
        getComponent().setWidth( width );
        assertEquals( "Ensuring width set", width, getComponent().getWidth() );
    }

    @AfterClass
    public static void finish()
    {
        final Component content = Application.getContent().getTestArea();
        content.removeAll();
        ContainerEx cEx = new ContainerEx();
        content.add( cEx );

        cEx.setWidth(new Extent(600));
        cEx.setHeightStretched(true);
        cEx.setLayoutStyle(ContainerEx.COLUMN_LAYOUT);

        CarouselContainer cc = new CarouselContainer();
        cEx.add(cc);
        cc.add(new Label("Test1"));
        cc.add(new Label("Test2"));             
        cc.add(new Label("Test3"));
        cc.add(new Label("Test4"));
        cc.add(new Label("Test5"));
        cc.add(new Label("Test6"));
        cc.setBackground(Color.GREEN);

        CarouselContainer cc2 = new CarouselContainer();
        cc2.setWidth(new Extent(500));
        cc2.setHeight(new Extent(100));
        cc2.setVisible(5);
        cEx.add(cc2);
        cc2.add(new Label("TestA"));
        cc2.add(new Label("TestB"));
        cc2.add(new Label("TestC"));
        cc2.add(new Label("TestD"));
        cc2.add(new Label("TestE"));
        cc2.add(new Label("TestF"));
        cc2.add(new Label("TestG"));
        cc2.add(new Label("TestH"));
        cc2.add(new Label("TestI"));
            cc2.setLeftIcon(new HttpImageReference("http://www.freebuttons.com/freebuttons/GrabIt/GrabItDd0.gif"));
        cc2.setRightIcon(new HttpImageReference("http://www.freebuttons.com/freebuttons/GrabIt/GrabItDe0.gif"));
            cc2.setRightMouseOverIcon(new HttpImageReference("http://www.freebuttons.com/freebuttons/GrabIt/GrabItDd0.gif"));
        cc2.setLeftMouseOverIcon(new HttpImageReference("http://www.freebuttons.com/freebuttons/GrabIt/GrabItDe0.gif"));
        cc2.setBackground(Color.YELLOW);


           CarouselContainer cc3 = new CarouselContainer();
        cc3.setWidth(new Extent(500));
        cc3.setHeight(new Extent(100));
        cc3.setVisible(3);
        cEx.add(cc3);
        cc3.add(new Label("TestA"));
        cc3.add(new Label("TestB"));
        cc3.add(new Label("TestC"));
            cc3.setLeftIcon(new HttpImageReference("http://www.freebuttons.com/freebuttons/GrabIt/GrabItDd0.gif"));
        cc3.setRightIcon(new HttpImageReference("http://www.freebuttons.com/freebuttons/GrabIt/GrabItDe0.gif"));
            cc3.setRightMouseOverIcon(new HttpImageReference("http://www.freebuttons.com/freebuttons/GrabIt/GrabItDd0.gif"));
        cc3.setLeftMouseOverIcon(new HttpImageReference("http://www.freebuttons.com/freebuttons/GrabIt/GrabItDe0.gif"));
        cc3.setBackground(Color.YELLOW);
    }


}
