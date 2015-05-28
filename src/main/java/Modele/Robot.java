package Modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public abstract class Robot extends Observable{

    protected Noeud noeudActuel;
    protected ArrayList<TypeArc> listTypeArcTraversable;
    protected ArrayList<Arc> listArcPlusCourt;
    protected ArrayList<Arc> chemin;
    protected Graphe graphe;
    protected Map dijkstra;
    protected int capacite;

    /**
     * Constructeur d'un Robot
     * Robot est une classe abstraite.
     * @param noeudActuel
     */
    public Robot(Noeud noeudActuel) {
        this.noeudActuel = noeudActuel;
        capacite=1;
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
     * @param typeArc
     * @return vrai si capable, faux sinon
     */
    public boolean capablePasser(TypeArc typeArc) {
        try {
            if (listTypeArcTraversable.contains(typeArc)) {
                return true;
            }
        } catch (NullPointerException e) {
            System.out.println("Absence d'arc traversable");
        } finally {
            return false;
        }
    }

    /**
     * Fonction pour éteindre un incendie
     * Si le noeud sur lequel se trouve le robot n'est pas un incendie, on le signal
     * Sinon, on éteins l'incendie : chaque seconde l'intensité de l'incendie sera diminué de la capacité du robot.
     * @throws InterruptedException
     */
    public void eteindreIncendie() throws InterruptedException {
        try {
            if (noeudActuel.getType() == TypeNoeud.INCENDIE) {
                while (noeudActuel.getIntensite() != 0) {
                    noeudActuel.setIntensite(noeudActuel.getIntensite() - capacite);
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
        Arc morceauChemin;
        if (chemin == null) {
            System.out.println("Pas de chemin à suivre.");
        } else {
            while (chemin.isEmpty() == false) {
                try {
                    morceauChemin = chemin.get(0);
                    if (this.capablePasser(morceauChemin.getType())==false) {
                        System.out.println("Impossible de passer");
                        return;
                    }
                    noeudActuel = morceauChemin.getNoeud2();
                    chemin.remove(morceauChemin);
                    synchronized (this) {
                        this.wait(1000);
                    }
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
    }
}
