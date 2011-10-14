package echopoint.layout;

import nextapp.echo.app.serial.SerialContext;
import nextapp.echo.app.serial.SerialException;
import nextapp.echo.app.serial.SerialUtil;
import nextapp.echo.app.serial.property.LayoutDataPeer;
import nextapp.echo.app.util.Context;

import org.w3c.dom.Element;

/**
 * <p>&copy; Copyright 2009 <a href='http://sptci.com/' target='_top'>Sans
 * Pareil Technologies, Inc.</a></p>
 *
 * @author Simon Lei 2009-03-16
 * @version $Id: HtmlLayoutDataPeer.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class HtmlLayoutDataPeer extends LayoutDataPeer
{
  /** The name of the {@link echopoint.layout.HtmlLayoutData#containerId}
   * property.
   */
  private static final String PROPERTY_NAME = "containerId";

  /** {@inheritDoc} */
  @Override
  public void toXml( final Context context, final Class objectClass,
      final Element propertyElement, final Object propertyValue )
      throws SerialException
  {
    final SerialContext serialContext = (SerialContext)
        context.get( SerialContext.class );
    final HtmlLayoutData layoutData = (HtmlLayoutData) propertyValue;

    propertyElement.setAttribute( "t", ( serialContext.getFlags() &
        SerialContext.FLAG_RENDER_SHORT_NAMES ) == 0 ? "LayoutData" : "L" );

    SerialUtil.toXml( context, HtmlLayoutData.class, propertyElement,
        PROPERTY_NAME, layoutData.getContainerId() );
  }
}
