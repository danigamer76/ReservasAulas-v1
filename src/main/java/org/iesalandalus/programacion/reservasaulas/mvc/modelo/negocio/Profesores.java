package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.Arrays;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {

	private int capacidad;
	private int tamano;
	Profesor coleccionProfesores[];

	public Profesores(int tamano) {
		if(tamano <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.coleccionProfesores = new Profesor[tamano];
	}

	public Profesor[] get() {
		return copiaProfundaProfesores();

	}

	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copiaProfesor = new Profesor[coleccionProfesores.length];
		for (int i = 0; i < coleccionProfesores.length; i++) {
			copiaProfesor[i] = coleccionProfesores[i];
		}
		return copiaProfesor;
	}

	public int getTamano() {
		tamano = 0;
		for (int i = 0; i < coleccionProfesores.length; i++) {
			if(coleccionProfesores[i] != null) {
				tamano++;
			}
		}
		return tamano;
	}

	public int getCapacidad() {
		return coleccionProfesores.length;
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException{
		if(profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}else {
			if(tamanoSuperado(getTamano()) == true) {
				throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
			}else {
				if(buscarIndice(profesor) != -1) {
					throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");
				}else {
					coleccionProfesores[getTamano()] = new Profesor(profesor);
				}
			}

		}

	}

	private int buscarIndice(Profesor profesor) {
		return Arrays.asList(coleccionProfesores).indexOf(profesor);

	}

	private boolean tamanoSuperado(int tamano) {
		if(tamano == getCapacidad()) {
			return true;
		}else {
			return false;
		}

	}

	private boolean capacidadSuperada(int capacidad) {
		if(capacidad > this.capacidad) {
			return true;
		}else {
			return false;
		}

	}

	public Profesor buscar(Profesor profesor) {
		if(profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un profesor nulo.");
		}else {
			if(buscarIndice(profesor) == -1) {
				return null;
			}else {
				return coleccionProfesores[buscarIndice(profesor)];
			}
		}


	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if(profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un profesor nulo.");
		}else {
			if(buscarIndice(profesor) == -1) {
				throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
			}else {
				desplazarUnaPosicionHaciaIzquierda(buscarIndice(profesor));
			}
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < getTamano(); i++) {
			coleccionProfesores[i] = coleccionProfesores[i+1];
		}
		coleccionProfesores[getTamano()] = null;
	}

	public String[] representar() {
		String[] representacion = new String[getTamano()];
		for (int i = 0; i < getTamano(); i++) {
			representacion[i] = coleccionProfesores[i].toString();
		}
		return representacion;
	}
}
