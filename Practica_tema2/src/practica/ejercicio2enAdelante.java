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

public class ejercicio2enAdelante {

	static ArrayList<String> lista = new ArrayList<String>();
	static String opcion = "";
	static int numAtrib = 0;
	static String url = "C:\\Users\\jpove\\Desktop\\pruebas\\textE2.txt"; // Pon el directorio
	static File file = new File(url);
	static Scanner scanner = new Scanner(System.in);
	static PrintWriter pw;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (file.exists()) {
			FileWriter fl = new FileWriter(file,false);
		}
		Estructura();
		Menu();
	}
	
	public static void Estructura() {
		System.out.println(" - El primer atributo sera el identificador del elemento, podria ser por ejemplo : id, dni, etc.. (no se puede repetir) ");
		System.out.println(" - Los elementos se mostraran con un * en su identificador cuando esten marcados como borrados");
		System.out.println("Cuantos atributos va a tener este elemento?");
		numAtrib = Integer.parseInt(scanner.nextLine());

		CrearAtributos(numAtrib);
	}

	public static void Menu() throws IOException {

		do {
			System.out.println("1 - Añadir");
			System.out.println("2 - Recuperar");
			System.out.println("3 - Modificar");
			System.out.println("4 - PreBorrar (paso 1, ejercicio 4)");
			System.out.println("5 - Borrar (paso 2, ejercicio 5)");

			opcion = scanner.nextLine();

			switch (opcion) {
			case "1":
				Anyadir();break;

			case "2":
				Recuperar();break;

			case "3":
				Modificar();break;
				
			case "4":
				BorrarStep1();break;
				
			case "5":
				BorrarStep2();break;

			case ".":
				System.exit(0);
				break; // Salir del programilla
			}	
		} while (opcion != ".");
	}
	
	// Ejercicio 4 (elimina de el fichero los elementos que esten marcados como borrados)
	public static void BorrarStep2() {
		try {
			BufferedReader fr = new BufferedReader(new FileReader(file));
			String dat;
			String linea = "";
			String lineaNueva = "";
			String todo = "";
			int ite = 0;
			List<String> list = new ArrayList<String>();

			Recuperar();

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
			Menu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Ejercicio 3 (marca el elemento seleccionado como borrado)
	public static void BorrarStep1() {
		try {
			BufferedReader fr = new BufferedReader(new FileReader(file));
			String[] dat;
			String linea = "";
			String lineaNueva = "";
			String todo = "";

			Recuperar();

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
					for (int i = 0; i < numAtrib ; i++) {
						linea = linea + dat[i] + ";";
						System.out.println(lista.get(i) + " : " + dat[i]);
					}
					
					dat[0] = "*" + dat[0];
					
					for (int i = 0; i < numAtrib ; i++) {
						lineaNueva = lineaNueva + dat[i] + ";";
					}
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
	
	// Estructura y codigo para crear los atributos que tendra el elemento
	public static void CrearAtributos(int num) {
		int a = 1;
		for (int i = 0; i < num; i++) {	
			System.out.println("Escribe el atributo " + a++);
			opcion = scanner.nextLine();
			
			lista.add(opcion);
			}
	}
	
	// Anyade un elemento
	public static void Anyadir() throws IOException {
		FileWriter fl = new FileWriter(file,true);
		String id;
		String datos = "";
		for (int i = 0; i < numAtrib; i++) {
			if (i == 0) {
				System.out.println("Introduce " + lista.get(i));
				opcion = scanner.nextLine();
				while (Existe(opcion))
					opcion = scanner.nextLine();	
			} else {
				System.out.println("Introduce " + lista.get(i));
				opcion = scanner.nextLine();
			}
			datos = datos + opcion + ";";
			}
		fl.write(datos + "\n");
		fl.close();
		
	}

	// Recuperar datos del fichero
	public static void Recuperar() throws IOException {
		
		BufferedReader fr = new BufferedReader(new FileReader(file));
		String[] dat;
		String linea = "";
		while ((linea = fr.readLine()) != null) {
			dat = linea.split(";");
			for (int i = 0; i < numAtrib ; i++) {
				System.out.print(lista.get(i) + ":" + dat[i] + " - ");
			}
			System.out.print("\n");
		}		
	}

	// Codigo para modificar 
	public static void Modificar() {
		try {

			BufferedReader fr = new BufferedReader(new FileReader(file));
			String[] dat;
			String linea = "";
			String lineaNueva = "";
			String todo = "";

			Recuperar();

			while (fr.ready() != false) {
				todo += fr.readLine() + "\n";
			}
			fr.close();

			BufferedReader fr2 = new BufferedReader(new FileReader(file));

			System.out.println("Escribe el identificador");
			String dni = scanner.nextLine();
			int ite = 0;
			
			while (fr2.ready() != false) {
				dat = fr2.readLine().split(";");

				if (dat[0].equals(dni)) {
					for (int i = 0; i < numAtrib ; i++) {
						linea = linea + dat[i] + ";";
						System.out.println(lista.get(i) + " : " + dat[i]);
					}
					System.out.println("Que quieres modificar? (Escribe el nombre del atributo)");
					String opcion2 = scanner.next();
					
					for (int i = 0; i < numAtrib ; i++) {
						if (opcion2.equals(lista.get(i))) {
							ite = i;
						}
					}
					if (ite == 0) {
						String id;
						System.out.println("Escribe el nuevo " + lista.get(ite));
						dat[ite] = scanner.next();
						while (Existe(dat[ite]))
							dat[ite] = scanner.next();	
					} else {
					System.out.println("Escribe el nuevo " + lista.get(ite));
					dat[ite] = scanner.next();
					}
					
					for (int i = 0; i < numAtrib ; i++) {
						lineaNueva = lineaNueva + dat[i] + ";";
					}
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
	
	// Actualizar fichero desde la lista de elementos
	public static void ActualizarFicheroO(List<String> l) throws IOException {
		FileWriter fl = new FileWriter(file,false);
		for (int i = 0; i < l.size(); i++) { 
			fl.write(l.get(i) + "\n");
		}
		fl.close();
	}
	
	// Actualizar el fichero despues de modificar
	public static void ActualizarFichero(String todo) throws IOException {
		FileWriter fl = new FileWriter(file,false);
		fl.write(todo);
		fl.close();
	}
	
	// Compureba si el identificador ya existe
	public static Boolean Existe(String id) throws IOException {
		BufferedReader fr = new BufferedReader(new FileReader(file));
		String[] dat;
		while (fr.ready() != false) {
			dat = fr.readLine().split(";");

			if (dat[0].equals(id)) {
				System.out.println("Este identificador ya existe escribe otro");
				fr.close();
				return true;
			}
		}
		fr.close();
		return false;
	}
}
