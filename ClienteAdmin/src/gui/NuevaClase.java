package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Clase;
import negocio.GestionClases;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class NuevaClase extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumAlum;
	private JTextField txtNombre;
	private JTextField txtTlfno;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaClase frame = new NuevaClase();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NuevaClase() {
		setTitle("EasySki");
		GestionClases gestionClases=new GestionClases();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.decode("#87CEFA"));
		
		JLabel lblDatosDeLa = new JLabel("Datos de la Clase");
		lblDatosDeLa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDatosDeLa.setBounds(314, 11, 189, 29);
		contentPane.add(lblDatosDeLa);
		
		JLabel lblNewLabel = new JLabel("Fecha:");
		lblNewLabel.setBounds(86, 97, 80, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblHora = new JLabel("Hora inicio:");
		lblHora.setBounds(86, 153, 80, 14);
		contentPane.add(lblHora);
		
		JLabel lblTipoClase = new JLabel("Tipo clase:");
		lblTipoClase.setBounds(86, 255, 80, 14);
		contentPane.add(lblTipoClase);
		
		JLabel lblZonaEncuentro = new JLabel("Zona encuentro:");
		lblZonaEncuentro.setBounds(81, 311, 107, 14);
		contentPane.add(lblZonaEncuentro);
		
		JLabel lblNumAlumnos = new JLabel("Num. Alumnos:");
		lblNumAlumnos.setBounds(336, 204, 80, 14);
		contentPane.add(lblNumAlumnos);
		

		
		JLabel lblNombreAlumno = new JLabel("Nombre alumno:");
		lblNombreAlumno.setBounds(336, 97, 97, 14);
		contentPane.add(lblNombreAlumno);
		
		JLabel lblTelfonoAlumno = new JLabel("Tel\u00E9fono alumno:");
		lblTelfonoAlumno.setBounds(336, 153, 97, 14);
		contentPane.add(lblTelfonoAlumno);
		
		JLabel lblProfesor = new JLabel("Profesor:");
		lblProfesor.setBounds(336, 311, 46, 14);
		contentPane.add(lblProfesor);
		
		JLabel lblHoraFin = new JLabel("Hora fin:");
		lblHoraFin.setBounds(86, 204, 80, 14);
		contentPane.add(lblHoraFin);
		
		JLabel lblModalidad = new JLabel("Modalidad:");
		lblModalidad.setBounds(336, 255, 80, 14);
		contentPane.add(lblModalidad);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		dateChooser.setBounds(188, 94, 119, 20);
		contentPane.add(dateChooser);
		
		JComboBox horaIni = new JComboBox();
		horaIni.setModel(new DefaultComboBoxModel(new String[] {"9", "10", "11", "12", "13", "14", "15", "16"}));
		horaIni.setBounds(188, 150, 119, 20);
		contentPane.add(horaIni);
		


		JComboBox horaFin = new JComboBox();
		horaFin.setBounds(188, 201, 119, 20);
		contentPane.add(horaFin);
		horaFin.setModel(new DefaultComboBoxModel(new String[] {"10", "11", "12", "13", "14", "15", "16", "17"}));
		
		JComboBox tipo_clase = new JComboBox();
		tipo_clase.setModel(new DefaultComboBoxModel(new String[] {"Particular", "Grupal"}));
		tipo_clase.setBounds(188, 252, 119, 20);
		contentPane.add(tipo_clase);
		
		JComboBox modalidad = new JComboBox();
		modalidad.setModel(new DefaultComboBoxModel(new String[] {"1-Esqui", "2-Snowboard"}));
		modalidad.setBounds(458, 252, 128, 20);
		contentPane.add(modalidad);
		
		JComboBox zona_clase = new JComboBox();
		zona_clase.setModel(new DefaultComboBoxModel(new String[] {"Escuela", "Calgosa", "H\u00EDjar"}));
		zona_clase.setBounds(188, 305, 119, 20);
		contentPane.add(zona_clase);
		
		txtNumAlum = new JTextField();
		txtNumAlum.setBounds(458, 201, 128, 20);
		contentPane.add(txtNumAlum);
		txtNumAlum.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(458, 94, 128, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTlfno = new JTextField();
		txtTlfno.setBounds(458, 150, 128, 20);
		contentPane.add(txtTlfno);
		txtTlfno.setColumns(10);

		table = new JTable();
		table.setBounds(458, 311, 248, 96);
		contentPane.add(table);
		table.setModel(gestionClases.getModeloProf());
		
		
		
		JButton btnSelectProf = new JButton("Selec. Profesor");
		btnSelectProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
				String fecha = formatoFecha.format(dateChooser.getDate());
				String mod = modalidad.getSelectedItem().toString();
				String[] aux = mod.split("-");
				int mod_ID = Integer.valueOf(aux[0]);
				int horaI= Integer.valueOf(horaIni.getSelectedItem().toString());
				int horaF=Integer.valueOf(horaFin.getSelectedItem().toString());
				System.out.println("Buscando: FECHA "+fecha+" HORA INI "+horaI+" HORA FIN "+horaF+"ID_ MODALIDAD "+mod_ID);
				gestionClases.profesoresDisponibles(fecha.toString(), horaI, horaF, mod_ID);
				
			}
		});
		btnSelectProf.setBounds(223, 445, 128, 23);
		contentPane.add(btnSelectProf);
		

		
		JButton btnConfirm = new JButton("Confirmar");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
				Date fecha = dateChooser.getDate();
				int column = 0;
				int row = table.getSelectedRow();
				String profesor_ID = table.getModel().getValueAt(row, column).toString();
				String mod = modalidad.getSelectedItem().toString();
				String[] aux = mod.split("-");
				int mod_ID = Integer.valueOf(aux[0]);
				Clase clase=new Clase(Integer.parseInt(profesor_ID),txtNombre.getText(),txtTlfno.getText(),Integer.valueOf(horaIni.getSelectedItem().toString()),Integer.valueOf(horaFin.getSelectedItem().toString()),tipo_clase.getSelectedItem().toString(),mod_ID,zona_clase.getSelectedItem().toString(),Integer.parseInt(txtNumAlum.getText()),formatoFecha.format(fecha),"Confirmada");
				if(gestionClases.nuevaClase(clase)==0) {
				JOptionPane.showMessageDialog(null, "Clase confirmada");
				NuevaClase nuevaClase = new NuevaClase();
				nuevaClase.setVisible(true);
				dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Error en la confirmación");
				}
			}
		});
		btnConfirm.setBounds(547, 445, 97, 23);
		contentPane.add(btnConfirm);
		
		

		
		
		
		JButton btnReserv = new JButton("Reservar");
		btnReserv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
				Date fecha = dateChooser.getDate();
				int column = 0;
				int row = table.getSelectedRow();
				String profesor_ID = table.getModel().getValueAt(row, column).toString();
				String mod = modalidad.getSelectedItem().toString();
				String[] aux = mod.split("-");
				int mod_ID = Integer.valueOf(aux[0]);
				Clase clase=new Clase(Integer.parseInt(profesor_ID),txtNombre.getText(),txtTlfno.getText(),Integer.valueOf(horaIni.getSelectedItem().toString()),Integer.valueOf(horaFin.getSelectedItem().toString()),tipo_clase.getSelectedItem().toString(),mod_ID,zona_clase.getSelectedItem().toString(),Integer.parseInt(txtNumAlum.getText()),formatoFecha.format(fecha),"Reservada");
				if(gestionClases.nuevaClase(clase)==0) {
				JOptionPane.showMessageDialog(null, "Clase reservada");

				NuevaClase nuevaClase = new NuevaClase();
				nuevaClase.setVisible(true);
				dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Error en la reserva");
				}
			}
		});
		btnReserv.setBounds(406, 445, 97, 23);
		contentPane.add(btnReserv);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InicioUser iniUser=new InicioUser();
				iniUser.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(86, 445, 97, 23);
		contentPane.add(btnVolver);
		

		



		
	}
}
