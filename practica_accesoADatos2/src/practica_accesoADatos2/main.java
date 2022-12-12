package practica_accesoADatos2;

import java.sql.SQLException;
import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		
		practica prac = new practica();
		Gestor_proyectos gp = null;
		try {
			gp = new Gestor_proyectos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Elige una opcion");
		System.out.println("1 - Navegar en clientes (ejercicio 1)");
		System.out.println("2 - Elegir la tabla en la que navegar (ejercicio 2)");
		System.out.println("3 - Gestor Proyectos (ejercicio 3)");
		
		int opcion = scanner.nextInt();
		
		switch(opcion) {
			case 1 : prac.Consulta("clientes");
			case 2 : prac.PedirTabla();
			case 3 : try {
				gp.menuGestor();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
	}
}