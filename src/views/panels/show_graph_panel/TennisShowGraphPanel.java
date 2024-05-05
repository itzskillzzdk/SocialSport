package views.panels.show_graph_panel;

import chartSports.ChartNatation;
import chartSports.ChartTennis;
import controller.Controller;
import modules.sports.SportEnum;
import views.panels.GraphiqueSports;

import javax.swing.*;
import java.awt.*;

public class TennisShowGraphPanel extends JPanel {
    private JLabel dureeJL;
    private JButton showDuree;
    private JLabel serviceJL;
    private JButton showService;
    private JLabel jeuxGagneeJL;
    private JButton showJeuxGagnee;
    private Controller controller;
    public TennisShowGraphPanel(Controller controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        setPreferredSize(GraphiqueSports.graphSportPanelDim);
        setMinimumSize(GraphiqueSports.graphSportPanelDim);
        setMaximumSize(GraphiqueSports.graphSportPanelDim);
        setLayout(new GridLayout(1, 3));

        setBorder(BorderFactory.createTitledBorder(SportEnum.TENNIS.getValeur()));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        dureeJL = new JLabel("Graphique de votre durée.");
        dureeJL.setAlignmentX(CENTER_ALIGNMENT);
        showDuree = new JButton(GraphiqueSports.btnPlaceHolder);
        showDuree.addActionListener(e -> {
            new ChartTennis("",controller, ChartTennis.EnumType.DUREE);
        });
        showDuree.setAlignmentX(CENTER_ALIGNMENT);
        panel1.add(Box.createVerticalStrut(30));
        panel1.add(dureeJL);
        panel1.add(Box.createVerticalStrut(5));
        panel1.add(showDuree);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        serviceJL = new JLabel("<html>Histogramme de vos nombres de service réussis.</html>");
        serviceJL.setAlignmentX(CENTER_ALIGNMENT);
        showService = new JButton(GraphiqueSports.btnPlaceHolder);
        showService.addActionListener(e -> {
            new ChartTennis("",controller, ChartTennis.EnumType.SERVICE);
        });
        showService.setAlignmentX(CENTER_ALIGNMENT);
        panel2.add(Box.createVerticalStrut(30));
        panel2.add(serviceJL);
        panel2.add(Box.createVerticalStrut(5));
        panel2.add(showService);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        jeuxGagneeJL = new JLabel("<html>Histogramme de vos nombres de jeux réussis.</html>");
        jeuxGagneeJL.setAlignmentX(CENTER_ALIGNMENT);
        showJeuxGagnee = new JButton(GraphiqueSports.btnPlaceHolder);
        showJeuxGagnee.addActionListener(e -> {
            new ChartTennis("",controller, ChartTennis.EnumType.JEUX);
        });
        showJeuxGagnee.setAlignmentX(CENTER_ALIGNMENT);
        panel3.add(Box.createVerticalStrut(30));
        panel3.add(jeuxGagneeJL);
        panel3.add(Box.createVerticalStrut(5));
        panel3.add(showJeuxGagnee);

        add(panel1);
        add(panel2);
        add(panel3);
    }
}
