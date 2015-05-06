package Modele;

import java.util.ArrayList;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public class RobotToutTerrain extends Robot {

    public RobotToutTerrain(Noeud noeudActuel) {
        super(noeudActuel);
        listTypeArcTraversable=new ArrayList<typeArc>();
        listTypeArcTraversable.add(typeArc.ESCARPE);
        listTypeArcTraversable.add(typeArc.INONDE);
        listTypeArcTraversable.add(typeArc.PLAT);
    }
}
