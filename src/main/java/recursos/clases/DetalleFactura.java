package recursos.clases;

public class DetalleFactura {
    private long   id;
    private int productoid;
    private int    cliente;
    private double total;
    private double descuento;
    private int empleado;
    public DetalleFactura(){
        super();
    }
    public DetalleFactura(long id, int productoid, int Cliente, double Total, double Descuento, int Empleado){
        this.id=id;
        this.productoid=productoid;
        this.cliente = Cliente;
        this.total = Total;
        this.descuento = Descuento;
        this.empleado = Empleado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProductoid() {
        return productoid;
    }

    public void setProductoid(int productoid) {
        this.productoid = productoid;
    }
    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }
}
