package practica_ficheros;

import java.io.File;

public class manejo_ficheros {
	
	public void CrearFichero() {
	try {
		 // Creamos el objeto que encapsula el fichero
		 File fichero = new File("C:\\Users\\jpove\\Desktop\\curso DAM\\segundo\\Acceso a Datos\\practica ficheros\\texto.txt");
		 // A partir del objeto File creamos el fichero físicamente
		 if (fichero.createNewFile())
		 System.out.println("El fichero se ha creado correctamente");
		 else
		 System.out.println("No ha podido ser creado el fichero");
		} catch (Exception ioe) {
		 ioe.getMessage();
		}

	}
}
