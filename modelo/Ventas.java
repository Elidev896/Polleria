package modelo;

import java.util.ArrayList;
import java.util.Date;


public class Ventas {
private int cod_v;
private Date fecha;
private ArrayList<linea_ventas> lv = new ArrayList<>();
private float total;

    public Ventas() {
    }

    public Ventas(int cod_v, Date fecha, float total) {
        this.cod_v = cod_v;
        this.fecha = fecha;
        this.total = total;
    }

    public int getCod_v() {
        return cod_v;
    }

    public void setCod_v(int cod_v) {
        this.cod_v = cod_v;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<linea_ventas> getLv() {
        return lv;
    }

    public void setLv(ArrayList<linea_ventas> lv) {
        this.lv = lv;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }



}
