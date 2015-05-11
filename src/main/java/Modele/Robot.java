package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public class Robot {

    protected Noeud noeudActuel;
    protected Noeud noeudDestination;
    protected ArrayList<typeArc> listTypeArcTraversable;
    protected ArrayList<Arc> listArcPlusCourt;
    protected Graphe graphe;
    protected Map dijkstra;


    public Robot(Noeud noeudActuel) {
        this.noeudActuel = noeudActuel;
    }

    public Noeud getNoeudActuel() {
        return noeudActuel;
    }

    public ArrayList<typeArc> getListTypeArcTraversable() {
        return listTypeArcTraversable;
    }

    public Noeud getNoeudDestination() {
        return noeudDestination;
    }

    public void setNoeudActuel(Noeud noeudActuel) {
        this.noeudActuel = noeudActuel;
    }

    public void setListTypeArcTraversable(ArrayList<typeArc> listTypeArcTraversable) {
        this.listTypeArcTraversable = listTypeArcTraversable;
    }

    public void setNoeudDestination(Noeud noeudDestination) {
        this.noeudDestination = noeudDestination;
    }

    public ArrayList<Arc> plusCourtChemin(Noeud noeudDestination){
        Map<Noeud,Integer> dijkstra = new HashMap<Noeud,Integer>();
        ArrayList<Arc> listArcPlusCourt;
        ArrayList<Noeud> listNoeudsNonVu;
        int num = 0;
        Noeud noeudParcouru;

        for (int i = 0 ; i < graphe.getListe_noeud().size() ; i++){
            dijkstra.put(graphe.getListe_noeud().get(i),-1);
        }
        listNoeudsNonVu = graphe.getListe_noeud();
        while (listNoeudsNonVu.isEmpty()){ //prévoir éventuellement une valeur d'arret lorsque on est sur qu'on sortira pas un chemin plus court pour la destination
            int min = 999999999;
            for (HashMap.Entry<Noeud,Integer> varParcours : dijkstra.entrySet()){ // on récupère la plus petite valeur dans les noeuds pas encore vu
                Integer minLocal = varParcours.getValue();
                if (minLocal < min){
                    min = minLocal;
                    noeudParcouru = varParcours.getKey();
                }
            }

        }
        return null; //listArcPlusCourt;
    }
}
