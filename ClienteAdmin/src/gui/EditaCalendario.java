package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

import negocio.GestionProfesores;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class EditaCalendario extends JFrame {

	private JPanel contentPane;
	private JTable tablaProf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditaCalendario frame = new EditaCalendario();
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
	public EditaCalendario() {
		setTitle("EasySki");
		GestionProfesores gestionProfesores= new GestionProfesores();
		gestionProfesores.listaProfesores();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.decode("#87CEFA"));
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(360, 109, 134, 20);
		contentPane.add(dateChooser);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(233, 109, 67, 14);
		contentPane.add(lblFecha);
		
		JLabel lblProfesor = new JLabel("Profesor: ");
		lblProfesor.setBounds(233, 180, 92, 14);
		contentPane.add(lblProfesor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(363, 179, 278, 150);
		contentPane.add(scrollPane);
		
		tablaProf = new JTable();
		scrollPane.setViewportView(tablaProf);
		tablaProf.setModel(gestionProfesores.getModeloProf());
		
		JButton btnDisponible = new JButton("Disponible");
		btnDisponible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = tablaProf.getSelectedRow();
				DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
				Date fechaAux = dateChooser.getDate();
				String dni=tablaProf.getModel().getValueAt(row, column).toString();
				String fecha= formatoFecha.format(fechaAux);
				gestionProfesores.editaDisponibilidad(dni, fecha, 1);
				
			}
		});
		btnDisponible.setBounds(382, 383, 112, 23);
		contentPane.add(btnDisponible);
		
		JButton btnNoDisponible = new JButton("No disponible");
		btnNoDisponible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = tablaProf.getSelectedRow();
				DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
				Date fechaAux = dateChooser.getDate();
				String dni=tablaProf.getModel().getValueAt(row, column).toString();
				String fecha= formatoFecha.format(fechaAux);
				gestionProfesores.editaDisponibilidad(dni, fecha, 0);
				
			}
		});
		btnNoDisponible.setBounds(529, 383, 112, 23);
		contentPane.add(btnNoDisponible);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InicioUser iniUser=new InicioUser();
				iniUser.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(230, 383, 112, 23);
		contentPane.add(btnNewButton);
	}
}
