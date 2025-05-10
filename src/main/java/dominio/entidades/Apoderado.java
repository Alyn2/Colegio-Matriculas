package dominio.entidades;

public class Apoderado extends Persona{
    private int id;
    private String celular;
    private String correo;

    public Apoderado(){
        
    }

    public Apoderado(int id, String celular, String correo, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String genero, DocumentoIdentidad documentoIdentidad) {
        super(primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno, genero, documentoIdentidad);
        this.id = id;
        this.celular = celular;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
   
}
