package controller.data_access_object;

import modules.User;
import modules.sports.Sport;
import modules.sports.SportEnum;
import org.hibernate.Query;

import java.io.Serializable;

public final class SportDAO extends GenericDAO<Sport>{

    private static final SportDAO instance = new SportDAO();
    private SportDAO() {
        super(Sport.class);
    }

    public static SportDAO getInstance() {
        return instance;
    }

    @Override
    public Sport save(Sport sport) {
        session.beginTransaction();
        session.persist(sport);
        session.getTransaction().commit();
        return sport;
    }

    @Override
    public Sport findById(Serializable id) {
        session.beginTransaction();
        Sport sport = (Sport) session.get(Sport.class,id);
        session.getTransaction().commit();
        return sport;
    }

    public Sport findByName(String name) {
        session.beginTransaction();

        Query query = session.createQuery("FROM Sport s WHERE s.nom=:name");
        query.setParameter("name", SportEnum.valueOf(name.toUpperCase()));
        Sport sport = (Sport) query.uniqueResult();

        session.getTransaction().commit();
        return sport;
    }

    @Override
    public Sport delete(Sport entity) {
        return null;
    }

    @Override
    public Sport update(Sport entity) {
        return null;
    }
}
