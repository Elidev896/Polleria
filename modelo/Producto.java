package modelo;

public class Producto {
    private int id_producto;
    private String descripcion;
    private float stock;
    private float precio;

    public Producto() {
    }

    public Producto(int id_producto, String descripcion, float stock, float precio) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.stock = stock;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
}
