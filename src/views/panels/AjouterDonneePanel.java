package views.panels;

import views.panels.data_panel.SportDataPanelFactory;
import controller.Controller;
import modules.sports.Sport;

import java.awt.*;
import java.util.List;

import javax.swing.*;

public class AjouterDonneePanel extends JPanel {
    public static final Dimension dataSportPanelDim = new Dimension(800, 150);
    private Controller controller;

    public AjouterDonneePanel(Controller controller) {
        this.controller = controller;
        initializeUI(controller.getCurrentUser().getSportList());
    }
    private void initializeUI(List<Sport> selectedSports) {
        setPreferredSize(MainPanel.mainPanelDim);
        setMinimumSize(MainPanel.mainPanelDim);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleJL = new JLabel("Ajouter des données: ");
        titleJL.setFont(new Font("Arial", Font.BOLD,24));
        titleJL.setAlignmentX(CENTER_ALIGNMENT);

        JPanel sportDataPanel1 = SportDataPanelFactory.createDataPanel(controller.getCurrentUser().getSportList().get(0).getNom().getValeur(), controller);
        JPanel sportDataPanel2 = SportDataPanelFactory.createDataPanel(controller.getCurrentUser().getSportList().get(1).getNom().getValeur(), controller);
        JPanel sportDataPanel3 = SportDataPanelFactory.createDataPanel(controller.getCurrentUser().getSportList().get(2).getNom().getValeur(), controller);
        sportDataPanel1.setAlignmentX(CENTER_ALIGNMENT);
        sportDataPanel2.setAlignmentX(CENTER_ALIGNMENT);
        sportDataPanel3.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(10));
        add(titleJL);
        add(sportDataPanel1);
        add(Box.createVerticalStrut(20));
        add(sportDataPanel2);
        add(Box.createVerticalStrut(20));
        add(sportDataPanel3);
    }
}