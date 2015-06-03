package Controleur;

//import org.dom4j.DocumentException;

import Modele.Graphe;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;


/**
 * Created by benoitvuillemin on 06/05/2015.
 */
public class TestGestionXML {
    final String repertoire = "src/test/resources/test.xml";

    /**
     * Teste la lecture et l'écriture d'un fichier XML
     *
     * @throws Exception si la lecture ou l'écriture ne marchent pas
     */
    /*@Test
    public void testXML() throws Exception {
        Graphe g = GestionXML.LectureXML(repertoire, );
        try {
            GestionXML.SauvegardeXML(g, "src/test/resources/essai.xml");
            BufferedReader br1 = new BufferedReader(new FileReader(new File("src/test/resources/test.xml")));
            String line1;
            StringBuilder sb1 = new StringBuilder();

            while ((line1 = br1.readLine()) != null) {
                sb1.append(line1.trim());
            }

            BufferedReader br2 = new BufferedReader(new FileReader(new File("src/test/resources/essai.xml")));
            StringBuilder sb2 = new StringBuilder();
            String line2;
            while ((line2 = br2.readLine()) != null) {
                sb2.append(line2.trim());
            }
            XMLUnit.setIgnoreWhitespace(true);

            try {
                assertXMLEqual(sb1.toString(), sb2.toString());
            } catch (SAXException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
