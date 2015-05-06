package Controleur;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by benoitvuillemin on 06/05/2015.
 */
public class GestionXML {


    public static void LectureXML(String repertoire) throws DocumentException {
        SAXReader reader = new SAXReader();
        URL url = null;
        try {
            url = new URL(repertoire);
            Document document = reader.read(url);
            Element root = document.getRootElement();
            for ( int i = 0, size = root.nodeCount(); i < size; i++ ) {
                Node node = root.node(i);
                if ( node instanceof Element ) {
                }
                else {
                    System.out.println(node.getStringValue());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
