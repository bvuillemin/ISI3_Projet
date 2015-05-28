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
    boolean premierClic=true;
    Noeud noeudTmp;

    public void setCarte(Carte carte){
        this.carte=carte;
        this.ip=null;
    }

    public void setIp(InterfacePrincipale ip) {
        this.ip=ip;
    }

    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        g=new Graphe();
        if (c.equals("Nouveau graphe")) {
            carte.setBackground(Color.WHITE);
            Noeud n1 = new Noeud(0,100,100, TypeNoeud.NORMAL,carte);
            g.ajouterNoeud(n1);
            carte.addNoeud(n1);
            Noeud n2 = new Noeud(0,300,100, TypeNoeud.NORMAL,carte);
            g.ajouterNoeud(n2);
            carte.addNoeud(n2);
            Noeud n3 = new Noeud(0,250,200, TypeNoeud.NORMAL,carte);
            g.ajouterNoeud(n3);
            carte.addNoeud(n3);
            Noeud n4 = new Noeud(0,200,140, TypeNoeud.NORMAL,carte);
            g.ajouterNoeud(n4);
            carte.addNoeud(n4);
            Noeud n5 = new Noeud(0,340,120, TypeNoeud.NORMAL,carte);
            g.ajouterNoeud(n5);
            carte.addNoeud(n5);
            Arc a1 = new Arc(n1,n2, TypeArc.PLAT, carte);
            g.ajouterArc(a1);
            carte.addArc(a1);
            Arc a2 = new Arc(n2,n4, TypeArc.PLAT, carte);
            g.ajouterArc(a2);
            carte.addArc(a2);
            Arc a3 = new Arc(n3,n5, TypeArc.PLAT, carte);
            g.ajouterArc(a3);
            carte.addArc(a3);
            Arc a4 = new Arc(n2,n5, TypeArc.PLAT, carte);
            g.ajouterArc(a4);
            carte.addArc(a4);
        }
    }

    public void mouseClicked(MouseEvent e) {
        int typeAjout = ip.typeAjout();
        if (typeAjout==1) {
            System.out.println("Nouveau Noeud");
            Noeud n = new Noeud(0,e.getX(),e.getY(),TypeNoeud.NORMAL,carte);
            g.ajouterNoeud(n);
            carte.addNoeud(n);
        } else if (typeAjout==2) {
            System.out.println("Nouvel Incendie");
            Noeud n = new Noeud(0,e.getX(),e.getY(),TypeNoeud.INCENDIE,carte);
            g.ajouterNoeud(n);
            carte.addNoeud(n);
        } else if (typeAjout==3) {
            System.out.println("Nouvel Arc");
            Noeud n = g.contientAppro(e.getX(),e.getY());
            if (n!=null) {
                if (premierClic != false) {
                    noeudTmp=n;
                    premierClic = false;
                } else {
                    Arc a = new Arc(noeudTmp,n,TypeArc.PLAT,carte);
                    g.ajouterArc(a);
                    carte.addArc(a);
                    premierClic = true;
                }
            }
        } else if (typeAjout==4) {
            System.out.println("Nouveau Robot");
            Noeud n = g.contientAppro(e.getX(),e.getY());
            if (n!=null) {
                Robot r = new RobotAPates(n);
                carte.addRobot(r);
            }
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
