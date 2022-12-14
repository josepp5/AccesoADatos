
package practica_accesoADatos2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Gestor_proyectos {
	Connection conexion;
	Scanner scanner = new Scanner(System.in); 
	
	public Gestor_proyectos() throws SQLException {
		////// CONEXION /////////
		conexion = main.connect();
	}
	
	public void menuGestor() throws SQLException {
		System.out.println("Que quieres gestionar?");
		System.out.println("1 - Introducir nuevo empleado");
		System.out.println("2 - Introducir nuevo proyecto");
		System.out.println("3 - Asignar un empleado a un proyecto");
		
		int opcion = scanner.nextInt();
		
		switch(opcion) {
		
		case 1 : {
			System.out.println("Introduce dni");
			String dni = scanner.next();
			System.out.println("Introduce nombre");
			String nombre = scanner.next();
			if (nuevoEmpleado(dni,nombre) == true) {
				System.out.println("Empleado introducido correctamente");
				} else System.out.println("ERROR: El empleado NO ha podido ser introducido");
			System.out.println("");
			menuGestor();
			}
		
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
			// Resultado
			System.out.println("El numero del nuevo proyecto introducido es " + nuevoProyecto(num_proy, nombre, dniJefe, fechaInicio, fechaFin));	
			System.out.println("");
			menuGestor();
			}
		
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
			if(asignaEmpAProyecto(dni, num_proy, fechaInicio, fechaFin)) {
				System.out.println("Empleado asignado correctamente al proyecto");
			}else System.out.println("Empleado no ha podido ser asignado correctamente");
			System.out.println("");
			menuGestor();
			}
		}
	}
	
	public Boolean nuevoEmpleado(String dni, String nombre) throws SQLException {
		try {
			//String query = "INSERT INTO empleados VALUES(?,?);";
			String query = "INSERT INTO empleados VALUES('"+dni+"','"+nombre+"')";
			PreparedStatement sta = conexion.prepareStatement(query);
			//sta.setString(1, dni);
			//sta.setString(2, nombre);
			sta.executeUpdate(query);
			
			sta.close();
			return true;
		} catch (SQLException ex) {
			System.out.println("ERROR:al hacer un Insert");
			 ex.printStackTrace();
			 return false;
		}
	}
	
	public int nuevoProyecto(int num_proy, String nombre, String dniJefe, LocalDate fechaInicio, LocalDate fechaFin) throws SQLException {
		try {
			String query = "INSERT INTO proyectos VALUES ('"+num_proy+"','"+nombre+"','"+dniJefe+"','"+fechaInicio+"','"+fechaFin+"');";
			PreparedStatement sta = conexion.prepareStatement(query);
			sta.executeUpdate(query);
			
			sta.close();
		} catch (SQLException ex) {
			 ex.printStackTrace();
			 System.out.println("ERROR: El proyecto NO ha podido ser introducido");
			 menuGestor();
		}
		return num_proy;
	}
	
	public Boolean asignaEmpAProyecto(String dniEmp, int num_proy, LocalDate fechaInicio, LocalDate fechaFin) throws SQLException {
		try {
			String query = "INSERT INTO asig_proyectos VALUES('"+dniEmp+"','"+num_proy+"','"+fechaInicio+"','"+fechaFin+"')";
			PreparedStatement sta = conexion.prepareStatement(query);
			/*sta.setString(1, dniEmp);
			sta.setString(2, num_proy);
			sta.setDate(3, fechaInicio);
			sta.setDate(4, fechaFin);*/
			sta.executeUpdate(query);
			
			sta.close();
			return true;
		} catch (SQLException ex) {
			 ex.printStackTrace();
			 System.out.println("ERROR: El empleado NO ha podido ser asignado al proyecto");
			 menuGestor();
			 return false;
		}
	}
	
}
