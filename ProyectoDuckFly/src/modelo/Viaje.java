package modelo;

import java.util.Date;

/**
 * Creamos la clase Viaje para definir los constructores, setters y getters necesarios para añadir los
 * parametros definidos en la tabla Viaje
 */
public class Viaje implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idViaje;
	private Cliente cliente;
	private String dniConductor;
	private Date fechaViaje;
	private double latitudInicial;
	private double longitudInicial;
	private double latitudFinal;
	private double longitudFinal;
	private Integer tiempoEspera;
	private Integer tiempoViaje;
	private Double distanciaViaje;
	private Double precio;
	private Boolean acabado;
	private String incidencia;
	private String valoracionConductor;
	private Boolean cocheXl;
	private Ubicacion ubicacionInicial = new Ubicacion();
	private Ubicacion ubicacionFinal = new Ubicacion();

	public Viaje() {
	}

	/**
	 * @param clienteDAO
	 * @param fechaViaje
	 * @param latitudInicial
	 * @param longitudInicial
	 * @param latitudFinal
	 * @param longitudFinal
	 * @param pais
	 *                        Constructor parametrizado para crear un Viaje
	 * 
	 */
	public Viaje(Cliente cliente, Date fechaViaje, double latitudInicial, double longitudInicial, double latitudFinal,
			double longitudFinal) {
		this.cliente = cliente;
		this.fechaViaje = fechaViaje;
		this.latitudInicial = latitudInicial;
		this.longitudInicial = longitudInicial;
		this.latitudFinal = latitudFinal;
		this.longitudFinal = longitudFinal;
	}

	/**
	 * @param clienteDAO
	 * @param dniConductor
	 * @param fechaViaje
	 * @param latitudInicial
	 * @param longitudInicial
	 * @param latitudFinal
	 * @param longitudFinal
	 * @param tiempoEspera
	 * @param tiempoViaje
	 * @param distanciaViaje
	 * @param precio
	 * @param acabado
	 * @param incidencia
	 * @param valoracionConductor
	 * @param cocheXl
	 * @param conductor
	 *                            Constructor parametrizado para crear Viaje que se conecta con la FK
	 *                            de la tabla Cliente
	 */
	public Viaje(Cliente cliente, String dniConductor, Date fechaViaje, double latitudInicial, double longitudInicial,
			double latitudFinal, double longitudFinal, Integer tiempoEspera, Integer tiempoViaje, Double distanciaViaje,
			Double precio, Boolean acabado, String incidencia, String valoracionConductor, Boolean cocheXl) {
		this.cliente = cliente;
		this.dniConductor = dniConductor;
		this.fechaViaje = fechaViaje;
		this.latitudInicial = latitudInicial;
		this.longitudInicial = longitudInicial;
		this.latitudFinal = latitudFinal;
		this.longitudFinal = longitudFinal;
		this.tiempoEspera = tiempoEspera;
		this.tiempoViaje = tiempoViaje;
		this.distanciaViaje = distanciaViaje;
		this.precio = precio;
		this.acabado = acabado;
		this.incidencia = incidencia;
		this.valoracionConductor = valoracionConductor;
		this.cocheXl = cocheXl;
	}

	public Integer getIdViaje() {
		return this.idViaje;
	}

	public void setIdViaje(Integer idViaje) {
		this.idViaje = idViaje;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDniConductor() {
		return this.dniConductor;
	}

	public void setDniConductor(String dniConductor) {
		this.dniConductor = dniConductor;
	}

	public void setFechaViaje(Date fechaViaje) {
		this.fechaViaje = fechaViaje;
	}

	public double getLatitudInicial() {
		return this.latitudInicial;
	}

	public void setLatitudInicial(double latitudInicial) {
		this.latitudInicial = latitudInicial;
	}

	public double getLongitudInicial() {
		return this.longitudInicial;
	}

	public void setLongitudInicial(double longitudInicial) {
		this.longitudInicial = longitudInicial;
	}

	public double getLatitudFinal() {
		return this.latitudFinal;
	}

	public void setLatitudFinal(double latitudFinal) {
		this.latitudFinal = latitudFinal;
	}

	public double getLongitudFinal() {
		return this.longitudFinal;
	}

	public void setLongitudFinal(double longitudFinal) {
		this.longitudFinal = longitudFinal;
	}

	public Integer getTiempoEspera() {
		return this.tiempoEspera;
	}

	public void setTiempoEspera(Integer tiempoEspera) {
		this.tiempoEspera = tiempoEspera;
	}

	public Integer getTiempoViaje() {
		return this.tiempoViaje;
	}

	public void setTiempoViaje(Integer tiempoViaje) {
		this.tiempoViaje = tiempoViaje;
	}

	public Double getDistanciaViaje() {
		return this.distanciaViaje;
	}

	public void setDistanciaViaje(Double distanciaViaje) {
		this.distanciaViaje = distanciaViaje;
	}

	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Boolean getAcabado() {
		return this.acabado;
	}

	public void setAcabado(Boolean acabado) {
		this.acabado = acabado;
	}

	public String getIncidencia() {
		return this.incidencia;
	}

	public void setIncidencia(String incidencia) {
		this.incidencia = incidencia;
	}

	public String getValoracionConductor() {
		return this.valoracionConductor;
	}

	public void setValoracionConductor(String valoracionConductor) {
		this.valoracionConductor = valoracionConductor;
	}

	public Boolean getCocheXl() {
		return this.cocheXl;
	}

	public void setCocheXl(Boolean cocheXl) {
		this.cocheXl = cocheXl;
	}
	public Date getFechaViaje() {
		return fechaViaje;
	}

	public Ubicacion getUbicacionInicial() {
		ubicacionInicial.setLatitud(latitudInicial);
		ubicacionInicial.setLongitud(longitudInicial);
		return ubicacionInicial;
	}

	public void setUbicacionInicial(Ubicacion ubicacionInicial) {
		this.ubicacionInicial = ubicacionInicial;
		latitudInicial = ubicacionInicial.getLatitud();
		longitudInicial = ubicacionInicial.getLongitud();
	}

	public Ubicacion getUbicacionFinal() {
		ubicacionFinal.setLatitud(latitudFinal);
		ubicacionFinal.setLongitud(longitudFinal);
		return ubicacionFinal;
	}

	public void setUbicacionFinal(Ubicacion ubicacionFinal) {
		this.ubicacionFinal = ubicacionFinal;
		latitudInicial = ubicacionInicial.getLatitud();
		longitudInicial = ubicacionInicial.getLongitud();
	}
	




}
