package Controleur;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by benoitvuillemin on 06/05/2015.
 */
public class GestionXML {
    public static void LectureXML(String repertoire) {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            try {
                final Document document = builder.parse(new File(repertoire));

                //Affiche la version de XML
                System.out.println(document.getXmlVersion());

                //Affiche l'encodage
                System.out.println(document.getXmlEncoding());

                //Affiche s'il s'agit d'un document standalone
                System.out.println(document.getXmlStandalone());
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}
