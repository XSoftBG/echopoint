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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.event.ActionListener;
import echopoint.event.TagEvent;
import echopoint.internal.AbstractContainer;
import echopoint.model.Tag;

/**
 * A <a href='http://en.wikipedia.org/wiki/Tag_cloud'>tag cloud</a> component
 * based on client-side code contribued by Tod.  Please note that the tag
 * model objects specified for this component should have a unique name
 * property.  This component supports the
 * following style properties in addition to those defined in {@link
 * nextapp.echo.app.Component}:
 *
 * <ul>
 *   <li><code>rolloverEnabled</code> - A flag used to indicate that the tag
 *     components should support change of style on mouse rollover.</li>
 *   <li><code>rolloverBackground</code> - The colour to use as the background
 *     of the tag element over which the mouse hovers.</li>
 *   <li><code>rolloverForeground</code> - The colour to use for the title
 *     of the tag element over which the mouse hovers.</li>
 * </ul>
 *
 * <p><b>Note:</b> There is a bug in how updates to the tags properties are
 * handled by the update manager at present.  For the time-being, please
 * remove and re-add the tag cloud to its parent if the tags have been
 * updated during an event cycle (see {@code TagCloudTest} for example).</p>
 *
 * <p>The following shows sample usage of this component:</p>
 * <pre>
 *   import echopoint.TagCloud;
 *   import echopoint.event.TagEvent;
 *   import echopoint.model.Tag;
 *   import nextapp.echo.app.Row;
 *
 *     ...
 *     final Collection&lt;Tag&gt; tags = new LinkedHashSet&lt;Tag&gt;();
 *     tags.add( new Tag( "Java", 50 ) );
 *     tags.add( new Tag( "Objective-C", 25 ) );
 *     tags.add( new Tag( "Groovy", 10 ) );
 *     tags.add( new Tag( "JavaScript", 15 ) );
 *
 *     final Row row = new Row();
 *     final TagCloud tc = new TagCloud();
 *     tc.setRolloverEnabled( true );
 *     tc.setRolloverBackground( new Color( 0xa1a1a1 ) );
 *     tc.setRolloverForeground( new Color( 0xc1c1c1 ) );
 *     tc.setTags( tags );
 *     row.add( tc );
 *
 *      tc.addActionListener( new ActionListener()
 *      {
 *        public void actionPerformed( final ActionEvent event )
 *        {
 *          final TagEvent te = (TagEvent) event;
 *          if ( te.getTag() != null )
 *          {
 *            final Component content = Application.getContent().getTestArea();
 *            content.add( new Label( "Clicked Tag: " + te.getTag().getName() ) );
 *          }
 *        }
 *      });
 * </pre>
 *
 * @author Rakesh 2008-07-20
 * @version $Id: TagCloud.java,v 1.1.1.1 2010-04-01 09:47:29 perxi Exp $
 */
public class TagCloud extends AbstractContainer
{
  private static final long serialVersionUID = 1l;

  /** A flag indicating whether rollover is enabled for a tag. */
  public static final String PROPERTY_ROLLOVER_ENABLED = "rolloverEnabled";

  /** The rollover background for the tag element. */
  public static final String PROPERTY_ROLLOVER_BACKGROUND = "rolloverBackground";

  /** The rollover foreground for the tag element. */
  public static final String PROPERTY_ROLLOVER_FOREGROUND = "rolloverForeground";

  /**
   * The collection of {@link echopoint.model.Tag} instances to be
   * represented in the component.
   */
  public static final String PROPERTY_TAGS = "tags";

  /**
   * <b>TagCloud</b> is <i>NOT</i> allowed to have any children.
   *
   * @see nextapp.echo.app.Component#isValidChild(nextapp.echo.app.Component)
   */
  @Override
  public boolean isValidChild( final Component child )
  {
    return false;
  }

  /**
   * Return the value of the {@link #PROPERTY_ROLLOVER_ENABLED} property.
   *
   * @return The flag indicating whether rollover behaviour is enabled or not.
   */
  public boolean getRolloverEnabled()
  {
    final Boolean enabled =  (Boolean) get( PROPERTY_ROLLOVER_ENABLED );
    return ( enabled != null ) ? enabled : false;
  }

