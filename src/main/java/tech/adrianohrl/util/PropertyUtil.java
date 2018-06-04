/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
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
    
    public static String getDefaultProfilePicturePath() {
        String path = PropertyUtil.getProperty("images.default.profile.picture");
        System.out.println("default profile picture path: " + path);
        ClassLoader classLoader = PropertyUtil.class.getClassLoader();
        URL url = classLoader.getResource(path);
        System.out.println("url path: " + url);
        return url != null ? url.getFile() : "";
    }
    
    public static double getLowPasswordEntropyThreshold() {
        return Double.parseDouble(PropertyUtil.getProperty("general.config.password.entropy.threshold.low"));
    }
    
    public static double getHighPasswordEntropyThreshold() {
        return Double.parseDouble(PropertyUtil.getProperty("general.config.password.entropy.threshold.high"));
    }
    
    public static Locale getDefaultLocale() {
        String language = PropertyUtil.getProperty("general.config.locale.language");
        String country = PropertyUtil.getProperty("general.config.locale.country");
        return new Locale(language, country);
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
