echopoint.constants.TEMPLATEPANEL = "echopoint.TemplatePanel";

/**
 * Component rendering peer: echopoint.TemplatePanel
 * @author MikaelS 2009-04-28
 * @version $Id: Sync.TemplatePanel.js,v 1.1.1.1 2010-04-01 09:47:33 perxi Exp $
 */


echopoint.TemplatePanelSync = Core.extend(Echo.Render.ComponentSync, {


    $load: function()
    {

        Echo.Render.registerPeer(echopoint.constants.TEMPLATEPANEL, this);
    },

    _containerDiv: null,

    parseXML: function(text) {

        var xmlDoc = null;

        try //Internet Explorer
        {
            xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
            xmlDoc.async = "false";
            xmlDoc.loadXML(text);
            return xmlDoc;
        }
        catch(e)
        {
            try //Firefox, Mozilla, Opera, etc.
            {
                parser = new DOMParser();
                xmlDoc = parser.parseFromString(text, "text/xml");
                xmlDoc.async = "false";
                return xmlDoc;
            }
            catch(e) {
                alert(e.message);
            }
        }

        return xmlDoc;

    },


    /** @see Echo.Render.ComponentSync#renderAdd */
    renderAdd: function(update, parentElement) {

        // Top level container
        this._containerDiv = document.createElement("div");
        this._containerDiv.style.outlineStyle = "none";

        this._containerDiv.id = this.component.renderId;
        parentElement.appendChild(this._containerDiv);

        // The number of components added to the TemplatePanel
        var componentCount = this.component.getComponentCount();
        // Contains the mapping between component names and render id
        var mappingXml = this.component.render("componentmapping");

        // The mapping document is parsed...
        var doc = this.parseXML(mappingXml);
        // ... and all mappings are stored in the array x
        x = doc.documentElement.childNodes;
        var componentHashmap = {};
        for (i = 0; i < x.length; i++)
        {
            var mappingComponentRenderId = "C." + x[i].childNodes[0].nodeValue;
            var mappingComponentName = x[i].getAttribute("name");
            componentHashmap[mappingComponentName] = mappingComponentRenderId;
        }

        // Parse the xhtml template (surround template with div-tag to create valid xml in case the document contains no root tag).
        var templateDoc = this.parseXML("<div>" + this.component.render("templateDataSource") + "</div>");

        // If the template contains a body-tag we need to remove it.
        var e = templateDoc.getElementsByTagName("body");
        var newNode;
        if (e.length > 0)
        {
            // So we have a body element.
            var bodyElement = e[0];
            // Iterate over all children to the body element and add each child to the top level container
            for (var childIndex = 0; childIndex < bodyElement.childNodes.length; childIndex++)
            {
                newNode = document._importNode(bodyElement.childNodes[childIndex], true);
                this._containerDiv.appendChild(newNode);
            }
        }
        else
        {
            // This means the xhtml does not contain any body tag...
            newNode = document._importNode(templateDoc.documentElement, true);
            // ... and thus append the template straight away.
            this._containerDiv.appendChild(newNode);
        }

        // Now find all component-tags (i.e. the placeholders for the child components).
        var cTags = this._containerDiv.getElementsByTagName("component");

        while (cTags.length > 0)
        {
            // For each component-tag we need to find the component that should replace that placeholder.
            var templateComponentName = cTags[0].attributes.getNamedItem("name").nodeValue;

            var component = null;

            var mappingRenderId = componentHashmap[templateComponentName];
            var newDiv;
            if (mappingRenderId == null)
            {
                newDiv = document.createElement('div');
                newDiv.style.background = "red";
                newDiv.style.color = "white";
                newDiv.style.fontSize = "8pt";
                newDiv.style.borderWidth = "1";
                newDiv.style.borderStyle = "solid";
                newDiv.style.borderColor = "black";
                newDiv.style.padding = "4";
                newDiv.style.margin ="4";
                var b = document.createElement('b');
                newDiv.appendChild(b);
                var p = document.createElement('p');
                b.appendChild(p);
                var errorNode = document.createTextNode("Template Error: " + "Component named '" + templateComponentName + "' could not be found!");
                p.appendChild(errorNode);
                cTags[0].parentNode.replaceChild(newDiv, cTags[0]);
            }
            else
            {

                // Now let's find the component that matches the render id in the xml mapping
                for (j = 0; j < componentCount; ++j) {

                    var child = this.component.getComponent(j);
                    if (child.renderId == mappingRenderId)
                    {
                        // Now we've found the component to replace the placeholder with.
                        component = child;
                        break;
                    }
                }


                if (component != null)
                {
                    newDiv = document.createElement('div');
                    // Replace the placeholder (component-tag) with the newly created div.
                    cTags[0].parentNode.replaceChild(newDiv, cTags[0]);
                    // Add the component
                    Echo.Render.renderComponentAdd(update, component, newDiv);

                    break;
                }
            }


        }


    },

    /** @see Echo.Render.ComponentSync#renderDispose */
    renderDispose: function(update) {
        this._containerDiv = null;
    },

    /** @see Echo.Render.ComponentSync#renderUpdate */
    renderUpdate: function(update) {
        var element = this._containerDiv;
        var containerElement = element.parentNode;
        Echo.Render.renderComponentDispose(update, update.parent);
        containerElement.removeChild(element);
        this.renderAdd(update, containerElement);
        return true;
    }



});