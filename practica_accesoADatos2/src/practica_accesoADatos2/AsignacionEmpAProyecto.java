package practica_accesoADatos2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class AsignacionEmpAProyecto {
	Connection conexion = main.connect();
	Scanner scanner = new Scanner(System.in); 
	int num_proy;
	String dniEmp;
	LocalDate fechaInicio;
	LocalDate fechaFin;
	
	
	public Boolean save(AsignacionEmpAProyecto asigEmpToProy) throws SQLException {
		try {
			String query = "INSERT INTO asig_proyectos VALUES('"+dniEmp+"','"+num_proy+"','"+fechaInicio+"','"+fechaFin+"')";
			PreparedStatement sta = conexion.prepareStatement(query);
			sta.executeUpdate(query);
			
			sta.close();
			return true;
		} catch (SQLException ex) {
			 ex.printStackTrace();
			 System.out.println("ERROR: El empleado NO ha podido ser asignado al proyecto");
			 return false;
		}finally{
			 if(conexion!=null)
				 conexion.close();
			}
	}
	
	public AsignacionEmpAProyecto() throws SQLException {
		this.num_proy = num_proy;
		this.dniEmp = dniEmp;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public String getDniEmp() {
		return this.dniEmp;
	}
	
	public void setDniEmp(String dniJefe) {
		this.dniEmp = dniJefe;
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
