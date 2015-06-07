package Modele;

import Vue.Carte;

import java.util.ArrayList;
/**
 * Type de robot précis : robot tout terrain
 */
public class RobotToutTerrain extends Robot {

    public RobotToutTerrain(Noeud noeudActuel, Carte c, Graphe g) {
        super(noeudActuel, c, g);
        listTypeArcTraversable=new ArrayList<TypeArc>();
        listTypeArcTraversable.add(TypeArc.ESCARPE);
        listTypeArcTraversable.add(TypeArc.INNONDE);
        listTypeArcTraversable.add(TypeArc.PLAT);
    }
}
