package FileReader;
import java.io.*;
public class main {
 public static void main(String[] args) throws IOException, ClassNotFoundException {
 File fichero = new File("C:\\Users\\jpove\\Desktop\\pruebas\\texto.txt");//declara fichero
 //crea flujo de salida hacia el fichero
 Persona persona;//defino variable persona
 ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));

	int i = 1;
	try {
		while (true) { // lectura del fichero
			persona = (Persona) dataIS.readObject(); // leer una Persona
			System.out.print(i + "=>");
			i++;
			System.out.printf("Nombre: %s, edad: %d %n", persona.getNombre(),persona.getEdad());
		}
	} catch (EOFException eo) {
		System.out.println("FIN DE LECTURA.");
	} catch (StreamCorruptedException x) {
	}

	dataIS.close(); // cerrar stream de entrada
}




}