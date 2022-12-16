package examen;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class main {
	
	static List<Persona> lista = new ArrayList<Persona>();
	static Persona p;
	static String opcion = "";
	static String url = "C:\\Users\\jpove\\Desktop\\pruebas\\textExamen6.txt";
	static File file = new File(url); // Pon el directorio

	static Scanner scanner = new Scanner(System.in);
	static PrintWriter pw;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FileWriter fl = new FileWriter(file,true);
		Recuperar2();
		Menu();
	}

	public static void Menu() throws IOException {

		do {
			
			System.out.println("1 - Añadir");
			System.out.println("2 - Recuperar");
			System.out.println("3 - Modificar");
			System.out.println("4 - Borrar");

			opcion = scanner.nextLine();

			switch (opcion) {
			case "1":
				Anyadir2();
				break;

			case "2":
				RecuperarE();
				break;

			case "3":
				Modificar2();

			case "4":
				BorrarStep1();
			case ".":
				System.exit(0);
				break; // Salir del programilla
			}
		} while (opcion != ".");
	}
	
	public static void BorrarStep1() {
		try {
			BufferedReader fr = new BufferedReader(new FileReader(file));
			String[] dat;
			String linea = "";
			String lineaNueva = "";
			String todo = "";
			Recuperar2();

			while (fr.ready() != false) {
				todo += fr.readLine() + "\n";
			}
			fr.close();

			BufferedReader fr2 = new BufferedReader(new FileReader(file));

			System.out.println("Escribe el identificador del elemento que quieres borrar");
			String dni = scanner.nextLine();
			
			while (fr2.ready() != false) {
				dat = fr2.readLine().split(";");

				if (dat[0].equals(dni)) {
					
					for (int i = 0; i < 2 ; i++) {
						linea = linea + dat[i] + ";";
					}
					
					dat[0] = "*" + dat[0];
					
					for (int i = 0; i < 2 ; i++) {
						lineaNueva = lineaNueva + dat[i] + ";";
					}
				}
			}
			todo = todo.replaceAll(linea, lineaNueva);
			fr2.close();
			ActualizarFichero(todo);
			BorrarStep2();
			System.out.println("Datos borrados correctamente, puedes recuperar los datos para comprobarlo");
			Menu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Borrar datos del fichero
		public static void BorrarStep2() {
			try {
				BufferedReader fr = new BufferedReader(new FileReader(file));
				String dat;
				String linea = "";
				String lineaNueva = "";
				String todo = "";
				int ite = 0;
				List<String> list = new ArrayList<String>();

				Recuperar2();

				while (fr.ready() != false) {
					todo += fr.readLine() + "\n";
				}
				fr.close();

				BufferedReader fr2 = new BufferedReader(new FileReader(file));
				
				while (fr2.ready() != false) {
					dat = fr2.readLine();
					list.add(dat);
					if (dat.contains("*")) {
						list.remove(ite);
					}
					ite++;
				}
				todo = todo.replaceAll(linea, lineaNueva);
				fr2.close();
				ActualizarFicheroO(list);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public static void RecuperarE() throws IOException {
		try {
			BufferedReader fr = new BufferedReader(new FileReader(file));
			String[] dat;
			String linea = "";
			String lineaNueva = "";
			String todo = "";
			Boolean exist = false;

			//Recuperar2();

			while (fr.ready() != false) {
				todo += fr.readLine() + "\n";
			}
			fr.close();

			BufferedReader fr2 = new BufferedReader(new FileReader(file));

			System.out.println("Escribe el DNI identificador");
			String dni = scanner.nextLine();

			while (fr2.ready() != false) {
				dat = fr2.readLine().split(";");

				if (dat[0].equals(dni)) {
					linea = dat[0] + ";" + dat[1] + ";";
					System.out.println("Nombre - " + dat[1]);
					System.out.println("DNI - " + dat[0]);
					System.out.println("");
					exist = true;
					Menu();
					}
				}
			
			if (!exist) System.out.println("El dni introducido no existe");
			}catch (EOFException eo){
				// TODO Auto-generated catch block
				eo.printStackTrace();
			}
		}
					
	public static void Modificar2() {
		try {

			BufferedReader fr = new BufferedReader(new FileReader(file));
			String[] dat;
			String linea = "";
			String lineaNueva = "";
			String todo = "";

			Recuperar2();

			while (fr.ready() != false) {
				todo += fr.readLine() + "\n";
			}
			fr.close();

			BufferedReader fr2 = new BufferedReader(new FileReader(file));

			System.out.println("Escribe el DNI identificador");
			String dni = scanner.nextLine();

			while (fr2.ready() != false) {
				dat = fr2.readLine().split(";");

				if (dat[0].equals(dni)) {
					linea = dat[0] + ";" + dat[1] + ";";
					System.out.println("1 Nombre - " + dat[1]);
					System.out.println("2 DNI - " + dat[0]);
					System.out.println("Que quieres modificar? Escribe el numero de campo");
					int opcion2 = scanner.nextInt();
					
					switch (opcion2) {
					
					case 1: {System.out.println("Escribe el nuevo nombre");
					dat[1] = scanner.next();}
					break;
					
					case 2: {
						System.out.println("Escribe el nuevo DNI");
						String id = scanner.next();
						while(Existe(id)){
							id = scanner.next();
						}
						dat[0] = id;
					}
					break;
					}
					lineaNueva = dat[0] + ";" + dat[1] + ";";
				}
			}
			todo = todo.replaceAll(linea, lineaNueva);
			fr2.close();
			ActualizarFichero(todo);
			Menu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void ActualizarFichero(String todo) throws IOException {
		FileWriter fl = new FileWriter(file,false);
		fl.write(todo);
		fl.close();

	}

	public static void Anyadir2() throws IOException {
		FileWriter fl = new FileWriter(file, true);
		String datos = "";
		String nombre;
		String dni;

		System.out.println("Escribe el DNI");
		dni = scanner.nextLine();
		while (Existe(dni))
			dni = scanner.nextLine();

		System.out.println("Escribe el Nombre");
		nombre = scanner.nextLine();

		datos = dni + ";" + nombre + ";";
		lista.add(new Persona(dni,nombre));
		fl.write(datos + "\n");
		fl.close();

	}

	public static void Recuperar2() throws IOException {

		BufferedReader fr = new BufferedReader(new FileReader(file));
		String[] dat;
		while (fr.ready() != false) {

			dat = fr.readLine().split(";");
			System.out.print(" - " + "DNI:" + dat[0] + " - " + "Nombre:" + dat[1]);
			System.out.println("");
			lista.add(new Persona(dat[0],dat[1]));
		}
		fr.close();
	}

	/// Comprueba si el DNI introducido ya existe
	public static Boolean Existe(String dni) throws IOException {
		BufferedReader fr = new BufferedReader(new FileReader(file));
		String[] dat;
		String linea = "";
		while (fr.ready() != false) {
			dat = fr.readLine().split(";");

			if (dat[0].equals(dni)) {
				System.out.println("Este dni ya existe escribe otro");
				fr.close();
				return true;
			}
		}
		fr.close();
		return false;
	}
	
	// Actualizar fichero desde la lista de elementos
		public static void ActualizarFicheroO(List<String> l) throws IOException {
			FileWriter fl = new FileWriter(file,false);
			for (int i = 0; i < l.size(); i++) { 
				fl.write(l.get(i) + "\n");
			}
			fl.close();
		}
}
