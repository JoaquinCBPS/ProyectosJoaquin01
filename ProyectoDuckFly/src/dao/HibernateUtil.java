package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase que contiene un método estático que inicializa Hibernate y crea el objeto SessionFactory.
 * Está encerrado en un try-catch para gestionar posibles errores que puedan suceder.
 */
public class HibernateUtil{
    private static final SessionFactory sessionFactory;
    static {
        try {
        	Configuration configuration = new Configuration();
        	configuration.configure();
        	sessionFactory = configuration.buildSessionFactory();//Se crea la sesión.
        } catch (Throwable ex) {//Captura de excepciones.
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);//Impresión por pantalla de errores.
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Este método accede a Sesionfactory, es un método estático que sera accesible por el resto de clases que 
     * lo necesitan sin necesidad de instanciar un objeto.
     * @return sessionfactory 
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}