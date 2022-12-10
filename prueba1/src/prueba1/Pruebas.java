package prueba1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pruebas {
	Connection conn1;
	public Pruebas() { //Constructor
		 // creates connection
		 try {
		 String url1 = "jdbc:mysql://localhost:3306/discografica2";
		 String user = "root";
		 String password = "pepe1223";
		 conn1 = DriverManager.getConnection(url1, user, password);
		 if (conn1 != null) {
		 System.out.println("Conectado a discografica2…");
		 }
		 
		 } catch (SQLException ex) {
		 System.out.println("ERROR:dirección no válida o usuario/clave");
		 ex.printStackTrace();
		 }
	}
	
	public void Delete() {
		try {
			// Crea un statement 
			 Statement sta = conn1.createStatement();
			 // Ejecuta la inserción
			 sta.executeUpdate("DELETE from albumes where idalbumes = 3");	
			 // Cierra el statement 
			 sta.close();
			 } catch (SQLException ex) {
			 System.out.println("ERROR:al hacer un Update");
			 ex.printStackTrace();
			 }
	}
	
	public static void muestraErrorSQL(SQLException e) {
		System.err.println("SQL ERROR mensaje: " + e.getMessage());
		System.err.println("SQL Estado: " + e.getSQLState());
		System.err.println("SQL codigo especifico: " + e.getErrorCode());
	}
	
	public void transacciones() {
		try (PreparedStatement sInsert = conn1.prepareStatement("INSERT INTO " + "albumes(idalbumes,titulo,autor) VALUES (?,?,?);") ) {
			conn1.setAutoCommit(false);
			
			int i = 0;
			sInsert.setString(i++, "1");
			sInsert.setString(i++, "adios");
			sInsert.setString(i++, "ethejhe");
			sInsert.executeUpdate();
			
			i = 0;
			sInsert.setString(i++, "2");
			sInsert.setString(i++, "dices");
			sInsert.setString(i++, "djdtjdj");
			sInsert.executeUpdate();
			
			i = 0;
			sInsert.setString(i++, "3");
			sInsert.setString(i++, "martinez");
			sInsert.setString(i++, "rhsrh");
			sInsert.executeUpdate();
			
			conn1.commit();
		}catch (SQLException e) {
			muestraErrorSQL(e);
			try {
				conn1.rollback();
			} catch (Exception er) {
				System.err.println("Error haciendo ROLLBACK");
				er.printStackTrace(System.err);
			}
		}
	} 
	
	
	public void Update() {
		try {
			// Crea un statement 
			 Statement sta = conn1.createStatement();
			 // Ejecuta la inserción
			 sta.executeUpdate("UPDATE albumes set titulo = 'Fiesta' where titulo = 'Black Album'");	
			 // Cierra el statement 
			 sta.close();
			 } catch (SQLException ex) {
			 System.out.println("ERROR:al hacer un Update");
			 ex.printStackTrace();
			 }
	}
	
	public void Insertar(){
		 try {
		// Crea un statement 
		 Statement sta = conn1.createStatement();
		 // Ejecuta la inserción
		 sta.executeUpdate("INSERT INTO albumes " + "VALUES (4, 'Black Album', 'Metallica')");
		 // Cierra el statement 
		 sta.close();
		 } catch (SQLException ex) {
		 System.out.println("ERROR:al hacer un Insert");
		 ex.printStackTrace();
		 }
	}

	public void Consulta_Statement(){
		 int num = 4;
		 try {
		 Statement stmt = conn1.createStatement();
		 String query = "SELECT * FROM albumes where idalbumes="+num;
		 ResultSet rs = stmt.executeQuery(query);
		 while (rs.next()) {
		 System.out.println("ID - " + rs.getInt("idalbumes") + ", Título : " + rs.getString("titulo") + ", Autor : " + rs.getString("autor") );
		 }
		 rs.close();
		 stmt.close();
		 } catch (SQLException ex) {
		 System.out.println("ERROR:al hacer un Insert");
		 ex.printStackTrace();
		 }
	}
	
	public static void cerrar_Conexion (Connection conn1){
		 try {
		 conn1.close();
		 } catch (SQLException ex) {
		 System.out.println("ERROR:al cerrar la conexión");
		 ex.printStackTrace();
		 }
	}
}
