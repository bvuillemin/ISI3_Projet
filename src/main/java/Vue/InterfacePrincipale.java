package Vue;

import Controleur.Controleur;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    JFileChooser chooser;
    int OPTIONS_WIDTH = 200;
    int OPTION_HEIGHT = 25;
    int CARTE_WIDTH = 600;
    int CARTE_HEIGHT = 600;

    public static void main(String[] args) {
        Controleur c = new Controleur();
        InterfacePrincipale fenetre = new InterfacePrincipale(c);
        c.setIp(fenetre);
        c.init();
    }

    public InterfacePrincipale(Controleur c) {
        this.c = c;
        this.setTitle("Robocup Rescue");
        this.setSize(OPTIONS_WIDTH + CARTE_WIDTH, CARTE_HEIGHT);
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

    public void setGraphe() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileFilter xmlFilter = new FileNameExtensionFilter("Fichiers XML", "xml");

        chooser.addChoosableFileFilter(xmlFilter);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(this.getJMenuBar()) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String sname = file.getAbsolutePath();
            c.setPath_XML(sname);
        }
    }

    public void initComposant() {
        /**
         * Mise en place de la barre de menu
         */
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem menuItemNouveau = new JMenuItem("Nouveau graphe");
        menuItemNouveau.addActionListener(c);
        menuFichier.add(menuItemNouveau);
        JMenuItem menuItemImage = new JMenuItem("Charger une image de fond");
        menuItemImage.addActionListener(c);
        menuFichier.add(menuItemImage);
        menuBar.add(menuFichier);

        JMenu menuSauvegarde = new JMenu("Sauvegarde/Chargement");
        JMenuItem menuItemCharger = new JMenuItem("Charger un graphe");
        menuItemCharger.addActionListener(c);
        menuSauvegarde.add(menuItemCharger);
        JMenuItem menuItemSauvegarder = new JMenuItem("Sauvegarder un graphe");
        menuItemSauvegarder.addActionListener(c);
        menuSauvegarde.add(menuItemSauvegarder);
        menuBar.add(menuSauvegarde);

        this.setJMenuBar(menuBar);

        /**
         * Pannel principal
         */
        JPanel content = new JPanel(new BorderLayout());

        Carte graphe = new Carte();
        graphe.setPreferredSize(new Dimension(CARTE_WIDTH, CARTE_HEIGHT));
        graphe.addMouseListener(c);
        c.setCarte(graphe);
        content.add(graphe, BorderLayout.EAST);

        /**
         * Pannel des options
         */
        JPanel option = new JPanel(new GridBagLayout());
        option.setPreferredSize(new Dimension(OPTIONS_WIDTH, CARTE_HEIGHT));
        GridBagConstraints gbc = new GridBagConstraints();
        ButtonGroup bg = new ButtonGroup();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        JLabel texteAjouter = new JLabel("Ajouter :");
        texteAjouter.setPreferredSize(new Dimension(OPTIONS_WIDTH * 9 / 10, OPTION_HEIGHT));
        option.add(texteAjouter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        JLabel espaceVide = new JLabel("");
        espaceVide.setPreferredSize(new Dimension(OPTIONS_WIDTH * 9 / 10, OPTION_HEIGHT / 3));
        option.add(espaceVide, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        rbAjoutNoeud = new JRadioButton("Un noeud");
        rbAjoutNoeud.setPreferredSize(new Dimension(OPTIONS_WIDTH * 9 / 10, OPTION_HEIGHT));
        rbAjoutNoeud.setSelected(true);
        bg.add(rbAjoutNoeud);
        option.add(rbAjoutNoeud, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        espaceVide = new JLabel("");
        espaceVide.setPreferredSize(new Dimension(OPTIONS_WIDTH * 9 / 10, OPTION_HEIGHT / 3));
        option.add(espaceVide, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        rbAjoutIncendie = new JRadioButton("Un incendie");
        rbAjoutIncendie.setPreferredSize(new Dimension(OPTIONS_WIDTH * 9 / 10, OPTION_HEIGHT));
        bg.add(rbAjoutIncendie);
        option.add(rbAjoutIncendie, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        espaceVide = new JLabel("");
        espaceVide.setPreferredSize(new Dimension(OPTIONS_WIDTH * 9 / 10, OPTION_HEIGHT / 3));
        option.add(espaceVide, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        rbAjoutArc = new JRadioButton("Un arc");
        rbAjoutArc.setPreferredSize(new Dimension(OPTIONS_WIDTH * 9 / 10, OPTION_HEIGHT));
        bg.add(rbAjoutArc);
        option.add(rbAjoutArc, gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        JComboBox listTypeArc = new JComboBox();
        listTypeArc.setPreferredSize(new Dimension(OPTIONS_WIDTH * 8 / 10, OPTION_HEIGHT));
        listTypeArc.addItem("Chemin plat");
        listTypeArc.addItem("Chemin escarpé");
        listTypeArc.addItem("Chemin inondé");
        option.add(listTypeArc, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        espaceVide = new JLabel("");
        espaceVide.setPreferredSize(new Dimension(OPTIONS_WIDTH * 9 / 10, OPTION_HEIGHT / 3));
        option.add(espaceVide, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        rbAjoutRobot = new JRadioButton("Un robot");
        rbAjoutRobot.setPreferredSize(new Dimension(OPTIONS_WIDTH * 9 / 10, OPTION_HEIGHT));
        option.add(rbAjoutRobot, gbc);
        bg.add(rbAjoutRobot);

        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        JComboBox listRobot = new JComboBox();
        listRobot.setPreferredSize(new Dimension(OPTIONS_WIDTH * 8 / 10, OPTION_HEIGHT));
        listRobot.addItem("Robot à pattes");
        listRobot.addItem("Robot chenille");
        listRobot.addItem("Robot tout terrain");
        option.add(listRobot, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 5;
        gbc.gridheight = 5;
        espaceVide = new JLabel("");
        espaceVide.setPreferredSize(new Dimension(OPTIONS_WIDTH * 9 / 10, OPTION_HEIGHT * 2));
        option.add(espaceVide, gbc);

        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = 5;
        gbc.gridheight = 2;
        JButton buttonLancer = new JButton("Lancer");
        buttonLancer.setPreferredSize(new Dimension(OPTIONS_WIDTH, OPTION_HEIGHT * 2));
        buttonLancer.addActionListener(c);
        option.add(buttonLancer, gbc);

        content.add(option, BorderLayout.WEST);
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
