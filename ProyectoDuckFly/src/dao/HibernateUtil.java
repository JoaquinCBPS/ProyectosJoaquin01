package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase que contiene un m�todo est�tico que inicializa Hibernate y crea el objeto SessionFactory.
 * Est� encerrado en un try-catch para gestionar posibles errores que puedan suceder.
 */
public class HibernateUtil{
    private static final SessionFactory sessionFactory;
    static {
        try {
        	Configuration configuration = new Configuration();
        	configuration.configure();
        	sessionFactory = configuration.buildSessionFactory();//Se crea la sesi�n.
        } catch (Throwable ex) {//Captura de excepciones.
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);//Impresi�n por pantalla de errores.
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Este m�todo accede a Sesionfactory, es un m�todo est�tico que sera accesible por el resto de clases que 
     * lo necesitan sin necesidad de instanciar un objeto.
     * @return sessionfactory 
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}