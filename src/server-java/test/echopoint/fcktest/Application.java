/*
 * Copyright (C) 2009 Andre Schild (a.schild@aarboard.ch)
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or the
 * GNU Lesser General Public License Version 2.1 or later (the "LGPL"), in which
 * case the provisions of the GPL or the LGPL are applicable instead of those
 * above. If you wish to allow use of your version of this file only under the
 * terms of either the GPL or the LGPL, and not to allow others to use your
 * version of this file under the terms of the MPL, indicate your decision by
 * deleting the provisions above and replace them with the notice and other
 * provisions required by the GPL or the LGPL. If you do not delete the
 * provisions above, a recipient may use your version of this file under the
 * terms of any one of the MPL, the GPL or the LGPL.
 *
 * @author Andre Schild Aarboard 2009-06-17
 * @version $Id: Application.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */

package echopoint.fcktest;

import nextapp.echo.app.ApplicationInstance;
import nextapp.echo.app.Button;
import nextapp.echo.app.Column;
import nextapp.echo.app.ContentPane;
import nextapp.echo.app.Window;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.webcontainer.WebContainerServlet;

public class Application extends WebContainerServlet
{
    private String fckURL= "fckeditor/";
    
    private ATestWindow _testWindow= null;

	public ApplicationInstance newApplicationInstance()
        {
		return new ApplicationInstance() {
			public Window init() {
				setStyleSheet(new StyleSheet());
				Window window = new Window();
				window.setTitle("RichTextArea powered by FCKEditor "+echopoint.Fckeditor.Version);
				window.setContent(newContentPane());
				return window;
			}
		};
	}

	private ContentPane newContentPane() {
		final ContentPane contentPane = new ContentPane();
        Column  col= new Column();
		Button richTextDemo = new Button("New RichTextArea");
		richTextDemo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            if (_testWindow != null )
                            {
                                contentPane.remove(_testWindow);
                                _testWindow= null;
                            }
                            _testWindow= new RichTextAreaWindow(fckURL);
                            contentPane.add(_testWindow);
			}
		});
        col.add(richTextDemo);
		Button richTextDemo2 = new Button("New RichTextAreaCustom with DEBUG");
		richTextDemo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            if (_testWindow != null )
                            {
                                contentPane.remove(_testWindow);
                                _testWindow= null;
                            }
                            _testWindow= new RichTextAreaWindowCustom(fckURL);
                            contentPane.add(_testWindow);
			}
		});
        col.add(richTextDemo2);
		Button richTextDemo3 = new Button("New Double RichTextArea");
		richTextDemo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            if (_testWindow != null )
                            {
                                contentPane.remove(_testWindow);
                                _testWindow= null;
                            }
                            _testWindow= new DoubleRichTextAreaWindow(fckURL);
                            contentPane.add(_testWindow);
			}
		});
        col.add(richTextDemo3);
		Button richTextDemo4 = new Button("New 4 RichTextArea");
		richTextDemo4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            if (_testWindow != null )
                            {
                                contentPane.remove(_testWindow);
                                _testWindow= null;
                            }
                            _testWindow= new FourRichTextAreaWindow(fckURL);
                            contentPane.add(_testWindow);
			}
		});
        col.add(richTextDemo4);
		Button richTextDemo5 = new Button("New interactive RichTextArea");
		richTextDemo5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            if (_testWindow != null )
                            {
                                contentPane.remove(_testWindow);
                                _testWindow= null;
                            }
                            _testWindow= new InteractiveTextAreaWindow(fckURL);
                            contentPane.add(_testWindow);
			}
		});
        col.add(richTextDemo5);
		contentPane.add(col);
		return contentPane;
	}


    public Application()
    {
        super();

    }
}
