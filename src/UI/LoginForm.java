package UI;

import controller.Controller;
import controller.data_access_object.UserDAO;
import exception.UtilisateurNonAuthentifieException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LoginForm extends JFrame {
    JPanel root = new JPanel();
    JLabel welcomeLabel, signInLabel, sportsLabel, usernameLabel, passwordLabel;
    private JTextField usernameTextField;
    JPasswordField passwordField;
    JButton loginButton;
    JLabel image1Label, image2Label;
    private final Controller controller;

    public LoginForm(Controller controller) {
        this.controller = controller;
        setContentPane(root);
        root.setLayout(null); // Using absolute layout

        // Calculate the screen size and set the frame size to 3/4 of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.75);
        int height = (int) (screenSize.height * 0.75);
        setSize(width, height);
        setLocationRelativeTo(null); // Center the window

        // Welcome label
        welcomeLabel = new JLabel("Bienvenue!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Increased font size
        welcomeLabel.setBounds(10, 10, width - 20, 35);
        root.add(welcomeLabel);

        // Sign-in label
        signInLabel = new JLabel("Connectez-vous à votre compte", SwingConstants.CENTER);
        signInLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // Increased font size
        signInLabel.setBounds(10, 55, width - 20, 30);
        root.add(signInLabel);

        // Sports label
        sportsLabel = new JLabel("Tous Sports Individuels", SwingConstants.CENTER);
        sportsLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Increased font size
        sportsLabel.setForeground(new Color(255, 105, 180)); // Pink
        sportsLabel.setBounds(10, 95, width - 20, 35);
        root.add(sportsLabel);

        // Username label and text field
        usernameLabel = new JLabel("Nom d'Utilisateur:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Increased font size
        usernameLabel.setBounds(300, 160, 200, 30);
        root.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(450, 160, 300, 30);
        usernameTextField.getDocument().addDocumentListener(new InputListener(usernameTextField));
        root.add(usernameTextField);

        // Password label and field
        passwordLabel = new JLabel("Mot de Passe:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Increased font size
        passwordLabel.setBounds(300, 220, 200, 30);
        root.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(450, 220, 300, 30);
        root.add(passwordField);

        // Login button
        loginButton = new JButton("Se Connecter");
        loginButton.setBounds(450, 300, 200, 40);
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new LoginInputListener(this, controller));

        root.add(loginButton);

        // Insérer l'image 1
        ImageIcon image1Icon = new ImageIcon("./src/photo/login1.png");
        image1Label = new JLabel(image1Icon);
        image1Label.setBounds(50, 200, image1Icon.getIconWidth(), image1Icon.getIconHeight());
        root.add(image1Label);

        // Insérer l'image 2
        ImageIcon image2Icon = new ImageIcon("./src/photo/login2.png");
        image2Label = new JLabel(image2Icon);
        image2Label.setBounds(650, 200, image2Icon.getIconWidth(), image2Icon.getIconHeight());
        root.add(image2Label);

        // Window settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }

    private static class LoginInputListener implements ActionListener {

        private final Controller controller;
        private final LoginForm form;

        public LoginInputListener(LoginForm form, Controller controller) {
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
                controller.authentification(form.getUsername(), form.getPassword());
                form.dispose();
            } catch (UtilisateurNonAuthentifieException unae) {
                JOptionPane.showMessageDialog(null, "Mauvais login veuillez réessayer.");
            }
        }
    }

    private static class InputListener implements DocumentListener {

        private final JTextField usernameInput;

        public InputListener(JTextField usernameInput) {
            this.usernameInput = usernameInput;
        }

        private void handleChange() {
            System.out.println("Text : " + usernameInput.getText());
        }


        /**
         * Gives notification that there was an insert into the document.  The
         * range given by the DocumentEvent bounds the freshly inserted region.
         *
         * @param e the document event
         */
        @Override
        public void insertUpdate(DocumentEvent e) {
            this.handleChange();
        }

        /**
         * Gives notification that a portion of the document has been
         * removed.  The range is given in terms of what the view last
         * saw (that is, before updating sticky positions).
         *
         * @param e the document event
         */
        @Override
        public void removeUpdate(DocumentEvent e) {
            this.handleChange();
        }

        /**
         * Gives notification that an attribute or set of attributes changed.
         *
         * @param e the document event
         */
        @Override
        public void changedUpdate(DocumentEvent e) {
            this.handleChange();
        }
    }
}