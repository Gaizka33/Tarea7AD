package ej1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CreacionDeAlumnos {
	private static ArrayList<Alumno> studentlist;
	private static Scanner abielto = new Scanner(System.in);
	private final static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public static ArrayList<Alumno> create5students() {
		for (int i = 0; i < 5; i++) {
			studentlist.add(createStudent());
		}
		return studentlist;
	}

	public static Alumno createStudent() {

		System.out.println("Dame la fecha de nacimiento del alumno (dd-MM-yyyy): ");
		String fechaNacimientoString = abielto.next();
		LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoString, format);

		System.out.println("Dame los datos del alumno (Nombre, Apellidos, Ciclo, Curso, Grupo, NIA y Genero): ");
		String nombre = abielto.next();
		String apellidos = abielto.next();
		String ciclo = abielto.next();
		String curso = abielto.next();
		String grupo = abielto.next();
		int nia = abielto.nextInt();
		char genero = abielto.next().charAt(0);

		Alumno alumno = new Alumno(nombre, apellidos, ciclo, curso, grupo, nia, genero, fechaNacimiento);
		return alumno;

	}
}
