package Vue;

import Controleur.Controleur;
import Modele.Manager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**
 * Created by thomascoquan on 06/05/2015.
 */
public class InterfacePrincipale extends JFrame {

    Controleur c;
    JRadioButton rbAjoutIncendie;
    JRadioButton rbAjoutNoeud;
    JRadioButton rbAjoutArc;
    JRadioButton rbAjoutRobot;

    public static void main(String[] args) {
        Controleur c = new Controleur();
        InterfacePrincipale fenetre = new InterfacePrincipale(c);
        c.setIp(fenetre);
    }

    public InterfacePrincipale(Controleur c) {
        this.c = c;
        this.setTitle("Robocup Rescue");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initComposant();
        this.setVisible(true);
    }

    public int typeAjout() {
        if (rbAjoutNoeud.isSelected() != false) {
            return 1;
        } else if (rbAjoutArc.isSelected() != false) {
            return 3;
        } else if (rbAjoutIncendie.isSelected() != false) {
            return 2;
        } else if (rbAjoutRobot.isSelected() != false) {
            return 4;
        } else {
            return -1;
        }
    }

    public void setBackground() {
        JFileChooser chooser;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileFilter imageFilter = new ImageFilter();

        chooser.addChoosableFileFilter(imageFilter);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(this.getJMenuBar()) == JFileChooser.APPROVE_OPTION) {
            c.removeBackground();
            File file = chooser.getSelectedFile();
            String sname = file.getAbsolutePath();
            ImageIcon icon = new ImageIcon(sname);
            Image img = icon.getImage();
            c.setBackground(img);
        }
    }

    public void initComposant() {
        /**
         * Mise en place de la barre de menu
         */
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem menuItemNouveau = new JMenuItem("Nouveau graphe sans fond");
        menuItemNouveau.addActionListener(c);
        menuFichier.add(menuItemNouveau);
        JMenuItem menuItemImage = new JMenuItem("Nouveau graphe avec un fond");
        menuItemImage.addActionListener(c);
        menuFichier.add(menuItemImage);
        JMenuItem menuItemCharger = new JMenuItem("Charger graphe");
        menuItemCharger.addActionListener(c);
        menuFichier.add(menuItemCharger);
        JMenuItem menuItemSauvegarder = new JMenuItem("Sauvegarder graphe");
        menuItemSauvegarder.addActionListener(c);
        menuFichier.add(menuItemSauvegarder);
        menuBar.add(menuFichier);
        this.setJMenuBar(menuBar);

        /**
         * Pannel principal
         */
        JPanel content = new JPanel(new BorderLayout());

        Carte graphe = new Carte();
        graphe.setPreferredSize(new Dimension(800, 400));
        graphe.addMouseListener(c);
        c.setCarte(graphe);
        content.add(graphe, BorderLayout.NORTH);

        JPanel option = new JPanel(new GridBagLayout());
        option.setPreferredSize(new Dimension(800, 100));
        GridBagConstraints gbc = new GridBagConstraints();
        ButtonGroup bg = new ButtonGroup();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        JPanel panelLancer = new JPanel();
        panelLancer.setPreferredSize(new Dimension(250, 100));
        JButton buttonLancer = new JButton("Lancer");
        buttonLancer.addActionListener(c);
        buttonLancer.setPreferredSize(new Dimension(130, 25));
        panelLancer.add(buttonLancer);
        option.add(panelLancer, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        rbAjoutNoeud = new JRadioButton("Ajouter un noeud");
        rbAjoutNoeud.setPreferredSize(new Dimension(200, 25));
        rbAjoutNoeud.setSelected(true);
        bg.add(rbAjoutNoeud);
        option.add(rbAjoutNoeud, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        rbAjoutArc = new JRadioButton("Ajouter un arc");
        rbAjoutArc.setPreferredSize(new Dimension(200, 25));
        bg.add(rbAjoutArc);
        option.add(rbAjoutArc, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        JComboBox listTypeArc = new JComboBox();
        listTypeArc.setPreferredSize(new Dimension(150, 25));
        listTypeArc.addItem("Chemin plat");
        listTypeArc.addItem("Chemin escarpé");
        listTypeArc.addItem("Chemin inondé");
        option.add(listTypeArc, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        rbAjoutIncendie = new JRadioButton("Ajouter un incendie");
        rbAjoutIncendie.setPreferredSize(new Dimension(200, 25));
        bg.add(rbAjoutIncendie);
        option.add(rbAjoutIncendie, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        rbAjoutRobot = new JRadioButton("Ajouter un robot");
        rbAjoutRobot.setPreferredSize(new Dimension(200, 25));
        option.add(rbAjoutRobot, gbc);
        bg.add(rbAjoutRobot);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        JComboBox listRobot = new JComboBox();
        listRobot.setPreferredSize(new Dimension(150, 25));
        listRobot.addItem("Robot à pattes");
        listRobot.addItem("Robot chenille");
        listRobot.addItem("Robot tout terrain");
        option.add(listRobot, gbc);

        content.add(option, BorderLayout.SOUTH);

        this.setContentPane(content);
    }

    public class ImageFilter extends FileFilter {
        public String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
            return ext;
        }

        //Accept all directories and all gif, jpg, tiff, or png files.
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }

            String extension = getExtension(f);
            if (extension != null) {
                if (extension.equals("gif") ||
                        extension.equals("jpeg") ||
                        extension.equals("jpg") ||
                        extension.equals("png")) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        //The description of this filter
        public String getDescription() {
            return "Images";
        }
    }

}
