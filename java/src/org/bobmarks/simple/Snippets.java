package org.bobmarks.simple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Collection of snippets.
 * 
 * See "https://sites.google.com/site/bobmarksweb/java"
 */
public class Snippets {

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
 
    public static void writeFile(File file, String text) throws IOException {
        FileWriter out = new FileWriter(file);
        out.write(text);
        out.close();
    }
}