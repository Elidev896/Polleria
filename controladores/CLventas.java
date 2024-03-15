package controladores;

import modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Producto;
import modelo.linea_ventas;

public class CLventas {

    private Conexion c = new Conexion();
    private Connection con;
    private PreparedStatement consulta;
    private ResultSet resultado;
    private ArrayList<linea_ventas> list_lv;
    private PreparedStatement consulta_prod;
    private ResultSet resultado_prod;
    private PreparedStatement consulta2;

    public void guardarLinea(linea_ventas lv, Producto prod) {
        try {
            con = c.iniciarConexion();
            consulta = con.prepareStatement("INSERT INTO `lineas_ventas` (`idlineas_venta`, `producto`, `cantidad`, `subtotal`, `venta_asociada`) VALUES('0',?,?,?,?)");
            consulta.setInt(1, prod.getId_producto());
            consulta.setInt(2, (int) lv.getCant());
            consulta.setFloat(3, lv.getSubtotal());
            consulta.setInt(4, lv.getVenta_asociada());
            consulta.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el producto", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(consulta);
        }
    }

    public int traerLineaVentaActual() {
        int id = 0;
        try {
            con = c.iniciarConexion();
            consulta2 = con.prepareStatement("SELECT `idlineas_ventas` FROM `lineas_venta` ORDER BY `idlineas_venta` DESC LIMIT 1 ");
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                id = resultado.getInt("idlineas_ventas");
            }
            System.out.println("El id es " + id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo traer la linea de venta actual", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(consulta2);
        }
        return id;
    }

    public ArrayList<linea_ventas> traerLinea(int venta) {
        list_lv = new ArrayList<>();
        try {
            con = c.iniciarConexion();
            consulta = con.prepareStatement("SELECT * FROM `lineas_venta` WHERE `venta_asociada` = ?");
            consulta.setInt(1, venta);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                linea_ventas lv = new linea_ventas();
                Producto prod = new Producto();
                try {
                    consulta_prod = con.prepareStatement("SELECT*FROM `producto` WHERE `id_productos` = ? ");
                    consulta_prod.setInt(1, resultado.getInt("producto"));
                    resultado_prod = consulta_prod.executeQuery();
                    while (resultado_prod.next()) {
                        prod.setId_producto(resultado_prod.getInt("id_productos"));
                        prod.setDescripcion(resultado_prod.getString("descripción"));
                        prod.setStock(resultado.getFloat("stock"));
                        prod.setPrecio(resultado_prod.getFloat("precio"));
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "No se recuperó los productos", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                lv.setCod_l(resultado.getInt("idlineas_venta"));
                lv.setProd(prod);
                lv.setCant(resultado.getFloat("cantidad"));
                lv.setSubtotal(resultado.getFloat("subtotal"));
                lv.setVenta_asociada(resultado.getInt("venta_asociada"));
                list_lv.add(lv);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se recuperó las líneas de venta", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(consulta);
        }
        return list_lv;
    }

    public float eliminrLinea(int lv) {
        float subtotal = 0;
        try {
            con = c.iniciarConexion();
            consulta = con.prepareStatement("SELECT `subtotal` FROM `lineas_venta` WHERE `idlineas_venta` = ?");
            consulta.setInt(1, lv);
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                subtotal = resultado.getFloat("subtotal");
            }
            consulta = con.prepareStatement("DELETE FROM `lineas_venta` WHERE `idlineas_venta` = ?");
            consulta.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar la venta", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return subtotal;
    }
}
