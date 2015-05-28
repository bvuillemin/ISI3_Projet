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
    Image background = null;
    boolean customBackground = false;

    public void setBackground(Image background) {
        this.background = background;
        customBackground = true;
        this.repaint();
    }

    public Carte() {
        listRobot = new ArrayList<Robot>();
        listNoeud = new ArrayList<Noeud>();
        listArc = new ArrayList<Arc>();
    }

    public void removeBackground() {
        background = null;
        customBackground = false;
        this.repaint();
    }

    public void reset() {
        listRobot.clear();
        listNoeud.clear();
        listArc.clear();
        background = null;
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

    public void paintComponent(Graphics g) {
        if (customBackground) {
            Float ratio;
            if (background.getWidth(null) > ((float) this.getWidth() /
                    (float) this.getHeight() * (float) background.getHeight(null))) {
                ratio = (float) background.getWidth(null) / (float) this.getWidth();
            } else {
                ratio = (float) background.getHeight(null) / (float) this.getHeight();
            }
            g.drawImage(background, 0, 0, Math.round((float) background.getWidth(null) / ratio),
                    Math.round((float) background.getHeight(null) / ratio), null);
        }
        for (Noeud n : listNoeud) {
            if (n.getIntensite() == 0) {
                g.drawOval((int) n.getX() - 5, (int) n.getY() - 5, 10, 10);
                g.drawOval((int) n.getX() - 5, (int) n.getY() - 5, 11, 11);
            } else {
                g.setColor(Color.RED);
                g.drawOval((int) n.getX() - 5, (int) n.getY() - 5, 10, 10);
                g.drawOval((int) n.getX() - 5, (int) n.getY() - 5, 11, 11);
                g.drawOval((int) n.getX() - 5, (int) n.getY() - 5, 12, 12);
                g.setColor(Color.BLACK);
            }
        }
        for (Arc a : listArc) {
            g.drawLine((int) a.getNoeud1().getX(), (int) a.getNoeud1().getY(), (int) a.getNoeud2().getX(), (int) a.getNoeud2().getY());
        }
        for (Robot r : listRobot) {
            g.fillRoundRect((int) r.getNoeudActuel().getX() - 10, (int) r.getNoeudActuel().getY() - 10, 10, 10, 0, 0);
        }
    }

    public void update(Observable o, Object arg) {
        this.repaint();
    }
}
