package echopoint.jquery;

import nextapp.echo.app.Component;
import nextapp.echo.app.PaneContainer;

/**
 * A component that renders an animated transition effect when its content is changed.
 *
 * This component is using the jQuery (http://www.jquery.com) and jQuery UI (http://www.jqueryui.com) projects.
 *
 * @author Mikael S\u00f6derman 2009-06-03
 * @version $Id: TransitionContainer.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class TransitionContainer extends Component implements PaneContainer {

    public static final int TYPE_IMMEDIATE_REPLACE = 0;
    public static final int TYPE_BLIND = 1;
    public static final int TYPE_BOUNCE = 2;
    public static final int TYPE_CLIP = 3;
    public static final int TYPE_DROP = 4;
    public static final int TYPE_EXPLODE = 5;
    public static final int TYPE_FOLD = 6;
    public static final int TYPE_HIGHLIGHT = 7;
    public static final int TYPE_PUFF = 8;
    public static final int TYPE_PULSATE = 9;
    public static final int TYPE_SCALE = 10;
    public static final int TYPE_SHAKE = 11;
    public static final int TYPE_SIZE = 12;
    public static final int TYPE_SLIDE = 13;

    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_DURATION = "duration";

    public static final int DEFAULT_DURATION = 550;

    public int getDuration() {
           Integer duration = (Integer) get(PROPERTY_DURATION);
           return duration == null ? DEFAULT_DURATION : duration.intValue();
       }

    public void setDuration(int newValue) {
        set(PROPERTY_DURATION, new Integer(newValue));
    }

      public int getType() {
        Integer type = (Integer) get(PROPERTY_TYPE);
        return type == null ? TYPE_IMMEDIATE_REPLACE : type.intValue();
    }

     public void setType(int newValue) {
        set(PROPERTY_TYPE, new Integer(newValue));
    }

}
