package org.iesalandalus.programacion.reservasaulas;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("Programa para la gestión de reservas de espacios del IES Al-Ándalus");
		String NOMBRE_PROFESOR1 = "José Ramón";
		String NOMBRE_PROFESOR2 = "Andrés";
		String CORREO = "a@b.cc";
		String NOMBRE_AULA1 = "Salón de actos 1";
		String NOMBRE_AULA2 = "Salón de actos 2";
		String NOMBRE_AULA3 = "Salón de actos 3";
		LocalDate DIA1 = LocalDate.of(2022, 12, 1);
		LocalDate DIA2 = LocalDate.of(2022, 12, 31);
		LocalDate DIA3 = LocalDate.of(2022, 12, 22);
		
		Profesor profesor1 = new Profesor(NOMBRE_PROFESOR1, CORREO);
		Profesor profesor2 = new Profesor(NOMBRE_PROFESOR2, CORREO);
		Aula aula1 = new Aula(NOMBRE_AULA1);
		Aula aula2 = new Aula(NOMBRE_AULA2);
		Aula aula3 = new Aula(NOMBRE_AULA3);
		Permanencia permanencia1 = new Permanencia(DIA1, Tramo.MANANA);
		Permanencia permanencia2 = new Permanencia(DIA2, Tramo.MANANA);
		Permanencia permanencia3 = new Permanencia(DIA3, Tramo.MANANA);
		Reserva reserva1 = new Reserva(profesor1, aula1, permanencia1);
		Reserva reserva2 = new Reserva(profesor1, aula1, permanencia2);
		Reserva reserva3 = new Reserva(profesor1, aula2, permanencia1);
		Reserva reserva4 = new Reserva(profesor1, aula2, permanencia2);
		Reserva reserva5 = new Reserva(profesor2, aula1, permanencia1);
		
		Reservas reservas = new Reservas();
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.insertar(reserva4);
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}
