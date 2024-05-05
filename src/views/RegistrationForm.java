package views;

import UI.SportSelectionFrame;
import controller.Controller;
import exception.UtilisateurNonAuthentifieException;
import modules.User;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RegistrationForm extends JFrame {
    JPanel root;
    JLabel welcomeLabel, signUpLabel, sportsLabel, userNameLabel, passwordLabel, confirmPasswordLabel;
    JTextField userNameTextField;
    JPasswordField passwordField, confirmPasswordField;
    JButton confirmButton;
    JLabel image1Label, image2Label;
    private Controller controller;

    public RegistrationForm(Controller controller) {
        this.controller = controller;
        root = new JPanel();
        setContentPane(root);
        root.setLayout(null); // Using absolute layout

        // Calculate the screen size and set the frame size to 3/4 of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.75);
        int height = (int) (screenSize.height * 0.75);
        setSize(width, height);
        setLocationRelativeTo(null); // Center the window

        // Welcome label
        welcomeLabel = new JLabel("Bienvenue !", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 35));
        welcomeLabel.setBounds(10, 10, width - 20, 40);
        root.add(welcomeLabel);

        // Sign-up label
        signUpLabel = new JLabel("Inscrivez-vous au", SwingConstants.CENTER);
        signUpLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        signUpLabel.setBounds(10, 52, width - 20, 40);
        root.add(signUpLabel);

        // Sports label
        sportsLabel = new JLabel("SocialSport", SwingConstants.CENTER);
        sportsLabel.setFont(new Font("Arial", Font.BOLD, 40));
        sportsLabel.setForeground(new Color(255, 105, 180)); // Pink
        sportsLabel.setBounds(10, 90, width - 20, 50);
        root.add(sportsLabel);

        // Username label and text field
        userNameLabel = new JLabel("Nom d'Utilisateur:");
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userNameLabel.setBounds(250, 140, 200, 30);
        root.add(userNameLabel);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(420, 140, 300, 30);
        root.add(userNameTextField);

        // Password label and field
        passwordLabel = new JLabel("Mot de Passe:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setBounds(250, 180, 200, 30);
        root.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(420, 180, 300, 30);
        root.add(passwordField);

        // Confirm password label and field
        confirmPasswordLabel = new JLabel("Confirmez votre mot de passe:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        confirmPasswordLabel.setBounds(250, 220, 200, 30);
        root.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(420, 220, 300, 30);
        root.add(confirmPasswordField);

        // Confirm button
        confirmButton = new JButton("S'inscrire");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setBounds(width / 2 - 100, 330, 200, 40);
        confirmButton.setBackground(Color.BLUE);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.addActionListener(new LoginInputListener(this, controller));
        root.add(confirmButton);

        // Insérer logo
        ImageIcon logoIcon = new ImageIcon("src/photo/logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(750, 0, 200, 200);
        root.add(logoLabel);

        // Insérer l'image 1
        ImageIcon image1Icon = new ImageIcon("src/photo/login1.png");
        image1Label = new JLabel(image1Icon);
        image1Label.setBounds(50, 250, image1Icon.getIconWidth(), image1Icon.getIconHeight());
        root.add(image1Label);

        // Insérer l'image 2
        ImageIcon image2Icon = new ImageIcon("src/photo/login2.png");
        image2Label = new JLabel(image2Icon);
        image2Label.setBounds(650, 250, image2Icon.getIconWidth(), image2Icon.getIconHeight());
        root.add(image2Label);

        // Window settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public String getUsername() {
        return userNameTextField.getText();
    }
    public String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }
    public String getConfirmPassword() {
        return String.valueOf(confirmPasswordField.getPassword());
    }

    private class LoginInputListener implements ActionListener {
        private final Controller controller;
        private final RegistrationForm form;
        public LoginInputListener(RegistrationForm form, Controller controller) {
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
                // && form.getPassword().equals(form.getConfirmPassword())
                if (controller.userExist(form.getUsername())) {
                    JOptionPane.showMessageDialog(RegistrationForm.this,"Ce nom d'utilisateur existe déjà.", "Erreur d'inscription", JOptionPane.ERROR_MESSAGE);
                }else if(!form.getPassword().equals(form.getConfirmPassword()) || form.getPassword().isBlank()) {
                    JOptionPane.showMessageDialog(RegistrationForm.this, "Les mots de passes ne sont pas identique.", "Erreur d'inscription", JOptionPane.ERROR_MESSAGE);
                } else {
                    form.dispose();
                    controller.setCurrentUser(new User(form.getUsername(), form.getPassword()));
                    new SportSelectionFrame(controller).setVisible(true);
                }
            } catch (UtilisateurNonAuthentifieException unae) {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer.");
            }
        }
    }
}