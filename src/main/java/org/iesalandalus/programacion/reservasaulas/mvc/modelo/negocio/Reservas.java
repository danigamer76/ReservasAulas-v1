package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.Arrays;
import java.util.Iterator;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {

	private int capacidad;
	private int tamano;
	Reserva coleccionReservas[];

	public Reservas(int tamano) {
		if(tamano <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.coleccionReservas = new Reserva[tamano];
	}

	public Reserva[] get() {
		return copiaProfundaReservas();

	}

	private Reserva[] copiaProfundaReservas() {
		Reserva[] copiaReserva = new Reserva[coleccionReservas.length];
		for (int i = 0; i < coleccionReservas.length; i++) {
			copiaReserva[i] = coleccionReservas[i];
		}
		return copiaReserva;
	}

	public int getTamano() {
		tamano = 0;
		for (int i = 0; i < coleccionReservas.length; i++) {
			if(coleccionReservas[i] != null) {
				tamano++;
			}
		}
		return tamano;
	}

	public int getCapacidad() {
		return coleccionReservas.length;
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException{
		if(reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}else {
			if(tamanoSuperado(getTamano()) == true) {
				throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
			}else {
				if(buscarIndice(reserva) != -1) {
					throw new OperationNotSupportedException("ERROR: Ya existe una reserva con ese nombre.");
				}else {
					coleccionReservas[getTamano()] = new Reserva(reserva);
				}
			}

		}

	}

	private int buscarIndice(Reserva reserva) {
		return Arrays.asList(coleccionReservas).indexOf(reserva);

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

	public Reserva buscar(Reserva reserva) {
		if(reserva == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una reserva nula.");
		}else {
			if(buscarIndice(reserva) == -1) {
				return null;
			}else {
				return coleccionReservas[buscarIndice(reserva)];
			}
		}


	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if(reserva == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una reserva nula.");
		}else {
			if(buscarIndice(reserva) == -1) {
				throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");
			}else {
				desplazarUnaPosicionHaciaIzquierda(buscarIndice(reserva));
			}
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < getTamano(); i++) {
			coleccionReservas[i] = coleccionReservas[i+1];
		}
		coleccionReservas[getTamano()] = null;
	}

	public String[] representar() {
		String[] representacion = new String[getTamano()];
		for (int i = 0; i < getTamano(); i++) {
			representacion[i] = coleccionReservas[i].toString();
		}
		return representacion;
	}

	public Reserva[] getReservasProfesor(Profesor profesor) {
		Reserva[] reservasProfesor = new Reserva[getCapacidad()];
		int indiceProfesor = 0;
		for (int i = 0; i < getTamano(); i++) {
			if (coleccionReservas[i].getProfesor() == profesor) {
				reservasProfesor[indiceProfesor] = coleccionReservas[i];
				indiceProfesor++;
			}
		}
		return reservasProfesor ;
	}

	public Reserva[] getReservasAula(Aula aula) {
		Reserva[] reservasAula = new Reserva[getCapacidad()];
		int indiceAula = 0;
		for (int i = 0; i < getTamano(); i++) {
			if (coleccionReservas[i].getAula() == aula) {
				reservasAula[indiceAula] = coleccionReservas[i];
				indiceAula++;
			}
		}
		return reservasAula ;
	}

	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		Reserva[] reservasPermanencia = new Reserva[getCapacidad()];
		int indicePermanencia = 0;
		for (int i = 0; i < getTamano(); i++) {
			if (coleccionReservas[i].getPermanencia() == permanencia) {
				reservasPermanencia[indicePermanencia] = coleccionReservas[i];
				indicePermanencia++;
			}
		}
		return reservasPermanencia ;
	}
	
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if(aula == null) {
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
		}
		if(permanencia == null) {
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
		}
		for (int i = 0; i < getTamano(); i++) {
			if (coleccionReservas[i].getAula() == aula) {
				if (coleccionReservas[i].getPermanencia() == permanencia) {
					return false;
				}
			}
		}
		return true;
	}
}
