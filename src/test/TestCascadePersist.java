package test;

import controller.Controller;
import controller.data_access_object.DataDAO;
import controller.data_access_object.DemandeAmisDAO;
import controller.data_access_object.SportDAO;
import controller.data_access_object.UserDAO;
import controller.db.DBConnection;
import controller.db.DataInit;
import controller.management.SportFactory;
import modules.DemandeAmis;
import modules.User;
import modules.donnees.*;
import modules.sports.*;

import java.time.*;
import java.util.Date;
import java.util.List;

public class TestCascadePersist {
    public static void main(String[] args) {
        // Create the Tables
        DataInit.createTables();

        // Initializing sports in the database
        SportDAO sportDAO = SportDAO.getInstance();

        Sport tennis = sportDAO.save(SportFactory.createSport(SportEnum.TENNIS.getValeur()));
        Sport natation = sportDAO.save(SportFactory.createSport(SportEnum.NATATION.getValeur()));
        Sport musculation = sportDAO.save(SportFactory.createSport(SportEnum.MUSCULATION.getValeur()));
        Sport escalade = sportDAO.save(SportFactory.createSport(SportEnum.ESCALADE.getValeur()));
        Sport jogging = sportDAO.save(SportFactory.createSport(SportEnum.JOGGING.getValeur()));
        Sport cyclisme = sportDAO.save(SportFactory.createSport(SportEnum.CYCLISME.getValeur()));

        // Create an admin user to make test
        UserDAO userDAO = UserDAO.getInstance();
        // creating fake friend
        List<Sport> enzoSports = List.of(tennis, jogging, natation);
        User enzo = new User("Enzo", "enzo", enzoSports);
        userDAO.save(enzo);

        List<Sport> flavioSports = List.of(musculation, escalade, natation);
        User flavio = new User("Flavio", "flavio", flavioSports);
        userDAO.save(flavio);

        List<Sport> farahSports = List.of(tennis, escalade, cyclisme);
        User farah = new User("Farah", "farah", farahSports);
        userDAO.save(farah);

        List<Sport> adminSports = List.of(tennis, escalade, cyclisme);
        User admin = new User("admin", "admin");
        admin.getSportList().addAll(adminSports);
        userDAO.save(admin);

        userDAO.addFriend(admin, flavio);
        userDAO.addFriend(admin, farah);
        DemandeAmisDAO demandeAmisDAO = DemandeAmisDAO.getInstance();
        DemandeAmis demandeAmis = new DemandeAmis();
        demandeAmis.setDemandeur(admin);
        demandeAmis.setDestinataire(farah);
        demandeAmis.setStatut(DemandeAmis.StatutDemande.EN_ATTENTE);
        demandeAmisDAO.save(demandeAmis);

        DataDAO dataDAO = DataDAO.getInstance();

        // Récupérer la date d'hier
        LocalDateTime yesterdayStart = LocalDate.now().minusDays(1).atStartOfDay();
        LocalDateTime date1 = LocalDate.now().minusDays(2).atStartOfDay();
        LocalDateTime date2 = LocalDate.now().minusDays(3).atStartOfDay();
        LocalDateTime date3 = LocalDate.now().minusDays(4).atStartOfDay();
        LocalDateTime date4 = LocalDate.now().minusDays(5).atStartOfDay();

        // Convertir la date d'hier en un objet Instant
        Instant yesterdayInstant = yesterdayStart.toInstant(ZoneOffset.UTC);
        Instant date1Instant = date1.toInstant(ZoneOffset.UTC);
        Instant date2Instant = date2.toInstant(ZoneOffset.UTC);
        Instant date3Instant = date3.toInstant(ZoneOffset.UTC);
        Instant date4Instant = date4.toInstant(ZoneOffset.UTC);


        //ENZO
        DonneeJogging donneeJoggingEnzo = new DonneeJogging(jogging, enzo, "60", 4000f, Date.from(yesterdayInstant));
        DonneeJogging donneeJogging1Enzo = new DonneeJogging(jogging, enzo, "80", 5000f, new Date());
        DonneeJogging donneeJogging2Enzo = new DonneeJogging(jogging, enzo, "100", 6000f, Date.from(date1Instant));
        DonneeJogging donneeJogging3Enzo = new DonneeJogging(jogging, enzo, "120", 8000f, Date.from(date2Instant));
        DonneeJogging donneeJogging4Enzo = new DonneeJogging(jogging, enzo, "140", 3000f, Date.from(date3Instant));
        enzo.getDonnee().add(donneeJoggingEnzo);
        enzo.getDonnee().add(donneeJogging1Enzo);
        enzo.getDonnee().add(donneeJogging2Enzo);
        enzo.getDonnee().add(donneeJogging3Enzo);
        enzo.getDonnee().add(donneeJogging4Enzo);

        DonneeNatation donneeNatationEnzo = new DonneeNatation(natation, enzo, "60", 4000f,NatationStyleEnum.PAPILLON, Date.from(yesterdayInstant));
        DonneeNatation donneeNatation1Enzo = new DonneeNatation(natation, enzo, "120", 8000f,NatationStyleEnum.CRAWL, Date.from(date1Instant));
        DonneeNatation donneeNatation2Enzo = new DonneeNatation(natation, enzo, "70", 5000f,NatationStyleEnum.PAPILLON, Date.from(date2Instant));
        DonneeNatation donneeNatation3Enzo = new DonneeNatation(natation, enzo, "30", 2000f,NatationStyleEnum.PAPILLON, Date.from(date3Instant));
        DonneeNatation donneeNatation4Enzo = new DonneeNatation(natation, enzo, "40", 3000f,NatationStyleEnum.BRASSE, Date.from(date4Instant));
        enzo.getDonnee().add(donneeNatationEnzo);
        enzo.getDonnee().add(donneeNatation1Enzo);
        enzo.getDonnee().add(donneeNatation2Enzo);
        enzo.getDonnee().add(donneeNatation3Enzo);
        enzo.getDonnee().add(donneeNatation4Enzo);

        DonneeTennis donneeTennisEnzo = new DonneeTennis(tennis, enzo, "60",10,12, Date.from(yesterdayInstant));
        DonneeTennis donneeTennis1Enzo = new DonneeTennis(tennis, enzo, "120",20,24, Date.from(date1Instant));
        DonneeTennis donneeTennis2Enzo = new DonneeTennis(tennis, enzo, "70", 15,18, Date.from(date2Instant));
        DonneeTennis donneeTennis3Enzo = new DonneeTennis(tennis, enzo, "30", 5,6, Date.from(date3Instant));
        DonneeTennis donneeTennis4Enzo = new DonneeTennis(tennis, enzo, "40", 7,9, Date.from(date4Instant));
        enzo.getDonnee().add(donneeTennisEnzo);
        enzo.getDonnee().add(donneeTennis1Enzo);
        enzo.getDonnee().add(donneeTennis2Enzo);
        enzo.getDonnee().add(donneeTennis3Enzo);
        enzo.getDonnee().add(donneeTennis4Enzo);


        //FARAH

        DonneeTennis donneeTennisFarah = new DonneeTennis(tennis, farah, "60",10,12, Date.from(yesterdayInstant));
        DonneeTennis donneeTennis1Farah = new DonneeTennis(tennis, farah, "120",20,24, Date.from(date1Instant));
        DonneeTennis donneeTennis2Farah = new DonneeTennis(tennis, farah, "70", 15,18, Date.from(date2Instant));
        DonneeTennis donneeTennis3Farah = new DonneeTennis(tennis, farah, "30", 5,6, Date.from(date3Instant));
        DonneeTennis donneeTennis4Farah = new DonneeTennis(tennis, farah, "40", 7,9, Date.from(date4Instant));
        farah.getDonnee().add(donneeTennisFarah);
        farah.getDonnee().add(donneeTennis1Farah);
        farah.getDonnee().add(donneeTennis2Farah);
        farah.getDonnee().add(donneeTennis3Farah);
        farah.getDonnee().add(donneeTennis4Farah);

        DonneeEscalade donneeEscaladeFarah = new DonneeEscalade(escalade, farah, "60",1000f,EscaladeTypeEnum.ESCALADE_EN_SALLE, Date.from(yesterdayInstant));
        DonneeEscalade donneeEscalade1Farah = new DonneeEscalade(escalade, farah, "120",2000f,EscaladeTypeEnum.ESCALADE_EN_SALLE, Date.from(date1Instant));
        DonneeEscalade donneeEscalade2Farah = new DonneeEscalade(escalade, farah, "70", 1500f,EscaladeTypeEnum.ESCALADE_EN_SALLE, Date.from(date2Instant));
        DonneeEscalade donneeEscalade3Farah = new DonneeEscalade(escalade, farah, "30", 5000f,EscaladeTypeEnum.ESCALADE_EN_SALLE, Date.from(date3Instant));
        DonneeEscalade donneeEscalade4Farah = new DonneeEscalade(escalade, farah, "40", 7000f,EscaladeTypeEnum.ESCALADE_EN_MOULINETTE, Date.from(date4Instant));
        farah.getDonnee().add(donneeEscaladeFarah);
        farah.getDonnee().add(donneeEscalade1Farah);
        farah.getDonnee().add(donneeEscalade2Farah);
        farah.getDonnee().add(donneeEscalade3Farah);
        farah.getDonnee().add(donneeEscalade4Farah);

        DonneeCyclisme donneeCyclismeFarah = new DonneeCyclisme(cyclisme, farah, "60",1000f, Date.from(yesterdayInstant));
        DonneeCyclisme donneeCyclisme1Farah = new DonneeCyclisme(cyclisme, farah, "120",2000f, Date.from(date1Instant));
        DonneeCyclisme donneeCyclisme2Farah = new DonneeCyclisme(cyclisme, farah, "70", 1500f, Date.from(date2Instant));
        DonneeCyclisme donneeCyclisme3Farah = new DonneeCyclisme(cyclisme, farah, "30", 5000f, Date.from(date3Instant));
        DonneeCyclisme donneeCyclisme4Farah = new DonneeCyclisme(cyclisme, farah, "40", 5000f, Date.from(date4Instant));
        farah.getDonnee().add(donneeCyclismeFarah);
        farah.getDonnee().add(donneeCyclisme1Farah);
        farah.getDonnee().add(donneeCyclisme2Farah);
        farah.getDonnee().add(donneeCyclisme3Farah);
        farah.getDonnee().add(donneeCyclisme4Farah);


        //ADMIN

        DonneeTennis donneeTennisAdmin = new DonneeTennis(tennis, admin, "60",10,12, Date.from(yesterdayInstant));
        DonneeTennis donneeTennis1Admin = new DonneeTennis(tennis, admin, "120",20,24, Date.from(date1Instant));
        DonneeTennis donneeTennis2Admin = new DonneeTennis(tennis, admin, "70", 15,18, Date.from(date2Instant));
        DonneeTennis donneeTennis3Admin = new DonneeTennis(tennis, admin, "30", 5,6, Date.from(date3Instant));
        DonneeTennis donneeTennis4Admin = new DonneeTennis(tennis, admin, "40", 7,9, Date.from(date4Instant));
        admin.getDonnee().add(donneeTennisAdmin);
        admin.getDonnee().add(donneeTennis1Admin);
        admin.getDonnee().add(donneeTennis2Admin);
        admin.getDonnee().add(donneeTennis3Admin);
        admin.getDonnee().add(donneeTennis4Admin);

        DonneeEscalade donneeEscaladeAdmin = new DonneeEscalade(escalade, admin, "60",1000f,EscaladeTypeEnum.ESCALADE_EN_SALLE, Date.from(yesterdayInstant));
        DonneeEscalade donneeEscalade1Admin = new DonneeEscalade(escalade, admin, "120",2000f,EscaladeTypeEnum.ESCALADE_EN_SALLE, Date.from(date1Instant));
        DonneeEscalade donneeEscalade2Admin = new DonneeEscalade(escalade, admin, "70", 1500f,EscaladeTypeEnum.ESCALADE_EN_SALLE, Date.from(date2Instant));
        DonneeEscalade donneeEscalade3Admin = new DonneeEscalade(escalade, admin, "30", 5000f,EscaladeTypeEnum.ESCALADE_EN_SALLE, Date.from(date3Instant));
        DonneeEscalade donneeEscalade4Admin = new DonneeEscalade(escalade, admin, "40", 7000f,EscaladeTypeEnum.ESCALADE_EN_MOULINETTE, Date.from(date4Instant));
        admin.getDonnee().add(donneeEscaladeAdmin);
        admin.getDonnee().add(donneeEscalade1Admin);
        admin.getDonnee().add(donneeEscalade2Admin);
        admin.getDonnee().add(donneeEscalade3Admin);
        admin.getDonnee().add(donneeEscalade4Admin);

        DonneeCyclisme donneeCyclismeAdmin = new DonneeCyclisme(cyclisme, admin, "60",900f, Date.from(yesterdayInstant));
        DonneeCyclisme donneeCyclisme1Admin = new DonneeCyclisme(cyclisme, admin, "120",1000f, Date.from(date1Instant));
        DonneeCyclisme donneeCyclisme2Admin = new DonneeCyclisme(cyclisme, admin, "70", 1500f, Date.from(date2Instant));
        DonneeCyclisme donneeCyclisme3Admin = new DonneeCyclisme(cyclisme, admin, "180", 9000f, Date.from(date3Instant));
        DonneeCyclisme donneeCyclisme4Admin = new DonneeCyclisme(cyclisme, admin, "40", 5000f, Date.from(date4Instant));
        admin.getDonnee().add(donneeCyclismeAdmin);
        admin.getDonnee().add(donneeCyclisme1Admin);
        admin.getDonnee().add(donneeCyclisme2Admin);
        admin.getDonnee().add(donneeCyclisme3Admin);
        admin.getDonnee().add(donneeCyclisme4Admin);

        // Sauvegarder la donnée dans la base de données

        // Save Enzo
        dataDAO.save(donneeJoggingEnzo);
        dataDAO.save(donneeJogging1Enzo);
        dataDAO.save(donneeJogging2Enzo);
        dataDAO.save(donneeJogging3Enzo);
        dataDAO.save(donneeJogging4Enzo);

        dataDAO.save(donneeNatationEnzo);
        dataDAO.save(donneeNatation1Enzo);
        dataDAO.save(donneeNatation2Enzo);
        dataDAO.save(donneeNatation3Enzo);
        dataDAO.save(donneeNatation4Enzo);

        dataDAO.save(donneeTennisEnzo);
        dataDAO.save(donneeTennis1Enzo);
        dataDAO.save(donneeTennis2Enzo);
        dataDAO.save(donneeTennis3Enzo);
        dataDAO.save(donneeTennis4Enzo);


        //Save FARAH
        dataDAO.save(donneeTennisFarah);
        dataDAO.save(donneeTennis1Farah);
        dataDAO.save(donneeTennis2Farah);
        dataDAO.save(donneeTennis3Farah);
        dataDAO.save(donneeTennis4Farah);

        dataDAO.save(donneeEscaladeFarah);
        dataDAO.save(donneeEscalade1Farah);
        dataDAO.save(donneeEscalade2Farah);
        dataDAO.save(donneeEscalade3Farah);
        dataDAO.save(donneeEscalade4Farah);

        dataDAO.save(donneeCyclismeFarah);
        dataDAO.save(donneeCyclisme1Farah);
        dataDAO.save(donneeCyclisme2Farah);
        dataDAO.save(donneeCyclisme3Farah);
        dataDAO.save(donneeCyclisme4Farah);

        //Save ADMIN
        dataDAO.save(donneeTennisAdmin);
        dataDAO.save(donneeTennis1Admin);
        dataDAO.save(donneeTennis2Admin);
        dataDAO.save(donneeTennis3Admin);
        dataDAO.save(donneeTennis4Admin);

        dataDAO.save(donneeEscaladeAdmin);
        dataDAO.save(donneeEscalade1Admin);
        dataDAO.save(donneeEscalade2Admin);
        dataDAO.save(donneeEscalade3Admin);
        dataDAO.save(donneeEscalade4Admin);

        dataDAO.save(donneeCyclismeAdmin);
        dataDAO.save(donneeCyclisme1Admin);
        dataDAO.save(donneeCyclisme2Admin);
        dataDAO.save(donneeCyclisme3Admin);
        dataDAO.save(donneeCyclisme4Admin);


        DBConnection.getSession().close();
    }
}