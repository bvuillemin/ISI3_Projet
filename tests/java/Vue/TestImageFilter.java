package Vue;

import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.Assert.assertEquals;

/**
 * Teste ImageFilter
 */
public class TestImageFilter {
    ImageFilter a = new ImageFilter();
    File f1 = new File("image.gif");
    File f2 = new File("image.jpeg");
    File f3 = new File("image.jpg");
    File f4 = new File("image.png");
    File f5 = new File("image.autre");

    /**
     * Teste la description affichée sur l'explorateur
     */
    @Test
    public void testDescription() {
        assertEquals(a.getDescription(), "Images");
    }

    /**
     * Teste l'acceptation d'un fichier
     */
    @Test
    public void testAccept() {
        assertTrue(a.accept(f1));
        assertTrue(a.accept(f2));
        assertTrue(a.accept(f3));
        assertTrue(a.accept(f4));
        assertFalse(a.accept(f5));
    }

    /**
     * Teste le renvoi d'une extension d'un fichier
     */
    @Test
    public void testGetExtension() {
        assertEquals(a.getExtension(f1), "gif");
    }

}
