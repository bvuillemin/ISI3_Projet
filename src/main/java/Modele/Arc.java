package Modele;

/**
 * Created by benoitvuillemin on 06/05/2015.
 */
enum typeArc {
    PLAT,
    ESCARPE,
    INONDE
}

public class Arc {
    private Noeud noeud1;
    private Noeud noeud2;
    private int longueur;
    private typeArc type;

    public Arc(Noeud noeud1, Noeud noeud2, int longueur, typeArc type) {
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

    public typeArc getType() {
        return type;
    }

    public void setType(typeArc type) {
        this.type = type;
    }

    public String toString() {
        return "Arc : nd1=" + noeud1 + " nd2=" + noeud2 + " type=" + type;
    }
}
