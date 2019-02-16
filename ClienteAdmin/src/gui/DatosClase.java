package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Clase;
import negocio.GestionClases;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DatosClase extends JFrame {

	private JPanel contentPane;
	private JTextField txtFecha;
	private JTextField txtHora;
	private JTextField txtProf;
	private JTextField txtAlum;
	private JTextField txtTlfno;
	private JTextField txtZona;
	private JTextField txtMod;
	private JTextField txtTipo;
	private JTextField txtNum;
	private JTextField txtEstado;



	/**
	 * Create the frame.
	 */
	public DatosClase(Clase clase) {
		setTitle("EasySki");
		GestionClases gestionClases = new GestionClases();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.decode("#87CEFA"));
		
		JLabel lblClaseID = new JLabel("");
		lblClaseID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClaseID.setBounds(257, 36, 190, 14);
		contentPane.add(lblClaseID);
		lblClaseID.setText("Datos de la clase "+clase.getClase_ID());

		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(80, 88, 46, 14);
		contentPane.add(lblFecha);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(80, 129, 46, 14);
		contentPane.add(lblHora);
		
		JLabel lblNombreAlumno = new JLabel("Nombre alumno:");
		lblNombreAlumno.setBounds(348, 88, 94, 14);
		contentPane.add(lblNombreAlumno);
		
		JLabel lblTelfonoAlumno = new JLabel("Tel\u00E9fono alumno:");
		lblTelfonoAlumno.setBounds(348, 129, 99, 14);
		contentPane.add(lblTelfonoAlumno);
		
		JLabel lblNombreProfesor = new JLabel("Nombre profesor:");
		lblNombreProfesor.setBounds(80, 169, 118, 14);
		contentPane.add(lblNombreProfesor);
		
		JLabel lblZonaEncuentro = new JLabel("Zona encuentro:");
		lblZonaEncuentro.setBounds(348, 169, 99, 14);
		contentPane.add(lblZonaEncuentro);
		
		JLabel lblNewLabel = new JLabel("Modalidad:");
		lblNewLabel.setBounds(80, 215, 83, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNmeroAlumnos = new JLabel("N\u00FAmero alumnos:");
		lblNmeroAlumnos.setBounds(80, 259, 118, 14);
		contentPane.add(lblNmeroAlumnos);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(348, 259, 46, 14);
		contentPane.add(lblEstado);
		
		JLabel lblTipoClase = new JLabel("Tipo clase: ");
		lblTipoClase.setBounds(348, 215, 83, 14);
		contentPane.add(lblTipoClase);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(189, 85, 86, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		txtFecha.setText(clase.getFecha());
		
		txtHora = new JTextField();
		txtHora.setBounds(189, 126, 86, 20);
		contentPane.add(txtHora);
		txtHora.setColumns(10);
		txtHora.setText(clase.getHoraIni()+"");
		
		txtProf = new JTextField();
		txtProf.setBounds(189, 166, 86, 20);
		contentPane.add(txtProf);
		txtProf.setColumns(10);
		txtProf.setText(clase.getNombre_profesor());
		
		txtAlum = new JTextField();
		txtAlum.setBounds(461, 85, 86, 20);
		contentPane.add(txtAlum);
		txtAlum.setColumns(10);
		txtAlum.setText(clase.getNombre_alumno());
		
		
		txtTlfno = new JTextField();
		txtTlfno.setBounds(461, 126, 86, 20);
		contentPane.add(txtTlfno);
		txtTlfno.setColumns(10);
		txtTlfno.setText(clase.getTlfno_alumno());
		
		txtZona = new JTextField();
		txtZona.setBounds(461, 166, 86, 20);
		contentPane.add(txtZona);
		txtZona.setColumns(10);
		txtZona.setText(clase.getZona_clase());
		
		txtMod = new JTextField();
		txtMod.setBounds(189, 212, 86, 20);
		contentPane.add(txtMod);
		txtMod.setColumns(10);
		txtMod.setText(clase.getModalidad());

		txtTipo = new JTextField();
		txtTipo.setBounds(461, 212, 86, 20);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		txtTipo.setText(clase.getTipo_clase());

		txtNum = new JTextField();
		txtNum.setBounds(189, 256, 86, 20);
		contentPane.add(txtNum);
		txtNum.setColumns(10);

		txtNum.setText(clase.getNumero_alumnos()+"");
		
		txtEstado = new JTextField();
		txtEstado.setBounds(461, 256, 86, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		txtEstado.setText(clase.getEstado());
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ListaClases listaClases= new ListaClases();
				dispose();
				listaClases.setVisible(true);
			}
		});
		btnNewButton.setBounds(279, 328, 89, 23);
		contentPane.add(btnNewButton);
	}
}
