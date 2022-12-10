package practica;

import java.io.Serializable;

public class Persona implements Serializable{
	  
	private String nombre;
	  private String dni;

	  public Persona(String nombre,String dni) {
	  this.nombre=nombre;
	  this.dni=dni;
	  }
	  
	  public Persona() {this.nombre=null;}
	  public void setNombre(String nom){nombre=nom;}
	  public void setDni(String id){dni=id;}

	  public String getNombre(){return nombre;}
	  public String getDni(){return dni;}
	  
}//fin Persona

