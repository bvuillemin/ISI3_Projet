package Vue;

import javax.swing.*;
import java.awt.*;

import Modele.*;
import Modele.Robot;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Carte extends JPanel implements Observer {
    /**
     * Liste des robots de la carte
     */
    ArrayList<Robot> listRobot;
    /**
     * Liste des noeuds de la carte
     */
    ArrayList<Noeud> listNoeud;
    /**
     * Liste des arcs de la carte
     */
    ArrayList<Arc> listArc;
    /**
     * Permier noeud sélectionné lors de la création d'un arc
     */
    Noeud noeudSelectionne;
    /**
     * Image de fond
     */
    Image background = null;
    /**
     * Image d'un robot à pattes, affiché sur l'interface
     */
    ImageIcon rpattes = new ImageIcon("src/main/resources/rpattes.png");
    /**
     * Image d'un robot chenille, affiché sur l'interface
     */
    ImageIcon rchenille = new ImageIcon("src/main/resources/rchenille.png");
    /**
     * Image d'un robot tout terrain, affiché sur l'interface
     */
    ImageIcon rtt = new ImageIcon("src/main/resources/rtt.png");
    /**
     * Image d'un incendie, affiché sur l'interface
     */
    ImageIcon incendie = new ImageIcon("src/main/resources/incendie.png");

    public ArrayList<Noeud> getNoeuds() {
        return listNoeud;
    }

    public ArrayList<Robot> getRobots() {
        return listRobot;
    }

    /**
     * Définit l'image de fond de l'interface
     *
     * @param background Image à mettre en fond
     */
    public void setBackground(Image background) {
        this.background = background;
        this.repaint();
    }

    /**
     * Définit le premier noeud sélectionné lors de la création d'un arc (il devient vert)
     *
     * @param noeudSelectionne noeud à définir comme sélectionné
     */
    public void setNoeudSelectionne(Noeud noeudSelectionne) {
        this.noeudSelectionne = noeudSelectionne;
        this.repaint();
    }

    public Carte() {
        listRobot = new ArrayList<Robot>();
        listNoeud = new ArrayList<Noeud>();
        listArc = new ArrayList<Arc>();
    }

    /**
     * Supprime l'image de fond de l'interface
     */
    public void removeBackground() {
        background = null;
        this.repaint();
    }

    /**
     * Remet à zéro le graphe de la carte
     */
    public void reset() {
        listRobot.clear();
        listNoeud.clear();
        listArc.clear();
        this.repaint();
    }

    /**
     * Ajoute un robot à la carte
     *
     * @param robot Robot à ajouter
     */
    public void addRobot(Robot robot) {
        listRobot.add(robot);
        this.repaint();
    }

    /**
     * Ajoute un noeud à la carte
     *
     * @param noeud Noeud à ajouter
     */
    public void addNoeud(Noeud noeud) {
        listNoeud.add(noeud);
        this.repaint();
    }

    /**
     * Ajoute un arc à la carte
     *
     * @param arc Arc à ajouter
     */
    public void addArc(Arc arc) {
        listArc.add(arc);
        this.repaint();
    }

    /**
     * Définit les graphismes de la carte
     *
     * @param g Graphiques
     */
    public void paintComponent(Graphics g) {
        if (background != null) {
            g.drawImage(background, 0, 0, background.getWidth(null), background.getHeight(null), null);
        }
        for (Noeud n : listNoeud) {
            if (n.getIntensite() == 0) {
                if (n.equals(noeudSelectionne)) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.drawOval((int) n.getX() - 15, (int) n.getY() - 15, 10, 10);
                g.drawOval((int) n.getX() - 15, (int) n.getY() - 15, 11, 11);
            } else {
                if (n.equals(noeudSelectionne)) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.RED);
                }
                int taille = n.getIntensite() * 10;
                g.drawImage(incendie.getImage(), (int) n.getX() - taille / 2 - 10, (int) n.getY() - taille / 2 - 10,
                        taille, taille, null);
                g.drawOval((int) n.getX() - 15, (int) n.getY() - 15, 10, 10);
                g.drawOval((int) n.getX() - 15, (int) n.getY() - 15, 11, 11);
                g.drawOval((int) n.getX() - 15, (int) n.getY() - 15, 12, 12);
            }
        }
        for (Arc a : listArc) {
            switch (a.getType()) {
                case ESCARPE:
                    g.setColor(Color.ORANGE);
                    break;
                case INNONDE:
                    g.setColor(Color.BLUE);
                    break;
                default:
                    g.setColor(Color.BLACK);
                    break;
            }
            g.drawLine((int) a.getNoeud1().getX() - 10, (int) a.getNoeud1().getY() - 10,
                    (int) a.getNoeud2().getX() - 10, (int) a.getNoeud2().getY() - 10);
        }
        for (Robot r : listRobot) {
            if (r instanceof RobotAPates) {
                g.drawImage(rpattes.getImage(), (int) r.getNoeudActuel().getX() - 10, (int) r.getNoeudActuel().getY() - 10,
                        20, 20, null);
            } else if (r instanceof RobotChenille) {
                g.drawImage(rchenille.getImage(), (int) r.getNoeudActuel().getX() - 10, (int) r.getNoeudActuel().getY() - 10,
                        20, 20, null);
            } else {
                g.drawImage(rtt.getImage(), (int) r.getNoeudActuel().getX() - 10, (int) r.getNoeudActuel().getY() - 10,
                        20, 20, null);
            }
        }
    }

    /**
     * Mise à jour des graphismes de la carte
     *
     * @param o   Observable
     * @param arg Appelé lors de l'update
     */
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}
