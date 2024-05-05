package controller.data_access_object;

import modules.DemandeAmis;
import modules.User;
import modules.sports.Sport;
import modules.sports.SportEnum;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

import static modules.DemandeAmis.StatutDemande.ACCEPTEE;
import static modules.DemandeAmis.StatutDemande.EN_ATTENTE;

public final class DemandeAmisDAO extends GenericDAO<DemandeAmis> {

    private static final DemandeAmisDAO instance = new DemandeAmisDAO();

    private DemandeAmisDAO() {
        super(DemandeAmis.class);
    }

    public static DemandeAmisDAO getInstance() {
        return instance;
    }

    @Override
    public DemandeAmis save(DemandeAmis demandeAmis) {
        session.beginTransaction();
        session.persist(demandeAmis);
        session.getTransaction().commit();
        return demandeAmis;
    }

    @Override
    public DemandeAmis findById(Serializable id) {
        session.beginTransaction();
        DemandeAmis demandeAmis = (DemandeAmis) session.get(DemandeAmis.class, id);
        session.getTransaction().commit();
        return demandeAmis;
    }
    public List<DemandeAmis> findByUsers(User demandeur, User destinataire) {
        session.beginTransaction();
        Query query = session.createQuery("FROM DemandeAmis d WHERE d.demandeur=:demandeur AND d.destinataire=:destinataire");
        query.setParameter("destinataire", destinataire);
        query.setParameter("demandeur", demandeur);
//        query.setParameter("accepte", ACCEPTEE);
        List<DemandeAmis> demandeAmis = query.list();
        session.getTransaction().commit();
        return demandeAmis;
    }

    public boolean existByUsers(User demandeur, User destinataire) {
        session.beginTransaction();
        Query query = session.createQuery("FROM DemandeAmis d WHERE d.demandeur=:demandeur AND d.destinataire=:destinataire AND d.statut!=:accepte");
        query.setParameter("destinataire", destinataire);
        query.setParameter("demandeur", demandeur);
        query.setParameter("accepte", ACCEPTEE);
        DemandeAmis demandeAmis = (DemandeAmis) query.uniqueResult();
        session.getTransaction().commit();
        return demandeAmis != null;
    }

    @Override
    public DemandeAmis delete(DemandeAmis entity) {
        return null;
    }

    @Override
    public DemandeAmis update(DemandeAmis entity) {
        session.beginTransaction();
        DemandeAmis demandeAmis = (DemandeAmis) session.merge(entity);
        session.getTransaction().commit();
        return demandeAmis;
    }
}
