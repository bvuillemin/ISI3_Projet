package Modele;

import java.util.ArrayList;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public class Manager {

    ArrayList<Robot> listRobots;
    ArrayList<Noeud> listIncendies;
    Graphe g;

    public Manager(Graphe g) {
        this.listRobots = null;
        this.listIncendies = null;
        this.g=g;
    }

    public ArrayList<Robot> getListRobots() {
        return listRobots;
    }

    public ArrayList<Noeud> getListNoeudsIncendies() {
        return listIncendies;
    }

    public void setListRobots(ArrayList<Robot> listRobots) {
        this.listRobots = listRobots;
    }

    public void setListNoeudsIncendies(ArrayList<Noeud> listNoeudsIncendies) {
        this.listIncendies = listNoeudsIncendies;
    }

    public void addRobot(Robot newRobot) {
        listRobots.add(newRobot);
    }

    public void addIncendie(Noeud newIncendie) {
        listIncendies.add(newIncendie);
    }

    public void removeRobot(Robot oldRobot) {
        listRobots.remove(oldRobot);
    }

    public void removeIncendie(Noeud oldIncendie) {
        listIncendies.remove(oldIncendie);
    }

    public void affecterIncendie() {
        PlusCoursChemin pcc = new PlusCoursChemin();
        ArrayList<Arc> chemin = new ArrayList<Arc>();
        double distance;
        double distanceMini=10000.0;
        Robot robotChoisi;
        ArrayList<Arc> cheminChoisi = null;
        for (Noeud incendie : listIncendies) {
            robotChoisi=listRobots.get(0);
            for (Robot robot : listRobots) {
                distance=0.0;
                chemin = pcc.ParcoursLargeur(robot.getNoeudActuel(),g);
                for (Arc arc : chemin) {
                    distance+=arc.getLongueur();
                }
                if (distance<distanceMini) {
                    distanceMini=distance;
                    robotChoisi=robot;
                    cheminChoisi=chemin;
                }
            }
            robotChoisi.setChemin(cheminChoisi);
            new Thread(robotChoisi).start();
            listRobots.remove(robotChoisi);
        }
    }
}
