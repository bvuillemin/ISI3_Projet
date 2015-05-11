package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public class Robot {

    protected Noeud noeudActuel;
    protected ArrayList<TypeArc> listTypeArcTraversable;
    protected ArrayList<Arc> listArcPlusCourt;
    protected Graphe graphe;
    protected Map dijkstra;


    public Robot(Noeud noeudActuel) {
        this.noeudActuel = noeudActuel;
    }

    public Noeud getNoeudActuel() {
        return noeudActuel;
    }

    public ArrayList<TypeArc> getListTypeArcTraversable() {
        return listTypeArcTraversable;
    }

    public void setNoeudActuel(Noeud noeudActuel) {
        this.noeudActuel = noeudActuel;
    }

    public void setListTypeArcTraversable(ArrayList<TypeArc> listTypeArcTraversable) {
        this.listTypeArcTraversable = listTypeArcTraversable;
    }

    public ArrayList<Arc> plusCourtChemin(Noeud noeudDestination){
        dijkstra = new HashMap();
        int num = 0;
        for (int i = 0 ; i < graphe.getListe_noeud().size() ; i++){
            dijkstra.put(graphe.getListe_noeud().indexOf(i),-1);
        }

        return listArcPlusCourt;
    }

    public void aller (Noeud noeudDestination) {

    }
}
