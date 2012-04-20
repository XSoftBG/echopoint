/*
 * Copyright (C) 2011 XSoft Ltd. (info@xsoftbg.com)
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
 * @author  Miroslav Yozov
 * @version $Id: Aar.Ckeditor.js,v 1.17 2011-02-15 08:55:42 yozov Exp $
 */

if (!Core.get(window, ["Aar", "Sync"])) {
	Core.set(window, ["Aar", "Sync"], {});
}

Aar.Ckeditor = Core.extend(Echo.Component, {
  focusable: true,
  
	$load : function() {
		Echo.ComponentFactory.registerType("Aar.Ckeditor", this);
	},

  /** @see Echo.Component#lostFocus */
  lostFocus: function() {
      try { this.peer.processBlur(null); } catch(e) {}
      Echo.Component.prototype.lostFocus.call(this);
  },

	componentType : "Aar.Ckeditor"
});

Aar.Ckeditor.Sync = Core.extend(Echo.Render.ComponentSync, {
  
	_editor: null,
  _textarea: null,
  _div: null,
  _editor_name: null,
  _fullPage: null,
  _toolbarCanCollapse: null,
  _toolbarLocation: null,
  _theme: null,
  _text: null,
  _ckBaseURL: null,
  _toolbar: null,
  _language: null,
  _resizable: null,
  _width: null,
  _height: null,
  _focused: false,
  _new_blured_data: null,
  _auto_maximize: null,

	$load : function() {
		Echo.Render.registerPeer("Aar.Ckeditor", this);
    Aar.Ckeditor.Loaded = false;
  },

  /**
   * Asynchronously retrieves the library.
   * This method will invoke the _jsLoaded listener when the library has been completed,
   * it will return before the library has been retrieved.
   */
  _loadCKScript : function(url)
  {
      if (this._debug)
      {
          Core.Debug.consoleWrite("_LoadCKScript from <"+url+">");
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
  _jsLoaded : function() {
    if (this._conn.getStatus() != 200)
    {
        // We should not use the private _url member of the
        // connection object, but....
        alert("Failed loading ckeditor.js with http status <" + this._conn.getStatus()
            + ">\nURL to Ckeditor wrong, or not set?\n" +
            this._conn._url
        );
    }

    if(!Aar.Ckeditor.Loaded) {
      this._jscontent = this._conn.getResponseText();
      window.CKEDITOR_BASEPATH = this._ckBaseURL;
			eval(this._jscontent);
      Aar.Ckeditor.Loaded = true;
    }

    this._createInstance();
  },

  _createInstance: function() {
    this._editor = CKEDITOR.appendTo(this._div,
    {
      fullPage: this._fullPage,
      toolbarCanCollapse: this._toolbarCanCollapse,
      toolbarLocation: this._toolbarLocation,
      skin: this._theme,
      toolbar: this._toolbar,
      language: this._language,
      resize_enabled: this._resizable,
      width: this._width,
      height: this._height,
      extraPlugins : 'autogrow',
      forcePasteAsPlainText : true
    });

    this._editor.on('instanceReady', this._initEditor, this);
  },

  _initEditor: function() {
    this._editor.on('paste', this._storeValue, this);
    this._editor.on('blur',  this.processBlur, this);
    this._editor.on('focus', this.processFocus, this);
    
    this._editor.document.on("keyup", this.processKey, this);
    this._editor.document.on("keydown", this.processKey, this);

    if(this._auto_maximize)
      this._editor.execCommand( 'maximize' );
    
    this._editor.setData(this._text);
  },

  renderAdd : function(update, parentElement) {
    this._div = document.createElement("div");
    this._div.style.width = "100%";
    this._div.style.height = "100%";
    this._div.id = this.component.renderId;

    this._fullPage           = this.component.render("fullPage", false);
    this._toolbarCanCollapse = this.component.render("toolbarCanCollapse", false);
    this._toolbarLocation    = this.component.render("toolbarLocation", "top");
    this._theme              = this.component.render("theme", "v2");
    this._language           = this.component.render("language", "bg");
    this._resizable          = this.component.render("resizable", false);    
    this._text               = this.component.render("text", "");
    this._ckBaseURL          = this.component.render("ckeditorURL", "ckeditor/");
    this._toolbar            = this.component.render("toolbar", "Full");

    if(this._toolbar != 'Full' && this._toolbar != 'Basic') {
      this._toolbar = this._toolbar.replace(' ', '');
      var toolbar_array = this._toolbar.split('|');
      var final_toolbar = [toolbar_array];
      this._toolbar = final_toolbar;
    }

    this._width  = this.component.render("width", "100%");
    this._height = this.component.render("height", "200px");

    this._auto_maximize = this.component.render("auto_maximize", false);

    parentElement.appendChild(this._div);
  	this._loadCKScript(this._ckBaseURL + "ckeditor.js");
  },

  renderDisplay : function() {
    
  },

  renderUpdate: function(update) {
    var updated_properties = update.getUpdatedPropertyNames();

    var new_config = this._editor.config;
    var text       = this._editor.getData();

    var is_new     = false;
    var new_text   = null;
    
    for(i = 0; i < updated_properties.length; i++) {
      var prop_name = updated_properties[i];
      switch(prop_name)
      {
        case "text":
          new_text = update.getUpdatedProperty(prop_name).newValue;
          break;
        case "height":
          new_config.height = update.getUpdatedProperty(prop_name).newValue;
          is_new = true;
          break;
        case "width":
          new_config.width = update.getUpdatedProperty(prop_name).newValue;
          is_new = true;
          break;
        case "toolbar":
          var new_prop = update.getUpdatedProperty(prop_name).newValue;
          if(new_prop == 'Full' || new_prop == 'Basic')
            new_config.toolbar = update.getUpdatedProperty(prop_name).newValue;
          else {
            new_prop = new_prop.split(' ').join('');
            var toolbar_array = new_prop.split('|');
            var final_toolbar = [toolbar_array];
            new_config.toolbar = final_toolbar;
          }
          is_new = true;
          break;
        case "resizable":
          new_config.resize_enabled = update.getUpdatedProperty(prop_name).newValue;
          is_new = true;
          break;
        case "language":
          new_config.language = update.getUpdatedProperty(prop_name).newValue;
          is_new = true;
          break;
        case "fullPage":
          new_config.fullPage = update.getUpdatedProperty(prop_name).newValue;
          break;
        case "toolbarCanCollapse":
          new_config.toolbarCanCollapse = update.getUpdatedProperty(prop_name).newValue;
          is_new = true;
          break;
        case "toolbarLocation":
          new_config.toolbarLocation = update.getUpdatedProperty(prop_name).newValue;
          is_new = true;
          break;
        case "theme":
          new_config.skin = update.getUpdatedProperty(prop_name).newValue;
          is_new = true;
          break;
        case "resize_maxHeight":
          var max_height = update.getUpdatedProperty(prop_name).newValue;
          new_config.resize_maxHeight = max_height.substring(0, max_height.length - 2);
          is_new = true;
          break;
        case "resize_maxWidth":
          var max_width = update.getUpdatedProperty(prop_name).newValue;
          new_config.resize_maxWidth = max_width.substring(0, max_width.length - 2);
          is_new = true;
          break;
        case "resize_minHeight":
          var min_height = update.getUpdatedProperty(prop_name).newValue;
          new_config.resize_minHeight = min_height.substring(0, min_height.length - 2);
          is_new = true;
          break;
        case "resize_minWidth":
          var min_width = update.getUpdatedProperty(prop_name);
          new_config.resize_minWidth = min_width.substring(0, min_width.length - 2);
          is_new = true;
          break;
      }
    }

    if(is_new) {
      this._editor.destroy();
      this._text = new_text == null ? text : new_text;
      this._editor = CKEDITOR.appendTo(this._div, new_config);
      this._editor.on("instanceReady", this._initEditor, this);
			return true;
    }

    if(new_text != null) {
      this._editor.setData(new_text);
			return true;
		}    
  },


  processFocus: function(e) {
    this._focused = true;
    if (this.client && this.component.isActive())
      this.client.application.setFocusedComponent(this.component);
    return false;
  },

  processKey: function(e) {
    this._storeValue(e);
    return true;
  },

  processBlur: function(e) {
    this._storeValue(e);
    return true;
  },

  _storeValue: function(e) {
    this._new_blured_data = this._editor.getData();

    if (!this.client.verifyInput(this.component))
      this.client.registerRestrictionListener(this.component, Core.method(this, this._processRestrictionsClear));
    else
      this.component.set("text", this._new_blured_data, true);
  },

  renderDispose : function(update) {
    this._editor.destroy(); 
    this._div = null;
    this._focused = false;
    this._editor = null;
    //Core.Web.Event.removeAll(this._editor);
  },

  /** @see Echo.Render.ComponentSync#renderFocus */
  renderFocus: function() {
    if (this._focused)
        return;
    this._focused = true;
    Core.Web.DOM.focusElement(this._div);
  },

  _processRestrictionsClear: function()
  {
    if (!this.client)
      return; // Component has been disposed, do nothing.
    this.component.set("text", this._new_blured_data, true);
  }
});
