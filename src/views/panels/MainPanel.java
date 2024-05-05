package views.panels;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public static final Dimension mainPanelDim = new Dimension(960, 576);
    public MainPanel() {
        init();
    }
    private void init() {
        setPreferredSize(mainPanelDim);
        setMinimumSize(mainPanelDim);

        setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
    }
}
