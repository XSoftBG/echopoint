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

package echopoint.style;

import echopoint.Anchor;
import echopoint.InfoWindow;
import echopoint.ProgressBar;
import echopoint.TagCloud;
import echopoint.style.google.chart.ChartStyleSheet;

/**
 * An extensible stylesheet that enforces a default look-and-feel for all
 * EchoPoint components.  Can be used as the starting point for applications
 * built using the framework.  We hope this promotes an object-oriented
 * management of styles for applications.
 *
 * <p>It is recommended that you extend from {@link echopoint.Servlet} (in
 * the webcontainer area) since the base servlet takes care of initialising
 * default style properties from init parameters.</p>
 *
 * <p>This class is based upon the
 * <a href='http://sptci.com/docs/public/com/sptci/echo/style/StyleSheet.html'>StyleSheet</a>
 * implementation that <a href='http://sptci.com/'>SPT</a> has used as the
 * basis for building a number of Echo2/3 applications.</p>
 *
 * @author Rakesh Vidyadharan 2009-05-12
 * @version $Id: StyleSheet.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
public class StyleSheet extends ChartStyleSheet
{
  private static final long serialVersionUID = 1L;

  /**
   * Initialises the default styles for the various components.  Sub-classes
   * should over-ride this method (while still invoking {@code super.init()})
   * to add additional styles.  Alternatively, sub-classes may over-ride the
   * various component style setting methods as appropriate.
   */
  @Override
  protected void init()
  {
    super.init();

    addAnchorStyles();
    addInfoWindowStyles();
    addProgressBarStyles();
    addTagCloudStyles();
  }

  /** Add styles for {@link echopoint.Anchor} components. */
  protected void addAnchorStyles()
  {
    final AnchorStyle style = new AnchorStyle();
    addStyle( Anchor.class, "", style );
    addStyle( Anchor.class, "Default", style );
  }

  /** Set the styles for {@link echopoint.InfoWindow} components. */
  protected void addInfoWindowStyles()
  {
    final InfoWindowStyle style = new InfoWindowStyle();
    addStyle( InfoWindow.class, "", style );
    addStyle( InfoWindow.class, "Default", style );
  }

  /** Set the styles for {@link echopoint.ProgressBar} components. */
  protected void addProgressBarStyles()
  {
    final ProgressBarStyle style = new ProgressBarStyle();
    addStyle( ProgressBar.class, "", style );
    addStyle( ProgressBar.class, "Default", style );
  }

  /** Add styles for {@link echopoint.TagCloud} components. */
  protected void addTagCloudStyles()
  {
    final TagCloudStyle style = new TagCloudStyle();
    addStyle( TagCloud.class, "", style );
    addStyle( TagCloud.class, "Default", style );
  }
}
