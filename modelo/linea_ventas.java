
package modelo;


public class linea_ventas {
    private int cod_l;
    private Producto prod;
    private float cant;
    private float subtotal;
    private int venta_asociada;

    public linea_ventas() {
    }

    public linea_ventas(int cod_l, Producto prod, float cant, float subtotal, int venta_asociada) {
        this.cod_l = cod_l;
        this.prod = prod;
        this.cant = cant;
        this.subtotal = subtotal;
        this.venta_asociada = venta_asociada;
    }

    public int getCod_l() {
        return cod_l;
    }

    public void setCod_l(int cod_l) {
        this.cod_l = cod_l;
    }

    public Producto getProd() {
        return prod;
    }

    public void setProd(Producto prod) {
        this.prod = prod;
    }

    public float getCant() {
        return cant;
    }

    public void setCant(float cant) {
        this.cant = cant;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public int getVenta_asociada() {
        return venta_asociada;
    }

    public void setVenta_asociada(int venta_asociada) {
        this.venta_asociada = venta_asociada;
    }
    
    
    
}
