package practica_accesoADatos2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class practica {
	
	Connection conexion;
	Scanner scanner = new Scanner(System.in);

	public practica() { ////// Constructor CONEXION /////////
		conexion = main.connect();
	}
	
	public static void muestraErrorSQL(SQLException e) {
		System.err.println("SQL ERROR mensaje: " + e.getMessage());
		System.err.println("SQL Estado: " + e.getSQLState());
		System.err.println("SQL codigo especifico: " + e.getErrorCode());
	}

	//// Ejercicio 1  //////////
	public void Consulta(String tabla) {
		try {
			String query = "SELECT * FROM " + tabla;
			String opcion = "";
			Statement pst = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pst.executeQuery(query);
			
			int numeroFilas = 0;
			
			rs.last();
			numeroFilas = rs.getRow();
			rs.first();			
			do {
				/// Ejercicio 2 /////
				for (int i = 1; i<= rs.getMetaData().getColumnCount();i++) {
					System.out.println(rs.getMetaData().getColumnName(i) + " : " + rs.getString(i));
				} //////////////////////
				System.out.println("");
				System.out.println("Selecciona una opcion : k = siguiente,  d = anterior, . = terminar el programa, m = Volver al Menu Principal");
				opcion = scanner.nextLine();
								
				
				if (opcion.matches("[0-9]+")) {
					if(Integer.parseInt(opcion) <= numeroFilas && Integer.parseInt(opcion) > 0) {
					rs.absolute(Integer.parseInt(opcion));
					} else System.out.println("Esa fila no existe");System.out.println("");
				} else {
					switch (opcion) {
					case "k":
						if(!rs.isLast()) rs.next(); else System.out.println("No existen mas clientes");					
						break;
					case "d":
						if(!rs.isFirst()) rs.previous(); else System.out.println("Estas viendo el primer cliente");
						break;
					case "m":
						System.out.println("Estas viendo el primer cliente"); main.MenuPrincipal();
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
	
	///// Ejercicio 2 ////////
	public void PedirTabla() {
		String tabla = "";
		Boolean existe = true;
		do {
			try {
				System.out.println("");
				System.out.println("Escribe el nombre de un tabla para ver sus datos");
				tabla = scanner.nextLine();
				Statement pst = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				String query = "SELECT * FROM " + tabla;
				ResultSet rs = pst.executeQuery(query);
				existe = true;		
			
			} catch (SQLException e) {
				existe = false;
				System.out.println("La tabla no existe, prueba otra vez");
			}
		} while (!existe);		
		Consulta(tabla);
	}
}
	