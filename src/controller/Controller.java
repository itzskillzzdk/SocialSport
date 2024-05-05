package controller;

import controller.data_access_object.DemandeAmisDAO;
import controller.data_access_object.SportDAO;
import controller.data_access_object.UserDAO;
import exception.UtilisateurNonAuthentifieException;
import exception.UtilisateurNotFoundException;
import modules.DemandeAmis;
import modules.User;
import modules.sports.Sport;
import views.Viewable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller {

    enum AppState {
        Connexion,
        Inscription,
        PageAccueil;
    }
    private AppState state;
    private Viewable view;
    private User currentUser;
    private UserDAO userDAO = UserDAO.getInstance();
    private DemandeAmisDAO demandeAmisDAO = DemandeAmisDAO.getInstance();

    public Controller(Viewable view) {
        this.view = view;
        this.state = AppState.Connexion;
        view.setController(this);
    }

    public Controller() {
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        userDAO.save(currentUser);
    }
    public User getCurrentUser() {
        return userDAO.save(currentUser);
    }
    public void run(){
//        while (state == AppState.Connexion){
//            view.demanderConnexion();
//        }
        switch (state){
            case Connexion : view.demanderConnexion(); break;
            case PageAccueil : view.afficherPageAccueil(currentUser); break;
            case Inscription : view.demanderInscription(); break;
        }
    }

    public void authentification(String nom, String motDePasse) throws UtilisateurNotFoundException, UtilisateurNonAuthentifieException {
        UserDAO userDAO = UserDAO.getInstance();
        User utilisateur = userDAO.findByNom(nom);
        if (!utilisateur.getMotDePasse().equals(motDePasse)) {

//            System.out.println("NomBD: " + utilisateur.getNom() + " ; NomEcrit: " + nom);
//            System.out.println("MdpBD: " + utilisateur.getMotDePasse() + " ; MdpEcrit: " + motDePasse);

            view.afficherErreurAuthentification();
            throw new UtilisateurNonAuthentifieException("Mot de passe incorrect pour l'utilisateur " + nom);
        }
        setCurrentUser(utilisateur);
        state = AppState.PageAccueil;
        this.run();
    }
    public void inscription(String nom, String motDePasse, List<Sport> sports) {
        state = AppState.Inscription;
        UserDAO userDAO = UserDAO.getInstance();
        if (userExist(nom)){
            view.afficherErreurInscription();
        }else{
            userDAO.save(new User(nom, motDePasse, sports));
            state = AppState.Connexion;
            this.run();
        }
    }
    public boolean userExist(String name) {
        UserDAO userDAO = UserDAO.getInstance();
        return name.equals(userDAO.findByNom(name).getNom());
    }

    public Sport getSportByName(String name) {
        SportDAO sportDAO = SportDAO.getInstance();
        return sportDAO.findByName(name);
    }

    public void creeRecommendationAmis() {

        // Récupérer la liste de tous les utilisateurs sauf l'utilisateur donné
        List<User> allUsers = userDAO.getAllUsersExceptCurrentUser(currentUser);

        // Récupérer les sports de l'utilisateur donné
        Set<Sport> sportsUser = new HashSet<>(currentUser.getSportList());

        // Parcourir la liste des utilisateurs
        for (User otherUser : allUsers) {
            if (!currentUser.getAmis().contains(otherUser) && !demandeAmisDAO.existByUsers(otherUser, currentUser)) { // Vérifier si l'utilisateur ne l'a pas deja en ami
                // Vérifier si l'autre utilisateur pratique au moins un sport en commun avec l'utilisateur donné
                Set<Sport> sportsOtherUser = new HashSet<>(otherUser.getSportList());
                sportsOtherUser.retainAll(sportsUser);
                if (!sportsOtherUser.isEmpty()) {
                    currentUser.getDemandes().add( new DemandeAmis(otherUser, currentUser));
                    userDAO.update(currentUser);
                    userDAO.update(otherUser);
                }
            }
        }
    }
    public void supprimerDemandeDe(User userToDel) {
        List<DemandeAmis> demandeAmis = demandeAmisDAO.findByUsers(userToDel, currentUser);
        for(DemandeAmis d : demandeAmis) {
                currentUser.getDemandes().remove(d);
                userDAO.update(currentUser);
        }
    }
    public void ajouterEnAmi(User newFriend) {
        List<DemandeAmis> demandeAmis = demandeAmisDAO.findByUsers(newFriend, currentUser);
        for (DemandeAmis d : demandeAmis) {
            d.accepterDemande();
            demandeAmisDAO.update(d);
        }
        userDAO.addFriend(currentUser, newFriend);
        userDAO.update(currentUser);
        userDAO.update(newFriend);
    }
    public void removeFriend(User friendToRemove) {
        currentUser.getAmis().remove(friendToRemove);
        userDAO.update(currentUser);
    }
}
