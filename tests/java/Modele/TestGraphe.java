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
 * Teste la classe Graphe
 */
public class TestGraphe {

    Graphe gVide;
    Graphe g1;

    Carte c = new Carte();

    Noeud n1 = new Noeud(0.0,1.0,TypeNoeud.NORMAL,c);
    Noeud n2 = new Noeud(1.0,1.0,TypeNoeud.NORMAL,c);
    Noeud n3 = new Noeud(3.0,4.0,TypeNoeud.NORMAL,c);
    Noeud n4 = new Noeud(5.0,2.0,TypeNoeud.NORMAL,c);

    Arc a1 = new Arc(n1,n2,TypeArc.PLAT,c);
    Arc a2 = new Arc(n2,n3,TypeArc.PLAT,c);
    Arc a3 = new Arc(n3,n4,TypeArc.PLAT,c);
    Arc a4 = new Arc(n4,n1,TypeArc.PLAT,c);

    ArrayList<Noeud> listNoeud = new ArrayList<Noeud>();
    ArrayList<Arc> listArc = new ArrayList<Arc>();
    /**
     * Test pour l'ensemble des fonctions de la classe Graphe
     */
    public TestGraphe() {
        gVide=new Graphe();
        listNoeud.add(n1);
        listNoeud.add(n2);
        listNoeud.add(n3);
        listNoeud.add(n4);
        listArc.add(a1);
        listArc.add(a2);
        listArc.add(a3);
        g1 = new Graphe(listArc,listNoeud);
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
    public void testGet() {
        assertEquals(g1.getListe_arcs(),listArc);
        assertEquals(g1.getListe_noeud(),listNoeud);
    }

    @Test
    public void testSet() {
        g1.setListe_arcs(null);
        assertNull(g1.getListe_arcs());
        listArc.add(a4);
        g1.setListe_arcs(listArc);
        assertEquals(g1.getListe_arcs(),listArc);
        g1.setListe_noeud(null);
        assertNull(g1.getListe_noeud());
        listNoeud.remove(n4);
        g1.setListe_noeud(listNoeud);
        assertEquals(g1.getListe_noeud(),listNoeud);
    }

    @Test
    public void testSupprimerNoeud() {
        g1.supprimerNoeud(n2);
        listNoeud.remove(n2);
        assertEquals(g1.getListe_noeud(), listNoeud);
    }

    @Test
    public void testAjout() {
        gVide.ajouterNoeud(n1);
        ArrayList<Noeud> listNoeud2 = new ArrayList<Noeud>();
        listNoeud2.add(n1);
        assertEquals(gVide.getListe_noeud(), listNoeud2);
        gVide.ajouterArc(a1);
        assertEquals(gVide.getListe_arcs(), new ArrayList<Arc>());
        gVide.ajouterArc(new Arc(n2, n2, TypeArc.PLAT, c));
        assertEquals(gVide.getListe_arcs(), new ArrayList<Arc>());
        gVide.ajouterNoeud(n2);
        gVide.ajouterArc(a1);
        ArrayList<Arc> listArc2 = new ArrayList<Arc>();
        listArc2.add(a1);
        assertEquals(gVide.getListe_arcs(), listArc2);
    }

    @Test
    public void testResearch() {
        assertEquals(g1.trouverNoeudParId(n1.getId()),n1);
        assertNull(g1.trouverNoeudParId(-1));
        assertEquals(g1.contientAppro(0, 0), n1);
        assertEquals(g1.contientAppro(5,6),n1);
        assertEquals(g1.contientAppro(-5,6),n1);
        assertEquals(g1.contientAppro(-5,-4),n1);
        assertEquals(g1.contientAppro(5,-4),n1);
        assertNull(g1.contientAppro(-6,3));
    }
}
