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

    private Robot r0;
    private Robot r1;
    private Robot r2;
    private Robot r3;
    private Noeud noeudDepartR1 = new Noeud(0, 0.0, 0.0, TypeNoeud.NORMAL);
    private Noeud noeud2R1 = new Noeud(0, 0.0, 1.0, TypeNoeud.NORMAL);
    private Noeud noeud3R1 = new Noeud(0, 0.0, 2.0, TypeNoeud.NORMAL);
    private Noeud newNoeudDepartR1 = new Noeud(0, 1.0, 0.0, TypeNoeud.NORMAL);
    private Noeud noeudDepartR3 = new Noeud(0, 0.0, 0.0, TypeNoeud.INCENDIE);

    private ArrayList<Arc> cheminR1 = new ArrayList<Arc>();
    private ArrayList<Arc> cheminPlat = new ArrayList<Arc>();
    public TestRobot() {
        r0 = new Robot(null);
        r1 = new Robot(noeudDepartR1);
        cheminR1.add(new Arc(noeudDepartR1,noeud2R1,TypeArc.PLAT));
        cheminR1.add(new Arc(noeud2R1,noeud3R1,TypeArc.ESCARPE));
        cheminR1.add(new Arc(noeud3R1,newNoeudDepartR1,TypeArc.INNONDE));
        r2 = new Robot(noeudDepartR1);
        cheminPlat.add(new Arc(noeudDepartR1,noeud2R1,TypeArc.PLAT));
        r2.setChemin(cheminPlat);
        r3 = new Robot(noeudDepartR3);
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
        System.out.println("Test GetChemin 1");
        assertEquals(null, r1.getChemin());
        System.out.println("Test GetChemin 2");
        assertEquals(cheminPlat,r2.getChemin());
    }

    @Test
    public void testSetNoeudActuel() {
        System.out.println("Test SetNoeudActuel 1");
        r1.setNoeudActuel(newNoeudDepartR1);
        System.out.println("Test SetNoeudActuel 2");
        r2.setNoeudActuel(null);
    }

    @Test
    public void testSetChemin() {
        System.out.println("Test SetChemin 1");
        r1.setChemin(cheminR1);
        System.out.println("Test SetChemin 2");
        r2.setChemin(null);
    }

    @Test
    public void testRun() {
        System.out.println("Test Run 0");
        r0.run();
        System.out.println("Test Run 1");
        r1.run();
        System.out.println("Test Run 2");
        r2.run();
        System.out.println("Test Run 3");
        r3.run();
    }
}
