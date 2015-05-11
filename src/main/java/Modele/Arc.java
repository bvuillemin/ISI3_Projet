package Modele;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by benoitvuillemin on 06/05/2015.
 */
enum TypeArc {
    PLAT,
    ESCARPE,
    INNONDE
}

public class Arc {
    private Noeud noeud1;
    private Noeud noeud2;
    private int longueur;
    private TypeArc type;

    public Arc(Noeud noeud1, Noeud noeud2, String type) {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.longueur = longueurArc(noeud1, noeud2);
        this.type = ArcStringToEnum(type);
    }

    public Arc(Noeud noeud1, Noeud noeud2, int longueur, TypeArc type) {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.longueur = longueur;
        this.type = type;
    }

    public Noeud getNoeud1() {
        return noeud1;
    }

    public void setNoeud1(Noeud noeud1) {
        this.noeud1 = noeud1;
    }

    public Noeud getNoeud2() {
        return noeud2;
    }

    public void setNoeud2(Noeud noeud2) {
        this.noeud2 = noeud2;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
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

    private TypeArc ArcStringToEnum(String s) {
        TypeArc t;
        if (s.equals("PLAT")) {
            t = TypeArc.PLAT;
        } else if (s.equals("ESCARPE")) {
            t = TypeArc.ESCARPE;
        } else if (s.equals("INNONDE")) {
            t = TypeArc.INNONDE;
        } else {
            return null;
        }
        return t;
    }
    private int longueurArc(Noeud n1, Noeud n2){
        return (int) sqrt(pow((n1.getX() - n2.getX()), 2) + pow((n1.getY() - n2.getY()), 2));
    }
}
