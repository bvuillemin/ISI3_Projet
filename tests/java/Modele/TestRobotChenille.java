/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import Vue.Carte;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Teste la classe RobotChenille
 */
public class TestRobotChenille{

    RobotChenille rc1;
    private Noeud noeudDepartRP1 = new Noeud(0, 0.0, 0.0, TypeNoeud.NORMAL, new Carte());

    public TestRobotChenille() {
        //rc1 = new RobotChenille(noeudDepartRP1);
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

}
