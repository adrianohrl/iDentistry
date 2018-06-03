package tech.adrianohrl.identistry.view.components;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import org.apache.log4j.Logger;
import tech.adrianohrl.util.PropertyUtil;
 
/**
 *
 * @author adrianohrl
 */
public class ImageUtil {
    
    private static final Logger logger = Logger.getLogger(ImageUtil.class);

    /**
     * Get the extension of a file.
     * 
     * @param f
     * @return
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
    
    /**
     * Returns an ImageIcon, or null if the file is not valid.
     * 
     * @param file 
     * @return the image icon.
     */
    public static ImageIcon createImageIcon(File file) {
        try {
            Image image = ImageIO.read(file);
            return new ImageIcon(image);
        } catch (IOException e) {
            return null;
        }
    }
    
    public static ImageIcon chooseImageIcon(Component parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new ImageFilter());
        chooser.setAcceptAllFileFilterUsed(false);
        int returnVal = chooser.showOpenDialog(parent);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            logger.info("Open command cancelled by user.");
            return null;
        }
        File file = chooser.getSelectedFile();
        try {
            Image image = ImageIO.read(file);
            logger.info("Opening " + file.getName() + ".");
            return new ImageIcon(image, file.getPath());
        } catch (IOException e) {
            logger.error("Error while openning " + file.getName() + ".");
            return null;
        }
    }
    
    public static ImageIcon getDefaultProfilePicture() {
        String path = PropertyUtil.getDefaultProfilePicturePath();
        File file = new File(path);
        try {
            Image image = ImageIO.read(file);
            logger.info("Opening " + file.getName() + ".");
            return new ImageIcon(image, "");
        } catch (IOException e) {
            logger.error("Error while openning " + file.getName() + ".");
            return null;
        }
    }
    
    public static void saveImageIcon(ImageIcon icon) {
        
    }
}
