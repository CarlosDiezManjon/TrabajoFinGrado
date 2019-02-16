package conexion;

import java.sql.*;
/**
 *
 * @author Charles
 */
public class Conexion {
    Connection conect = null;
    public Connection conexion()
        {
            try {
                conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdeasyski","root","");

            } catch ( SQLException e) {
            	e.printStackTrace();
            }
            return conect;
        }
     
}