package recursos.clases;

import java.util.Date;

public class DetalleOrden {
    private long   id;
    private int productoid;
    private String fecha;
    private double total;
    private int numeroOrden;

    public DetalleOrden() {
        super();
    }
    public DetalleOrden(long id, int productoid, String Fecha, double Total, int NumeroOrden) {
        this.id = id;
        this.productoid = productoid;
        this.fecha = Fecha;
        this.total = Total;
        this.numeroOrden = NumeroOrden;

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
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }





}
