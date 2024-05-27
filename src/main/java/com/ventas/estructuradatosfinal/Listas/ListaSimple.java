package com.ventas.estructuradatosfinal.Listas;

import java.io.Serializable;
import java.util.Iterator;

/**
 * 
 * Definici�n de la clase lista Simple de tipo Generics
 * @param <T>
 * 
 * **/

public class ListaSimple<T> implements Iterable<T>, Serializable {

	private Nodo<T> nodoPrimero;
	private Nodo<T> nodoUltimo;
	private int tamanio;
	

	public ListaSimple() {
		nodoPrimero = null;
		nodoPrimero = null;
		tamanio = 0;
	}
	
	

	
	
	/**
	 * Agregar UN ELEMENTO al inicio de la lista
	 * @param valorNodo
	 */
	public void agregarInicio(T valorNodo) {
		
		Nodo<T> nuevoNodo = new Nodo<>(valorNodo);
		
		if(estaVacia())
		{
			nodoPrimero = nuevoNodo;
		}
		else
		{
			nuevoNodo.setSiguienteNodo(nodoPrimero);
			nodoPrimero = nuevoNodo;
		}
		tamanio++;
	}
	
	
	/**
	 * Agregar UN ELEMENTO al final de la lista
	 * @param valorNodo
	 */
	public void agregarfinal(T valorNodo) {
		
		Nodo<T> nodo = new Nodo<>(valorNodo);
		
		if( estaVacia() ) {
			nodoPrimero = nodoUltimo = nodo;
		}else {
			nodoUltimo.setSiguienteNodo(nodo);
			nodoUltimo = nodo;
		}
		
		tamanio++;
	}
	
	
	/**
	 * OBTIENE EL VALOR DE UN NODO DADO UN INDICE
	 * @param indice
	 * @return
	 */
	public T obtenerValorNodo(int indice) {
		
		Nodo<T> nodoTemporal = null;
		int contador = 0;
		
		if(indiceValido(indice))
		{
			nodoTemporal = nodoPrimero;
			
			while (contador < indice) {
				
				nodoTemporal = nodoTemporal.getSiguienteNodo();
				contador++;
			}
		}
		
		if(nodoTemporal != null)
			return nodoTemporal.getValorNodo();
		else
			return null;
	}
	
	
	/**
	 * Verificar si indice es valido
	 * @param indice
	 * @return
	 */
	private boolean indiceValido(int indice) {		
		if( indice>=0 && indice<tamanio ) {
			return true;
		}			
		throw new RuntimeException("�ndice no v�lido");
	}
	
	
	/**
	 * Verificar si la lista esta vacia
	 * @return
	 */
	public boolean estaVacia() {
		return(nodoPrimero == null)?true:false;
	}

	
	/**
	 * Imprime en consola la lista enlazada
	 */
	public void imprimirLista() {
		
		Nodo<T> aux = nodoPrimero;
		
		while(aux!=null) {
			System.out.print( aux.getValorNodo()+"\t" );
			aux = aux.getSiguienteNodo();			
		}
		
		System.out.println();
	}
	
	/**
	 * Eliminar ELEMENTO dado el valor de un nodo
	 * @param dato
	 * @return
	 */
	public T eliminar(T dato){
		Nodo<T> nodo = nodoPrimero;
		Nodo<T> previo = null;
		Nodo<T> siguiente = null;
		boolean encontrado = false;
		
		//buscar el nodo previo
		while(nodo!=null) {			
			if( nodo.getValorNodo() == dato ) {
				encontrado = true;
				break;
			}
			previo = nodo;
			nodo = nodo.getSiguienteNodo();
		}
		
		if(encontrado) {
			siguiente = nodo.getSiguienteNodo();
			if( previo==null ) {
				nodoPrimero = siguiente;
			}else {
				previo.setSiguienteNodo(siguiente);
			}
			
			if(siguiente==null) {
//				nodoUltimo = previo;
			}else {
				nodo.setSiguienteNodo(null);
			}
			
			nodo = null;
			tamanio--;
			return dato;			
		}
		throw new RuntimeException("El elemento no existe");
	}
	
	
	/**
	 * Elimina el primer nodo de la lista
	 * @return
	 */
	public T eliminarPrimero() {
		
		if( !estaVacia() ) {
			Nodo<T> n = nodoPrimero;
		    T valor = n.getValorNodo();
			nodoPrimero = n.getSiguienteNodo();
			
			if(nodoPrimero==null) {
//				nodoUltimo = null;
			}
			
			tamanio--;
			return valor;
		}
		
		throw new RuntimeException("Lista vac�a");		
	}

