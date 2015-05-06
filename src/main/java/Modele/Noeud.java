package Modele;

/**
 * Created by benoitvuillemin on 06/05/2015.
 */
enum TypeNoeud {
    NORMAL,
    INCENDIE
}

public class Noeud {
    private int id;
    private double x;
    private double y;
    private TypeNoeud type;

    public Noeud(int id, double x, double y, TypeNoeud type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public TypeNoeud getType() {
        return type;
    }

    public void setType(TypeNoeud type) {
        this.type = type;
    }

    public String toString() {
        return "Noeud : id=" + id + " x=" + x + " y=" + y + " type=" + type;
    }
}
