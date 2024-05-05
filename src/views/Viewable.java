package views;

import controller.Controller;
import modules.User;

public interface Viewable {
    public void setController(Controller controller);
    public void demanderConnexion();

    public void afficherPageAccueil(User user);

    public void afficherErreurAuthentification();

    public void afficherErreurInscription();

    public void demanderInscription();
}
