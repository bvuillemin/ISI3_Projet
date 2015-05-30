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
    Noeud noeudSelectionne;
    Image background = null;
    boolean customBackground = false;

    public ArrayList<Noeud> getNoeuds() {
        return listNoeud;
    }

    public ArrayList<Robot> getRobots() {
        return listRobot;
    }

    public void setBackground(Image background) {
        this.background = background;
        customBackground = true;
        this.repaint();
    }

    public void setNoeudSelectionne(Noeud noeudSelectionne) {
        this.noeudSelectionne = noeudSelectionne;
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
        this.repaint();
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

            //Ce code permettait d'adapter les dimensions de l'image à l'interface. Supprimé pour que l'exemple marche
            //Calcule les nouvelles dimensions de l'image de fond
            /*Float ratio;
            if (background.getWidth(null) > ((float) this.getWidth() /
                    (float) this.getHeight() * (float) background.getHeight(null))) {
                ratio = (float) background.getWidth(null) / (float) this.getWidth();
            } else {
                ratio = (float) background.getHeight(null) / (float) this.getHeight();
            }
            int newWidth = Math.round((float) background.getWidth(null) / ratio);
            int newHeight = Math.round((float) background.getHeight(null) / ratio);
            //Réduit les dimensions de l'image et la centre
            g.drawImage(background, (this.getWidth() - newWidth) / 2,
                    (this.getHeight() - newHeight) / 2, newWidth, newHeight, null);*/

            g.drawImage(background, 0, 0, background.getWidth(null), background.getHeight(null), null);
        }
        for (Noeud n : listNoeud) {
            if (n.getIntensite() == 0) {
                if(n.equals(noeudSelectionne)){
                    g.setColor(Color.GREEN);
                }
                else{
                    g.setColor(Color.BLACK);
                }
                g.drawOval((int) n.getX() - 15, (int) n.getY() - 15, 10, 10);
                g.drawOval((int) n.getX() - 15, (int) n.getY() - 15, 11, 11);
            } else {
                if(n.equals(noeudSelectionne)){
                    g.setColor(Color.GREEN);
                }
                else{
                    g.setColor(Color.RED);
                }
                g.drawOval((int) n.getX() - 15, (int) n.getY() - 15, 10, 10);
                g.drawOval((int) n.getX() - 15, (int) n.getY() - 15, 11, 11);
                g.drawOval((int) n.getX() - 15, (int) n.getY() - 15, 12, 12);
            }
        }
        for (Arc a : listArc) {
            g.setColor(Color.BLACK);
            g.drawLine((int) a.getNoeud1().getX() - 10, (int) a.getNoeud1().getY() - 10, (int) a.getNoeud2().getX() - 10, (int) a.getNoeud2().getY() - 10);
        }
        for (Robot r : listRobot) {
            g.setColor(Color.BLACK);
            g.fillRoundRect((int) r.getNoeudActuel().getX() - 20, (int) r.getNoeudActuel().getY() - 20, 10, 10, 0, 0);
        }
    }

    public void update(Observable o, Object arg) {
        System.out.println("test");
        this.repaint();
    }
}
