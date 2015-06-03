package Modele;

import java.util.*;

/**
 * Created by Dorian on 24/05/2015.
 */
public class PlusCoursChemin {

    Noeud actuel;
    LinkedList<Noeud> listNoeud;
    ArrayList<Arc> listArc;

    public PlusCoursChemin (){

    }

    public ArrayList<Arc> ParcoursLargeur(Robot robot, Graphe graph, Noeud goal){
        listArc = graph.getListe_arcs();
        actuel = robot.noeudActuel;
        listNoeud = new LinkedList<Noeud>();
        ArrayList<Arc> listArc_voisin = new ArrayList<Arc>();
        LinkedHashMap<Noeud, ArrayList<Arc>> listArcOpti = new LinkedHashMap<Noeud, ArrayList<Arc>>();
        LinkedHashMap<Noeud, Double> listMarque = new LinkedHashMap<Noeud, Double>();
        listNoeud.add(actuel);
        listMarque.put(actuel, 0.0);
        listArcOpti.put(actuel,new ArrayList<Arc>());
        while (!listNoeud.isEmpty()){
            actuel = listNoeud.removeFirst();
            if (actuel != goal) {
                //on récupère la liste des voisins
                for (Arc arc_voisin : listArc) {
                    if (((arc_voisin.getNoeud1() == actuel) || (arc_voisin.getNoeud2() == actuel))&&(robot.capablePasser(arc_voisin.getType())!=false)) {
                        listArc_voisin.add(arc_voisin);
                    }
                }
                for (Arc e : listArc_voisin) {
                    if (e.getNoeud1() == actuel) {
                        if (!listMarque.containsKey(e.getNoeud2())) {
                            listNoeud.add(e.getNoeud2());
                            listMarque.put(e.getNoeud2(), e.getLongueur() + listMarque.get(actuel));
                            listArcOpti.put(e.getNoeud2(), (ArrayList<Arc>) listArcOpti.get(actuel).clone());
                            listArcOpti.get(e.getNoeud2()).add(e);
                        } else if (listMarque.get(e.getNoeud2()) > (e.getLongueur() + listMarque.get(actuel))) {
                            listNoeud.add(e.getNoeud2());
                            listMarque.remove(e.getNoeud2());
                            listMarque.put(e.getNoeud2(), e.getLongueur() + listMarque.get(actuel));
                            listArcOpti.remove(e.getNoeud2());
                            listArcOpti.put(e.getNoeud2(), (ArrayList<Arc>) listArcOpti.get(actuel).clone());
                            listArcOpti.get(e.getNoeud2()).add(e);
                        }
                    } else if (e.getNoeud2() == actuel) {
                        if (!listMarque.containsKey(e.getNoeud1())) {
                            listNoeud.add(e.getNoeud1());
                            listMarque.put(e.getNoeud1(), e.getLongueur() + listMarque.get(actuel));
                            listArcOpti.put(e.getNoeud1(), (ArrayList<Arc>) listArcOpti.get(actuel).clone());
                            listArcOpti.get(e.getNoeud1()).add(e);
                        } else if (listMarque.get(e.getNoeud1()) > (e.getLongueur() + listMarque.get(actuel))) {
                            listNoeud.add(e.getNoeud1());
                            listMarque.remove(e.getNoeud1());
                            listMarque.put(e.getNoeud1(), e.getLongueur() + listMarque.get(actuel));
                            listArcOpti.remove(e.getNoeud1());
                            listArcOpti.put(e.getNoeud1(), (ArrayList<Arc>) listArcOpti.get(actuel).clone());
                            listArcOpti.get(e.getNoeud1()).add(e);
                        }
                    }
                }
                listArc_voisin.clear();
            }
        }
        return listArcOpti.get(goal);
    }

}
