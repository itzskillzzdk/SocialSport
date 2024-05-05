package views;

import UI.HomePage;
import controller.Controller;
import exception.UtilisateurNonAuthentifieException;
import modules.User;

public class GUIView implements Viewable {
    private Controller controller;
    public void setController(Controller controller) {
        this.controller = controller;
    }
    @Override
    public void demanderConnexion() {
        HomePage homePage = new HomePage(this.controller);
    }
    @Override
    public void afficherPageAccueil(User user) {
        // PersonalUI personalUI = new PersonalUI(controller, user);
        ContainerFrame containerFrame = new ContainerFrame(controller);
    }
    @Override
    public void afficherErreurAuthentification() {
        throw new UtilisateurNonAuthentifieException("Erreur d'authentification.");
    }
    @Override
    public void afficherErreurInscription() {
        throw new UtilisateurNonAuthentifieException("Erreur d'inscription.");
    }
    @Override
    public void demanderInscription() {
    }
}
