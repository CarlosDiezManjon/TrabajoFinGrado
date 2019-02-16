package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

import entidades.Clase;
import negocio.GestionClases;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ListaClases extends JFrame {

	private JPanel contentPane;
	private JTable tablaClases;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaClases frame = new ListaClases();
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
	public ListaClases() {
		setTitle("EasySki");
		GestionClases gestionClases= new GestionClases();
		DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaAct = new Date();
		gestionClases.listaClases(formatoFecha.format(fechaAct));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.decode("#87CEFA"));
		

		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(194, 45, 95, 20);
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel = new JLabel("Fecha:");
		lblNewLabel.setBounds(138, 51, 46, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(324, 45, 89, 23);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Date fecha = dateChooser.getDate();
				gestionClases.listaClases(formatoFecha.format(fecha));
			}
		});
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 118, 536, 320);
		contentPane.add(scrollPane);


		
		
		
		tablaClases = new JTable();
		scrollPane.setViewportView(tablaClases);
		tablaClases.setModel(gestionClases.getModeloClas());
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 5;
				int row = tablaClases.getSelectedRow();
				int clase_ID = Integer.valueOf(tablaClases.getModel().getValueAt(row, column).toString());
				if(gestionClases.confirmaClase(clase_ID)==0) {
				JOptionPane.showMessageDialog(null, "Clase "+clase_ID+" confirmada.");
				}else {
					JOptionPane.showMessageDialog(null, "Error en la confirmación");
				}
			}
		});
		btnConfirmar.setBounds(675, 115, 110, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 5;
				int row = tablaClases.getSelectedRow();
				int clase_ID = Integer.valueOf(tablaClases.getModel().getValueAt(row, column).toString());
				gestionClases.eliminaClase(clase_ID);
				JOptionPane.showMessageDialog(null, "Clase "+clase_ID+" eliminada.");
			}
		});
		btnEliminar.setBounds(675, 185, 110, 23);
		contentPane.add(btnEliminar);
		
		JButton btnNewButton = new JButton("Visualizar");
		btnNewButton.setBounds(675, 151, 110, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int column = 5;
				int row = tablaClases.getSelectedRow();
				int clase_ID = Integer.valueOf(tablaClases.getModel().getValueAt(row, column).toString());
				Clase clase= gestionClases.consultaClase(clase_ID);
				dispose();
				DatosClase datosClase= new DatosClase(clase);
				datosClase.setVisible(true);
				
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InicioUser iniUser=new InicioUser();
				iniUser.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(675, 219, 110, 23);
		contentPane.add(btnVolver);
	}
}
