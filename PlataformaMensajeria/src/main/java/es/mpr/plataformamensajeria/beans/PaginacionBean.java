package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;
import java.util.List;

public class PaginacionBean {

	private static final int NUM_PAG_ACTUAL_DEFAULT = 1;
	private static final int NUM_PAG_TOTALES_DEFAULT = 1;
	private static final int NUM_REG_MOSTRAR_DEFAULT_10 = 10;
	public static final int NUM_REG_MOSTRAR_DEFAULT_5 = 5;
	public static final int NUM_REG_PAGINACION_INTERMEDIO = 1;	
	public static final int NUM_REG_DIF_PAGINACION_INICIAL_FINAL = 2;	
	private static final int ELEMENTO_INICIAL_PAGINACION = 0;	
	private static final int ELEMENTO_FINAL_PAGINACION = 0;
	private static final int NUM_REG_TOTALES_DEFAULT = 0;
	
	//Campos para paginación
	private Integer numPaginaActual = NUM_PAG_ACTUAL_DEFAULT;  
	private Integer numPaginasTotales = NUM_PAG_TOTALES_DEFAULT;
	private Integer numRegistrosMostrar = NUM_REG_MOSTRAR_DEFAULT_10;
	private Integer elementoInicialPaginacion = ELEMENTO_INICIAL_PAGINACION;  
	private Integer elementoFinalPaginacion = ELEMENTO_FINAL_PAGINACION;
	private Integer numRegistrosTotales = NUM_REG_TOTALES_DEFAULT;
	
	private String jsonResultados;
	
	public Integer getNumPaginaActual() {
		return numPaginaActual;
	}
	public void setNumPaginaActual(Integer numPaginaActual) {
		this.numPaginaActual = numPaginaActual;
	}
	public Integer getNumPaginasTotales() {
		return numPaginasTotales;
	}
	public void setNumPaginasTotales(Integer numPaginasTotales) {
		this.numPaginasTotales = numPaginasTotales;
	}
	public Integer getNumRegistrosMostrar() {
		return numRegistrosMostrar;
	}
	public void setNumRegistrosMostrar(Integer numRegistrosMostrar) {
		this.numRegistrosMostrar = numRegistrosMostrar;
	}
	
	public Integer getElementoInicialPaginacion(){
		return elementoInicialPaginacion;
	}
	
	public void setElementoInicialPaginacion(Integer elementoInicialPaginacion) {
		this.elementoInicialPaginacion = elementoInicialPaginacion;
	}
	
	public Integer getElementoFinalPaginacion(){
		return elementoFinalPaginacion;
	}
	public void setElementoFinalPaginacion(Integer elementoFinalPaginacion) {
		this.elementoFinalPaginacion = elementoFinalPaginacion;
	}
	
	public Integer getNumRegistrosTotales() {
		return numRegistrosTotales;
	}
	public void setNumRegistrosTotales(Integer numRegistrosTotales) {
		this.numRegistrosTotales = numRegistrosTotales;
	}
	
	private void calcularNumPaginasTotales(int numResultados){
		int numPaginasTotalesAux = numResultados/numRegistrosMostrar;
		if((numResultados%numRegistrosMostrar)>0){
			numPaginasTotalesAux++;
		}
		this.setNumPaginasTotales(numPaginasTotalesAux);
	}
	
	public void limpiarPaginacion(){
		this.setNumPaginaActual(NUM_PAG_ACTUAL_DEFAULT);
		this.setNumPaginasTotales(NUM_PAG_TOTALES_DEFAULT);
//		this.setNumRegistrosMostrar(NUM_REG_MOSTRAR_DEFAULT_10);
		this.setElementoInicialPaginacion(ELEMENTO_INICIAL_PAGINACION);
		this.setElementoFinalPaginacion(ELEMENTO_FINAL_PAGINACION);
		this.setNumRegistrosTotales(ELEMENTO_INICIAL_PAGINACION);
	}

	/**
	 * Metodo que calcula los elementos de la paginación recibiendo el numero total de elementos
	 * @param tamanioListaPaginar
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
	 * Metodo que pagina una lista recibida (lista Original)
	 * @param listaOriginal
	 * @return
	 */
	public List<? extends Object> aplicarPaginacion(List<? extends Object> listaOriginal){
		List<? extends Object> listaDevuelta = new ArrayList<Object>();
		if(listaOriginal!=null && listaOriginal.size()>0){
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
	
	public String getJsonResultados() {
		return jsonResultados;
	}
	public void setJsonResultados(String jsonResultados) {
		this.jsonResultados = jsonResultados;
	}
}
