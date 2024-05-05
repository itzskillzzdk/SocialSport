package views.panels;

import views.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class BannerPanel extends JPanel {
    // LeftPanel fields
    private JLabel profilPicture;

    // RightPanel fields
    private JLabel usernameJL;
    private JLabel usersportsJL;
    public BannerPanel() { init(); }
    private void init() {
        Dimension dimension = new Dimension(960, 144);
        setPreferredSize(dimension);
        setMinimumSize(dimension);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        // leftPanel
        Dimension leftDimension = new Dimension(144,144);
        leftPanel.setPreferredSize(leftDimension);
        leftPanel.setMinimumSize(leftDimension);

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        profilPicture = new JLabel(Utils.getResizeImage("src/photo/avatar1.jpg", 128,128));
        profilPicture.setAlignmentY(Component.CENTER_ALIGNMENT);
        profilPicture.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(profilPicture);

        // rightPanel
        Dimension rightDimension = new Dimension(960 - 144,144);
        rightPanel.setPreferredSize(rightDimension);
        rightPanel.setMinimumSize(rightDimension);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        usernameJL = new JLabel();
        usernameJL.setFont(new Font("Arial", Font.BOLD, 24));
        usersportsJL = new JLabel();
        usersportsJL.setFont(new Font("Arial", Font.ITALIC, 14));

        rightPanel.add(usernameJL);
        rightPanel.add(usersportsJL);

        setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

        add(leftPanel);
        add(rightPanel);
    }
    public JLabel getUsernameJL() {
        return usernameJL;
    }
    public JLabel getUsersportsJL() {
        return usersportsJL;
    }
}
