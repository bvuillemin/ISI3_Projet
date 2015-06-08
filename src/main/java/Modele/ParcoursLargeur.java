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
    /*public ArrayList<Arc> Parcourir(Robot robot, Graphe graph, Noeud goal) {
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
    }*/

    protected int choixIndex() {
        return 0;
    }

    protected void developper(Noeud n) {
        ArrayList<Arc> listArc_voisin;
        Noeud nTmp;
        listArc_voisin=voisins(n);
        for (Arc a : listArc_voisin) {
            nTmp = deuxiemeNoeudArc(a,n);
            if ((listNoeud.contains(nTmp)==false)&&(listExplore.contains(nTmp))==false) {
                listNoeud.add(nTmp);
                listArcOpti.put(nTmp, (ArrayList<Arc>) listArcOpti.get(actuel).clone());
                listArcOpti.get(nTmp).add(a);
            }
        }
    }

    protected void cheminAmeliore(Noeud n, Arc a) {
        listNoeud.add(n);
        listMarque.put(n, a.getLongueur() + listMarque.get(actuel));
        listArcOpti.put(n, (ArrayList<Arc>) listArcOpti.get(actuel).clone());
        listArcOpti.get(n).add(a);
    }

}
