package Modele;

import Vue.Carte;

import java.util.Observable;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Arc extends Observable{
    private Noeud noeud1;
    private Noeud noeud2;
    private int longueur;
    private TypeArc type;

    /**
     * Créé un nouvel arc
     *
     * @param noeud1 1er noeud
     * @param noeud2 2eme noeud
     * @param type   type de l'arc
     */
    public Arc(Noeud noeud1, Noeud noeud2, String type, Carte c) {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.longueur = longueurArc(noeud1, noeud2);
        this.type = ArcStringToEnum(type);
        this.addObserver(c);
    }

    public Arc(Noeud noeud1, Noeud noeud2, TypeArc type, Carte c) {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.longueur = longueurArc(noeud1, noeud2);
        this.type = type;
        this.addObserver(c);
    }

    public Noeud getNoeud1() {
        return noeud1;
    }

    public void setNoeud1(Noeud noeud1) {
        this.noeud1 = noeud1;
        this.longueur = longueurArc(noeud1, noeud2);
    }

    public Noeud getNoeud2() {
        return noeud2;
    }

    public void setNoeud2(Noeud noeud2) {
        this.noeud2 = noeud2;
        this.longueur = longueurArc(noeud1, noeud2);
    }

    public int getLongueur() {
        return longueur;
    }

    public TypeArc getType() {
        return type;
    }

    public void setType(TypeArc type) {
        this.type = type;
    }

    public String toString() {
        return "Arc : nd1=" + noeud1 + " nd2=" + noeud2 + " type=" + type;
    }

    /**
     * Convertit un String en une instance de typeArc (de type enum). Utilisé lors de la lecture d'un fichier XML
     *
     * @param s String de départ
     * @return typeArc correspondant
     * @throws IllegalArgumentException si le string de départ ne correspond à rien
     */
    private TypeArc ArcStringToEnum(String s) {
        TypeArc t;
        if (s.equals("PLAT")) {
            t = TypeArc.PLAT;
        } else if (s.equals("ESCARPE")) {
            t = TypeArc.ESCARPE;
        } else if (s.equals("INNONDE")) {
            t = TypeArc.INNONDE;
        } else {
            throw new IllegalArgumentException("String de typeArc invalide" + s);
        }
        return t;
    }

    /**
     * Convertit une instance de typeArc (de type enum) en String. Utilisé lors de l'écriture d'un fichier XML
     *
     * @return String correspondant au typeArc
     * @throws Exception si le typeArc est invalide
     */
    public String getTypeString() throws Exception {
        if (this.type.equals(TypeArc.PLAT)) {
            return "PLAT";
        } else if (this.type.equals(TypeArc.ESCARPE)) {
            return "ESCARPE";
        } else if (this.type.equals(TypeArc.INNONDE)) {
            return "INNONDE";
        } else {
            throw new Exception("TypeArc de départ invalide");
        }
    }

    /**
     * Calcule la longueur entre deux noeuds (pour la création d'un arc)
     *
     * @param n1 noeud 1
     * @param n2 noeud 2
     * @return la longueur entre les deux noeuds
     */
    private int longueurArc(Noeud n1, Noeud n2) {
        return (int) sqrt(pow((n1.getX() - n2.getX()), 2) + pow((n1.getY() - n2.getY()), 2));
    }
}
