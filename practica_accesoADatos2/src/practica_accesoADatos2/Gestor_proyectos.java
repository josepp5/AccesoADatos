package practica_accesoADatos2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Gestor_proyectos {
	Connection conexion;
	Scanner scanner = new Scanner(System.in); 
	
	public Gestor_proyectos() {
		////// Constructor CONEXION /////////
			try {
				String url1 = "jdbc:mysql://localhost:3306/practica_accesoadatos2";
				String user = "root";
				String password = "pepe1223";
				conexion = DriverManager.getConnection(url1, user, password);
				if (conexion != null) {
					System.out.println("Conectado a practica_accesoadatos…");
				}

			} catch (SQLException ex) {
				System.out.println("ERROR:dirección no válida o usuario/clave");
				ex.printStackTrace();
			}
	}
	
	public void menuGestor() {
		System.out.println("Que quieres gestionar?");
		System.out.println("1 - Introducir nuevo empleado");
		System.out.println("2 - Introducir nueco proyecto");
		
		int opcion = scanner.nextInt();
		
		switch(opcion) {
		
		case 1 : {
			System.out.println("Introduce dni");
			String dni = scanner.next();
			System.out.println("Introduce nombre");
			String nombre = scanner.next();
			nuevoEmpleado(dni,nombre);
			}
		case 2 : {
			System.out.println("Introduce numero de proyecto");
			String num_proy = scanner.next();
			System.out.println("Introduce nombre");
			String nombre = scanner.next();
			System.out.println("Introduce el DNI del Jefe");
			String dniJefe = scanner.next();
			LocalDate fechaInicio = LocalDate.now();
			LocalDate fechaFin = null;
			//nuevoProyecto(num_proy, nombre, dniJefe, fechaInicio, fechaFin);
			}
		}
	}
	
	public Boolean nuevoEmpleado(String dni, String nombre) {
		try {
			String query = "INSERT INTO empleados" + " VALUES(?,?)";
			PreparedStatement sta = conexion.prepareStatement(query);
			sta.setString(1, dni);
			sta.setString(2, nombre);
			sta.executeUpdate(query);
			
			sta.close();
			return true;
		} catch (SQLException ex) {
			System.out.println("ERROR:al hacer un Insert");
			 ex.printStackTrace();
			 return false;
		}
	}
	
	public String nuevoProyecto(String num_proy, String nombre, String dniJefe, Date fechaInicio, Date fechaFin) {
		try {
			String query = "INSERT INTO proyectos VALUES (?,?,?,?,?)";
			PreparedStatement sta = conexion.prepareStatement(query);
			sta.setString(1, num_proy);
			sta.setString(2, nombre);
			sta.setString(3, dniJefe);
			sta.setDate(4, fechaInicio);
			sta.setDate(5, fechaFin);
			sta.executeUpdate(query);
			
			sta.close();
		} catch (SQLException ex) {
			System.out.println("ERROR:al hacer un Insert");
			 ex.printStackTrace();
		}
		return num_proy;
	}
	
	public void asignaEmpAProyecto(String dniEmp, String num_proy, Date fechaInicio, Date fechaFin) {
		try {
			String query = "INSERT INTO asig_proyecto VALUES(?,?,?,?)";
			PreparedStatement sta = conexion.prepareStatement(query);
			sta.setString(1, dniEmp);
			sta.setString(2, num_proy);
			sta.setDate(3, fechaInicio);
			sta.setDate(4, fechaFin);
			sta.executeUpdate(query);
			
			sta.close();
		} catch (SQLException ex) {
			System.out.println("ERROR:al hacer un Insert");
			 ex.printStackTrace();
		}
	}
	
}
