package views.panels.data_panel;

import controller.Controller;
import controller.data_access_object.DataDAO;
import controller.data_access_object.SportDAO;
import exception.UtilisateurNonAuthentifieException;
import modules.User;
import modules.donnees.DonneeTennis;
import modules.sports.Sport;
import modules.sports.SportEnum;
import views.panels.AjouterDonneePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TennisDataPanel extends JPanel {

    private JTextField dureeTF;
    private JTextField  jeuxGagneeTF;
    private JTextField serviceReussiTF;
    private JButton confirmButton;
    private Controller controller;
    public TennisDataPanel(Controller controller) {
        this.controller = controller;

        setPreferredSize(AjouterDonneePanel.dataSportPanelDim);
        setMinimumSize(AjouterDonneePanel.dataSportPanelDim);
        setMaximumSize(AjouterDonneePanel.dataSportPanelDim);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(BorderFactory.createTitledBorder("Tennis"));

        JPanel panel1 = new JPanel();
        JLabel dureeJL = new JLabel("Durée (min): ");
        dureeTF = new JTextField(10);
        panel1.add(dureeJL);
        panel1.add(dureeTF);
        panel1.setAlignmentX(CENTER_ALIGNMENT);

        JPanel panel2 = new JPanel();
        JLabel styleJL = new JLabel("Jeux gagnés: ");
        jeuxGagneeTF = new JTextField(10);
        panel2.add(styleJL);
        panel2.add(jeuxGagneeTF);
        panel2.setAlignmentX(CENTER_ALIGNMENT);

        JPanel panel3 = new JPanel();
        JLabel distanceJL = new JLabel("Nombre de services réussis:  ");
        serviceReussiTF = new JTextField(10);
        panel3.add(distanceJL);
        panel3.add(serviceReussiTF);
        panel3.setAlignmentX(CENTER_ALIGNMENT);

        confirmButton = new JButton("Confirmer");
        confirmButton.setAlignmentX(CENTER_ALIGNMENT);
        confirmButton.addActionListener(new LoginInputListener(this, controller));
        add(panel1);
        add(panel3);
        add(panel2);
        add(confirmButton);
    }

    public JTextField getJeuxGagneeTF() {
        return jeuxGagneeTF;
    }

    public JTextField getServiceReussiTF() {
        return serviceReussiTF;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JTextField getDureeTF() {
        return dureeTF;
    }

    private static class LoginInputListener implements ActionListener {
        private final Controller controller;
        private final TennisDataPanel form;
        public LoginInputListener(TennisDataPanel form, Controller controller) {
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
                if(form.getJeuxGagneeTF().getText().isBlank() || form.getDureeTF().getText().isBlank() || form.getServiceReussiTF().getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Remplisser tout les champs.");
                } else {
                    DataDAO dataDAO = DataDAO.getInstance();
                    SportDAO sportDAO = SportDAO.getInstance();
                    Sport sport = sportDAO.findByName(SportEnum.TENNIS.getValeur());
                    User user = controller.getCurrentUser();
                    String duree = form.getDureeTF().getText();
                    int jeuxGagnee = Integer.parseInt(form.getJeuxGagneeTF().getText());
                    int service = Integer.parseInt(form.getServiceReussiTF().getText());
                    dataDAO.save(new DonneeTennis(sport, user, duree, jeuxGagnee, service, new Date()));
                    JOptionPane.showMessageDialog(null, "Donnée " + sport.getNom() + " bien enregistrée.");
                }
            } catch (UtilisateurNonAuthentifieException unae) {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer.");
            }
        }
    }
}
