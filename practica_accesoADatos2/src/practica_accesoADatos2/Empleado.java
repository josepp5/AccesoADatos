package practica_accesoADatos2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Empleado {
	static Connection conexion = main.connect();
	Scanner scanner = new Scanner(System.in); 
	String dni;
	String nombre;
	
	//// Ejercicio 4 ////
	// Esta funcion hace el insert de un empleado en la Base de datos 
	public Boolean save(Empleado e) throws SQLException {
		try {
			String query = "INSERT INTO empleados VALUES('"+dni+"','"+nombre+"')";
			PreparedStatement sta = conexion.prepareStatement(query);
			sta.executeUpdate(query);
			
			sta.close();
			return true;
		} catch (SQLException ex) {
			System.out.println("ERROR:al hacer un Insert");
			 ex.printStackTrace();
			 return false;
		}
	}
	
	// Constructor que no coge parametros
	public Empleado() throws SQLException {
		this.dni = dni;
		this.nombre = nombre;
	}
	
	// Constructor al que le pasamos unicamente el campo de la primary key
	public Empleado(String dni) throws SQLException {
		this.dni = dni;
		this.nombre = nombre;
	}
	
	// Getter y Setters
	public String getDni() {
		return this.dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}
