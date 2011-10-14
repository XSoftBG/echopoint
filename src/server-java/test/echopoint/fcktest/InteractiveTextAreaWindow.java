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
 * @version $Id: InteractiveTextAreaWindow.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
package echopoint.fcktest;


import nextapp.echo.app.Alignment;
import nextapp.echo.app.Button;
import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Row;
import nextapp.echo.app.TextArea;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import echopoint.Fckeditor;

public class InteractiveTextAreaWindow extends ATestWindow {

	Fckeditor rtaArea = new Fckeditor();
	TextArea textArea = new TextArea();
        Row rowTE= new Row();

	public InteractiveTextAreaWindow(String fckURL)
        {
		super("RichTextArea (powered by FCKEditor) "+Fckeditor.Version, new Extent(675),
				new Extent(675));

                rtaArea.setFckeditorURL(fckURL);
		rtaArea.setWidth(new Extent(100, Extent.PERCENT));
		rtaArea.setHeight(new Extent(400, Extent.PX));
                rtaArea.setText("Start text content");
                rtaArea.setDebug(true);

		textArea.setWidth(new Extent(100, Extent.PERCENT));
		textArea.setHeight(new Extent(200));
        

		Button rta2text = new Button("Copy to PlainTextArea");
		rta2text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            String content= rtaArea.getText();
				textArea.setText(content);
			}
		});

		Button text2rta = new Button("Copy to RichTextArea");
		text2rta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rtaArea.setText(textArea.getText());
			}
		});

		Button text3rta = new Button("Toggle Row container visible");
		text3rta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            rowTE.setVisible(!rowTE.isVisible());
			}
		});

		Button text4rta = new Button("Toggle editor ENABLED");
		text4rta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            rowTE.setEnabled(!rowTE.isEnabled());
			}
		});

		Row row = new Row();
		row.setAlignment(Alignment.ALIGN_CENTER);
		row.setCellSpacing(new Extent(30));
		row.add(text2rta);
		row.add(rta2text);
		Row row2 = new Row();
		row2.setAlignment(Alignment.ALIGN_CENTER);
		row2.setCellSpacing(new Extent(30));
		row2.add(text3rta);
		row2.add(text4rta);

                rowTE.add(rtaArea);
                rowTE.setVisible(true);
                
		Column column = new Column();
		column.setCellSpacing(new Extent(5));
		column.add(rowTE);
		column.add(row);
		column.add(row2);
		column.add(textArea);

		add(column);
	}
}
