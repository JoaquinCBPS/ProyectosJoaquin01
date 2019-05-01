package modelo;

import java.util.Date;

/**
 * Creamos la clase Cocheconductor para definir los constructores, setters y getters necesarios para añadir los
 * parametros definidos en la tabla cocheConductor
 */
public class CocheConductor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idCocheConductor;
	private Coche coche;
	private Conductor conductor;
	private Date fecha;

	public CocheConductor() {
	}

	/**
	 * @param cocheDAO
	 * @param conductorDAO
	 * @param fecha
	 *                  Constructor parametrizado para crear Cocheconductor
	 */
	public CocheConductor(Coche coche, Conductor conductor, Date fecha) {
		this.coche = coche;
		this.conductor = conductor;
		this.fecha = fecha;
	}

	public Integer getIdCocheConductor() {
		return this.idCocheConductor;
	}

	public void setIdCocheConductor(Integer idCocheConductor) {
		this.idCocheConductor = idCocheConductor;
	}

	public Coche getCoche() {
		return this.coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public Conductor getConductor() {
		return this.conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
