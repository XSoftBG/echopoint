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
 * @version $Id: StyleSheet.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $
 */
package echopoint.fcktest;


import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.CheckBox;
import nextapp.echo.app.Color;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImageBorder;
import nextapp.echo.app.Font;
import nextapp.echo.app.Insets;
import nextapp.echo.app.MutableStyle;
import nextapp.echo.app.MutableStyleSheet;
import nextapp.echo.app.TextArea;
import nextapp.echo.app.WindowPane;
import nextapp.echo.app.button.AbstractButton;

public class StyleSheet extends MutableStyleSheet {

	private static final MutableStyle BUTTON = new MutableStyle();
	private static final MutableStyle CHECKBOX = new MutableStyle();
	private static final MutableStyle TEXTAREA = new MutableStyle();
	private static final MutableStyle WINDOWPANE = new MutableStyle();

	static {
		BUTTON.set(AbstractButton.PROPERTY_WIDTH, new Extent(180));
		BUTTON.set(AbstractButton.PROPERTY_INSETS, new Insets(4, 2, 4, 2));
		BUTTON.set(AbstractButton.PROPERTY_ALIGNMENT, new Alignment(
				Alignment.CENTER, Alignment.CENTER));
		BUTTON.set(AbstractButton.PROPERTY_BACKGROUND, new Color(0xe0e0e0));
		BUTTON.set(AbstractButton.PROPERTY_BORDER, new Border(1, new Color(
				0xbfbfbf), Border.STYLE_OUTSET));
		BUTTON.set(AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.TRUE);
		BUTTON.set(AbstractButton.PROPERTY_ROLLOVER_BORDER, new Border(1,
				Color.BLACK, Border.STYLE_OUTSET));
		BUTTON.set(AbstractButton.PROPERTY_PRESSED_ENABLED, Boolean.TRUE);
		BUTTON.set(AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1,
				new Color(0x7f7f7f), Border.STYLE_INSET));

		CHECKBOX.set(CheckBox.PROPERTY_ROLLOVER_ENABLED, Boolean.TRUE);
		CHECKBOX
				.set(CheckBox.PROPERTY_ROLLOVER_FOREGROUND, new Color(0x8899aa));

		TEXTAREA.set(TextArea.PROPERTY_BACKGROUND, new Color(0xe0e0e0));

		WINDOWPANE.set(WindowPane.PROPERTY_DEFAULT_CLOSE_OPERATION,
				new Integer(WindowPane.DISPOSE_ON_CLOSE));
		WINDOWPANE.set(WindowPane.PROPERTY_TITLE_FONT, new Font(null,
				Font.BOLD, null));
		WINDOWPANE.set(WindowPane.PROPERTY_BORDER, new FillImageBorder(
				new Color(0x606060), new Insets(1, 1, 1, 1), new Insets(1, 1,
						1, 1)));
		WINDOWPANE.set(WindowPane.PROPERTY_TITLE_INSETS, new Insets(5, 5));
		WINDOWPANE.set(WindowPane.PROPERTY_TITLE_BACKGROUND,
				new Color(0x8899aa));
		WINDOWPANE.set(WindowPane.PROPERTY_TITLE_FOREGROUND, Color.WHITE);
	}

	public StyleSheet() {
		addStyle(Button.class, "", BUTTON);
		addStyle(CheckBox.class, "", CHECKBOX);
		addStyle(TextArea.class, "", TEXTAREA);
		addStyle(WindowPane.class, "", WINDOWPANE);
	}

}
