package echopoint.model;

import java.util.List;

/**
 * The AutoLookupModel provides for support for looking up
 * {@code AutoLookupModel.Entry}'s based on partial-string matching.
 *
 * @author Christoff Spinner 2009-12-07
 * @version $Id: AutoLookupModel.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public interface AutoLookupModel
{
  /**
   * <code>AutoLookupModel.Entry</code> represents the entries that can be
   * returned by the AutoLookupModel.
   */
  interface Entry
  {
    /** @return the value that is used */
    String getValue();
  }

  /**
   * A simple implementation of <code>AutoLookupModel.Entry</code> is
   * provided.
   */
  class DefaultEntry implements Entry
  {

    private String value;

    /**
     * Constructs a <code>AutoLookupModel.DefaultEntry</code> where all
     * three values (value, sortValue, xhtml) are the same
     *
     * @param value The value to use.
     */
    public DefaultEntry( final String value )
    {
      setValue( value );
    }

    /** @see Entry#getValue() */
    public String getValue()
    {
      return value;
    }

    /**
     * Sets the value
     *
     * @param value - the new value The value to set.
     */
    public void setValue( final String value )
    {
      this.value = value;
    }
  }

  /**
   * This is called to populate a cache of <code>AutoLookupModel.Entry</code>'s
   * on the client based on the partial search value
   *
   * @param searchValue - the search value to use
   * @return a NON-NULL array of <code>AutoLookupModel.Entry</code>'s. This
   *         may be zero length but may not be null.
   */
  List<Entry> searchEntries( String searchValue );
}
