package views;

import controller.Controller;
import controller.management.SportFactory;
import modules.User;
import modules.sports.Sport;
import modules.sports.SportEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CliView implements Viewable {
    private Controller controller;
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void demanderConnexion() {
        System.out.println("Entrez inscription si vous voulez vous inscrire");
        String choix = scanner.nextLine();
        if (choix.equals("inscription")) {
            demanderInscription();
        } else {
            System.out.println("CONNEXION");
            System.out.println("Entrez votre nom d'utilisateur :");
            String nom = scanner.nextLine();
            System.out.println("Entrez votre mot de passe :");
            String motDePasse = scanner.nextLine();
            if (nom.isEmpty() || motDePasse.isEmpty()) {
                afficherErreurAuthentification();
            } else {
                controller.authentification(nom, motDePasse);
            }
        }
    }

    @Override
    public void afficherPageAccueil(User user) {
        System.out.println("Bienvenue, connexion réussie");
    }

    @Override
    public void afficherErreurAuthentification() {
        System.out.println("nom ou mot de passe incorrect");
    }

    @Override
    public void afficherErreurInscription() {
        System.out.println("ce nom existe déjà");
    }

    @Override
    public void demanderInscription() {
        System.out.println("INSCRIPTION");
        System.out.println("Entrez votre nom d'utilisateur :");
        String nom = scanner.nextLine();
        System.out.println("Entrez votre mot de passe :");
        String motDePasse = scanner.nextLine();
        System.out.println("Entrer 3 à 4 sports parmi les suivants :");
        System.out.print("(");
        for(SportEnum sport : SportEnum.values()) {
            System.out.print(sport.getValeur() + " ");
        }
        System.out.println(")");

        List<Sport> sportsChoisis = new ArrayList<Sport>();
        for (int i = 0; i < 4; i++) {
            String sportChoisi = scanner.nextLine();
            // Ne pas faire ça !
            // Il faut utiliser les sports deja créer dans la BD et non en faire de nouveau !
            sportsChoisis.add(SportFactory.createSport(sportChoisi));
        }
        if (nom.isEmpty() || motDePasse.isEmpty()){
            afficherErreurInscription();
        }
        else {
            controller.inscription(nom,motDePasse,sportsChoisis);
        }
    }
}
