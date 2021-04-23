package recursos.clases;

public class Mesas {
    private long   id;
    private int asientos;
    private String    estado;
    private int numero;
    private int piso;
    public Mesas(){
        super();
    }
    public Mesas(long id, int Asientos, String estado, int Numero, int Piso){
    this.id = id;
    this.asientos=asientos;
    this.estado=estado;
    this.numero =Numero;
    this.piso =Piso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
}
