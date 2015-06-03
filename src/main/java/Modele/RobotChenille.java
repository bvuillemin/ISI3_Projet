package Modele;

import Vue.Carte;

import java.util.ArrayList;

public class RobotChenille extends Robot {

    public RobotChenille(Noeud noeudActuel, Carte c, Graphe g) {
        super(noeudActuel, c, g);
        listTypeArcTraversable=new ArrayList<TypeArc>();
        listTypeArcTraversable.add(TypeArc.INNONDE);
        listTypeArcTraversable.add(TypeArc.PLAT);
    }
}
