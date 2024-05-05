package views.panels.show_graph_panel;

import chartSports.ChartCyclisme;
import chartSports.ChartEscalade;
import controller.Controller;
import modules.sports.SportEnum;
import views.panels.GraphiqueSports;

import javax.swing.*;
import java.awt.*;

public class EscaladeShowGraphPanel extends JPanel {
    private JLabel dureeJL;
    private JButton showDuree;
    private JLabel hauteurJL;
    private JButton showHauteur;
    private JLabel typeJL;
    private JButton showType;
    private Controller controller;
    public EscaladeShowGraphPanel(Controller controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        setPreferredSize(GraphiqueSports.graphSportPanelDim);
        setMinimumSize(GraphiqueSports.graphSportPanelDim);
        setMaximumSize(GraphiqueSports.graphSportPanelDim);
        setLayout(new GridLayout(1, 3));

        setBorder(BorderFactory.createTitledBorder(SportEnum.ESCALADE.getValeur()));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        dureeJL = new JLabel("Histogramme de votre durée.");
        dureeJL.setAlignmentX(CENTER_ALIGNMENT);
        showDuree = new JButton(GraphiqueSports.btnPlaceHolder);
        showDuree.addActionListener(e -> {
            new ChartEscalade("",controller, ChartEscalade.EnumType.DUREE);
        });
        showDuree.setAlignmentX(CENTER_ALIGNMENT);
        panel1.add(Box.createVerticalStrut(30));
        panel1.add(dureeJL);
        panel1.add(Box.createVerticalStrut(5));
        panel1.add(showDuree);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        hauteurJL = new JLabel("Histogramme de votre hauteur grimper.");
        hauteurJL.setAlignmentX(CENTER_ALIGNMENT);
        showHauteur = new JButton(GraphiqueSports.btnPlaceHolder);
        showHauteur.addActionListener(e -> {
            new ChartEscalade("",controller, ChartEscalade.EnumType.HAUTEUR);
        });
        showHauteur.setAlignmentX(CENTER_ALIGNMENT);
        panel2.add(Box.createVerticalStrut(30));
        panel2.add(hauteurJL);
        panel2.add(Box.createVerticalStrut(5));
        panel2.add(showHauteur);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        typeJL = new JLabel("<html>Graphique circulaire de vos types d'escalade pratiqués.</html>");
        typeJL.setAlignmentX(CENTER_ALIGNMENT);
        showType = new JButton(GraphiqueSports.btnPlaceHolder);
        showType.addActionListener(e -> {
            new ChartEscalade("",controller, ChartEscalade.EnumType.TYPE);
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
