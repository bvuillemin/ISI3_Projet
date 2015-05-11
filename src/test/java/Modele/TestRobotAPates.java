/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

/**
 *
 * @author thomascoquan
 */
public class TestRobotAPates {

    RobotAPates rp1;
    private Noeud noeudDepartRP1 = new Noeud(0, 0.0, 0.0, TypeNoeud.NORMAL);
    private Noeud noeud2RP1 = new Noeud(0, 0.0, 1.0, TypeNoeud.INCENDIE);
    private ArrayList<Arc> cheminPlat = new ArrayList<Arc>();

    public TestRobotAPates() {
        rp1 = new RobotAPates(noeudDepartRP1);
        cheminPlat.add(new Arc(noeudDepartRP1,noeud2RP1,TypeArc.PLAT));
        rp1.setChemin(cheminPlat);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void testRun() {
        rp1.run();
    }
}
