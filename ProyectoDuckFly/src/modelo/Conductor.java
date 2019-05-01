package modelo;
// Generated 02-dic-2018 17:07:17 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Creamos la clase Conductor para definir los constructores, setters y getters necesarios para añadir
 * los
 * parametros definidos en la tabla Conductor
 */
public class Conductor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dniConductor;
	private Persona persona;
	private Date fechaInicioContrato;
	private double salario;
	private boolean disponible;
	private String numeroLicencia;
	private String numeroSeguridadSocial;
	private Double valoracionMedia;
	private Set<?> cocheConductors = new HashSet<Object>(0);
	private Ubicacion ubicacion = new Ubicacion();


	public Conductor() {
	}

	/**
	 * @param personaDAO
	 * @param fechaInicioContrato
	 * @param salario
	 * @param disponible
	 * @param numeroLicencia
	 * @param numeroSeguridadSocial
	 *                              Constructor parametrizado para crear Conductor
	 * 
	 */
	public Conductor(Persona persona, Date fechaInicioContrato, double salario, boolean disponible,
			String numeroLicencia, String numeroSeguridadSocial) {
		this.persona = persona;
		this.fechaInicioContrato = fechaInicioContrato;
		this.salario = salario;
		this.disponible = disponible;
		this.numeroLicencia = numeroLicencia;
		this.numeroSeguridadSocial = numeroSeguridadSocial;
	}
	/**
	 * @param personaDAO
	 * @param fechaInicioContrato
	 * @param salario
	 * @param disponible
	 * @param numeroLicencia
	 * @param numeroSeguridadSocial
	 * @param valoracionMedia
	 * @param cocheConductors
	 *                              Constructor parametrizado para crear Conducor que se conecta con la FK
	 *                              de la tabla cocheconductor
	 */
	public Conductor(Persona persona, Date fechaInicioContrato, double salario, boolean disponible,
			String numeroLicencia, String numeroSeguridadSocial, Double valoracionMedia, Set<?> cocheConductors) {
		this.persona = persona;
		this.fechaInicioContrato = fechaInicioContrato;
		this.salario = salario;
		this.disponible = disponible;
		this.numeroLicencia = numeroLicencia;
		this.numeroSeguridadSocial = numeroSeguridadSocial;
		this.valoracionMedia = valoracionMedia;
		this.cocheConductors = cocheConductors;
	}

	public String getDniConductor() {
		return this.dniConductor;
	}

	public void setDniConductor(String dniConductor) {
		this.dniConductor = dniConductor;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Date getFechaInicioContrato() {
		return this.fechaInicioContrato;
	}

	public void setFechaInicioContrato(Date fechaInicioContrato) {
		this.fechaInicioContrato = fechaInicioContrato;
	}

	public double getSalario() {
		return this.salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public boolean isDisponible() {
		return this.disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getNumeroLicencia() {
		return this.numeroLicencia;
	}

	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}

	public String getNumeroSeguridadSocial() {
		return this.numeroSeguridadSocial;
	}

	public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
		this.numeroSeguridadSocial = numeroSeguridadSocial;
	}

	public Double getValoracionMedia() {
		return this.valoracionMedia;
	}

	public void setValoracionMedia(Double valoracionMedia) {
		this.valoracionMedia = valoracionMedia;
	}

	public Set<?> getCocheConductors() {
		return this.cocheConductors;
	}

	public void setCocheConductors(Set<?> cocheConductors) {
		this.cocheConductors = cocheConductors;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	

}
