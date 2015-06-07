package Modele;

import java.util.*;

/**
 * Parcours en largeur, utilisé pour trouver le plus court chemin lors du déplacement des robots
 */
public class ParcoursLargeur extends Parcours {

    /**
     * Fonction qui permet de parcourir le graphe pour trouver le plus court chemin
     *
     * @param robot Robot du parcours
     * @param graph Graphe du parcours
     * @param goal  But à atteindre du parcours
     * @return liste des Noeuds à parcourir pour atteindre le but
     */
    public ArrayList<Arc> Parcourir(Robot robot, Graphe graph, Noeud goal) {
        super.init(robot, graph, goal);
        Noeud nTemp;
        ArrayList<Arc> listArc_voisin;
        while (!listNoeud.isEmpty()) {
            actuel = listNoeud.removeFirst();
            if (actuel != goal) {
                listArc_voisin = voisins(actuel);
                for (Arc e : listArc_voisin) {
                    nTemp = deuxiemeNoeudArc(e, actuel);
                    if (!listMarque.containsKey(nTemp)) {
                        cheminAmeliore(nTemp, e);
                    } else if (listMarque.get(nTemp) > (e.getLongueur() + listMarque.get(actuel))) {
                        listMarque.remove(nTemp);
                        listArcOpti.remove(nTemp);
                        cheminAmeliore(nTemp, e);
                    }
                }
                listArc_voisin.clear();
            }
        }
        return listArcOpti.get(goal);
    }


}
