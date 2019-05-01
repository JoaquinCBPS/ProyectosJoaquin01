package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import modelo.CocheConductor;


/**
 * @author Grupo 3
 * Clase CocheConductorDao con acceso a datos, contiene los metodos para manipular la tabla CocheConductor de la base de datos.
 * 
 */
public class CocheConductorDAO {
	private static final Logger logger = Logger.getLogger(CocheConductorDAO.class);//Creamos un objeto logger que muestra mensajes con informaci�n en la clase.


	/**
	 * Constructor por defecto que inicializa el Logger.
	 */

	public CocheConductorDAO(){
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
	}
	
	/**
	 * Metodo que a�ade un par coche-conductor.
	 * @param c
	 */
	public void addCocheConductor(CocheConductor c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		logger.info("CocheConductor saved successfully, CocheConductor Details="+c);
		session.close();
	}

	/**
	 * Actualiza una pareja coche-conductor en la base de datos, a partir del par�metro id
	 * @param c par�metro id.                                      
	 */               
	public void updateCocheConductor(CocheConductor c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(c);
		session.getTransaction().commit();
		logger.info("CocheConductor updated successfully, CocheConductor Details="+c);
		session.close();
	}


	/**
	 * Metodo que devueleve un ArrayList de parejas coche-conductor
	 * @return Lista Coche-conductor.
	 */
	public  List<CocheConductor> listCocheConductors() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<CocheConductor> cocheConductorsList = session.createQuery("from CocheConductor").list();
		session.getTransaction().commit();
		session.close();
		for(CocheConductor c : cocheConductorsList){
			logger.info("CocheConductor List::"+c);
		}
		return cocheConductorsList;
	}

	/**
	 * Devuelve un conductor a a trav�s del id introducido.
	 * @param id
	 * @return Devuelve una asignacion cocheConductor
	 */
	public  CocheConductor getCocheConductorById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();		
		CocheConductor c = (CocheConductor) session.load(CocheConductor.class, id);
		logger.info("CocheConductor loaded successfully, CocheConductor details="+c);
		session.close();
		return c;
	}

	/**
	 * Borra un cocheconductor de la base de datos.
	 * @param id
	 */
	public void removeCocheConductor(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();	
        session.beginTransaction();
		CocheConductor c = (CocheConductor) session.get(CocheConductor.class, id);
		if(null != c){
			session.delete(c);
		}
        session.getTransaction().commit();
        session.close();
		logger.info("CocheConductor deleted successfully, CocheConductor details="+c);
	}
	
	public  CocheConductor getCocheConductorByDni(String dni){
		List<CocheConductor> miConductor = new ArrayList<CocheConductor>();
		List<CocheConductor> conductores = listCocheConductors();
		for(CocheConductor c: conductores){
			if(c.getConductor().getDniConductor().equals(dni)){
				miConductor.add(c);
			}
		}
		int idUltimoViaje = 0;
		for(CocheConductor c:miConductor){
			if(c.getIdCocheConductor()>idUltimoViaje){
				idUltimoViaje = c.getIdCocheConductor();
			}
		}
		CocheConductor conductor = getCocheConductorById(idUltimoViaje);
		
		return conductor;
		
	}
	

}
