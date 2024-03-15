package controladores;

import modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Ventas;
import modelo.linea_ventas;

public class Cventas {

    private Conexion c = new Conexion();
    private Connection con;
    private PreparedStatement consulta;
    private ResultSet resultado;
    private ArrayList<Ventas> list_v;
    private ArrayList<linea_ventas> list_lv;
    private PreparedStatement consulta2;

    public int guardarVenta(Ventas v) {
        int id = 0;
        Date date = new Date(System.currentTimeMillis());
        java.sql.Date fechaSQL = new java.sql.Date(date.getTime());
        try {
            con = c.iniciarConexion();
            consulta = con.prepareStatement("INSERT INTO `ventas´ (`idventas`,`fecha`, `total`) VALUES ('0',?,?)");
            consulta.setDate(1, fechaSQL);
            consulta.setFloat(2, v.getTotal());
            consulta.executeUpdate();
            id = traerVentaActual();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar con las ventas", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    public int traerVentaActual() {
        int id = 0;
        try {
            con = c.iniciarConexion();
            consulta2 = con.prepareStatement("SELECT `idventas` FROM `ventas` ORDER BY `ìdventas` DESC LIMIT 1 ");
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                id = resultado.getInt("idventas");
            }
            System.out.println("El id es " + id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo traer una venta");
            System.out.println(consulta2);
        }
        return id;
    }

    public ArrayList<Ventas> traerVentas() {
        list_v = new ArrayList<>();
        try {
            con = c.iniciarConexion();
            consulta = con.prepareStatement("SELECT*FROM `ventas`");
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                Ventas vent = new Ventas();
                vent.setCod_v(resultado.getInt("idventas"));
                vent.setFecha(resultado.getDate("fecha"));
                vent.setTotal(resultado.getFloat("total"));
                list_v.add(vent);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo trer los productos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return list_v;
    }

    public void actualizarStock(Ventas v) {
        int Producto;
        float nuevo_stock;
        float cantidad;
        float stock;
        String descripcion;
        try {
            con = c.iniciarConexion();
            consulta = con.prepareStatement("SELECT lv.producto, p.stock, lv.cantidad, p.descripcion FROM `lineas_venta` lv INNER JOIN `producto` p ON lv.producto = p.idproductos WHERE lv.venta_asociada = ?");
            consulta.setInt(1, v.getCod_v());
            resultado = consulta.executeQuery();
            while (resultado.next()) {
                Producto = resultado.getInt("producto");
                cantidad = resultado.getFloat("cantidad");
                stock = resultado.getFloat("stock");
                nuevo_stock = stock - cantidad;
                descripcion = resultado.getString("descripcion");
                if (cantidad > stock) {
                    JOptionPane.showMessageDialog(null, "No se puede vender el producto " + descripcion + " porqueno hay suficiente stock. Sólo queda " + stock + " " + descripcion, "ERROR", JOptionPane.ERROR_MESSAGE);
                    consulta = con.prepareStatement("DELETE FROM `lineas_venta` WHERE lineas_venta.venta_asociada = ?");
                    consulta.setInt(1, v.getCod_v());
                    consulta.executeUpdate();

                    consulta = con.prepareStatement("DELETE FROM `ventas` WHERE ventas.idventas = ?");
                    consulta.setInt(1, v.getCod_v());
                    consulta.executeUpdate();
                } else {
                    consulta = con.prepareStatement("UPDATE `producto` SET `stock` WHERE `idproducto` = ?");
                    consulta.setFloat(1, nuevo_stock);
                    consulta.setInt(2, Producto);
                    consulta.executeUpdate();
                    System.out.println(consulta);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el stock", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(consulta);
            System.out.println(e);
        }
    }
    
    public void calcularTotal(Ventas v){
        try {
            con = c.iniciarConexion();
            consulta = con.prepareStatement("UPDATE `ventas` SET `total` = ? WHERE `idventas` = ?");
            consulta.setFloat(1, v.getTotal());
            consulta.setInt(2, v.getCod_v());
            consulta.executeUpdate(); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede calcular el total", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(consulta);
        }
    }
}
