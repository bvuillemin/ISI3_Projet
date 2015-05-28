package Vue;

import javax.swing.*;
import java.awt.*;

import Modele.Arc;
import Modele.Noeud;
import Modele.Robot;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by benoitvuillemin on 11/05/2015.
 */
public class Carte extends JPanel implements Observer {

    ArrayList<Robot> listRobot;
    ArrayList<Noeud> listNoeud;
    ArrayList<Arc> listArc;

    public Carte() {
        listRobot = new ArrayList<Robot>();
        listNoeud = new ArrayList<Noeud>();
        listArc = new ArrayList<Arc>();
    }

    public void reset() {
        listRobot.clear();
        listNoeud.clear();
        listArc.clear();
    }

    public void addRobot(Robot robot) {
        listRobot.add(robot);
        this.repaint();
    }

    public void addNoeud(Noeud noeud) {
        listNoeud.add(noeud);
        this.repaint();
    }

    public void addArc(Arc arc) {
        listArc.add(arc);
        this.repaint();
    }

    public void paintComponent(Graphics g){
        //Vous verrez cette phrase chaque fois que la méthode sera invoquée
        System.out.println("Je suis exécutée !");
        for (Noeud n : listNoeud) {
            if (n.getIntensite()==0) {
                g.setColor(Color.BLACK);
                g.drawOval((int) n.getX() - 5, (int) n.getY() - 5, 10, 10);
                g.drawOval((int) n.getX() - 5, (int) n.getY() - 5, 11, 11);
            } else {
                g.setColor(Color.RED);
                g.drawOval((int) n.getX() - 5, (int) n.getY() - 5, 10, 10);
                g.drawOval((int) n.getX() - 5, (int) n.getY() - 5, 11, 11);
                g.drawOval((int) n.getX() - 5, (int) n.getY() - 5, 12, 12);
            }
        }
        for (Arc a : listArc) {
            g.drawLine((int)a.getNoeud1().getX(),(int)a.getNoeud1().getY(),(int)a.getNoeud2().getX(),(int)a.getNoeud2().getY());
        }
    }

    public void update(Observable o, Object arg) {
        this.repaint();
    }
}
