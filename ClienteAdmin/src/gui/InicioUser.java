package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.GestionClases;
import negocio.GestionProfesores;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InicioUser extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioUser frame = new InicioUser();
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
	public InicioUser() {
		setTitle("EasySki");
		GestionClases gestionClases=new GestionClases();
		GestionProfesores gestionProfesores = new GestionProfesores();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.decode("#87CEFA"));
		JButton btnNuevaClase = new JButton("Nueva clase");
		btnNuevaClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
				NuevaClase newClase= new NuevaClase();
				newClase.setVisible(true);
			}


		});
		btnNuevaClase.setBounds(232, 121, 343, 52);
		contentPane.add(btnNuevaClase);
		
		JButton btnNewButton = new JButton("Listado de Clases");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClases listaClases = new ListaClases();
				listaClases.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(232, 207, 343, 52);
		contentPane.add(btnNewButton);
		
		JButton btnCambiarUsuario = new JButton("Cambiar usuario");
		btnCambiarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnCambiarUsuario.setBounds(415, 412, 160, 35);
		contentPane.add(btnCambiarUsuario);
		
		JButton btnEditaCalendario = new JButton("Edita calendario");
		btnEditaCalendario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditaCalendario editaCalendario = new EditaCalendario();
				editaCalendario.setVisible(true);
				dispose();
				
			}
		});
		btnEditaCalendario.setBounds(232, 292, 343, 52);
		contentPane.add(btnEditaCalendario);
	}
}
