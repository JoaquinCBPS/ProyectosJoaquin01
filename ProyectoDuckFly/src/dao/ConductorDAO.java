package dao;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import modelo.Conductor;
import modelo.Viaje;


/**
 * ConductorDAO Contiene los m�todos para manipular la base de datos.
 * @author admin
 *
 */
public class ConductorDAO {
	private static final Logger logger = Logger.getLogger(ConductorDAO.class);
	
	
	public ConductorDAO(){
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
	}
	
	public void addConductor(Conductor c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		logger.info("Conductor saved successfully, Conductor Details="+c);
		session.close();
	}

	public void updateConductor(Conductor c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(c);
		session.getTransaction().commit();
		logger.info("Conductor updated successfully, Conductor Details="+c);
		session.close();
	}


	public List<Conductor> listConductors() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Conductor> ConductorsList = session.createQuery("from Conductor").list();
		session.getTransaction().commit();
		session.close();
		for(Conductor c : ConductorsList){
			logger.info("Conductor List::"+c);
		}
		return ConductorsList;
	}

	public Conductor getConductorById(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();		
		Conductor c = (Conductor) session.load(Conductor.class, id);
		logger.info("Conductor loaded successfully, Conductor details="+c);
		session.close();
		return c;
	}

	public void removeConductor(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();	
        session.beginTransaction();
		Conductor c = (Conductor) session.get(Conductor.class, id);
		if(null != c){
			session.delete(c);
		}
        session.getTransaction().commit();
        session.close();
		logger.info("Conductor deleted successfully, Conductor details="+c);
	}
	
	/**
	 * @param trayecto
	 * @return trayectoTerminado
	 *         El método comunicarFinTrayecto recibe por parámetro el trayecto indicado por el cliente
	 *         Obtenemos la ubicación final del conductor para utilizarlo en el método calcularCocheCercano como
	 *         la ubicación actual del coche.
	 *         Invocamos el método cambioDisponibilidad y le damos el valor falso para cambiar la disponibilidad
	 *         del coche de ocupado a disponible
	 *         Cambiamos el valor de trayectoTerminado a true.
	 *         El método ComunicarFinTrayecto devuelve el valor de trayectoTerminado.
	 */
    public void comunicaFinTrayecto(Viaje trayecto){
		
    	String dniConductor = trayecto.getDniConductor();
    	Conductor c = getConductorById(dniConductor);
    	
    	trayecto.setAcabado(true);
		ViajeDAO vg = new ViajeDAO();
		vg.updateViaje(trayecto);
		
		
		cambioDisponibilidad(false, c);
	}

    
    /**
	 * MÉTODOS NECESARIOS DE LA CLASE CONDUCTOR
	 */

	/**
	 * @param ocupado
	 * @returndisponibilidad
	 * 						El método cambiodisponibilidad recibe por parámetro ocupado, true si el coche está
	 *                       ocupado, false si no está ocupado.
	 *                       Si el coche está ocupado, no está disponible.
	 *                       Si el coche no está ocupado, está disponible
	 *                       El método cambioDisponibilidad devuelve el valor de la disponibilidad del coche.
	 */
	public Conductor cambioDisponibilidad(boolean ocupado, Conductor c){
		boolean disponibilidad;
		
		if(ocupado==true){
			 disponibilidad=false;
			 c.setDisponible(disponibilidad);
		}
		else {
			disponibilidad = true;
			c.setDisponible(disponibilidad);
		}
		updateConductor(c);
		return c;
	}
	
	
	public String tipoDeAveria(){
		
		int probabilidadCocheOperativo= (int)Math.round(Math.random()*4);
		
		String[] averias = {"Voló_y_yo_volé_de_él_hacia_la_arbolada"," Hey,_Doc._No_tenemos_suficiente_plutonio","El_coche_sigue_en_2015","Es_el_conductor_el_que_elige_a_sus_clientes,_M.Rajoy.",
				"Hemos_chocado_con_un_patinete_volador",};
		
		return averias[probabilidadCocheOperativo];
				
	

	}

}
