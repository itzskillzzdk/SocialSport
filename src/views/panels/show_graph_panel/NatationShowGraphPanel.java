package views.panels.show_graph_panel;

import chartSports.ChartMusculation;
import chartSports.ChartNatation;
import controller.Controller;
import modules.sports.SportEnum;
import views.panels.GraphiqueSports;

import javax.swing.*;
import java.awt.*;

public class NatationShowGraphPanel extends JPanel {
    private JLabel dureeJL;
    private JButton showDuree;
    private JLabel distanceJL;
    private JButton showDistance;
    private JLabel styleJL;
    private JButton showStyle;
    private Controller controller;
    public NatationShowGraphPanel(Controller controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        setPreferredSize(GraphiqueSports.graphSportPanelDim);
        setMinimumSize(GraphiqueSports.graphSportPanelDim);
        setMaximumSize(GraphiqueSports.graphSportPanelDim);
        setLayout(new GridLayout(1, 3));

        setBorder(BorderFactory.createTitledBorder(SportEnum.NATATION.getValeur()));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        dureeJL = new JLabel("Histogramme de votre durée.");
        dureeJL.setAlignmentX(CENTER_ALIGNMENT);
        showDuree = new JButton(GraphiqueSports.btnPlaceHolder);
        showDuree.addActionListener(e -> {
            new ChartNatation("",controller, ChartNatation.EnumType.DUREE);
        });
        showDuree.setAlignmentX(CENTER_ALIGNMENT);
        panel1.add(Box.createVerticalStrut(30));
        panel1.add(dureeJL);
        panel1.add(Box.createVerticalStrut(5));
        panel1.add(showDuree);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        distanceJL = new JLabel("Histogramme de votre distance nagée.");
        distanceJL.setAlignmentX(CENTER_ALIGNMENT);
        showDistance= new JButton(GraphiqueSports.btnPlaceHolder);
        showDistance.addActionListener(e -> {
            new ChartNatation("",controller, ChartNatation.EnumType.DISTANCE);
        });
        showDistance.setAlignmentX(CENTER_ALIGNMENT);
        panel2.add(Box.createVerticalStrut(30));
        panel2.add(distanceJL);
        panel2.add(Box.createVerticalStrut(5));
        panel2.add(showDistance);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        styleJL = new JLabel("<html>Graphique circulaire de vos styles de nage pratiqués.</html>");
        styleJL.setAlignmentX(CENTER_ALIGNMENT);
        showStyle = new JButton(GraphiqueSports.btnPlaceHolder);
        showStyle.addActionListener(e -> {
            new ChartNatation("",controller, ChartNatation.EnumType.STYLE);
        });
        showStyle.setAlignmentX(CENTER_ALIGNMENT);
        panel3.add(Box.createVerticalStrut(30));
        panel3.add(styleJL);
        panel3.add(Box.createVerticalStrut(5));
        panel3.add(showStyle);

        add(panel1);
        add(panel2);
        add(panel3);
    }
}
