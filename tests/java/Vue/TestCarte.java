package Vue;

import Modele.*;
import org.junit.Test;

import javax.swing.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Teste la vue Carte
 */
public class TestCarte {
    private Carte c = new Carte();
    private Graphe g = new Graphe();
    private Noeud n1 = new Noeud(0.0, 1.0, TypeNoeud.NORMAL, c);
    private Noeud n2 = new Noeud(1.0, 1.0, TypeNoeud.NORMAL, c);
    private Noeud n3 = new Noeud(3.0, 4.0, TypeNoeud.NORMAL, c);
    private Noeud n4 = new Noeud(5.0, 2.0, TypeNoeud.NORMAL, c);

    private Arc a1 = new Arc(n1, n2, TypeArc.PLAT, c);
    private Arc a2 = new Arc(n2, n3, TypeArc.PLAT, c);
    private Arc a3 = new Arc(n3, n4, TypeArc.PLAT, c);
    private Arc a4 = new Arc(n4, n1, TypeArc.PLAT, c);

    private RobotAPattes r1 = new RobotAPattes(n1, c, g);

    public void testCarte() {
    }

    /**
     * Teste les ajouts d'éléments sur la carte
     */
    @Test
    public void testAjoutElements() {
        c.addNoeud(n1);
        c.addNoeud(n2);
        c.addNoeud(n3);
        c.addNoeud(n4);
        c.addArc(a1);
        c.addArc(a2);
        c.addArc(a3);
        c.addArc(a4);
        c.addRobot(r1);
        assertTrue(c.getNoeuds().contains(n1));
        assertTrue(c.getNoeuds().contains(n2));
        assertTrue(c.getNoeuds().contains(n3));
        assertTrue(c.getNoeuds().contains(n4));
        assertTrue(c.getArcs().contains(a1));
        assertTrue(c.getArcs().contains(a2));
        assertTrue(c.getArcs().contains(a3));
        assertTrue(c.getArcs().contains(a4));
        assertTrue(c.getRobots().contains(r1));
    }

    /**
     * Teste la remise à zéro de la carte
     */
    @Test
    public void testReset() {
        ImageIcon rtt = new ImageIcon("resources/rtt.png");
        c.reset();
        assertTrue(c.getNoeuds().isEmpty());
        assertTrue(c.getArcs().isEmpty());
        assertTrue(c.getRobots().isEmpty());
        assertNull(c.getImageFond());
    }

    /**
     * Teste la sélection d'un Noeud lors de la création d'un Arc
     */
    @Test
    public void testSelection() {
        c.setNoeudSelectionne(n1);
        assertEquals(c.getNoeudSelectionne(), n1);
    }

    /**
     * Teste l'ajout et la suppression d'une image de fond
     */
    @Test
    public void testImageFond() {
        ImageIcon rtt = new ImageIcon("ressources/rtt.png");
        c.setImageFond(rtt.getImage());
        assertEquals(c.getImageFond(), rtt.getImage());
        c.supprimerImageFond();
        assertNull(c.getImageFond());
    }


}
