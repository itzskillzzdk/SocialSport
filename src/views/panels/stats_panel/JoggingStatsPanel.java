package views.panels.stats_panel;

import controller.Controller;
import controller.data_access_object.DataDAO;
import modules.donnees.DonneeJogging;
import modules.donnees.DonneeMusculation;
import modules.sports.MusculationExerciceEnum;
import modules.sports.SportEnum;
import views.panels.ProfilPanel;
import views.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JoggingStatsPanel extends JPanel {
    // leftPanel
    private JLabel sportPicture;
    // rightPanel
    private JLabel sportJL;
    private JLabel dureeJL;
    private JLabel distanceJL;
    private Controller controller;
    List<DonneeJogging> data;
    public JoggingStatsPanel(Controller controller) {
        this.controller = controller;
        data = DataDAO.getInstance().getDataJogging(controller.getCurrentUser());
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
        int duree = 0;
        float distance = 0f;
        for(DonneeJogging d : data) {
            duree += Integer.parseInt(d.getDuree());
            distance += d.getDistance();
        }

        // leftPanel
        Dimension leftDimension = new Dimension(height, height);
        leftPanel.setPreferredSize(leftDimension);
        leftPanel.setMinimumSize(leftDimension);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        sportPicture = new JLabel(Utils.getResizeImage("src/photo/Jogging.png",128, 128));
        sportPicture.setAlignmentX(CENTER_ALIGNMENT);
        sportPicture.setAlignmentY(CENTER_ALIGNMENT);
        leftPanel.add(sportPicture);

        // rightPanel
        Dimension rightDimension = new Dimension(width - height, height);
        rightPanel.setPreferredSize(rightDimension);
        rightPanel.setMinimumSize(rightDimension);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        sportJL = new JLabel(SportEnum.JOGGING.getValeur());
        sportJL.setFont(new Font("Arial", Font.BOLD, 24));
        // Modifier les données ici !
        dureeJL = new JLabel("Durée (min): " + duree);
        dureeJL.setFont(new Font("Arial", Font.ITALIC, 14));
        distanceJL = new JLabel("Distance (m): " + distance);
        distanceJL.setFont(new Font("Arial", Font.ITALIC, 14));
        rightPanel.add(sportJL);
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(dureeJL);
        rightPanel.add(distanceJL);
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

    public JLabel getDureeJL() {
        return dureeJL;
    }

    public JLabel getDistanceJL() {
        return distanceJL;
    }
}
