package Modele;

import java.util.*;

/**
 * Parcours en largeur, utilisé pour trouver le plus court chemin lors du déplacement des robots
 */
public class ParcoursLargeur extends Parcours {

    /**
     * Retourne l'index 0, pour utiliser le premier de la liste.
     * @return index choisi
     */
    protected int choixIndex() {
        return 0;
    }

    /**
     * Parcourt les enfants du noeud dans l'ordre.
     * approcher
     * @param n Noeud à développer
     */
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

}
