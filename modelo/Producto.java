package modelo;

public class Producto {
    private int id_producto;
    private String descripcion;
    private float stock;
    private float stock_critico;
    
    private float precio;

    public Producto() {
    }

    public Producto(int id_producto, String descripcion, float stock, float stock_critico, int tipo, float precio) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.stock = stock;
        this.stock_critico = stock_critico;
        this.precio = precio;
    }   

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public float getStock_critico() {
        return stock_critico;
    }

    public void setStock_critico(float stock_critico) {
        this.stock_critico = stock_critico;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
}
