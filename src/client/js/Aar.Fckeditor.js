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
 * @version $Id: Aar.Fckeditor.js,v 1.3 2011-05-28 13:21:03 perxi Exp $
 */

if (!Core.get(window, ["Aar", "Sync"])) {
	Core.set(window, ["Aar", "Sync"], {});
}

Aar.Fckeditor = Core.extend(Echo.Component, {

	$load : function() {
		Echo.ComponentFactory.registerType("Aar.Fckeditor", this);
	},

	componentType : "Aar.Fckeditor"
});

Aar.Fckeditor.Sync = Core.extend(Echo.Render.ComponentSync, {

	$load : function() {
		Echo.Render.registerPeer("Aar.Fckeditor", this);
        },

        /**
         * Asynchronously retrieves the library.
         * This method will invoke the _jsLoaded listener when the library has been completed,
         * it will return before the library has been retrieved.
         */
        _loadFCKScript : function(url)
        {
            if (this._debug)
            {
                Core.Debug.consoleWrite("_LoadFCKScript from <"+url+">");
            }
            this._conn = new Core.Web.HttpConnection(url, "GET");
            this._conn.addResponseListener(Core.method(this, this._jsLoaded));
            this._conn.connect();
        },

        /**
         *
         * Javascript library has been loaded, now execute the script and the
         * create the instances
         *
         */
        _jsLoaded : function(){
                if (this._debug)
                {
                    Core.Debug.consoleWrite("jsLoaded callback connection status "+this._conn.getStatus());
                }
                if (this._conn.getStatus() != 200)
                {
                    // We should not use the private _rul member of the
                    // connection object, but....
                    alert("Failed loading fckeditor.js with http status <"+this._conn.getStatus()
                        +">\nURL to fckeditor wrong, or not set?\n"+
                        this._conn._url
                    );
                }
                this._jscontent = this._conn.getResponseText();
                eval(this._jscontent);  // Execute script
                Aar.Fckeditor.jsLoaded= true;
                Aar.Fckeditor.jsLoading= false;

                if (this._debug)
                {
                    Core.Debug.consoleWrite("jsLoaded and evaluated FCKeditor is: "+FCKeditor);
                }
                Aar.Fckeditor.fckNewFunction= FCKeditor;
                if (typeof(Aar.Fckeditor.queuedCreations) != "undefined")
                {
                    if (this._debug)
                    {
                        Core.Debug.consoleWrite("jsLoaded and evaluated, now creating instances");
                    }
                    for (queuedInstance in Aar.Fckeditor.queuedCreations)
                    {
                        Aar.Fckeditor.queuedCreations[queuedInstance]._createInstance();
                    }
                }

                function FCKeditor_OnComplete(editorInstance)
                {
                    Core.Debug.consoleWrite("fckeditor inline OnComplete received for "+editorInstance.name);
                }

        },

        _createInstance: function()
        {
                if (this._debug)
                {
                    Core.Debug.consoleWrite("creating instance "+this._editorID);
                    //Core.Debug.consoleWrite("FCKeditor is: "+FCKeditor);
                    Core.Debug.consoleWrite("fckEditorVariable is: "+Aar.Fckeditor.fckNewFunction);
                }
                Aar.Fckeditor.fckNewFunction.BasePath = this._fckBaseURL ;
                this._fck = new Aar.Fckeditor.fckNewFunction(this._editorID);
                if (typeof(this._fckConfigURL) != "undefined")
                {
                    this._fck.Config["CustomConfigurationsPath"] = this._fckConfigURL  ;
                }
                // Set CSS if specified
                if (typeof(this._fckCssURL) != "undefined")
                {
                    this._fck.Config["EditorAreaCSS"] = this._fckCssURL  ;
                }
                // Set toolbar if specified
                if (typeof(this._toolbar) != "undefined")
                {
                    this._fck.ToolbarSet = this._toolbar  ;
                }
                // Set toolbar state
                this._toolbarCollapsed= this.component.render("toolbarCollapsed");
                this._toolbarAutocollapse= this.component.render("toolbarAutocollapse");
                if (! this.component.isRenderEnabled())
                {
                    this._fck.Config["ToolbarStartExpanded"]= false;
                }
                else
                {
                    if (this._toolbarAutocollapse != undefined && this._toolbarAutocollapse)
                    {
                        this._fck.Config["ToolbarStartExpanded"]= false;
                    }
                    else
                    {
                        if (this._toolbarCollapsed != undefined)
                        {
                            this._fck.Config["ToolbarStartExpanded"]= !this._toolbarCollapsed;
                        }
                    }
                }

                // Set all properties set from server side
                if (typeof(this._config) != "undefined")
                {
                    for (property in this._config)
                    {
                        this._fck.Config[property]= this._config[property];
                    }
                }
                this._fck.Width= "100%"; // Fill the enclosing div which is already correctly sized
                this._fck.Height= "100%";// Fill the enclosing div which is already correctly sized
                this._div.innerHTML = this._fck.CreateHtml();
                if (this._debug)
                {
                    Core.Debug.consoleWrite("renderAdd jsLoaded createHtml done for: "+this._editorID);
                }
                // Normally we should just be able to wait for the FCKeditor_OnComplete
                // Event, but for some unknown reason the one included in this
                // js file is not called.
                // So we poll for the init to be done
                //
                this.pendingInit = Core.Web.Scheduler.run(Core.method(this,
                                                        this.waitForInit), 500, true);
        },

	renderAdd : function(update, parentElement) {
                // Turning debugging on results in behaviour changes due to other script timings
                this._debug= this.component.render("debug");
                if (this._debug == undefined)
                {
                    this._debug= false;
                }

		this._div = document.createElement("div");
		this._div.id = this.component.renderId;
                this._editorID= this._div.id+"_FCK";

                this._text= this.component.render("text");
                this._fckBaseURL= this.component.render("fckeditorURL");
                this._fckCssURL= this.component.render("fckeditorCssURL");
                this._fckConfigURL= this.component.render("fckeditorConfigURL");
                this._config= this.component.render("config");
                this._toolbar= this.component.render("toolbar");
                this._toolbarCollapsed= this.component.render("toolbarCollapsed");
                this._toolbarAutocollapse= this.component.render("toolbarAutocollapse");
                if (this._debug)
                {
                    Core.Debug.consoleWrite("JS Base is "+this._fckBaseURL+" "+this._editorID);
                    Core.Debug.consoleWrite("JS Config is "+this._fckConfigURL+" "+this._editorID);
                    Core.Debug.consoleWrite("Config is "+this._config+" "+this._editorID);
                    Core.Debug.consoleWrite("isEnabled "+this.component.isEnabled()+" "+this._editorID);
                    Core.Debug.consoleWrite("isRenderEnabled "+this.component.isRenderEnabled()+" "+this._editorID);

                    if (typeof("this._config") != "undefined")
                    {
                        for (property in this._config)
                        {
                            Core.Debug.consoleWrite("Config property "+property+" is "+this._config[property]+" "+this._editorID);
                        }
                    }
                }

                var width = this.component.render("width");
                this._div.style.width = Echo.Sync.Extent.toCssValue(width, true);
                var height = this.component.render("height");
                this._div.style.height = Echo.Sync.Extent.toCssValue(height, false);
                if (this._debug)
                {
                    Core.Debug.consoleWrite("Creating fckeditor div done, attaching to dom tree "+this._editorID);
                }
		parentElement.appendChild(this._div);

                if (Aar.Fckeditor.jsLoaded || Aar.Fckeditor.jsLoading)
                {
                    // JS loading done or in progress in another instance
                    if (Aar.Fckeditor.jsLoaded)
                    {
                        // JS loading done in another instance
                        this._createInstance();
                    }
                    else
                    {
                        // JS loading in progress, so queue editor creation
                        if (typeof(Aar.Fckeditor.queuedCreations) == "undefined")
                        {
                            Core.Web.debug("queuedCreations undefined, should not happen");
                            Aar.Fckeditor.queuedCreations= new Object();
                        }
                        Aar.Fckeditor.queuedCreations[this._editorID]= this;
                    }
                }
                else
                {
                    Aar.Fckeditor.jsLoading= true;
                    if (typeof(Aar.Fckeditor.queuedCreations) == "undefined")
                    {
                        Aar.Fckeditor.queuedCreations= new Object();
                    }
                    Aar.Fckeditor.queuedCreations[this._editorID]= this;
                    this._loadFCKScript(this._fckBaseURL+"fckeditor.js");
                }
	},

	renderDispose : function(update) {
                if (this._debug)
                {
                    Core.Debug.consoleWrite("fckeditor renderDispose "+this._editorID);
                }
		this.cancelPendingSync();
		delete this._div;
	},

	scheduleSync : function() {
                if (this._debug)
                {
                    Core.Debug.consoleWrite("fckeditor sheduleSync called "+this._editorID);
                }
		// Cancel pending sync before a new sync is scheduled,
		// so only the last sync in a row is executed
		// while someone is typing rapidly.
		this.cancelPendingSync();
		this.pendingSync = Core.Web.Scheduler.run(Core.method(this,
						this.executeSync), 500, true);
	},

	waitForInit : function() {
                var waitAgain= false;
		// Cancel pending sync before a new sync is scheduled,
                if (this._debug)
                {
                    Core.Debug.consoleWrite("waitForInit called "+this._editorID);
                }
                if (typeof(FCKeditorAPI) != "undefined")
                {
                    if (this._debug)
                    {
                        Core.Debug.consoleWrite("waitForInit FCKeditorAPI exists "+FCKeditorAPI);
                    }
                    var oEditor = FCKeditorAPI.GetInstance(this._editorID) ;
                    if (typeof(oEditor) != "undefined")
                    {
                        if (this._debug)
                        {
                            Core.Debug.consoleWrite("waitForInit have oEditor status "+oEditor.Status);
                        }
                        if (typeof(oEditor.Status) != "undefined" && oEditor.Status == FCK_STATUS_COMPLETE)
                        {
                            if (this._debug)
                            {
                                Core.Debug.consoleWrite("waitForInit have oEditor with status ready");
                            }
                            this.cancelPendingInit();
                            if (this._debug)
                            {
                                Core.Debug.consoleWrite("waitForInit calling renderUpdate");
                            }
                            this.renderUpdate();
                            waitAgain= false;
                            //this.scheduleSync();
                        }
                        else
                        {
                            if (this._debug)
                            {
                                Core.Debug.consoleWrite("waitForInit have oEditor but no status or not yet ready");
                            }
                            waitAgain= true;
                        }
                    }
                    else
                    {
                        if (this._debug)
                        {
                            Core.Debug.consoleWrite("waitForInit NOT have oEditor");
                        }
                        waitAgain= true;
                    }
                }
                else
                {
                    if (this._debug)
                    {
                        Core.Debug.consoleWrite("waitForInit NOT have FCKeditorAPI");
                    }
                    waitAgain= true;
                }
                if (waitAgain)
                {
                    if (this._debug)
                    {
                        Core.Debug.consoleWrite("waitForInit waitAGain "+this._editorID);
                    }
                    this.cancelPendingInit();
                    this.pendingInit = Core.Web.Scheduler.run(Core.method(this,
                                                    this.waitForInit), 100, true);
                }
                else
                {
                    if (this._debug)
                    {
                        Core.Debug.consoleWrite("waitForInit NOT again for "+this._editorID);
                    }
                }
                if (this._debug)
                {
                    Core.Debug.consoleWrite("waitForInit "+this._editorID+" finished");
                }
	},

	cancelPendingInit : function() {
                if (this._debug)
                {
                    Core.Debug.consoleWrite("cancelPendingInit "+this._editorID);
                }
		if (typeof(this.pendingInit) !== "undefined") {
			Core.Web.Scheduler.remove(this.pendingInit);
			delete this.pendingInit;
		}
	},

	cancelPendingSync : function() {
                if (this._debug)
                {
                    Core.Debug.consoleWrite("cancelPendingSync "+this._editorID);
                }
		if (typeof(this.pendingSync) !== "undefined") {
			Core.Web.Scheduler.remove(this.pendingSync);
			delete this.pendingSync;
		}
	},

        renderUpdate: function(update) {
                if (this._debug)
                {
                    Core.Debug.consoleWrite("fckeditor renderUpdate "+this._editorID);
                }
                if (typeof(FCKeditorAPI) == "undefined")
                {
                    Core.Debug.consoleWrite("renderUpdate called, but FCKEditorAPI not yet existing");
                }
                else
                {
                    var oEditor = FCKeditorAPI.GetInstance(this._editorID) ;
                    oEditor._echoObject= this; // Add link back to myself for FCKEditor events

                    this._text= this.component.render("text");
                    if (this._text == undefined)
                    {
                        // Prevent problem when NULL is passed in on serverside
                        if (this._debug)
                        {
                            Core.Debug.consoleWrite("fckeditor renderUpdate "+this._editorID+" setting to empty string instead of undefined");
                        }
                        oEditor.SetData("");
                    }
                    else
                    {
                        oEditor.SetData(this._text);
                    }
                    this._toolbarCollapsed= this.component.render("toolbarCollapsed");
                    this._toolbarAutocollapse= this.component.render("toolbarAutocollapse");
                    if (!this.component.isRenderEnabled())
                    {
                        // Not yet working
                        if (this._debug)
                        {
                            Core.Debug.consoleWrite("fckeditor renderUpdate "+this._editorID+" DISabling editor");
                        }
                        oEditor.ToolbarSet.Collapse();
                    }
                    else
                    {
                        if (this._text != undefined)
                        {
                            if (this._toolbarAutocollapse)
                            {
                                oEditor.ToolbarSet.Collapse();
                            }
                            else
                            {
                                if (this._toolbarCollapsed)
                                {
                                    oEditor.ToolbarSet.Collapse();
                                }
                                else
                                {
                                    oEditor.ToolbarSet.Expand();
                                }
                            }
                        }
                    }

                    // The event listeners get discarded with the SetData method, so we have to re attach them each time
                    oEditor.Events.AttachEvent( 'OnSelectionChange', this.editEvent) ;
                    oEditor.Events.AttachEvent( 'OnPaste', this.editEvent ) ;
                    oEditor.Events.AttachEvent( 'OnStatusChange', this.editEvent ) ;
                    if (this._toolbarAutocollapse)
                    {
                        oEditor.Events.AttachEvent( 'OnBlur' , this.onBlur ) ;
                        oEditor.Events.AttachEvent( 'OnFocus', this.onFocus ) ;
                    }
                }
                return true;
        },

	executeSync : function() {
                if (this._debug)
                {
                    Core.Debug.consoleWrite("executeSync started "+this._editorID);
                }
                if (typeof(FCKeditorAPI) == "undefined")
                {
                    Core.Debug.consoleWrite("executeSync called, but FCKEditorAPI not yet existing");
                }
                else
                {
                    var oEditor = FCKeditorAPI.GetInstance(this._editorID) ;
                    var newHTML = oEditor.GetData();
                    var oldHTML = this.component.get("text");
                    if (newHTML != oldHTML)
                    {
                        if (this._debug)
                        {
                            Core.Debug.consoleWrite("old <"+oldHTML+"> new <"+newHTML+">");
                        }
                        this.component.set("text", newHTML);
                        oEditor.ResetIsDirty();
                    }
                }
	},

	editEvent : function(oEditor) {
                // This is not this, but the function
                if (this._debug)
                {
                    Core.Debug.consoleWrite("editEvent started "+oEditor.Name);
                    Core.Debug.consoleWrite("IsDirty? "+oEditor.IsDirty());
                }
                var newHTML = oEditor.GetData();
                var oldHTML = oEditor._echoObject.component.get("text");
                if (newHTML != oldHTML)
                {
                    if (this._debug)
                    {
                        Core.Debug.consoleWrite("New <"+newHTML+">");
                    }
                    oEditor._echoObject.component.set("text", newHTML);
                    oEditor.ResetIsDirty();
                }
	},

        onFocus : function(editorInstance) {
            editorInstance.ToolbarSet.Expand() ;
        },

        onBlur : function(editorInstance) {
            editorInstance.ToolbarSet.Collapse() ;
        }
});


function FCKeditor_OnComplete(editorInstance)
{
    Core.Debug.consoleWrite("fckeditor OnComplete received for "+editorInstance.name);
}
