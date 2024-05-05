package controller.data_access_object;

import exception.UtilisateurNotFoundException;
import modules.DemandeAmis;
import modules.User;
import modules.donnees.DonneeEscalade;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

public final class UserDAO extends GenericDAO<User> {

    /**
     * The unique instance of the class prepared in an eager way (object created
     * at beginning).
     */
    private final static UserDAO instance = new UserDAO();

    /**
     * Private constructor ensuring no access from outside.
     */
    private UserDAO() {
        super(User.class);
    }

    /**
     * Static method allows users to get the unique instance of the class.
     *
     * @return the unique instance of the class.
     */
    public static UserDAO getInstance() {
        return instance;
    }

    @Override
    public User save(User user) {
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public User findById(Serializable id) {
        session.beginTransaction();
        User user = (User) session.get(User.class,id);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public User delete(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {
        session.beginTransaction();
        User user = (User) session.merge(entity);
        session.getTransaction().commit();
        return user;
    }

    public User updateDemandeAmis(User user, DemandeAmis demandeAmis){
        session.beginTransaction();
        session.evict(user);
        user.getDemandes().add(demandeAmis);
        session.update(user);
        session.getTransaction().commit();
        return user;
    }
    /**
     * Finds an entity by its "nom" property.
     *
     * @param nom The value of the "nom" property to search for.
     * @return The found entity, or null if not found.
     */
    public User findByNom(String nom) throws UtilisateurNotFoundException {

        session.beginTransaction();

        Query query = session.createQuery("FROM User u WHERE u.nom=:nom");
        query.setParameter("nom", nom);

        User utilisateur = (User) query.uniqueResult();

        session.getTransaction().commit();

        // If a supprimer ???
        if (utilisateur == null) {
            utilisateur = new User("","");
        }

        return utilisateur;
    }

    public void addFriend(User currentUser, User friend) {

        session.beginTransaction();

        if (friend != null && !currentUser.getAmis().contains(friend)) {
            currentUser.getAmis().add(friend);

            session.persist(currentUser);
        }

        session.getTransaction().commit();
    }



    public List<User> getAllUsersExceptCurrentUser(User currentUser) {
        session.beginTransaction();

        Query query = session.createQuery("FROM User u WHERE u != :currentUser");
        query.setParameter("currentUser", currentUser);
        List<User> users = query.list();

        session.getTransaction().commit();

        return users;
    }



}
