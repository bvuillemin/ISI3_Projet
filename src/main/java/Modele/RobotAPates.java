package Modele;

import Vue.Carte;

import java.util.ArrayList;

public class RobotAPates extends Robot {

    public RobotAPates(Noeud noeudActuel, Carte c, Graphe g) {
        super(noeudActuel, c, g);
        listTypeArcTraversable=new ArrayList<TypeArc>();
        listTypeArcTraversable.add(TypeArc.ESCARPE);
        listTypeArcTraversable.add(TypeArc.PLAT);
    }
}
