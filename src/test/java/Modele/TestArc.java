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

import static org.junit.Assert.*;

/**
 *
 * @author thomascoquan
 */
public class TestArc {

    Carte c = new Carte();
    Noeud n1 = new Noeud(0.0,0.0,TypeNoeud.NORMAL,c);
    Noeud n2 = new Noeud(1.0,1.0,TypeNoeud.NORMAL,c);
    Noeud nNew = new Noeud(0.0,1.0,TypeNoeud.NORMAL,c);

    Arc a1;
    Arc a2;
    Arc a3;
    Arc a4;

    /**
     * Test pour l'ensemble des fonctions de la classe Arc
     */
    public TestArc() {
        a1 = new Arc(n1,n2,TypeArc.PLAT,c);
        a2 = new Arc(n1,n2,"PLAT",c);
        a3 = new Arc(n1,n2,"innonde",c);
        a4 = new Arc(n1,n2,"EscARpE",c);
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

    @Test(expected = IllegalArgumentException.class)
    public void testArcTypeArcInvalid() {
        a1 = new Arc(n1,n2,"PLATe",c);
    }

    @Test(expected = NullPointerException.class)
    public void testArcNoeud1Null1() {
        a1 = new Arc(null,n2,TypeArc.PLAT,c);
    }

    @Test(expected = NullPointerException.class)
    public void testArcNoeud2Null1() {
        a1 = new Arc(n1,null,TypeArc.PLAT,c);
    }

    @Test(expected = NullPointerException.class)
    public void testArcNoeud1Null2() {
        a1 = new Arc(null,n2,"PLAT",c);
    }

    @Test(expected = NullPointerException.class)
    public void testArcNoeud2Null2() {
        a1 = new Arc(n1,null,"PLAT",c);
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(a2.getNoeud1(),n1);
        assertEquals(a2.getNoeud2(),n2);
        assertEquals(a2.getLongueur(),Math.sqrt(2.0),0.0);
        assertNotEquals(a2.getLongueur(), 0.0);
        assertEquals(a2.getType(), TypeArc.PLAT);
        assertEquals(a2.getTypeString(),"PLAT");
        assertEquals(a3.getTypeString(),"INNONDE");
        assertEquals(a4.getTypeString(),"ESCARPE");
        assertNotEquals(a2.getType(),TypeArc.ESCARPE);
    }

    @Test
    public void testSet() {
        a1.setNoeud1(nNew);
        assertEquals(a1, new Arc(nNew, n2, TypeArc.PLAT, c));
        a1.setNoeud2(nNew);
        assertEquals(a1, new Arc(nNew, nNew, TypeArc.PLAT, c));
        a1.setNoeud2(n1);
        assertEquals(a1, new Arc(nNew, n1, TypeArc.PLAT, c));
        a1.setType(TypeArc.ESCARPE);
        assertEquals(a1.getType(),TypeArc.ESCARPE);
    }
}
