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
    private int intensite;
    private TypeNoeud type;

    public Noeud(int id, double x, double y, String type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = NoeudStringToEnum(type);
        if(type.equals(TypeNoeud.NORMAL)){
            this.intensite = 0;
        }
        else{
            this.intensite = (int)(Math.random() * (9) + 1);
        }
    }
    public Noeud(int id, double x, double y, TypeNoeud type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
        if(type.equals(TypeNoeud.NORMAL)){
            this.intensite = 0;
        }
        else{
            this.intensite = (int)(Math.random() * (9) + 1);
        }
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

    public int getIntensite() {
        return intensite;
    }

    public void setIntensite(int intensite) {
        this.intensite = intensite;
        if(intensite > 0){
            this.type = TypeNoeud.INCENDIE;
        }
        else{
            this.type = TypeNoeud.NORMAL;
        }
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

    private TypeNoeud NoeudStringToEnum(String s) {
        TypeNoeud t;
        if (s.equals("NORMAL")) {
            t = TypeNoeud.NORMAL;
        } else if (s.equals("INCENDIE")) {
            t = TypeNoeud.INCENDIE;
        } else {
            return null;
        }
        return t;
    }
    public String getTypeString(){
        if (this.type.equals(TypeNoeud.NORMAL)){
            return "NORMAL";
        }
        else if (this.type.equals(TypeNoeud.INCENDIE)){
            return "INCENDIE";
        }
        else{
            return "";
        }
    }
}
