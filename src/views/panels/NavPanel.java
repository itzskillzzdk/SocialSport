package views.panels;

import views.ContainerFrame;
import views.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class NavPanel extends JPanel {
    private ContainerFrame containerFrame;
    private JLabel logoTextJL;
    private JLabel logoImageJL;
    private JButton profilBtn;
    private JButton sportsBtn;
    private JButton donneeSportBtn;
    private JButton demandeAmisBtn;
    private JButton mesAmisBtn;
    public NavPanel(ContainerFrame containerFrame) {
        this.containerFrame = containerFrame;
        init();
    }
    private void init() {
        Dimension dimension = new Dimension(320, 720);
        setPreferredSize(dimension);
        setMinimumSize(dimension);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(30, 30, 30, 30);

        // Initialize JButton and JLabel
        logoImageJL = new JLabel();
        logoTextJL = new JLabel(" SocialSport");
        logoTextJL.setFont(new Font("Arial", Font.PLAIN, 24));

        Box logoBox = Box.createHorizontalBox();
        ImageIcon icon = Utils.getResizeImage("src/photo/logo.png", 32, 32);
        logoImageJL.setIcon(icon);
        logoBox.add(logoImageJL);
        logoBox.add(logoTextJL);

        profilBtn = new JButton("Profil");
        profilBtn.addActionListener(e -> {
            containerFrame.setNewMainPanel(new ProfilPanel(containerFrame.getController()));
        });
        sportsBtn = new JButton("Graphiques sportifs");
        sportsBtn.addActionListener(e -> {
            containerFrame.setNewMainPanel(new GraphiqueSports(containerFrame.getController()));
        });
        donneeSportBtn = new JButton("Ajouter des données");
        donneeSportBtn.addActionListener(e -> {
            containerFrame.setNewMainPanel(new AjouterDonneePanel(containerFrame.getController()));
        });
        demandeAmisBtn = new JButton("Recommendation d'amis");
        demandeAmisBtn.addActionListener(e -> {
            containerFrame.setNewMainPanel(new FriendRecommendationPanel(containerFrame.getController()));
        });
        mesAmisBtn = new JButton("Mes amis");
        mesAmisBtn.addActionListener(e -> {
            containerFrame.setNewMainPanel(new FriendsPanel(containerFrame.getController()));
        });

        setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        add(logoBox, constraints);
        constraints.insets = new Insets(15,15,15,15);
        add(profilBtn, constraints);
        add(sportsBtn, constraints);
        add(donneeSportBtn, constraints);
        add(demandeAmisBtn, constraints);
        add(mesAmisBtn, constraints);
    }
}
