/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import Vue.Carte;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 *
 * @author thomascoquan
 */
public class TestRobotAPates {

    private Robot r0;
    private Robot r1;
    private Robot r2;
    private Robot r3;

    private Noeud noeudDepartR1 = new Noeud(0, 0.0, 0.0, TypeNoeud.NORMAL, new Carte());
    private Noeud noeud2R1 = new Noeud(0, 0.0, 1.0, TypeNoeud.NORMAL, new Carte());
    private Noeud noeud3R1 = new Noeud(0, 0.0, 2.0, TypeNoeud.NORMAL, new Carte());
    private Noeud newNoeudDepartR1 = new Noeud(0, 1.0, 0.0, TypeNoeud.NORMAL, new Carte());
    private Noeud noeudDepartR3 = new Noeud(0, 0.0, 0.0, TypeNoeud.INCENDIE, new Carte());

    private ArrayList<Arc> cheminR1 = new ArrayList<Arc>();
    private ArrayList<Arc> cheminPlat = new ArrayList<Arc>();

    /**
     * Test pour l'ensemble des fonctions de la classe abstraite Robot via sa classe fille RobotAPattes
     */
    public TestRobotAPates() {
        //r0 = new RobotAPattes(null);
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

    /**
     * Test du run de Robot
     * 1er test : Vérification qu'il n'y a pas de problème si le robot ne posséde pas de position, ni de chemin
     */
    @Test
    public void testRun() {
        r0.run();
    }
}
