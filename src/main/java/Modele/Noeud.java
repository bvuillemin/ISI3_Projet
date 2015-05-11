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

    /**
     * Créé un nouveau noeud
     *
     * @param id   id du noeud
     * @param x    abscisse du noeud
     * @param y    ordonnée du noeud
     * @param type type du noeud (String)
     */
    public Noeud(int id, double x, double y, String type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = NoeudStringToEnum(type);
        if (type.equals(TypeNoeud.NORMAL)) {
            this.intensite = 0;
        } else {
            this.intensite = (int) (Math.random() * (9) + 1);
        }
    }

    /**
     * Créé un nouveau noeud
     *
     * @param id   id du noeud
     * @param x    abscisse du noeud
     * @param y    ordonnée du noeud
     * @param type type du noeud (TypeNoeud)
     */
    public Noeud(int id, double x, double y, TypeNoeud type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
        if (type.equals(TypeNoeud.NORMAL)) {
            this.intensite = 0;
        } else {
            this.intensite = (int) (Math.random() * (9) + 1);
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
        if (intensite > 0) {
            this.type = TypeNoeud.INCENDIE;
        } else {
            this.type = TypeNoeud.NORMAL;
        }
    }

    public TypeNoeud getType() {
        return type;
    }

    public void setType(TypeNoeud type) {
        this.type = type;
        if (type.equals(TypeNoeud.NORMAL)) {
            this.intensite = 0;
        } else {
            this.intensite = (int) (Math.random() * (9) + 1);
        }
    }

    public String toString() {
        return "Noeud : id=" + id + " x=" + x + " y=" + y + " type=" + type;
    }

    /**
     * Convertit un String en une instance de typeNoeud (de type enum). Utilisé lors de la lecture d'un fichier XML
     *
     * @param s String de départ
     * @return typeNoeud correspondant
     * @throws IllegalArgumentException si le string de départ ne correspond à rien
     */
    private TypeNoeud NoeudStringToEnum(String s) {
        TypeNoeud t;
        if (s.equals("NORMAL")) {
            t = TypeNoeud.NORMAL;
        } else if (s.equals("INCENDIE")) {
            t = TypeNoeud.INCENDIE;
        } else {
            throw new IllegalArgumentException("String de typeNoeud invalide" + s);
        }
        return t;
    }

    /**
     * Convertit une instance de typeNoeud (de type enum) en String. Utilisé lors de l'écriture d'un fichier XML
     *
     * @return String correspondant au typeNoeud
     * @throws Exception si le typeNoeud est invalide
     */
    public String getTypeString() throws Exception {
        if (this.type.equals(TypeNoeud.NORMAL)) {
            return "NORMAL";
        } else if (this.type.equals(TypeNoeud.INCENDIE)) {
            return "INCENDIE";
        } else {
            throw new Exception("TypeNoeud de départ invalide");
        }
    }
}
