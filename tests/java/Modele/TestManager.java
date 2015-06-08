/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import Controleur.Manager;
import Vue.Carte;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;


public class TestManager {

    Manager m;

    Graphe g;

    Carte c = new Carte();

    Noeud n1 = new Noeud(0.0,1.0,TypeNoeud.NORMAL,c);
    Noeud n2 = new Noeud(7.0,3.0,TypeNoeud.INCENDIE,c);
    Noeud n4 = new Noeud(5.0,2.0,TypeNoeud.INCENDIE,c);

    Arc a4 = new Arc(n4,n1,TypeArc.PLAT,c);

    Robot r1 = new RobotToutTerrain(n1,c,g);

    ArrayList<Noeud> listNoeud = new ArrayList<Noeud>();
    ArrayList<Arc> listArc = new ArrayList<Arc>();
    /**
     * Test pour l'ensemble des fonctions de la classe Graphe
     */
    public TestManager() {
        listArc.add(a4);
        listNoeud.add(n1);
        listNoeud.add(n2);
        listNoeud.add(n4);
        g = new Graphe(listArc,listNoeud);
        c.addArc(a4);
        c.addNoeud(n1);
        c.addNoeud(n2);
        c.addNoeud(n4);
        m = new Manager();
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

    @Test(expected = NullPointerException.class)
    public void testCarteNull() {
        m.affecterIncendie(null,g);
    }

    @Test(expected = NullPointerException.class)
    public void testGrapheNull() {
        c.addRobot(r1);
        m.affecterIncendie(c,null);
    }

    @Test
    public void testIncendieInatteignable() {
        c.addRobot(r1);
        m.affecterIncendie(c,g);
    }

    @Test
    public void testManqueRobot() {
        m.affecterIncendie(c,g);
    }
}
