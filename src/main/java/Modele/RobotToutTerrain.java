package Modele;

import java.util.ArrayList;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public class RobotToutTerrain extends Robot {

    public RobotToutTerrain(Noeud noeudActuel) {
        super(noeudActuel);
        listTypeArcTraversable=new ArrayList<TypeArc>();
        listTypeArcTraversable.add(TypeArc.ESCARPE);
        listTypeArcTraversable.add(TypeArc.INNONDE);
        listTypeArcTraversable.add(TypeArc.PLAT);
    }
}
