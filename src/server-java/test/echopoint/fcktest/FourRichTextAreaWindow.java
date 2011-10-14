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
 * @version $Id: FourRichTextAreaWindow.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
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

public class FourRichTextAreaWindow extends ATestWindow {

	Fckeditor rtaArea1 = new Fckeditor();
	Fckeditor rtaArea2 = new Fckeditor();
	Fckeditor rtaArea3 = new Fckeditor();
	Fckeditor rtaArea4 = new Fckeditor();
	TextArea textArea = new TextArea();

	public FourRichTextAreaWindow(String fckURL) {
		super("RichTextArea 4 editors (powered by FCKEditor) "+Fckeditor.Version, new Extent(675),
				new Extent(675));

                rtaArea1.setFckeditorURL(fckURL);
		rtaArea1.setWidth(new Extent(330, Extent.PX));
		rtaArea1.setHeight(new Extent(200, Extent.PX));
                rtaArea1.setText("Start text content editor 1");

		textArea.setWidth(new Extent(100, Extent.PERCENT));
		textArea.setHeight(new Extent(100));

                rtaArea2.setFckeditorURL(fckURL);
		rtaArea2.setWidth(new Extent(330, Extent.PX));
		rtaArea2.setHeight(new Extent(200, Extent.PX));
                rtaArea2.setText("Start text content editor 2");

                rtaArea3.setFckeditorURL(fckURL);
		rtaArea3.setWidth(new Extent(330, Extent.PX));
		rtaArea3.setHeight(new Extent(200, Extent.PX));
                rtaArea3.setText("Start text content editor 3, Editor is DISABLED");
                rtaArea3.setToolbar("Basic");
                rtaArea3.setEnabled(false);
                rtaArea3.setDebug(true);

                rtaArea4.setFckeditorURL(fckURL);
		rtaArea4.setWidth(new Extent(330, Extent.PX));
		rtaArea4.setHeight(new Extent(200, Extent.PX));
                rtaArea4.setText("Start text content editor 4, Toolbar in AutoCollapse mode");
                rtaArea4.setToolbar("Basic");
                rtaArea4.setToolbarAutocollapse(true);

		Button rta1text = new Button("Copy from editor 1 to PlainTextArea");
		rta1text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            String content= rtaArea1.getText();
				textArea.setText(content);
			}
		});

		Button rta2text = new Button("Copy from editor 2 to PlainTextArea");
		rta2text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            String content= rtaArea2.getText();
				textArea.setText(content);
			}
		});

		Button rta3text = new Button("Copy from editor 3 to PlainTextArea");
		rta3text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            String content= rtaArea3.getText();
				textArea.setText(content);
			}
		});

		Button rta4text = new Button("Copy from editor 4 to PlainTextArea");
		rta4text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                            String content= rtaArea4.getText();
				textArea.setText(content);
			}
		});

		Button text1rta = new Button("Copy to RichTextArea 1");
		text1rta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rtaArea1.setText(textArea.getText());
			}
		});

		Button text2rta = new Button("Copy to RichTextArea 2");
		text2rta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rtaArea2.setText(textArea.getText());
			}
		});
		Button text3rta = new Button("Copy to RichTextArea 3");
		text3rta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rtaArea3.setText(textArea.getText());
			}
		});
		Button text4rta = new Button("Copy to RichTextArea 4");
		text4rta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rtaArea4.setText(textArea.getText());
			}
		});

		Row row = new Row();
		row.setAlignment(Alignment.ALIGN_CENTER);
		row.setCellSpacing(new Extent(5));
		row.add(text1rta);
		row.add(text2rta);
		row.add(text3rta);
		row.add(text4rta);

		Row row2 = new Row();
		row2.setAlignment(Alignment.ALIGN_CENTER);
		row2.setCellSpacing(new Extent(5));
                row2.add(rta1text);
		row2.add(rta2text);
                row2.add(rta3text);
		row2.add(rta4text);

		Column column = new Column();
		column.setCellSpacing(new Extent(5));
		Row rowE1 = new Row();
		rowE1.add(rtaArea1);
		rowE1.add(rtaArea2);
                column.add(rowE1);
		Row rowE2 = new Row();
		rowE2.add(rtaArea3);
		rowE2.add(rtaArea4);
                column.add(rowE2);
		column.add(row);
		column.add(row2);
		column.add(textArea);

		add(column);
	}
}
