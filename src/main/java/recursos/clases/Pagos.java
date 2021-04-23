package recursos.clases;

public class Pagos {
    private long   id;
    private String name;
    private String    estado;
    public Pagos(){
        super();
    }
    public Pagos(long id,String name, String estado){
    this.id = id;
    this.name = name;
    this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
