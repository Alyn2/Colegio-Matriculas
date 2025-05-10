package dominio.entidades;

public class Docente extends Persona{
    private int id;
    private String titulo;
    private String telefono;
    private String correo;

    public Docente() {
    }

    public Docente(int id, String titulo, String telefono, String correo, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String genero, DocumentoIdentidad documentoIdentidad) {
        super(primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno, genero, documentoIdentidad);
        this.id = id;
        this.titulo = titulo;
        this.telefono = telefono;
        this.correo = correo;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
   
}
