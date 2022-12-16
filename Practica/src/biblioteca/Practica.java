package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Ejercicio 2
public class Practica {
	Connection conn1;
	public Practica() { //Constructor
		 // crea una conexión
		 try {
		 String url1 = "jdbc:mysql://localhost:3306/biblioteca";
		 String user = "root";
		 String password = "root";//no tiene clave
		 conn1 = DriverManager.getConnection(url1, user, password);
		 if (conn1 != null) {
		 System.out.println("Conectado a biblioteca...");
		 }
		 
		 } catch (SQLException ex) {
		 System.out.println("ERROR:dirección no válida o usuario/clave");
		 ex.printStackTrace();
		 }
	}
	
	
	public void insert(){
		 try {
		 // Crea un statement 
		 Statement sta = conn1.createStatement();
		 // Ejecuta la inserción
		 sta.executeUpdate("INSERT INTO libros " + "VALUES ('La Bella y la bestia', 20, 'AMA', 210, 2020)");
		 sta.executeUpdate("INSERT INTO socios " + "VALUES ('Juan', 'Martinez', 20, 'C/Enriqueta17', 123456789)");
		 // Cierra el statement
		 sta.close();
		 System.out.println("Realizado de forma correcta el INSERT.");
		 } catch (SQLException ex) {
		 System.out.println("ERROR:al hacer un Insert");
		 ex.printStackTrace();
		 }
		 }
	
	public void update(){
		 try {
		 // Crea un statement 
		 Statement sta = conn1.createStatement();
		 // Ejecuta la inserción
		 sta.executeUpdate("UPDATE libros SET titulo = 'La Bella y la bestia 2', n_ejemplares = '140', editorial = 'AXA', n_paginas = '250', anyo_edicion = '2021' WHERE titulo = 'Como no ser un manco'");
		 sta.executeUpdate("UPDATE socios SET nombre = 'Pepe', apellio = 'Poveda', edad = '26', direccion = 'C/Enriqueta17', telefono = '987654321' WHERE nombre = 'Fran'");
		 // Cierra el statement
		 sta.close();
		 System.out.println("Realizado de forma correcta el UPDATE.");
		 } catch (SQLException ex) {
		 System.out.println("ERROR:al hacer un UPDATE");
		 ex.printStackTrace();
		 }
		 }
	
	public void delete(){
		 try {
		 // Crea un statement 
		 Statement sta = conn1.createStatement();
		 // Ejecuta la inserción
		 sta.executeUpdate("DELETE FROM libros WHERE titulo = 'La Bella y la bestia 2'");
		 sta.executeUpdate("DELETE FROM socios WHERE titulo = 'Pepe'");
		 // Cierra el statement
		 sta.close();
		 System.out.println("Realizado de forma correcta el DELETE.");
		 } catch (SQLException ex) {
		 System.out.println("ERROR:al hacer un DELETE");
		 ex.printStackTrace();
		 }
		 }
	
	
	//Ejercicio 3
	
	public void Consulta_preparedStatement(){
		 try {
		 String query = "SELECT * FROM libros WHERE titulo like ?";
		 PreparedStatement pst = conn1.prepareStatement(query);
		 pst.setString(1, "C%");
		 ResultSet rs = pst.executeQuery();
		 while (rs.next()) {
		 System.out.println("Título - " + rs.getString("titulo") + 
		 ", n_ejemplares " + rs.getInt("n_ejemplares") + 
		 ", Editorial " + rs.getString("editorial") + 
		 ", n_páginas " + rs.getInt("n_paginas") +
		 ", Año de edición " + rs.getInt("anyo_edicion") );
		 }
		 rs.close();
		 pst.close();
		 
		 query = "SELECT * FROM socios WHERE nombre like ?";
		 pst = conn1.prepareStatement(query);
		 pst.setString(1, "Fran");
		  rs = pst.executeQuery();
		 while (rs.next()) {
		 System.out.println("Nombre - " + rs.getString("nombre") + 
		 ", apellido " + rs.getString("apellido") + 
		 ", edad " + rs.getInt("edad") + 
		 ", direccion " + rs.getString("direccion") +
		 ", teléfono " + rs.getInt("telefono") );
		 }
		 rs.close();
		 pst.close();
		 } catch (SQLException ex) {
		 System.out.println("ERROR:al consultar");
		 ex.printStackTrace();
		 }
		 }
	
	//Ejercicio 4
	public void insert4(){
		 try {
		 // Crea un statement 
		 Statement sta = conn1.createStatement();
		 // Ejecuta la inserción
		 sta.executeUpdate("INSERT INTO prestamos " + "VALUES ('Como no ser un manco', 'Fran', '2022-10-10', '2022-11-10')");
		 // Cierra el statement
		 sta.close();
		 System.out.println("Realizado de forma correcta el INSERT.");
		 } catch (SQLException ex) {
		 System.out.println("ERROR:al hacer un Insert");
		 ex.printStackTrace();
		 }
		 }
	
	//Ejercicio 5
	public void consultas(){
		 try {
		 Statement stmt = conn1.createStatement();
		 String query = "SELECT libro FROM prestamos";
		 ResultSet rs = stmt.executeQuery(query);
		 while (rs.next()) {
		 System.out.println("Título - " + rs.getString("libro"));
		 }
		 rs.close();
		 stmt.close();
		 
		 stmt = conn1.createStatement();
		 query = "SELECT COUNT(*) as num_prestamos FROM prestamos WHERE socio like 'Fran'";
		 rs = stmt.executeQuery(query);
		 while (rs.next()) {
		 System.out.println("Numero de libros prestados - " + rs.getInt("num_prestamos"));
		 }
		 rs.close();
		 stmt.close();
		 
		 stmt = conn1.createStatement();
		 query = "SELECT libro from prestamos WHERE fecha_fin > '2022-11-09'";
		 rs = stmt.executeQuery(query);
		 while (rs.next()) {
		 System.out.println("Libros con fecha final vencida - " + rs.getString("libro"));
		 }
		 rs.close();
		 stmt.close();
		 
		 stmt = conn1.createStatement();
		 query = "SELECT socio from prestamos WHERE fecha_fin > '2022-11-09'";
		 rs = stmt.executeQuery(query);
		 while (rs.next()) {
		 System.out.println("Socios con fecha final vencida - " + rs.getString("socio"));
		 }
		 rs.close();
		 stmt.close();
		 } catch (SQLException ex) {
		 System.out.println("ERROR:al hacer un Select");
		 ex.printStackTrace();
		 }
		}
		
}