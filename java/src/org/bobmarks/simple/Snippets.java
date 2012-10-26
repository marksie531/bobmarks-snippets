/*
 * BobMarks-snippets - Snippets of code
 * http://code.google.com/p/bobmarks-snippets
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bobmarks.simple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Collection of snippets.
 * 
 * See "https://sites.google.com/site/bobmarksweb/java"
 */
public class Snippets {

    /**
     * 1) Read contents of a file into a String.
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readFile(File fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String nextLine = null;        
        while ((nextLine = br.readLine()) != null) {
            if(sb.length() > 0)
                sb.append("\n");
            sb.append(nextLine);
        }
        br.close();
        return sb.toString();
    }

    /**
     * 2) Write a String to a file.
     * 
     * @param file
     * @param text
     * @throws IOException
     */
    public static void writeFile(File file, String text) throws IOException {
        FileWriter out = new FileWriter(file);
        out.write(text);
        out.close();
    }

    /**
     * 3) Parsing a String to a JDOM XML document (requires JDOM library).
     * 
     * @param input
     * @return
     * @throws JDOMException
     */
    public static Document getJDomDoc (String input) throws JDOMException {
        SAXBuilder builder = new SAXBuilder();
        StringReader sr = new StringReader (input);
        Document dom = builder.build(sr);   
        return dom;
    }   

    /**
     * 4) Formatting an JDOM XML document into a String (requires JDOM library).
     * 
     * @param dom
     * @param indent
     * @param newLines
     * @return
     */
    public static String formatJDomDoc(Document dom, String indent, boolean newLines) {
        XMLOutputter outputter = new XMLOutputter(indent, newLines);
        Element rootElm = dom.getRootElement();
        return outputter.outputString(rootElm);
    }

    /**
     * 5) Return String from regex specified with group
     * 
     * @param input
     * @param regex
     * @param group
     * @return
     */
    public static String getRegex(String input, String regex, int group) {
        Pattern p = Pattern.compile(regex, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher m = p.matcher(input);
        while (m.find()) {
            return m.group(group);
        }
        return null;
    }

    /**
     * 6) Return String from xpath (requires dom4j, jaxen and saxpath)
     * 
     * @param xpath
     * @return
     */
    public static String getXpath (org.dom4j.Element element, String xpath) {
        Node node = element.selectSingleNode (xpath);
        return node.getStringValue();
    }

    /**
     * 7) Parsing a String to a dom4j XML document (requires dom4j library)
     * 
     * @param input
     * @return
     * @throws DocumentException
     */
    public static org.dom4j.Document getDom4JDoc (String input) throws DocumentException {
        SAXReader reader = new SAXReader();
        StringReader sr = new StringReader (input);
        org.dom4j.Document dom = reader.read(sr);
        return dom;
    }

    /**
     * 8) Formatting an dom4j XML document into a String (requires dom4j library)
     * 
     * NOTE: Note that both dom4j document and element class contain asXml() methods which might be ok.
     * 
     * @param dom
     * @param indent
     * @param newLines
     * @return
     * @throws IOException 
     */
    public static String formatDom4JDoc(org.dom4j.Document dom, String indent, boolean newLines) throws IOException {
              StringWriter sw = new StringWriter ();
        OutputFormat format = new OutputFormat (indent, newLines);
        XMLWriter writer = new XMLWriter(sw, format);
        writer.write(dom);
        writer.flush();
        return sw.toString();
    }

    /**
     * 9) Load properties file as a Resource
     * 
     * @param filename
     * @return
     * @throws IOException
     */
    public static Properties getProperties (String filename) throws IOException {
        InputStream is = Snippets.class.getResourceAsStream (filename);
        Properties properties = new Properties ();
        properties.load (is);
        return properties;
    }
    
    /**
     * 10) Format a Date into a String.
     * 
     * @param date
     * @return
     */
    public static String formatDate (Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat (format);
        return sdf.format(date);
    }
    
    /**
     * 11) Format a String into a Date.
     * 
     * @param date
     * @return
     */
    public static Date parseDate (String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat (format);
        return sdf.parse(date);
    }
    
    /**
     * 12) Return a JSon object from a String.
     * 
     * @param input
     * @return
     * @throws ParseException
     */
    public static JSONObject getJson (String input) throws org.json.simple.parser.ParseException {
    	JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(input);
    }

    /**
     * 13) Format a String from a Json object.
     * 
     * @param input
     * @return
     * @throws ParseException
     */
    public static String formatJson (JSONObject input) {
    	return input.toJSONString();
    }    
    
    /**
     * Main method to test snippets.
     * 
     * @param args
     */
    @SuppressWarnings("unchecked")
	public static void main (String [] args) throws Exception {
        File file = new File ("test_file.txt");
        
        Snippets.writeFile(file, "random text");        // 2)
        
        String input = Snippets.readFile(file);        // 1)
        System.out.println ("Snippets.readFile: " + input);
        
        Document doc = Snippets.getJDomDoc("<?xml version=\"1.0\"?><test1><elm/></test1>");  // 3)
        doc.getRootElement().addContent(new Element ("Dave"));
        
        input = Snippets.formatJDomDoc(doc, "    ", true);       // 4)
        System.out.println ("Snippets.formatJDomDoc: " + input);
        
        input = Snippets.getRegex("the quick brown fox jumps over the lazy dog", "brown (.*) the", 1);   // 5 
        System.out.println ("Snippets.getRegex: " + input);
        
        org.dom4j.Document dom = Snippets.getDom4JDoc("<?xml version=\"1.0\"?><test2><a><b u=\"hell\" t=\"world\" /></a></test2>");// 7)
        doc.getRootElement().addContent(new Element ("Bob"));
        
        input = Snippets.getXpath(dom.getRootElement(), "//b/@t");  // 6
        System.out.println ("Snippets.getXpath: " + input);
        
        input = Snippets.formatDom4JDoc(dom, "  ", true);       // 8)
        System.out.println ("Snippets.formatDom4JDoc: " + input);
        
        Properties properties = Snippets.getProperties("/org/bobmarks/simple/test.properties");   // 9
        System.out.println ("Snippets.getProperties: " + properties.getProperty("key2"));   
        
        String format = "yyyy-MM-dd hh:mm:ss";
        String dateAsString = Snippets.formatDate(new Date(), format);          // 10
        System.out.println ("Snippets.formatDate: " + dateAsString);
        
        Date date = Snippets.parseDate(dateAsString, format);                   // 11
        System.out.println ("Snippets.parseDate: " + date);
        
        input = "{\"hello\": {\"extra\":\"stuff\"},\"messages\":[\"msg 1\",\"msg 2\",\"msg 3\"]}";
		JSONObject jsonObj = Snippets.getJson (input);						// 12
		System.out.println ("Snippets.getJson: " + jsonObj);
		
		jsonObj.put("new", "thing");
		System.out.println ("Snippets.formatJson: " + Snippets.formatJson(jsonObj));		// 13
        
        file.delete();
    }
}