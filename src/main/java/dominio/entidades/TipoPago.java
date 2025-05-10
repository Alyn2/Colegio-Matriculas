package dominio.entidades;

public class TipoPago {
    private String descripcion;

    public TipoPago() {
    }

    public TipoPago(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
