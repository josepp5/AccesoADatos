package practica_accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class ejercicio2 {
	Connection conexion;
	public ejercicio2() { ////// Constructor CONEXION /////////
		 
		 try {
		 String url1 = "jdbc:mysql://localhost:3306/practica_accesoadatos";
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
	
	public static void muestraErrorSQL(SQLException e) {
		System.err.println("SQL ERROR mensaje: " + e.getMessage());
		System.err.println("SQL Estado: " + e.getSQLState());
		System.err.println("SQL codigo especifico: " + e.getErrorCode());
	}
	
	
	////// EJERCICIO 2 ////////////
	public void InsertarLibro() {
		try {
					
			Statement sta = conexion.createStatement();
			
			sta.executeUpdate("INSERT INTO libros " + "VALUES ( 1 , 'Caperucita roja', 24, 'Manzanita', 105, 2010)");
			sta.executeUpdate("INSERT INTO libros " + "VALUES ( 2 , 'Robin Hood', 54, 'Santillana', 206, 1994)");
			
			sta.close();
		} catch (SQLException ex) {
			System.out.println("ERROR:al hacer un Insert");
			 ex.printStackTrace();
		}
	}
	
	public void InsertarSocio() {
		try {
					
			Statement sta = conexion.createStatement();
			
			sta.executeUpdate("INSERT INTO socios " + "VALUES ( 1 , 'Franco', 'Ruiz', 23, 'av/ falsa 123', 664533478)");
			sta.executeUpdate("INSERT INTO socios " + "VALUES ( 2 , 'Martin', 'Martinez', 48, 'calle falsa 345', 675543678)");
			
			sta.close();
		} catch (SQLException ex) {
			System.out.println("ERROR:al hacer un Insert");
			 ex.printStackTrace();
		}
	}
	
	public void DeleteLibro() {
		try {
			// Crea un statement 
			 Statement sta = conexion.createStatement();
			 // Ejecuta la inserción
			 sta.executeUpdate("DELETE from libros where idlibros = 1");	
			 // Cierra el statement 
			 sta.close();
			 } catch (SQLException ex) {
			 System.out.println("ERROR:al hacer un Update");
			 ex.printStackTrace();
			 }
	}
	
	public void DeleteSocio() {
		try {
			// Crea un statement 
			 Statement sta = conexion.createStatement();
			 // Ejecuta la inserción
			 sta.executeUpdate("DELETE from socios where idsocios = 1");	
			 // Cierra el statement 
			 sta.close();
			 } catch (SQLException ex) {
			 System.out.println("ERROR:al hacer un Delete de Socios");
			 ex.printStackTrace();
			 }
	}
	
	public void UpdateLibro() {
		try {
			// Crea un statement 
			 Statement sta = conexion.createStatement();
			 // Ejecuta la inserción
			 sta.executeUpdate("UPDATE libros set titulo = 'Blancanieves' where titulo = 'Caperucita roja'");	
			 // Cierra el statement 
			 sta.close();
			 } catch (SQLException ex) {
			 System.out.println("ERROR:al hacer un Update");
			 ex.printStackTrace();
			 }
	}
	
	public void UpdateSocio() {
		try {
			// Crea un statement 
			 Statement sta = conexion.createStatement();
			 // Ejecuta la inserción
			 sta.executeUpdate("UPDATE socios set nombre = 'Manuel' where nombre = 'Martin'");	
			 // Cierra el statement 
			 sta.close();
			 } catch (SQLException ex) {
			 System.out.println("ERROR:al hacer un Update de un Socio");
			 ex.printStackTrace();
			 }
	}
	
	////// EJERCICIO 3 /////////////////////
	
	public void Consulta_Libro_titulo(){
		 try {
		 String query = "SELECT * FROM libros WHERE titulo like ?";
		 PreparedStatement pst = conexion.prepareStatement(query); 
		 pst.setString(1,"B%");
		 ResultSet rs = pst.executeQuery();
		 while (rs.next()) {
		 System.out.println("ID - " + rs.getInt("idlibros") + ", Título : " + rs.getString("titulo") + ", Numero de Ejemplares : " + rs.getString("num_ejemplares") + ", Editorial : " + rs.getString("editorial") + "Numero de Paginas : " + rs.getString("num_paginas") );
		 }
		 rs.close();
		 pst.close();
		 } catch (SQLException ex) {
		 System.out.println("ERROR:al mostrar los datos");
		 ex.printStackTrace();
		 }
	}
	
	public void Consulta_Socio_nombre(){
		 try {
		 String query = "SELECT * FROM socios WHERE nombre like ?";
		 PreparedStatement pst = conexion.prepareStatement(query); 
		 pst.setString(1,"M%");
		 ResultSet rs = pst.executeQuery();
		 while (rs.next()) {
		 System.out.println("ID - " + rs.getInt("idsocios") + ", Nombre : " + rs.getString("nombre") + ", Apellidos : " + rs.getString("apellidos") + ", Edad : " + rs.getString("edad") + "Direccion : " + rs.getString("direccion") + "Telefono : " + rs.getString("telefono") );
		 }
		 rs.close();
		 pst.close();
		 } catch (SQLException ex) {
		 System.out.println("ERROR:al mostrar los datos");
		 ex.printStackTrace();
		 }
	}
	
	/////// EJERCICIO 4 /////////////////////
	
	
	public void NuevoPrestamo() {
		try {
			
			Statement sta = conexion.createStatement();
			
			sta.executeUpdate("INSERT INTO prestamos " + "VALUES ( 11111 , 1, 2, '2010-10-10', '2010-11-10')");
			sta.executeUpdate("INSERT INTO prestamos " + "VALUES ( 22222 , 2, 1, '2011-10-12', '2012-10-12')");
			
			sta.close();
		} catch (SQLException ex) {
			System.out.println("ERROR:al hacer insertar un Prestamo");
			 ex.printStackTrace();
		}
	}
	
	public void DeletePrestamo() {
		try {
			// Crea un statement 
			 Statement sta = conexion.createStatement();
			 // Ejecuta la inserción
			 sta.executeUpdate("DELETE from prestamos WHERE idprestamos = 22222");	
			 // Cierra el statement 
			 sta.close();
			 } catch (SQLException ex) {
			 System.out.println("ERROR:al hacer un Delete de Prestamos");
			 ex.printStackTrace();
			 }
	}
	
	/// Ejercicio 5 ///////////////
	
	public void LibrosPrestados() {
		try {
			 String query = "SELECT * FROM prestamos";
			 PreparedStatement pst = conexion.prepareStatement(query); 
			 ResultSet rs = pst.executeQuery();
			 while (rs.next()) {
			 System.out.println("ID - " + rs.getInt("idprestamos") + ", Libro : " + rs.getString("libro") + ", Socio : " + rs.getString("socio") + ", Fecha Inicio : " + rs.getString("fecha_inicio") + "Fecha Fin : " + rs.getString("fecha_fin") );
			 }
			 rs.close();
			 pst.close();
			 } catch (SQLException ex) {
			 System.out.println("ERROR:al mostrar los datos");
			 ex.printStackTrace();
			 }
	}
	
	public void LibrosPrestadosSocio() {
		try {
			 String query = "SELECT count(*) as total_prestamos FROM prestamos where socio like 2";
			 PreparedStatement pst = conexion.prepareStatement(query); 
			 ResultSet rs = pst.executeQuery();
			 while (rs.next()) {
			 System.out.println("El socio tiene " +  rs.getInt("total_prestamos") + " prestamo/s" );
			 }
			 rs.close();
			 pst.close();
			 } catch (SQLException ex) {
			 System.out.println("ERROR:al mostrar los datos");
			 ex.printStackTrace();
			 }
	}
	
	public void FechaSuperadaLibro() {
		try {
			 String query = "SELECT * FROM prestamos where fecha_fin > 2022-10-18";
			 PreparedStatement pst = conexion.prepareStatement(query); 
			 ResultSet rs = pst.executeQuery();
			 System.out.println("Los ID de libros prestados pasados de fecha son :");
			 while (rs.next()) {
			 System.out.println(" " + rs.getInt("libro"));
			 }
			 rs.close();
			 pst.close();
			 } catch (SQLException ex) {
			 System.out.println("ERROR:al mostrar los datos");
			 ex.printStackTrace();
			 }
	}
	
	public void FechaSuperadaSocio() {
		try {
			 String query = "SELECT * FROM prestamos where fecha_fin > 2022-10-18";
			 PreparedStatement pst = conexion.prepareStatement(query); 
			 ResultSet rs = pst.executeQuery();
			 System.out.println("Los ID de Socios con prestamos pasados de fecha son :");
			 while (rs.next()) {
			 System.out.println(" " + rs.getInt("socio"));
			 }
			 rs.close();
			 pst.close();
			 } catch (SQLException ex) {
			 System.out.println("ERROR:al mostrar los datos");
			 ex.printStackTrace();
			 }
	}
	
	///// FIN EJERCICIOS PAG 67 ////////
	
	
	
	/*
	public void Menu() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("1 - Dar de Alta Cliente o Socio");
		System.out.println("2 - Dar de Baja Cliente o Socio");
		System.out.println("3 - Dar de Alta Socio");
		System.out.println("4 - Dar de Baja Socio");
		System.out.println("5 - Modificar Cliente");
		System.out.println("6 - Modificar Socio");
		System.out.println("7 - Consultar Cliente");
		System.out.println("8 - Consultar Socios");
		
		String opcion = scanner.nextLine(); 
		
		
	}
	
	
	public void InsertarLibros() {
		try (PreparedStatement sInsert = conexion.prepareStatement("INSERT INTO " + "libros( idlibros , titulo, num_ejemplares, editorial, num_paginas, anyo_edicion) VALUES (?,?,?,?,?,?);")) {
			conexion.setAutoCommit(false);
			
			int i = 0;
			sInsert.setString(i++, "1");
			sInsert.setString(i++, "Caperucita roja");
			sInsert.setString(i++, "24");
			sInsert.setString(i++, "Manzanita");
			sInsert.setString(i++, "105");
			sInsert.setString(i++, "1994");
			sInsert.executeUpdate();
			
			i = 0;
			sInsert.setString(i++, "2");
			sInsert.setString(i++, "Robin Hood");
			sInsert.setString(i++, "35");
			sInsert.setString(i++, "Santillana");
			sInsert.setString(i++, "259");
			sInsert.setString(i++, "1998");
			sInsert.executeUpdate();
			
			i = 0;
			sInsert.setString(i++, "3");
			sInsert.setString(i++, "adios");
			sInsert.setString(i++, "28");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "208");
			sInsert.setString(i++, "2001");
			sInsert.executeUpdate();
			
			
			
			conexion.commit();
		}catch (SQLException e) {
			muestraErrorSQL(e);
			try {
				conexion.rollback();
			} catch (Exception er) {
				System.err.println("Error haciendo ROLLBACK");
				er.printStackTrace(System.err);
			}
		}
	} 
	
	public void InsertarSocios() {
		try (PreparedStatement sInsert = conexion.prepareStatement("INSERT INTO " + "socios( idSocios , titulo, numEjemplares, editorial, num_paginas, anyo_edicion) VALUES (?,?,?,?,?,?);") ) {
			conexion.setAutoCommit(false);
			
			int i = 0;
			sInsert.setString(i++, "1");
			sInsert.setString(i++, "Caperucita roja");
			sInsert.setString(i++, "24");
			sInsert.setString(i++, "Manzanita");
			sInsert.setString(i++, "105");
			sInsert.setString(i++, "10/10/2010");
			sInsert.executeUpdate();
			
			i = 0;
			sInsert.setString(i++, "2");
			sInsert.setString(i++, "Robin Hood");
			sInsert.setString(i++, "35");
			sInsert.setString(i++, "Santillana");
			sInsert.setString(i++, "259");
			sInsert.setString(i++, "12/05/1998");
			sInsert.executeUpdate();
			
			i = 0;
			sInsert.setString(i++, "3");
			sInsert.setString(i++, "adios");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "ethejhe");
			sInsert.executeUpdate();
			
			i = 0;
			sInsert.setString(i++, "4");
			sInsert.setString(i++, "adios");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "ethejhe");
			sInsert.executeUpdate();
			
			i = 0;
			sInsert.setString(i++, "5");
			sInsert.setString(i++, "adios");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "ethejhe");
			sInsert.executeUpdate();
			
			i = 0;
			sInsert.setString(i++, "6");
			sInsert.setString(i++, "adios");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "ethejhe");
			sInsert.setString(i++, "ethejhe");
			sInsert.executeUpdate();
			
			conexion.commit();
		}catch (SQLException e) {
			muestraErrorSQL(e);
			try {
				conexion.rollback();
			} catch (Exception er) {
				System.err.println("Error haciendo ROLLBACK");
				er.printStackTrace(System.err);
			}
		}
	} 
	*/
	
}