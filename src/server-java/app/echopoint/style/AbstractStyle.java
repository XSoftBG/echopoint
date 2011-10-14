package echopoint.style;

import static echopoint.util.ColorKit.makeColor;
import static nextapp.echo.app.Component.PROPERTY_FONT;
import static nextapp.echo.app.Component.PROPERTY_FOREGROUND;

import java.util.logging.Level;
import java.util.logging.Logger;

import nextapp.echo.app.MutableStyle;

/**
 * Abstract base class from which all style classes are derived.  Primarily
 * delegates to the {@link #init} method for configuration.
 *
 * @author Rakesh 2009-05-12
 * @version $Id: AbstractStyle.java,v 1.2 2011-05-28 13:21:58 perxi Exp $
 */
public abstract class AbstractStyle extends MutableStyle
{
  /** The logger to use to log messages. */
  protected static final Logger logger = Logger.getAnonymousLogger();

  /** The log level to use to log missing style messages. */
  protected final Level level = Level.FINE;

  /** Default constructor.  Over-ridden to invoke {@link #init}. */
  public AbstractStyle()
  {
    init();
  }

  /**
   * Mandatory style initialisation method to be implemented by all
   * sub-classes.  The default implementation sets the default font to use.
   * Sub-classes should generally invoke {@code super.init()}.
   *
   * @see echopoint.style.DefaultFont
   * @see #setFont
   */
  protected void init()
  {
    setFont();
    setForeground();
  }

  /** Set the default font to be used for all components. */
  protected void setFont()
  {
    set( PROPERTY_FONT, DefaultFont.getInstance() );
  }

  /** Set the default foreground colour for all components. */
  protected void setForeground()
  {
    set( PROPERTY_FOREGROUND, makeColor( "#000000" ) );
  }

  /**
   * Over-ridden to log missing property requests.
   *
   * @param name The name of the property to use to retrieve style.
   * @return Object The appropriate instance of Style.
   */
  @Override
  public Object get( final String name )
  {
    final Object object = super.get( name );

    if ( object == null )
    {
      if ( logger.isLoggable( level ) )
      {
        logger.log( level, "No style class defined with name " + name );
      }
    }

    return object;
  }
}
