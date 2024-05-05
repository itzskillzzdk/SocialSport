package UI;

import controller.Controller;
import views.RegistrationForm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomePage extends JFrame {
    JPanel root;
    JLabel welcomeLabel, sportsLabel, image1Label, image2Label;
    JButton loginButton, signupButton;

    private Controller controller;

    public HomePage(Controller controller) {
        root = new JPanel();
        setContentPane(root);
        root.setLayout(null);

        welcomeLabel = new JLabel("Bienvenue!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 35));
        welcomeLabel.setBounds(80, 10, 600, 60);
        root.add(welcomeLabel);

        sportsLabel = new JLabel("SocialSport", SwingConstants.CENTER);
        sportsLabel.setFont(new Font("Arial", Font.BOLD, 45));
        sportsLabel.setForeground(new Color(255, 105, 180));
        sportsLabel.setBounds(80, 80, 600, 60);
        root.add(sportsLabel);

        loginButton = new JButton("Se Connecter");
        loginButton.setBounds(270, 180, 200, 50);
        loginButton.setFont(new Font("Time New Romans", Font.BOLD, 16));
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        root.add(loginButton);

        signupButton = new JButton("S'inscrire");
        signupButton.setBounds(270, 260, 200, 50);
        signupButton.setFont(new Font("Time New Romans", Font.BOLD, 16));
        signupButton.setForeground(Color.BLUE);
        signupButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        signupButton.setBackground(Color.WHITE);
        root.add(signupButton);

        ImageIcon logoIcon = new ImageIcon("src/photo/logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(500, -10, 200, 200);
        root.add(logoLabel);

        ImageIcon image1Icon = new ImageIcon("src/photo/home1.png");
        image1Label = new JLabel(image1Icon);
        image1Label.setBounds(10, 150, image1Icon.getIconWidth(), image1Icon.getIconHeight());
        root.add(image1Label);

        ImageIcon image2Icon = new ImageIcon("src/photo/home2.png");
        image2Label = new JLabel(image2Icon);
        image2Label.setBounds(400, 200, image2Icon.getIconWidth(), image2Icon.getIconHeight());
        root.add(image2Label);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginForm(controller).setVisible(true);
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegistrationForm(controller).setVisible(true);
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(350, 100, 800, 700);
        setVisible(true);
    }
}
