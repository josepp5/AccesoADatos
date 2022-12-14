package practica_accesoADatos2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class main {
	
	static Connection conexion;
	public static void main(String[] args) {
		MenuPrincipal();
	}
	
	public static void MenuPrincipal() {
		/////// Ejercicios 1 y 2 /////////
		practica prac = new practica();
		/////// Ejercicio 3 //////////////
		Gestor_proyectos gp = null;
		try {
			gp = new Gestor_proyectos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			////// Ejercicio 4 ////////////////
		Gestor_clases gc = null;
		try {
			gc = new Gestor_clases();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Elige una opcion");
		System.out.println("1 - Navegar en clientes (ejercicio 1)");
		System.out.println("2 - Elegir la tabla en la que navegar (ejercicio 2)");
		System.out.println("3 - Gestor Proyectos (ejercicio 3)");
		System.out.println("4 - Trabajando con Clases y (ejercicio 4 y 5)");
		
		
		int opcion = scanner.nextInt();
		
		switch(opcion) {
			case 1 : prac.Consulta("clientes"); ///// ejercicio 1
			case 2 : prac.PedirTabla();  /////////// ejercicio 2
			case 3 : try {
				gp.menuGestor();    ///// Ejercicio 3
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			case 4 : try {
				gc.menuClases();   //// Ejercicio 4
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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