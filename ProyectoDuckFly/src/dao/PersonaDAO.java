package dao;

import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import modelo.Persona;


/**
 * PersonaDAO Contiene todos los métodos para poder acceder a la base de datos.
 * @author Grupo3
 */
public class PersonaDAO {
	private static final Logger logger = Logger.getLogger(PersonaDAO.class);
	
	public PersonaDAO(){
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
	}
	
	public void addPersona(Persona c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		logger.info("Persona saved successfully, Persona Details="+c);
		session.close();
	}

	public void updatePersona(Persona c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(c);
		session.getTransaction().commit();
		logger.info("Persona updated successfully, Persona Details="+c);
		session.close();
	}


	public List<Persona> listPersonas() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Persona> PersonasList = session.createQuery("from Persona").list();
		session.getTransaction().commit();
		session.close();
		for(Persona c : PersonasList){
			logger.info("Persona List::"+c);
		}
		return PersonasList;
	}

	public Persona getPersonaById(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();		
		Persona c = (Persona) session.load(Persona.class, id);
		logger.info("Persona loaded successfully, Persona details="+c);
		session.close();
		return c;
	}

	public void removePersona(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();	
        session.beginTransaction();
		Persona c = (Persona) session.get(Persona.class, id);
		if(null != c){
			session.delete(c);
		}
        session.getTransaction().commit();
        session.close();
		logger.info("Persona deleted successfully, Persona details="+c);
	}

}
