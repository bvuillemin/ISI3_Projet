package Modele;

import Vue.Carte;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Robot de l'application
 */
public abstract class Robot extends Observable implements Runnable {
    /**
     * Noeud sur lequel se trouve le robot
     */
    protected Noeud noeudActuel;
    /**
     * liste de type d'Arcs que le robot peut traverser
     */
    protected ArrayList<TypeArc> listTypeArcTraversable;
    /**
     * Chemin que d'emprunter le robot pour aller à l'incendie
     */
    protected ArrayList<Arc> chemin;
    /**
     * Graphe sur lequel se situe le robot
     */
    protected Graphe graphe;
    /**
     * Capacité du robot
     */
    protected int capacite;
    /**
     * Détermine si le robot est occupé ou non
     */
    protected boolean occupe;

    /**
     * Constructeur d'un Robot
     * Robot est une classe abstraite.
     *
     * @param noeudActuel Noeud sur lequel est placé le robot
     * @param c           Carte sur lequel est placé le robot (utile pour l'observer)
     * @param g           Graphe du robot
     */
    public Robot(Noeud noeudActuel, Carte c, Graphe g) {
        this.noeudActuel = noeudActuel;
        capacite = 1;
        this.graphe = g;
        this.addObserver(c);
    }

    public boolean isOccupe() {
        return occupe;
    }

    public Noeud getNoeudActuel() {
        return noeudActuel;
    }

    public ArrayList<Arc> getChemin() {
        return chemin;
    }

    public void setNoeudActuel(Noeud noeudActuel) {
        this.noeudActuel = noeudActuel;
    }

    public void setChemin(ArrayList<Arc> chemin) {
        this.chemin = chemin;
    }

    /**
     * Indique si le robot est capable de passer ce type d'arc
     *
     * @param typeArc Type de l'arc en question
     * @return vrai si capable, faux sinon
     */
    public boolean capablePasser(TypeArc typeArc) {
        try {
            if (listTypeArcTraversable.contains(typeArc)) {
                return true;
            }
        } catch (NullPointerException e) {
            System.out.println("Absence d'arc traversable");
        }
        return false;
    }

    /**
     * Fonction pour éteindre un incendie
     * Si le noeud sur lequel se trouve le robot n'est pas un incendie, on le signal
     * Sinon, on éteint l'incendie : chaque seconde, l'intensité de l'incendie sera diminué de la capacité du robot.
     *
     * @throws InterruptedException peut être renvoyé lors du wait
     */
    public void eteindreIncendie() throws InterruptedException {
        try {
            int probaInondation = 2;
            int rand = 0;
            if (noeudActuel.getType() == TypeNoeud.INCENDIE) {
                while (noeudActuel.getIntensite() != 0) {
                    noeudActuel.setIntensite(noeudActuel.getIntensite() - capacite);
                    for (Arc arc_voisin : graphe.getListe_arcs()) {
                        if (((arc_voisin.getNoeud1() == noeudActuel) || (arc_voisin.getNoeud2() == noeudActuel)) && (arc_voisin.getType() != TypeArc.INNONDE)) {
                            rand = (int) (Math.random() * (14) + 1);
                            if (rand < probaInondation) {
                                arc_voisin.setType(TypeArc.INNONDE);
                            }
                        }
                    }
                    synchronized (this) {
                        this.wait(1000);
                    }
                }
                System.out.println("Incendie éteint");
            } else {
                System.out.println("Pas d'incendie à éteindre");
            }
        } catch (NullPointerException e) {
            System.out.println("Erreur : Robot se trouvant nulle part ...");
        }
    }

    /**
     * Fonction run du Thread : déplace le robot suivant le chemin qu'il doit suivre.
     * S'il n'a pas de chemin à suivre, on le signale et le robot ne bouge pas.
     * Sinon, il essaie d'avancer en suivant les arc du chemin. Si jamais il rencontre un arc qu'il ne peux pas
     * traverser, on le signale et il s'arrête là.
     * S'il arrive au bout de chemin, il tente d'éteindre l'incendie s'y trouvant via la fonction eteindreIncendie
     */
    public void run() {
        occupe = true;
        double distanceParcActu = 0.0;
        Arc morceauChemin;
        if (chemin == null) {
            System.out.println("Pas de chemin à suivre.");
        } else {
            while (chemin.isEmpty() == false) {
                try {
                    morceauChemin = chemin.get(0);
                    distanceParcActu = morceauChemin.getLongueur();
                    System.out.println(morceauChemin);
                    if (this.capablePasser(morceauChemin.getType()) == false) {
                        System.out.println("Impossible de passer");
                        return;
                    }
                    synchronized (this) {
                        this.wait((int) distanceParcActu * 10);
                    }
                    if (noeudActuel == morceauChemin.getNoeud1()) {
                        noeudActuel = morceauChemin.getNoeud2();
                    } else {
                        noeudActuel = morceauChemin.getNoeud1();
                    }
                    chemin.remove(morceauChemin);
                    this.setChanged();
                    this.notifyObservers();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            this.eteindreIncendie();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        occupe = false;
    }
}
