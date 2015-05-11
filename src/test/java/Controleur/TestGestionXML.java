package Controleur;

//import org.dom4j.DocumentException;

import Modele.Graphe;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by benoitvuillemin on 06/05/2015.
 */
public class TestGestionXML {
    final String repertoire = "src/test/resources/test.xml";

    @Test
    public void testLectureXML() {
        Graphe g = GestionXML.LectureXML(repertoire);
        try {
            GestionXML.SauvegardeXML(g, "essai.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
