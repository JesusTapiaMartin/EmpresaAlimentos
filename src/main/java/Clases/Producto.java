package Clases;

public class Producto {

    // ===== ATRIBUTOS =====
    private int     id      , cantidad;
    private String  nombre;


    // ===== CONSTRUCTOR =====
    public Producto(int id, String nombre, int cantidad) {
        this.id         = id;
        this.nombre     = nombre;
        this.cantidad   = cantidad;
    }


    // ===== GETTER Y SETTER =====
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
