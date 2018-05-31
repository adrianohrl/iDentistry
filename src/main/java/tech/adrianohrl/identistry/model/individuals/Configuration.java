/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.UIManager;
import org.apache.log4j.Logger;
import org.ho.yaml.Yaml;
import tech.adrianohrl.util.PropertyUtil;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Configuration implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    private double sessionDuration;
    private String lookAndFeel;
    private final static Logger logger = Logger.getLogger(Configuration.class);

    public Configuration() {
    }
    
    public static Configuration getDefault() {
        String filename = "default-user-config.yaml";
        Configuration configuration = null;
        String path = PropertyUtil.getResourceConfigFolderPath();
        try {
            File file = new File(path + "/" + filename);
            configuration = Yaml.loadType(file, Configuration.class);
        } catch (FileNotFoundException e) {
            System.err.println("The iDentistry project is not configured properly: the \"config/default-user-config.yaml\" file does not exist.");
        }
        return configuration;
    }
    
    public void save(String filename) throws FileNotFoundException {
        String path = PropertyUtil.getPathToSave();
        File file = new File(path + "/" + filename);
        Yaml.dump(this, file, true);
    }
    
    public static Configuration load(String filename) throws FileNotFoundException {
        String path = PropertyUtil.getPathToSave();
        File file = new File(path + "/" + filename);
        return Yaml.loadType(file, Configuration.class);
    }

    @Override
    public String toString() {
        return "sessionDuration: " + sessionDuration + "\n" +
               "lookAndFeel: " + lookAndFeel;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public double getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(double sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    public String getLookAndFeel() {
        return lookAndFeel;
    }

    public void setLookAndFeel(String lookAndFeel) {
        switch (lookAndFeel) {
            case "Metal":
                this.lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
                break;
            case "System":
                this.lookAndFeel = UIManager.getSystemLookAndFeelClassName();
                break;
            case "Motif":
                this.lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                break;
            case "GTK":
                this.lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
                break;
            default:
                logger.error("Unexpected value of LookAndFeel specified: " + lookAndFeel);
                System.err.println("Unexpected value of LookAndFeel specified: " + lookAndFeel);
                this.lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        }
    }
    
}