	/**
	 * OBTIENE UN NODO DADO UN INDICE
	 * @param indice
	 * @return
	 */
	private Nodo<T> obtenerNodo(int indice) {
		
		if(indice>=0 && indice<tamanio) {
		
			Nodo<T> nodo = nodoPrimero;
			
			for (int i = 0; i < indice; i++) {
				nodo = nodo.getSiguienteNodo();
			}
		
			return nodo;			
		}
		
		return null;
	}
	
	
	/**
	 * Cambia el valor de un nodo dada su posici�n en la lista
	 * @param indice posici�n donde se va a cambiar el dato
	 * @param nuevo nuevo valor por el que se actualizar� el nodo
	 */
	public void modificarNodo(int indice, T nuevo) {
		
		if( indiceValido(indice) ) {			
			Nodo<T> nodo = obtenerNodo(indice);
			nodo.setValorNodo(nuevo);			
		}
		
	}
	
	
	/**
	 * Retorna la primera posici�n donde est� guardado el dato
	 * @param dato valor a buscar dentro de la lista
	 * @return primera posici�n del dato
	 */
	public int obtenerPosicionNodo(T dato) {

		int i = 0;
		
		for( Nodo<T> aux = nodoPrimero ; aux!=null ; aux = aux.getSiguienteNodo() ) {
			if( aux.getValorNodo().equals(dato) ) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	
	@Override
	public Iterator<T> iterator() {
		
		return new IteradorListaSimple (nodoPrimero);
	}
	
	public class IteradorListaSimple implements Iterator<T>{

		private Nodo<T> nodo;
		private int posicion;
		
		/**
		 * Constructor de la clase Iterador
		 */
		public IteradorListaSimple(Nodo<T> nodo) {
			this.nodo = nodo;
			this.posicion = 0;
		}
		
		@Override
		public boolean hasNext() {
			return nodo!=null;
		}

		@Override
		public T next() {
			T valor = nodo.getValorNodo();
			nodo = nodo.getSiguienteNodo();
			posicion++;
			return valor;
		}
		
		/**
		 * Posici�n actual de la lista
		 * @return posici�n
		 */
		public int getPosicion() {
			return posicion;
		}
		
	}
	
	
	//Metodos get y set de la clase ListaSimple
	
	
	public Nodo<T> getNodoPrimero() {
		return nodoPrimero;
	}


	public void setNodoPrimero(Nodo<T> nodoPrimero) {
		this.nodoPrimero = nodoPrimero;
	}
	
	public Nodo<T> getNodoUltimo() {
		return nodoUltimo;
	}


	public void setNodoUltimo(Nodo<T> nodoUltimo) {
		this.nodoUltimo = nodoUltimo;
	}


	public int getTamanio() {
		return tamanio;
	}


	public void setTamanio(int tama) {
		this.tamanio = tama;
	}

	@Override
	public String toString() {
		String infoLista = "";
		Nodo<T> aux      = nodoPrimero;
		
		while (aux != null) {
			infoLista += aux.toString()+"\n";
			aux        = aux.getSiguienteNodo();
		}
		
		return infoLista;
	}

	/**
	 * Concatena listas
	 * @param listaA
	 * @param listaB
	 * @return
	 */
	public ListaSimple<T> concatenarListas(ListaSimple<T> listaA, ListaSimple<T> listaB) {
		ListaSimple<T> listaConcatenada = new ListaSimple<T>();
		listaConcatenada = llenarLista(listaConcatenada, listaA);
		listaConcatenada = llenarLista(listaConcatenada, listaB);
		
		return listaConcatenada;
	}

	/**
	 * llena una lista con una dada
	 * @param listaALlenar
	 * @param listaB
	 * @return
	 */
	private ListaSimple<T> llenarLista(ListaSimple<T> listaALlenar, ListaSimple<T> listaB) {
		for (T t : listaB) {
			listaALlenar.agregarfinal(t);
		}
		return listaALlenar;
	}

	/**
	 * Invierte la lista simple
	 */
	public void invertir() {
		Nodo<T> nodo = invertirLista(this.getNodoPrimero(), 0);
	}

	/**
	 * Invierte la lista enlazada simple de manera recursiva
	 * @param nodo
	 * @param i
	 * @return nodoPrimero, que seria el ultimo
	 */
	private Nodo<T> invertirLista(Nodo<T> nodo, int i) {
		if( i >= this.getTamanio()-2 ){
			nodoPrimero = nodo.getSiguienteNodo();
			nodo.setSiguienteNodo(null);
			nodoPrimero.setSiguienteNodo(nodo);
			return nodoPrimero.getSiguienteNodo();
		}
		else{
			Nodo<T> nodoAux = invertirLista(nodo.getSiguienteNodo(), i+1);
			nodo.setSiguienteNodo(null);
			nodoAux.setSiguienteNodo(nodo);
			return nodoAux.getSiguienteNodo();
		}
		
	}
	/**
	 * Inserta un Cero en la posicion donde este el valor de la suma
	 * @param nodo
	 * @param suma
	 * @return
	 */
	public int insertarCeroEnSuma(Nodo nodo, int suma){
		int sumaAux;
		if(nodo!=null){
			sumaAux = insertarCeroEnSuma(nodo.getSiguienteNodo(), suma +(int) nodo.getValorNodo());
			if(nodo.getValorNodo().equals(sumaAux)){
				nodo.setSiguienteNodo(new Nodo(0, nodo.getSiguienteNodo()));
			}
		}else{
			return sumaAux = suma;
		}
		return sumaAux;
	}
	

}
