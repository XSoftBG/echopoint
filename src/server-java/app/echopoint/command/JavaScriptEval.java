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
package echopoint.command;

import nextapp.echo.app.Command;

/**
 * <code>JavaScriptEval</code> can be used to <code>eval</code> some arbitary
 * JavaScript on the client.
 * <p>
 * The javascript must be in a form ready for the eval() function.
 * @author Brad Baker <p>Modified by Mikael Soderman 2009-04-28</p>
 * @version $Id: JavaScriptEval.java,v 1.1.1.1 2010-04-01 09:47:30 perxi Exp $

 */
public class JavaScriptEval implements Command {
    private String javaScript;

	/**
	 * Constructs a <code>JavaScriptEval</code>
	 *
	 * @param javaScript - the JavaScript text to <code>eval</code>
	 */
    public JavaScriptEval(String javaScript) {
        this.javaScript = javaScript;
    }


	/**
	 * @return Returns the javaScript.
	 */
    public String getJavaScript() {
        return javaScript;
    }


	/**
	 * @param javaScript - The javaScript to set.
	 */
    public void setJavaScript(String javaScript) {
        this.javaScript = javaScript;
    }
}
