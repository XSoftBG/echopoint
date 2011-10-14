package echopoint;

import static org.junit.Assert.assertEquals;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Label;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import echopoint.jquery.TransitionContainer;

/**
 * Created: 2009-maj-09
 */
public class TransitionContainerTest extends AbstractTest<TransitionContainer> {
    @BeforeClass
    public static void init() {
        set(new TransitionContainer());
    }

    @Test
    public void renderId() {
        final String renderId = "echopointUnitTestTransitionContainer";
        getComponent().setRenderId(renderId);
        assertEquals("Ensuring renderId set", renderId, getComponent().getRenderId());
    }

    @AfterClass
    public static void finish() {
        final Component content = Application.getContent().getTestArea();
        content.removeAll();
        final TransitionContainer tc = (TransitionContainer) get();
        tc.setType(TransitionContainer.TYPE_FOLD);
        tc.setDuration(800);

        tc.add(new Label("This is just a test label."));
        ContainerEx cEx = new ContainerEx();
        cEx.setWidth(new Extent(400));
        cEx.setHeight(new Extent(300));
        cEx.setBackground(Color.GREEN);
        tc.add(cEx);

        content.add(tc);

        Button b = new Button("Change label");
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                int type = tc.getType();
                if (type == 12) type = 2;
                else type++;
                tc.setType(type);
                tc.removeAll();
                tc.add(new Label("This is another label"));
                ContainerEx cEx = new ContainerEx();
                cEx.setWidth(new Extent(500));
                cEx.setHeight(new Extent(400));
                cEx.setBackground(Color.BLUE);
                tc.add(cEx);
            }
        });
        content.add(b);


    }
}

