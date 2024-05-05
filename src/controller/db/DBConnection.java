
package controller.db;
import modules.DemandeAmis;
import modules.User;
import modules.donnees.*;
import modules.sports.Sport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class DBConnection {
    private static SessionFactory sessionFactory;
    private static AnnotationConfiguration config;

    public static AnnotationConfiguration getConfig() {
        if(config == null) {
            config = new AnnotationConfiguration();
            config.addAnnotatedClass(Sport.class);
            config.addAnnotatedClass(User.class);
            config.addAnnotatedClass(DemandeAmis.class);
            config.addAnnotatedClass(Donnee.class);
            config.addAnnotatedClass(DonneeNatation.class);
            config.addAnnotatedClass(DonneeTennis.class);
            config.addAnnotatedClass(DonneeEscalade.class);
            config.addAnnotatedClass(DonneeJogging.class);
            config.addAnnotatedClass(DonneeCyclisme.class);
            config.addAnnotatedClass(DonneeMusculation.class);

            String packageName = DBConnection.class.getPackage().getName();
            config.configure("controller/connection.cfg.xml");
        }
        return config;
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try{
                AnnotationConfiguration config = getConfig();
                sessionFactory = config.buildSessionFactory();
            } catch(Throwable ex) {
                System.err.println("Initial session factory creation failed" + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

    public static Session getSession() { return getSessionFactory().openSession(); }
}