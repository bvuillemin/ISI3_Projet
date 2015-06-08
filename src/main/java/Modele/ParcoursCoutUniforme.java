package Modele;

import java.util.*;

public class ParcoursCoutUniforme extends Parcours {

    /**
     * Liste d'arcs marqués
     */
    LinkedHashMap<Noeud, Double> listMarque;

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

    /**
     * Initialisation du parcours
     *
     * @param robot Robot du parcours
     * @param graph Graphe du parcours
     */
    public void init(Robot robot, Graphe graph) {
        super.init(robot, graph);
        listMarque = new LinkedHashMap<Noeud, Double>();
        listMarque.put(robot.getNoeudActuel(), 0.0);
    }

    /**
     * Retourne l'index du noeuds suivants se trouvant le plus proche.
     * @return index choisi
     */
    protected int choixIndex() {
        if (actuel!=null) {
            ArrayList<Arc> voisins = voisins(actuel);
            int i = 0;
            while ((i < voisins.size()) && (listNoeud.contains(deuxiemeNoeudArc(voisins.get(i), actuel)) == false)) {
                i++;
            }
            if (i == voisins.size()) {
                return -1;
            }
            double distanceMin = voisins.get(i).getLongueur();
            Noeud nSuiv = deuxiemeNoeudArc(voisins.get(i), actuel);
            for (Arc a : voisins) {
                if ((distanceMin > a.getLongueur()) && (listNoeud.contains(deuxiemeNoeudArc(a, actuel)))) {
                    distanceMin = a.getLongueur();
                    nSuiv = deuxiemeNoeudArc(a, actuel);
                }
            }
            return listNoeud.indexOf(nSuiv);
        } else {
            return 0;
        }
    }

    /**
     * Parcourt les enfants du noeud dans l'ordre mais met à jour la distance et le meilleur chemin si on en trouve un
     * meilleur
     * @param n Noeud à développer
     */
    protected void developper(Noeud n) {
        ArrayList<Arc> listArc_voisin;
        Noeud nTmp;
        listArc_voisin=voisins(n);
        for (Arc a : listArc_voisin) {
            nTmp = deuxiemeNoeudArc(a,n);
            if ((listNoeud.contains(nTmp)==false)&&(listExplore.contains(nTmp)) ==false) {
                listNoeud.add(nTmp);
                listArcOpti.put(nTmp, (ArrayList<Arc>) listArcOpti.get(actuel).clone());
                listArcOpti.get(nTmp).add(a);
                listMarque.put(nTmp, listMarque.get(actuel)+a.getLongueur());
            } else if ((listNoeud.contains(nTmp)!=false)&&(listMarque.get(nTmp)>listMarque.get(actuel)+a.getLongueur())) {
                listArcOpti.remove(nTmp);
                listArcOpti.put(nTmp, (ArrayList<Arc>) listArcOpti.get(actuel).clone());
                listArcOpti.get(nTmp).add(a);
                listMarque.replace(nTmp,listMarque.get(actuel)+a.getLongueur());
            }
        }
    }
}

