package Modele;

/**
 * Created by benoitvuillemin on 06/05/2015.
 */
public class Noeud {
    private int id;
    private int x;
    private int y;
    private Type type;

    public enum Type{
        NORMAL,
        INCENDIE
    }

    public Noeud(int id, int x, int y, Type type) {
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
