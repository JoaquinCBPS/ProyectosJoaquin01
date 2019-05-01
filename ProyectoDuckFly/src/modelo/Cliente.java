package modelo;

import java.util.HashSet;
import java.util.Set;

/**
 * Creamos la tabla Cliente coche para definir los constructores, setters y getters necesarios para añadir los
 * parametros definidos en la tabla cliente
 */
public class Cliente implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dniCliente;
	private Persona persona;
	private String nombreUsuario;
	private String contrasenia;
	private String numeroTarjeta;
	private String fechaCaducidad;
	private Set<?> viajes = new HashSet<Object>(0);
	
	/**
	 * Constructor vacío
	 */
	public Cliente() {
	}

	/**
	 * @param personaDAO
	 * @param nombreUsuario
	 * @param contrasenia
	 * @param numeroTarjeta
	 * @param fechaCaducidad
	 *                       Constructor parametrizado para crear un cliente
	 */
	public Cliente(Persona persona, String nombreUsuario, String contrasenia, String numeroTarjeta,
			String fechaCaducidad) {
		this.persona = persona;
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
		this.numeroTarjeta = numeroTarjeta;
		this.fechaCaducidad = fechaCaducidad;
	}

	/**
	 * @param personaDAO
	 * @param nombreUsuario
	 * @param contrasenia
	 * @param numeroTarjeta
	 * @param fechaCaducidad
	 * @param viajeDAOs         Cronstructor Parametrizado para crear un cliente que se conecta con la FK
	 *                       de la tabla viajes
	 */
	public Cliente(Persona persona, String nombreUsuario, String contrasenia, String numeroTarjeta,
			String fechaCaducidad, Set<?> viajes) {
		this.persona = persona;
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
		this.numeroTarjeta = numeroTarjeta;
		this.fechaCaducidad = fechaCaducidad;
		this.viajes = viajes;
	}

	public String getDniCliente() {
		return this.dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNumeroTarjeta() {
		return this.numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getFechaCaducidad() {
		return this.fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Set<?> getViajes() {
		return this.viajes;
	}

	public void setViajes(Set<?> viajes) {
		this.viajes = viajes;
	}

}
