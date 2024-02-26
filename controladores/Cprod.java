package controladores;

import modelo.Conexion;
import modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Cprod {

    private Conexion conect = new Conexion();
    private Connection c2;
    private PreparedStatement consulta;
    private ResultSet resultado;
    private ArrayList<Producto> lista_prod;

    public void agregarProducto(Producto prod) {
        try {
            c2 = conect.iniciarConexion();
            consulta = c2.prepareStatement("INSERT INTO `productos`(`id_producto`, `descripcion`, `stock`, `precio`, `baja_logica`) VALUES('0',?,?,?,'0')");
            consulta.setString(1, prod.getDescripcion());
            consulta.setFloat(2, prod.getStock());
            consulta.setFloat(3, prod.getPrecio());
            consulta.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el producto", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(consulta);
        }
    }

    public ArrayList<Producto> traerProductos() {
        lista_prod = new ArrayList<>();
        try {
            c2 = conect.iniciarConexion();
            consulta = c2.prepareStatement("SELECT * FROM productos WHERE `baja_logica` = 0");
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                Producto p = new Producto();
                
                p.setDescripcion(resultado.getString("descripcion"));
                p.setStock(resultado.getFloat("stock"));
                p.setPrecio(resultado.getFloat("Precio"));
                lista_prod.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar con los productos");
            System.out.println(consulta);
        }
        return lista_prod;
    }

    public void modificarProductos(Producto prod) {
        try {
            c2 = conect.iniciarConexion();
            consulta = c2.prepareStatement("UPDATE `productos` SET `descripcion` = ?, `stock` = ?, `precio` = ? WHERE `id_producto` = ? ");
            consulta.setString(1, prod.getDescripcion());
            consulta.setFloat(2, prod.getStock());
            consulta.setFloat(3, prod.getPrecio());
            consulta.setInt(4, prod.getId_producto());
            consulta.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo modificar el producto", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(consulta);
        }
    }
}
