/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author adrianohrl
 */
public class PropertyUtil {
    
    private PropertyUtil() {}
    
    private static final String propertyFilename = "config.properties";
    
    public static String getResourceConfigFolderPath() {
        ClassLoader classLoader = PropertyUtil.class.getClassLoader();
        URL url = classLoader.getResource("config");
        if (url == null) {
            String projectName = PropertyUtil.getProperty("project.name");
            System.err.println("The " + projectName + " project is not configured properly: the \"config\" folder does not exist.");
        }
        return url != null ? url.getFile() : "";
    }
    
    public static String getPathToSave() {
        String filepath = PropertyUtil.getProperty("general.config.filepath");
        return !filepath.startsWith("/") ? System.getProperty("user.home")  + "/" + filepath : filepath;
    }
    
    public static String getProperty(String property) {
        Properties properties = new Properties();
        ClassLoader classLoader = PropertyUtil.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(propertyFilename);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            String projectName = PropertyUtil.getProperty("project.name");
            System.err.println("The " + projectName + " project is not configured properly: the \"config.properties\" file does not exist.");
        }
        return properties.getProperty(property);
    }

    
}
