package views.panels.show_graph_panel;

import chartSports.ChartCyclisme;
import controller.Controller;
import modules.sports.SportEnum;
import views.panels.GraphiqueSports;

import javax.swing.*;
import java.awt.*;

public class CyclismeShowGraphPanel extends JPanel {
    private JLabel dureeJL;
    private JButton showDuree;
    private JLabel distanceJL;
    private JButton showDistance;
    private JLabel vitesseJL;
    private JButton showVitesse;
    private JLabel vitesseCombinedJL;
    private JButton showVitesseCombined;
    private Controller controller;
    public CyclismeShowGraphPanel(Controller controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        setPreferredSize(GraphiqueSports.graphSportPanelDim);
        setMinimumSize(GraphiqueSports.graphSportPanelDim);
        setMaximumSize(GraphiqueSports.graphSportPanelDim);
        setLayout(new GridLayout(1, 4));

        setBorder(BorderFactory.createTitledBorder(SportEnum.CYCLISME.getValeur()));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        dureeJL = new JLabel("Histogramme de votre durée.");
        dureeJL.setAlignmentX(CENTER_ALIGNMENT);
        showDuree = new JButton(GraphiqueSports.btnPlaceHolder);
        showDuree.addActionListener(e -> {
            new ChartCyclisme("",controller, ChartCyclisme.EnumType.DUREE);
        });
        showDuree.setAlignmentX(CENTER_ALIGNMENT);
        panel1.add(Box.createVerticalStrut(30));
        panel1.add(dureeJL);
        panel1.add(Box.createVerticalStrut(5));
        panel1.add(showDuree);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        distanceJL = new JLabel("Histogramme de votre distance parcourue.");
        distanceJL.setAlignmentX(CENTER_ALIGNMENT);
        showDistance = new JButton(GraphiqueSports.btnPlaceHolder);
        showDistance.addActionListener(e -> {
            new ChartCyclisme("",controller, ChartCyclisme.EnumType.DISTANCE);
        });
        showDistance.setAlignmentX(CENTER_ALIGNMENT);
        panel2.add(Box.createVerticalStrut(30));
        panel2.add(distanceJL);
        panel2.add(Box.createVerticalStrut(5));
        panel2.add(showDistance);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        vitesseJL = new JLabel("Graphique de votre vitesse moyenne.");
        vitesseJL.setAlignmentX(CENTER_ALIGNMENT);
        showVitesse = new JButton(GraphiqueSports.btnPlaceHolder);
        showVitesse.addActionListener(e -> {
            new ChartCyclisme("",controller, ChartCyclisme.EnumType.VITESSE);
        });
        showVitesse.setAlignmentX(CENTER_ALIGNMENT);
        panel3.add(Box.createVerticalStrut(30));
        panel3.add(vitesseJL);
        panel3.add(Box.createVerticalStrut(5));
        panel3.add(showVitesse);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        vitesseCombinedJL = new JLabel("Votre vitesse avec votre ami.");
        vitesseCombinedJL.setAlignmentX(CENTER_ALIGNMENT);
        showVitesseCombined = new JButton(GraphiqueSports.btnPlaceHolder);
        showVitesseCombined.addActionListener(e -> {
            new ChartCyclisme("",controller, ChartCyclisme.EnumType.VITESSECOMBINED);
        });
        showVitesseCombined.setAlignmentX(CENTER_ALIGNMENT);
        panel4.add(Box.createVerticalStrut(30));
        panel4.add(vitesseCombinedJL);
        panel4.add(Box.createVerticalStrut(5));
        panel4.add(showVitesseCombined);

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);

    }
}
