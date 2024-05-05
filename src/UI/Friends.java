package UI;

import controller.Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Friends extends JFrame {
    // 使用静态列表存储添加的好友信息
    private static List<Friends> friendsList = new ArrayList<>();
    private JPanel panel;
    Controller controller;

    public Friends() {
        setTitle("Mes Amis");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Mes Amis", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setPreferredSize(new Dimension(0, 50));
        headerPanel.add(label, BorderLayout.CENTER);

        // 将头部面板添加到窗口的北部
        add(headerPanel, BorderLayout.NORTH);

        JButton backButton = new JButton("Back to PersonalUI");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(new Color(200, 191, 231));
        backButton.setPreferredSize(new Dimension(1000, 40));

        backButton.addActionListener(e -> {
            dispose();
//            EventQueue.invokeLater(() -> new PersonalUI(controller).setVisible(true));
        });

        // Add the button to the bottom of the frame
        add(backButton, BorderLayout.SOUTH);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        refreshFriendsList();

        setLocationRelativeTo(null);
    }

    // 添加好友到静态列表
    public static void addFriend(Friends friend) {
        friendsList.add(friend);
    }

    // 从静态列表中删除好友
    public static void removeFriend(String name) {
        friendsList.removeIf(friend -> friend.getName().equals(name));
    }

    private void refreshFriendsList() {
        panel.removeAll();

        for (Friends friend : friendsList) {
            // 为每个好友创建一个面板，使用 BoxLayout 来垂直排列组件
            JPanel friendPanel = new JPanel();
            friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.X_AXIS));
            friendPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 添加一些内边距

            try {
                BufferedImage img = ImageIO.read(new File(friend.getPhotoPath()));
                ImageIcon icon = new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                JLabel photoLabel = new JLabel(icon);
                friendPanel.add(photoLabel);
            } catch (IOException e) {
                friendPanel.add(new JLabel("Photo not available"));
            }

            // 创建一个新的面板来放置文本信息，使用 BoxLayout 来垂直排列文本
            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

            textPanel.add(new JLabel("Nom: " + friend.getName()));
            textPanel.add(new JLabel("Date d'inscription: " + friend.getRegistrationDate()));
            String sportsText = "Sports: " + String.join(", ", friend.getSports());
            textPanel.add(new JLabel(sportsText));

            friendPanel.add(textPanel);

            JButton removeButton = new JButton("Supprimer");
            removeButton.addActionListener((ActionEvent e) -> {
                removeFriend(friend.getName());
                refreshFriendsList();
                JOptionPane.showMessageDialog(Friends.this, friend.getName() + " a été supprimé.");
            });

            // 将删除按钮也放在文本面板的下方
            textPanel.add(removeButton);

            // 将好友面板添加到主面板中
            panel.add(friendPanel);

            // 添加一个分隔组件来区分每个好友的面板
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Friends().setVisible(true));
    }

    // 假定Friends类有相同的属性和构造函数定义
    String name;
    String photoPath;
    String registrationDate;
    List<String> sports;

    public Friends(String name, String photoPath, String registrationDate, List<String> sports) {
        this.name = name;
        this.photoPath = photoPath;
        this.registrationDate = registrationDate;
        this.sports = sports;
    }

    public String getName() {
        return name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public List<String> getSports() {
        return sports;
    }
}
