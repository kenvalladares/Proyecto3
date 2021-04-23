package recursos.clases;

import java.sql.Date;

public class Factura {
    private long    id;
    private int    cliente;
    private int    empleado;
    private int    cai;
    private double total;
    private String fecha;
    public Factura(){
        super();
    }
    public Factura(long id, int cliente, int empleado, int cai, double Total, String Fecha){
        this.id = id;
        this.cliente = cliente;
        this.empleado = empleado;
        this.cai = cai;
        this.total=Total;
        this.fecha = Fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    public int getCAI() {
        return cai ;
    }

    public void setCAI(int CAI) {
        this.cai = CAI;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
