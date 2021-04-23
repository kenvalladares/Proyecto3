package recursos.clases;



public class Menu {
    private long   id;
    private String name;
    private double precio;
    private String descripcion;
    private double descuento;
    public Menu(){
        super();
    }
    public Menu(long id, String name,double Precio, String Descripcion,double Descuento){
        this.id = id;
        this.name = name;
        this.precio = Precio;
        this.descripcion = Descripcion;
        this.descuento = Descuento;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }


}
