package practica_accesoADatos2;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Gestor_clases {
	
	Connection conexion;
	Scanner scanner = new Scanner(System.in); 
	
	
	public Gestor_clases() throws SQLException {
		////// CONEXION /////////
		conexion = main.connect();
	}
	
	public void menuClases() throws SQLException {
		System.out.println("Que quieres gestionar?");
		System.out.println("1 - Introducir nuevo empleado");
		System.out.println("2 - Introducir nuevo proyecto");
		System.out.println("3 - Asignar un empleado a un proyecto");
		System.out.println("4 - Mostrar Los empleados asignados a un proyecto (ejercicio 5)");
		
		int opcion = scanner.nextInt();
		
		switch(opcion) {
		
		// Funcionamiento con clase objeto Empleado
		case 1 : {
				System.out.println("Introduce dni");
				String dni = scanner.next();
				System.out.println("Introduce nombre");
				String nombre = scanner.next();
				
				Empleado e = new Empleado();
				e.setDni(dni);
				e.setNombre(nombre);
				
				if(e.save(e)) {
					System.out.println("Empleado introducido correctamente");
					System.out.println("");
				} else {
					System.out.println("Empleado NO se ha introducido correctamente");
					System.out.println("Revisa los datos puede que el DNI introducido YA exista");
				}
				menuClases();
			}	
		
		
		// Funcionamiento con clase objeto Proyecto
		case 2 : {
			System.out.println("Introduce numero de proyecto");
			int num_proy = scanner.nextInt();
			System.out.println("Introduce nombre");
			String nombre = scanner.next();
			System.out.println("Introduce el DNI del Jefe");
			String dniJefe = scanner.next();
			LocalDate fechaInicio = LocalDate.now();
			System.out.println("Introduce la fecha final del proyecto (formato : yyyy-mm-dd)");
			System.out.println("Introduce el año (yyyy)");
			int year = scanner.nextInt();
			System.out.println("Introduce el mes (mm)");
			int month = scanner.nextInt();
			System.out.println("Introduce el dia (dd)");
			int day = scanner.nextInt();
			LocalDate fechaFin;
			try {
				fechaFin = LocalDate.of(year, month, day);
			}catch (DateTimeException ex) {
				fechaFin = null;				
			}
				
			Proyecto p = new Proyecto();
			p.setDniJefe(dniJefe);
			p.setNombre(nombre);
			p.setNum_proy(num_proy);
			p.setFechaInicio(fechaInicio);
			p.setFechaFin(fechaFin);
			if(p.save(p)) {
				System.out.println("Proyecto introducido correctamente");
				System.out.println("");
			} else {
				System.out.println("Proyecto NO se ha introducido correctamente");
				System.out.println("Revisa los datos puede que el DNI introducido no exista");
			}
			menuClases();
			}
		
		// Funcionamiento con clase objeto AsignacionEmpAProyecto
		case 3 : {
			System.out.println("Introduce dni del empleado");
			String dni = scanner.next();
			System.out.println("Introduce numero del proyecto");
			int num_proy = scanner.nextInt();
			LocalDate fechaInicio = LocalDate.now();
			System.out.println("Introduce la fecha final del proyecto (formato : yyyy-mm-dd)");
			System.out.println("Introduce el año (yyyy)");
			int year = scanner.nextInt();
			System.out.println("Introduce el mes (mm)");
			int month = scanner.nextInt();
			System.out.println("Introduce el dia (dd)");
			int day = scanner.nextInt();
			LocalDate fechaFin;
			try {
				fechaFin = LocalDate.of(year, month, day);
			}catch (DateTimeException ex) {
				fechaFin = null;				
			}
			
			AsignacionEmpAProyecto asigEmpToProy = new AsignacionEmpAProyecto();
			asigEmpToProy.setNum_proy(num_proy);
			asigEmpToProy.setDniEmp(dni);
			asigEmpToProy.setFechaInicio(fechaInicio);
			asigEmpToProy.setFechaFin(fechaFin);
			
			if(asigEmpToProy.save(asigEmpToProy)) {
				System.out.println("Proyecto asignado correctamente a empleado");
				System.out.println("");
			} else {
				System.out.println("El proyecto NO se ha asignado correctamente");
				System.out.println("Revisa los datos puede que el DNI introducido no exista");
			}
			menuClases();
			}
		
		//////////////////////////////// Ejercicio 5 //////////////////////////////////////////////////////////
		case 4 : {
			System.out.println("Introduce numero de proyecto para ver los empleados asignados en el");
			int num_proy = scanner.nextInt();
			Proyecto p = new Proyecto();
			ArrayList<String> list = p.getListAsigEmpleados(num_proy);
			System.out.println("Esta es la lista devuelta de los empleados asignados al proyecto elegido");
			System.out.println("");
			// Test del ejercicio
			for (int i = 0; i< list.size(); i++) {
				System.out.println(list.get(i));
			}
			menuClases();
			}
		
		default : menuClases();
		}
	}
}
