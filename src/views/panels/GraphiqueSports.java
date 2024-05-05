package views.panels;

import controller.Controller;
import views.panels.MainPanel;
import views.panels.show_graph_panel.GraphiqueSportPanelFactory;

import javax.swing.*;
import java.awt.*;

public class GraphiqueSports extends JPanel {
    public static final Dimension graphFrameDim = new Dimension(500,270);
    public static final Dimension graphSportPanelDim = new Dimension(800, 150);
    public static final String btnPlaceHolder = "Afficher le graphique";
    private Controller controller;
    public GraphiqueSports(Controller controller) {
        this.controller = controller;
        init();
    }
    private void init() {
        setPreferredSize(MainPanel.mainPanelDim);
        setMinimumSize(MainPanel.mainPanelDim);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleJL = new JLabel("Graphiques par sport: ");
        titleJL.setFont(new Font("Arial", Font.BOLD,24));
        titleJL.setAlignmentX(CENTER_ALIGNMENT);

        JPanel sportGraphPanel1 = GraphiqueSportPanelFactory.createShowGraphPanel(controller.getCurrentUser().getSportList().get(0).getNom().getValeur(), controller);
        JPanel sportGraphPanel2 = GraphiqueSportPanelFactory.createShowGraphPanel(controller.getCurrentUser().getSportList().get(1).getNom().getValeur(), controller);
        JPanel sportGraphPanel3 = GraphiqueSportPanelFactory.createShowGraphPanel(controller.getCurrentUser().getSportList().get(2).getNom().getValeur(), controller);
        sportGraphPanel1.setAlignmentX(CENTER_ALIGNMENT);
        sportGraphPanel2.setAlignmentX(CENTER_ALIGNMENT);
        sportGraphPanel3.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(10));
        add(titleJL);
        add(Box.createVerticalStrut(10));
        add(sportGraphPanel1);
        add(Box.createVerticalStrut(10));
        add(sportGraphPanel2);
        add(Box.createVerticalStrut(10));
        add(sportGraphPanel3);
    }
}