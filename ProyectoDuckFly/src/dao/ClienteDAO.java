package dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import modelo.Cliente;
import modelo.Conductor;
import modelo.Ubicacion;
import modelo.Viaje;


/**
 * Cliente Dao(objetos de acceso a datos)
 * Contiene los m�todos necesarios para manipular la base de datos.
 * @author admin
 */
public class ClienteDAO {

	private static final Logger logger = Logger.getLogger(ClienteDAO.class);
    private static Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	public ClienteDAO(){
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
	}
	
	public void addCliente(Cliente c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		logger.info("Cliente saved successfully, Cliente Details="+c);
		session.close();
	}

	public void updateCliente(Cliente c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(c);
		session.getTransaction().commit();
		logger.info("Cliente updated successfully, Cliente Details="+c);
		session.close();
	}


	public List<Cliente> listClientes() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Cliente> clientesList = session.createQuery("from Cliente").list();
		session.getTransaction().commit();
		session.close();
		for(Cliente c : clientesList){
			logger.info("Cliente List::"+c);
		}
		return clientesList;
	}

	public  Cliente getClienteById(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();		
		Cliente c = (Cliente) session.load(Cliente.class, id);
		logger.info("Cliente loaded successfully, Cliente details="+c);
		session.close();
		return c;
	}

	public void removeCliente(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();	
        session.beginTransaction();
		Cliente c = (Cliente) session.get(Cliente.class, id);
		if(null != c){
			session.delete(c);
		}
        session.getTransaction().commit();
        session.close();
		logger.info("Cliente deleted successfully, Cliente details="+c);
	}
	

	
	/**
	 * @param ubicacionInicial
	 * @param ubicacionFinal
	 * @param cocheXL
	 * @param dniCliente
	 * @return trayecto
	 * Para pedir un coche el cliente debe introducir la ubicacion inicial, la ubicacion final, si quiere
	 * un coche normal o XL.
	 * El m�todo recibe el dni del cliente
	 */
	
	public Viaje pedirCoche(Ubicacion ubicacionInicial, Ubicacion ubicacionFinal, boolean cocheXL, String dniCliente){	
		
		Cliente cliente = getClienteById(dniCliente);
		
		Viaje trayecto = new Viaje(cliente, timestamp, ubicacionInicial.getLatitud(), ubicacionInicial.getLongitud()
				, ubicacionFinal.getLatitud(), ubicacionFinal.getLongitud());
		trayecto.setCocheXl(cocheXL);
	
		return trayecto;
	}
	
	
	/**
	 * @param trayecto
	 * @return aceptado
	 * Para que el cliente acepte el viaje al m�todo aceptarViaje le entra como parámetro el trayecto que
	 * ha solicitado
	 */
	public boolean aceptarViaje(Viaje trayecto){
		
		ViajeDAO viajeDAO = new ViajeDAO();
		ConductorDAO conductorDAO = new ConductorDAO();
		Conductor c = conductorDAO.getConductorById(trayecto.getDniConductor());
		c.setDisponible(false);
		viajeDAO.addViaje(trayecto);
		conductorDAO.updateConductor(c);
		
		
		boolean aceptado = true;

		return aceptado;
	}
	
	
	
	public boolean cancelarViaje()
	{
		/**
		 * Indicamos que el viaje ha sido cancelado cambiando el valor de aceptado a false
		 */
		boolean aceptado = false;
		/**
		 * El método aceptarViaje devuelve el valor de aceptado
		 */
		return aceptado;
	}
	
	
	
	/**
	 * @param fechaCaducidad
	 * @returncaducado
	 * 				El método comprobarTarjeta recibe la fecha de caducidad de la tarjeta de credito
	 *                 introducida por el cliente.
	 *                 El método comprueba que la fecha de la tarjeta no esté caducada para ello comprobamos si el
	 *                 mes y el año son mayores al mes y año actual
	 */
	public boolean comprobarTarjeta(String fechaCaducidad){
		/**
		 * Creamos la variable caducado donde se guarda el resultado del método, true si la tarjeta está caducada y
		 * false si la tarjeta noe stá caducada
		 */
		boolean caducado;
		/**
		 * Instanciamos el objeto Calendar para obtener el mes y el año actual
		 */
		Calendar c = Calendar.getInstance();
		int mes = c.get(Calendar.MONTH)+1;
		int anio = c.get(Calendar.YEAR)%100;
		/**
		 * Obtenemos los datos de mes, años y siglo del String fechaCaducidad
		 */
		int mesCaducidad = Integer.parseInt(fechaCaducidad.substring(0, 2));
	    int anioCaducidad = Integer.parseInt(fechaCaducidad.substring(3));
		/**
		 * Si el mes actual es menor al de la tarjeta, el año actual es menor que el de la tarjeta:
		 * La tarjeta no está caducada.
		 * En caso contrario la tarjeta está caducada
		 */
	    if(mes<=mesCaducidad && anio<=anioCaducidad){
	    	caducado = false;
	    }else{
	    	caducado = true;
	    }
		/**
		 * El método comprobarTarjeta devuelve el valor de caducado
		 */
		return caducado;
	}

	

}

