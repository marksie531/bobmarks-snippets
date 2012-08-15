package org.bobmarks.simple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
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
     * Main method to test snippets.
     * 
     * @param args
     */
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
        
        org.dom4j.Document dom = Snippets.getDom4JDoc("<?xml version=\"1.0\"?><test2><a><b u=\"hell\" t=\"world\" /></a></test2>");  // 7)
        doc.getRootElement().addContent(new Element ("Bob"));
        
        input = Snippets.getXpath(dom.getRootElement(), "//b/@t");
        System.out.println ("Snippets.getXpath: " + input);
        
        input = Snippets.formatDom4JDoc(dom, "  ", true);       // 8)
        System.out.println ("Snippets.formatDom4JDoc: " + input);
        
        file.delete();
    }
}