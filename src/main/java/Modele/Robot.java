package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public class Robot extends Thread{

    protected Noeud noeudActuel;
    protected ArrayList<typeArc> listTypeArcTraversable;
    protected ArrayList<Arc> listArcPlusCourt;
    protected ArrayList<Arc> chemin;
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

    public ArrayList<Arc> getChemin() {
        return chemin;
    }

    public void setNoeudActuel(Noeud noeudActuel) {
        this.noeudActuel = noeudActuel;
    }

    public void setListTypeArcTraversable(ArrayList<typeArc> listTypeArcTraversable) {
        this.listTypeArcTraversable = listTypeArcTraversable;
    }

    public void setChemin(ArrayList<Arc> chemin) {
        this.chemin = chemin;
    }

    public ArrayList<Arc> plusCourtChemin(Noeud noeudDestination){
        dijkstra = new HashMap();
        int num = 0;
        for (int i = 0 ; i < graphe.getListe_noeud().size() ; i++){
            dijkstra.put(graphe.getListe_noeud().indexOf(i),-1);
        }

        return listArcPlusCourt;
    }

    @Override
    public void run() {
        Arc morceauChemin;
        while(chemin.isEmpty()==false) {
            try {
                morceauChemin=chemin.get(0);
                noeudActuel=morceauChemin.getNoeud2();
                chemin.remove(morceauChemin);
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
