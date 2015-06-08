package Controleur;

import Modele.*;
import Vue.Carte;

import java.util.ArrayList;

/**
 * Manager de l'application, utilisé lors de l'affectation d'un incendie
 */
public class Manager {
    /**
     * Affecte un incendie à un robot
     * @param carte Carte de l'application
     * @param g Graphe de l'application
     */
    public void affecterIncendie(Carte carte, Graphe g) {
        if (carte==null) {
            throw new NullPointerException("Carte null");
        }
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
        Parcours pcc = new ParcoursLargeur();
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
                    chemin = pcc.Parcourir(robot, g, incendie);
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
