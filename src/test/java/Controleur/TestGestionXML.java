package Controleur;

import org.dom4j.DocumentException;
import org.junit.Test;

/**
 * Created by benoitvuillemin on 06/05/2015.
 */
public class TestGestionXML {
final String repertoire = "src/test/resources/test.xml";
    @Test
    public void testXML(){
        try {
            GestionXML.LectureXML(repertoire);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
