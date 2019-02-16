package negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import conexion.Conexion;

public class GestionProfesores {
	
	
	Conexion con= new Conexion();
	PreparedStatement st;
	DefaultTableModel modeloProf=new DefaultTableModel();
	
public int cargaProfesores() {
		
		try {
		Connection miCon = con.conexion();
		FileInputStream fichero = new FileInputStream(new File("E:\\Charles\\Documents\\Trabajo de Fin de Grado\\carga_profesores.xlsx"));
		
		XSSFWorkbook libro = new XSSFWorkbook(fichero);
		XSSFSheet hoja = libro.getSheetAt(0);
		
		int numFilas = hoja.getLastRowNum();
		
		for(int i=1; i<=numFilas;i++) {
			Row fila = hoja.getRow(i);	

			st= miCon.prepareStatement("insert into profesores(dni,nombre,apellido1,apellido2,fechaReg) values (?,?,?,?,?)");
			
			st.setString(1, fila.getCell(0).getStringCellValue());

			st.setString(2, fila.getCell(1).getStringCellValue());

			st.setString(3, fila.getCell(2).getStringCellValue());

			st.setString(4, fila.getCell(3).getStringCellValue());

			st.setString(5, fila.getCell(4).getStringCellValue());

			st.executeUpdate();

			
			st = miCon.prepareStatement("select modalidad_ID from modalidades where modalidad=?");
			st.setString(1, fila.getCell(5).getStringCellValue());
			ResultSet rs1= st.executeQuery();
			String modalidad_ID="";
			while(rs1.next()) {
			modalidad_ID=rs1.getString("modalidad_ID");
			}
			st= miCon.prepareStatement("select p.profesor_ID from profesores p where p.dni=?");
			st.setString(1, fila.getCell(0).getStringCellValue());
			ResultSet rs2= st.executeQuery();
			int prof_ID = 0;
			while(rs2.next()) {
				prof_ID= rs2.getInt(1);
			}
			
			st= miCon.prepareStatement("insert into prof_modalidad(profesor_ID,modalidad_ID,fecha_alta) values (?,?,?)");
			st.setInt(1, prof_ID);
			st.setString(2, modalidad_ID);
			st.setString(3, fila.getCell(4).getStringCellValue());
			st.executeUpdate();
			
			
		}
		
		
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
		
	}
	
	public int cargaCalendario() {
			int disponible=1;
			int cuenta=0;
		try {
			Connection miCon = con.conexion();
			FileInputStream fichero = new FileInputStream(new File("E:\\Charles\\Documents\\Trabajo de Fin de Grado\\carga_disponibilidad.xlsx"));
			
			XSSFWorkbook libro = new XSSFWorkbook(fichero);
			XSSFSheet hoja = libro.getSheetAt(0);
			
			int numFilas = hoja.getLastRowNum();
			
			for(int i=1; i<=numFilas;i++) {
				Row fila = hoja.getRow(i);	
				int numColum = fila.getLastCellNum();
				String dni = fila.getCell(0).getStringCellValue();
				System.out.println("Profesor "+dni+" con "+numColum+ " fechas.");
				for(int j=1;j < numColum; j++) {
					
					st= miCon.prepareStatement("select p.profesor_ID from profesores p where p.dni=?");
					st.setString(1, dni);
					ResultSet rs= st.executeQuery();
					int prof_ID = 0;
					while(rs.next()) {
						prof_ID= rs.getInt(1);
					}
					
					
					st= miCon.prepareStatement("insert into calendario(profesor_ID,fecha,disponible) values (?,?,?)");
					
					st.setInt(1, prof_ID);
					st.setString(2, fila.getCell(j).getStringCellValue());
					st.setInt(3, disponible);
					st.executeUpdate();
					cuenta++;
					System.out.println("Introducida la fecha: "+fila.getCell(j).getStringCellValue());
				}

			}
			System.out.println(cuenta);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	
	public void listaProfesores() {
		try {
		Connection miCon = con.conexion();

			st= miCon.prepareStatement("select p.dni, p.nombre, p.apellido1 from profesores p");
			ResultSet rs= st.executeQuery();
			
			modeloProf.setColumnIdentifiers(new Object[] {"DNI","Nombre","Apellido"});
			while (rs.next()) {
				modeloProf.addRow(new Object[] {rs.getString("dni"),rs.getString("nombre"),rs.getString("apellido1")});
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editaDisponibilidad(String dni, String fecha, int disponible) {
		try {
			
		Connection miCon = con.conexion();
		
		st= miCon.prepareStatement("select p.profesor_ID from profesores p where p.dni=?");
		st.setString(1, dni);
		ResultSet rs= st.executeQuery();
		int prof_ID = 0;
		while(rs.next()) {
			prof_ID= rs.getInt(1);
		}
		
			
		if(disponible==1) {
			st = miCon.prepareStatement("insert into calendario(profesor_ID,fecha,disponible) values (?,?,?)");
			st.setInt(1, prof_ID);
			st.setString(2, fecha);
			st.setInt(3, disponible);
			st.executeUpdate();
		}else {
			st = miCon.prepareStatement("delete from calendario where profesor_ID = ? and fecha = ?");
			st.setInt(1, prof_ID);
			st.setString(2, fecha);
			st.executeUpdate();
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	public DefaultTableModel getModeloProf() {
		return modeloProf;
	}

}
