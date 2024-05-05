package views.panels.data_panel;

import controller.Controller;
import controller.data_access_object.DataDAO;
import controller.data_access_object.SportDAO;
import exception.UtilisateurNonAuthentifieException;
import modules.User;
import modules.donnees.DonneeMusculation;
import modules.sports.MusculationExerciceEnum;
import modules.sports.Sport;
import modules.sports.SportEnum;
import views.panels.AjouterDonneePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MusculationDataPanel extends JPanel {

    private JTextField repTF;
    private JComboBox<MusculationExerciceEnum> styleJCB;
    private JTextField poidsLevee;
    private JButton confirmButton;
    private Controller controller;

    public MusculationDataPanel(Controller controller) {
        this.controller = controller;
        setPreferredSize(AjouterDonneePanel.dataSportPanelDim);
        setMinimumSize(AjouterDonneePanel.dataSportPanelDim);
        setMaximumSize(AjouterDonneePanel.dataSportPanelDim);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(BorderFactory.createTitledBorder("Musculation"));

        JPanel panel1 = new JPanel();
        JLabel dureeJL = new JLabel("Répétition: ");
        repTF = new JTextField(10);
        panel1.add(dureeJL);
        panel1.add(repTF);
        panel1.setAlignmentX(CENTER_ALIGNMENT);

        JPanel panel2 = new JPanel();
        JLabel styleJL = new JLabel("Type d'exercice: ");
        styleJCB = new JComboBox<>(MusculationExerciceEnum.values());
        panel2.add(styleJL);
        panel2.add(styleJCB);
        panel2.setAlignmentX(CENTER_ALIGNMENT);

        JPanel panel3 = new JPanel();
        JLabel distanceJL = new JLabel("Poids levés (kg): ");
        poidsLevee = new JTextField(10);
        panel3.add(distanceJL);
        panel3.add(poidsLevee);
        panel3.setAlignmentX(CENTER_ALIGNMENT);

        confirmButton = new JButton("Confirmer");
        confirmButton.setAlignmentX(CENTER_ALIGNMENT);
        confirmButton.addActionListener(new LoginInputListener(this, controller));
        add(panel1);
        add(panel3);
        add(panel2);
        add(confirmButton);
    }

    public JTextField getRepTF() {
        return repTF;
    }

    public JComboBox<MusculationExerciceEnum> getStyleJCB() {
        return styleJCB;
    }

    public JTextField getPoidsLevee() {
        return poidsLevee;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    private static class LoginInputListener implements ActionListener {
        private final Controller controller;
        private final MusculationDataPanel form;
        public LoginInputListener(MusculationDataPanel form, Controller controller) {
            this.controller = controller;
            this.form = form;
        }
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(form.getPoidsLevee().getText().isBlank() || form.getRepTF().getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Remplisser tout les champs.");
                } else {
                    DataDAO dataDAO = DataDAO.getInstance();
                    SportDAO sportDAO = SportDAO.getInstance();
                    Sport sport = sportDAO.findByName(SportEnum.MUSCULATION.getValeur());
                    User user = controller.getCurrentUser();
                    int rep = Integer.parseInt(form.getRepTF().getText());
                    float poids = Float.parseFloat(form.getPoidsLevee().getText());
                    MusculationExerciceEnum exo = (MusculationExerciceEnum) form.getStyleJCB().getSelectedItem();
                    dataDAO.save(new DonneeMusculation(sport, user, rep, poids, exo, new Date()));
                    JOptionPane.showMessageDialog(null, "Donnée " + sport.getNom() + " bien enregistrée.");
                }
            } catch (UtilisateurNonAuthentifieException unae) {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer.");
            }
        }
    }
}