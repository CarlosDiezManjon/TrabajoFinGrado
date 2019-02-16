package negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import conexion.Conexion;
import entidades.Clase;
import entidades.Factura;

public class GestionClases {
	
	Conexion con= new Conexion();
	DefaultTableModel modeloProf=new DefaultTableModel();
	DefaultTableModel modeloClas=new DefaultTableModel();
	PreparedStatement st;
	String fechaAux;

	


	public int nuevaClase(Clase clase){
		try {
			Connection myConn = con.conexion();
			
			st = myConn.prepareStatement("insert into clases(profesor_ID, nombre_alumno,horaIni,horaFin,tlfno_alumno,tipo_clase,modalidad_ID,zona_clase,numero_alumnos,fecha,estado) values (?,?,?,?,?,?,?,?,?,?,?)");
			st.setInt(1, clase.getProfesor_ID());
			st.setString(2, clase.getNombre_alumno());
			st.setInt(3, clase.getHoraIni());
			st.setInt(4, clase.getHoraFin());
			st.setString(5, clase.getTlfno_alumno());
			st.setString(6, clase.getTipo_clase());
			st.setInt(7, clase.getModalidad_ID());
			st.setString(8, clase.getZona_clase());
			st.setInt(9, clase.getNumero_alumnos());
			st.setString(10, clase.getFecha());
			st.setString(11, clase.getEstado());
			st.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	//CAMBIAR
	public void profesoresDisponibles(String fecha, int horaIni, int horaFin, int modalidad) {
		try {
		Connection miCon = con.conexion();

			st= miCon.prepareStatement("select p.profesor_ID, p.nombre, p.apellido1 from profesores p where p.profesor_ID in (select c.profesor_ID from calendario c where c.fecha = ? and c.disponible=1) and p.profesor_ID in (select pm.profesor_ID from prof_modalidad pm where pm.modalidad_ID=? and pm.fecha_baja is null) and p.profesor_ID not in (select c.profesor_ID from clases c where c.fecha=? and c.horaIni=? or c.horaIni<? and c.horaFin>? or c.horaFin=? or c.horaFin>? and c.horaIni<? or c.horaIni>? and c.horaFin<?)");
			st.setString(1, fecha);
			st.setInt(2, modalidad);
			st.setString(3, fecha);
			st.setInt(4, horaIni);
			st.setInt(5, horaIni);
			st.setInt(6, horaIni);
			st.setInt(7, horaFin);
			st.setInt(8, horaFin);
			st.setInt(9, horaFin);
			st.setInt(10, horaIni);
			st.setInt(11, horaFin);
			ResultSet rs= st.executeQuery();
			
			modeloProf.setColumnIdentifiers(new Object[] {"Código","Nombre","Apellido"});
			while (rs.next()) {
				modeloProf.addRow(new Object[] {rs.getString("profesor_ID"),rs.getString("nombre"),rs.getString("apellido1")});
			}
			
		miCon.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void listaClases(String fecha) {
		fechaAux = fecha;
		try {
			modeloClas.setRowCount(0);
			Connection miCon = con.conexion();
			
			st= miCon.prepareStatement("select c.horaIni, c.horaFin, c.nombre_alumno, p.nombre, c.estado, c.clase_ID  from clases c, profesores p where c.profesor_ID = p.profesor_ID and c.fecha=? order by c.horaIni");
			st.setString(1, fecha);
			ResultSet rs= st.executeQuery();
			
			modeloClas.setColumnIdentifiers(new Object[] {"Inicio","Fin","Alumno","Profesor","Estado","Cod_Clase"});
			while (rs.next()) {
				modeloClas.addRow(new Object[] {rs.getString("horaIni"),rs.getString("horaFin"), rs.getString("nombre_alumno"),rs.getString("nombre"),rs.getString("estado"),rs.getString("clase_ID")});
			}
			
			
			miCon.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int eliminaClase(int clase_ID) {


		try {

			Connection miCon = con.conexion();
			st= miCon.prepareStatement("delete from clases where clase_ID=?");
			st.setInt(1, clase_ID);
			st.execute();
			
			miCon.close();
			modeloClas.setRowCount(0);
			listaClases(fechaAux);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return 0;

	}
	
	public int confirmaClase(int clase_ID) {


		try {
			
			Connection miCon = con.conexion();
			st= miCon.prepareStatement("update clases set estado = 'Confirmada' where clase_ID = ?");
			st.setInt(1, clase_ID);
			st.execute();
			miCon.close();
			generaFactura(clase_ID);
			modeloClas.setRowCount(0);
			listaClases(fechaAux);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return 0;

	}
	
	public Clase consultaClase(int clase_ID) {
		Clase clase = null;
		String mod_ID="";
		try {
			
			Connection miCon = con.conexion();
			st= miCon.prepareStatement("select c.clase_ID, p.nombre, c.nombre_alumno, c.tlfno_alumno, c.horaIni,c.horaFin, c.tipo_clase, m.modalidad, c.zona_clase, c.numero_alumnos, c.fecha, c.estado from clases c,profesores p, modalidades m where c.profesor_ID=p.profesor_ID and c.clase_ID=? and c.modalidad_ID=m.modalidad_ID");
			st.setInt(1, clase_ID);
			ResultSet rs=st.executeQuery();
			
			
			while(rs.next()) {
			clase=new Clase(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10),rs.getString(11),rs.getString(12));
			}
			
			
			miCon.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return clase;
	}
	
	public int generaFactura(int clase_ID) {
		
		
		try {
			Connection miCon = con.conexion();
			st= miCon.prepareStatement("select c.clase_ID, c.fecha, t.tipo_clase,t.precio_persona, t.precio_total from clases c, tarifas t where c.numero_alumnos= t.num_alumnos and c.clase_ID=?");
			st.setInt(1, clase_ID);
			ResultSet rs1 = st.executeQuery();
			Factura fact = null;
			
			while(rs1.next()) {
				fact= new Factura(rs1.getInt(1),rs1.getString(2),rs1.getString(3), rs1.getDouble(4),rs1.getDouble(5));
			}
			DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
			Date fecha = new Date();
			st= miCon.prepareStatement("insert into facturas(clase_ID, fecha, importe_persona, importe_total) values (?,?,?,?)");
			st.setInt(1, fact.getClase_ID());
			st.setString(2, formatoFecha.format(fecha));
			st.setDouble(3, fact.getImporte_persona());
			st.setDouble(4, fact.getImporte_total());
			st.executeUpdate();
			
			st = miCon.prepareStatement("select f.factura_ID from facturas f where f.clase_ID =? ");
			st.setInt(1, fact.getClase_ID());
			
			ResultSet rs2 = st.executeQuery();
			int factura_ID=0;
			
			while(rs2.next()) {
				factura_ID= rs2.getInt(1);	
			}
			
			miCon.close();
			
		String archivo = "E:\\Charles\\Documents\\Trabajo de Fin de Grado\\Eclipse Workspace\\Fra-N"+factura_ID+".pdf";
		Document document = new Document();
		

			PdfWriter.getInstance(document, new FileOutputStream(archivo));
			
			document.open();
			
			DateFormat formatoFecha1 = new SimpleDateFormat("dd/MM/yyyy");
			Paragraph para1 = new Paragraph("Factura");
			Paragraph para2 = new Paragraph("Nº "+factura_ID);
			Paragraph para3 = new Paragraph("Fecha: "+ formatoFecha1.format(fecha));
			Paragraph para4 = new Paragraph("Tipo clase: "+fact.getTipo_clase());
			Paragraph para5 = new Paragraph("Importe por persona: "+fact.getImporte_persona()+"€ (IVA Incluido)");
			Paragraph para6 = new Paragraph("Importe total: "+fact.getImporte_total()+"€ (IVA Incluido)");
			
			document.add(para1);
			document.add(para2);
			document.add(para3);
			document.add(para4);
			document.add(para5);
			document.add(para6);
			
			
			
			
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			return 1;
		}
		return 0;
		
	}

	

	public DefaultTableModel getModeloProf() {
		return modeloProf;
	}
	
	public DefaultTableModel getModeloClas() {
		return modeloClas;
	}
	



}
