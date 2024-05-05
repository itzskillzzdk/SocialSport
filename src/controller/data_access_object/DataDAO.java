package controller.data_access_object;

import modules.User;
import modules.donnees.*;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public final class DataDAO extends GenericDAO<Donnee> {

    private static final DataDAO instance = new DataDAO();

    private DataDAO() {
        super(Donnee.class);
    }

    public static DataDAO getInstance() {
        return instance;
    }

    @Override
    public Donnee save(Donnee donnee) {
        session.beginTransaction();
        session.persist(donnee);
        session.getTransaction().commit();
        return donnee;
    }

    @Override
    public Donnee findById(Serializable id) {
        session.beginTransaction();
        Donnee donnee = (Donnee) session.get(Donnee.class, id);
        session.getTransaction().commit();
        return donnee;
    }

    @Override
    public Donnee delete(Donnee entity) {
        return null;
    }

    @Override
    public Donnee update(Donnee entity) {
        return null;
    }

    public DonneeCyclisme findCyclingDataById(Serializable id) {
        session.beginTransaction();
        DonneeCyclisme donnee = (DonneeCyclisme) session.get(DonneeCyclisme.class, id);
        session.getTransaction().commit();
        return donnee;
    }

    public DonneeEscalade findClimbingDataById(Serializable id) {
        session.beginTransaction();
        DonneeEscalade donnee = (DonneeEscalade) session.get(DonneeEscalade.class, id);
        session.getTransaction().commit();
        return donnee;
    }

    public DonneeJogging findJoggingDataById(Serializable id) {
        session.beginTransaction();
        DonneeJogging donnee = (DonneeJogging) session.get(DonneeJogging.class, id);
        session.getTransaction().commit();
        return donnee;
    }

    public DonneeMusculation findMuscleDataById(Serializable id) {
        session.beginTransaction();
        DonneeMusculation donnee = (DonneeMusculation) session.get(DonneeMusculation.class, id);
        session.getTransaction().commit();
        return donnee;
    }

    public DonneeNatation findSwimmingDataById(Serializable id) {
        session.beginTransaction();
        DonneeNatation donnee = (DonneeNatation) session.get(DonneeNatation.class, id);
        session.getTransaction().commit();
        return donnee;
    }

    public DonneeTennis findTennisDataById(Serializable id) {
        session.beginTransaction();
        DonneeTennis donnee = (DonneeTennis) session.get(DonneeTennis.class, id);
        session.getTransaction().commit();
        return donnee;
    }

    public List<DonneeCyclisme> getDataCyclisme(User user) {
        session.beginTransaction();

        Query query = session.createQuery("FROM DonneeCyclisme d WHERE d.utilisateur =:user ORDER BY d.date ASC");

        query.setParameter("user", user);
        List<DonneeCyclisme> donnees = query.list();

        if(donnees.isEmpty())
            System.out.println("empty");

        for (DonneeCyclisme d:donnees) {
            System.out.println(d.toString());
        }

        session.getTransaction().commit();

        return donnees;
    }

    public List<DonneeJogging> getDataJogging(User user) {
        session.beginTransaction();

        Query query = session.createQuery("FROM DonneeJogging d WHERE d.utilisateur =:user ORDER BY d.date ASC");

        query.setParameter("user", user);
        List<DonneeJogging> donnees = query.list();

        if(donnees.isEmpty())
            System.out.println("empty");

        for (DonneeJogging d:donnees) {
            System.out.println(d.toString());
        }

        session.getTransaction().commit();

        return donnees;
    }
    public List<DonneeMusculation> getDataMusculation(User user) {
        session.beginTransaction();

        Query query = session.createQuery("FROM DonneeMusculation d WHERE d.utilisateur =:user ORDER BY d.date ASC");

        query.setParameter("user", user);
        List<DonneeMusculation> donnees = query.list();

        if(donnees.isEmpty())
            System.out.println("empty");

        for (DonneeMusculation d:donnees) {
            System.out.println(d.toString());
        }

        session.getTransaction().commit();

        return donnees;
    }

    public List<DonneeNatation> getDataNatation(User user) {
        session.beginTransaction();

        Query query = session.createQuery("FROM DonneeNatation d WHERE d.utilisateur =:user ORDER BY d.date ASC");

        query.setParameter("user", user);
        List<DonneeNatation> donnees = query.list();

        if(donnees.isEmpty())
            System.out.println("empty");

        for (DonneeNatation d:donnees) {
            System.out.println(d.toString());
        }

        session.getTransaction().commit();

        return donnees;
    }
    public List<DonneeTennis> getDataTennis(User user) {
        session.beginTransaction();

        Query query = session.createQuery("FROM DonneeTennis d WHERE d.utilisateur =:user ORDER BY d.date ASC");

        query.setParameter("user", user);
        List<DonneeTennis> donnees = query.list();

        if(donnees.isEmpty())
            System.out.println("empty");

        for (DonneeTennis d:donnees) {
            System.out.println(d.toString());
        }

        session.getTransaction().commit();

        return donnees;
    }
    public List<DonneeEscalade> getDataEscalade(User user) {
        session.beginTransaction();

        Query query = session.createQuery("FROM DonneeEscalade d WHERE d.utilisateur =:user ORDER BY d.date ASC");

        query.setParameter("user", user);
        List<DonneeEscalade> donnees = query.list();

        if(donnees.isEmpty())
            System.out.println("empty");

        for (DonneeEscalade d:donnees) {
            System.out.println(d.toString());
        }

        session.getTransaction().commit();

        return donnees;
    }
}
