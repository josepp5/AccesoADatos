package practica;

import java.util.Map;

public class Person {

		private String dni;
		private String atrib;
		
		private Map<String , String> atributos;
		
		public Person(String dni, Map<String , String> atributos) {
			this.dni = dni;
			this.atributos = atributos;
		}
		
		public void addAtributo(String key, String value) {
			this.atributos.put(key, value);
		}
		
		public String toString() {
			String atributosString = "";
			atrib = "";
			
			this.atributos.toString();
			this.atributos.forEach((key, value) -> atrib += "[" + key + ":"+ value + "]");
			
			return "(dni: "+ dni + ";" + atributosString + "), ";
		}
}
