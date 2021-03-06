package Modele;

import Vue.Carte;

import java.util.Observable;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Arc du graphe
 */
public class Arc extends Observable {
    /**
     * Permier Noeud de l'Arc
     */
    private Noeud noeud1;
    /**
     * Second Noeud de l'Arc
     */
    private Noeud noeud2;
    /**
     * Longueur de l'Arc
     */
    private double longueur;
    /**
     * Type de l'Arc
     */
    private TypeArc type;

    /**
     * Crée un nouvel arc (appelé lors du chargement d'un XML)
     *
     * @param noeud1 1er noeud
     * @param noeud2 2eme noeud
     * @param type   type de l'arc (format String)
     * @param c      Carte de l'application (pour ajouter l'Observer)
     */
    public Arc(Noeud noeud1, Noeud noeud2, String type, Carte c) {
        if ((noeud1 == null) || (noeud2 == null)) {
            throw new NullPointerException("Initialisation avec un noeud nul");
        }
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.longueur = longueurArc(noeud1, noeud2);
        this.type = ArcStringToEnum(type);
        this.addObserver(c);
    }

    /**
     * Crée un nouvel arc
     *
     * @param noeud1 1er noeud
     * @param noeud2 2eme noeud
     * @param type   type de l'arc (format TypeArc)
     * @param c      Carte de l'application (pour ajouter l'Observer)
     */
    public Arc(Noeud noeud1, Noeud noeud2, TypeArc type, Carte c) {
        if ((noeud1 == null) || (noeud2 == null)) {
            throw new NullPointerException("Initialisation avec un noeud nul");
        }
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

    public double getLongueur() {
        return longueur;
    }

    public TypeArc getType() {
        return type;
    }

    public void setType(TypeArc type) {
        this.type = type;
        this.setChanged();
        this.notifyObservers();
    }

    public String toString() {
        return "Arc : type  : " + type + "\nNoeud 1 :" + noeud1 + "\nNoeud 2 :" + noeud2;
    }

    /**
     * Convertit un String en une instance de typeArc (de type enum). Utilisé lors de la lecture d'un fichier XML
     *
     * @param s String de départ
     * @return typeArc correspondant
     * @throws IllegalArgumentException si le string de départ ne correspond à rien
     */
    private TypeArc ArcStringToEnum(String s) {
        s = s.toUpperCase();
        TypeArc t;
        if (s.equals("PLAT")) {
            t = TypeArc.PLAT;
        } else if (s.equals("ESCARPE")) {
            t = TypeArc.ESCARPE;
        } else if (s.equals("INNONDE")) {
            t = TypeArc.INNONDE;
        } else {
            throw new IllegalArgumentException("String de typeArc invalide : " + s);
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
    private double longueurArc(Noeud n1, Noeud n2) {
        return sqrt(pow((n1.getX() - n2.getX()), 2) + pow((n1.getY() - n2.getY()), 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Arc arc = (Arc) o;

        if (noeud1 != null ? !noeud1.equals(arc.noeud1) : arc.noeud1 != null) return false;
        if (noeud2 != null ? !noeud2.equals(arc.noeud2) : arc.noeud2 != null) return false;
        if (type != arc.type) return false;

        return true;
    }
}
