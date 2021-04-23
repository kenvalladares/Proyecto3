package recursos.clases;

import java.util.Date;

public class Proveedores {
    private long   id;
    private String name;
    private String producto;
    private String fechaExp;
    private String fechaElaboracion;
    private double precio;
    public Proveedores(){
        super();
    }
    public Proveedores(long id, String name, String Producto, String FechaExp, String FechaElaboracion, double Precio ){
    this.id = id;
    this.name = name;
    this.producto =Producto;
    this.fechaExp =FechaExp;
    this.fechaElaboracion = FechaElaboracion;
    this.precio = Precio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }


    public String getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(String fechaExp) {
        this.fechaExp = fechaExp;
    }

    public String getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(String fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
