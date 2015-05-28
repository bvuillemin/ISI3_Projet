package Modele;

import java.util.ArrayList;

/**
 * Created by benoitvuillemin on 06/05/2015.
 */
public class Graphe {
    private ArrayList<Arc> liste_arcs;
    private ArrayList<Noeud> liste_noeuds;

    public Graphe() {
        this.liste_arcs=new ArrayList<Arc>();
        this.liste_noeuds=new ArrayList<Noeud>();
    }

    /**
     * Créé un nouveau graphe
     *
     * @param liste_arcs  liste des arcs
     * @param liste_noeud liste des noeuds
     */
    public Graphe(ArrayList<Arc> liste_arcs, ArrayList<Noeud> liste_noeud) {
        this.liste_arcs = liste_arcs;
        this.liste_noeuds = liste_noeud;
    }

    public ArrayList<Arc> getListe_arcs() {
        return liste_arcs;
    }

    public void setListe_arcs(ArrayList<Arc> liste_arcs) {
        this.liste_arcs = liste_arcs;
    }

    public ArrayList<Noeud> getListe_noeud() {
        return liste_noeuds;
    }

    public void setListe_noeud(ArrayList<Noeud> liste_noeud) {
        this.liste_noeuds = liste_noeud;
    }

    /**
     * Supprime un noeud du graphe, et les arcs associés
     *
     * @param n noeud à supprimer
     */
    public void supprimerNoeud(Noeud n) {
        liste_noeuds.remove(n);
        for (Arc arc : liste_arcs) {
            if (arc.getNoeud1() == n || arc.getNoeud2() == n) {
                liste_arcs.remove(arc);
            }
        }
    }

    /**
     * Ajoute un noeud
     *
     * @param n noeud à ajouter
     */
    public void ajouterNoeud(Noeud n) {
        liste_noeuds.add(n);
    }

    /**
     * Ajoute un arc
     *
     * @param a arc à ajouter
     */
    public void ajouterArc(Arc a) {
        if (a.getNoeud1() != a.getNoeud2()) {
            boolean noeud1_existe = false;
            boolean noeud2_existe = false;
            for (Noeud noeud : liste_noeuds) {
                if (a.getNoeud1() == noeud) {
                    noeud1_existe = true;
                }
                if (a.getNoeud2() == noeud) {
                    noeud2_existe = true;
                }
            }
            if (noeud1_existe && noeud2_existe) {
                liste_arcs.add(a);
            } else {
                System.out.println("Arc impossible !" + a.toString());
            }
        } else {
            System.out.println("L'arc relie le même noeud !" + a.toString());
        }
    }

    /**
     * Trouve un noeud dans le graphe avec son id
     *
     * @param id id du noeud à trouver
     * @return le noeud à trouver
     */
    public Noeud trouverNoeudParId(int id) {
        for (Noeud noeud : liste_noeuds) {
            if (noeud.getId() == id) {
                return noeud;
            }
        }
        return null;
    }

    public Noeud contientAppro(int x, int y) {
        for (Noeud n : liste_noeuds) {
            if ((n.getX()-5<=x)&&(n.getX()+5>=x)&&(n.getY()-5<=y)&&(n.getY()+5>=y)) {
                return n;
            }
        }
        return null;
    }
}
