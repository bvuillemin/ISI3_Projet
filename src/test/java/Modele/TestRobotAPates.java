/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author thomascoquan
 */
public class TestRobotAPates extends TestRobot{

    RobotAPates rp1;
    private Noeud noeudDepartRP1 = new Noeud(0, 0.0, 0.0, TypeNoeud.NORMAL);

    public TestRobotAPates() {
        rp1 = new RobotAPates(noeudDepartRP1);
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
