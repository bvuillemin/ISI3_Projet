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
        double distanceMini;
        Robot robotChoisi;
        ArrayList<Arc> cheminChoisi = null;
        for (Noeud incendie : listIncendies) {
            if (listRobots.isEmpty()==false) {
                distanceMini=Double.MAX_VALUE;
                robotChoisi=null;
                for (Robot robot : listRobots) {
                    distance = 0.0;
                    chemin = pcc.ParcoursLargeur(robot, g, incendie);
                    if (chemin != null) {
                        for (Arc arc : chemin) {
                            distance += arc.getLongueur();
                        }
                        if (distance < distanceMini) {
                            distanceMini = distance;
                            robotChoisi = robot;
                            cheminChoisi = chemin;
                        }
                    }
                }
                if (robotChoisi!=null) {
                    robotChoisi.setChemin(cheminChoisi);
                    new Thread(robotChoisi).start();
                    listRobots.remove(robotChoisi);
                } else {
                    System.out.println("Pas de robot disponible pour cet incendie");
                }
            } else {
                return;
            }
        }
    }
}
