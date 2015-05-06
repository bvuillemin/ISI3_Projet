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
    private int i;

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

    public ArrayList<Arc> plusCourtChemin(Noeud noeudActuel, Noeud noeudDestination){
        dijkstra = new HashMap();
        //for (i : graphe.getListe_noeud().indexOf()){
            dijkstra.put(i,-1);
        //}

        return listArcPlusCourt;
    }
}
