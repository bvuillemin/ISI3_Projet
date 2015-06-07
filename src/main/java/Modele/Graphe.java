package Modele;

import java.util.ArrayList;

/**
 * Graphe de l'application, contenant noeuds et arcs
 */
public class Graphe {
    /**
     * Liste des Arcs du graphe
     */
    private ArrayList<Arc> liste_arcs;
    /**
     * Liste des Noeuds du graphe
     */
    private ArrayList<Noeud> liste_noeuds;

    public Graphe() {
        this.liste_arcs = new ArrayList<Arc>();
        this.liste_noeuds = new ArrayList<Noeud>();
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
        for (int i = 0; i < liste_arcs.size(); i++) {
            Arc arc = liste_arcs.get(i);
            if (arc.getNoeud1() == n || arc.getNoeud2() == n) {
                liste_arcs.remove(arc);
                i--;
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

    /**
     * Vérifie si l'utilisateur a cliqué sur un noeud lors de la création d'un arc
     * (vérifie à 5 pixels près, évite de devoir cliquer au pixel près)
     *
     * @param x Abscisse du clic
     * @param y Ordonnée du clic
     * @return Le noeud sur lequel l'utilisateur a cliqué, null si l'utilisateur n'a cliqué sur rien
     */
    public Noeud contientAppro(int x, int y) {
        for (Noeud n : liste_noeuds) {
            if ((n.getX() - 5 <= x) && (n.getX() + 5 >= x) && (n.getY() - 5 <= y) && (n.getY() + 5 >= y)) {
                return n;
            }
        }
        return null;
    }
}
