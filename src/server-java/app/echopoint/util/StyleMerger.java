/*
 * This file is part of the Echo Point Project.  This project is a collection
 * of Components that have extended the Echo Web Application Framework.
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
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
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */
package echopoint.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * <code>StyleMerger</code> is an Component which alllows you to merge two
 * xml style definitions into a single one, which can then be loaded by echo.
 *
 * We use it in the following way:
 *
 * Our application has a default style which is used for normal installations
 * If we build special versions of our application to a customer, we then
 * add a second stylesheet with only the modifications for that client.
 * On system startup we then merge the two XML styles into one and then load
 * the resulting one.
 *
 * @author a.schild
 */
public class StyleMerger
{
    /**
     *
     * @param baseStylesFile   File with default styles
     * @param patchStylesFile  File which contains the styles to be added/patched
     * @param outFile          File with the merged styles
     *
     * @throws IOException
     * @throws JDOMException
     */
    public static void mergeXMLStyles(String baseStylesFile, String patchStylesFile, String outFile) throws IOException, JDOMException
    {
        mergeXMLStyles(new FileReader(baseStylesFile), new FileReader(patchStylesFile), new FileWriter(outFile));
    }

    /**
     *
     * @param mainStyles        Reader with with default styles
     * @param patchStyles       Reader which contains the styles to be added/patched
     * @param out               Output with the merged styles
     *
     * @throws IOException
     * @throws JDOMException
     */
    public static void mergeXMLStyles(Reader mainStyles, Reader patchStyles, Writer out) throws IOException, JDOMException
    {
        SAXBuilder builder= new SAXBuilder();
        Document doc = builder.build(mainStyles);
        Document doc2 = builder.build(patchStyles);

        Document outDoc= mergeStyles(doc, doc2);

        XMLOutputter xmlOut= new XMLOutputter();
        xmlOut.output(doc, out);
    }


    /**
     *
     * @param mainStyles        Stream with with default styles
     * @param patchStyles       Stream which contains the styles to be added/patched
     * @param out               Output with the merged styles
     * 
     * @throws IOException
     * @throws JDOMException
     */
    public static void mergeXMLStyles(InputStream mainStyles, InputStream patchStyles, OutputStream out) throws IOException, JDOMException
    {
        SAXBuilder builder= new SAXBuilder();
        Document doc = builder.build(mainStyles);
        Document doc2 = builder.build(patchStyles);

        Document outDoc= mergeStyles(doc, doc2);

        XMLOutputter xmlOut= new XMLOutputter();
        xmlOut.output(outDoc, out);
    }

    /**
     * Merge the two docs and return the merged one.
     *
     * @param doc1
     * @param doc2
     *
     * @return doc1 is containing the merged document
     */
    protected static Document mergeStyles(Document doc1, Document doc2)
    {
        // Get List of children to be added/patched into the main style
        // Should be the ss entry
        Element doc1RootElement= doc1.getRootElement();
        Element doc2RootElement= doc2.getRootElement();
        if (doc2RootElement.getName().equals("ss"))
        {
            List<Element> l1Children= doc2RootElement.getChildren();

            for(Element style : l1Children)
            {
                String name= style.getName();
                if (name.equals("s"))
                {
                    // Build style name, so search fo rin the original document
                    String attrName= makeStyleName(style);
                    // Now look if we have such a style in the original styles
                    Element targetElement= findStyleDefinition(doc1RootElement, attrName);
                    if (targetElement != null)
                    {
                        // Found style in original document, so we have to merge it
                        mergeStyle(targetElement, style);
                    }
                    else
                    {
                        // Not found in ogiginal document, so copy it over
                        Element newElement= (Element) style.clone();
                        doc1RootElement.addContent(newElement);
                    }
                }
                else
                {
                    //_log.fatal("Style is not named <s> but <"+name+"> instead");
                }
                //List<Content> styles= (List<Content>)ssChild..getContent();
            }
        }
        else
        {
            //_log.fatal("StyleSheet is not named <ss> but <"+doc2RootElement.getName()+"> instead");
        }
        return doc1;
    }

    /**
     * Make up a stylename which respects all attributes to uniquely identify
     * a style.
     * 
     * @param style
     * @return
     */
    protected static String makeStyleName(Element style)
    {
        String retVal= "";
        List<Attribute> sAttrs= style.getAttributes();
        for (Attribute thisAttr : sAttrs)
        {
            retVal+=">"+thisAttr.getName()+">"+thisAttr.getValue();
        }
        return retVal;
    }

    /**
     * Sear this tree for a matching style
     * 
     * @param rootNode
     * @param styleName
     * @return
     */
    protected static Element findStyleDefinition(Element rootNode, String styleName)
    {
        Element retVal= null;
        List<Element> children= rootNode.getChildren();
        Iterator<Element> iChildren= children.iterator();
        while (retVal == null && iChildren.hasNext())
        {
            Element thisChild= iChildren.next();
            String thisName= makeStyleName(thisChild);
            if (thisName.equals(styleName))
            {
                retVal= thisChild;
            }
        }
        return retVal;
    }

    /**
     * Merge two styles.
     * All attribuets/values found in partialStyle are replacing existing
     * values in targetStyle.
     * If no such attributes exist in targetStyle, then we add them
     *
     * @param targetStyle
     * @param partialStyle
     */
    protected static void mergeStyle(Element targetStyle, Element partialStyle)
    {
        // Don't copy over attributes
        // partialStyle.getAttributes()

        // Now the definitions
        List<Element> children= partialStyle.getChildren();
        for (Element child: children)
        {
            String cName= makeStyleName(child);
            // Now search in target
            List<Content> tChildren= targetStyle.getChildren(child.getName());
            if (tChildren.isEmpty())
            {
                // Ok, add it
                Element tElement= (Element)child.clone();
                targetStyle.addContent(tElement);
            }
            else
            {
                // .. merge ..
                Element targetNode= null;
                for (Content childContent : tChildren)
                {
                    if (childContent instanceof org.jdom.Text)
                    {
                        // Skip
                    }
                    else
                    {
                        // A node, add it
                        Element myElement= (Element) childContent;
                        String eName= makeStyleName(myElement);
                        if (targetNode == null)
                        {
                            if (eName.equals(cName))
                            {
                                targetNode= myElement;
                            }
                        }
                    }
                }
                if (targetNode == null)
                {
                    // Add node

                }
                else
                {
                    // Merge/Replace?
                    targetStyle.removeContent(targetNode);
                    targetStyle.addContent((Content) child.clone());
                }
            }
        }

    }

}
