package views.panels.stats_panel;

import controller.Controller;
import controller.data_access_object.DataDAO;
import modules.donnees.DonneeMusculation;
import modules.donnees.DonneeNatation;
import modules.sports.EscaladeTypeEnum;
import modules.sports.MusculationExerciceEnum;
import modules.sports.NatationStyleEnum;
import modules.sports.SportEnum;
import views.panels.ProfilPanel;
import views.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MusculationStatsPanel extends JPanel {
    // leftPanel
    private JLabel sportPicture;
    // rightPanel
    private JLabel sportJL;
    private JLabel repetitionJL;
    private JLabel poidsLeveeMaxJL;
    private Controller controller;
    private List<DonneeMusculation> data;
    public MusculationStatsPanel(Controller controller) {
        this.controller = controller;
        data = DataDAO.getInstance().getDataMusculation(controller.getCurrentUser());
        init();
    }
    private void init() {
        setPreferredSize(ProfilPanel.dataSportPanelDim);
        setMinimumSize(ProfilPanel.dataSportPanelDim);
        setMaximumSize(ProfilPanel.dataSportPanelDim);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        int width = (int)ProfilPanel.dataSportPanelDim.getWidth();
        int height = (int)ProfilPanel.dataSportPanelDim.getHeight();

        // data
        int rep = 0;
        float poids = 0f;
        MusculationExerciceEnum type;
        for(DonneeMusculation d : data) {
            rep += d.getRepetition();
            poids += d.getPoidsLeve();
        }

        // leftPanel
        Dimension leftDimension = new Dimension(height, height);
        leftPanel.setPreferredSize(leftDimension);
        leftPanel.setMinimumSize(leftDimension);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        sportPicture = new JLabel(Utils.getResizeImage("src/photo/Musculation.png",128, 128));
        sportPicture.setAlignmentX(CENTER_ALIGNMENT);
        sportPicture.setAlignmentY(CENTER_ALIGNMENT);
        leftPanel.add(sportPicture);

        // rightPanel
        Dimension rightDimension = new Dimension(width - height, height);
        rightPanel.setPreferredSize(rightDimension);
        rightPanel.setMinimumSize(rightDimension);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        sportJL = new JLabel(SportEnum.MUSCULATION.getValeur());
        sportJL.setFont(new Font("Arial", Font.BOLD, 24));
        // Modifier les données ici !
        repetitionJL = new JLabel("Nombres répétitions: " + rep);
        repetitionJL.setFont(new Font("Arial", Font.ITALIC, 14));
        poidsLeveeMaxJL = new JLabel("Poids maximum levé: " + poids);
        poidsLeveeMaxJL.setFont(new Font("Arial", Font.ITALIC, 14));

        rightPanel.add(sportJL);
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(repetitionJL);
        rightPanel.add(poidsLeveeMaxJL);
        rightPanel.setOpaque(false);

        setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

        add(leftPanel);
        add(rightPanel);
    }
    public JLabel getSportPicture() {
        return sportPicture;
    }
    public JLabel getSportJL() {
        return sportJL;
    }
    public JLabel getRepetitionJL() {
        return repetitionJL;
    }
    public JLabel getPoidsLeveeMaxJL() {
        return poidsLeveeMaxJL;
    }
}
