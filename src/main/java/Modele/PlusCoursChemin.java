package Modele;

import java.util.*;

/**
 * Created by Dorian on 24/05/2015.
 */
public class PlusCoursChemin {

    Noeud actuel;
    LinkedList<Noeud> listNoeud;
    ArrayList<Arc> listArc;
    Robot robot;
    LinkedHashMap<Noeud, ArrayList<Arc>> listArcOpti;
    LinkedHashMap<Noeud, Double> listMarque;

    public PlusCoursChemin (){

    }

    public ArrayList<Arc> ParcoursLargeur(Robot robot, Graphe graph, Noeud goal){
        Noeud nTemp;
        ArrayList<Arc> listArc_voisin;
        init(robot);
        listArc = graph.getListe_arcs();
        while (!listNoeud.isEmpty()){
            actuel = listNoeud.removeFirst();
            if (actuel != goal) {
                listArc_voisin=voisins(actuel);
                for (Arc e : listArc_voisin) {
                    nTemp = deuxiemeNoeudArc(e,actuel);
                    if (!listMarque.containsKey(nTemp)) {
                        cheminAmeliore(nTemp,e);
                    } else if (listMarque.get(nTemp) > (e.getLongueur() + listMarque.get(actuel))) {
                        listMarque.remove(nTemp);
                        listArcOpti.remove(nTemp);
                        cheminAmeliore(nTemp,e);
                    }
                }
                listArc_voisin.clear();
            }
        }
        return listArcOpti.get(goal);
    }

    protected void init(Robot robot) {
        this.robot=robot;
        actuel = robot.noeudActuel;
        listNoeud = new LinkedList<Noeud>();
        listArcOpti = new LinkedHashMap<Noeud, ArrayList<Arc>>();
        listMarque = new LinkedHashMap<Noeud, Double>();
        listNoeud.add(actuel);
        listMarque.put(actuel, 0.0);
        listArcOpti.put(actuel,new ArrayList<Arc>());
    }

    protected ArrayList<Arc> voisins (Noeud n) {
        ArrayList<Arc> listArc_voisin = new ArrayList<Arc>();
        for (Arc arc_voisin : listArc) {
            if (((arc_voisin.getNoeud1() == n) || (arc_voisin.getNoeud2() == n))&&(robot.capablePasser(arc_voisin.getType())!=false)) {
                listArc_voisin.add(arc_voisin);
            }
        }
        return listArc_voisin;
    }

    protected Noeud deuxiemeNoeudArc(Arc a,Noeud noeud1) {
        if (a.getNoeud1() == noeud1) {
            return a.getNoeud2();
        } else if (a.getNoeud2() == noeud1) {
            return a.getNoeud1();
        }
        return null;
    }

    protected void cheminAmeliore(Noeud n, Arc a) {
        listNoeud.add(n);
        listMarque.put(n, a.getLongueur() + listMarque.get(actuel));
        listArcOpti.put(n, (ArrayList<Arc>) listArcOpti.get(actuel).clone());
        listArcOpti.get(n).add(a);
    }
}
