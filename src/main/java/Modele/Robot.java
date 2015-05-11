package Modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public class Robot extends Thread{

    protected Noeud noeudActuel;
    protected ArrayList<TypeArc> listTypeArcTraversable;
    protected ArrayList<Arc> listArcPlusCourt;
    protected ArrayList<Arc> chemin;
    protected Graphe graphe;
    protected Map dijkstra;
    protected int capacite;


    public Robot(Noeud noeudActuel) {
        this.noeudActuel = noeudActuel;
        capacite=1;
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

    public ArrayList<Arc> plusCourtChemin(Noeud noeudDestination){
        dijkstra = new HashMap();
        int num = 0;
        for (int i = 0 ; i < graphe.getListe_noeud().size() ; i++){
            dijkstra.put(graphe.getListe_noeud().indexOf(i),-1);
        }

        return listArcPlusCourt;
    }

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

    @Override
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
