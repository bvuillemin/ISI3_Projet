package Vue;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by benoitvuillemin on 30/05/15.
 */
public class UtilitairesVue {

    public static class ImageFilter extends FileFilter {
        public String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
            return ext;
        }

        //Accept all directories and all gif, jpg, tiff, or png files.
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }

            String extension = getExtension(f);
            if (extension != null) {
                return extension.equals("gif") ||
                        extension.equals("jpeg") ||
                        extension.equals("jpg") ||
                        extension.equals("png");
            }
            return false;
        }

        public String getDescription() {
            return "Images";
        }
    }

}
