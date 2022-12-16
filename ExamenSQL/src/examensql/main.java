package examensql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class main {

	static Connection conexion;

	public static void main(String[] args) {
		conexion = main.connect();
		Consulta();
	}
	
	public static void Consulta() {
		try {
			String query = "SELECT * FROM clientes";
			String opcion = "";
			Scanner scanner = new Scanner(System.in);
			Statement pst = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pst.executeQuery(query);
			
			int numeroFilas = 0;
			int contador = 1;
			rs.last();
			numeroFilas = rs.getRow();
			rs.first();			
			do {
				System.out.println("Fila " + contador );
				for (int i = 1; i<= rs.getMetaData().getColumnCount();i++) {
					System.out.println(rs.getMetaData().getColumnName(i) + " : " + rs.getString(i));
				} 
				System.out.println("");
				System.out.println("Selecciona una opcion : k = siguiente,  d = anterior, . = terminar el programa");
				opcion = scanner.nextLine();
								
				
				if (opcion.matches("[0-9]+")) {
					if(Integer.parseInt(opcion) <= numeroFilas && Integer.parseInt(opcion) > 0) {
					rs.absolute(Integer.parseInt(opcion));
					} else System.out.println("Esa fila no existe");System.out.println("");
				} else {
					switch (opcion) {
					case "k":
						if(!rs.isLast()) {
							rs.next();
							System.out.println("");
							contador++;
						} else System.out.println("Estas viendo el ultimo Cliente, no existen mas clientes");					
						break;
					case "d":
						if(!rs.isFirst()) {
							rs.previous();
							contador--;
						}  else System.out.println("Estas viendo el primer cliente, no hay mas clientes en este sentido");
						break;
					case ".":
						System.out.println("Adios");
						System.exit(0);
					default:
						System.out.println(
								"Selecciona una opcion : k = siguiente,  d = anterior, . = terminar el programa");
					}
				}
			} while (opcion != ".");
			rs.close();
			pst.close();

		} catch (SQLException ex) {
			System.out.println("ERROR:al mostrar los datos");
			ex.printStackTrace();
		}
	}
	
	
	
	
	
	// Esta funcion devuelve la conexion
		public static Connection connect() {
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
			return conexion;
		}
}
