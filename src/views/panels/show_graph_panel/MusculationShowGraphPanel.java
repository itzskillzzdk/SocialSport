package views.panels.show_graph_panel;

import chartSports.ChartJogging;
import chartSports.ChartMusculation;
import controller.Controller;
import modules.sports.SportEnum;
import views.panels.GraphiqueSports;

import javax.swing.*;
import java.awt.*;

public class MusculationShowGraphPanel extends JPanel {
    private JLabel repetionJL;
    private JButton showRepetion;
    private JLabel poidsLeveeJL;
    private JButton showPoidsLevee;
    private JLabel typeJL;
    private JButton showType;
    private Controller controller;
    public MusculationShowGraphPanel(Controller controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        setPreferredSize(GraphiqueSports.graphSportPanelDim);
        setMinimumSize(GraphiqueSports.graphSportPanelDim);
        setMaximumSize(GraphiqueSports.graphSportPanelDim);
        setLayout(new GridLayout(1, 3));

        setBorder(BorderFactory.createTitledBorder(SportEnum.MUSCULATION.getValeur()));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        repetionJL = new JLabel("Histogramme de vos répétitions.");
        repetionJL.setAlignmentX(CENTER_ALIGNMENT);
        showRepetion = new JButton(GraphiqueSports.btnPlaceHolder);
        showRepetion.addActionListener(e -> {
            new ChartMusculation("",controller, ChartMusculation.EnumType.REPETITION);
        });
        showRepetion.setAlignmentX(CENTER_ALIGNMENT);
        panel1.add(Box.createVerticalStrut(30));
        panel1.add(repetionJL);
        panel1.add(Box.createVerticalStrut(5));
        panel1.add(showRepetion);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        poidsLeveeJL = new JLabel("Graphique du poids levés par jour.");
        poidsLeveeJL.setAlignmentX(CENTER_ALIGNMENT);
        showPoidsLevee = new JButton(GraphiqueSports.btnPlaceHolder);
        showPoidsLevee.addActionListener(e -> {
            new ChartMusculation("",controller, ChartMusculation.EnumType.POIDS);
        });
        showPoidsLevee.setAlignmentX(CENTER_ALIGNMENT);
        panel2.add(Box.createVerticalStrut(30));
        panel2.add(poidsLeveeJL);
        panel2.add(Box.createVerticalStrut(5));
        panel2.add(showPoidsLevee);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        typeJL = new JLabel("<html>Graphique circulaire de vos types d'exercice pratiqués.</html>");
        typeJL.setAlignmentX(CENTER_ALIGNMENT);
        showType = new JButton(GraphiqueSports.btnPlaceHolder);
        showType.addActionListener(e -> {
            new ChartMusculation("",controller, ChartMusculation.EnumType.TYPE);
        });
        showType.setAlignmentX(CENTER_ALIGNMENT);
        panel3.add(Box.createVerticalStrut(30));
        panel3.add(typeJL);
        panel3.add(Box.createVerticalStrut(5));
        panel3.add(showType);

        add(panel1);
        add(panel2);
        add(panel3);
    }
}
