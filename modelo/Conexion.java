package modelo;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private Connection con;
    public Connection iniciarConexion(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/Polleria", "root", "");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se inició la conexión correctamente");
            System.out.println(e);
        }
        return con;
    }
}
