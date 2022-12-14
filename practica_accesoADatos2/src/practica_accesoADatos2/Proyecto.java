package practica_accesoADatos2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Proyecto {

	static Connection conexion = main.connect();
	Scanner scanner = new Scanner(System.in); 
	int num_proy;
	String nombre;
	String dniJefe;
	LocalDate fechaInicio;
	LocalDate fechaFin;
	
	public ArrayList<String> getListAsigEmpleados(int num_proy) {
		try {
			String query = "SELECT empleados.DNI_NIF, empleados.NOMBRE FROM empleados INNER JOIN asig_proyectos ON empleados.DNI_NIF = asig_proyectos.DNI_NIF_EMP where NUM_PROY = " + num_proy + ";";
			Statement pst = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pst.executeQuery(query);
			ArrayList<String> list = new ArrayList<String>(); 
			
			int numeroFilas = 0;
			
			rs.last();
			numeroFilas = rs.getRow();
			rs.first();
			System.out.println("Los empleados asignados a el proyecto numero " + num_proy + " son :");
			System.out.println("");
			for (int i = 1; i<= numeroFilas;i++) {
					for (int i2 = 1; i2<= rs.getMetaData().getColumnCount();i2++) {
						System.out.println(rs.getMetaData().getColumnName(i2) + " : " + rs.getString(i2));
						if (i2 == 1) {
							list.add(rs.getString(i2));
						}
					}
					rs.next();
					System.out.println("");
			}
			rs.close();
			pst.close();
			return list;
		} catch (SQLException ex) {
			 ex.printStackTrace();
			 System.out.println("ERROR: El numero de proyecto introducido no existe");
			 return null;
		}
	}
	
	
	public Boolean save(Proyecto e) throws SQLException {
		try {
			String query = "INSERT INTO proyectos VALUES ('"+num_proy+"','"+nombre+"','"+dniJefe+"','"+fechaInicio+"','"+fechaFin+"');";
			PreparedStatement sta = conexion.prepareStatement(query);
			sta.executeUpdate(query);
			
			sta.close();
			return true;
		} catch (SQLException ex) {
			 ex.printStackTrace();
			 System.out.println("ERROR: El proyecto NO ha podido ser introducido");
			 return false;
		}
	}
	
	public Proyecto() throws SQLException {
		this.num_proy = num_proy;
		this.nombre = nombre;
		this.dniJefe = dniJefe;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public String getDniJefe() {
		return this.dniJefe;
	}
	
	public void setDniJefe(String dniJefe) {
		this.dniJefe = dniJefe;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getNum_proy() {
		return this.num_proy;
	}
	
	public void setNum_proy(int num_proy) {
		this.num_proy = num_proy;
	}
	
	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
	
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

}
