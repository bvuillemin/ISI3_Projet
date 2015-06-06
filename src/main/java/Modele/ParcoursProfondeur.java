package Modele;

import java.util.*;

public class ParcoursProfondeur extends Parcours {

    /*public ArrayList<Arc> Parcourir(Robot robot, Graphe graph, Noeud goal){
        super.init(robot, graph, goal);
        Noeud nTemp;
        ArrayList<Arc> listArc_voisin;
        while (!listNoeud.isEmpty()){
            actuel = listNoeud.removeFirst();
            if (actuel != goal) {
                listArc_voisin=voisins(actuel);
                for (Arc e : listArc_voisin) {
                    nTemp = deuxiemeNoeudArc(e,actuel);
                    if (!listMarque.containsKey(nTemp)) {
                        cheminAmeliore(nTemp,e);
                    } else if (listMarque.get(nTemp) > (e.getLongueur() + listMarque.get(actuel))) {
                        listMarque.remove(nTemp);
                        listArcOpti.remove(nTemp);
                        cheminAmeliore(nTemp,e);
                    }
                }
                listArc_voisin.clear();
            }
        }
        return listArcOpti.get(goal);
    }*/

    /*

    DFS (graphe G, sommet s)
{
  Marquer(s);
  POUR CHAQUE élément s_fils de Voisins(s) FAIRE
     SI NonMarqué(s_fils) ALORS
       DFS(G,s_fils);
     FIN-SI
  FIN-POUR
}
     */

    public ArrayList<Arc> Parcourir(Robot robot, Graphe graph, Noeud goal) {

        if (actuel != goal){

        }
        return listArcOpti.get(goal);
    }
}

