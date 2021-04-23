package recursos.clases;

public class Producto {
    private long   id;
    private String name;
    private int proveedor;
    private int cantidad;
    private double precio;
    public Producto(){
        super();
    }
    public Producto(long id, String name, int Proveedor, int Cantidad, double Precio){
       this.id = id;
       this.name = name;
       this.proveedor = Proveedor;
       this.cantidad =Cantidad;
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

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
