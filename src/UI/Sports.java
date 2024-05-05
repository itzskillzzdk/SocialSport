package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller;

public class Sports extends JFrame {
    private Map<String, String> sportsData;
    private JLabel avatarLabel, nicknameLabel;
    private ImageIcon avatarIcon = new ImageIcon("src/photo/profile.png"); // Update path
    private String nickname = "Mes données sportives";
    private Controller controller;

    public Sports(Controller controller) {
        this.controller = controller;
        this.sportsData = Map.of("Natation", "PAPILLON");
        commonInitialization();
    }

    private void commonInitialization() {
        setTitle("Mes données sportives");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel profilePanel = new JPanel(new FlowLayout());
        avatarLabel = new JLabel(new ImageIcon(avatarIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        nicknameLabel = new JLabel(nickname);
        profilePanel.add(avatarLabel);
        profilePanel.add(nicknameLabel);

        add(profilePanel, BorderLayout.NORTH);

        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        sportsData.forEach((sport, value) -> {
            JPanel panel = new JPanel(new FlowLayout());
            JLabel label = new JLabel(sport + ": " + value);
            JButton chartButton = new JButton("View Chart");
            chartButton.addActionListener(e -> showChart(sport));
            panel.add(label);
            panel.add(chartButton);
            dataPanel.add(panel);
        });

        JScrollPane scrollPane = new JScrollPane(dataPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void showChart(String sport) {
        JFrame chartFrame = getChartFrame(sport);
        if (chartFrame != null) {
            chartFrame.pack();
            chartFrame.setLocationRelativeTo(null);
            chartFrame.setVisible(true);
        }
    }

    private JFrame getChartFrame(String sport) {
        switch (sport) {
            case "Jogging":
//                return new Jogging("Jogging Chart");
            case "Cyclisme":
//                return new Cyclisme("Cyclisme Chart");
            case "Tennis":
//                return new Tennis("Tennis Chart");
            case "Natation":
 //               return new Natation("Natation Chart");
            case "Escalade":
             //   return new Escalade("Escalade Chart");
            case "Musculation":
   //             return new Musculation("Musculation Chart");
            default:
                return null;
        }
    }
}
