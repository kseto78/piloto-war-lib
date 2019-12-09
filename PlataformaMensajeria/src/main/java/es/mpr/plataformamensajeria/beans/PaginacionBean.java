package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class PaginacionBean.
 */
public class PaginacionBean {

	/** Constante NUM_PAG_ACTUAL_DEFAULT. */
	private static final int NUM_PAG_ACTUAL_DEFAULT = 1;
	
	/** Constante NUM_PAG_TOTALES_DEFAULT. */
	private static final int NUM_PAG_TOTALES_DEFAULT = 1;
	
	/** Constante NUM_REG_MOSTRAR_DEFAULT_10. */
	private static final int NUM_REG_MOSTRAR_DEFAULT_10 = 10;
	
	/** Constante NUM_REG_MOSTRAR_DEFAULT_5. */
	public static final int NUM_REG_MOSTRAR_DEFAULT_5 = 5;
	
	/** Constante NUM_REG_PAGINACION_INTERMEDIO. */
	public static final int NUM_REG_PAGINACION_INTERMEDIO = 1;	
	
	/** Constante NUM_REG_DIF_PAGINACION_INICIAL_FINAL. */
	public static final int NUM_REG_DIF_PAGINACION_INICIAL_FINAL = 2;	
	
	/** Constante ELEMENTO_INICIAL_PAGINACION. */
	private static final int ELEMENTO_INICIAL_PAGINACION = 0;	
	
	/** Constante ELEMENTO_FINAL_PAGINACION. */
	private static final int ELEMENTO_FINAL_PAGINACION = 0;
	
	/** Constante NUM_REG_TOTALES_DEFAULT. */
	private static final int NUM_REG_TOTALES_DEFAULT = 0;
	
	/**  num pagina actual. */
	//Campos para paginación
	private Integer numPaginaActual = NUM_PAG_ACTUAL_DEFAULT;  
	
	/**  num paginas totales. */
	private Integer numPaginasTotales = NUM_PAG_TOTALES_DEFAULT;
	
	/**  num registros mostrar. */
	private Integer numRegistrosMostrar = NUM_REG_MOSTRAR_DEFAULT_10;
	
	/**  elemento inicial paginacion. */
	private Integer elementoInicialPaginacion = ELEMENTO_INICIAL_PAGINACION;  
	
	/**  elemento final paginacion. */
	private Integer elementoFinalPaginacion = ELEMENTO_FINAL_PAGINACION;
	
	/**  num registros totales. */
	private Integer numRegistrosTotales = NUM_REG_TOTALES_DEFAULT;
	
	/**  json resultados. */
	private String jsonResultados;
	
	/**
	 * Obtener num pagina actual.
	 *
	 * @return num pagina actual
	 */
	public Integer getNumPaginaActual() {
		return numPaginaActual;
	}
	
	/**
	 * Modificar num pagina actual.
	 *
	 * @param numPaginaActual new num pagina actual
	 */
	public void setNumPaginaActual(Integer numPaginaActual) {
		this.numPaginaActual = numPaginaActual;
	}
	
	/**
	 * Obtener num paginas totales.
	 *
	 * @return num paginas totales
	 */
	public Integer getNumPaginasTotales() {
		return numPaginasTotales;
	}
	
	/**
	 * Modificar num paginas totales.
	 *
	 * @param numPaginasTotales new num paginas totales
	 */
	public void setNumPaginasTotales(Integer numPaginasTotales) {
		this.numPaginasTotales = numPaginasTotales;
	}
	
	/**
	 * Obtener num registros mostrar.
	 *
	 * @return num registros mostrar
	 */
	public Integer getNumRegistrosMostrar() {
		return numRegistrosMostrar;
	}
	
	/**
	 * Modificar num registros mostrar.
	 *
	 * @param numRegistrosMostrar new num registros mostrar
	 */
	public void setNumRegistrosMostrar(Integer numRegistrosMostrar) {
		this.numRegistrosMostrar = numRegistrosMostrar;
	}
	
	/**
	 * Obtener elemento inicial paginacion.
	 *
	 * @return elemento inicial paginacion
	 */
	public Integer getElementoInicialPaginacion(){
		return elementoInicialPaginacion;
	}
	
	/**
	 * Modificar elemento inicial paginacion.
	 *
	 * @param elementoInicialPaginacion new elemento inicial paginacion
	 */
	public void setElementoInicialPaginacion(Integer elementoInicialPaginacion) {
		this.elementoInicialPaginacion = elementoInicialPaginacion;
	}
	
	/**
	 * Obtener elemento final paginacion.
	 *
	 * @return elemento final paginacion
	 */
	public Integer getElementoFinalPaginacion(){
		return elementoFinalPaginacion;
	}
	
	/**
	 * Modificar elemento final paginacion.
	 *
	 * @param elementoFinalPaginacion new elemento final paginacion
	 */
	public void setElementoFinalPaginacion(Integer elementoFinalPaginacion) {
		this.elementoFinalPaginacion = elementoFinalPaginacion;
	}
	
