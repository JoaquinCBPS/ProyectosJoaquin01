package manejador;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import dao.ClienteDAO;
import dao.HibernateUtil;
import dao.PersonaDAO;
import modelo.Cliente;
import modelo.Persona;


/**
 * Creamos la clase GestionUsuarios para tratar todas las operaciones relacionadas con la gestion de los usuarios en la base de datos.
 *
 */
public class GestionUsuarios {
	
	
    
	/**Comprueba si la contarseña introducida por parámetro corresponde al usuario introducido por parámetro.
	 * @param usuario El usuario a validar que intenta acceder a la aplicación	
	 * @param password La contraseña introducida por el usuario para acceder a la aplicación
	 * @return Un valor booleando dependiendo de si los datos introducidos son correctos.
	 */
	public boolean comprobacionUsuario(String usuario, String password){
		boolean validado = false;	 
		try{
			//Consultamos a la Base de Datos el dni correspondiente al nombre de usuario introducido por el cliente
			BasicConfigurator.configure();
	        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
			Session session = HibernateUtil.getSessionFactory().openSession();
			String dniCliente = (String) session.createQuery("select c.dniCliente from Cliente c where c.nombreUsuario='"+usuario+"'").getSingleResult();
			session.close();
			//Instanciamos un objeto cliente con los atributos correspondientes al dni introducido.
			ClienteDAO clienteDao = new ClienteDAO();
			Cliente c = clienteDao.getClienteById(dniCliente);
			//Si el objeto cliente no es nulo y su contraseña coincide con la contraseña introducida devolveremos un valor true
			if(c!=null && c.getContrasenia().equals(password) ){
				validado = true;
			}
		//Si la búsqueda nos devuelve una excepción sin resultados devolveremos el valor false (el usuario no se encuentra en la base de datos).
		}catch(javax.persistence.NoResultException e){
			validado = false;
		}

		return validado;	
	}
	
	public String extraerDni(String nombreUsuario){
		BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
		Session session = HibernateUtil.getSessionFactory().openSession();
		String dniCliente = (String) session.createQuery("select c.dniCliente from Cliente c where c.nombreUsuario='"+nombreUsuario+"'").getSingleResult();
		session.close();
        return dniCliente;
	}
	
	/**
	 * Método para la introducción a la base de datos de un nuevo cliente.
	 * @param p Objeto persona con sus correspondientes atributos (extraidos del formulario)
	 * @param c Objeto cliente con sus correspondientes atributos (extraidos del formulario)
	 * @return Devuelve un String cuyo valor sera 'ok' si la inserción se ha producido correctamente. En caso contrario devolverá un string con la causa por la cual esta inserción
	 * no ha podido ser llevada a cabo.
	 */
	public String nuevoUsuario(Persona p, Cliente c){
		String problema="ok";
	
		PersonaDAO pd = new PersonaDAO();
		ClienteDAO cd = new ClienteDAO();
		
		//Comprobamos que el dni introucido no existe dentro de la Base de Datos
		if(comprobarCampo("Persona", "dniPersona", p.getDniPersona())){
			problema = "El DNI del usuario ya existe en la base de datos";
		//Comprobamos que el nombre de usuario introucido no existe dentro de la Base de Datos	
		}else if(comprobarCampo("Cliente","nombreUsuario",c.getNombreUsuario())){
			problema = "El nombre de usuario ya existe en la base de datos";
		//Comprobamos que el email introucido no existe dentro de la Base de Datos
		}else if(comprobarEmail(p.getEmail())){
			problema = "El email introducido ya existe en la base de datos";
		//Comprobamos que la fecha de caducidad correspondiente a la tarjeta introducida no es una fecha pasada
		}else if(cd.comprobarTarjeta(c.getFechaCaducidad())){
			problema = "La tarjeta introducida esta caducada";
		//En el caso de que todos los datos comprobados sean nuevos se procederá a introducir a la Base de Datos a la persona y al cliente
		}else{
			try{
				pd.addPersona(p);
				cd.addCliente(c);
			//De no poder introducir en la Base de Datos a la persona y al cliente devolveremos un String con el mensajer correspondiente.
			}catch(Exception e){
				problema="El registro no ha podido ser completado, por favor vuelva a intentarlo más tarde";
			}	
		}
		return problema;
	}
	
	/**
	 * Con este metodo comprobamos que un email no existe en la Base de Datos
	 * @param email El correo introducido por el usuario en el formulario
	 * @return Devuelve un valor booleano indicandonos el resultado de nuestra busqueda
	 */
	public boolean comprobarEmail(String email){
		boolean existe = false;
		String comprobacion;
		try{
			BasicConfigurator.configure();
	        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
			Session session = HibernateUtil.getSessionFactory().openSession();
			comprobacion = (String) session.createQuery("select c.email from Persona c where c.email=:email").setParameter("email", email).getSingleResult();
			session.close();
		}catch(Exception e){
			comprobacion = null;
		}

		if(comprobacion != null){
			existe = true;
		}
		return existe;
	}
	
	/**
	 * Metodo generico que nos permite comprobar si un valor cualquiera se encuentra en la Base de Datos
	 * @param tabla Tabla en la cual buscaremos el valor concreto
	 * @param campo Nombre del campo en el cual buscaremos el valor concreto
	 * @param valor Valor a buscar
	 * @return Devuelve true si el valor esta en la Base de Datos y false si no lo encontramos.
	 */
	public boolean comprobarCampo(String tabla, String campo, String valor){
		boolean existe = false;
		String comprobacion;
		try{
			BasicConfigurator.configure();
	        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
			Session session = HibernateUtil.getSessionFactory().openSession();

			comprobacion = (String) session.createQuery("select c."+campo+" from "+tabla+" c where c."+campo+"='"+valor+"'").getSingleResult();
			session.close();
		}catch(Exception e){
			comprobacion = null;
		}

		if(comprobacion != null){
			existe = true;
		}
		return existe;
	}
	



}
