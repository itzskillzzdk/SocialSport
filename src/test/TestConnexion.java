package test;

import controller.Controller;
import controller.db.DBConnection;
import controller.db.DataInit;
import org.hibernate.Session;
import views.CliView;
import views.GUIView;
import views.Viewable;

import java.time.LocalTime;

public class TestConnexion {
    public static void main(String[] args) {

        Session session = DBConnection.getSession();
        Viewable view = new GUIView();

        Controller controller = new Controller(view);
        controller.run();
        session.close();
    }
}