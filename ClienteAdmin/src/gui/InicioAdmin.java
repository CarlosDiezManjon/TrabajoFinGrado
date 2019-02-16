package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.GestionClases;
import negocio.GestionProfesores;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InicioAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioAdmin frame = new InicioAdmin();
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
	public InicioAdmin() {
		setTitle("EasySki");
		GestionProfesores gestionProfesores = new GestionProfesores();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.decode("#87CEFA"));
		
		JButton btnNewButton = new JButton("Carga profesores");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(gestionProfesores.cargaProfesores()==0) {
					JOptionPane.showMessageDialog(null,"Profesores cargados correctamente.");
				}else {
					JOptionPane.showMessageDialog(null,"Error al cargar los profesores");
				}
			}
		});
		btnNewButton.setBounds(302, 129, 257, 55);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_2 = new JButton("Cambiar usuario");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(415, 373, 144, 31);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Cargar disponibilidad profesores");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(gestionProfesores.cargaCalendario()==0) {
					JOptionPane.showMessageDialog(null,"Disponibilidad cargada correctamente");
				}else {
					JOptionPane.showMessageDialog(null,"Error al cargar la disponibilidad");
				}
			}
		});
		btnNewButton_1.setBounds(302, 243, 257, 55);
		contentPane.add(btnNewButton_1);
	}
}
