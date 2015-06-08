package Modele;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Classe abstraite de parcours de graphe
 */
public abstract class Parcours {
    /**
     * Noeud actuel
     */
    Noeud actuel;
    /**
     * Liste de noeuds
     */
    LinkedList<Noeud> listNoeud;
    /**
     * Liste des noeuds explorés
     */
    ArrayList<Noeud> listExplore;
    /**
     * Liste d'arcs
     */
    ArrayList<Arc> listArc;
    /**
     * Robot du parcours
     */
    Robot robot;
    /**
     * Liste des chemins optimaux
     */
    LinkedHashMap<Noeud, ArrayList<Arc>> listArcOpti;

    /**
     * Initialisation du parcours
     *
     * @param robot Robot du parcours
     * @param graph Graphe du parcours
     */
    public void init(Robot robot, Graphe graph) {
        this.robot = robot;
        actuel=null;
        listNoeud = new LinkedList<Noeud>();
        listExplore = new ArrayList<Noeud>();
        listArcOpti = new LinkedHashMap<Noeud, ArrayList<Arc>>();
        //listMarque = new LinkedHashMap<Noeud, Double>();
        listNoeud.add(robot.noeudActuel);
        //listMarque.put(actuel, 0.0);
        listArcOpti.put(robot.noeudActuel, new ArrayList<Arc>());
        listArc = graph.getListe_arcs();
    }

    /**
     * Fonction qui permet de parcourir le graphe pour trouver le plus court chemin
     *
     * @param robot Robot du parcours
     * @param graph Graphe du parcours
     * @param goal  But à atteindre du parcours
     * @return liste des Arcs à parcourir pour atteindre le but
     */
    public ArrayList<Arc> Parcourir(Robot robot, Graphe graph, Noeud goal) {
        init(robot, graph);
        while (true) {
            if (listNoeud.isEmpty()!=false) {
                return null;
            }
            System.out.println("Noeuds : "+listNoeud);
            actuel = listNoeud.remove(choixIndex());
            if (actuel==goal) {
                return listArcOpti.get(goal);
            }
            listExplore.add(actuel);
            developper(actuel);
        }
    }

    /**
     * Retourne l'index du noeuds suivants à parcourir dans un parcours en fonction du type d'algo
     * @return index choisi
     */
    protected abstract int choixIndex();

    /**
     * Parcourt les enfants du noeud en fonction du type d'algo pour voir si le but ne s'y trouve pas et sinon s'en
     * approcher
     * @param n Noeud à développer
     */
    protected abstract void developper(Noeud n);

    /**
     * Trouve la liste des arcs d'un Noeud
     *
     * @param n Noeud en paramètre
     * @return Liste des arcs du Noeud
     */
    protected ArrayList<Arc> voisins(Noeud n) {
        ArrayList<Arc> listArc_voisin = new ArrayList<Arc>();
        for (Arc arc_voisin : listArc) {
            if (((arc_voisin.getNoeud1() == n) || (arc_voisin.getNoeud2() == n)) && (robot.capablePasser(arc_voisin.getType()) != false)) {
                listArc_voisin.add(arc_voisin);
            }
        }
        return listArc_voisin;
    }

    /**
     * Renvoie le deuxième Noeud d'un Arc passée en paramètre
     *
     * @param a      Arc
     * @param noeud1 Permier Noeud
     * @return Le deuxième Noeud
     */
    protected Noeud deuxiemeNoeudArc(Arc a, Noeud noeud1) {
        if (a.getNoeud1() == noeud1) {
            return a.getNoeud2();
        } else if (a.getNoeud2() == noeud1) {
            return a.getNoeud1();
        }
        return null;
    }
}
