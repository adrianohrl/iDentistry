/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.components;

/**
 *
 * @author adrianohrl
 */
public enum ImageExtensions {
    
    JPEG("jpeg"),
    JPG("jpg"),
    GIF("gif"),
    TIFF("tiff"),
    TIF("tif"),
    PNG("png");
        
    private final String extension;

    private ImageExtensions(String extension) {
        this.extension = extension;
    }
    
    public boolean equals(ImageExtensions imageExtension) {
        return extension.equals(imageExtension.extension);
    }
    
    public boolean equals(String extension) {
        return extension != null && this.extension.equals(extension);
    }    
    
    public String getExtension() {
        return extension;
    }
    
    public static boolean isImageExtension(String extension) {
        for (ImageExtensions imageExtension : ImageExtensions.values()) {
            if (imageExtension.equals(extension)) {
                return true;
            }
        }
        return false;
    }
    
    
}
