package recursos.clases;

import java.util.Date;

public class Orden {
    private long     id;
    private int      cliente;
    private String descripcion;
    private String fecha;
    private int empleado;
    public Orden(){
        super();
    }
    public Orden(long id, int cliente, String Descripcion, String Fecha, int Empleado){
    this.id = id;
    this.cliente = cliente;
    this.descripcion =Descripcion;
    this.fecha =Fecha;
    this.empleado = Empleado;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }
}
