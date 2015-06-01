package Controleur;

import Modele.*;
import Modele.Robot;
import Vue.Carte;
import Vue.InterfacePrincipale;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by thomascoquan on 28/05/2015.
 */
public class Controleur implements ActionListener, MouseListener {

    Carte carte;
    InterfacePrincipale ip;
    Graphe g;
    boolean premierClic = true;
    Noeud noeudTmp;
    Manager manager = new Manager();

    public void setCarte(Carte carte) {
        this.carte = carte;
        this.ip = null;
    }

    public void setIp(InterfacePrincipale ip) {
        this.ip = ip;
    }

    public void setManager(Manager m) {
        this.manager = m;
    }

    public void setPath_XML(String path_XML) {
        g = GestionXML.LectureXML(path_XML);
        for (Noeud noeud : g.getListe_noeud()) {
            carte.addNoeud(noeud);
        }
        for (Arc arc : g.getListe_arcs()) {
            carte.addArc(arc);
        }
        this.carte.setBackground(Color.WHITE);
    }

    public void savePath_XML(String path_XML) {
        try {
            GestionXML.SauvegardeXML(g, path_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        g = new Graphe();
        this.carte.setBackground(Color.WHITE);
    }

    public void removeBackground() {
        carte.removeBackground();
    }

    public void setBackground(Image i) {
        carte.setBackground(i);
    }

    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals("Nouveau graphe")) {
            carte.reset();
            g = new Graphe();
            this.removeBackground();
            carte.setBackground(Color.WHITE);
        } else if (c.equals("Charger une image de fond")) {
            ip.setBackground();
        } else if (c.equals("Charger un graphe")) {
            carte.reset();
            ip.setGraphe();
        } else if (c.equals("Sauvegarder un graphe")) {
            ip.saveGraphe();
        } else if (c.equals("Lancer")) {
            manager.affecterIncendie(carte, g);
        }
    }

    public void mouseClicked(MouseEvent e) {
        int typeAjout = ip.typeAjout();
        Noeud n;
        switch (typeAjout) {
            case 1:
                System.out.println("Nouveau Noeud");
                n = g.contientAppro(e.getX() + 10, e.getY() + 10);
                if (n==null) {
                    n = new Noeud(e.getX() + 10, e.getY() + 10, TypeNoeud.NORMAL, carte);
                    g.ajouterNoeud(n);
                    carte.addNoeud(n);
                }
                break;
            case 2:
                System.out.println("Nouvel Incendie");
                n = g.contientAppro(e.getX() + 10, e.getY() + 10);
                if (n==null) {
                    n = new Noeud(e.getX() + 10, e.getY() + 10, TypeNoeud.INCENDIE, carte);
                    g.ajouterNoeud(n);
                    carte.addNoeud(n);
                } else {
                    n.setIntensite((int) (Math.random() * (9) + 1));
                }
                break;
            case 3:
                System.out.println("Nouvel Arc Plat");
                n = g.contientAppro(e.getX() + 10, e.getY() + 10);
                if (n != null) {
                    if (premierClic) {
                        noeudTmp = n;
                        premierClic = false;
                        carte.setNoeudSelectionne(n);
                    } else {
                        Arc a = new Arc(noeudTmp, n, TypeArc.PLAT, carte);
                        g.ajouterArc(a);
                        carte.addArc(a);
                        premierClic = true;
                        carte.setNoeudSelectionne(null);
                    }
                }
                break;
            case 4:
                System.out.println("Nouvel Arc Escarpé");
                n = g.contientAppro(e.getX() + 10, e.getY() + 10);
                if (n != null) {
                    if (premierClic) {
                        noeudTmp = n;
                        premierClic = false;
                        carte.setNoeudSelectionne(n);
                    } else {
                        Arc a = new Arc(noeudTmp, n, TypeArc.ESCARPE, carte);
                        g.ajouterArc(a);
                        carte.addArc(a);
                        premierClic = true;
                        carte.setNoeudSelectionne(null);
                    }
                }
                break;
            case 5:
                System.out.println("Nouvel Arc Innondé");
                n = g.contientAppro(e.getX() + 10, e.getY() + 10);
                if (n != null) {
                    if (premierClic) {
                        noeudTmp = n;
                        premierClic = false;
                        carte.setNoeudSelectionne(n);
                    } else {
                        Arc a = new Arc(noeudTmp, n, TypeArc.INNONDE, carte);
                        g.ajouterArc(a);
                        carte.addArc(a);
                        premierClic = true;
                        carte.setNoeudSelectionne(null);
                    }
                }
                break;
            case 6:
                System.out.println("Nouveau Robot à Pattes");
                n = g.contientAppro(e.getX() + 10, e.getY() + 10);
                if (n != null) {
                    Robot r = new RobotAPates(n, carte, g);
                    carte.addRobot(r);
                }
                break;
            case 7:
                System.out.println("Nouveau Robot Chenille");
                n = g.contientAppro(e.getX() + 10, e.getY() + 10);
                if (n != null) {
                    Robot r = new RobotChenille(n, carte, g);
                    carte.addRobot(r);
                }
                break;
            case 8:
                System.out.println("Nouveau Robot Tout Terrain");
                n = g.contientAppro(e.getX() + 10, e.getY() + 10);
                if (n != null) {
                    Robot r = new RobotToutTerrain(n, carte, g);
                    carte.addRobot(r);
                }
                break;
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
