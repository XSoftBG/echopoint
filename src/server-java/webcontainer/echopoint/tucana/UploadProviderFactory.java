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
package echopoint.tucana;

/**
 * Factory for {@link UploadSPI} implementations.
 * <p/>
 * <p>Custom {@link UploadSPI} implementations may be configured by calling
 * {@link #setUploadProvider(UploadSPI)} from within an application entry
 * point.
 *
 * @author Echo File Transfer Library
 * @version $Id: UploadProviderFactory.java,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */
public class UploadProviderFactory
{
  private static UploadSPI instance;

  /**
   * Gets the singleton <code>UploadSPI</code>. If it has not been set, a
   * default implementation is created.
   *
   * @return the singleton provider.
   */
  public synchronized static UploadSPI getUploadProvider()
  {
    if ( instance == null )
    {
      instance = new JakartaCommonsFileUploadProvider();
    }
    return instance;
  }

  /**
   * Sets the upload provider to be used.
   *
   * @param uploadProvider the upload provider
   * @throws IllegalStateException if the upload provider has already been
   * set.
   */
  public synchronized static void setUploadProvider( UploadSPI uploadProvider )
  {
    if ( instance != null )
    {
      throw new IllegalStateException( "UploadSPI already configured" );
    }
    instance = uploadProvider;
  }
}