	/**
	 * Obtener num registros totales.
	 *
	 * @return num registros totales
	 */
	public Integer getNumRegistrosTotales() {
		return numRegistrosTotales;
	}
	
	/**
	 * Modificar num registros totales.
	 *
	 * @param numRegistrosTotales new num registros totales
	 */
	public void setNumRegistrosTotales(Integer numRegistrosTotales) {
		this.numRegistrosTotales = numRegistrosTotales;
	}
	
	/**
	 * Calcular num paginas totales.
	 *
	 * @param numResultados the num resultados
	 */
	private void calcularNumPaginasTotales(int numResultados){
		int numPaginasTotalesAux = numResultados/numRegistrosMostrar;
		if(numResultados%numRegistrosMostrar>0){
			numPaginasTotalesAux++;
		}
		this.setNumPaginasTotales(numPaginasTotalesAux);
	}
	
	/**
	 * Limpiar paginacion.
	 */
	public void limpiarPaginacion(){
		this.setNumPaginaActual(NUM_PAG_ACTUAL_DEFAULT);
		this.setNumPaginasTotales(NUM_PAG_TOTALES_DEFAULT);
		this.setElementoInicialPaginacion(ELEMENTO_INICIAL_PAGINACION);
		this.setElementoFinalPaginacion(ELEMENTO_FINAL_PAGINACION);
		this.setNumRegistrosTotales(ELEMENTO_INICIAL_PAGINACION);
	}

	/**
	 * Metodo que calcula los elementos de la paginación recibiendo el numero total de elementos.
	 *
	 * @param tamanioListaPaginar the tamanio lista paginar
	 */
	public void calcularElementosPaginacion(Integer tamanioListaPaginar){
		if(tamanioListaPaginar!=null && tamanioListaPaginar>0){
			this.numRegistrosTotales=tamanioListaPaginar;
			this.calcularNumPaginasTotales(tamanioListaPaginar);
			this.setElementoInicialPaginacion(0);
			Integer elementoFinalPaginacion = 0;
			Integer paginaSeleccionada = numPaginaActual;
			if(paginaSeleccionada>numPaginasTotales){
				paginaSeleccionada = numPaginasTotales;
				this.setNumPaginaActual(paginaSeleccionada);
			}else if(paginaSeleccionada<=0){
				paginaSeleccionada = 1;
				this.setNumPaginaActual(paginaSeleccionada);
			}
			Integer numElementPage = numRegistrosMostrar;
			Integer finalPaginacionAux = paginaSeleccionada * numElementPage;
			
			if (finalPaginacionAux > tamanioListaPaginar){
				elementoFinalPaginacion = tamanioListaPaginar;
			}else{
				elementoFinalPaginacion = finalPaginacionAux;
			}
			this.setElementoFinalPaginacion(elementoFinalPaginacion);
			this.setElementoInicialPaginacion(finalPaginacionAux - numElementPage);
		}
	}
	
	/**
	 * Metodo que pagina una lista recibida (lista Original).
	 *
	 * @param listaOriginal the lista original
	 * @return the list<? extends object>
	 */
	public List<? extends Object> aplicarPaginacion(List<? extends Object> listaOriginal){
		List<? extends Object> listaDevuelta = new ArrayList<>();
		if(listaOriginal!=null && !listaOriginal.isEmpty()){
			this.numRegistrosTotales=listaOriginal.size();
			this.calcularNumPaginasTotales(listaOriginal.size());
			this.setElementoInicialPaginacion(0);
			Integer elementoFinalPaginacion = 0;
			Integer paginaSeleccionada = numPaginaActual;
			if(paginaSeleccionada>numPaginasTotales){
				paginaSeleccionada = numPaginasTotales;
				this.setNumPaginaActual(paginaSeleccionada);
			}else if(paginaSeleccionada<=0){
				paginaSeleccionada = 1;
				this.setNumPaginaActual(paginaSeleccionada);
			}
			Integer numElementPage = numRegistrosMostrar;
			Integer finalPaginacionAux = paginaSeleccionada * numElementPage;
			
			if (finalPaginacionAux > listaOriginal.size()){
				elementoFinalPaginacion = listaOriginal.size();
			}else{
				elementoFinalPaginacion = finalPaginacionAux;
			}
			this.setElementoInicialPaginacion(finalPaginacionAux - numElementPage);
			listaDevuelta = listaOriginal.subList(this.getElementoInicialPaginacion(), elementoFinalPaginacion);
		}
		return listaDevuelta;
	}
	
	/**
	 * Obtener json resultados.
	 *
	 * @return json resultados
	 */
	public String getJsonResultados() {
		return jsonResultados;
	}
	
	/**
	 * Modificar json resultados.
	 *
	 * @param jsonResultados new json resultados
	 */
	public void setJsonResultados(String jsonResultados) {
		this.jsonResultados = jsonResultados;
	}
}
