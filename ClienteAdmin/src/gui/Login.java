package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.util.SystemOutLogger;

import entidades.Usuario;
import negocio.GestionClases;
import negocio.GestionProfesores;
import negocio.GestionSesion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
		GestionSesion gestionSesion=new GestionSesion();
		setTitle("EasySki");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.decode("#87CEFA"));
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(312, 106, 97, 30);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContrasea.setBounds(312, 187, 97, 24);
		contentPane.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(462, 107, 139, 33);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(462, 185, 139, 33);
		contentPane.add(txtPassword);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario user=new Usuario(txtUsuario.getText(),txtPassword.getText());
				if(gestionSesion.login(user)==0) {
					dispose();
					InicioAdmin iniA=new InicioAdmin();
					iniA.setVisible(true);
				}else
				if(gestionSesion.login(user)==1) {
					dispose();
					InicioUser iniU=new InicioUser();
					iniU.setVisible(true);
				}else
		
				if(gestionSesion.login(user)==2) {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
				}
			}

			
		});
		btnEntrar.setBounds(312, 390, 126, 30);
		contentPane.add(btnEntrar);
		
		JLabel lblNewLabel = new JLabel("EASYSKI");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(403, 27, 97, 30);
		contentPane.add(lblNewLabel);
		
		JComboBox listaRol = new JComboBox();
		listaRol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		listaRol.setModel(new DefaultComboBoxModel(new String[] {"admin", "user"}));
		listaRol.setBounds(462, 257, 139, 33);
		contentPane.add(listaRol);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String u= txtUsuario.getText();
				String p= txtPassword.getText();
				if(u.equals("") || p.equals("")) {
					JOptionPane.showMessageDialog(null,"Introduzca usuario o contraseña");
				}else {
				Usuario user = new Usuario(u,p,listaRol.getSelectedItem().toString());
				if(gestionSesion.registraUsuario(user)==1) {
					JOptionPane.showMessageDialog(null,"El usuario ya existe");
				}else {
					JOptionPane.showMessageDialog(null,"Registro completado");
				}
				}
			}
		});
		btnRegistrar.setBounds(475, 390, 126, 30);
		contentPane.add(btnRegistrar);
		
		JLabel lblRol = new JLabel("Rol (Solo registro):");
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRol.setBounds(312, 261, 126, 24);
		contentPane.add(lblRol);
		

	}
}
