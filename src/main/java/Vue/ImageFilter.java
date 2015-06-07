package Vue;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Définit un filtre permettant de choisir uniquement des images (gif, jpeg, jpg ou png)
 */
public class ImageFilter extends FileFilter {
    /**
     * Retourne l'extension du fichier choisi
     *
     * @param f Fichier en entrée
     * @return Extension du foichier sous forme de String
     */
    public String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    /**
     * Définit si le fichier est une image ou non (permet aussi de naviguer dans les répertoires)
     * @param f Fichier en entrée
     * @return true si le fichier est une image, false sinon
     */
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

    /**
     * Montre les fichiers attendus sur l'interface graphique
     * @return "Images"
     */
    public String getDescription() {
        return "Images";
    }
}
