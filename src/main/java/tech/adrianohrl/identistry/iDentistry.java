/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry;

import java.util.Locale;
import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author adrianohrl
 */
public class iDentistry {
    
    private final static String LOOKANDFEEL = "System";
    private final static String THEME = "Ocean";
    
    private static void setLookAndFeel() {
        String lookAndFeel;
        if (LOOKANDFEEL != null) {
            if (LOOKANDFEEL.equals("Metal")) {
                lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";  
            } else if (LOOKANDFEEL.equals("System")) {
                lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            } else if (LOOKANDFEEL.equals("Motif")) {
                lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            } else if (LOOKANDFEEL.equals("GTK")) { 
                lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            } else {
                System.err.println("Unexpected value of LOOKANDFEEL specified: " + LOOKANDFEEL);
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }
            try {
                UIManager.setLookAndFeel(lookAndFeel);
                if (LOOKANDFEEL.equals("Metal")) {
                  if (THEME.equals("DefaultMetal")) {
                      MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
                  } else if (THEME.equals("Ocean")) {
                     MetalLookAndFeel.setCurrentTheme(new OceanTheme());
                  }
                  UIManager.setLookAndFeel(new MetalLookAndFeel()); 
                }	                
            } catch (ClassNotFoundException e) {
                System.err.println("Couldn't find class for specified look and feel:" + lookAndFeel);
                System.err.println("Did you include the L&F library in the class path?");
                System.err.println("Using the default look and feel.");
            } catch (UnsupportedLookAndFeelException e) {
                System.err.println("Can't use the specified look and feel (" + lookAndFeel + ") on this platform.");
                System.err.println("Using the default look and feel.");
            } catch (InstantiationException | IllegalAccessException e) {
                System.err.println("Couldn't get specified look and feel (" + lookAndFeel + "), for some reason.");
                System.err.println("Using the default look and feel.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        IconFontSwing.register(FontAwesome.getIconFont());
        Locale.setDefault(new Locale("pt", "BR"));
        setLookAndFeel();
    }
    
}
