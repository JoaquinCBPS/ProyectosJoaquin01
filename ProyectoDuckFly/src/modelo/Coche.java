package modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Creamos la clase Coche para definir los constructores, setters y getters necesarios para añadir los
 * parametros definidos en la tabla coche
 */
public class Coche implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String marca;
	private String modelo;
	private Date fechaMatriculacion;
	private Boolean cocheXl;
	private Boolean averia;
	private Set<?> cocheConductors = new HashSet<Object>(0);

	public Coche() {
	}

	/**
	 * @param persona
	 * @param nombreUsuario
	 * @param contrasenia
	 * @param numeroTarjeta
	 * @param fechaCaducidad
	 *                       Constructor parametrizado para crear un coche
	 */
	public Coche(String matricula, String marca, String modelo, Date fechaMatriculacion) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.fechaMatriculacion = fechaMatriculacion;
	}

	/**
	 * @param persona
	 * @param nombreUsuario
	 * @param contrasenia
	 * @param numeroTarjeta
	 * @param fechaCaducidad
	 *                       Constructor parametrizado para crear un coche que se conecta con la FK
	 *                       de la tabla cocheconductor
	 */
	public Coche(String matricula, String marca, String modelo, Date fechaMatriculacion, Boolean cocheXl,
			Boolean averia, Set<?> cocheConductors) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.fechaMatriculacion = fechaMatriculacion;
		this.cocheXl = cocheXl;
		this.averia = averia;
		this.cocheConductors = cocheConductors;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Date getFechaMatriculacion() {
		return this.fechaMatriculacion;
	}

	public void setFechaMatriculacion(Date fechaMatriculacion) {
		this.fechaMatriculacion = fechaMatriculacion;
	}

	public Boolean getCocheXl() {
		return this.cocheXl;
	}

	public void setCocheXl(Boolean cocheXl) {
		this.cocheXl = cocheXl;
	}

	public Boolean getAveria() {
		return this.averia;
	}

	public void setAveria(Boolean averia) {
		this.averia = averia;
	}

	public Set<?> getCocheConductors() {
		return this.cocheConductors;
	}

	public void setCocheConductors(Set<?> cocheConductors) {
		this.cocheConductors = cocheConductors;
	}

}
