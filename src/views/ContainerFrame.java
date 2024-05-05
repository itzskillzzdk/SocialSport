package views;

import controller.Controller;
import views.panels.BannerPanel;
import views.panels.MainPanel;
import views.panels.NavPanel;

import javax.swing.*;
import java.awt.*;

public class ContainerFrame extends JFrame {
    private NavPanel navPanel;
    private BannerPanel bannerPanel;
    private Component mainPanel;
    private Controller controller;
    GridBagConstraints constraints = new GridBagConstraints();
    public ContainerFrame(Controller controller) {
        super("SocialSport");
        this.controller = controller;
        init();
    }
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setPreferredSize(new Dimension (1280, 720));
        setMinimumSize(new Dimension (1280, 720));
        Container container = getContentPane();

        // Set up the layout
        container.setLayout(new GridBagLayout());

        navPanel = new NavPanel(this);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        add(navPanel ,constraints);

        bannerPanel = new BannerPanel();
        bannerPanel.getUsernameJL().setText(controller.getCurrentUser().getNom());
        //bannerPanel.getUsersportsJL().setText(controller.getCurrentUser().sportListToString());
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(bannerPanel ,constraints);

        mainPanel = new MainPanel();
        setNewMainPanel(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setNewMainPanel(Component newPanel) {
        remove(mainPanel);
        mainPanel = newPanel;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(newPanel, constraints);
        revalidate();
        repaint();
    }
    public Controller getController() {
        return controller;
    }
}
