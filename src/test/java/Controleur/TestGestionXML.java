package Controleur;

import org.junit.Test;

/**
 * Created by benoitvuillemin on 06/05/2015.
 */
public class TestGestionXML {
final String repertoire = "src/test/resources/test.xml";
    @Test
    public void testXML(){
        GestionXML.LectureXML(repertoire);
    }

}
