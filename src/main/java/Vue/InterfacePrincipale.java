package Vue;

import javax.swing.*;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public class InterfacePrincipale extends JFrame{
    public static void main(String[] args){

        InterfacePrincipale fenetre = new InterfacePrincipale();
    }
    public InterfacePrincipale(){
        this.setTitle("Robocup Rescue");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        this.setContentPane(new Carte());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
