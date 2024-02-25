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

    private Conexion conect;
    private Connection c2;
    private PreparedStatement consulta;
    private ResultSet resultado;
    private ArrayList<Producto> lista_prod;
    private Producto prod;

    public void agregarProducto() {
        try {
            c2 = conect.iniciarConexion();
            consulta = c2.prepareStatement("INSERT INTO `productos`(`id_producto`, `descripcion`, `stock`,  `stock_critico`, `precio`, `baja_logica`) VALUES('0',?,?,?,?,'0')");
            consulta.setString(1, prod.getDescripcion());
            consulta.setFloat(2, prod.getStock());
            consulta.setFloat(3, prod.getStock_critico());
            consulta.setFloat(4, prod.getPrecio());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el producto");
            System.out.println(consulta);
        }
    }
    
    public ArrayList<Producto> traerProductos(){
     lista_prod = new ArrayList<>();
        try {
            c2 = conect.iniciarConexion();
            consulta = c2.prepareStatement("SELECT * FROM productos WHERE `baja_logica` = 0");
            resultado = consulta.executeQuery();
            while(resultado.next()){
                Producto p = new Producto();
                p.setId_producto(resultado.getInt("id_producto"));
                p.setDescripcion(resultado.getString("descripcion"));
                p.setStock(resultado.getFloat("stock"));
                p.setStock_critico(resultado.getFloat("stock_critico"));
                p.setPrecio(resultado.getFloat("Precio"));
                lista_prod.add(p);
            }
        } catch (SQLException e) {
        }
        return lista_prod;
    }
    
}
