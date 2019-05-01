package dao;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import modelo.Coche;


/**
 * CocheDao, contiene todos los m�todos para poder acceder a la base de datos.
 * @author Grupo 3
 *
 */
public class CocheDAO {
	private static final Logger logger = Logger.getLogger(CocheDAO.class);
	
	public CocheDAO(){
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
	}
	
	public void addCoche(Coche c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		logger.info("Coche saved successfully, Coche Details="+c);
		session.close();
	}

	public void updateCoche(Coche c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(c);
		session.getTransaction().commit();
		logger.info("Coche updated successfully, Coche Details="+c);
		session.close();
	}


	public List<Coche> listCoches() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Coche> CochesList = session.createQuery("from Coche").list();
		session.getTransaction().commit();
		session.close();
		for(Coche c : CochesList){
			logger.info("Coche List::"+c);
		}
		return CochesList;
	}

	public Coche getCocheById(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();		
		Coche c = (Coche) session.load(Coche.class, id);
		logger.info("Coche loaded successfully, Coche details="+c);
		session.close();
		return c;
	}

	public void removeCoche(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();	
        session.beginTransaction();
		Coche c = (Coche) session.get(Coche.class, id);
		if(null != c){
			session.delete(c);
		}
        session.getTransaction().commit();
        session.close();
		logger.info("Coche deleted successfully, Coche details="+c);
	}
	
	
	
	/**
	 * @return devuelve un true si el coche tiene una averia.
	 *         El método averiado genera una probabilidad de una avería antes de comenzar la ruta.
	 *         Avería por defecto es false.
	 *         La probabilidad de avería es de 1/100, si probabilidadCocheOperativo toma el valor 1, cambia el
	 *         estado de averia a true
	 */
	public boolean averiado(String matricula){
		
		int probabilidadCocheOperativo= (int)(Math.random()*100+1) ;
		Coche c = getCocheById(matricula);
	    if(probabilidadCocheOperativo ==1){
		c.setAveria(true);
	    }
	    return c.getAveria();
	}

}
