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
	
	public Empleado() throws SQLException {
		this.dni = dni;
		this.nombre = nombre;
	}
	
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
