package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Dorian on 24/05/2015.
 */
public class PlusCoursChemin {

    Noeud actuel;
    LinkedList<Noeud> listNoeud;
    ArrayList<Arc> listArc;

    public PlusCoursChemin (){

    }

    public ArrayList<Arc> ParcoursLargeur(Noeud position, Graphe graph){
        listArc = graph.getListe_arcs();
        actuel = position;
        listNoeud = new LinkedList<Noeud>();
        ArrayList<Arc> listArc_voisin = new ArrayList<Arc>();
        ArrayList<Noeud> listMarque = new ArrayList<Noeud>();
        listNoeud.add(actuel);
        listMarque.add(actuel);
        while (!listNoeud.isEmpty()){
            actuel = listNoeud.removeFirst();
            //afficher(s) ??
            //on récupère la liste des voisins
            for (Arc arc_voisin : listArc){
                //noeud1 est source
                if (arc_voisin.getNoeud1() == actuel) {
                    listArc_voisin.add(arc_voisin);
                }
            }
            for (Arc e : listArc_voisin ){
                //on regarde si l'element est marqué - noeud2 est tjrs destination
                if (!listMarque.contains(e.getNoeud2())){
                    listNoeud.add(e.getNoeud2());
                    listMarque.add(e.getNoeud2());
                }
            }

        }
        return null;
    }

}
