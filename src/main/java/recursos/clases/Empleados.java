package recursos.clases;

public class Empleados {
    private long     id;
    private String  name;
    private String puesto;
    private double sueldo;
    private int dni;
    private int telefono;
    public Empleados() {
        super();
    }
    public Empleados(long id, String name, String Puesto, double Sueldo, int DNI, int Telefono) {
        this.id = id;
        this.name = name;
        this.puesto =Puesto;
        this.sueldo =Sueldo;
        this.dni =DNI;
        this.telefono =Telefono;
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

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    public int getDNI() {
        return dni;
    }

    public void setDNI(int DNI) {
        this.dni = DNI;
    }
    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }



}
