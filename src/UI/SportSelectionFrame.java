package UI;

import controller.Controller;
import modules.sports.Sport;
import modules.sports.SportEnum;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SportSelectionFrame extends JFrame {
    JPanel rootPanel;
    JLabel welcomeLabel, sportsLabel, dateLabel;
    JButton confirmButton;
    int selectedCount = 0;
    final int MIN_SELECTED = 3;
    final int MAX_SELECTED = 3;
    private List<JCheckBox> sportCheckBoxes = new ArrayList<>();
    private Controller controller;
    public SportSelectionFrame(Controller controller) {
        this.controller = controller;
        rootPanel = new JPanel() {
            // Override paintComponent to draw borders around images and labels
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (JCheckBox checkBox : sportCheckBoxes) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setColor(Color.WHITE);
                    g2d.setStroke(new BasicStroke(4));
                    g2d.drawRect(checkBox.getX() - 10, checkBox.getY() - 150, checkBox.getWidth() + 30,
                            checkBox.getHeight() + 150);
                    g2d.dispose();
                }
            }
        };
        setContentPane(rootPanel);
        rootPanel.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.75);
        int height = (int) (screenSize.height * 0.75);
        setSize(width, height);
        setLocationRelativeTo(null);

        welcomeLabel = new JLabel("Trouvez Vos", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, height / 20));
        welcomeLabel.setBounds(width / 10, height / 20, width * 8 / 10, height / 15);
        rootPanel.add(welcomeLabel);

        sportsLabel = new JLabel("Sports", SwingConstants.CENTER);
        sportsLabel.setFont(new Font("Arial", Font.BOLD, height / 15));
        sportsLabel.setForeground(new Color(255, 105, 180)); // Rose vif
        sportsLabel.setBounds(width / 10, height / 15 + height / 20, width * 8 / 10, height / 15);
        rootPanel.add(sportsLabel);

        setupSport(SportEnum.NATATION.getValeur(), width / 12, height / 4, new ImageIcon("src/photo/Natation.png"), width, height);
        setupSport(SportEnum.JOGGING.getValeur(), width / 12 * 4, height / 4, new ImageIcon("src/photo/Jogging.png"), width, height);
        setupSport(SportEnum.MUSCULATION.getValeur(), width / 12 * 7, height / 4, new ImageIcon("src/photo/Musculation.png"), width, height);
        setupSport(SportEnum.TENNIS.getValeur(), width / 12, height / 4 * 2, new ImageIcon("src/photo/Tennis.png"), width, height);
        setupSport(SportEnum.CYCLISME.getValeur(), width / 12 * 4, height / 4 * 2, new ImageIcon("src/photo/Cyclisme.png"), width, height);
        setupSport(SportEnum.ESCALADE.getValeur(), width / 12 * 7, height / 4 * 2, new ImageIcon("src/photo/Escalade.png"), width, height);

        confirmButton = new JButton("Confirmer");
        confirmButton.setBounds(500, 500, 200, 50);
        confirmButton.setBackground(Color.BLUE);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setEnabled(false); // Désactive initialement le bouton
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Sport> selectedSports = new ArrayList<Sport>();
                for (JCheckBox checkBox : sportCheckBoxes) {
                    if (checkBox.isSelected()) {
                        selectedSports.add(controller.getSportByName(checkBox.getText()));
                    }
                }

                if (selectedSports.size() >= MIN_SELECTED && selectedSports.size() <= MAX_SELECTED) {
                    controller.getCurrentUser().getSportList().addAll(selectedSports);
                    // Pass the selectedSports and the selectedDate to DonneeSports
                    JOptionPane.showMessageDialog(SportSelectionFrame.this, "Inscription réussie!", "", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new LoginForm(controller);
                } else {
                    JOptionPane.showMessageDialog(SportSelectionFrame.this,
                            "Veuillez sélectionner entre 3 et 4 sports.",
                            "Erreur de sélection",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        rootPanel.add(confirmButton);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setupSport(String sportName, int x, int y, ImageIcon icon, int frameWidth, int frameHeight) {

        Image image = icon.getImage().getScaledInstance(frameWidth / 5, frameHeight / 5, Image.SCALE_SMOOTH);
        JLabel sportImageLabel = new JLabel(new ImageIcon(image));
        sportImageLabel.setBounds(x, y, frameWidth / 5, frameHeight / 5);
        rootPanel.add(sportImageLabel);

        JCheckBox sportCheckBox = new JCheckBox(sportName);
        sportCheckBox.setBounds(x, y + frameHeight / 5, frameWidth / 5, frameHeight / 20);
        sportCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedCount++;
                } else {
                    selectedCount--;
                }
                confirmButton.setEnabled(selectedCount >= MIN_SELECTED && selectedCount <= MAX_SELECTED);
            }
        });
        rootPanel.add(sportCheckBox);
        sportCheckBoxes.add(sportCheckBox);
    }
}
