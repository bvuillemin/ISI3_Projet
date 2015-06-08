package Modele;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

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
    ArrayList<Noeud> listExplore;
    /**
     * Liste d'arcs
     */
    ArrayList<Arc> listArc;
    ArrayList<Arc> cheminFounded;
    /**
     * Robot du parcours
     */
    Robot robot;
    /**
     * Liste d'arcs otimisée
     */
    LinkedHashMap<Noeud, ArrayList<Arc>> listArcOpti;
    /**
     * Liste d'arcs marqués
     */
    LinkedHashMap<Noeud, Double> listMarque;

    /**
     * Initialisation du parcours
     *
     * @param robot Robot du parcours
     * @param graph Graphe du parcours
     */
    public void init(Robot robot, Graphe graph) {
        this.robot = robot;
        actuel = robot.noeudActuel;
        listNoeud = new LinkedList<Noeud>();
        listExplore = new ArrayList<Noeud>();
        listArcOpti = new LinkedHashMap<Noeud, ArrayList<Arc>>();
        //listMarque = new LinkedHashMap<Noeud, Double>();
        listNoeud.add(actuel);
        //listMarque.put(actuel, 0.0);
        listArcOpti.put(actuel, new ArrayList<Arc>());
        listArc = graph.getListe_arcs();
        cheminFounded = new ArrayList<Arc>();
    }

    /**
     * Fonction qui permet de parcourir le graphe pour trouver le plus court chemin
     *
     * @param robot Robot du parcours
     * @param graph Graphe du parcours
     * @param goal  But à atteindre du parcours
     * @return liste des Noeuds à parcourir pour atteindre le but
     */
    public ArrayList<Arc> Parcourir(Robot robot, Graphe graph, Noeud goal) {
        init(robot, graph);
        while (true) {
            if (listNoeud.isEmpty()!=false) {
                return null;
            }
            actuel = listNoeud.remove(choixIndex());
            if (actuel==goal) {
                return listArcOpti.get(goal);
            }
            listExplore.add(actuel);
            developper(actuel);
        }
    }

    protected abstract int choixIndex();
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
