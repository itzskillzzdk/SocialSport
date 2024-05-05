package views.panels.stats_panel;

import controller.Controller;
import views.panels.data_panel.*;

import javax.swing.*;

public class SportStatsPanelFactory {
    public static JPanel createStatsPanel(String sport, Controller controller) {
        switch (sport.toUpperCase()) {
            case "NATATION":
                return new NatationStatsPanel(controller);
            case "ESCALADE":
                return new EscaladeStatsPanel(controller);
            case "JOGGING":
                return new JoggingStatsPanel(controller);
            case "TENNIS":
                return new TennisStatsPanel(controller);
            case "MUSCULATION":
                return new MusculationStatsPanel(controller);
            case "CYCLISME":
                return new CyclismeStatsPanel(controller);
            default:
                throw new IllegalArgumentException(sport);
        }
    }
}
