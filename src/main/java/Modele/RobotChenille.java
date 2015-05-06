package Modele;

import java.util.ArrayList;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public class RobotChenille extends Robot {

    public RobotChenille(Noeud noeudActuel) {
        super(noeudActuel);
        listTypeArcTraversable=new ArrayList<typeArc>();
        listTypeArcTraversable.add(typeArc.INONDE);
        listTypeArcTraversable.add(typeArc.PLAT);
    }
}
