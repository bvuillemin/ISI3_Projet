package Modele;

import Vue.Carte;

import java.util.ArrayList;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public class Manager {

    public void affecterIncendie(Carte carte, Graphe g) {
        ArrayList<Noeud> listIncendies = (ArrayList<Noeud>) carte.getNoeuds().clone();
        Noeud n;
        for (int i=0; i<listIncendies.size(); i++) {
            n=listIncendies.get(i);
            if (n.getIntensite()==0) {
                listIncendies.remove(i);
                i--;
            }
        }
        ArrayList<Robot> listRobots = (ArrayList<Robot>) carte.getRobots().clone();
        PlusCoursChemin pcc = new PlusCoursChemin();
        ArrayList<Arc> chemin;
        double distance;
        double distanceMini=10000.0;
        Robot robotChoisi;
        ArrayList<Arc> cheminChoisi = null;
        for (Noeud incendie : listIncendies) {
            robotChoisi=listRobots.get(0);
            for (Robot robot : listRobots) {
                distance=0.0;
                chemin = pcc.ParcoursLargeur(robot.getNoeudActuel(), g, incendie);
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
