package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by benoitvuillemin on 11/05/2015.
 */
public class Carte extends JPanel implements Observer {

    public void paintComponent(Graphics g){
        //Vous verrez cette phrase chaque fois que la méthode sera invoquée
        System.out.println("Je suis exécutée !");
        g.fillOval(20, 20, 75, 75);
    }
    public void update(Observable o, Object arg) {

    }
}
