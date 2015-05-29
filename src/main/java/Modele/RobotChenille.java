package Modele;

import Vue.Carte;

import java.util.ArrayList;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public class RobotChenille extends Robot {

    public RobotChenille(Noeud noeudActuel, Carte c) {
        super(noeudActuel, c);
        listTypeArcTraversable=new ArrayList<TypeArc>();
        listTypeArcTraversable.add(TypeArc.INNONDE);
        listTypeArcTraversable.add(TypeArc.PLAT);
    }
}
