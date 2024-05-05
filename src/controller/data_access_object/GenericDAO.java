package controller.data_access_object;

import controller.db.DBConnection;
import org.hibernate.Session;
import java.io.Serializable;

public abstract class GenericDAO<T> {
    /**
     * The entity class type this DAO manages.
     */
    private final Class<T> entityClass;
    /**
     * The current Hibernate session, used for database interactions.
     */
    protected Session session = DBConnection.getSession();

    /**
     * Creates a new GenericDAO instance for the specified entity class.
     *
     * @param entityClass The class of the entity to be managed by this DAO.
     */
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Saves a new entity to the database.
     *
     * @param entity The entity to be saved.
     * @return The saved entity.
     */
    public abstract T save(T entity);

    /**
     * Finds an entity by its primary key ID.
     *
     * @param id The primary key ID of the entity to find.
     * @return The found entity, or null if not found.
     */
    public abstract T findById(Serializable id);

    /**
     * Deletes an entity from the database.
     *
     * @param entity The entity to be deleted.
     * @return The deleted entity.
     */
    public abstract T delete(T entity);

    /**
     * Updates an existing entity in the database.
     *
     * @param entity The entity to be updated.
     * @return The updated entity.
     */
    public abstract T update(T entity);

    /**
     * Gets the current Hibernate session.
     *
     * @return The current Session object.
     */
    public Session getSession() {
        return session;
    }

    /**
     * Sets the Hibernate session to be used for database interactions.
     *
     * @param session The Session object to set.
     */
    public void setSession(Session session) {
        this.session = session;
    }
}