package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import entidades.Usuario;

public class GestionSesion {
	
	private List<Usuario> listaUsuarios=new ArrayList<Usuario>();
	PreparedStatement st;
	ResultSet rs;
	Conexion con= new Conexion();
	public GestionSesion() {
	}
	
	public int login(Usuario user) {
		Usuario u = null;
		int salida = 0;
		Connection miCon = con.conexion();
		try {
			st= miCon.prepareStatement("select u.usuario, aes_decrypt(u.contrasena,'altocampoo'), u.rol from usuarios u where u.usuario=?");
			st.setString(1, user.getUsuario());
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				u=new Usuario(rs.getString(1),rs.getString(2),rs.getString(3));
			}
			if(user.getUsuario().equals(u.getUsuario()) && user.getPassword().equals(u.getPassword())) {
				if(u.getRol().equals("admin")) {
					salida = 0;
				}
				if(u.getRol().equals("user")) {
					salida = 1;
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			salida = 2;
		}

		return salida;
		
	}
	
	public int registraUsuario(Usuario user) {
		
		try {
			Connection miCon = con.conexion();
			st = miCon.prepareStatement("insert into usuarios(usuario,contrasena,rol) values (?,aes_encrypt(?,'altocampoo'),?)");
			st.setString(1, user.getUsuario());
			st.setString(2, user.getPassword());
			st.setString(3, user.getRol());
			st.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	}
