package echopoint;

import static org.junit.Assert.assertEquals;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Font;
import nextapp.echo.app.HttpImageReference;
import nextapp.echo.app.Label;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.jquery.DockMenu;

/**
 * Created: 2009-maj-09
 */
public class DockMenuTest extends AbstractTest<DockMenu> {
    @BeforeClass
    public static void init() {
        set(new DockMenu(40, DockMenu.LABELS_BOTTOM_CENTER, 500));
    }

    @Test
    public void renderId() {
        final String renderId = "echopointUnitTestDockMenu";
        getComponent().setRenderId(renderId);
        assertEquals("Ensuring renderId set", renderId, getComponent().getRenderId());
    }

    @AfterClass
    public static void finish() {
        final Component content = Application.getContent().getTestArea();
        content.removeAll();
        DockMenu dm = (DockMenu) get();
        dm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("EVENT");
            }
        });
        dm.setFont(new Font(Font.ARIAL, Font.PLAIN, new Extent(18)));
        dm.setForeground(Color.RED);

        dm.addMenuItem("Thunderbird", "Thunderbird", 64, 64, new HttpImageReference("http://www.mozilla.com/img/thunderbird-logo-64x64.png"), new HttpImageReference("http://www.mozilla.com/img/firefox-logo-64x64.png"));
        dm.addMenuItem("Firefox", "Firefox1", 64, 64, new HttpImageReference("http://www.mozilla.com/img/firefox-logo-64x64.png"),  null);
        dm.addMenuItem("Firefox", "Firefox2", 64, 64, new HttpImageReference("http://www.mozilla.com/img/firefox-logo-64x64.png"),  null);
        dm.addMenuItem("Firefox", "Firefox3", 64, 64, new HttpImageReference("http://www.mozilla.com/img/firefox-logo-64x64.png"), null);
               
        Row r = new Row();
        r.add(new Label("Left"));
        ContainerEx c = new ContainerEx();

        c.setWidth(new Extent(300));
        c.add(dm);
        r.add(c);

        r.add(new Label("Right"));

        //ColumnLayoutData cld = new ColumnLayoutData();
        //cld.setAlignment(Alignment.ALIGN_CENTER);
        //c2.setLayoutData(cld);
        content.add(new Strut(100, 100));
        content.add(r);

    }
}

