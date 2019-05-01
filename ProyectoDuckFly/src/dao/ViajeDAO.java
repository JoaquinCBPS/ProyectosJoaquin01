package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import modelo.Viaje;



/**
 * ViajeDAO contiene los m�todos para manipular la base de datos.
 * @author Grupo 3
 */
public class ViajeDAO  {
	
	
	private static final Logger logger = Logger.getLogger(ViajeDAO.class);



	public ViajeDAO(){
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
	}
	
	public void addViaje(Viaje c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		logger.info("Viaje saved successfully, Viaje Details="+c);
		session.close();
	}

	public void updateViaje(Viaje c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(c);
		session.getTransaction().commit();
		logger.info("Viaje updated successfully, Viaje Details="+c);
		session.close();
	}


	public  List<Viaje> listViajes() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Viaje> viajesList = session.createQuery("from Viaje").list();
		session.getTransaction().commit();
		session.close();
		for(Viaje c : viajesList){
			logger.info("Viaje List::"+c);
		}
		return viajesList;
	}

	public  Viaje getViajeById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();		
		Viaje v = (Viaje) session.load(Viaje.class, id);
		logger.info("Viaje loaded successfully, Viaje details="+v);
		session.close();
		return v;
	}

	public void removeViaje(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();	
        session.beginTransaction();
		Viaje c = (Viaje) session.get(Viaje.class, id);
		if(null != c){
			session.delete(c);
		}
        session.getTransaction().commit();
        session.close();
		logger.info("Viaje deleted successfully, Viaje details="+c);
	}
	
	public List<Viaje> getViajeByDni(String dni){
		List<Viaje> misViajes = new ArrayList<Viaje>();
		List<Viaje> viajes = listViajes();
		for(Viaje v: viajes){
			if(v.getCliente().getDniCliente().equals(dni)){
				misViajes.add(v);
			}
		}
		return misViajes;
	}
	
	public  Viaje getLastViaje(String dni){
		List<Viaje> misViajes = getViajeByDni(dni);
		int idMaxViaje = 0;
		for(Viaje v:misViajes){
			if(v.getIdViaje()>idMaxViaje){
				idMaxViaje = v.getIdViaje();
			}
		}
		Viaje miViaje = getViajeById(idMaxViaje);
		
		return miViaje;
	}
	


}
