package Modele;

import java.util.ArrayList;

/**
 * Created by benoitvuillemin on 06/05/2015.
 */
public class Graphe {
    private ArrayList<Arc> liste_arcs;
    private ArrayList<Noeud> liste_noeud;

    public Graphe(ArrayList<Arc> liste_arcs, ArrayList<Noeud> liste_noeud) {
        this.liste_arcs = liste_arcs;
        this.liste_noeud = liste_noeud;
    }

    public ArrayList<Arc> getListe_arcs() {
        return liste_arcs;
    }

    public void setListe_arcs(ArrayList<Arc> liste_arcs) {
        this.liste_arcs = liste_arcs;
    }

    public ArrayList<Noeud> getListe_noeud() {
        return liste_noeud;
    }

    public void setListe_noeud(ArrayList<Noeud> liste_noeud) {
        this.liste_noeud = liste_noeud;
    }
}
