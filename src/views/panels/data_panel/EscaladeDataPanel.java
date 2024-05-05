package views.panels.data_panel;

import controller.Controller;
import controller.data_access_object.DataDAO;
import controller.data_access_object.SportDAO;
import exception.UtilisateurNonAuthentifieException;
import modules.User;
import modules.donnees.DonneeEscalade;
import modules.sports.*;
import views.panels.AjouterDonneePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class EscaladeDataPanel extends JPanel {

    private JTextField duree;
    private JComboBox<EscaladeTypeEnum> styleJCB;
    private JTextField hauteur;
    private JButton confirmButton;
    private Controller controller;

    public EscaladeDataPanel(Controller controller) {
        this.controller = controller;
        setPreferredSize(AjouterDonneePanel.dataSportPanelDim);
        setMinimumSize(AjouterDonneePanel.dataSportPanelDim);
        setMaximumSize(AjouterDonneePanel.dataSportPanelDim);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(BorderFactory.createTitledBorder("Escalade"));

        JPanel panel1 = new JPanel();
        JLabel dureeJL = new JLabel("Durée (min): ");
        duree = new JTextField(10);
        panel1.add(dureeJL);
        panel1.add(duree);
        panel1.setAlignmentX(CENTER_ALIGNMENT);

        JPanel panel2 = new JPanel();
        JLabel styleJL = new JLabel("Type d'escalade: ");
        styleJCB = new JComboBox<>(EscaladeTypeEnum.values());
        panel2.add(styleJL);
        panel2.add(styleJCB);
        panel2.setAlignmentX(CENTER_ALIGNMENT);

        JPanel panel3 = new JPanel();
        JLabel distanceJL = new JLabel("Hauteur (m): ");
        hauteur = new JTextField(10);
        panel3.add(distanceJL);
        panel3.add(hauteur);
        panel3.setAlignmentX(CENTER_ALIGNMENT);

        confirmButton = new JButton("Confirmer");
        confirmButton.setAlignmentX(CENTER_ALIGNMENT);
        confirmButton.addActionListener(new LoginInputListener(this, controller));
        add(panel1);
        add(panel3);
        add(panel2);
        add(confirmButton);
    }

    public JTextField getDuree() {
        return duree;
    }

    public JComboBox<EscaladeTypeEnum> getStyleJCB() {
        return styleJCB;
    }

    public JTextField getHauteur() {
        return hauteur;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    private static class LoginInputListener implements ActionListener {
        private final Controller controller;
        private final EscaladeDataPanel form;
        public LoginInputListener(EscaladeDataPanel form, Controller controller) {
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
                if(form.getHauteur().getText().isBlank() || form.getDuree().getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Remplisser tout les champs.");
                } else {
                    DataDAO dataDAO = DataDAO.getInstance();
                    SportDAO sportDAO = SportDAO.getInstance();
                    Sport sport = sportDAO.findByName(SportEnum.ESCALADE.getValeur());
                    User user = controller.getCurrentUser();
                    String duree = form.getDuree().getText();
                    EscaladeTypeEnum style = (EscaladeTypeEnum) form.getStyleJCB().getSelectedItem();
                    float hauteur = Float.parseFloat(form.getHauteur().getText());
                    dataDAO.save(new DonneeEscalade(sport, user, duree, hauteur, style, new Date()));
                    JOptionPane.showMessageDialog(null, "Donnée " + sport.getNom() + " bien enregistrée.");
                }
            } catch (UtilisateurNonAuthentifieException unae) {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer.");
            }
        }
    }
}
