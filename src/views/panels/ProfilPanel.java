package views.panels;

import controller.Controller;
import modules.sports.Sport;
import views.panels.data_panel.SportDataPanelFactory;
import views.panels.stats_panel.SportStatsPanelFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProfilPanel extends Panel {
    public static final Dimension dataSportPanelDim = new Dimension(500, 150);
    private Controller controller;
    public ProfilPanel(Controller controller) {
        this.controller = controller;
        init(controller.getCurrentUser().getSportList());
    }

    private void init(List<Sport> selectedSports) {
        setPreferredSize(MainPanel.mainPanelDim);
        setMinimumSize(MainPanel.mainPanelDim);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleJL = new JLabel("Statistiques Globales: ");
        titleJL.setFont(new Font("Arial", Font.BOLD,24));
        titleJL.setAlignmentX(CENTER_ALIGNMENT);

        JPanel sportStatsPanel = new JPanel();
        sportStatsPanel.setLayout(new BoxLayout(sportStatsPanel,BoxLayout.Y_AXIS));
        sportStatsPanel.add(Box.createVerticalStrut(10));
        sportStatsPanel.add(SportStatsPanelFactory.createStatsPanel(controller.getCurrentUser().getSportList().get(0).getNom().getValeur(), controller));
        sportStatsPanel.add(Box.createVerticalStrut(10));
        sportStatsPanel.add(SportStatsPanelFactory.createStatsPanel(controller.getCurrentUser().getSportList().get(1).getNom().getValeur(), controller));
        sportStatsPanel.add(Box.createVerticalStrut(10));
        sportStatsPanel.add(SportStatsPanelFactory.createStatsPanel(controller.getCurrentUser().getSportList().get(2).getNom().getValeur(), controller));

        add(Box.createVerticalStrut(10));
        add(titleJL);
        add(Box.createVerticalStrut(10));
        add(sportStatsPanel);
    }
}
