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

import static org.junit.Assert.*;

/**
 *
 * @author thomascoquan
 */
public class TestRobot {

    private Robot r1;
    private Robot r2;
    private Noeud noeudDepartR1 = new Noeud(0, 0.0, 0.0, TypeNoeud.NORMAL);
    private Noeud noeud2R1 = new Noeud(0, 0.0, 1.0, TypeNoeud.NORMAL);
    private Noeud noeud3R1 = new Noeud(0, 0.0, 2.0, TypeNoeud.NORMAL);
    private Noeud newNoeudDepartR1 = new Noeud(0, 1.0, 0.0, TypeNoeud.NORMAL);

    private ArrayList<Arc> cheminR1 = new ArrayList<Arc>();
    public TestRobot() {
        r1 = new Robot(noeudDepartR1);
        cheminR1.add(new Arc(noeudDepartR1,noeud2R1,1,TypeArc.PLAT));
        cheminR1.add(new Arc(noeud2R1,noeud3R1,1,TypeArc.ESCARPE));
        cheminR1.add(new Arc(noeud3R1,newNoeudDepartR1,2,TypeArc.INNONDE));
        r2 = new Robot(noeudDepartR1);
        r2.setChemin(cheminR1);
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
    public void testGetChemin() {
        assertEquals(null,r1.getChemin());
        assertEquals(cheminR1,r2.getChemin());
    }

    @Test
    public void testSetNoeudActuel() {
        r1.setNoeudActuel(newNoeudDepartR1);
        r2.setNoeudActuel(null);
    }

    @Test
    public void testSetChemin() {
        r1.setChemin(cheminR1);
        r2.setChemin(null);
    }

    @Test
    public void testRun() {
        r1.run();
        r2.run();
    }
}
