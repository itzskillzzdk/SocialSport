package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

public class AddFriends extends JFrame {
    private List<String> selectedSports = Arrays.asList("Natation", "Jogging", "Tennis");

    public AddFriends() {
        setTitle("Add Friends");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Ajouter des ami·e·s", JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setPreferredSize(new Dimension(0, 50));

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(label, BorderLayout.CENTER);
        panel.add(labelPanel);

        List<Friends> allFriends = Arrays.asList(
                new Friends("Alice", "src/photo/avatar1.jpg", "2024/03/10",
                        Arrays.asList("Cyclisme", "Escalade,", "Musculation")),
                new Friends("Lily", "src/photo/avatar2.jpg", "2024/02/23",
                        Arrays.asList("Musculation", "Cyclisme", "Jogging", "Tennis")),
                new Friends("Amy", "src/photo/avatar3.jpg", "2024/03/18",
                        Arrays.asList("Cyclisme", "Escalade,", "Musculation")),
                new Friends("Krystal", "src/photo/avatar4.jpg", "2024/01/11",
                        Arrays.asList("Tennis", "Escalade,", "Musculation")),
                new Friends("Grace", "src/photo/avatar5.jpg", "2024/03/07",
                        Arrays.asList("Natation", "Escalade,", "Musculation")),
                new Friends("Bella", "src/photo/avatar6.jpg", "2024/04/22",
                        Arrays.asList("Natation", "Escalade,", "Musculation", "Cyclisme")),
                new Friends("Jesse", "src/photo/avatar7.jpg", "2024/01/01",
                        Arrays.asList("Escalade", "Natation", "Escalade,", "Musculation")),
                new Friends("Vivian", "src/photo/avatar8.jpg", "2024/01/01",
                        Arrays.asList("Escalade", "Cyclisme", "Jogging", "Tennis")),
                new Friends("Cindy ", "src/photo/avatar9.jpg", "2024/03/16",
                        Arrays.asList("Cyclisme", "Escalade,", "Musculation", "Tennis")),
                new Friends("Fanny", "src/photo/avatar10.jpg", "2024/01/05",
                        Arrays.asList("Cyclisme", "Escalade,", "Musculation")),
                new Friends("Isabel ", "src/photo/avatar11.jpg", "2024/01/01",
                        Arrays.asList("Cyclisme", "Escalade,", "Musculation")),
                new Friends("Kiki", "src/photo/avatar12.jpg", "2024/04/16",
                        Arrays.asList("Cyclisme", "Escalade,", "Musculation")));

        for (Friends friend : allFriends) {
            if (friend.getSports().stream().anyMatch(selectedSports::contains)) {
                JPanel friendPanel = new JPanel();
                friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.X_AXIS));
                friendPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

                try {
                    BufferedImage originalImage = ImageIO.read(new File(friend.getPhotoPath()));
                    ImageIcon icon = new ImageIcon(originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    JLabel photoLabel = new JLabel(icon);
                    friendPanel.add(photoLabel);
                } catch (IOException e) {
                    e.printStackTrace();
                    friendPanel.add(new JLabel("Photo not available"));
                }

                JPanel infoPanel = new JPanel(new GridLayout(3, 1));
                infoPanel.add(new JLabel(friend.getName()));
                infoPanel.add(new JLabel(friend.getRegistrationDate()));

                // 添加显示所选择的运动的标签
                JLabel sportsLabel = new JLabel(String.join(", ", friend.getSports()));
                infoPanel.add(sportsLabel);

                friendPanel.add(infoPanel);

                JPanel buttonPanel = new JPanel();
                JButton addButton = new JButton("Ajouter");
                addButton.addActionListener(e -> {
                    Friends newFriend = new Friends(friend.getName(), friend.getPhotoPath(),
                            friend.getRegistrationDate(), friend.getSports());
                    Friends.addFriend(newFriend); // 注意：这需要Friends类有一个接受Friend对象的addFriend方法
                    JOptionPane.showMessageDialog(AddFriends.this, "Ajouté: " + newFriend.getName());
                });

                JButton removeButton = new JButton("Supprimer");
                removeButton.addActionListener(e -> {
                    JOptionPane.showMessageDialog(AddFriends.this, "Supprimé " + friend.getName());
                    // 实际的删除逻辑可能需要在Friends类中处理
                });
                buttonPanel.add(addButton);
                buttonPanel.add(removeButton);
                friendPanel.add(buttonPanel);

                panel.add(friendPanel);
            }
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        JButton viewFriendsBtn = new JButton("Voir les amis");
        viewFriendsBtn.addActionListener(e -> {
            EventQueue.invokeLater(() -> {
                Friends friendsFrame = new Friends();
                friendsFrame.setVisible(true);
            });
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(viewFriendsBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new AddFriends().setVisible(true));
    }
}
