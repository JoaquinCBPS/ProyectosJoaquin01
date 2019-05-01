package modelo;

import java.util.Date;

/**
 * Creamos la clase Persona para definir los constructores, setters y getters necesarios para añadir los
 * parametros definidos en la tabla Conductor
 */
public class Persona implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dniPersona;
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	private Date fechaNacimiento;
	private String direccion;
	private String codigoPostal;
	private String ciudad;
	private String pais;
	private Cliente cliente;
	private Conductor conductor;

	public Persona() {
	}

	/**
	 * @param dniPersona
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param telefono
	 * @param fechaNacimiento
	 * @param direccion
	 * @param codigoPostal
	 * @param ciudad
	 * @param pais
	 *                        Constructor parametrizado para crear Persona
	 */
	public Persona(String dniPersona, String nombre, String apellidos, String email, String telefono,
			Date fechaNacimiento, String direccion, String codigoPostal, String ciudad, String pais) {
		this.dniPersona = dniPersona;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.ciudad = ciudad;
		this.pais = pais;
	}
	/**
	 * @param dniPersona
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param telefono
	 * @param fechaNacimiento
	 * @param direccion
	 * @param codigoPostal
	 * @param ciudad
	 * @param pais
	 * @param clienteDAO
	 * @param conductorDAO
	 *                        Constructor parametrizado para crear Conducor que se conecta con la FK
	 *                        de la tabla Cliente y Conductor
	 * 
	 */
	public Persona(String dniPersona, String nombre, String apellidos, String email, String telefono,
			Date fechaNacimiento, String direccion, String codigoPostal, String ciudad, String pais, Cliente cliente,
			Conductor conductor) {
		this.dniPersona = dniPersona;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.ciudad = ciudad;
		this.pais = pais;
		this.cliente = cliente;
		this.conductor = conductor;
	}

	public String getDniPersona() {
		return this.dniPersona;
	}

	public void setDniPersona(String dniPersona) {
		this.dniPersona = dniPersona;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Conductor getConductor() {
		return this.conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

}
