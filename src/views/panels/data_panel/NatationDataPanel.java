package views.panels.data_panel;

import controller.Controller;
import controller.data_access_object.DataDAO;
import controller.data_access_object.SportDAO;
import exception.UtilisateurNonAuthentifieException;
import modules.User;
import modules.donnees.DonneeNatation;
import modules.sports.NatationStyleEnum;
import modules.sports.Sport;
import modules.sports.SportEnum;
import views.panels.AjouterDonneePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class NatationDataPanel extends JPanel {

    private JTextField dureeTF;
    private JComboBox<NatationStyleEnum>  styleJCB;
    private JTextField distanceTF;
    private JButton confirmButton;
    private Controller controller;
    public NatationDataPanel(Controller controller) {
        this.controller = controller;

        setPreferredSize(AjouterDonneePanel.dataSportPanelDim);
        setMinimumSize(AjouterDonneePanel.dataSportPanelDim);
        setMaximumSize(AjouterDonneePanel.dataSportPanelDim);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(BorderFactory.createTitledBorder("Natation"));

        JPanel panel1 = new JPanel();
        JLabel dureeJL = new JLabel("Durée (min): ");
        dureeTF = new JTextField(10);
        panel1.add(dureeJL);
        panel1.add(dureeTF);
        panel1.setAlignmentX(CENTER_ALIGNMENT);

        JPanel panel2 = new JPanel();
        JLabel styleJL = new JLabel("Style: ");
        styleJCB = new JComboBox<>(NatationStyleEnum.values());
        panel2.add(styleJL);
        panel2.add(styleJCB);
        panel2.setAlignmentX(CENTER_ALIGNMENT);

        JPanel panel3 = new JPanel();
        JLabel distanceJL = new JLabel("Distance (m): ");
        distanceTF = new JTextField(10);
        panel3.add(distanceJL);
        panel3.add(distanceTF);
        panel3.setAlignmentX(CENTER_ALIGNMENT);

        confirmButton = new JButton("Confirmer");
        confirmButton.setAlignmentX(CENTER_ALIGNMENT);
        confirmButton.addActionListener(new LoginInputListener(this, controller));

        add(panel1);
        add(panel3);
        add(panel2);
        add(confirmButton);
    }

    public JTextField getDureeTF() {
        return dureeTF;
    }

    public JComboBox<NatationStyleEnum> getStyleJCB() {
        return styleJCB;
    }

    public JTextField getDistanceTF() {
        return distanceTF;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    private static class LoginInputListener implements ActionListener {
        private final Controller controller;
        private final NatationDataPanel form;
        public LoginInputListener(NatationDataPanel form, Controller controller) {
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
                if(form.getDistanceTF().getText().isBlank() || form.getDureeTF().getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Remplisser tout les champs.");
                } else {
                    DataDAO dataDAO = DataDAO.getInstance();
                    SportDAO sportDAO = SportDAO.getInstance();
                    Sport sport = sportDAO.findByName(SportEnum.NATATION.getValeur());
                    User user = controller.getCurrentUser();
                    String duree = form.getDureeTF().getText();
                    NatationStyleEnum style = (NatationStyleEnum) form.getStyleJCB().getSelectedItem();
                    float distance = Float.parseFloat(form.getDistanceTF().getText());
                    dataDAO.save(new DonneeNatation(sport, user, duree, distance, style, new Date()));
                    JOptionPane.showMessageDialog(null, "Donnée " + sport.getNom() + " bien enregistrée.");
                }
            } catch (UtilisateurNonAuthentifieException unae) {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer.");
            }
        }
    }
}
