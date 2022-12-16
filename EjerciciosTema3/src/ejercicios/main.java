package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class main {
	public static Connection conexion;
	public static void main(String[] args) {
		
		connect();
		//Datos de muestra dados en la pag 23
		//if (InsertDatosMuestra())System.out.println("Datos introducidos correctamente") ;
		
		// Ejercicio A pagina 24
		//if (EjercicioApag24())System.out.println("Datos Modificados y Eliminados correctamente") ;
		
		// Ejercicio A pagina 28
		if (EjercicioApag28())System.out.println("si ves esto es que se puede recoger los datos con getInt()") ;
		// Esta funcion devuelve TRUE porlotanto si se puede recoger el tipo CHAR con getInt()
		
	}
	
	public static Boolean EjercicioApag28() {
		try {
			String query = "SELECT * FROM clientes";
			Statement pst = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pst.executeQuery(query);
			
			
			rs.first();		
			
				for (int i = 1; i<= rs.getMetaData().getColumnCount();i++) {
					if (i == 3) {
						System.out.println(rs.getMetaData().getColumnName(i) + " : " + rs.getInt(i));
					} else System.out.println(rs.getMetaData().getColumnName(i) + " : " + rs.getString(i));
				} 
			
			rs.close();
			pst.close();
			return true;

		} catch (SQLException ex) {
			System.out.println("ERROR:al mostrar los datos");
			ex.printStackTrace();
			return false;
		}
			
	}
	
	public static Boolean EjercicioApag24() {
		try {
			// Modifico el cliente
			String query = "UPDATE clientes SET apellidos = \"ROJAS\" WHERE dni = \"89012345E\";";
			PreparedStatement sta = conexion.prepareStatement(query);
			sta.executeUpdate(query);
			sta.close();
			// Elimino otro cliente
			query = "Delete from clientes WHERE dni = \"56789013G\";";
			sta = conexion.prepareStatement(query);
			sta.executeUpdate(query);
			sta.close();
			
			return true;
		} catch (SQLException ex) {
			 ex.printStackTrace();
			 System.out.println("ERROR");
			 return false;
		}
	}
	
	public static Boolean InsertDatosMuestra() {
		try {
			String query = "Insert into clientes (dni,apellidos,cp) VALUES  (\"78901234X\", \"NADALES\", \"44126\"),\r\n"
					+ "(\"89012345E\", \"HOJAS\", null),\r\n"
					+ "(\"56789012B\", \"SAMPER\",\"29730\"),\r\n"
					+ "(\"56789013G\",\"LAMIQUIZ\",null);";
			PreparedStatement sta = conexion.prepareStatement(query);
			sta.executeUpdate(query);
			sta.close();
			return true;
		} catch (SQLException ex) {
			 ex.printStackTrace();
			 System.out.println("ERROR: El proyecto NO ha podido ser introducido");
			 return false;
		}
	}
	
	// Esta funcion devuelve la conexion
		public static Connection connect() {
			try {
				String url1 = "jdbc:mysql://localhost:3306/ejerciciostema3";
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
			return conexion;
		}

}
