package views.panels.data_panel;

import views.panels.data_panel.*;
import controller.Controller;

import javax.swing.*;

public class SportDataPanelFactory {

    public static JPanel createDataPanel(String sport, Controller controller) throws IllegalArgumentException{
        switch(sport.toUpperCase()) {
            case "NATATION":
                return new NatationDataPanel(controller);
            case "ESCALADE":
                return new EscaladeDataPanel(controller);
            case "JOGGING":
                return new JoggingDataPanel(controller);
            case "TENNIS":
                return new TennisDataPanel(controller);
            case "MUSCULATION":
                return new MusculationDataPanel(controller);
            case "CYCLISME":
                return new CyclismeDataPanel(controller);
            default:
                throw new IllegalArgumentException(sport);
        }
    }

}
