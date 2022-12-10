package practica;

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
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

	static List<Persona> lista = new ArrayList<Persona>();
	static Persona p;
	static String opcion = "";
	static String url = "C:\\Users\\jpove\\Desktop\\pruebas\\text.txt";
	static File file = new File(url); // Pon el directorio

	static Scanner scanner = new Scanner(System.in);
	static PrintWriter pw;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Menu();
	}

	public static void Menu() throws IOException {

		do {
			System.out.println("1 - Añadir");
			System.out.println("2 - Recuperar");
			System.out.println("3 - Modificar");

			opcion = scanner.nextLine();

			switch (opcion) {
			case "1":
				Anyadir2();
				break;

			case "2":
				Recuperar2();
				break;

			case "3":
				Modificar2();

			case ".":
				System.exit(0);
				break; // Salir del programilla
			}
		} while (opcion != ".");
	}

	public static void Anyadir() throws IOException {

		DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(file, true));

		String nombre;
		String dni;

		System.out.println("Escribe el DNI");
		dni = scanner.nextLine();

		while (Existe(dni))
			dni = scanner.nextLine();

		System.out.println("Escribe el Nombre");
		nombre = scanner.nextLine();

		dataOS.writeUTF(nombre);
		dataOS.writeUTF(dni);

		dataOS.close(); // cerrar stream

		Persona p = new Persona(nombre, dni);
		lista.add(p);
	}

	// recuperar datos del fichero
	public static void Recuperar() throws IOException {
		DataInputStream dataIS = new DataInputStream(new FileInputStream(file));

		String nombre;
		String dni;
		lista.clear();
		int i = 0;

		try {
			while (true) {
				nombre = dataIS.readUTF(); // recupera el nombre
				dni = dataIS.readUTF(); // recupera dni
				lista.add(new Persona(nombre, dni));
				System.out.println("[ " + i++ + " ] " + " Nombre : " + nombre + " - DNI : " + dni);
			}
		} catch (EOFException eo) {
		}
		dataIS.close(); // cerrar stream
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
					System.out.println("Que quieres modificar?");
					int opcion2 = scanner.nextInt();
					
					switch (opcion2) {
					
					case 1: {System.out.println("Escribe el nuevo nombre");
					dat[1] = scanner.next();}
					break;
					
					case 2: {System.out.println("Escribe el nuevo DNI");
					dat[0] = scanner.next();}
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

		fl.write(datos + "\n");
		fl.close();

	}

	public static void Recuperar2() throws IOException {

		BufferedReader fr = new BufferedReader(new FileReader(file));
		String[] dat;
		while (fr.ready() != false) {

			dat = fr.readLine().split(";");
			System.out.print(" - " + "DNI:" + dat[0] + " - " + "Nombre:" + dat[1]);
			System.out.println("\n");
		}
		fr.close();
	}

	// Codigo para modificar
	public static void Modificar() throws FileNotFoundException {
		FileOutputStream fl = new FileOutputStream(file, true);

		try {
			Recuperar();
			System.out.println("Escribe el numero identificador");
			int opcion = Integer.parseInt(scanner.nextLine());

			System.out.println("1 Nombre - " + lista.get(opcion).getNombre());
			System.out.println("2 DNI - " + lista.get(opcion).getDni());
			System.out.println("Que quieres modificar?");

			int opcion2 = Integer.parseInt(scanner.nextLine());
			if (opcion2 == 1) {
				System.out.println("Escribe el nuevo nombre");
				lista.get(opcion).setNombre(scanner.nextLine());
			} else {
				System.out.println("Escribe el nuevo DNI");
				lista.get(opcion).setDni(scanner.nextLine());
			}
			Menu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	/*
	 * /// Elimina todos los datos y vuelve a escribir el fichero desde 0 public
	 * static void ActualizarFichero() throws IOException { DataOutputStream dataOS
	 * = new DataOutputStream(new FileOutputStream(file, false));
	 * 
	 * for (int i = 0; i < lista.size(); i++) { String nombre =
	 * lista.get(i).getNombre() ; String dni = lista.get(i).getDni();
	 * dataOS.writeUTF(nombre); // recupera el nombre dataOS.writeUTF(dni); }
	 * dataOS.close(); // cerrar stream }
	 * 
	 * // Recuperar datos desde el array de personas public static void RecuperarO()
	 * { for (int i = 0; i < lista.size(); i++) { System.out.println("Nombre : " +
	 * lista.get(i).getNombre() + "| DNI : " + lista.get(i).getDni()); } }
	 */
}
