package views.panels.show_graph_panel;

import controller.Controller;
import views.panels.data_panel.*;

import javax.swing.*;

public class GraphiqueSportPanelFactory {
    public static JPanel createShowGraphPanel(String sport, Controller controller) throws IllegalArgumentException {
        switch(sport.toUpperCase()) {
            case "NATATION":
                return new NatationShowGraphPanel(controller);
            case "ESCALADE":
                return new EscaladeShowGraphPanel(controller);
            case "JOGGING":
                return new JoggingShowGraphPanel(controller);
            case "TENNIS":
                return new TennisShowGraphPanel(controller);
            case "MUSCULATION":
                return new MusculationShowGraphPanel(controller);
            case "CYCLISME":
                return new CyclismeShowGraphPanel(controller);
            default:
                throw new IllegalArgumentException(sport);
        }
    }
}
