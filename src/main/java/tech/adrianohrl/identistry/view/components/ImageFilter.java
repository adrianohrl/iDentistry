package tech.adrianohrl.identistry.view.components;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ImageFilter extends FileFilter {

    /**
     * Accepts only image files.
     * 
     * @param file
     * @return 
     */
    @Override
    public boolean accept(File file) {
        return file != null && (file.isDirectory() || ImageExtensions.isImageExtension(ImageUtil.getExtension(file)));
    }

    /**
     * Filter description.
     * 
     * @return the image filter description.
     */
    @Override
    public String getDescription() {
        return "Just Images";
    }
}