  /**
   * Set the value of the {@link #PROPERTY_ROLLOVER_ENABLED} property.
   *
   * @param enabled The flag indicating whether rollover behaviour is to be
   *   enabled or not.
   */
  public void setRolloverEnabled( final boolean enabled )
  {
    set( PROPERTY_ROLLOVER_ENABLED, enabled );
  }

  /**
   * Return the value of the {@link #PROPERTY_ROLLOVER_BACKGROUND} property.
   *
   * @return The colour used as the background while rolling over the tag.
   */
  public Color getRolloverBackground()
  {
    return (Color) get( PROPERTY_ROLLOVER_BACKGROUND );
  }

  /**
   * Set the value of the {@link #PROPERTY_ROLLOVER_BACKGROUND} property.
   *
   * @param background The colour used as the background while rolling over.
   */
  public void setRolloverBackground( final Color background )
  {
    set( PROPERTY_ROLLOVER_BACKGROUND, background );
  }

  /**
   * Return the value of the {@link #PROPERTY_ROLLOVER_BACKGROUND} property.
   *
   * @return The colour used as the foreground while rolling over the tag.
   */
  public Color getRolloverForeground()
  {
    return (Color) get( PROPERTY_ROLLOVER_FOREGROUND );
  }

  /**
   * Set the value of the {@link #PROPERTY_ROLLOVER_BACKGROUND} property.
   *
   * @param foreground The colour used as the foreground while rolling over.
   */
  public void setRolloverForeground( final Color foreground )
  {
    set( PROPERTY_ROLLOVER_FOREGROUND, foreground );
  }

  /**
   * Return the value of the {@link #PROPERTY_TAGS} property.
   *
   * @return A read-only view of the collection of tags instances.
   */
  @SuppressWarnings( {"unchecked"} )
  public Collection<Tag> getTags()
  {
    final Map<String,Tag> map = (Map<String,Tag>) get( PROPERTY_TAGS );
    return Collections.unmodifiableCollection( map.values() );
  }

  /**
   * Return the collection of tags that represent the model for this component.
   *
   * @return The collection of tag instances.
   */
  @SuppressWarnings( {"unchecked"} )
  protected Collection<Tag> getData()
  {
    final Map<String,Tag> map = (Map<String,Tag>) get( PROPERTY_TAGS );
    return new ArrayList<Tag>( map.values() );
  }

  /**
   * Return the tag identified by its name.
   *
   * @param name The unique name for the tag.
   * @return The tag model object or {@code null} if no such tag exists.
   */
  @SuppressWarnings( {"unchecked"} )
  public Tag getTag( final String name )
  {
    final Map<String,Tag> map = (Map<String,Tag>) get( PROPERTY_TAGS );
    return map.get( name );
  }

  /**
   * Set the value of the {@link #PROPERTY_TAGS} property.  Note that there
   * is not way to individually add/delete tag instances other than setting
   * a new collection (or a modified version of the original collection you
   * used to set the property).
   *
   * @param tags The collection of tag instances to be represented in this
   *   component.  Please note that a {@link java.util.Set} implementation
   *   is preferred since this implementation requires unique tag names.
   */
  public void setTags( final Collection<Tag> tags )
  {
    final Map<String,Tag> map = new LinkedHashMap<String,Tag>();

    for ( Tag tag : tags )
    {
      map.put( tag.getName(), tag );
    }

    set( PROPERTY_TAGS, map );
  }

  /** {@inheritDoc} */
  @Override
  public void addActionListener( final ActionListener listener )
  {
    super.addActionListener( listener );
  }

  /** {@inheritDoc} */
  public void removeActionListener( final ActionListener listener )
  {
    super.removeActionListener( listener );
  }

  /** {@inheritDoc} */
  @Override
  public boolean hasActionListeners()
  {
    return super.hasActionListeners();
  }

  /** {@inheritDoc} */
  @Override
  public void processInput( String name, Object value )
  {
    super.processInput( name, value );
    if ( INPUT_ACTION.equals( name ) )
    {
      final Tag tag = getTag( (String) value );
      fireActionPerformed( new TagEvent( this, tag.getName(), tag ) );
    }
  }
}
