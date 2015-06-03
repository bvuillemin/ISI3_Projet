package Vue;

import Controleur.Controleur;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import com.apple.eawt.Application;

public class InterfacePrincipale extends JFrame {
    /**
     * Contrôleur du programme
     */
    Controleur c;
    /**
     * Bouton radio d'ajout d'un incendie
     */
    JRadioButton rbAjoutIncendie;
    /**
     * Bouton radio d'ajout d'un noeud
     */
    JRadioButton rbAjoutNoeud;
    /**
     * Bouton radio d'ajout d'un arc
     */
    JRadioButton rbAjoutArc;
    /**
     * Bouton radio d'ajout d'un robot
     */
    JRadioButton rbAjoutRobot;
    /**
     * Liste de sélection du type d'arc
     */
    JComboBox listTypeArc;
    /**
     * Liste de sélection du type de robot
     */
    JComboBox listRobot;
    /**
     * Explorateur de fichiers utilisé lors de la sauvegarde et du chargement d'un XML, et de l'ajout d'une image de fond
     */
    JFileChooser chooser;
    /**
     * Largeur du pannel d'options
     */
    int OPTIONS_WIDTH = 200;
    /**
     * Longueur d'un texte dans les options
     */
    int OPTION_HEIGHT = 25;
    /**
     * Largeur de la carte
     */
    int CARTE_WIDTH = 600;
    /**
     * Longueur de la carte
     */
    int CARTE_HEIGHT = 600;
    /**
     * Icone de l'interface
     */
    ImageIcon icone = new ImageIcon("src/main/resources/icone.png");

    /**
     * Fonction principale. Crée un nouveau contrôleur et une nouvelle fenêtre
     *
     * @param args
     */
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
        this.setIconImage(icone.getImage());
        Application application = Application.getApplication();
        application.setDockIconImage(icone.getImage());
        this.setVisible(true);
    }

    /**
     * Définit l'objet à ajouter sur la carte à êrtir de l'interface
     *
     * @return Entier correspondant au type d'objet à ajouter (comme arc, noeud...)
     */
    public int typeAjout() {
        if (rbAjoutNoeud.isSelected()) {
            return 1;
        } else if (rbAjoutIncendie.isSelected()) {
            return 2;
        } else if (rbAjoutArc.isSelected()) {
            if (listTypeArc.getSelectedIndex() == 0)
                return 3;
            else if (listTypeArc.getSelectedIndex() == 1)
                return 4;
            else if (listTypeArc.getSelectedIndex() == 2)
                return 5;
            else
                return -1;
        } else if (rbAjoutRobot.isSelected()) {
            if (listRobot.getSelectedIndex() == 0)
                return 6;
            else if (listRobot.getSelectedIndex() == 1)
                return 7;
            else if (listRobot.getSelectedIndex() == 2)
                return 8;
            else
                return -1;
        } else {
            return -1;
        }
    }

    /**
     * Définit l'image de fond de la carte en proposant à l'utilisateur un explorateur de fichiers (JFileChooser)
     */
    public void setBackground() {
        chooser = new JFileChooser();
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

    /**
     * Propose à utilisateur en explorateur de fichiers permettant de choisir un fichier XML
     * qui sera chargé sur la carte
     */
    public void setGraphe() {
        chooser = new JFileChooser();
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

    /**
     * Propose à l'utilisateur de sauvegarder le graphe de la carte dans un fichier XML dont il déterminera
     * le dossier est le nom du fichier
     */
    public void saveGraphe() {
        chooser = new JFileChooser();
        int userSelection = chooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();
            if (fileToSave.getAbsolutePath().endsWith(".xml"))
                c.savePath_XML(fileToSave.getAbsolutePath());
            else
                c.savePath_XML(fileToSave.getAbsolutePath() + ".xml");
        }
    }

    /**
     * Initialise les composants de la fenêtre : la carte, les options à gauche de la fenêtre et la barre de menu
     */
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
        listTypeArc = new JComboBox();
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
        listRobot = new JComboBox();
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
}
