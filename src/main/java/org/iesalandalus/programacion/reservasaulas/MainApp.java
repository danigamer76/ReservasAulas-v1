package org.iesalandalus.programacion.reservasaulas;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("Programa para la gestión de reservas de espacios del IES Al-Ándalus");
		final String NOMBRE_SALON1 = "Salón 1";
		final String NOMBRE_SALON2 = "Salón 2";
		final String NOMBRE_SALON3 = "Salón 3";
		final Aula salon1 = new Aula(NOMBRE_SALON1);
		final Aula salon2 = new Aula(NOMBRE_SALON2);
		final Aula salon3 = new Aula(NOMBRE_SALON3);
		System.out.println(salon1.toString());
		System.out.println(salon2.toString());
		System.out.println(salon3.toString());
		Aulas aulas1 = new Aulas();
		System.out.println(aulas1.representar());
//		Aulas aulas2 = new Aulas(aulas1);
		
	}

}
