package Controleur;


import Modele.Arc;
import Modele.Graphe;
import Modele.Noeud;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benoitvuillemin on 06/05/2015.
 */
public class GestionXML {
    public static Graphe LectureXML(String repertoire) {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(repertoire);
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            Graphe g = new Graphe();
            List list_noeud_xml = rootNode.getChildren("node");
            List list_arc_xml = rootNode.getChildren("edge");
            ArrayList<Noeud> liste_noeud = new ArrayList<Noeud>();
            ArrayList<Arc> liste_arc = new ArrayList<Arc>();
            int id;
            double x;
            double y;
            int index_n1;
            int index_n2;
            Noeud n1;
            Noeud n2;
            String type;

            for (int i = 0; i < list_noeud_xml.size(); i++) {
                Element noeud = (Element) list_noeud_xml.get(i);
                id = Integer.valueOf(noeud.getAttributeValue("id"));
                x = Double.valueOf(noeud.getAttributeValue("x"));
                y = Double.valueOf(noeud.getAttributeValue("y"));
                type = noeud.getAttributeValue("type");
                liste_noeud.add(new Noeud(id, x, y, type));
            }
            g.setListe_noeud(liste_noeud);
            for (int i = 0; i < list_arc_xml.size(); i++) {
                Element arc = (Element) list_arc_xml.get(i);
                index_n1 = Integer.valueOf(arc.getAttributeValue("nd1"));
                index_n2 = Integer.valueOf(arc.getAttributeValue("nd2"));
                n1 = g.trouverNoeudParId(index_n1);
                n2 = g.trouverNoeudParId(index_n2);
                type = arc.getAttributeValue("type");
                liste_arc.add(new Arc(n1, n2, type));
            }
            g.setListe_arcs(liste_arc);
            return g;

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
        return null;
    }

    public static void SauvegardeXML(Graphe g, String repertoire) throws IOException {
        Element osm = new Element("osm");
        Document doc = new Document(osm);
        doc.setRootElement(osm);
        Element element_arc = new Element("edge");
        for (Noeud noeud : g.getListe_noeud()) {
            Element element_noeud = new Element("node");
            element_noeud.setAttribute("id", Integer.toString(noeud.getId()));
            element_noeud.setAttribute("x", Double.toString(noeud.getX()));
            element_noeud.setAttribute("y", Double.toString(noeud.getY()));
            element_noeud.setAttribute("type", noeud.getTypeString());
            doc.getRootElement().addContent(element_noeud);
        }
// new XMLOutputter().output(doc, System.out);
        XMLOutputter xmlOutput = new XMLOutputter();

        // display nice nice
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(doc, new FileWriter("repertoire"));
    }
}
