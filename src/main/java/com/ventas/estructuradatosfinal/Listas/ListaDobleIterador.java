package com.ventas.estructuradatosfinal.Listas;

import java.util.Iterator;



public class ListaDobleIterador<T> implements Iterator<T> {

	private NodoDoble<T> nodo;
	private NodoDoble<T> nodoPrimero;
	private NodoDoble<T> nodoUltimo;
	private int tamanio;
	private int posicion;
	
	/**
	 * Constructor de la clase Iterador
	 * @param aux Primer Nodo de la lista
	 */
	public ListaDobleIterador(NodoDoble<T> nodo) {
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

	public boolean hasPrevious() {
		return nodo!=null;
	}


	public T previous() {
		T aux = nodo.getValorNodo();
		nodo = nodo.getAnteriorNodo();
		posicion--;
		return aux;
	}

	public int nextIndex() {
		return posicion;
	}


	public int previousIndex() {
		return posicion-1;
	}

	public void remove() {
		if(nodo!=null) {
			eliminar(nodo.getValorNodo());
		}
	}

	public void set(T e) {
		if(nodo!=null) {
			nodo.setValorNodo(e);
		}
	}

	public void add(T e) {
		agregarfinal(e);
	}


	/**
	 * Posici�n actual de la lista
	 * @return posici�n
	 */
	public int getPosicion() {
		return posicion;
	}
	
	public Iterator<T> iterator() {

		return new ListaDobleIterador (nodoPrimero);
	}
	/**
	 * ELIMINA DADO EL VALOR DE UN NODO
	 * @param dato
	 * @return
	 */
	public T eliminar(T dato) {

		NodoDoble<T> nodo = buscarNodo(dato);

		if(nodo!=null) {

			NodoDoble<T> previo = nodo.getAnteriorNodo();
			NodoDoble<T> siguiente = nodo.getSiguienteNodo();

			if(previo==null) {
				nodoPrimero = siguiente;
			}else {
				previo.setSiguienteNodo(siguiente);
			}

			if(siguiente==null) {
				nodoUltimo = previo;
			}else {
				siguiente.setAnteriorNodo(previo);
			}

			nodo=null;
			tamanio--;

			return dato;
		}

		throw new RuntimeException("El valor no existe");
	}
	
	/**
	 * AGREGA ELEMENTO AL FINAL DE LA LISTA
	 * @param valorNodo
	 */
	public void agregarfinal(T valorNodo) {

		NodoDoble<T> nuevoNodo = new NodoDoble<>(valorNodo);

		if(estaVacia())
		{
			nodoPrimero = nodoUltimo = nuevoNodo;
		}
		else
		{
			nuevoNodo.setSiguienteNodo(nodoPrimero);
			nodoPrimero.setAnteriorNodo(nuevoNodo);
			nodoPrimero = nuevoNodo;
		}
		tamanio++;
	}

	/**
	 * VERIFICA SI LA LISTA ESTA VACIA
	 * @return
	 */
		public boolean estaVacia() {
			//		return(nodoPrimero == null)?true:false;
			return nodoPrimero == null && nodoUltimo == null;
		}
	
		/**
		 * Devuelve un nodo que contenga un dato espec�fico
		 * @param dato Dato a buscar
		 * @return Nodo
		 */
		private NodoDoble<T> buscarNodo(T dato){

			NodoDoble<T> aux = nodoPrimero;

			while(aux!=null) {
				if(aux.getValorNodo().equals(dato)) {
					return aux;
				}
				aux = aux.getSiguienteNodo();
			}

			return null;
		}
		
		
}
