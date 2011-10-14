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
 * @version $Id: DoubleRichTextAreaWindow.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
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

public class DoubleRichTextAreaWindow extends ATestWindow {

	Fckeditor rtaArea1 = new Fckeditor();
	Fckeditor rtaArea2 = new Fckeditor();
	TextArea textArea = new TextArea();

	public DoubleRichTextAreaWindow(String fckURL) {
		super("RichTextArea two editors (powered by FCKEditor) "+Fckeditor.Version, new Extent(675),
				new Extent(675));

                rtaArea1.setFckeditorURL(fckURL);
		rtaArea1.setWidth(new Extent(100, Extent.PERCENT));
		rtaArea1.setHeight(new Extent(200, Extent.PX));
                rtaArea1.setText("Start text content editor 1");

		textArea.setWidth(new Extent(100, Extent.PERCENT));
		textArea.setHeight(new Extent(100));

                rtaArea2.setFckeditorURL(fckURL);
		rtaArea2.setWidth(new Extent(100, Extent.PERCENT));
		rtaArea2.setHeight(new Extent(200, Extent.PX));
                rtaArea2.setText("Start text content editor 2, Toolbar should be collapsed");
                rtaArea2.setToolbarCollapsed(true);

		Button rta2text = new Button("Copy from editor 1 to PlainTextArea");
		rta2text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            String content= rtaArea1.getText();
				textArea.setText(content);
			}
		});

		Button rta3text = new Button("Copy from editor 2 to PlainTextArea");
		rta3text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            String content= rtaArea2.getText();
				textArea.setText(content);
			}
		});

		Button text2rta = new Button("Copy to RichTextArea 1");
		text2rta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rtaArea1.setText(textArea.getText());
			}
		});

		Button text3rta = new Button("Copy to RichTextArea 2");
		text3rta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rtaArea2.setText(textArea.getText());
			}
		});

		Row row = new Row();
		row.setAlignment(Alignment.ALIGN_CENTER);
		row.setCellSpacing(new Extent(30));
		row.add(text2rta);
		row.add(text3rta);

		Row row2 = new Row();
		row2.setAlignment(Alignment.ALIGN_CENTER);
		row2.setCellSpacing(new Extent(30));
                row2.add(rta2text);
		row2.add(rta3text);

		Column column = new Column();
		column.setCellSpacing(new Extent(5));
		column.add(rtaArea1);
		column.add(rtaArea2);
		column.add(row);
		column.add(row2);
		column.add(textArea);

		add(column);
	}
}
